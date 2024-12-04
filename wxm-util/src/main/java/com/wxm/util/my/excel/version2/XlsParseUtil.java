package com.wxm.util.my.excel.version2;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-10 10:29:14
 */
@Slf4j
public class XlsParseUtil {
    public static void parseXls(File file) throws IOException {
        InputStream inputStream=null;
        try {
            // 加载.xls文件
//            File file = new File(filePath);
            inputStream = new FileInputStream(file);

            // 使用POIFSFileSystem从InputStream创建workbook
            POIFSFileSystem fs = new POIFSFileSystem(inputStream);
            // 使用HSSFWorkbookFactory创建workbook
            Workbook workbook = new HSSFWorkbook(fs);


            HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);


            // 解析sheet
            DataFormatter formatter = new DataFormatter();
            for (int r = sheet.getFirstRowNum(); r <= sheet.getLastRowNum(); r++) {
                HSSFRow row = sheet.getRow(r);
                if (row != null) {
                    StringBuilder builder=new StringBuilder();
                    for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
                        Cell cell=row.getCell(c);
//                        String value = formatter.formatCellValue(cell);

                        String value = getString(workbook, formatter, row, c);
                        builder.append(value);
                        if(c<row.getLastCellNum()-1){
                            builder.append(",");
                        }
                    }
                    System.out.println(builder.toString());
                }
            }

            // 关闭输入流
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(inputStream!=null){
                // 关闭输入流
                inputStream.close();
            }
        }
    }

    private static String getString(Workbook workbook, DataFormatter formatter, HSSFRow row, int c) {
        String value="";
        Cell cell= row.getCell(c);
        CellType cellType = cell.getCellType();
        switch (cellType) {
            case STRING:
                value=cell.getStringCellValue();
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    value=String.valueOf(cell.getDateCellValue());
                } else {
                    value=String.valueOf(cell.getNumericCellValue());
                }
                break;
            case BOOLEAN:
                value=String.valueOf(cell.getBooleanCellValue());
                break;
            case FORMULA:
                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                CellValue cellValue = evaluator.evaluate(cell);
                value=cellValue.formatAsString();
                break;
            case BLANK:
                value="";
                break;
            case ERROR:
                value = formatter.formatCellValue(row.getCell(c));
                break;
            default:
                value = formatter.formatCellValue(row.getCell(c));
        }
        return value;
    }

}
