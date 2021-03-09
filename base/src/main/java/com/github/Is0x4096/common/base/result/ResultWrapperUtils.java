package com.github.Is0x4096.common.base.result;

import java.util.Map;
import java.util.Objects;

/**
 * @author 0x4096.peng@gmail.com
 * @date 2021/3/5
 */
public class ResultWrapperUtils {

    /**
     * 请求参数缺失
     */
    public static <T> ResultWrapper<T> parametersMiss(String... args) {
        ResultWrapper<T> result = new ResultWrapper<>();
        result.setSuccess(false);
        result.setCode("400");
        if (Objects.nonNull(args)) {
            result.setMessage("请求缺失参数,请检查以下参数是否完整: " + String.join(", ", args));
        } else {
            result.setCode("请求缺失必要参数");
        }
        return result;
    }

    public static <T> ResultWrapper<T> success() {
        return success(null, "success", null);
    }

    public static <T> ResultWrapper<T> success(T data) {
        return success(data, "success", null);
    }

    public static <T> ResultWrapper<T> success(T data, String msg) {
        return success(data, msg, null);
    }

    /**
     * 成功处理封装
     */
    public static <T> ResultWrapper<T> success(T data, String msg, Map<String, Object> extensionMap) {
        ResultWrapper<T> result = new ResultWrapper<>();
        result.setCode("200");
        result.setMessage(msg);
        result.setData(data);
        result.setExtensionMap(extensionMap);
        return result;
    }

    public static <T> ResultWrapper<T> failure() {
        return failure("502", "服务器暂时不可用", null);
    }


    public static <T> ResultWrapper<T> failure(String code, String message) {
        return failure(code, message, null);
    }

    /**
     * 失败处理封装
     */
    public static <T> ResultWrapper<T> failure(String code, String message, Map<String, Object> extensionMap) {
        ResultWrapper<T> result = new ResultWrapper<>();
        result.setSuccess(false);
        result.setCode(code);
        result.setMessage(message);
        result.setExtensionMap(extensionMap);
        return result;
    }

    public static <T> ResultWrapper<T> serverError() {
        return serverError(null);
    }

    /**
     * 系统内部错误
     */
    public static <T> ResultWrapper<T> serverError(Map<String, Object> extensionMap) {
        ResultWrapper<T> result = new ResultWrapper<>();
        result.setSuccess(false);
        result.setCode("500");
        result.setMessage("服务器内部错误");
        result.setExtensionMap(extensionMap);
        return result;
    }

}
