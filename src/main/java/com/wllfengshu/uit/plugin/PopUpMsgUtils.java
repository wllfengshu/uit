package com.wllfengshu.uit.plugin;

import com.intellij.notification.NotificationGroupManager;
import com.intellij.openapi.project.ProjectManager;
import com.wllfengshu.uit.app.enumerate.ResultEnum;
import org.jetbrains.annotations.NotNull;

/**
 * Pop up message
 *
 * @author liangliang.wang
 * @date 2022/1/27 16:38
 */
public class PopUpMsgUtils {

    private static final String APP_NAME = "UIT";
    private static final String UIT_NOTIFICATION_GROUP = "Uit_Notification_Group";
    private PopUpMsgUtils() {}

    /**
     * pop
     *
     * @param title
     * @param resultEnum
     */
    public static void pop(@NotNull String title, @NotNull ResultEnum resultEnum) {
        String msg = String.format(resultEnum.getMsg(), title);
        NotificationGroupManager.getInstance()
                .getNotificationGroup(UIT_NOTIFICATION_GROUP)
                .createNotification(APP_NAME, msg, resultEnum.getLeaver())
                .notify(ProjectManager.getInstance().getDefaultProject());
    }

    /**
     * pop ignore success
     *
     * @param title
     * @param resultEnum
     */
    public static void popIgnoreSuccess(@NotNull String title, @NotNull ResultEnum resultEnum) {
        if (resultEnum == ResultEnum.SUCCESS) {
            return;
        }
        pop(title, resultEnum);
    }
}
