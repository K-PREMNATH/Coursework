package com.business.impl;

import com.business.OrderBusiness;
import com.dao.OrderDAO;
import com.dto.request.GetOrderDetailReq;
import com.dto.response.GetOrderDetailRes;
import com.dto.response.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderBusinessImpl implements OrderBusiness {

    @Autowired
    OrderDAO orderDAO;
    @Override
    public List<Product> getAllProducts() {
        return orderDAO.getAllProducts();
    }

    @Override
    public GetOrderDetailRes getOrderSingleCalculation(GetOrderDetailReq getOrderDetailReq) {
        return orderDAO.getOrderSingleCalculation(getOrderDetailReq);
    }
}
