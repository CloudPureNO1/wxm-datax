package com.wxm.base.dto.quartz.in;

import com.wxm.base.bean.JsonBean;
import com.wxm.base.bean.QuartzForm;
import com.wxm.base.bean.datax.*;
import com.wxm.base.dto.BaseDto;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 * <p>
 * {
 * "job": {
 * "content": [
 * {
 * "reader": {
 * "name": "mysqlreader",
 * "parameter": {
 * "querySql": "", #自定义sql,支持多表关联，当用户配置querySql时，直接忽略table、column、where条件的配置。
 * "fetchSize": ""， #默认1024，该配置项定义了插件和数据库服务器端每次批量数据获取条数，该值决定了DataX和服务器端的网络交互次数，能够较大的提升数据抽取性能，注意，该值过大(>2048)可能造成DataX进程OOM
 * "splitPk": "db_id", #仅支持整形型数据切分；如果指定splitPk，表示用户希望使用splitPk代表的字段进行数据分片，如果该值为空，代表不切分，使用单通道进行抽取
 * "column": [], #"*"默认所有列,支持列裁剪，列换序
 * "connection": [
 * {
 * "jdbcUrl": ["jdbc:mysql://IP:3306/database?useUnicode=true&characterEncoding=utf8"],  #支持多数据源
 * "table": [] #支持多张表同时抽取
 * }
 * ],
 * "password": "",
 * "username": "",
 * "where": "" #指定的column、table、where条件拼接SQL，可以指定limit 10，也可以增量数据同步，如果该值为空，代表同步全表所有的信息
 * }
 * },
 * "writer": {
 * "name": "mysqlwriter",
 * "parameter": {
 * "column": [], #必须指定，不能留空；如果要依次写入全部列，使用表示, 例如: "column": [""]，强烈不建议
 * "batchSize": "", #默认值1024 一次性批量提交的记录数大小，该值可以极大减少DataX与Mysql的网络交互次数，并提升整体吞吐量。但是该值设置过大可能会造成DataX运行进程OOM情况。
 * "connection": [
 * {
 * "jdbcUrl": "jdbc:mysql://IP:3306/database?useUnicode=true&characterEncoding=utf8",
 * "table": [] #支持写入一个或者多个表。当配置为多张表时，必须确保所有表结构保持一致。
 * }
 * ],
 * "password": "",
 * "preSql": [], #写入数据到目的表前，会先执行这里的标准语句。例在导入表前先进行删除操作：["delete from 表名"]
 * "postSql":[], #写入数据到目的表后，会执行这里的标准语句。（原理同 preSql ）
 * "session": [], #DataX在获取Mysql连接时，执行session指定的SQL语句，修改当前connection session属性
 * "username": "",
 * "writeMode": "" #默认insert ，可选insert/replace/update
 * }
 * }
 * }
 * ],
 * "setting": {
 * "speed": { #流量控制
 * "byte": 1048576, #控制传输速度，单位为byte/s，DataX运行会尽可能达到该速度但是不超过它
 * "channel": ""  #控制同步时的并发数
 * }
 * "errorLimit": { #脏数据控制
 * "record": 0 #对脏数据最大记录数阈值（record值）或者脏数据占比阈值（percentage值，当数量或百分比，DataX Job报错退出
 * }
 * }
 * }
 * }
 *
 * </p>
 *
 * @author 王森明
 * @date 2022/7/7 15:54
 * @since 1.0.0
 */
@Data
public class Quartz12001In extends BaseDto implements java.io.Serializable {
    private Map<String, Object> job = new HashMap<>();
    private DataXSpeed speed;
    private DataXErrorLimit errorLimit;
    private DataXDbSource dbSource;
    private DataXDbTarget dbTarget;
    private QuartzForm quartzForm;

    public String getCron() {
        return quartzForm.getCron().trim();
    }

    public String getTables() {
        if (StringUtils.hasLength(dbSource.getTable())) {
            return dbSource.getTable().replaceAll(",", "`");
        }
        if (StringUtils.hasLength(dbTarget.getTable())) {
            return dbTarget.getTable().replaceAll(",", "`");
        }
        return "";
    }

    public String getReaderName() {
        return dbSource.getName().replaceAll(" ", "");
    }

    public String getWriterName() {
        return dbTarget.getName().replaceAll(" ", "");
    }


    public Map<String, Object> getJob(boolean build) {
        Map<String,Object>rs=new HashMap<>();
        if (!build) {
            rs.put("job",job);
            return rs;
        }

        // speed
        Map<String, Object> setting = new HashMap<>();
        setting.put("speed", speed.buildSpeed());
        setting.put("errorLimit", errorLimit.buildErrorLimit());
        // errorLimit
        List<Map<String, Object>> content = new ArrayList<>();
        // contentItem
        Map<String, Object> contentItem = new HashMap<>();
        contentItem.put("reader", dbSource.buildReader());
        contentItem.put("writer", dbTarget.buildWriter());
        content.add(contentItem);


        Map<String, Object> map = new HashMap<>();
        map.put("setting", setting);
        map.put("content", content);
        rs.put("job",map);
        return rs;
    }


}
