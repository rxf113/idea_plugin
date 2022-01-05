package com.rxf113.convert;

import java.util.List;

/**
 * 模型格式化
 *
 * @author rxf113
 */
public interface FieldModelFormatter {

    String format(FieldModel fieldModel);

    String formatAll(List<FieldModel> fieldModes);
}
