package com.dao.impl;

import com.dao.OfferDAOConstant;
import com.dao.OrderDAO;
import com.dto.request.GetOrderDetailReq;
import com.dto.response.GeneralResponse;
import com.dto.response.GetOrderDetailRes;
import com.dto.response.Product;
import com.dto.user.response.CustomerRes;
import com.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class OrderDAOImpl implements OrderDAO {

    Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Product> getAllProducts() {
        Long startTime = System.currentTimeMillis();
        List<Product> list = null;
        Connection connection = null;
        CallableStatement callableStatement;
        ResultSet resultSet;
        try {
            connection = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
            callableStatement = connection.prepareCall(OfferDAOConstant.OrderConstant.GET_ALL_PRODUCTS);
            callableStatement.execute();
            resultSet = callableStatement.getResultSet();
            if (resultSet != null) {
                list = new ArrayList<>();
                while (resultSet.next()) {
                    Product response = new Product();
                    response.setProductId(resultSet.getInt(1));
                    response.setProductName(resultSet.getString(2));
                    list.add(response);
                }
            }
        } catch (SQLException exception) {
            logger.info("An error occured in getAllProducts " + exception.toString());
        } finally {
            DataSourceUtils.releaseConnection(connection, jdbcTemplate.getDataSource());
            logger.info("Time taken for getAllProducts in seconds: " + (double) (System.currentTimeMillis() - startTime) / 1000);
        }
        return list;
    }

    @Override
    public GetOrderDetailRes getOrderSingleCalculation(GetOrderDetailReq getOrderDetailReq) {
        Long startTime = System.currentTimeMillis();
        GetOrderDetailRes response = new GetOrderDetailRes();
        Connection connection = null;
        CallableStatement callableStatement;
        try {
            logger.info("getOrderSingleCalculation-request------------------>" + getOrderDetailReq.toString());

            connection = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
            callableStatement = connection.prepareCall(OfferDAOConstant.OrderConstant.GET_ORDER_DETAIL_REQUEST_PROC);
            callableStatement.setObject(1, getOrderDetailReq.getProductId(), Types.INTEGER);
            callableStatement.setObject(2, getOrderDetailReq.getQty(), Types.INTEGER);

            callableStatement.registerOutParameter(3, Types.INTEGER);
            callableStatement.registerOutParameter(4, Types.VARCHAR);
            callableStatement.registerOutParameter(5, Types.VARCHAR);
            callableStatement.registerOutParameter(6, Types.INTEGER);
            callableStatement.registerOutParameter(7, Types.VARCHAR);
            callableStatement.registerOutParameter(8, Types.DOUBLE);

            callableStatement.execute();
            callableStatement.getResultSet();
            response.setProductId(callableStatement.getInt(3));
            response.setCategoryName(callableStatement.getString(4));
            response.setBrandName(callableStatement.getString(5));
            response.setQty(callableStatement.getInt(6));
            response.setProductName(callableStatement.getString(7));
            response.setSubTotal(callableStatement.getDouble(8));

        } catch (SQLException exception) {
            logger.info("An error occured in getOrderSingleCalculation " + exception.toString());
        } finally {
            DataSourceUtils.releaseConnection(connection, jdbcTemplate.getDataSource());
            logger.info("Time taken for getOrderSingleCalculation in seconds: " + (double) (System.currentTimeMillis() - startTime) / 1000);
        }
        return response;
    }
}
