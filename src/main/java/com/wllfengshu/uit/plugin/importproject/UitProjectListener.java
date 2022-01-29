package com.wllfengshu.uit.plugin.importproject;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManagerListener;
import com.wllfengshu.uit.app.core.AppCore;
import com.wllfengshu.uit.plugin.ExtractProjectPathUtils;
import com.wllfengshu.uit.plugin.PopUpMsgUtils;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * When import project run
 *
 * @author wangll
 * @date 2022-01-28 18:08
 */
public class UitProjectListener implements ProjectManagerListener {

    private static final Logger LOG = Logger.getInstance(UitProjectListener.class);

    @Override
    public void projectOpened(@NotNull Project project) {
        LOG.info("----UitProjectListenerStart");
        List<String> pendingList = ExtractProjectPathUtils.give(project);
        pendingList.forEach(e -> PopUpMsgUtils.popIgnoreSuccess(e, AppCore.instance().run(e)));
        LOG.info("----UitProjectListenerEnd");
    }
}
