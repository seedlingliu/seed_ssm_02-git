package com.seed.ssm.dao;

import com.seed.ssm.domain.Traveller;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface ITravellerDao {
    @Select(" select * from traveller where id in (SELECT ordersId FROM orders_traveller WHERE ordersId = #{ordersId}) ")
    Traveller findByOrdersId(int ordersId);
}
