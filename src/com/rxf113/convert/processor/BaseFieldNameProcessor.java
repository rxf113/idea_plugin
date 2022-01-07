package com.rxf113.convert.processor;

import com.rxf113.utils.TransUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * base 变量类型处理
 *
 * @author rxf113
 */
public class BaseFieldNameProcessor implements FieldNameProcessor {

    static final Pattern DB_FIELD_PATTERN = Pattern.compile("\\s*(\\w+)");

    @Override
    public String process(String rowStr) {
        String fieldName = extractValByRegex(DB_FIELD_PATTERN, rowStr);
        return TransUtil.underLine2Hump(fieldName);
    }
}
