package com.rxf113.convert;

/**
 * 字段模型
 *
 * @author rxf113
 */
public class FieldModel {
    /**
     * 修饰符
     */
    private String modifier;
    /**
     * 变量类型
     */
    private String variableType;
    /**
     * 字段名
     */
    private String fieldName;
    /**
     * 注释
     */
    private String comment;

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getVariableType() {
        return variableType;
    }

    public void setVariableType(String variableType) {
        this.variableType = variableType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
