package com.wllfengshu.core;

import com.wllfengshu.common.Constant;

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

    /**
     * 修改指定目录的文件
     *
     * @param filePath
     * @return
     */
    public String updateAppointFile(String filePath) {
        // 1check
        if (!this.check(filePath)) {
            return Constant.FILE_NAME_ERROR;
        }
        File workspace = new File(filePath);
        // 2备份
        if (!this.backups(workspace, filePath)) {
            return Constant.BACKUPS_FILE_FAIL;
        }
        // 3update
        if (!this.update(workspace)) {
            return Constant.RESULT_FAIL;
        }
        return Constant.RESULT_SUCCESS;
    }

    /**
     * 修改当前目录的文件
     *
     * @return
     */
    public String updateNowFile() {
        // 1查找当前目录是否有workspace.xml文件
        File workspace = this.giveWorkspace();
       if (null == workspace) {
           return Constant.NOT_FOUND_FILE;
       }
       // 2updateAppointFile
        return this.updateAppointFile(workspace.getAbsolutePath());
    }

    /**
     * 查找workspace.xml文件
     *
     * @return
     */
    private File giveWorkspace() {
        File nowPath = new File("./");
        File[] nowFiles = nowPath.listFiles();
        assert nowFiles != null;
        for (File nowFile : nowFiles) {
            if (nowFile.getName().endsWith(Constant.WORKSPACE_XML)) {
                return nowFile;
            }
        }
        return null;
    }

    /**
     * 参数校验
     *
     * @param filePath
     * @return
     */
    private boolean check(String filePath) {
        if (!filePath.endsWith(Constant.WORKSPACE_XML)) {
            return false;
        }
        return true;
    }

    /**
     * 修改文件
     *
     * @param workspace
     * @return
     */
    private boolean update(File workspace) {
        try {
            Path path = workspace.toPath();
            String file = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
            if (file.contains(Constant.DYNAMIC_CLASSPATH)) {
                // 已经有了待添加的数据
                return true;
            }
            String fileNew = file.replace(Constant.PROPERTIES_COMPONENT_ORIGINAL, Constant.PROPERTIES_COMPONENT_NEW);
            Files.write(path, fileNew.getBytes(StandardCharsets.UTF_8));
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 备份文件
     *
     * @param workspace
     * @param filePath
     * @return
     */
    private boolean backups(File workspace, String filePath) {
        try {
            String backupFilePath = filePath.replace(Constant.WORKSPACE_XML, Constant.WORKSPACE_XML_BACK);
            File backFile = new File(backupFilePath);
            if (backFile.exists()) {
                // 待备份文件已存在
                return true;
            }
            Files.copy(workspace.toPath(), backFile.toPath());
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
