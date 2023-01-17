package com.wxm.base.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p></p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/5/5 11:17
 * @since 1.0.0
 */

@NoArgsConstructor
@Data
public class JsonBean {

    /**
     * job : {"setting":{"speed":{"channel":3,"byte":1048576},"errorLimit":{"record":0,"percentage":0.02}},"content":[{"reader":{"name":"mysqlreader","parameter":{"username":"disabled","password":"disabled","splitPk":"","column":["aaa001","aaa002","aaa005","aaa104","aaa105","prseno","aaa010","aaa011"],"connection":[{"jdbcUrl":["jdbc:mysql://192.168.179.125:3306/disabled?useSSL=false&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true&useAffectedRows=true&zeroDateTimeBehavior=convertToNull"],"table":["dis_aa01"]}],"where":""}},"writer":{"name":"mysqlwriter","parameter":{"username":"root","password":"root123","column":["aaa001","aaa002","aaa005","aaa104","aaa105","prseno","aaa010","aaa011"],"connection":[{"table":["dis_aa01"],"jdbcUrl":"jdbc:mysql://192.168.179.125:3306/mtms?useSSL=false&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true&useAffectedRows=true&zeroDateTimeBehavior=convertToNull"}],"writeMode":"update","preSql":[]}}}]}
     */

    private Job job;

    @NoArgsConstructor
    @Data
    public static class Job implements Serializable {
        /**
         * setting : {"speed":{"channel":3,"byte":1048576},"errorLimit":{"record":0,"percentage":0.02}}
         * content : [{"reader":{"name":"mysqlreader","parameter":{"username":"disabled","password":"disabled","splitPk":"","column":["aaa001","aaa002","aaa005","aaa104","aaa105","prseno","aaa010","aaa011"],"connection":[{"jdbcUrl":["jdbc:mysql://192.168.179.125:3306/disabled?useSSL=false&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true&useAffectedRows=true&zeroDateTimeBehavior=convertToNull"],"table":["dis_aa01"]}],"where":""}},"writer":{"name":"mysqlwriter","parameter":{"username":"root","password":"root123","column":["aaa001","aaa002","aaa005","aaa104","aaa105","prseno","aaa010","aaa011"],"connection":[{"table":["dis_aa01"],"jdbcUrl":"jdbc:mysql://192.168.179.125:3306/mtms?useSSL=false&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true&useAffectedRows=true&zeroDateTimeBehavior=convertToNull"}],"writeMode":"update","preSql":[]}}}]
         */

        private Setting setting;
        private List<Content> content;

        @NoArgsConstructor
        @Data
        public static class Setting implements Serializable {
            /**
             * speed : {"channel":3,"byte":1048576}
             * errorLimit : {"record":0,"percentage":0.02}
             */

            private Speed speed;
            private ErrorLimit errorLimit;

            @NoArgsConstructor
            @Data
            public static class Speed implements Serializable {
                /**
                 * channel : 3
                 * byte : 1048576
                 */

                private Integer channel;
                @JSONField(name = "byte")
                private Integer byteX;

            }

            @NoArgsConstructor
            @Data
            public static class ErrorLimit implements Serializable {
                /**
                 * record : 0
                 * percentage : 0.02
                 */

                private Integer record;
                private BigDecimal percentage;
            }
        }

        @NoArgsConstructor
        @Data
        public static class Content implements Serializable {
            /**
             * reader : {"name":"mysqlreader","parameter":{"username":"disabled","password":"disabled","splitPk":"","column":["aaa001","aaa002","aaa005","aaa104","aaa105","prseno","aaa010","aaa011"],"connection":[{"jdbcUrl":["jdbc:mysql://192.168.179.125:3306/disabled?useSSL=false&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true&useAffectedRows=true&zeroDateTimeBehavior=convertToNull"],"table":["dis_aa01"]}],"where":""}}
             * writer : {"name":"mysqlwriter","parameter":{"username":"root","password":"root123","column":["aaa001","aaa002","aaa005","aaa104","aaa105","prseno","aaa010","aaa011"],"connection":[{"table":["dis_aa01"],"jdbcUrl":"jdbc:mysql://192.168.179.125:3306/mtms?useSSL=false&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true&useAffectedRows=true&zeroDateTimeBehavior=convertToNull"}],"writeMode":"update","preSql":[]}}
             */

