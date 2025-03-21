package com.mwu.myv1.pojo;

public enum ProductStatus {
    OK(0, "ok"),
    DELETED(1, "Đã bỏ");

    final Integer id;
    final String desc;

    public Integer id() {
        return id;
    }

    public String desc() {
        return desc;
    }

    ProductStatus(Integer id, String desc) {
        this.id = id;
        this.desc = desc;
    }
}
