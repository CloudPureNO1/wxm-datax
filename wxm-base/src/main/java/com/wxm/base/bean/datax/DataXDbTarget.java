package com.wxm.base.bean.datax;

import com.wxm.base.enums.JobEnum;
import com.wxm.base.exception.JobException;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p></p>
 * <p>
 * dbTarget: {
 * 'name': 'mysqlwriter', // 默认mysql
 * 'jdbcUrl': '', // 只能一个数据源
 * 'username': '',
 * 'password': '',
 * 'batchSize': '1024', // 默认值1024 一次性批量提交的记录数大小，该值可以极大减少DataX与Mysql的网络交互次数，并提升整体吞吐量。但是该值设置过大可能会造成DataX运行进程OOM情况。
 * 'column': '', // 必须指定，不能留空；如果要依次写入全部列，使用表示, 例如: "column": [""]，强烈不建议,多个以逗号分割
 * 'table': '', // 支持写入一个或者多个表。当配置为多张表时，必须确保所有表结构保持一致。多个以逗号分割
 * 'preSql': '', // 写入数据到目的表前，会先执行这里的标准语句。例在导入表前先进行删除操作：["delete from 表名"] 多个以逗号分割
 * 'postSql': '', // 写入数据到目的表后，会执行这里的标准语句。（原理同 preSql ） 多个以逗号分割
 * 'session': '', // DataX在获取Mysql连接时，执行session指定的SQL语句，修改当前connection session属性
 * 'writeMode': 'update' // 默认insert ，可选insert/replace/update
 * <p>
 * },
 * </p>
 *
 * @author 王森明
 * @date 2022/7/8 9:36
 * @since 1.0.0
 */
@Data
public class DataXDbTarget implements java.io.Serializable {
    private String name;
    private String jdbcUrl;
    private String username;
    private String password;
    private String batchSize;
    private String table;
    private String column;
    private String preSql;
    private String postSql;
    private String session;
    private String writeMode;


    public Map<String, Object> buildWriter() {
        Map<String, Object> parameter = new HashMap<>();
        if (StringUtils.hasLength(username)) {
            parameter.put("username", username.trim());
        }
        if (StringUtils.hasLength(password)) {
            parameter.put("password", password.trim());
        }
        if (StringUtils.hasLength(preSql)) {
            parameter.put("preSql", preSql.trim().split(","));
        }
        if (StringUtils.hasLength(session)) {
            parameter.put("session", session.trim().split(","));
        }
        if (StringUtils.hasLength(writeMode)) {
            parameter.put("writeMode", writeMode.trim());
        }
        if (StringUtils.hasLength(postSql)) {
            parameter.put("postSql", postSql.trim().split(","));
        }
        if (StringUtils.hasLength(batchSize) && !"0".equals(batchSize)) {
            parameter.put("batchSize", Long.valueOf(batchSize.trim()));
        }
        parameter.put("column", buildColumns());
        List<Map<String, Object>> connection = new ArrayList<>();
        Map<String, Object> connectionItem = new HashMap<>();
        if (StringUtils.hasLength(jdbcUrl)) {
            connectionItem.put("jdbcUrl", jdbcUrl.trim());
        }
        connectionItem.put("table", buildTables());
        connection.add(connectionItem);
        parameter.put("connection", connection);

        Map<String, Object> map = new HashMap<>();
        map.put("parameter", parameter);
        if(StringUtils.hasLength(name)) {
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
