package com.seed.ssm.dao;

import com.seed.ssm.domain.Member;
import com.seed.ssm.domain.Orders;
import com.seed.ssm.domain.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrdersDao {
    @Select(" select * from orders ")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "product", column = "productId", javaType = Product.class, one = @One(select = "com.seed.ssm.dao.IProductDao.findOneById"))
    })
    List<Orders> findAll() throws Exception;

    @Select(" select * from orders where id = #{id} ")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "product", column = "productId", javaType = Product.class, one = @One(select = "com.seed.ssm.dao.IProductDao.findOneById")),
            @Result(property = "member", column = "memberId", javaType = Member.class, one = @One(select = "com.seed.ssm.dao.IMemberDao.findOneById")),
            @Result(property = "travellerList", column = "id", javaType = List.class, many = @Many(select = "com.seed.ssm.dao.ITravellerDao.findByOrdersId"))
    })
    Orders findByID(int id) throws Exception;
}
