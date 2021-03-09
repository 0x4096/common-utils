package com.github.Is0x4096.common.utils.mapper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

/**
 * @author 0x4096.peng@gmail.com
 * @project common-utils
 * @datetime 2020/2/2 16:36
 * @description
 * @readme
 */
public class BeanMapperUtils {

    private BeanMapperUtils() {
    }

    private static final Logger logger = LoggerFactory.getLogger(BeanMapperUtils.class);

    private static final XmlMapper xmlMapper = new XmlMapper();

    private static final ObjectMapper objectMapper = new ObjectMapper();


    /**
     * XML转JSON
     *
     * @param xml xml
     * @return XML -> JSON
     * @apiNote 若发生异常, 则返回 null
     */
    public static String xml2Json(String xml) {
        StringWriter stringWriter = new StringWriter();
        JsonParser jsonParser;

        try {
            jsonParser = xmlMapper.getFactory().createParser(xml);
            JsonGenerator jsonGenerator = objectMapper.getFactory().createGenerator(stringWriter);
            while (jsonParser.nextToken() != null) {
                jsonGenerator.copyCurrentEvent(jsonParser);
            }
            jsonParser.close();
            jsonGenerator.close();
        } catch (Exception e) {
            logger.error("", e);
            return null;
        }

        return stringWriter.toString();
    }


    /**
     * JSON转XML
     *
     * @param jsonStr jsonStr
     * @return JSON转XML
     * @apiNote 若发生异常, 则返回 null
     */
    public static String json2Xml(String jsonStr) {
        try {
            JsonNode root = objectMapper.readTree(jsonStr);
            return xmlMapper.writeValueAsString(root);
        } catch (IOException e) {
            logger.error("", e);
        }
        return null;
    }


    /**
     * 对象转XML
     */
    public static String bean2Xml(Object bean) {
        return XmlMapperUtils.toXml(bean, false);
    }


    /**
     * XML转对象
     */
    public static <T> T xml2Bean(String xml, Class<T> clazz) {
        return XmlMapperUtils.fromXml(xml, clazz);
    }


    /**
     * json -> Map
     */
    public static Map<String, Object> json2Map(String jsonStr) {
        return JSON.parseObject(jsonStr, new TypeReference<Map<String, Object>>() {
        });
    }


    /**
     * map -> Json
     */
    public static String map2Json(Map<String, Object> map) {
        return JSON.toJSONString(map);
    }

}
