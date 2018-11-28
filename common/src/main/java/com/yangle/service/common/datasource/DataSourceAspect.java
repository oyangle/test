package com.yangle.service.common.datasource;

import org.apache.ibatis.datasource.DataSourceException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * programname: product_factory
 * <p>
 * title: 处理注解类
 *
 * @author: yishao
 * <p>
 * created: 2018-11-28 15:06
 **/
@Component
@Aspect
@Order(0)
@ComponentScan//组件自动扫描
@EnableAspectJAutoProxy//spring自动切换JDK动态代理和CGLIB
public class DataSourceAspect {

    public static final Logger logger = LoggerFactory.getLogger(DataSourceAspect.class);

    @Around("@annotation(DataSourceRouting)&&@annotation(dataSourceRouting)")
    public Object doAround(ProceedingJoinPoint pjp,DataSourceRouting dataSourceRouting){

        Object retVal = null;
        boolean selectDataSource =false;
        try {

            if (null!=dataSourceRouting){
                selectDataSource = true;
                if (DataSourceTypeEnum.MASTER==dataSourceRouting.value()){
                    DynamicDataSource.useMaser();
                }else if (DataSourceTypeEnum.SLAVE==dataSourceRouting.value()){
                    DynamicDataSource.useSlave();
                }else {
                    //暂时不写
                }
            }

            retVal = pjp.proceed();

        }catch (Throwable e){
            logger.warn("数据源切换错误", e);
            throw new DataSourceException("数据源切换错误", e);
        }finally {
            if (selectDataSource){
                //切换为原来的数据源
                DynamicDataSource.reset();
            }
        }
        return retVal;
    }

}
