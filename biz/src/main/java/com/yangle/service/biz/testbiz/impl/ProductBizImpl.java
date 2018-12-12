package com.yangle.service.biz.testbiz.impl;

import com.yangle.service.biz.testbiz.ProductBiz;
import com.yangle.service.dao.entity.ProductUser;
import com.yangle.service.service.ProductUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * programname: product_factory
 * <p>
 * title: 产品化服务实现
 *
 * @author: yishao
 * <p>
 * created: 2018-11-14 12:09
 **/
@Service
public class ProductBizImpl implements ProductBiz{

    public static final Logger logger = LoggerFactory.getLogger(ProductBizImpl.class);


    @Resource
    private ProductUserService productUserService;

    /**
     *
     * 切库的话不能使用事物，使用事物暂时会导致切库失败。
     * 简单说就是暂时不支持分布式事物
     *
     */
    public void addNewProduct() {

        ProductUser user = new ProductUser();
        user.setAge(10);
        user.setUserName("李四");

        //insert强制主库
        productUserService.insert(user);

        List<ProductUser> users = productUserService.queryByName("李四");
        if (CollectionUtils.isEmpty(users)){
            logger.info("未从从库查询到数据");
        }else {
            logger.info("已从从库查询到数据");
        }

    }

    public void throwE(){
        throw new RuntimeException("出异常啦");
    }

    @Transactional
    public void addNewProductRollBack() {
        ProductUser user = new ProductUser();
        user.setAge(10);
        user.setUserName("李四");

        productUserService.insert(user);
        throwE();
    }
}
