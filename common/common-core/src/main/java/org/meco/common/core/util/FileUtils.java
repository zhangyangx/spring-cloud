package org.meco.common.core.util;

import cn.hutool.http.HttpUtil;
import lombok.NoArgsConstructor;

/**
 * @author ZhangYang
 * @date 2021/1/6
 */
@NoArgsConstructor
public class FileUtils {

    public static byte[]  fileDownload(String url){
        byte[] bytes = HttpUtil.downloadBytes(url);
        return bytes;
    }

}
