package com.rxf113.utils;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 11313
 */
public class Properties2YmlUtil {
    public static void main(String[] args) {
        //测试
        String df = "spring:\n" +
                " datasource:\n" +
                "  type: com.zaxxer.hikari.HikariDataSource\n" +
                "\n" +
                "  hikari:\n" +
                "   jdbc-url: jdbc:mysql://localhost:3306/blogdb?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&allowMultiQueries=true\n" +
                "\n" +
                "   username: root\n" +
                "\n";

        String s = properties2YmlStr(df);
        System.out.println(s);
    }

    public static String properties2YmlStr(String proStr) {
        //按行拆分properties字符串
        String[] proStrArr = proStr.split("\\n");
        Map<String, Object> globalMap = new HashMap<>(8, 1);
        for (String line : proStrArr) {
            //排除注释
            if(!line.matches("^\\s*$") && !line.matches("^\\s*#.*$")){
                recMap(line, globalMap);
            }

        }
        return extractVal(globalMap, 0);
    }

    /**
     * 递归组装map
     *
     * @param str str
     * @param map map
     */
    @SuppressWarnings("unchecked")
    private static void recMap(String str, Map<String, Object> map) {
        int pointIndex = str.indexOf(".");
        int eqIndex = str.indexOf("=");
        if (pointIndex == -1 || pointIndex > eqIndex) {
            if (eqIndex != -1) {
                map.put(str.substring(0, eqIndex), str.substring(eqIndex + 1));
                return;
            } else {
                throw new IllegalArgumentException("参数有误!");
            }
        }
        String key = str.substring(0, pointIndex);
        String val = str.substring(pointIndex + 1);
        Map<String, Object> newMap = (Map<String, Object>) map.computeIfAbsent(key, k -> Maps.newHashMap());
        recMap(val, newMap);
    }

    /**
     * 提取yml值
     *
     * @param map        map
     * @param blankSpace blankSpace
     * @return
     */
    @SuppressWarnings("unchecked")
    private static String extractVal(Map<String, Object> map, int blankSpace) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            sb.append(Strings.repeat(" ",blankSpace));
            sb.append(key).append(":");
            if (!(value instanceof String)) {
                sb.append("\r\n").append(extractVal((Map<String, Object>) value, blankSpace + 1));
            } else {
                sb.append(" ").append(value).append(Strings.repeat("\r\n",2));
            }
        }
        return sb.toString();
    }
}
