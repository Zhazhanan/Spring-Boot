package com.example.commonutils.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description
 * <br> 字符串操作工具类
 *
 * @author WangKun
 * @date 2019/07/03
 **/
public class ComStringUtils {

    /**
     * Description
     * <br> 字符串替换
     *
     * @param targetString 目标字符串
     * @param params       key：替换字符串标识，value：替换值
     * @author WangKun
     * @date 2019/07/03
     **/
    public static String replcePlaceHolder(String targetString, final Map<String, String> params) {
        if (null == params || params.isEmpty()) {
            return targetString;
        }
        String rule = StringUtils.join(params.keySet(), "|");
        Pattern pattern = Pattern.compile(rule);
        Matcher matcher = pattern.matcher(targetString);
        StringBuffer stringBuilder = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(stringBuilder, params.get(matcher.group()));
        }
        return matcher.appendTail(stringBuilder).toString();
    }

}
