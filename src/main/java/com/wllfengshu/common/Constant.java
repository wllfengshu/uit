package com.wllfengshu.common;

/**
 * 常量
 *
 * @author liangliang.wang
 * @version 1.0
 * @date 2022/1/15 15:18
 */
public class Constant {

    /**
     * ui界面大小
     */
    public static final int UI_WIDTH = 600;
    public static final int UI_HEIGHT = 200;

    /**
     * xml
     */
    public static final String WORKSPACE_XML = "workspace.xml";
    public static final String WORKSPACE_XML_BACK = "workspace_back.xml";

    /**
     * 描述信息
     */
    public static final String TITLE = "UIT";
    public static final String CHOICE_BUT_TITLE = "选择文件";
    public static final String SUBMIT_BUT_TITLE = "提交文件";
    public static final String TRANSFORM_BUT_TITLE = "转换文件";

    /**
     * 选择文件按钮最大大小
     */
    public static final int CHOICE_BUT_MAX_WIDTH = 100;
    public static final int CHOICE_BUT_MAX_HEIGHT = 50;

    /**
     * 提示信息
     */
    public static final String TIPS_NO_CHOICE = "未选择文件！";
    public static final String TIPS_NO_FILE = "未获取到文件！";

    /**
     * 操作结果
     */
    public static final String FILE_NAME_ERROR = "文件名必须是" + WORKSPACE_XML;
    public static final String BACKUPS_FILE_FAIL = "备份文件失败";
    public static final String RESULT_SUCCESS = "成功";
    public static final String RESULT_FAIL = "失败";
    public static final String NOT_FOUND_FILE= "当前目录没有找到" + WORKSPACE_XML;

    /**
     * 主要替换的代码
     */
    public static final String PROPERTIES_COMPONENT_ORIGINAL = "<component name=\"PropertiesComponent\">";
    public static final String DYNAMIC_CLASSPATH = "<property name=\"dynamic.classpath\" value=\"true\" />";
    public static final String PROPERTIES_COMPONENT_NEW = PROPERTIES_COMPONENT_ORIGINAL + "\n    " + DYNAMIC_CLASSPATH;
}
