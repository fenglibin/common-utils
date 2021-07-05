package com.eeeffff.common.utils;


import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * 符合ERP业务需要的字符串处理类.
 *
 * @author jiangchaoo
 */
public class StringUtil {

    public static List<Integer> toIntegerList(String ids, String regex) {
        List<Integer> idList = new ArrayList<>();
        if (StringUtils.isNotEmpty(ids)) {
            String[] idStr = ids.split(regex);
            for (String id : idStr) {
                idList.add(Integer.parseInt(id));
            }
        }
        return idList;
    }

    /**
     * 获得一个UUID
     *
     * @return String UUID
     */
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        //去掉“-”符号
        return uuid.replaceAll("-", "");
    }


    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) {
    	getUUID();
    }

}

