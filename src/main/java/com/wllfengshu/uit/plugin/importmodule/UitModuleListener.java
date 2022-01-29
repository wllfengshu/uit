package com.wllfengshu.uit.plugin.importmodule;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.ModuleListener;
import com.intellij.openapi.project.Project;
import com.wllfengshu.uit.app.core.AppCore;
import com.wllfengshu.uit.plugin.ExtractProjectPathUtils;
import com.wllfengshu.uit.plugin.PopUpMsgUtils;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * When import module run
 *
 * @author wangll
 * @date 2022-01-28 18:08
 */
public class UitModuleListener implements ModuleListener {

    private static final Logger LOG = Logger.getInstance(UitModuleListener.class);

    @Override
    public void moduleAdded(@NotNull Project project, @NotNull Module module) {
        LOG.info("----UitModuleListenerStart");
        List<String> pendingList = ExtractProjectPathUtils.give(module);
        pendingList.forEach(e -> PopUpMsgUtils.popIgnoreSuccess(e, AppCore.instance().run(e)));
        LOG.info("----UitModuleListenerEnd");
    }
}
