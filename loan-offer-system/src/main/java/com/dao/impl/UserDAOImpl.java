package com.dao.impl;

import com.dao.OfferDAOConstant;
import com.dao.UserDAO;
import com.dto.response.GeneralResponse;
import com.dto.user.request.CreateNewUserReq;
import com.dto.user.request.GetCustomerDetailReq;
import com.dto.user.response.CustomerRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class UserDAOImpl implements UserDAO {

    Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public GeneralResponse createNewUser(CreateNewUserReq createNewUserReq) {
        Long startTime = System.currentTimeMillis();
        GeneralResponse response = new GeneralResponse();
        Connection connection = null;
        CallableStatement callableStatement;
        try {
            logger.info("createNewUser-request------------------>"+createNewUserReq.toString());

            connection = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
            callableStatement = connection.prepareCall(OfferDAOConstant.UserConstant.INSERT_UPDATE_ADMIN_CUSTOMER);
            callableStatement.setObject(1,createNewUserReq.getUserId(),Types.INTEGER);
            callableStatement.setObject(2,createNewUserReq.getFirstName(),Types.VARCHAR);
            callableStatement.setObject(3,createNewUserReq.getLastName(),Types.VARCHAR);
            callableStatement.setObject(4,createNewUserReq.getDob(),Types.VARCHAR);
            callableStatement.setObject(5,createNewUserReq.getUserType(),Types.INTEGER);
            callableStatement.setObject(6,createNewUserReq.getUserName(),Types.VARCHAR);
            callableStatement.setObject(7,createNewUserReq.getSecretKey(),Types.VARCHAR);
            callableStatement.setObject(8,createNewUserReq.getUserEmail(),Types.VARCHAR);
            callableStatement.setObject(9,createNewUserReq.getUserMobileNumber(),Types.VARCHAR);
            callableStatement.setObject(10,createNewUserReq.getNic(),Types.VARCHAR);
            callableStatement.setObject(11,createNewUserReq.getCustomerId(),Types.INTEGER);
            callableStatement.setObject(12,createNewUserReq.getLoanBalance(),Types.DOUBLE);
            callableStatement.setObject(13,createNewUserReq.getUsedAmount(),Types.DOUBLE);
            callableStatement.setObject(14,createNewUserReq.getInstallPlan(),Types.INTEGER);

            callableStatement.registerOutParameter(15,Types.BOOLEAN);
            callableStatement.registerOutParameter(16,Types.INTEGER);
            callableStatement.registerOutParameter(17,Types.VARCHAR);

            callableStatement.executeUpdate();
            callableStatement.getResultSet();
            response.setRes((Boolean) callableStatement.getObject(15));
            response.setStatusCode((Integer) callableStatement.getObject(16));
            response.setMsg((String) callableStatement.getObject(17));
        }catch (SQLException exception){
            logger.info("An error occured in createNewUser "+exception.toString());
        }finally {
            DataSourceUtils.releaseConnection(connection,jdbcTemplate.getDataSource());
            logger.info("Time taken for createNewUser in seconds: "+ (double)(System.currentTimeMillis() - startTime)/1000 );
        }
        return response;
    }

    @Override
    public CustomerRes getCustomerDetail(GetCustomerDetailReq getCustomerDetailReq) {
        Long startTime = System.currentTimeMillis();
        CustomerRes response = null;
        Connection connection = null;
        CallableStatement callableStatement;
        ResultSet resultSet;
        try {
            logger.info("getCustomerDetail-request------------------>"+getCustomerDetailReq.toString());

            connection = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
            callableStatement = connection.prepareCall(OfferDAOConstant.UserConstant.GET_CUSTOMER_DETAIL);
            callableStatement.setObject(1,getCustomerDetailReq.getCustomerId(),Types.INTEGER);
            callableStatement.execute();
            resultSet = callableStatement.getResultSet();
            if(resultSet != null){
                response = new CustomerRes();
                while (resultSet.next()){
                    response.setFullName(resultSet.getString(1));
                    response.setDob(resultSet.getString(2));
                    response.setUserTypeStr(resultSet.getString(3));
                    response.setUserEmail(resultSet.getString(4));
                    response.setUserMobileNumber(resultSet.getString(5));
                    response.setLoanBalance(resultSet.getDouble(6));
                    response.setUsedAmount(resultSet.getDouble(7));
                    response.setPlanName(resultSet.getString(8));
                }
            }
        }catch (SQLException exception){
            logger.info("An error occured in getCustomerDetail "+exception.toString());
        }finally {
            DataSourceUtils.releaseConnection(connection,jdbcTemplate.getDataSource());
            logger.info("Time taken for getCustomerDetail in seconds: "+ (double)(System.currentTimeMillis() - startTime)/1000 );
        }
        return response;
    }

    @Override
    public List<CustomerRes> getCustomerList() {
        Long startTime = System.currentTimeMillis();
        List<CustomerRes> list = null;
        Connection connection = null;
        CallableStatement callableStatement;
        ResultSet resultSet;
        try {
            connection = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
            callableStatement = connection.prepareCall(OfferDAOConstant.UserConstant.GET_CUSTOMER_LIST);
            callableStatement.execute();
            resultSet = callableStatement.getResultSet();
            if(resultSet != null){
                list = new ArrayList<>();
                while (resultSet.next()){
                    CustomerRes response = new CustomerRes();
                    response.setFullName(resultSet.getString(1));
                    response.setDob(resultSet.getString(2));
                    response.setUserTypeStr(resultSet.getString(3));
                    response.setUserEmail(resultSet.getString(4));
                    response.setUserMobileNumber(resultSet.getString(5));
                    response.setLoanBalance(resultSet.getDouble(6));
                    response.setUsedAmount(resultSet.getDouble(7));
                    response.setPlanName(resultSet.getString(8));

                    list.add(response);
                }
            }
        }catch (SQLException exception){
            logger.info("An error occured in getCustomerList "+exception.toString());
        }finally {
            DataSourceUtils.releaseConnection(connection,jdbcTemplate.getDataSource());
            logger.info("Time taken for getCustomerList in seconds: "+ (double)(System.currentTimeMillis() - startTime)/1000 );
        }
        return list;
    }
}
