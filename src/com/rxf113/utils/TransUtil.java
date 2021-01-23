package com.rxf113.utils;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import org.apache.commons.lang.StringUtils;

/**
 * 工具类
 *
 * @author rxf113
 */
public class TransUtil {
    /**
     * 获取选中文本
     *
     * @param editor editor
     * @return selectedText
     */
    public static String getSelectedText(Editor editor) {
        if (null == editor) {
            return null;
        }
        SelectionModel selectionModel = editor.getSelectionModel();
        String selectedText = selectionModel.getSelectedText();
        if (StringUtils.isBlank(selectedText)) {
            return null;
        }
        return selectedText;
    }

    public static String toLowercase(String originStr) {
        return originStr.toLowerCase();
    }

    public static String toUppercase(String originStr) {
        return originStr.toUpperCase();
    }

    /**
     * 驼峰转下划线
     */
    public static String hump2UnderLine(String originStr) {
        char[] chars = originStr.toCharArray();
        StringBuilder sb = new StringBuilder(chars.length + chars.length / 2);
        sb.append(chars[0]);
        for (int i = 1; i < chars.length; i++) {
            char currentChar = chars[i];
            if (Character.isUpperCase(currentChar)) {
                currentChar = Character.toLowerCase(currentChar);
                sb.append("_");
            }
            sb.append(currentChar);
        }
        return sb.toString();
    }

    /**
     * 下划线转驼峰
     */
    public static String underLine2Hump(String originStr) {
        char[] chars = originStr.toCharArray();
        StringBuilder sb = new StringBuilder(chars.length);
        int flag = 0;
        for (char aChar : chars) {
            if (aChar == '_') {
                flag = 1;
                continue;
            }
            if (flag == 1) {
                aChar = Character.toUpperCase(aChar);
                flag = 0;
            }
            sb.append(aChar);
        }
        return sb.toString();
    }
}
