package com.zhaowa.galaxy.springcloud.servicea.api;

public class Param {
    private String paramA;
    private String paramB;

    public Param() {
    }

    public Param(String paramA, String paramB) {
        this.paramA = paramA;
        this.paramB = paramB;
    }

    public String getParamA() {
        return paramA;
    }

    public void setParamA(String paramA) {
        this.paramA = paramA;
    }

    public String getParamB() {
        return paramB;
    }

    public void setParamB(String paramB) {
        this.paramB = paramB;
    }
}
