package com.dct.service;

import com.dct.core.data.Message;
import com.dct.exception.HttpStatusException;
import com.dct.service.response.BaseErrorResponse;

public abstract class BaseController {

    public Message getErrorResponse(HttpStatusException e) {
        Message message = new Message();

        BaseErrorResponse errorResponse = e.getEntity(BaseErrorResponse.class);
        message.setMessage(errorResponse.getDetails());
        message.setError(e.getStatus());

        return message;
    }
}
