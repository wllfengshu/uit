package com.wllfengshu.common;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Date;

/**
 * 日志·工具类
 *
 * @author wangll
 * @date 2022-01-16 12:43
 */
public class LogUtils {

    private static final String LOG_PATH = "./" + Constant.APP_NAME + "_log.txt";

    /**
     * 记录日志
     *
     * @param info
     */
    public static void write(String info) {
        try {
            File logFile = new File(LOG_PATH);
            if (!logFile.exists()) {
                logFile.createNewFile();
            }
            FastDateFormat isoDatetimeFormat = DateFormatUtils.ISO_DATETIME_FORMAT;
            String log = isoDatetimeFormat.format(new Date()) + StringUtils.SPACE + info + StringUtils.LF;
            Files.write(logFile.toPath(), log.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
