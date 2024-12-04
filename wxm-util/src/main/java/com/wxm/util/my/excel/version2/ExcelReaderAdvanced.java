package com.wxm.util.my.excel.version2;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.*;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-11 11:48:56
 */


public class ExcelReaderAdvanced {

    public static void main(String[] args) {
        try {
            readXLSXFile("C:\\Users\\wangsm\\Desktop\\11\\test.xlsx");
        } catch (IOException | SAXException e) {
            e.printStackTrace();
        } catch (OpenXML4JException e) {
            throw new RuntimeException(e);
        }
    }

    private static void readXLSXFile(String path) throws IOException, SAXException, OpenXML4JException {
        OPCPackage pkg = OPCPackage.open(path);
        ReadOnlySharedStringsTable sst = new ReadOnlySharedStringsTable(pkg);
        XSSFReader r = new XSSFReader(pkg);

        XMLReader parser = XMLReaderFactory.createXMLReader();
        ContentHandler handler = new SheetHandler(sst);
        parser.setContentHandler(handler);

        try (InputStream sheetStream = r.getSheetsData().next()) {
            parser.parse(new InputSource(sheetStream));
        }
    }

    static class SheetHandler extends org.xml.sax.helpers.DefaultHandler {
        private final ReadOnlySharedStringsTable sst;
        private String lastContents;
        private boolean nextIsString;
        private String cellPosition;
        private int rowIndex = -1;
        private int colIndex = -1;
        private LinkedHashMap<String, String> rowContents = new LinkedHashMap<>();
        private List<String> list=new ArrayList<>();
        private List<List<String>>rows=new ArrayList<>();
        public LinkedHashMap<String, String> getRowContents() {
            return rowContents;
        }

        public SheetHandler(ReadOnlySharedStringsTable sst) {
            this.sst = sst;
        }

        @Override
        public void startElement(String uri, String localName, String qName, org.xml.sax.Attributes attributes) throws SAXException {

            if (qName.equals("row")) {
                rowIndex++;
                colIndex = -1;
                list.clear();
            }else  if ("c".equals(localName)) {
                cellPosition = attributes.getValue("r");
                String cellType = attributes.getValue("t");
                nextIsString = "s".equals(cellType);

                colIndex++;
            }
            lastContents = "";
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (qName.equals("row")) {
                // 这里执行每行结束后需要的操作
                System.out.println((rowIndex+1)+":"+String.join(",",list)); // 比如打印一个换行符来分隔各行数据
                rows.add(list);
            }
            if (nextIsString) {
                int idx = Integer.parseInt(lastContents);
                lastContents = sst.getItemAt(idx).getString(); // 注意这里的路径和方法名可能因版本而异
                nextIsString = false;
            }
            if ("v".equals(localName)) {
                // 数据读取结束后，将单元格坐标,内容存入map中
                if (cellPosition != null && !cellPosition.isEmpty()) {
                    rowContents.put(cellPosition, lastContents);
                    list.add(lastContents);

                }
            }
        }



        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            lastContents += new String(ch, start, length);

        }
    }
}