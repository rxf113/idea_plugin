import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

public class TextBoxes extends AnAction {

    public void actionPerformed(AnActionEvent event) {
        Project project = event.getData(PlatformDataKeys.PROJECT);
//        Messages.showInputDialog(
//                project,
//                "What is your name?",
//                "Input your name",
//                Messages.getInformationIcon(),
//                "初始的 的", null, null);
//
//        Messages.showInfoMessage(project,"this is message","234");
        //    public static int showDialog(String message, @Nls(capitalization = Capitalization.Title) String title, @Nls @NotNull String[] options, int defaultOptionIndex, @Nullable Icon icon) {
        // Messages.showIdeaMessageDialog(project, "message", "title", new String[]{"copy", "cancel"}, 0, Messages.getInformationIcon(), null);
        //Messages.showEditableChooseDialog("message", "title", Messages.getInformationIcon(), new String[]{"copy", "cancel"}, "初始化的", null);
        int i = Messages.showDialog("2022-01-06 18:15:23,408 [   2052]   WARN - ution.rmi.RemoteProcessSupport - Picked up JAVA_TOOL_OPTIONS: -Dfile.encoding=UTF-8 \n" +
                "2022-01-06 18:15:26,029 [   4673]   WARN - com.intellij.util.xmlb.Binding - no accessors for class org.jetbrains.kotlin.idea.highlighter.KotlinDefaultHighlightingSettingsProvider \n" +
                "2022-01-06 18:15:27,201 [   5845]   WARN - openapi.wm.impl.ToolWindowImpl - ToolWindow icons should be 13x13. Please fix ToolWindow", "title", new String[]{"copy", "cancel"}, 0, null);
        if (i == 0) {
            System.out.println("点击了确定");
        }

    }
}