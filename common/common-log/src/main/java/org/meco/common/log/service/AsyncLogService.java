package org.meco.common.log.service;

import com.alibaba.fastjson.JSONObject;
import org.meco.common.log.domain.Log;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author ZhangYang
 * @date 2020/12/31
 */
@Service
public class AsyncLogService {
//    private final RemoteLoggingService logService;
//
//    public AsyncLogService(RemoteLoggingService logService) {
//        this.logService = logService;
//    }


    /**
     * 保存系统日志记录
     */
    @Async
    public void saveSysLog(Log log) {

        System.out.println(JSONObject.toJSONString(log));
        //logService.saveLog(log);
    }
}
