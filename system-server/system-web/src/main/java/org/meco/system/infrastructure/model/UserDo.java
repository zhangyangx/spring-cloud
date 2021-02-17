package org.meco.system.infrastructure.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.meco.mybatis.model.BaseEntity;

/**
 * @author ZhangYang
 * @date 2020/12/22
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("user")
public class UserDo extends BaseEntity {

    private String username;

    private String password;
}
