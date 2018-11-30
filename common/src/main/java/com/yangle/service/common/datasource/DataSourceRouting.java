package com.yangle.service.common.datasource;

import org.springframework.core.annotation.Order;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * programname: product_factory
 * <p>
 * title: 数据源操作路由
 *
 * @author: yishao
 * <p>
 * created: 2018-09-17 21:35
 **/
@Target(ElementType.METHOD)
@Order(0)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSourceRouting {

    //默认使用主库
    DataSourceTypeEnum value() default DataSourceTypeEnum.MASTER;

}
