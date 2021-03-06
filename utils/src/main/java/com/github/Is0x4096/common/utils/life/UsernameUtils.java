package com.github.Is0x4096.common.utils.life;

import java.util.Random;

/**
 * @Author: 0x4096.peng@gmail.com
 * @Project: common-utils
 * @DateTime: 2019-10-12 23:43
 * @Description: 参考来源: https://www.jianshu.com/p/cc8d567ebda5
 */
public class UsernameUtils {

    private UsernameUtils() {
    }

    private static final String[] SURNAME = {"赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈", "褚", "卫", "蒋", "沈", "韩", "杨", "朱", "秦", "尤", "许",
            "何", "吕", "施", "张", "孔", "曹", "严", "华", "金", "魏", "陶", "姜", "戚", "谢", "邹", "喻", "柏", "水", "窦", "章", "云", "苏", "潘", "葛", "奚", "范",
            "彭", "郎", "鲁", "韦", "昌", "马", "苗", "凤", "花", "方", "俞", "任", "袁", "柳", "酆", "鲍", "史", "唐", "费", "廉", "岑", "薛", "雷", "贺", "倪", "汤",
            "滕", "殷", "罗", "毕", "郝", "邬", "安", "常", "乐", "于", "时", "傅", "皮", "卞", "齐", "康", "伍", "余", "元", "卜", "顾", "孟", "平", "黄", "和", "穆",
            "萧", "尹", "姚", "邵", "湛", "汪", "祁", "毛", "禹", "狄", "米", "贝", "明", "臧", "计", "伏", "成", "戴", "谈", "宋", "茅", "庞", "熊", "纪", "舒", "屈",
            "项", "祝", "董", "梁", "杜", "阮", "蓝", "闵", "席", "季"};

    private static final String GIRL = "秀娟英华慧巧美娜静淑惠珠翠雅芝玉萍红娥玲芬芳燕彩春菊兰凤洁梅琳素云莲真环雪荣爱妹霞香月莺媛艳瑞凡佳嘉琼勤珍贞莉桂娣叶璧璐娅琦晶妍茜秋珊莎锦黛青倩婷姣婉娴瑾颖露瑶怡婵雁蓓纨仪荷丹蓉眉君琴蕊薇菁梦岚苑婕馨瑗琰韵融园艺咏卿聪澜纯毓悦昭冰爽琬茗羽希宁欣飘育滢馥筠柔竹霭凝晓欢霄枫芸菲寒伊亚宜可姬舒影荔枝思丽 ";
    private static final String BOY = "伟刚勇毅俊峰强军平保东文辉力明永健世广志义兴良海山仁波宁贵福生龙元全国胜学祥才发武新利清飞彬富顺信子杰涛昌成康星光天达安岩中茂进林有坚和彪博诚先敬震振壮会思群豪心邦科承乐绍功松善厚庆磊民友裕河哲江超浩亮政谦亨奇固之轮翰朗伯宏言若鸣朋斌梁栋维启克伦翔旭鹏泽晨辰士以建家致树炎德行时泰盛雄琛钧冠策腾楠榕风航弘";

    private static final int SURNAME_LENGTH = SURNAME.length;
    private static final int GIRL_LENGTH = GIRL.length();
    private static final int BOY_LENGTH = BOY.length();

    private static final Random RANDOM = new Random();


    public static String generate() {
        int index = RANDOM.nextInt(SURNAME_LENGTH - 1);
        String name = SURNAME[index];
        int i = RANDOM.nextInt(3);
        if (i == 2) {
            int j = RANDOM.nextInt(GIRL_LENGTH - 2);
            if (j % 2 == 0) {
                name = name + GIRL.substring(j, j + 2);
            } else {
                name = name + GIRL.substring(j, j + 1);
            }

        } else {
            int j = RANDOM.nextInt(BOY_LENGTH - 2);
            if (j % 2 == 0) {
                name = name + BOY.substring(j, j + 2);
            } else {
                name = name + BOY.substring(j, j + 1);
            }
        }
        return name;
    }

}
