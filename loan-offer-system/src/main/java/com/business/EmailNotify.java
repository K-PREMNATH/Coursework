package com.business;

import com.dto.request.EmailReq;

public interface EmailNotify {

    public String sendEmail(EmailReq emailReq);

}
