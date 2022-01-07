package com.rxf113;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.rxf113.utils.TransUtil;
import org.jetbrains.annotations.NotNull;

/**
 * @author 11313
 */
public class UnderLine2HumpAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        Editor editor = anActionEvent.getData(CommonDataKeys.EDITOR);
        String selectedText = TransUtil.getSelectedText(editor);
        if (selectedText == null) {
            return;
        }
        WriteCommandAction.runWriteCommandAction(anActionEvent.getData(CommonDataKeys.PROJECT), new Runnable() {
            @Override
            public void run() {
                editor.getDocument().replaceString(editor.getSelectionModel().getSelectionStart(), editor.getSelectionModel().getSelectionEnd(), TransUtil.underLine2Hump(selectedText));
            }
        });
    }
}
