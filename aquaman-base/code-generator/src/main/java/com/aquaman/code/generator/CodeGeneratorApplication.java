package com.aquaman.code.generator;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * 代码生成器
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/3/1
 * @since 2019/3/1
 */
public class CodeGeneratorApplication {

    private static final String MYSQL_URL = "jdbc:mysql://fanxiweb.mysql.rds.aliyuncs.com:3306/fanxiweb";

    private static final String MYSQL_USER = "fanxiweb";

    private static final String MYSQL_PWD = "q1w2e3r4t5";

    private static final String MYSQL_DRIVER_NAME = "com.mysql.jdbc.Driver";

    private static final String PROJECT_AUTHOR = "wei wang";

    private static final String PROJECT_MODULE_PAGE = "admin";

    private static final String PROJECT_ENTITY_PAGE = "entity.domain";

    private static final String PROJECT_PARENT_PAGE = "com.aquaman.security";

    private static final String CURRENT_EXECUTE_TABLE = "ad_upload_file_info";

    private static final String PROJECT_NAME = "aquaman-admin-os";

    /**
     * 快速入门参考https://mp.baomidou.com/guide/generator.html#%E6%B7%BB%E5%8A%A0%E4%BE%9D%E8%B5%96
     * 配置新增参考https://mp.baomidou.com/config/generator-config.html#logicdeletefieldname
     * @param args
     */
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        //  + "/" + PROJECT_NAME
        final String projectPath = System.getProperty("user.dir") + "/" + PROJECT_NAME;
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor(PROJECT_AUTHOR);
        gc.setOpen(false);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(MYSQL_URL);
        // dsc.setSchemaName("public");
        dsc.setDriverName(MYSQL_DRIVER_NAME);
        dsc.setUsername(MYSQL_USER);
        dsc.setPassword(MYSQL_PWD);
        mpg.setDataSource(dsc);

        // 包配置
        final PackageConfig pc = new PackageConfig();
        // 父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名，缺省：com.baomidou
        pc.setParent(PROJECT_PARENT_PAGE);
        // 父包模块名
        pc.setModuleName(PROJECT_MODULE_PAGE);
        // Entity包名
        pc.setEntity(PROJECT_ENTITY_PAGE);
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/"
                + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        //strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
        strategy.setInclude(CURRENT_EXECUTE_TABLE);
        //strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix("ad" + "_");
        // Boolean类型字段是否移除is前缀（默认 false）
        strategy.setEntityBooleanColumnRemoveIsPrefix(true);
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    /*public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }*/

}
