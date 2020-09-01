package com.seed.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.seed.ssm.dao.IOrdersDao;
import com.seed.ssm.domain.Orders;
import com.seed.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements IOrdersService {
    @Autowired
    private IOrdersDao ordersDao;

    @Override
    public List<Orders> findAll(int pageNum, int pageSize) throws Exception {
        PageHelper.startPage(pageNum, pageSize);
        return ordersDao.findAll();
    }

    @Override
    public Orders findById(int id) throws Exception {
        return ordersDao.findByID(id);
    }
}
