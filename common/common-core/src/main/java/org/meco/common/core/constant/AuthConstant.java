package org.meco.common.core.constant;

/**
 * 授权常量
 *
 * @author ZhangYang
 * @date 2021/1/6
 */
public interface AuthConstant {

    /**
     * 对称秘钥
     */
    String SIGNING_KEY = "meco123";

    String AUTH_KEY = "AUTH_KEY";

    /**
     * 网关响应消息
     */
    String LACK_TOKEN = "令牌缺失，拒绝访问";

    String DECRYPTION_FAILURE = "解密失败，请求未授权";

    String TOKEN_EXPIRED = "令牌已过期，请重新认证";
}
