package com.yangle.service.common.datasource;

/**
 * @program: product_factory
 * @description: 数据源类型
 * @author: yishao
 * @create: 2018-11-28 14:56
 **/
public enum DataSourceTypeEnum {

    //主库
    MASTER("master"),
    //从库
    SLAVE("slave"),
    //离线库
    OFFLINE("offline")
    ;

    private String name;

    private DataSourceTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
