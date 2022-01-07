package com.rxf113.convert.processor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 按行处理
 *
 * @author rxf113
 */
public interface RowProcessor {
    String process(String rowStr);

    default String extractValByRegex(Pattern pattern, String inputStr) {
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "null";
    }
}
