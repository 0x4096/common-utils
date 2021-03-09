package com.github.Is0x4096.common.utils.encrypt.signature;

import com.github.Is0x4096.common.utils.encrypt.EncryptUtils;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * @Author: 0x4096.peng@gmail.com
 * @Project: common-utils
 * @DateTime: 2019-11-29 23:15
 * @Description: 签名工具类
 */
public class SignatureUtils {

    private SignatureUtils() {
    }

    private final static Logger logger = LoggerFactory.getLogger(SignatureUtils.class);

    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";

    private static final char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};


    /**
     * MD5 签名
     * 签名生成的通用步骤如下：
     * 第一步，设所有发送或者接收到的数据为集合M，将集合M内非空参数值的参数按照参数名ASCII码从小到大排序（字典序），使用URL键值对的格式（即key1=value1&key2=value2…）拼接成字符串stringA
     * 特别注意以下重要规则：
     * ◆ 参数名ASCII码从小到大排序（字典序）；
     * ◆ 如果参数的值为空不参与签名；
     * ◆ 参数名区分大小写；
     * ◆ 验证调用返回或微信主动通知签名时，传送的sign参数不参与签名，将生成的签名与该sign值作校验。
     * ◆ 微信接口可能增加字段，验证签名时必须支持增加的扩展字段
     * 第二步，在stringA最后拼接上key得到stringSignTemp字符串，并对stringSignTemp进行MD5运算，再将得到的字符串所有字符转换为大写，得到sign值signValue
     *
     * @param bizMap    业务参数
     * @param secretKey secretKey
     * @return signString
     * @readme link https://pay.weixin.qq.com/wiki/doc/api/jsapi_sl.php?chapter=4_3
     */
    public static String sign(Map<String, Object> bizMap, String secretKey) {
        Set<String> keySet = bizMap.keySet();
        String[] keyArray = keySet.toArray(new String[0]);
        Arrays.sort(keyArray);

        StringBuilder sb = new StringBuilder();
        for (String key : keyArray) {
            if ("sign".equals(key)) {
                continue;
            }

            if (bizMap.get(key) != null) {
                sb.append(key).append("=").append(bizMap.get(key)).append("&");
            }
        }
        sb.append("key=").append(secretKey);
        return EncryptUtils.MD5(sb.toString()).toUpperCase();
    }


    /**
     * HMAC-SHA1加密方案
     *
     * @param content   待加密内容
     * @param secretKey 密钥
     * @return HMAC_SHA1加密后的字符串
     */
    public static String HMACSHA1(String content, String secretKey) {
        Preconditions.checkNotNull(content, "content 不能为 null");
        Preconditions.checkNotNull(secretKey, "secretKey 不能为 null");

        byte[] secretKeyBytes = secretKey.getBytes();
        SecretKey secretKeyObj = new SecretKeySpec(secretKeyBytes, HMAC_SHA1_ALGORITHM);

        try {
            Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
            mac.init(secretKeyObj);
            byte[] text = content.getBytes(StandardCharsets.UTF_8);
            text = mac.doFinal(text);
            return bytesToHexString(text);
        } catch (Exception e) {
            logger.error("", e);
        }
        return content;
    }


    /**
     * 获取字节数组的16进制字符串表示形式
     * 范例：0xff->'ff'
     *
     * @param bytes 字节数组
     * @return string-16进制的字符串表示形式
     */
    private static String bytesToHexString(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte ib : bytes) {
            char[] ob = new char[2];
            ob[0] = Digit[(ib >>> 4) & 0X0f];
            ob[1] = Digit[ib & 0X0F];
            hexString.append(ob);
        }
        return hexString.toString();
    }

}
