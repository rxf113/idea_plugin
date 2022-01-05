package com.rxf113.convert.processor;

/**
 * 格式检查
 *
 * @author rxf113
 */
public interface FormatCheckProcessor extends RowProcessor {

    /**
     * 校验
     *
     * @param sqlStr sql
     * @return true/false
     */
    boolean check(String sqlStr);
}
