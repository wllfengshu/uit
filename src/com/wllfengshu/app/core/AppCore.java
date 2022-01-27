package com.wllfengshu.app.core;

import com.wllfengshu.app.common.AppConstant;
import com.wllfengshu.app.enumerate.ResultEnum;
import com.wllfengshu.common.LogUtils;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * 核心逻辑
 *
 * @author liangliang.wang
 * @version 1.0
 * @date 2022/1/15 14:31
 */
public class AppCore {

    private static volatile AppCore appCore = null;
    private AppCore(){}

    public static AppCore instance() {
        if (null == appCore) {
            synchronized (AppCore.class) {
                if (null == appCore) {
                    appCore = new AppCore();
                }
            }
        }
        return appCore;
    }

    /**
     * 解决单测报错问题
     *
     * @param basePath 项目的根路径（不包含.idea这一层目录）
     * @return
     */
    public ResultEnum run(@NotNull String basePath) {
        LogUtils.write("--start:project:" + basePath);
        // 1init
        final String ideaPath = basePath + AppConstant.IDEA_PATH;
        final String workspacePath = ideaPath + AppConstant.WORKSPACE_XML;
        final File workspaceFile = new File(workspacePath);
        if (!workspaceFile.exists()) {
            LogUtils.write("run: not found file " + workspacePath);
            return ResultEnum.NOT_FOUNT_WORKSPACE;
        }
        // 2backups
        if (!this.backups(workspaceFile, ideaPath)) {
            return ResultEnum.BACKUPS_FILE_FAIL;
        }
        // 3update
        if (!this.update(workspaceFile)) {
            return ResultEnum.UPDATE_FILE_FAIL;
        }
        LogUtils.write("--end:project:" + basePath);
        return ResultEnum.SUCCESS;
    }

    /**
     * 修改文件
     *
     * @param workspaceFile
     * @return
     */
    private boolean update(File workspaceFile) {
        try {
            Path path = workspaceFile.toPath();
            String file = Files.readString(path, StandardCharsets.UTF_8);
            if (file.contains(AppConstant.DYNAMIC_CLASSPATH)) {
                // 已经有了待添加的数据
                LogUtils.write("update: already file " + path.getFileName());
                return true;
            }
            String fileNew = file.replace(AppConstant.PROPERTIES_COMPONENT_ORIGINAL, AppConstant.PROPERTIES_COMPONENT_NEW);
            Files.writeString(path, fileNew, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 备份文件
     *
     * @param workspaceFile
     * @param ideaPath
     * @return
     */
    private boolean backups(File workspaceFile, String ideaPath) {
        try {
            String backupFilePath = ideaPath + AppConstant.WORKSPACE_XML_BACK;
            File backFile = new File(backupFilePath);
            if (backFile.exists()) {
                // 待备份文件已存在
                LogUtils.write("backups: already file " + backupFilePath);
                return true;
            }
            Files.copy(workspaceFile.toPath(), backFile.toPath());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
