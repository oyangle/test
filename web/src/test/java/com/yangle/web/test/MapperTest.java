package com.yangle.web.test;

import com.alibaba.fastjson.JSONObject;
import com.yangle.service.common.utils.DataSourceUtil;
import com.yangle.service.dao.entity.ProductUser;
import com.yangle.service.dao.mapper.ProductUserMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * programname: product_factory
 * <p>
 * title: mapper
 *
 * @author: yishao
 * <p>
 * created: 2018-09-17 19:23
 **/
public class MapperTest extends BaseTest{


    @Test
    public void testInsert(){
        ProductUser user = new ProductUser();
        user.setUserName("张三");
        user.setAge(10);

        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        ProductUserMapper mapper = sqlSession.getMapper(ProductUserMapper.class);

        mapper.insert(user);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testSelect(){

        SqlSession sqlSession = DataSourceUtil.getSqlSession();
        ProductUserMapper mapper = sqlSession.getMapper(ProductUserMapper.class);

        ProductUser productUser = mapper.selectById(2L);
        sqlSession.close();
        System.out.println(JSONObject.toJSONString(productUser));
    }

}
