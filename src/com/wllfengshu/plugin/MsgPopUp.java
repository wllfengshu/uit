package com.wllfengshu.plugin;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.Notifications;
import com.wllfengshu.app.enumerate.ResultEnum;
import com.wllfengshu.common.Constant;
import org.jetbrains.annotations.NotNull;

/**
 * 消息弹框
 *
 * @author liangliang.wang
 * @date 2022/1/27 16:38
 */
public class MsgPopUp {

    /**
     * 提示消息
     */
    private static final NotificationGroup NOTIFICATION_GROUP = new NotificationGroup(Constant.APP_ID, NotificationDisplayType.BALLOON, true);

    /**
     * 处理操作结果
     *
     * @param project
     * @param resultEnum
     */
    public void pop(@NotNull String project, @NotNull ResultEnum resultEnum) {
        Notification notification = NOTIFICATION_GROUP.createNotification(resultEnum.getMsg(), null, project, resultEnum.getLeaver());
        Notifications.Bus.notify(notification);
    }
}
