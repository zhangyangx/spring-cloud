package org.meco.system.api;

import org.meco.common.core.model.R;
import org.meco.system.dto.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ZhangYang
 * @date 2020/12/23
 */
@RequestMapping("/user")
public interface UserApi {

    @GetMapping
    R<UserDto> findUserByUsername(@RequestParam("username") String username);

    @GetMapping("/test")
    void test();
}
