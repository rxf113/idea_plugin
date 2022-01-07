package com.rxf113;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.InputValidator;
import com.intellij.openapi.ui.Messages;
import com.rxf113.convert.MysqlSql2EntityConverter;
import com.rxf113.convert.processor.*;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

/**
 * @author 11313
 */
public class MySql2FieldsAction extends AnAction {

    static MysqlSql2EntityConverter mysqlSql2EntityConverter = new MysqlSql2EntityConverter(
            new BaseFieldModelFormatProcessor(),
            new BaseModifierProcessor(),
            new BaseVariableTypeProcess(),
            new BaseFieldNameProcessor(),
            new BaseCommentProcessor(),
            new CsvFormatCheckProcessor());

    @Override
    public void actionPerformed(AnActionEvent event) {

        Project project = event.getData(CommonDataKeys.PROJECT);


        Editor editor = event.getData(CommonDataKeys.EDITOR);
        if (editor != null) {
            //打开input框
            String initStr = "name varchar(64) not null comment '名称',\n" +
                    "start_time datetime(3) not null comment '开始时间',\n" +
                    "type tinyint not null comment '类型'";

            String sqlStr = Messages.showMultilineInputDialog(project, "需满足如下 mysql ddl 标准数据列格式", "输入sql字段", initStr, null, new InputValidator() {
                @Override
                public boolean checkInput(String inputStr) {
                    return mysqlSql2EntityConverter.getFormatCheckProcessor().check(inputStr);
                }

                @Override
                public boolean canClose(String s) {
                    return true;
                }
            });

            if (StringUtils.isNotEmpty(sqlStr)) {
                //生成字段
                String fields = mysqlSql2EntityConverter.convert(sqlStr);
                int i = Messages.showDialog(project, fields, "Preview", new String[]{"Copy", "Cancel"}, 0, null);
                if (i == 0) {
                    //复制到剪切板
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clipboard.setContents(new StringSelection(fields), null);
                }
            }
        }
    }

    @Override
    public void update(AnActionEvent event) {
        // Set the availability based on whether a project is open
        Project project = event.getProject();
        event.getPresentation().setEnabledAndVisible(project != null);
    }
}
