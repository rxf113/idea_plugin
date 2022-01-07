package com.rxf113.convert.processor;

import com.rxf113.convert.FieldModel;

import java.util.List;

/**
 * 模型格式化
 *
 * @author rxf113
 */
public interface FieldModelFormatProcessor {

    String format(FieldModel fieldModel);

    String formatAll(List<FieldModel> fieldModes);
}
