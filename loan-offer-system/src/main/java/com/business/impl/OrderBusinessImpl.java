package com.business.impl;

import com.business.OrderBusiness;
import com.dao.OrderDAO;
import com.dto.request.CreateOrderReq;
import com.dto.request.GetOrderDetailReq;
import com.dto.request.OrderInfoReq;
import com.dto.response.CommonResponse;
import com.dto.response.GetOrderDetailRes;
import com.dto.response.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class OrderBusinessImpl implements OrderBusiness {

    Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    OrderDAO orderDAO;

    @Override
    public List<Product> getAllProducts() {
        return orderDAO.getAllProducts();
    }

    @Override
    public CommonResponse getOrderSingleCalculation(GetOrderDetailReq getOrderDetailReq) {
        return orderDAO.getOrderSingleCalculation(getOrderDetailReq);
    }

    @Override
    public CommonResponse placeOrder(CreateOrderReq createOrderReq) {
        logger.info("placeOrder------------------->" + createOrderReq.toString());
        String orderRequest = "";
        for (OrderInfoReq req : createOrderReq.getOrderDetail()) {
            orderRequest = orderRequest + req.getProductId() +"||"+req.getQty()+",";
        }
        orderRequest = orderRequest.substring(0, orderRequest.length()-1);
        logger.info("placeOrder-orderRequest------------------>" + orderRequest);
        return orderDAO.placeOrder(createOrderReq,orderRequest);
    }
}
