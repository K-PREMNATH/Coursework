package com.controller.impl;

import com.business.OrderBusiness;
import com.controller.OrderController;
import com.dto.request.GetOrderDetailReq;
import com.dto.response.GetOrderDetailRes;
import com.dto.response.LoanOfferResponse;
import com.dto.response.Product;
import com.util.ApplicationConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class OrderControllerImpl implements OrderController {

    @Autowired
    OrderBusiness orderBusiness;

    @PostMapping("/get/all/products")
    public LoanOfferResponse getAllProducts() {
        List<Product> productList = orderBusiness.getAllProducts();
        return LoanOfferResponse.generateResponse(
                productList,
                ApplicationConstant.SuccessStatusCode,
                ApplicationConstant.SuccessMsg);
    }

    @Override
    @PostMapping("/get/order/single/request")
    public LoanOfferResponse getOrderSingleCalculation(@RequestBody GetOrderDetailReq getOrderDetailReq) {
        GetOrderDetailRes getOrderDetailRes = orderBusiness.getOrderSingleCalculation(getOrderDetailReq);
        return LoanOfferResponse.generateResponse(
                getOrderDetailRes,
                ApplicationConstant.SuccessStatusCode,
                ApplicationConstant.SuccessMsg);
    }


}
