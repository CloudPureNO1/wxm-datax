package com.wxm.pattern.templateMethod.real;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 具体的批处理作业类
 *
 * @author 王森明-wangsm
 * @version 1.0.0
 * @date 2024-07-19 15:25:54
 */
//
class DatabaseBatchJob extends BatchJob {
    @Override
    protected List<String> readData() {
// 从数据库读取数据
        System.out.println("Reading data from database...");
        return Arrays.asList("data1", "data2", "data3");
    }

    @Override
    protected List<String> transform(List<String> data) {
        // 转换数据
        System.out.println("Transforming data...");
        return data.stream().map(s -> s.toUpperCase()).collect(Collectors.toList());
    }

    @Override
    protected void writeData(List<String> data) {
        // 将数据写入文件
        System.out.println("Writing data to file...");
    }
}