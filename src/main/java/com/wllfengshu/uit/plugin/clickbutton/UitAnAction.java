package com.wllfengshu.uit.plugin.clickbutton;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.diagnostic.Logger;
import com.wllfengshu.uit.app.core.AppCore;
import com.wllfengshu.uit.plugin.ExtractProjectPathUtils;
import com.wllfengshu.uit.plugin.PopUpMsgUtils;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Click button (Show in "idea->Tools")
 *
 * @author liangliang.wang
 * @date 2022/1/16 1:38
 */
public class UitAnAction extends AnAction {

    private static final Logger LOG = Logger.getInstance(UitAnAction.class);

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        LOG.info("----UitAnActionStart");
        if (null == event.getProject()) {
            return;
        }
        List<String> pendingList = ExtractProjectPathUtils.give(event.getProject());
        pendingList.forEach(e -> PopUpMsgUtils.pop(e, AppCore.instance().run(e)));
        LOG.info("----UitAnActionEnd");
    }
}
