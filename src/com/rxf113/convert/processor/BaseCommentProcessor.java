package com.rxf113.convert.processor;

import java.util.regex.Pattern;

/**
 * 注释处理
 *
 * @author rxf113
 */
public class BaseCommentProcessor implements CommentProcessor {

    static final Pattern COMMENT_PATTERN = Pattern.compile(".*[comment|COMMENT]\\s(.*)$");


    @Override
    public String process(String rowStr) {
        String val = extractValByRegex(COMMENT_PATTERN, rowStr);
        String comment = "/**\n" +
                " * ${val}\n" +
                " */";
        return comment.replace("${val}", val);
    }
}
