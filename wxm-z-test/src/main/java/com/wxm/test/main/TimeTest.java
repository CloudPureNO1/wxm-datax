package com.wxm.test.main;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/7/4 13:31
 * @since 1.0.0
 */
@Slf4j
public class TimeTest {
    public static void main(String[] args) throws UnsupportedEncodingException {
        log.info(">>>>>当前系统时间（毫秒）：{}", System.currentTimeMillis());
        enStr();

    }


    public static String enStr() throws UnsupportedEncodingException {
        String str = "{\n" +
                "    \"transCode\":\"12001\",\n" +
                "    \"cron\":\"0/50 * * * * ?\",\n" +
                "    \"job\":{\n" +
                "        \"setting\":{\n" +
                "            \"speed\":{\n" +
                "                \"channel\":3,\n" +
                "                \"byte\":1048576\n" +
                "            },\n" +
                "            \"errorLimit\":{\n" +
                "                \"record\":0,\n" +
                "                \"percentage\":0.02\n" +
                "            }\n" +
                "        },\n" +
                "        \"content\":[\n" +
                "            {\n" +
                "                \"reader\":{\n" +
                "                    \"name\":\"mysqlreader\",\n" +
                "                    \"parameter\":{\n" +
                "                        \"username\":\"disabled\",\n" +
                "                        \"password\":\"disabled\",\n" +
                "                        \"splitPk\":\"\",\n" +
                "                        \"column\":[\n" +
                "                            \"aac101\",\n" +
                "                            \"aae135\",\n" +
                "                            \"aac003\",\n" +
                "                            \"aac004\",\n" +
                "                            \"aac006\",\n" +
                "                            \"aac106\",\n" +
                "                            \"aac107\",\n" +
                "                            \"aac108\",\n" +
                "                            \"aac109\",\n" +
                "                            \"aac110\",\n" +
                "                            \"aac111\",\n" +
                "                            \"aac112\",\n" +
                "                            \"aac113\",\n" +
                "                            \"aac114\",\n" +
                "                            \"aac115\",\n" +
                "                            \"aac116\",\n" +
                "                            \"aac117\",\n" +
                "                            \"aac118\",\n" +
                "                            \"create_time\",\n" +
                "                            \"update_time\"\n" +
                "                        ],\n" +
                "                        \"connection\":[\n" +
                "                            {\n" +
                "                                \"jdbcUrl\":[\n" +
                "                                    \"jdbc:mysql://192.168.179.125:3306/disabled?useSSL=false&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true&useAffectedRows=true&zeroDateTimeBehavior=convertToNull\"\n" +
                "                                ],\n" +
                "                                \"table\":[\n" +
                "                                    \"ac01\"\n" +
                "                                ]\n" +
                "                            }\n" +
                "                        ],\n" +
                "                        \"where\":\"\"\n" +
                "                    }\n" +
                "                },\n" +
                "                \"writer\":{\n" +
                "                    \"name\":\"mysqlwriter\",\n" +
                "                    \"parameter\":{\n" +
                "                        \"username\":\"root\",\n" +
                "                        \"password\":\"root123\",\n" +
                "                        \"column\":[\n" +
                "                            \"aac101\",\n" +
                "                            \"aae135\",\n" +
                "                            \"aac003\",\n" +
                "                            \"aac004\",\n" +
                "                            \"aac006\",\n" +
                "                            \"aac106\",\n" +
                "                            \"aac107\",\n" +
                "                            \"aac108\",\n" +
                "                            \"aac109\",\n" +
                "                            \"aac110\",\n" +
                "                            \"aac111\",\n" +
                "                            \"aac112\",\n" +
                "                            \"aac113\",\n" +
                "                            \"aac114\",\n" +
                "                            \"aac115\",\n" +
                "                            \"aac116\",\n" +
                "                            \"aac117\",\n" +
                "                            \"aac118\",\n" +
                "                            \"create_time\",\n" +
                "                            \"update_time\"\n" +
                "                        ],\n" +
                "                        \"connection\":[\n" +
                "                            {\n" +
                "                                \"table\":[\n" +
                "                                    \"ac01\"\n" +
                "                                ],\n" +
                "                                \"jdbcUrl\":\"jdbc:mysql://192.168.179.125:3306/mtms?useSSL=false&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true&useAffectedRows=true&zeroDateTimeBehavior=convertToNull\"\n" +
                "                            }\n" +
                "                        ],\n" +
                "                        \"writeMode\":\"update\",\n" +
                "                        \"preSql\":[\n" +
                "\n" +
                "                        ]\n" +
                "                    }\n" +
                "                }\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}";
        String strRs = Base64.getEncoder().encodeToString(str.getBytes("UTF-8"));
        System.out.println(strRs);
        return strRs;
    }
}