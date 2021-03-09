package com.github.Is0x4096.common.utils.network.http;

import com.google.common.collect.Maps;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;

/**
 * @Author: 0x4096.peng@gmail.com
 * @Project: common-utils
 * @DateTime: 2019-10-29 23:21
 * @Description: HTTP请求头处理工具类
 */
public class HttpRequestHeaderUtils {

    private HttpRequestHeaderUtils() {
    }

    /**
     * 通过请求头 key 获取 value
     *
     * @param request request
     * @param key     请求头 key
     * @return null or value
     */
    public static String getByKey(HttpServletRequest request, String key) {
        return getAll(request).get(key);
    }


    /**
     * 获取所有请求头信息
     *
     * @param request request
     * @return headers
     */
    public static Map<String, String> getAll(HttpServletRequest request) {
        Map<String, String> headers = Maps.newHashMap();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String element = headerNames.nextElement();
            headers.put(element, request.getHeader(element));
        }
        return headers;
    }

}
