package com.yangle.service.impl;

import com.yangle.service.common.datasource.DataSourceRouting;
import com.yangle.service.common.datasource.DataSourceTypeEnum;
import com.yangle.service.dao.entity.ProductUser;
import com.yangle.service.dao.mapper.ProductUserMapper;
import com.yangle.service.service.ProductUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * programname: product_factory
 * <p>
 * title: 用户表服务实现
 *
 * @author: yishao
 * <p>
 * created: 2018-09-18 21:21
 **/
@Service
public class ProductUserServiceImpl implements ProductUserService{

    @Resource
    private ProductUserMapper productUserMapper;

    @DataSourceRouting(DataSourceTypeEnum.MASTER)
    public void insert(ProductUser user) {
        productUserMapper.insert(user);
    }

    @DataSourceRouting(DataSourceTypeEnum.SLAVE)
    public ProductUser queryById(Long id) {
        return productUserMapper.selectById(id);
    }

    @DataSourceRouting(DataSourceTypeEnum.SLAVE)
    public List<ProductUser> queryByName(String name) {
        return productUserMapper.selectByName(name);
    }
}
