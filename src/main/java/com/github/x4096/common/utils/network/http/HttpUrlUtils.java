package com.github.x4096.common.utils.network.http;

import com.github.x4096.common.utils.text.ValidateUtils;
import com.google.common.base.Preconditions;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author 0x4096.peng@gmail.com
 * @project common-utils
 * @datetime 2020/6/11 23:44
 * @description httpUrl 工具类
 * @readme
 */
public class HttpUrlUtils {

    private HttpUrlUtils() {
    }


    /**
     * 从 URL 中获取请求参数转 Map
     *
     * @param url exp: https://0x4096.com?k1=v1&k2=v2
     * @return Map exp: k1=v1,k2=v2
     */
    public static Map<String, String> urlToMap(String url) {
        Map<String, String> returnMap = new HashMap<>();

        String[] strings = url.split("&");
        for (String str : strings) {
            strings = str.split("=");
            if (strings.length == 2) {
                returnMap.put(strings[0], strings[1]);
            } else {
                returnMap.put(strings[0], "");
            }
        }

        return returnMap;
    }


    /**
     * Get url join
     *
     * @param url    base url , exp: https://0x4096.com
     * @param params params   , exp: k1=v1,k2=v2
     * @return String         , exp: https://0x4096.com?k1=v1&k2=v2
     */
    public static String urlJoin(String url, Map<String, String> params) {
        Preconditions.checkArgument(ValidateUtils.isUrl(url), "URL无效: " + url);
        Preconditions.checkNotNull(params, "params 不能为 null");

        StringBuilder endUrl = new StringBuilder(url);
        boolean isFirst = true;
        Set<Map.Entry<String, String>> entrySet = params.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            if (isFirst && !url.contains("?")) {
                isFirst = false;
                endUrl.append("?");
            } else {
                endUrl.append("&");
            }
            endUrl.append(entry.getKey());
            endUrl.append("=");
            endUrl.append(entry.getValue());
        }
        return endUrl.toString();
    }


    /**
     * HTTP 请求参数转 Map
     *
     * @param request HttpServletRequest  exp: https://0x4096.com?k1=v1&k2=v2
     * @return 参数 Map                    exp: k1=v1,k2=v2
     * @apiNote 注意: 只返回 HTTP 请求参数中的第一个,若同一 key 对应多个 value 请不要使用此方法
     */
    public Map<String, String> paramsToMap(HttpServletRequest request) {
        Preconditions.checkNotNull(request, "HttpServletRequest 不能为null");

        Map<String, String[]> stringMap = request.getParameterMap();
        Map<String, String> result = new HashMap<>(stringMap.size());

        stringMap.forEach((k, vs) -> result.put(k, vs[0]));
        return result;
    }

}
