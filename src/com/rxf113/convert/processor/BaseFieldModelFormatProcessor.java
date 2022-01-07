package com.rxf113.convert.processor;

import com.rxf113.convert.FieldModel;

import java.util.List;

public class BaseFieldModelFormatProcessor implements FieldModelFormatProcessor {
    @Override
    public String format(FieldModel fieldModel) {
        return fieldModel.getComment() + "\r\n" +
                fieldModel.getModifier() + " " +
                fieldModel.getVariableType() + " " +
                fieldModel.getFieldName() + ";";
    }

    @Override
    public String formatAll(List<FieldModel> fieldModes) {
        StringBuilder sb = new StringBuilder(64);
        for (FieldModel fieldMode : fieldModes) {
            sb.append(format(fieldMode)).append("\r\n").append("\r\n");
        }
        return sb.toString();
    }
}
