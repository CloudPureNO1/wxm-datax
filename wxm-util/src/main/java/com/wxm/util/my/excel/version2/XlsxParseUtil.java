package com.wxm.util.my.excel.version2;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 在Java中，使用Apache POI库可以有效地从Excel文件（.xlsx）中读取数据，
 * 特别是当处理大文件时，可以使用基于流的方式避免内存溢出。
 * 下面是一个使用XSSFReader类的示例，该类是Apache POI提供的用于读取.xlsx文件的流式API。
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-10 10:38:20
 */
public class XlsxParseUtil {

    public static void parseXlsx(File file) throws IOException {
        OPCPackage pkg=null;
        ReadOnlySharedStringsTable sst=null;
        XSSFReader r=null;
        XMLReader parser=null;
        try{
            pkg = OPCPackage.open(file);
            sst = new ReadOnlySharedStringsTable(pkg);
            r = new XSSFReader(pkg);

            parser = XMLReaderFactory.createXMLReader();
            ContentHandler handler = new SheetHandler(sst);
            parser.setContentHandler(handler);

            try (InputStream sheetStream = r.getSheetsData().next()) {
                parser.parse(new InputSource(sheetStream));
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            if(parser!=null) {
                parser=null;
            }
            if(r!=null){
                r=null;
            }
            if(sst!=null){
                sst=null;
            }
            if(pkg!=null){
                pkg.close();
            }
        }

    }



    static class SheetHandler extends org.xml.sax.helpers.DefaultHandler {
        private final ReadOnlySharedStringsTable sst;
        private String lastContents;
        private boolean nextIsString;
        private String cellPosition;
        private int rowIndex = -1;
        private LinkedHashMap<String, String> rowContents = new LinkedHashMap<>();
        private List<String> list=new ArrayList<>();
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
                list.clear();
            }else if ("c".equals(localName)) {
                cellPosition = attributes.getValue("r");
                String cellType = attributes.getValue("t");
                nextIsString = "s".equals(cellType);
            }
            lastContents = "";
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (qName.equals("row")) {
                // 这里执行每行结束后需要的操作
                System.out.println((rowIndex+1)+":"+String.join(",",list)); // 比如打印一个换行符来分隔各行数据
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
