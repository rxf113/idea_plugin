package com.rxf113.convert.processor;

import java.util.regex.Pattern;

/**
 * 匹配逗号分隔的sql格式
 * <p>
 * id   varchar(16) not null primary key,
 * name varchar(8)  null,
 * age  tinyint     null
 *
 * @author 11313
 */
public class CsvFormatCheckProcessor implements FormatCheckProcessor {

    static final Pattern ROW_PATTERN = Pattern.compile("^\\s*\\w+\\s+\\w+(\\s+\\w+)?.*$");

    @Override
    public boolean check(String sqlStr) {
        if (sqlStr == null || "".equals(sqlStr.trim())) {
            return false;
        }
        sqlStr = sqlStr.replaceAll("[`']","");
        sqlStr = sqlStr.replaceAll("(.*)\\n(.*)", "$1 $2");

        String[] rows = sqlStr.split(",");
        for (String row : rows) {
            if (!ROW_PATTERN.matcher(row).matches()) {
                return false;
            }
        }
        return true;
    }
}
