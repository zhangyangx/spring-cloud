package org.meco.common.core.constant;

/**
 * @author ZhangYang
 * @date 2020/12/31
 */
public interface CommonConstant {
    String RESET_PASS = "重置密码";

    String RESET_MAIL = "重置邮箱";

    /**
     * 常用接口
     */
    class Url{
        //免费图床
        public static final String SM_MS_URL = "https://sm.ms/api";

        // IP归属地查询
        public static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp?ip=%s&json=true";
    }
}
