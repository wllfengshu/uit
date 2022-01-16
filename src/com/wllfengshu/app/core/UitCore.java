package com.wllfengshu.app.core;

import com.wllfengshu.app.common.Constant;
import com.wllfengshu.app.enumerate.ResultEnum;
import com.wllfengshu.app.util.LogUtils;
import com.wllfengshu.app.util.StringUtils;

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
public class UitCore {

    private static volatile UitCore uitCore = null;
    private UitCore(){}

    public static UitCore instance() {
        if (null == uitCore) {
            synchronized (UitCore.class) {
                if (null == uitCore) {
                    uitCore = new UitCore();
                }
            }
        }
        return uitCore;
    }

    /**
     * 解决单测报错问题
     *
     * @param basePath 项目的根路径
     * @return
     */
    public ResultEnum core(String basePath) {
        LogUtils.info("pending project:" + basePath);
        // 1check
        if (StringUtils.isEmpty(basePath)) {
            return ResultEnum.NOT_FOUNT_WORKSPACE;
        }
        final String ideaPath = basePath + Constant.IDEA_PATH;
        final String workspacePath = ideaPath + Constant.WORKSPACE_XML;
        final File workspaceFile = new File(workspacePath);
        // 2backups
        if (!this.backups(workspaceFile, ideaPath)) {
            return ResultEnum.BACKUPS_FILE_FAIL;
        }
        // 3update
        if (!this.update(workspaceFile)) {
            return ResultEnum.UPDATE_FILE_FAIL;
        }
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
            if (file.contains(Constant.DYNAMIC_CLASSPATH)) {
                // 已经有了待添加的数据
                LogUtils.info("update: already file " + file);
                return true;
            }
            String fileNew = file.replace(Constant.PROPERTIES_COMPONENT_ORIGINAL, Constant.PROPERTIES_COMPONENT_NEW);
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
            String backupFilePath = ideaPath + Constant.WORKSPACE_XML_BACK;
            File backFile = new File(backupFilePath);
            if (backFile.exists()) {
                // 待备份文件已存在
                LogUtils.info("backups: already file " + backupFilePath);
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
