package com.wllfengshu.plugin;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.wllfengshu.app.core.UitCore;
import com.wllfengshu.app.enumerate.ResultEnum;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 展示在idea->Tools菜单栏上的按钮
 *
 * @author liangliang.wang
 * @date 2022/1/16 1:38
 */
public class UitAnAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        List<String> pendingList = this.giveProjectPath(event);
        pendingList.forEach(e -> this.dealResult(e, UitCore.instance().core(e)));
    }

    /**
     * 处理操作结果
     *
     * @param project
     * @param resultEnum
     */
    private void dealResult(@NotNull String project, @NotNull ResultEnum resultEnum) {
        NotificationGroup notificationGroup = new NotificationGroup("uitId", NotificationDisplayType.BALLOON, true);
        Notification notification = notificationGroup.createNotification(resultEnum.getMsg(), null, project, resultEnum.getLeaver());
        Notifications.Bus.notify(notification);
    }

    /**
     * 获取当前项目的路径
     *
     * @param event
     * @return
     */
    private List<String> giveProjectPath(@NotNull AnActionEvent event) {
        List<String> result = new ArrayList<>();
        if (null == event.getProject()) {
            return result;
        }
        result.add(event.getProject().getBasePath());
        return result;
    }
}
