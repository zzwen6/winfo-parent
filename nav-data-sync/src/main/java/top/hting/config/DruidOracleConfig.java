package top.hting.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.logging.log4j2.Log4j2Impl;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


@Configuration
@MapperScan(basePackages = "top.hting.mapper.oracle",
    sqlSessionFactoryRef = "oracleSessionFactory")
public class DruidOracleConfig {

    /**数据源*/
    @Bean(name = "oracleDatasource")
    @Qualifier("oracleDatasource")
    @ConfigurationProperties(prefix = "spring.datasource.oracle")
    public DataSource oracleDatasource() {
        return new DruidDataSource();
    }


    @Bean
    public MybatisSqlSessionFactoryBean oracleSessionFactory(@Qualifier("oracleDatasource") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();

        MybatisConfiguration config = new MybatisConfiguration();
        config.setMapUnderscoreToCamelCase(false);
        config.setCacheEnabled(true);

        // config.setLogImpl(StdOutImpl.class);
        factoryBean.setConfiguration(config);


        factoryBean.setDataSource(dataSource);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/oracle/*.xml"));
        return factoryBean;
    }


    @Bean
    public DataSourceTransactionManager oracleTransactionManager(@Qualifier("oracleDatasource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }



}
