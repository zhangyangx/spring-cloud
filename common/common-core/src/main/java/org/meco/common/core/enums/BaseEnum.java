package org.meco.common.core.enums;

/**
 * @author ZhangYang
 * @date 2021/1/6
 */
public interface BaseEnum {
    /**
     * 枚举key
     *
     * @return int
     */
    int getCode();

    /**
     * 描述信息
     *
     * @return desc
     */
    String getMsg();
}
