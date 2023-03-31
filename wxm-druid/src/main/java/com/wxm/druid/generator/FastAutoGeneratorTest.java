package com.wxm.druid.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.fill.Property;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p>mybatis-plus 代码生成器</p>
 * <p></p>
 *
 * @author 王森明
 * @date 2022/4/28 11:26
 * @since 1.0.0
 */
public class FastAutoGeneratorTest {


    /**
     * 数据源配置
     */
    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG =
            //new DataSourceConfig.Builder("jdbc:mysql://127.0.0.1:3306/qjob?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true&useAffectedRows=true&zeroDateTimeBehavior=convertToNull", "qjob", "qjob");
//            new DataSourceConfig.Builder("jdbc:mysql://127.0.0.1:3306/wxm?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true&useAffectedRows=true&zeroDateTimeBehavior=convertToNull", "wxm", "wxm")
//            new DataSourceConfig.Builder("jdbc:mysql://10.20.1.69:13367/admdvsmnit?characterEncoding=UTF-8", "admdvsmnit", "admdvsmnit")
            new DataSourceConfig.Builder("jdbc:mysql://rdsnimyju26nerqo.mysql.rds.aliyuncs.com:3306/disabled?characterEncoding=UTF-8", "disabled2", "Disabled2")
                    .dbQuery(new MySqlQuery())
                    //.schema("qjob")
                    .typeConvert(new MySqlTypeConvert())
                    .keyWordsHandler(new MySqlKeyWordsHandler());

    /**
     * 执行 run
     * new GlobalConfig.Builder()
     * .fileOverride()
     * .outputDir("/opt/baomidou")
     * .author("baomidou")
     * .enableKotlin()
     * .enableSwagger()
     * .dateType(DateType.TIME_PACK)
     * .commentDate("yyyy-MM-dd")
     * .build();
     * <p>
     * new PackageConfig.Builder()
     * .parent("com.baomidou.mybatisplus.samples.generator")
     * .moduleName("sys")
     * .entity("po")
     * .service("service")
     * .serviceImpl("service.impl")
     * .mapper("mapper")
     * .xml("mapper.xml")
     * .controller("controller")
     * .other("other")
     * .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D://"))
     * .build();
     * <p>
     * <p>
     * new StrategyConfig.Builder()
     * .enableCapitalMode()
     * .enableSkipView()
     * .disableSqlFilter()
     * .likeTable(new LikeTable("USER"))
     * .addInclude("t_simple")
     * .addTablePrefix("t_", "c_")
     * .addFieldSuffix("_flag")
     * .build();
     * <p>
     * <p>
     * new StrategyConfig.Builder()
     * .entityBuilder()
     * .superClass(BaseEntity.class)
     * .disableSerialVersionUID()
     * .enableChainModel()
     * .enableLombok()
     * .enableRemoveIsPrefix()
     * .enableTableFieldAnnotation()
     * .enableActiveRecord()
     * .versionColumnName("version")
     * .versionPropertyName("version")
     * .logicDeleteColumnName("deleted")
     * .logicDeletePropertyName("deleteFlag")
     * .naming(NamingStrategy.no_change)
     * .columnNaming(NamingStrategy.underline_to_camel)
     * .addSuperEntityColumns("id", "created_by", "created_time", "updated_by", "updated_time")
     * .addIgnoreColumns("age")
     * .addTableFills(new Column("create_time", FieldFill.INSERT))
     * .addTableFills(new Property("updateTime", FieldFill.INSERT_UPDATE))
     * .idType(IdType.AUTO)
     * .formatFileName("%sEntity")
     * .build();
     */
    public static void main(String[] args) throws SQLException {
        FastAutoGenerator.create(DATA_SOURCE_CONFIG)
                // 全局配置
                .globalConfig((scanner, builder) -> builder.author(scanner.apply("请输入作者名称？")).fileOverride())
                // 包配置
                .packageConfig((scanner, builder) -> builder.parent(scanner.apply("请输入包名？")))
                // 策略配置
                .strategyConfig((scanner, builder) -> builder.addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all")))
//                        .addTablePrefix("qrtz_")
                        .controllerBuilder().enableRestStyle().enableHyphenStyle()
                        .entityBuilder().idType(IdType.AUTO).addTableFills(new Property("updateTime", FieldFill.INSERT_UPDATE)).addTableFills(new Column("create_time", FieldFill.INSERT)).enableLombok().enableTableFieldAnnotation().disableSerialVersionUID().addTableFills(
                                new Column("create_time", FieldFill.INSERT)
                        ).build())
                /*
                    模板引擎配置，默认 Velocity 可选模板引擎 Beetl 或 Freemarker
                   .templateEngine(new BeetlTemplateEngine())
                   .templateEngine(new FreemarkerTemplateEngine())
                 */
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();

    }

    // 处理 all 情况
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }
}
