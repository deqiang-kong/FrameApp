package com.kong.frameapp.net;

import java.io.Serializable;


/**
 * 网络应答数据基础模型
 */
public class ResultBaseBean implements Serializable {

    private boolean success;
    private int flg;
    private int failCode;
    private int dataType;

    private String failInfo;
    private String msg;
    private String baseTm;


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getFlg() {
        return flg;
    }

    public void setFlg(int flg) {
        this.flg = flg;
    }

    public int getFailCode() {
        return failCode;
    }

    public void setFailCode(int failCode) {
        this.failCode = failCode;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public String getFailInfo() {
        return failInfo;
    }

    public void setFailInfo(String failInfo) {
        this.failInfo = failInfo;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getBaseTm() {
        return baseTm;
    }

    public void setBaseTm(String baseTm) {
        this.baseTm = baseTm;
    }
}
