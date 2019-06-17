package com.luo.springcloudeurekaclientuser.entity;

import java.io.Serializable;

public enum Gender implements Serializable {
    UNKNOW(0, "未知"),
    MAN(1, "先生"),
    WOMAN(2, "女士");

    private Byte value;
    private String name;

    Gender(int value, String name) {
        this.value = (byte)value;
        this.name = name;
    }

    public Byte getValue() {
        return this.value;
    }


    public String getName() {
        return this.name;
    }
}