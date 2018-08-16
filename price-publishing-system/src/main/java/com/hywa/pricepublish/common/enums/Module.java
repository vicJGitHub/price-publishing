package com.hywa.pricepublish.common.enums;

public enum Module {
    ALL("所有权限"), COLLECT("采集模块"), TEST("测试模块");

    private String moduleName;

    Module(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleName() {
        return this.moduleName;
    }
}
