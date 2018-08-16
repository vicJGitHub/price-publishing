package com.hywa.pricepublish.common.enums;

import com.hywa.pricepublish.common.exception.GlobalException;

public enum CommonEnum {
    MEN("男", (short) 0),
    WOMEN("女", (short) 1),
    COUNTRY("country", (short) 0),
    PROVINCE("province", (short) 1),
    CITY("city", (short) 2),
    DISTRICT("county", (short) 3),
    TOWN("town", (short) 4),
    VILLAGE("village", (short) 5),
    SUCCESS("成功", (short) 0),
    FAILURE("失败", (short) 1),
    USER_CANCEL("用户已经注销，请联系管理员", (short) 1001),
    PASSWORD_ERROR("密码错误", (short) 1002),
    USER_UNREGISTERED_ERROR("用户未注册", (short) 1003),
    RE_LOGIN("认证失败，请重新登录！", (short) 1004),
    UNBOUND_REGION_ERROR("此账户未绑定区域，请联系管理员！", (short) 1005),
    USER_NAME_REPEATED("用户名称已经存在", (short) 1006),
    USER_TELEPHONE_REPEATED("电话号码已经存在", (short) 1007),
    TELEPHONE_FORMAL_ERROR("电话号码格式错误", (short) 1008),
    RE_LOGOUT("未正确退出,请重新退出", (short) 1009),
    UNIQUE_CHECK_ERROR("重复", (short) 1101),
    ENCRYPTION_ERROR("加密操作失败",(short)1102),
    ILLEGAL_PARAMETER_ERROR("参数非法", (short) 2001),
    PARAMETER_NOT_NULL("参数不能为空", (short) 2002),
    ID_NOT_NULL("id不能为空",(short)2009),
    TYPE_REFERENCED("类型被引用,请先删除引用", (short) 2003),
    FILE_NOT_NULL("文件不能为空", (short) 2004),
    FILE_CONTENT_NOT_NULL("文件内容不能为空", (short) 2005),
    FILE_UPLOAD_FAIL("文件上传失败", (short) 2006),
    DATE_TIME_FORMAT_ERR("请按正确的时间格式填写，格式为：年-月-日，例如：2018-06-28", (short) 2007),
    COORDINATE_PARSE_ERROR("坐标解析错误",(short) 2008),
    DEL_FAILURE("删除失败",(short) 2009),
    PRODUCT_NAME_REPEATED("该商品已经存在", (short) 3001),
    FILE_NOT_FOUND("文件不存在", (short) 3002),
    FILE_REPEATED("文件已存在", (short) 3003),
    MAX_UPLOAD_SIZE_EXCEEDED("文件过大无法上传",(short)3004),
    INTERNAL_ERROR("系统错误，请联系管理员！", (short) 9999);



    private String value;
    private Short index;

    CommonEnum(String sex, Short index) {
        this.setValue(sex);
        this.setIndex(index);
    }

    public String getValue() {
        return value;
    }

    public static String getSex(Short index) {
        if (index == MEN.index) {
            return MEN.getValue();
        } else if (index == WOMEN.index) {
            return WOMEN.getValue();
        } else {
            return null;
        }
    }

    public static short getIndex(String sex) {
        if (sex.trim().equals(MEN.value)) {
            return MEN.getIndex();
        } else if (sex.trim().equals(WOMEN.value)) {
            return WOMEN.getIndex();
        } else {
            throw new GlobalException("性别输入异常，性别只能为：男、女！");
        }
    }

    public Short getIndex() {
        return index;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setIndex(Short index) {
        this.index = index;
    }
}
