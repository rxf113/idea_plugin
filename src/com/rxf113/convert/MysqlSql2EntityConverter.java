package com.rxf113.convert;

import com.rxf113.convert.processor.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 基础实现
 *
 * @author rxf113
 */
public class MysqlSql2EntityConverter implements Sql2EntityFieldsConverter {

    private final ModifierProcessor modifierProcessor;

    private final VariableTypeProcess variableTypeProcess;

    private final FieldNameProcessor fieldNameProcessor;

    private final CommentProcessor commentProcessor;

    private final FormatCheckProcessor formatCheckProcessor;

    private final FieldModelFormatProcessor fieldModelFormatProcessor;

    public MysqlSql2EntityConverter(FieldModelFormatProcessor fieldModelFormatProcessor, ModifierProcessor modifierProcessor, VariableTypeProcess variableTypeProcess, FieldNameProcessor fieldNameProcessor, CommentProcessor commentProcessor, FormatCheckProcessor formatCheckProcessor) {
        this.modifierProcessor = modifierProcessor;
        this.variableTypeProcess = variableTypeProcess;
        this.fieldNameProcessor = fieldNameProcessor;
        this.commentProcessor = commentProcessor;
        this.formatCheckProcessor = formatCheckProcessor;
        this.fieldModelFormatProcessor = fieldModelFormatProcessor;
    }

    public ModifierProcessor getModifierProcessor() {
        return modifierProcessor;
    }

    public VariableTypeProcess getVariableTypeProcess() {
        return variableTypeProcess;
    }

    public FieldNameProcessor getFieldNameProcessor() {
        return fieldNameProcessor;
    }

    public CommentProcessor getCommentProcessor() {
        return commentProcessor;
    }

    public FormatCheckProcessor getFormatCheckProcessor() {
        return formatCheckProcessor;
    }

    public FieldModelFormatProcessor getFieldModelFormatProcessor() {
        return fieldModelFormatProcessor;
    }

    @Override
    public String convert(String sqlStr) {
        String[] rows = sqlStr.split(",\n");
        List<FieldModel> fieldModels = Arrays.stream(rows)
                .map(row -> row.replace("\\n", "").replaceAll("\\s+", " ").replaceAll("[`']","").trim())
                .map(row -> {
                    FieldModel fieldModel = new FieldModel();
                    //修饰符
                    fieldModel.setModifier(modifierProcessor.process(row));
                    //变量类型
                    fieldModel.setVariableType(variableTypeProcess.process(row));
                    //变量名
                    fieldModel.setFieldName(fieldNameProcessor.process(row));
                    //注释
                    fieldModel.setComment(commentProcessor.process(row));
                    return fieldModel;
                }).collect(Collectors.toList());
        return fieldModelFormatProcessor.formatAll(fieldModels);
    }
}
