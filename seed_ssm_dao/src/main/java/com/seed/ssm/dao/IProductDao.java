package com.seed.ssm.dao;

import com.seed.ssm.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface IProductDao {

    @Select(" select * from product where id = #{id} ")
    Product findOneById(int id) throws Exception;

    @Select(" select * from product ")
    List<Product> findAll() throws Exception;

    @Insert(" INSERT INTO product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus)\n" +
            "VALUES (#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus}) ")
    void save(Product product) throws Exception;
}
