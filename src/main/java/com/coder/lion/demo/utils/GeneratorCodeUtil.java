package com.coder.lion.demo.utils;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.logging.log4j.util.Strings;

import java.util.*;

import java.util.*;


/**
 * 自动生成mybatis-plus的相关代码
 */
public class GeneratorCodeUtil {

    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if(StringUtils.isNotBlank(ipt)){
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        String[][] talbes = {
                {"user", "t_user"}
        };
        if (talbes.length > 0) {
            Arrays.stream(talbes).forEach(table -> {
                build(table[0], table[1]);
            });
        } else {
            build(null, null);
        }
    }

    private static void build(String module, String tables) {
        String projectPath = System.getProperty("user.dir");
        //获取模块名

        String moduleName = Strings.isBlank(module) ? scanner("请输入模块名") : module;
        String entityDir = projectPath + "/src/main/java/com/coder/lion/demo/model/entity/";
        String mapperDir = projectPath + "/src/main/java/com/coder/lion/demo/mapper/";
        String mapperXmlDir = projectPath + "/src/main/resources/mapper/";
        String serviceDir = projectPath + "/src/main/java/com/coder/lion/demo/service/" + moduleName + "/service/";
        String serviceImplDir = projectPath + "/src/main/java/com/coder/lion/demo/service/" + moduleName + "/service/impl";

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

         String authorName = scanner("请输入author（代码注释用）");

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setAuthor(authorName);
        gc.setOpen(false);
        //实体属性 Swagger2 注解
        gc.setSwagger2(false);
        gc.setBaseResultMap(true);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://101.132.140.20:3306/health?characterEncoding=utf-8&useSSL=false");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("lz1024cx");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.coder.lion.demo");
        pc.setEntity("model.entity");
        pc.setMapper("mapper");
        pc.setService("service." + moduleName + ".service");
        pc.setServiceImpl("service." + moduleName + ".service.impl");
        mpg.setPackageInfo(pc);

        //设置自定义输出目录（多模块项目使用）
        Map<String, String> pathInfo = new HashMap<>();
        pathInfo.put(ConstVal.ENTITY_PATH, entityDir);
        pathInfo.put(ConstVal.MAPPER_PATH, mapperDir);
        pathInfo.put(ConstVal.XML_PATH, mapperXmlDir);
        pathInfo.put(ConstVal.SERVICE_PATH, serviceDir);
        pathInfo.put(ConstVal.SERVICE_IMPL_PATH, serviceImplDir);
        pc.setPathInfo(pathInfo);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return mapperXmlDir + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);

        strategy.setInclude(Strings.isBlank(module) ? scanner("表名，多个英文逗号分割").split(",") : tables.split(","));
        strategy.setControllerMappingHyphenStyle(true);
        //strategy.setTablePrefix(moduleName + "_");
        strategy.setTablePrefix("");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }


}
