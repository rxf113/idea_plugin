package com.rxf113.convert.processor;


import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * base 变量类型处理
 *
 * @author rxf113
 */
public class BaseVariableTypeProcess implements VariableTypeProcess {

    static final Pattern DB_TYPE_PATTERN = Pattern.compile("\\s*\\w+\\s(\\w+)");

    static final Map<String, String> DB_TYPE_2_JAVA_TYPE = new HashMap<>();

    static {
        //加载配置
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("resources\\db.yml"));
            Properties properties = new Properties();
            properties.load(bufferedReader);
            properties.forEach((k, v) -> {
                if (StringUtils.isNotBlank((String) v)) {
                    String[] vals = v.toString().split(",");
                    for (String val : vals) {
                        DB_TYPE_2_JAVA_TYPE.put(val, (String) k);
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String process(String rowStr) {
        String val = extractValByRegex(DB_TYPE_PATTERN, rowStr);
        return DB_TYPE_2_JAVA_TYPE.get(val);
    }
}
