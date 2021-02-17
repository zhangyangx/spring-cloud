package org.meco.common.security.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ZhangYang
 * @date 2020/12/22
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AccessDeniedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    protected String errorCode;
    /**
     * 错误信息
     */
    protected String errorMsg;
}
