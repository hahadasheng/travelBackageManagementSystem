package com.lingting.travel.domain;

public class ResponseMsg {

    private Integer status = 200;
    private String desc = "OK";
    private Object msg;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ResponseMsg{" +
                "status=" + status +
                ", desc='" + desc + '\'' +
                ", msg=" + msg +
                '}';
    }
}
