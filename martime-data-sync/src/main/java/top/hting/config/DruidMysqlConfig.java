package top.hting.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


@Configuration
@MapperScan(basePackages = "top.hting.mapper.mysql",
    sqlSessionFactoryRef = "mysqlSessionFactory" )
public class DruidMysqlConfig {

    /**数据源*/

    @Bean(name = "mysqlDatasource")
    @Qualifier("mysqlDatasource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    public DataSource mysqlDatasource() {
        return new DruidDataSource();
    }

    @Bean
    @Primary
    public MybatisSqlSessionFactoryBean mysqlSessionFactory(@Qualifier("mysqlDatasource") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        MybatisConfiguration config = new MybatisConfiguration();
        config.setMapUnderscoreToCamelCase(false);
        config.setCacheEnabled(true);

        // config.setLogImpl(StdOutImpl.class);
        factoryBean.setConfiguration(config);

//        factoryBean.setTypeAliasesPackage("top.hting.mapper.mysql");
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/mysql/*.xml"));
        return factoryBean;
    }


    @Bean
    @Primary
    public DataSourceTransactionManager mysqlTransactionManager(@Qualifier("mysqlDatasource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }



}
