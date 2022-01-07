package com.rxf113.convert;


/**
 * sql转实体字段
 *
 * @author rxf113
 */
public interface Sql2EntityFieldsConverter {

    /**
     * 转换
     *
     * @param sqlStr sql
     * @return 字段
     */
    String convert(String sqlStr);
}