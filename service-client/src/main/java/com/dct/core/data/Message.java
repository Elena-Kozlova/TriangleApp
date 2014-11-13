package com.dct.core.data;

import com.google.common.base.Objects;

/**
 * Response message.
 *
 * @param <T> Object type to be returned.
 */
public class Message<T> {
    public static final String SUCCESS = "success";
    public static final String ERROR = "error";

    private String response = SUCCESS;
    private Integer errorCode = 0;
    private String message = "";
    private T data;

    public String getResponse() {
        return response;
    }

    public Message<T> setError(int errorCode) {
        this.response = ERROR;
        this.errorCode = errorCode;
        return this;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("response", response)
                .add("errorCode", errorCode)
                .add("message", message)
                .add("data", data)

                .toString();
    }
}
