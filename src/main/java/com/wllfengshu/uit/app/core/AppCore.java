package com.wllfengshu.uit.app.core;

import com.intellij.openapi.diagnostic.Logger;
import com.wllfengshu.uit.app.common.AppConstant;
import com.wllfengshu.uit.app.enumerate.ResultEnum;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * App core
 *
 * @author liangliang.wang
 * @version 1.0
 * @date 2022/1/15 14:31
 */
public class AppCore {

    private static final Logger LOG = Logger.getInstance(AppCore.class);
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
     * run
     *
     * @param basePath Is project root path, but not include ".idea"
     * @return
     */
    public ResultEnum run(@NotNull String basePath) {
        LOG.info("--start:project:" + basePath);
        // 1init
        final String ideaPath = basePath + AppConstant.IDEA_PATH;
        final String workspacePath = ideaPath + AppConstant.WORKSPACE_XML;
        final File workspaceFile = new File(workspacePath);
        if (!workspaceFile.exists()) {
            LOG.warn("run: not found file " + workspacePath);
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
        LOG.info("--end:project:" + basePath);
        return ResultEnum.SUCCESS;
    }

    /**
     * update file
     *
     * @param workspaceFile
     * @return
     */
    private boolean update(File workspaceFile) {
        try {
            Path path = workspaceFile.toPath();
            String file = Files.readString(path, StandardCharsets.UTF_8);
            if (file.contains(AppConstant.DYNAMIC_CLASSPATH)) {
                LOG.info("update: already file " + path.getFileName());
                return true;
            }
            String fileNew = file.replace(AppConstant.PROPERTIES_COMPONENT_ORIGINAL, AppConstant.PROPERTIES_COMPONENT_NEW);
            Files.writeString(path, fileNew, StandardCharsets.UTF_8);
        } catch (Exception e) {
            LOG.error(e);
            return false;
        }
        return true;
    }

    /**
     * backups file
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
                LOG.info("backups: already file " + backupFilePath);
                return true;
            }
            Files.copy(workspaceFile.toPath(), backFile.toPath());
        } catch (Exception e) {
            LOG.error(e);
            return false;
        }
        return true;
    }
}
