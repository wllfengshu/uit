package com.wllfengshu.uit.plugin;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import org.apache.commons.compress.utils.Lists;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Extract project path
 *
 * @author wangll
 * @date 2022-01-28 22:14
 */
public class ExtractProjectPathUtils {

    private ExtractProjectPathUtils() {}

    /**
     * give for project
     *
     * @param project
     * @return
     */
    public static List<String> give(@NotNull Project project) {
        List<String> result = Lists.newArrayList();
        // 1check
        if (project.isDefault()) {
            return result;
        }
        // 2add project
        result.add(project.getBasePath());
        // 3add module
        Module[] modules = ModuleManager.getInstance(project).getModules();
        for (Module module : modules) {
            result.addAll(give(module));
        }
        return result;
    }

    /**
     * give for module
     *
     * @param module
     * @return
     */
    public static List<String> give(@NotNull Module module) {
        return List.of(module.getModuleNioFile().getParent().toString());
    }
}