            private Reader reader;
            private Writer writer;

            @NoArgsConstructor
            @Data
            public static class Reader implements Serializable {
                /**
                 * name : mysqlreader
                 * parameter : {"username":"disabled","password":"disabled","splitPk":"","column":["aaa001","aaa002","aaa005","aaa104","aaa105","prseno","aaa010","aaa011"],"connection":[{"jdbcUrl":["jdbc:mysql://192.168.179.125:3306/disabled?useSSL=false&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true&useAffectedRows=true&zeroDateTimeBehavior=convertToNull"],"table":["dis_aa01"]}],"where":""}
                 */

                private String name;
                private Parameter parameter;

                @NoArgsConstructor
                @Data
                public static class Parameter implements Serializable {
                    /**
                     * username : disabled
                     * password : disabled
                     * splitPk :
                     * column : ["aaa001","aaa002","aaa005","aaa104","aaa105","prseno","aaa010","aaa011"]
                     * connection : [{"jdbcUrl":["jdbc:mysql://192.168.179.125:3306/disabled?useSSL=false&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true&useAffectedRows=true&zeroDateTimeBehavior=convertToNull"],"table":["dis_aa01"]}]
                     * where :
                     */

                    private String username;
                    private String password;
                    private String splitPk;
                    private String where;
                    private Integer fetchSize;
                    private String querySql;
                    private List<String> column;
                    private List<Connection> connection;

                    @NoArgsConstructor
                    @Data
                    public static class Connection implements Serializable {
                        private List<String> jdbcUrl;
                        private List<String> table;
                    }
                }
            }

            @NoArgsConstructor
            @Data
            public static class Writer implements Serializable {
                /**
                 * name : mysqlwriter
                 * parameter : {"username":"root","password":"root123","column":["aaa001","aaa002","aaa005","aaa104","aaa105","prseno","aaa010","aaa011"],"connection":[{"table":["dis_aa01"],"jdbcUrl":"jdbc:mysql://192.168.179.125:3306/mtms?useSSL=false&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true&useAffectedRows=true&zeroDateTimeBehavior=convertToNull"}],"writeMode":"update","preSql":[]}
                 */

                private String name;
                private ParameterX parameter;

                @NoArgsConstructor
                @Data
                public static class ParameterX implements Serializable {
                    /**
                     * username : root
                     * password : root123
                     * column : ["aaa001","aaa002","aaa005","aaa104","aaa105","prseno","aaa010","aaa011"]
                     * connection : [{"table":["dis_aa01"],"jdbcUrl":"jdbc:mysql://192.168.179.125:3306/mtms?useSSL=false&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true&useAffectedRows=true&zeroDateTimeBehavior=convertToNull"}]
                     * writeMode : update
                     * preSql : []
                     */

                    private String username;
                    private String password;
                    private String writeMode;
                    private Integer batchSize;
                    private List<String> postSql;
                    private List<String> session;
                    private List<String> column;
                    private List<ConnectionX> connection;
                    private List<String> preSql;

                    @NoArgsConstructor
                    @Data
                    public static class ConnectionX implements Serializable {
                        /**
                         * table : ["dis_aa01"]
                         * jdbcUrl : jdbc:mysql://192.168.179.125:3306/mtms?useSSL=false&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true&useAffectedRows=true&zeroDateTimeBehavior=convertToNull
                         */

