package org.meco.common.core.enums;

/**
 * 3位数-公共
 * 1开头 权限相关
 *
 * @author ZhangYang
 * @date 2021/1/6
 */
public enum ExceptionEnum implements BaseEnum {

    SUCCESS(200, "success"),
    FAIL(500, "系统内部错误internal error"),
    VALIDATE_FAIL(400, "参数验证错误！"),

    UNAUTHORIZED(100001, "未经授权");

    private final int code;

    private final String msg;

    ExceptionEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}