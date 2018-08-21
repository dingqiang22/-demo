package com.dazhi.demo.dazhi.bean;

public class ResultCode<T> {
    private int code;//0成功 500失败

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }

    private T Data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


}