                        private String jdbcUrl;
                        private List<String> table;
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
        String str="{\n" +
                "  \"job\": {\n" +
                "    \"content\": [\n" +
                "      {\n" +
                "        \"reader\": {\n" +
                "          \"parameter\": {\n" +
                "            \"querySql\": \"\",\n" +
                "            \"password\": \"disabled\",\n" +
                "            \"fetchSize\": 100,\n" +
                "            \"column\": [\n" +
                "              \"aac101\",\n" +
                "              \"aae135\",\n" +
                "              \"aac003\",\n" +
                "              \"aac004\",\n" +
                "              \"aac006\",\n" +
                "              \"aac106\",\n" +
                "              \"aac107\",\n" +
                "              \"aac108\",\n" +
                "              \"aac109\",\n" +
                "              \"aac110\",\n" +
                "              \"aac111\",\n" +
                "              \"aac112\",\n" +
                "              \"aac113\",\n" +
                "              \"aac114\",\n" +
                "              \"aac115\",\n" +
                "              \"aac116\",\n" +
                "              \"aac117\",\n" +
                "              \"aac118\",\n" +
                "              \"create_time\",\n" +
                "              \"update_time\"\n" +
                "            ],\n" +
                "            \"where\": \"\",\n" +
                "            \"connection\": [\n" +
                "              {\n" +
                "                \"jdbcUrl\": [\n" +
                "                  \"jdbc:mysql://192.168.179.125:3306/disabled?useSSL=false&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true&useAffectedRows=true&zeroDateTimeBehavior=convertToNull\"\n" +
                "                ],\n" +
                "                \"table\": [\n" +
                "                  \"ac01\"\n" +
                "                ]\n" +
                "              }\n" +
                "            ],\n" +
                "            \"splitPk\": \"\",\n" +
                "            \"username\": \"disabled\"\n" +
                "          },\n" +
                "          \"name\": \"mysqlreader\"\n" +
                "        },\n" +
                "        \"writer\": {\n" +
                "          \"parameter\": {\n" +
                "            \"postSql\": \"\",\n" +
                "            \"password\": \"root123\",\n" +
                "            \"column\": [\n" +
                "              \"aac101\",\n" +
                "              \"aae135\",\n" +
                "              \"aac003\",\n" +
                "              \"aac004\",\n" +
                "              \"aac006\",\n" +
                "              \"aac106\",\n" +
                "              \"aac107\",\n" +
                "              \"aac108\",\n" +
                "              \"aac109\",\n" +
                "              \"aac110\",\n" +
                "              \"aac111\",\n" +
                "              \"aac112\",\n" +
                "              \"aac113\",\n" +
                "              \"aac114\",\n" +
                "              \"aac115\",\n" +
                "              \"aac116\",\n" +
                "              \"aac117\",\n" +
                "              \"aac118\",\n" +
                "              \"create_time\",\n" +
                "              \"update_time\"\n" +
                "            ],\n" +
                "            \"connection\": [\n" +
                "              {\n" +
                "                \"jdbcUrl\": \"jdbc:mysql://192.168.179.125:3306/mtms?useSSL=false&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true&useAffectedRows=true&zeroDateTimeBehavior=convertToNull\",\n" +
                "                \"table\": [\n" +
                "                  \"ac01\"\n" +
                "                ]\n" +
                "              }\n" +
                "            ],\n" +
                "            \"writeMode\": \"update\",\n" +
                "            \"batchSize\": 1024,\n" +
                "            \"username\": \"root\",\n" +
                "            \"preSql\": \"delete from ac01 where aac004='2'\"\n" +
                "          },\n" +
                "          \"name\": \"mysqlwriter\"\n" +
                "        }\n" +
                "      }\n" +
                "    ],\n" +
                "    \"setting\": {\n" +
                "      \"errorLimit\": {\n" +
                "        \"record\": 0,\n" +
                "        \"percentage\": 0\n" +
                "      },\n" +
                "      \"speed\": {\n" +
                "        \"byte\": 1048576,\n" +
                "        \"channel\": 3\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";

        JsonBean jsonBean = JSON.parseObject(str,JsonBean.class);
        System.out.println(JSON.toJSONString(jsonBean));
    }
}
