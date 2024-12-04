
package com.wxm.util.my.excel.version2;

import java.io.File;
import java.io.IOException;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-10 9:23:13
 */
public class ExcelStreamReader {

    public static void main(String[] args) throws IOException {

//        String fileName = "E:\\opt\\ephd\\datafiles\\8f102181e84b130483885629612e69f0\\52dd78ba3953-47cd-922f-fdbee1463903\\145528deeed0944f64a303a59b967346\\aa10excel_0_0_20240710163405.xls";
        String fileName = "C:\\Users\\wangsm\\Desktop\\11\\test.xlsx";
        ExcelStreamReader reader = new ExcelStreamReader();
        reader.readExcel(fileName);


    }



    public void readExcel(String fileName) throws IOException {
        File file = new File(fileName);
        boolean isXlsx = file.getName().toLowerCase().endsWith(".xlsx");

        if (isXlsx) {
            System.out.println("文件是.xlsx格式。");
            XlsxParseUtil.parseXlsx(file);
        } else {
            System.out.println("文件不是.xlsx格式。");
            XlsParseUtil.parseXls(file);
        }
    }
}