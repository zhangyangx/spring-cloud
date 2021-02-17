package org.meco.common.core.exception;

import org.meco.common.core.enums.BaseEnum;

/**
 * @author ZhangYang
 * @date 2021/1/6
 */
public class BizException extends RuntimeException {
    private int code;

    public BizException() {
    }

    public BizException(String msg) {
        super(msg);
        this.code = 500;
    }

    public BizException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public BizException(BaseEnum baseEnum) {
        super(baseEnum.getMsg());
        this.code = baseEnum.getCode();
    }

    public int getCode() {
        return code;
    }
}
