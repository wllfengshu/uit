package com.wllfengshu;

import com.wllfengshu.core.UitCore;

/**
 * 不需要ui，点击直接修改当前目录下的文件
 *
 * @author wangll
 * @date 2022-01-15 0:37
 */
public class Runner2 {

    private static final UitCore CORE = new UitCore();

    public static void main(String[] args) {
        CORE.updateNowFile();
    }
}
