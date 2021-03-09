package com.github.Is0x4096.common.base.result;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

/**
 * 统一结果包装
 *
 * @author 0x4096.peng@gmail.com
 * @date 2021/3/5
 */
@Setter
@Getter
public class ResultWrapper<T> implements Serializable {

    private static final long serialVersionUID = 945314152507157220L;

    /**
     * 本次处理成功与否
     */
    private boolean success;

    /**
     * 状态码
     */
    private String code;

    /**
     * 响应信息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 扩展Map
     */
    private Map<String, Object> extensionMap;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
