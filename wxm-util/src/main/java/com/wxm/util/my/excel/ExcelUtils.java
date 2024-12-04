package com.wxm.util.my.excel;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-03-14 17:19:09
 */
@Slf4j
public class ExcelUtils {
    private final static String EXCEL2003 = "xls";
    private final static String EXCEL2007 = "xlsx";

    public static <T> List<DicKeyDto> dicKeys(Class<T> clazz) {
        List<DicKeyDto> list = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            ExcelColumn annotation = field.getAnnotation(ExcelColumn.class);
            if (annotation != null && StringUtils.hasText(annotation.dicKey())) {
                DicKeyDto dto=new DicKeyDto();
                dto.setKey(field.getName());
                dto.setDic(annotation.dicKey());

                list.add(dto);
            }
        }
        return list;
    }

    /**
     * TODO
     *
     * @param path
     * @param cls
     * @param file
     * @return java.util.List<T>
     * @throws
     * @Author 王森明-wangsm
     * @Date 2023-08-17 18:32
     * @version 1.0.0
     **/
    public static <T> List<T> readExcel(String path, Class<T> cls, MultipartFile file) {

        String fileName = file.getOriginalFilename();
        if (!fileName.toLowerCase().matches("^.+\\.(?i)(xls)$") && !fileName.toLowerCase().matches("^.+\\.(?i)(xlsx)$")) {
            log.error("上传文件格式不正确");
        }
        List<T> dataList = new ArrayList<>();
        Workbook workbook = null;
        try {
            InputStream is = file.getInputStream();
            if (fileName.endsWith(EXCEL2007)) {
//                FileInputStream is = new FileInputStream(new File(path));
                workbook = new XSSFWorkbook(is);
            }
            if (fileName.endsWith(EXCEL2003)) {
//                FileInputStream is = new FileInputStream(new File(path));
                workbook = new HSSFWorkbook(is);
            }
            if (workbook != null) {
                //类映射  注解 value-->bean columns
                Map<String, List<Field>> classMap = new HashMap<>();
                List<Field> fields = Stream.of(cls.getDeclaredFields()).collect(Collectors.toList());
                fields.forEach(
                        field -> {
                            ExcelColumn annotation = field.getAnnotation(ExcelColumn.class);
                            if (annotation != null) {
                                String value = annotation.value();
                                if (!StringUtils.hasText(value)) {
                                    return;//return起到的作用和continue是相同的 语法
                                }
                                if (!classMap.containsKey(value)) {
                                    classMap.put(value, new ArrayList<>());
                                }
                                field.setAccessible(true);
                                classMap.get(value).add(field);
                            }
                        }
                );
                //索引-->columns
                Map<Integer, List<Field>> reflectionMap = new HashMap<>(16);
                //默认读取第一个sheet
                Sheet sheet = workbook.getSheetAt(0);

                boolean firstRow = true;
                for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    //首行  提取注解
                    if (firstRow) {
                        for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
                            Cell cell = row.getCell(j);
                            String cellValue = getCellValueNew(cell);

                            cellValue = cellValue.replaceAll(" ", "").replaceAll("\\*", "");
                            if (classMap.containsKey(cellValue)) {
                                reflectionMap.put(j, classMap.get(cellValue));
                                continue;
                            }
                            Set<String> keys = classMap.keySet();
                            Iterator<String> ites = keys.iterator();
                            while (ites.hasNext()) {
                                String key = ites.next();
                                String[] names = key.split("\\|");
                                String finalCellValue = cellValue;
                                if (Arrays.asList(names).stream().anyMatch(n -> n.replaceAll(" ", "").equals(finalCellValue))) {
                                    reflectionMap.put(j, classMap.get(key));
                                    break;
                                }
                            }
                        }
                        firstRow = false;
                    } else {
                        //忽略空白行
                        if (row == null) {
                            continue;
                        }
                        try {
                            T t = cls.newInstance();
                            //判断是否为空白行
                            boolean allBlank = true;
                            for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
                                if (reflectionMap.containsKey(j)) {
                                    Cell cell = row.getCell(j);
                                    String cellValue = getCellValueNew(cell);
                                    if (StringUtils.hasText(cellValue)) {
                                        allBlank = false;
                                    }
                                    List<Field> fieldList = reflectionMap.get(j);
                                    fieldList.forEach(
                                            x -> {
                                                try {
                                                    handleField(t, cellValue, x);
                                                } catch (Exception e) {
                                                    log.error(String.format("reflect field:%s value:%s exception!", x.getName(), cellValue), e);
                                                }
                                            }
                                    );
                                }
                            }
                            if (!allBlank) {
                                dataList.add(t);
                            } else {
                                log.warn(String.format("row:%s is blank ignore!", i));
                            }
                        } catch (Exception e) {
                            log.error(String.format("parse row:%s exception!", i), e);
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error(String.format("parse excel exception!"), e);
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (Exception e) {
                    log.error(String.format("parse excel exception!"), e);
                }
            }
        }
        return dataList;
    }

    /**
     * TODO
     *
     * @param t
     * @param value
     * @param field
     * @return void
     * @throws
     * @Author 王森明-wangsm
     * @Date 2023-08-17 18:32
     * @version 1.0.0
     **/
    private static <T> void handleField(T t, String value, Field field) throws Exception {
        Class<?> type = field.getType();
        if (type == null || type == void.class || !StringUtils.hasText(value)) {
            return;
        }
        if (type == Object.class) {
            field.set(t, value);
            //数字类型
        } else if (type.getSuperclass() == null || type.getSuperclass() == Number.class) {
            if (type == int.class || type == Integer.class) {
                field.set(t, new BigDecimal(value).intValue());
            } else if (type == long.class || type == Long.class) {
                field.set(t, new BigDecimal(value).longValue());
            } else if (type == byte.class || type == Byte.class) {
                field.set(t, new BigDecimal(value).byteValue());
            } else if (type == short.class || type == Short.class) {
                field.set(t, new BigDecimal(value).shortValue());
            } else if (type == double.class || type == Double.class) {
                field.set(t, new BigDecimal(value).doubleValue());
            } else if (type == float.class || type == Float.class) {
                field.set(t, Float.valueOf(value));
            } else if (type == char.class || type == Character.class) {
                field.set(t, value.charAt(0));
            } else if (type == boolean.class) {
                field.set(t, Boolean.valueOf(value));
            } else if (type == BigDecimal.class) {
                field.set(t, new BigDecimal(value));
            }
        } else if (type == Boolean.class) {
            field.set(t,Boolean.valueOf(value));
        } else if (type == Date.class) {
            //
            field.set(t, value);
        } else if (type == String.class) {
            field.set(t, value);
        } else {
            Constructor<?> constructor = type.getConstructor(String.class);
            field.set(t, constructor.newInstance(value));
        }
    }


    /**
     * TODO
     *
     * @param cell
     * @return java.lang.String
     * @throws
     * @Author 王森明-wangsm
     * @Date 2023-08-17 18:33
     * @version 1.0.0
     **/
    private static String getCellValueNew(Cell cell) {
        if (cell == null) {
            return "";
        }


        if (cell.getCellType() == CellType.NUMERIC) {
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                return HSSFDateUtil.getJavaDate(cell.getNumericCellValue()).toString();
            } else {
                //return new BigDecimal(cell.getNumericCellValue()).toString();
                return cell.getNumericCellValue() + "";
            }

        } else if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        } else if (cell.getCellType() == CellType.FORMULA) {
            return cell.getCellFormula();
        } else if (cell.getCellType() == CellType.BLANK) {
            return "";
        } else if (cell.getCellType() == CellType.BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == CellType.ERROR) {
            return "ERROR";
        } else {
            return cell.toString().trim();
        }

    }
}
