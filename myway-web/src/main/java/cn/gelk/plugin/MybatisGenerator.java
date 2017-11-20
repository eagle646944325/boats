package cn.gelk.plugin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * 自动生成mybatis xml以及对应接口
 * 修改generatorConfig.xml文件，直接执行main()方法
 */
public class MybatisGenerator {

    public static void main(String[] args) throws InvalidConfigurationException, InterruptedException, SQLException, IOException, XMLParserException {
        /**
         * 执行完毕后注意修改两个小地方！
         *    (1) 插件生成的domain实体请继承基类 {@link cn.gelk.domain.base.BaseDomain},
         *        因为 spring-mybatis.xml中已经配置了 <property name="typeAliasesSuperType" value="cn.gelk.domain.base.BaseDomain" />
         *
         *    (2) 插件生成的 mapper 接口请将 extends Mapper<T> 变换成 extends MyBatisMapper<T>
         *         因为 spring-mybatis.xml中已经配置了<property name="markerInterface" value="cn.gelk.markerInterface.MyBatisMapper" />
         *         MyBatisMapper 主要是相对原来的通用mapper里面多了批量插入、更新的接口
         */
        List<String> warnings = new ArrayList<>();
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(MybatisGenerator.class.getClassLoader().getResourceAsStream("plugin/generatorConfig.xml"));
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }
}
