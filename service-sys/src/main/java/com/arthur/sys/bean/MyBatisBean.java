package com.arthur.sys.bean;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSON;
import com.arthur.plugins.SqlInterceptor;

@Configuration
@MapperScan(basePackages = "com.arthur.**.mapper", sqlSessionTemplateRef = "mySqlSessionTemplate")
public class MyBatisBean {


    private final Logger logger = Logger.getLogger(getClass());
    @Value("${jdbc.mysql.url}")
    private String dbUrl;

    @Value("${jdbc.mysql.username}")
    private String username;

    @Value("${jdbc.mysql.password}")
    private String password;

    @Value("${jdbc.mysql.driverClassName}")
    private String driverClassName;

    @Value("${jdbc.mysql.initialSize}")
    private int initialSize;

    @Value("${jdbc.mysql.minIdle}")
    private int minIdle;

    @Value("${jdbc.mysql.maxActive}")
    private int maxActive;

    @Value("${jdbc.mysql.maxWait}")
    private int maxWait;

    @Value("${jdbc.mysql.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${jdbc.mysql.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    @Value("${jdbc.mysql.validationQuery}")
    private String validationQuery;

    @Value("${jdbc.mysql.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${jdbc.mysql.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${jdbc.mysql.testOnReturn}")
    private boolean testOnReturn;

    @Value("${jdbc.mysql.poolPreparedStatements}")
    private boolean poolPreparedStatements;

    @Value("${jdbc.mysql.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;

    @Value("${jdbc.mysql.removeAbandonedTimeout}")
    private String removeAbandonedTimeout;

    @Value("${jdbc.mysql.removeAbandoned}")
    private String removeAbandoned;


    @Value("${jdbc.mysql.filters}")
    private String filters;

    @Value("${jdbc.mysql.connectionProperties}")
    private String connectionProperties;

    @Value("${jdbc.mysql.logAbandoned}")
    private boolean logAbandoned;

    @Bean(name = "dataSource")
    @Primary
    public DataSource testDataSource() {
        // return DataSourceBuilder.create().build();
        DruidDataSource datasource = new DruidDataSource();

        datasource.setUrl(this.dbUrl);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);

        // configuration
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setLogAbandoned(this.logAbandoned);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setRemoveAbandonedTimeout(Integer.valueOf(removeAbandonedTimeout));
        datasource.setRemoveAbandoned(Boolean.valueOf(removeAbandoned));
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        datasource
                .setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        try {
            datasource.setFilters(filters);
        } catch (Exception e) {
            logger.error("druid configuration initialization filter", e);
        }
        System.out.println(JSON.toJSONString(dbUrl));
        datasource.setConnectionProperties(connectionProperties);

        return datasource;
    }

    @Bean(name = "mySqlSessionFactory")
    @Primary
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("dataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mybatis_sql_config/mybatis_sql_*/*.xml"));
        //设置mybatis插件
        Interceptor[] interceptors= {new SqlInterceptor()};
        bean.setPlugins(interceptors);
        return bean.getObject();
    }

    @Bean(name = "mySqlTransactionManager")
    @Primary
    public DataSourceTransactionManager testTransactionManager(
            @Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "mySqlSessionTemplate")
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(
            @Qualifier("mySqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    
    
    

}
