package com.controller;

import com.dto.request.CreateOrderReq;
import com.dto.request.GetOrderDetailReq;
import com.dto.response.LoanOfferResponse;

public interface OrderController {

    /**
     * getAllProducts
     * @return
     */
    public LoanOfferResponse getAllProducts();

    /**
     * getOrderSingleCalculation
     * @param getOrderDetailReq
     * @return
     */
    public LoanOfferResponse getOrderSingleCalculation(GetOrderDetailReq getOrderDetailReq);

    /**
     * placeOrder
     * @param createOrderReq
     * @return
     */
    public LoanOfferResponse placeOrder(CreateOrderReq createOrderReq);
}
