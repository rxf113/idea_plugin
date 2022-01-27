package com.rxf113.convert.processor;


import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * base 变量类型处理
 *
 * @author rxf113
 */
public class BaseVariableTypeProcess implements VariableTypeProcess {

    static final Pattern DB_TYPE_PATTERN = Pattern.compile("^\\s*\\w+\\s(\\w+)");

    static final Map<String, String> DB_TYPE_2_JAVA_TYPE = new HashMap<>(16, 1);

    static final Map<String, List<String>> MYSQL_TYPE = new HashMap<>(16, 1);


    static {
        //整型
        MYSQL_TYPE.put("Integer",
                Lists.newArrayList("tinyint", "smallint", "mediumint", "int", "bigint"));
        //浮点
        MYSQL_TYPE.put("Double", Lists.newArrayList("float", "double"));
        //time
        MYSQL_TYPE.put("LocalDateTime", Lists.newArrayList("time", "date", "datetime", "timestamp"));
        //string
        MYSQL_TYPE.put("String", Lists.newArrayList("set", "enum", "blob", "text", "varchar", "char"));

        MYSQL_TYPE.forEach((k, v) -> {
            for (String type : v) {
                DB_TYPE_2_JAVA_TYPE.put(type, k);
            }
        });
    }

    @Override
    public String process(String rowStr) {
        String val = extractValByRegex(DB_TYPE_PATTERN, rowStr);
        return DB_TYPE_2_JAVA_TYPE.get(val);
    }
}
