package com.wllfengshu.app.enumerate;

import com.intellij.notification.NotificationType;

/**
 * 操作结果枚举
 *
 * @author liangliang.wang
 * @date 2022/1/15 15:18
 */
public enum ResultEnum {

    SUCCESS(NotificationType.INFORMATION, "Success"),

    NOT_FOUNT_WORKSPACE(NotificationType.ERROR, "Not fount ./idea/workspace.xml"),

    BACKUPS_FILE_FAIL(NotificationType.ERROR, "Backups ./idea/workspace.xml fail"),

    UPDATE_FILE_FAIL(NotificationType.ERROR, "Update ./idea/workspace.xml fail");

    /**
     * 级别
     */
    private NotificationType leaver;
    /**
     * 提示信息
     */
    private String msg;

    public NotificationType getLeaver() {
        return leaver;
    }

    public String getMsg() {
        return msg;
    }

    ResultEnum(NotificationType leaver, String msg) {
        this.leaver = leaver;
        this.msg = msg;
    }
}
