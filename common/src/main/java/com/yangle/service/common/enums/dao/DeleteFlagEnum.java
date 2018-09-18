package com.yangle.service.common.enums.dao;

import com.yangle.service.common.enums.CodeDescEnum;

/**
 * @program: product_factory
 * @description: 删除标识
 * @author: yishao
 * @create: 2018-09-17 17:13
 **/
public enum DeleteFlagEnum implements CodeDescEnum{

    VALID(1,"有效"),
    INVALID(2,"无效"),;

    private int code;
    private String desc;

    DeleteFlagEnum(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static DeleteFlagEnum valueOf(int code){
        for (DeleteFlagEnum each : DeleteFlagEnum.values()) {
            if (code == each.getCode())
                return each;
        }
        return null;
    }
}
