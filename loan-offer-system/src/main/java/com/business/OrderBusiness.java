package com.business;

import com.dto.request.GetOrderDetailReq;
import com.dto.response.GetOrderDetailRes;
import com.dto.response.Product;

import java.util.List;

public interface OrderBusiness {
    /**
     * getAllProducts
     * @return
     */
    List<Product> getAllProducts();

    /**
     * getOrderSingleCalculation
     * @param getOrderDetailReq
     * @return
     */
    GetOrderDetailRes getOrderSingleCalculation(GetOrderDetailReq getOrderDetailReq);
}
