package com.rxf113.convert.processor;


/**
 * 默认 private 修辞
 *
 * @author rxf113
 */
public class BaseModifierProcessor implements ModifierProcessor {
    @Override
    public String process(String line) {
        return "private";
    }
}
