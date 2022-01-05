package com.rxf113.convert;

import com.rxf113.convert.processor.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 基础实现
 *
 * @author rxf113
 */
public class BaseSql2EntityConverter implements Sql2EntityFieldsConverter {

    private final ModifierProcessor modifierProcessor;

    private final VariableTypeProcess variableTypeProcess;

    private final FieldNameProcessor fieldNameProcessor;

    private final CommentProcessor commentProcessor;

    private final FormatCheckProcessor formatCheckProcessor;

    private final FieldModelFormatter fieldModelFormatter;

    public BaseSql2EntityConverter(FieldModelFormatter fieldModelFormatter, ModifierProcessor modifierProcessor, VariableTypeProcess variableTypeProcess, FieldNameProcessor fieldNameProcessor, CommentProcessor commentProcessor, FormatCheckProcessor formatCheckProcessor) {
        this.modifierProcessor = modifierProcessor;
        this.variableTypeProcess = variableTypeProcess;
        this.fieldNameProcessor = fieldNameProcessor;
        this.commentProcessor = commentProcessor;
        this.formatCheckProcessor = formatCheckProcessor;
        this.fieldModelFormatter = fieldModelFormatter;
    }

    @Override
    public String convert(String sqlStr) {
        if (formatCheckProcessor.check(sqlStr)) {
            String[] rows = sqlStr.split(",");
            List<FieldModel> fieldModels = new ArrayList<>(4);
            for (String row : rows) {
                FieldModel fieldModel = new FieldModel();
                //修饰符
                fieldModel.setModifier(modifierProcessor.process(row));
                //变量类型
                fieldModel.setVariableType(variableTypeProcess.process(row));
                //变量名
                fieldModel.setFieldName(fieldNameProcessor.process(row));
                //注释
                fieldModel.setComment(commentProcessor.process(row));
            }
            return fieldModelFormatter.formatAll(fieldModels);

        } else {
            throw new IllegalArgumentException("参数格式不支持");
        }
    }
}
