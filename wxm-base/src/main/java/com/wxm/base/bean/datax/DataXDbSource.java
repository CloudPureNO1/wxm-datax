package com.wxm.base.bean.datax;

import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p></p>
 * <p>
 * *       dbSource: {
 * *         'name': 'mysqlreader', // 默认mysql
 * *         'jdbcUrl': '', // 可以使用多个，但是不建议，多个使用都好分割
 * *         'password': '',
 * *         'username': '',
 * *         'fetchSize': '1024', // 默认1024，该配置项定义了插件和数据库服务器端每次批量数据获取条数，该值决定了DataX和服务器端的网络交互次数，能够较大的提升数据抽取性能，注意，该值过大(>2048)可能造成DataX进程OOM
 * *         'querySql': '', // 自定义sql,支持多表关联，当用户配置querySql时，直接忽略table、column、where条件的配置。
 * *         'splitPk': '', // 仅支持整形型数据切分；如果指定splitPk，表示用户希望使用splitPk代表的字段进行数据分片，如果该值为空，代表不切分，使用单通道进行抽取
 * *         'table': '', // 多个使用逗号分割
 * *         'column': '', // "*" 默认所有列,支持列裁剪，列换序,不建议使用*,多个字段使用都好分割
 * *         'where': '' // 指定的column、table、where条件拼接SQL，可以指定limit 10，也可以增量数据同步，如果该值为空，代表同步全表所有的信息
 * *       },
 * </p>
 *
 * @author 王森明
 * @date 2022/7/8 9:35
 * @since 1.0.0
 */
@Data
public class DataXDbSource implements java.io.Serializable {
    private String name;
    private String jdbcUrl;
    private String username;
    private String password;
    private String fetchSize;
    private String querySql;
    private String splitPk;
    private String table;
    private String column;
    private String where;

    public Map<String, Object> buildReader() {
        Map<String, Object> parameter = new HashMap<>();
        if (StringUtils.hasLength(username)) {
            parameter.put("username", username.trim());
        }
        if (StringUtils.hasLength(password)) {
            parameter.put("password", password.trim());
        }
        if (StringUtils.hasLength(where)) {
            parameter.put("where", where.trim());
        }
        if (StringUtils.hasLength(querySql)) {
            parameter.put("querySql", querySql.trim());
        }
        if (StringUtils.hasLength(fetchSize) && !"0".equals(fetchSize)) {
            parameter.put("fetchSize", Long.valueOf(fetchSize.trim()));
        }
        if (StringUtils.hasLength(splitPk)) {
            parameter.put("splitPk", splitPk.trim());
        }
        parameter.put("column", buildColumns());
        List<Map<String, Object>> connection = new ArrayList<>();
        Map<String, Object> connectionItem = new HashMap<>();
        if (StringUtils.hasLength(jdbcUrl)) {
            connectionItem.put("jdbcUrl", new String[]{jdbcUrl.trim()});
        }
        connectionItem.put("table", buildTables());
        connection.add(connectionItem);
        parameter.put("connection", connection);

        Map<String, Object> map = new HashMap<>();
        map.put("parameter", parameter);
        if (StringUtils.hasLength(name)) {
            map.put("name", name.replaceAll(" ", ""));
        }
        return map;
    }

    public List<String> buildColumns() {
        if (StringUtils.hasLength(column)) {
            return Arrays.stream(column.split(",")).map(item -> item.trim()).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public List<String> buildTables() {
        if (StringUtils.hasLength(table)) {
            return Arrays.stream(table.split(",")).map(item -> item.trim()).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
