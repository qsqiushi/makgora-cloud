package com.arthur.plugins;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.apache.log4j.Logger;
/**
 * 
 * @author   qius
 * @date     2017年1月13日 下午1:34:51
 * @describe mybatis sql 拦截器
 */
@Intercepts(value = {
        @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }),
        @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
                RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class }),
        @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
                RowBounds.class, ResultHandler.class }) })
public class SqlInterceptor implements Interceptor {
    private static final Logger logger = Logger.getLogger(SqlInterceptor.class);
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object parameter = null;
        if (invocation.getArgs().length > 1) {
            parameter = invocation.getArgs()[1];
        }
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        Configuration configuration = mappedStatement.getConfiguration();
        Method method = invocation.getMethod();
        showSql(configuration, boundSql,method);
        Object target = invocation.getTarget();
        Object result = null;
        if (target instanceof Executor) {
            long start = System.currentTimeMillis();
            /** 执行方法 */
            result = invocation.proceed();
            long end = System.currentTimeMillis();
            logger.error(new Date()+"[TimerInterceptor] execute [" + method.getName() + "] cost [" + (end - start) + "] ms");
        }
        return result;
    }
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }
    @Override
    public void setProperties(Properties arg0) {
    }
    public String showSql(Configuration configuration, BoundSql boundSql, Method method) {  
        Object parameterObject = boundSql.getParameterObject();  
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();  
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");  
        if (parameterMappings.size() > 0 && parameterObject != null) {  
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();  
            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {  
                sql = sql.replaceFirst("\\?", getParameterValue(parameterObject));  
            } else {  
                MetaObject metaObject = configuration.newMetaObject(parameterObject);  
                for (ParameterMapping parameterMapping : parameterMappings) {  
                    String propertyName = parameterMapping.getProperty();  
                    if (metaObject.hasGetter(propertyName)) {  
                        Object obj = metaObject.getValue(propertyName);  
                        sql = sql.replaceFirst("\\?", getParameterValue(obj));  
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {  
                        Object obj = boundSql.getAdditionalParameter(propertyName);  
                        sql = sql.replaceFirst("\\?", getParameterValue(obj));  
                    }  
                }  
            }  
        }  
        logger.error(new Date()+"[SqlInterceptor] execute [" + method.getName() + "]"+sql);   
        return sql;  
    }  
    private String getParameterValue(Object obj) {  
        String value = null;  
        if (obj instanceof String) {  
            value = "'" + obj.toString() + "'";  
        } else if (obj instanceof Date) {  
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);  
            value = "'" + formatter.format(obj) + "'";  
        } else {  
            if (obj != null) {  
                value = obj.toString();  
            } else {  
                value = "";  
            }  
        }  
        return value;  
    }  
}