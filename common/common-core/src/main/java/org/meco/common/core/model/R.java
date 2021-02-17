package org.meco.common.core.model;

import lombok.Data;
import org.meco.common.core.enums.BaseEnum;
import org.meco.common.core.enums.ExceptionEnum;

import java.io.Serializable;

/**
 * 全局统一的返回结果
 *
 * @author ZhangYang
 * @date 2020/12/21
 */
@Data
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 响应码
     */
    private int code;

    /**
     * 响应信息
     */
    private String msg;

    /**
     * 响应数据
     */
    private T data;

    public R() {
    }

    public R(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public R(BaseEnum baseEnum) {
        this.code = baseEnum.getCode();
        this.msg = baseEnum.getMsg();
    }

    public R(ExceptionEnum exceptionEnum, T data) {
        this.code = exceptionEnum.getCode();
        this.msg = exceptionEnum.getMsg();
        this.data = data;
    }

    public static <T> R<T> success() {
        return new R<>(ExceptionEnum.SUCCESS);
    }

    public static <T> R<T> success(T data) {
        return new R<>(ExceptionEnum.SUCCESS, data);
    }

    public static <T> R<T> fail() {
        return new R<>(ExceptionEnum.FAIL);
    }

    public static <T> R<T> fail(int code, String msg) {
        return new R<>(code, msg);
    }

    public static <T> R<T> fail(ExceptionEnum exceptionEnum) {
        return new R<>(exceptionEnum);
    }

    public static <T> R<T> fail(T data) {
        return new R<>(ExceptionEnum.FAIL, data);
    }

    public static <T> R<T> fail(ExceptionEnum exceptionEnum, T data) {
        return new R<>(exceptionEnum, data);
    }

    public static <T> R<T> validateFailed() {
        return new R<>(ExceptionEnum.VALIDATE_FAIL);
    }
}