package com.yangle.service.dao.mapper;

import com.yangle.service.dao.entity.ProductUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: product_factory
 * @description: 用户表
 * @author: yishao
 * @create: 2018-09-17 17:57
 **/
@Mapper
public interface ProductUserMapper {

    void insert(ProductUser user);

    ProductUser selectById(@Param("id")Long id);

    List<ProductUser> selectByName(@Param("userName")String userName);

}
