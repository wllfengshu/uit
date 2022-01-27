package com.wllfengshu.plugin.button;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.wllfengshu.app.core.AppCore;
import com.wllfengshu.common.LogUtils;
import com.wllfengshu.plugin.MsgPopUp;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 展示在idea->Tools菜单栏上的按钮
 *
 * @author liangliang.wang
 * @date 2022/1/16 1:38
 */
public class UitAnAction extends AnAction {

    private AppCore appCore = AppCore.instance();
    private MsgPopUp msgPopUp = new MsgPopUp();

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        LogUtils.write("----start");
        // 1check
        if (null == event.getProject()) {
            return;
        }
        // 2givePath
        List<String> pendingList = this.giveProjectPath(event.getProject());
        // 3run
        pendingList.forEach(e -> msgPopUp.pop(e, appCore.run(e)));
        LogUtils.write("----end");
    }

    /**
     * 获取当前项目的路径
     *
     * @param project
     * @return
     */
    private List<String> giveProjectPath(@NotNull Project project) {
        List<String> result = new ArrayList<>();
        // 1check
        if (project.isDefault()) {
            return result;
        }
        // 2add project
        result.add(project.getBasePath());
        // 3add module
        Module[] modules = ModuleManager.getInstance(project).getModules();
        for (Module module : modules) {
            //TODO 这里会有一次冗余的
            result.add(new File(module.getModuleFilePath()).getParent());
        }
        return result;
    }
}
