package com.rxf113;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.rxf113.utils.Properties2YmlUtil;
import com.rxf113.utils.TransUtil;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

/**
 * @author 11313
 */
public class Properties2YmlAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {

        Project project = anActionEvent.getData(CommonDataKeys.PROJECT);
        Editor editor = anActionEvent.getData(CommonDataKeys.EDITOR);
        String selectedText = TransUtil.getSelectedText(editor);
        if (selectedText == null) {
            return;
        }
        //转换
        String ymlStr = Properties2YmlUtil.properties2YmlStr(selectedText);
        int i = Messages.showDialog(project, ymlStr, "预览: 点击Copy, 粘贴到yml文件中即可", new String[]{"Copy", "Cancel"}, 0, null);
        if (i == 0) {
            //复制到剪切板
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(new StringSelection(ymlStr), null);
        }
    }

    @Override
    public void update(AnActionEvent event) {
        Project project = event.getProject();
        event.getPresentation().setEnabledAndVisible(project != null);
    }
}
