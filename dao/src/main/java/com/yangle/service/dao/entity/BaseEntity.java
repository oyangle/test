package com.yangle.service.dao.entity;

import com.yangle.service.common.enums.dao.DeleteFlagEnum;

import java.io.Serializable;

/**
 * programname: product_factory
 * <p>
 * title: 表中基础字段
 *
 * @author: yishao
 * <p>
 * created: 2018-09-17 17:09
 **/
public class BaseEntity implements Serializable{

    private Long id;
    private DeleteFlagEnum deleteFlag;
    private Integer created;
    private Integer updated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DeleteFlagEnum getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(DeleteFlagEnum deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public Integer getUpdated() {
        return updated;
    }

    public void setUpdated(Integer updated) {
        this.updated = updated;
    }
}
