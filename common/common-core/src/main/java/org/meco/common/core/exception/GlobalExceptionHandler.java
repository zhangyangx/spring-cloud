package org.meco.common.core.exception;

import lombok.extern.slf4j.Slf4j;
import org.meco.common.core.enums.ExceptionEnum;
import org.meco.common.core.model.R;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一异常处理
 *
 * @author ZhangYang
 * @date 2021/1/6
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public R<Object> handle(HttpServletRequest request, Exception e) {
        log.error(ExceptionEnum.FAIL.getMsg(), e);
        recordRequestMsg(request);
        if (e instanceof BizException) {
            return R.fail(((BizException) e).getCode(), e.getMessage());
        }
        return R.fail();
    }

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R<Object> handleMethodArgumentNotValidException() {
        return R.validateFailed();
    }

    /**
     * 处理Get请求中 使用 @Valid 验证路径中请求实体校验失败后抛出的异常
     */
    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public R<Object> handleBindException() {
        return R.validateFailed();
    }

    private void recordRequestMsg(HttpServletRequest request) {
        log.error("URL:        {}", request.getRequestURL());
        log.error("HTTP Method:{}", request.getMethod());
        log.error("IP:         {}", request.getRemoteAddr());
    }
}
