package com.wllfengshu.uit.app.enumerate;

import com.intellij.notification.NotificationType;

/**
 * Result enum
 *
 * @author liangliang.wang
 * @date 2022/1/15 15:18
 */
public enum ResultEnum {

    SUCCESS(NotificationType.INFORMATION, "Success"),

    NOT_FOUNT_WORKSPACE(NotificationType.WARNING, "Not fount: %s/idea/workspace.xml"),

    BACKUPS_FILE_FAIL(NotificationType.ERROR, "Backups fail: %s/idea/workspace.xml"),

    UPDATE_FILE_FAIL(NotificationType.ERROR, "Update fail: %s/idea/workspace.xml");

    /**
     * leave
     */
    private final NotificationType leaver;
    /**
     * massage
     */
    private final String msg;

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
