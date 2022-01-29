package com.wllfengshu.uit.app.common;

/**
 * App constant
 *
 * @author liangliang.wang
 * @date 2022/1/15 15:18
 */
public class AppConstant {

    /**
     * path
     */
    public static final String IDEA_PATH = "\\.idea\\";

    /**
     * xml
     */
    public static final String WORKSPACE_XML = "workspace.xml";
    public static final String WORKSPACE_XML_BACK = "workspace_back.xml";

    /**
     * core code
     */
    public static final String PROPERTIES_COMPONENT_ORIGINAL = "<component name=\"PropertiesComponent\">";
    public static final String DYNAMIC_CLASSPATH = "<property name=\"dynamic.classpath\" value=\"true\" />";
    public static final String PROPERTIES_COMPONENT_NEW = PROPERTIES_COMPONENT_ORIGINAL + "\n    " + DYNAMIC_CLASSPATH;
}
