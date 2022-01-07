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
        String[] rows = sqlStr.split(",");
        List<FieldModel> fieldModels = Arrays.stream(rows)
                .map(row -> row.replace("\\n", "").replaceAll("\\s+", " ").trim())
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

    public static void main(String[] args) {
        MysqlSql2EntityConverter sql2EntityConverter = new MysqlSql2EntityConverter(
                new BaseFieldModelFormatProcessor(),
                new BaseModifierProcessor(),
                new BaseVariableTypeProcess(),
                new BaseFieldNameProcessor(),
                new BaseCommentProcessor(),
                new CsvFormatCheckProcessor());
        String convert = sql2EntityConverter.convert("node_name           varchar(64)  null comment '节点名称',\n" +
                "    detail              varchar(255) null comment '详情',\n" +
                "    process_instance_id varchar(64)  null comment '流程实例id',\n" +
                "    task_def_key        varchar(64)  null,\n" +
                "    tenant_id           varchar(16)  null comment '租戶id'");
        System.out.println(convert);
    }
}
