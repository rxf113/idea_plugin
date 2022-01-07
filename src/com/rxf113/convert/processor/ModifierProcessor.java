package com.rxf113.convert.processor;

/**
 * 修饰符处理
 *
 * @author rxf113
 */
public interface ModifierProcessor extends RowProcessor {
    String process(String line);
}
