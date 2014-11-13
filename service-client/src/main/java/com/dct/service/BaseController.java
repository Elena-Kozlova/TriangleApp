package com.dct.service;

import com.dct.core.data.Message;
import com.dct.exception.HttpStatusException;
import com.dct.service.response.BaseErrorResponse;

/**
 * Base class of controller.
 */
public abstract class BaseController {

    /**
     *
     * @param e Exception if status code is not 200.
     * @return  Error message.
     */
    public Message getErrorResponse(HttpStatusException e) {
        Message message = new Message();

        BaseErrorResponse errorResponse = e.getEntity(BaseErrorResponse.class);
        message.setMessage(errorResponse.getDetails());
        message.setError(e.getStatus());

        return message;
    }
}
