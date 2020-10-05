package com.github.x4096.common.utils.test.mapper;

import com.alibaba.fastjson.JSON;
import com.github.x4096.common.utils.mapper.XmlMapperUtils;
import com.github.x4096.common.utils.test.pojo.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: 0x4096.peng@gmail.com
 * @Project: common-utils
 * @DateTime: 2019-10-07 14:36
 * @Description:
 */
public class XmlMapperUtilsTest {

    /**
     * 普通对象,即不使用相关注解
         <Student>
         <username>我梦</username>
         <age>18</age>
         </Student>
     */

    public static void main(String[] args) {
        Student student = new Student();
        student.setAge(18);
        student.setUsername("我梦");

        String xml = XmlMapperUtils.toXml(student, false);
        System.err.println(xml);


        List<Student> studentList = new ArrayList<>();
        studentList.add(student);

        Student student1 = new Student();
        student1.setAge(23);
        student1.setUsername("我梦23");
        studentList.add(student1);


        xml = XmlMapperUtils.toXml(studentList, "6666", Student.class, false);

        System.err.println(xml);

        xml = "<xml><appid><![CDATA[wxad136b9887dbb035]]></appid>\\n<bank_type><![CDATA[PAB_CREDIT]]></bank_type>\\n<cash_fee><![CDATA[45050]]></cash_fee>\\n<coupon_count><![CDATA[2]]></coupon_count>\\n<coupon_fee>600</coupon_fee>\\n<coupon_fee_0><![CDATA[500]]></coupon_fee_0>\\n<coupon_fee_1><![CDATA[100]]></coupon_fee_1>\\n<coupon_id_0><![CDATA[15109608971]]></coupon_id_0>\\n<coupon_id_1><![CDATA[15109880255]]></coupon_id_1>\\n<fee_type><![CDATA[CNY]]></fee_type>\\n<is_subscribe><![CDATA[N]]></is_subscribe>\\n<mch_id><![CDATA[1574442701]]></mch_id>\\n<nonce_str><![CDATA[cc2e53c5f9a9a2e40766df661fa2826d]]></nonce_str>\\n<openid><![CDATA[ovllB5kf8BmLKub_jfleQZIr8nLY]]></openid>\\n<out_trade_no><![CDATA[BQ200823201523495005291881181184]]></out_trade_no>\\n<result_code><![CDATA[SUCCESS]]></result_code>\\n<return_code><![CDATA[SUCCESS]]></return_code>\\n<sign><![CDATA[CF8C40DFC449E5763DDE829FEC85CBE1]]></sign>\\n<sub_appid><![CDATA[wxb906dc7a630d2513]]></sub_appid>\\n<sub_is_subscribe><![CDATA[N]]></sub_is_subscribe>\\n<sub_mch_id><![CDATA[1583719091]]></sub_mch_id>\\n<sub_openid><![CDATA[oCj3O4vDgAvgCXuU5_axEucScgNI]]></sub_openid>\\n<time_end><![CDATA[20200823201540]]></time_end>\\n<total_fee>45650</total_fee>\\n<trade_type><![CDATA[JSAPI]]></trade_type>\\n<transaction_id><![CDATA[4200000699202008230378983895]]></transaction_id>\\n</xml>";

        Map<String, Object> bizMap = XmlMapperUtils.xml2Map(xml);

        System.err.println(JSON.toJSONString(bizMap));


        System.err.println(XmlMapperUtils.toXml(bizMap));

    }

}
