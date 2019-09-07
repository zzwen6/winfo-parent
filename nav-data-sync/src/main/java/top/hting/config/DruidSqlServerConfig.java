package top.hting.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


@Configuration
@MapperScan(basePackages = "top.hting.mapper.sqlserver",
    sqlSessionFactoryRef = "sqlServerSessionFactory" )
public class DruidSqlServerConfig {

    /**数据源*/

    @Bean(name = "sqlServerDatasource")
    @Qualifier("sqlServerDatasource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.sqlserver")
    public DataSource sqlServerDatasource() {
        return new DruidDataSource();
    }

    @Bean
    @Primary
    public MybatisSqlSessionFactoryBean sqlServerSessionFactory(@Qualifier("sqlServerDatasource") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        MybatisConfiguration config = new MybatisConfiguration();
        config.setMapUnderscoreToCamelCase(false);
        config.setCacheEnabled(true);

        // config.setLogImpl(StdOutImpl.class);
        factoryBean.setConfiguration(config);

//        factoryBean.setTypeAliasesPackage("top.hting.mapper.sqlserver");
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/sqlserver/*.xml"));
        return factoryBean;
    }


    @Bean
    @Primary
    public DataSourceTransactionManager sqlServerTransactionManager(@Qualifier("sqlServerDatasource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }



}
