package com.business;

import com.dto.response.GeneralResponse;
import com.dto.response.LoanOfferResponse;
import com.dto.user.request.CreateNewUserReq;

public interface UserBusiness {
    /**
     * createNewUser
     * @param createNewUserReq
     * @return
     */
    GeneralResponse createNewUser(CreateNewUserReq createNewUserReq);
}
