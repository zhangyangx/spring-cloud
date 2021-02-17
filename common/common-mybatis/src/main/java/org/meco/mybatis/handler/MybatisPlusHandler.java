package org.meco.mybatis.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author ZhangYang
 * @date 2020/12/22
 */
@Component
public class MybatisPlusHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        setFieldValByName("createdTime", new Date(), metaObject);
        setFieldValByName("updatedTime", new Date(), metaObject);
        setFieldValByName("createdBy", SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString(), metaObject);
        setFieldValByName("updatedBy", SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("updatedTime", new Date(), metaObject);
        setFieldValByName("updatedBy", SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString(), metaObject);
    }
}
