package com.rxf113;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.rxf113.utils.TransUtil;

/**
 * author rxf113
 **/
public class ToUppercaseAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        Editor editor = anActionEvent.getData(PlatformDataKeys.EDITOR);
        String selectedText = TransUtil.getSelectedText(editor);
        if (selectedText == null) {
            return;
        }
        WriteCommandAction.runWriteCommandAction(anActionEvent.getData(PlatformDataKeys.PROJECT), new Runnable() {
            @Override
            public void run() {
                editor.getDocument().replaceString(editor.getSelectionModel().getSelectionStart(), editor.getSelectionModel().getSelectionEnd(), TransUtil.toUppercase(selectedText));
            }
        });
    }
}
