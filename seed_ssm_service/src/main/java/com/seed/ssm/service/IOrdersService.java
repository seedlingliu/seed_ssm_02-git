package com.seed.ssm.service;

import com.seed.ssm.domain.Orders;

import java.util.List;

public interface IOrdersService {
    List<Orders> findAll(int pageNum, int pageSize) throws Exception;

    Orders findById(int id) throws Exception;
}
