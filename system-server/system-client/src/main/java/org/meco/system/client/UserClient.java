package org.meco.system.client;

import org.meco.system.api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author ZhangYang
 * @date 2020/12/23
 */
@FeignClient("system-server")
public interface UserClient extends UserApi {
}
