package org.meco.system.api.impl;

import org.meco.common.core.enums.ExceptionEnum;
import org.meco.common.core.exception.BizException;
import org.meco.common.core.model.R;
import org.meco.common.core.util.RequestUtils;
import org.meco.system.api.UserApi;
import org.meco.system.dto.UserDto;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangYang
 * @date 2020/12/23
 */
@RestController
public class UserApiImpl implements UserApi {
    @Override
    public R<UserDto> findUserByUsername(String username) {
        UserDto userDto = new UserDto();
        userDto.setPassword("123");
        userDto.setUsername(username);
        throw new BizException(ExceptionEnum.FAIL);

    }

    @Override
    public void test() {
        System.out.println(RequestUtils.getRequestIp(RequestUtils.getHttpServletRequest()));
//        throw new BizException(ExceptionEnum.UNAUTHORIZED);
        System.out.println(1/0);
    }
}
