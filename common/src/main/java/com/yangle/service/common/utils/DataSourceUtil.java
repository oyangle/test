package com.yangle.service.common.utils;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;


/**
 * programname: product_factory
 * <p>
 * title: 数据库连接
 *
 * @author: yishao
 * <p>
 * created: 2018-09-17 21:12
 **/
public class DataSourceUtil {

    private static final String SQL_XML ="mybatis-config.xml";
    private static SqlSessionFactory sqlSessionFactory;

    static {
        InputStream inputStream = DataSourceUtil.class.getClassLoader().getResourceAsStream(SQL_XML);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }
}
