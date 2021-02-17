package org.meco.common.log.aspect;

import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.meco.common.core.util.ThrowableUtils;
import org.meco.common.log.domain.Log;
import org.meco.common.log.service.AsyncLogService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * @author ZhangYang
 * @date 2020/12/30
 */
@Component
@Aspect
@Slf4j
public class LogAspect {

    @Resource
    private AsyncLogService logService;

    ThreadLocal<Long> currentTime = new ThreadLocal<>();

    /**
     * 配置切入点
     */
    @Pointcut("@annotation(org.meco.common.log.annotation.Log)")
    public void logPointcut() {
        // 该方法无方法体,主要为了让同类中其他方法使用此切入点
    }

    /**
     * 配置环绕通知,使用在方法logPointcut()上注册的切入点
     *
     * @param joinPoint join point for advice
     */
    @Around("logPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result;
        currentTime.set(System.currentTimeMillis());
        result = joinPoint.proceed();
        Log log = new Log("INFO", System.currentTimeMillis() - currentTime.get());
        currentTime.remove();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        saveLog(joinPoint, log, signature, method);
        return result;
    }

    /**
     * 配置异常通知
     *
     * @param joinPoint join point for advice
     * @param e         exception
     */
    @AfterThrowing(pointcut = "logPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        Log log = new Log("ERROR", System.currentTimeMillis() - currentTime.get());
        currentTime.remove();
        log.setExceptionDetail(ThrowableUtils.getStackTrace(e).getBytes());
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        saveLog(joinPoint, log, signature, method);
    }

    /**
     * 方法抽取
     */
    private void saveLog(JoinPoint joinPoint, Log log, MethodSignature signature, Method method) {
        org.meco.common.log.annotation.Log aopLog = method.getAnnotation(org.meco.common.log.annotation.Log.class);

        // 方法路径
        String methodName = joinPoint.getTarget().getClass().getName() + "." + signature.getName() + "()";

        StringBuilder params = new StringBuilder("{");
        //参数值
        Object[] argValues = joinPoint.getArgs();
        //参数名称
        String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        if (argValues != null) {
            for (int i = 0; i < argValues.length; i++) {
                params.append(" ").append(argNames[i]).append(": ").append(argValues[i]);
            }
        }
        // 描述
        if (log != null) {
            log.setDescription(aopLog.value());
        }
        //类型 0-后台 1-前台
        assert log != null;
        log.setType(aopLog.type());
        if (getUid() != null) {
            log.setUid(getUid());
        }
//        log.setRequestIp(StringUtils.getIp(RequestHolder.getHttpServletRequest()));
        String username = getUsername();
        String loginPath = "login";
        if (loginPath.equals(signature.getName())) {
            try {
                assert argValues != null;
                username = new JSONObject(argValues[0]).get("username").toString();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
//        log.setAddress(StringUtils.getCityInfo(log.getRequestIp()));
        log.setMethod(methodName);
        log.setUsername(username);
        log.setParams(params.toString() + " }");
        logService.saveSysLog(log);
    }

    public String getUsername() {
        try {
            //return SecurityUtils.getUsername();
            return "";
        } catch (Exception e) {
            return "";
        }
    }

    public Long getUid() {
        try {
            //return SecurityUtils.getLoginUser().getId();
            return 0L;
        } catch (Exception e) {
            return 0L;
        }
    }
}
