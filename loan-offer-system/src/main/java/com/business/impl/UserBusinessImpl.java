package com.business.impl;

import com.business.UserBusiness;
import com.dao.UserDAO;
import com.dto.request.UserLoginReq;
import com.dto.response.CommonResponse;
import com.dto.response.GeneralResponse;
import com.dto.user.request.CreateNewUserReq;
import com.dto.user.request.GetCustomerDetailReq;
import com.dto.user.response.CustomerRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBusinessImpl implements UserBusiness {

    @Autowired
    UserDAO userDAO;

    @Override
    public GeneralResponse createNewUser(CreateNewUserReq createNewUserReq) {
        return userDAO.createNewUser(createNewUserReq);
    }

    @Override
    public CustomerRes getCustomerDetail(GetCustomerDetailReq getCustomerDetailReq) {
        return userDAO.getCustomerDetail(getCustomerDetailReq);
    }

    @Override
    public List<CustomerRes> getCustomerList() {
        return userDAO.getCustomerList();
    }

    @Override
    public CommonResponse login(UserLoginReq loginReq) {
        return userDAO.login(loginReq);
    }
}
