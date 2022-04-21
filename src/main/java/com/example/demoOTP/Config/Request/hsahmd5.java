package com.example.demoOTP.Config.Request;

public class hsahmd5 {
    private String nameService ;
    private String secretKey;
    private String reqid;

    public hsahmd5() {
    }

    public hsahmd5(String nameService, String secretKey, String reqid) {
        this.nameService = nameService;
        this.secretKey = secretKey;
        this.reqid = reqid;
    }

    public String getNameService() {
        return nameService;
    }

    public void setNameService(String nameService) {
        this.nameService = nameService;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getReqid() {
        return reqid;
    }

    public void setReqid(String reqid) {
        this.reqid = reqid;
    }
}
