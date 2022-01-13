package com.wllfengshu.ui;

import com.wllfengshu.common.Constant;
import com.wllfengshu.core.UitCore;
import com.wllfengshu.util.StringUtils;

import javax.swing.*;
import java.awt.*;

/**
 * 页面
 *
 * @author liangliang.wang
 * @version 1.0
 * @date 2022/1/15 14:50
 */
public class UitUi extends JFrame {

    private String filePath;
    private static final JLabel TIPS = new JLabel();
    private static final UitCore CORE = new UitCore();

    public UitUi() {
        this.setTitle(Constant.TITLE);
        this.setLayout(new FlowLayout());
        this.setResizable(false);
        this.setVisible(true);
        this.setSize(Constant.UI_WIDTH, Constant.UI_HEIGHT);
        this.setLocationRelativeTo(null);

        Box box = Box.createVerticalBox();
        box.add(new JLabel("状态："));
        box.add(TIPS);
        box.add(new JLabel("------------------------------------------------------------"));
        box.add(new JLabel("方法一：选择 项目/.idea/workspace.xml文件，然后点击下方“提交文件”按钮"));
        box.add(this.giveChoiceBut());
        box.add(this.giveSubmitBut());
        box.add(new JLabel("方法二：把此程序放到 项目/.idea/目录下打开，然后点击下方“转换文件”按钮"));
        box.add(this.giveTransformBut());
        this.add(box);
    }

    /**
     * 转换文件的按钮
     *
     * @return
     */
    private JButton giveTransformBut() {
        final JButton but = new JButton(Constant.TRANSFORM_BUT_TITLE);
        but.addActionListener(e -> {
            TIPS.setText(CORE.updateNowFile());
        });
        return but;
    }

    /**
     * 选择文件的按钮
     *
     * @return
     */
    private JButton giveChoiceBut() {
        final JButton but = new JButton(Constant.CHOICE_BUT_TITLE);
        but.setMaximumSize(new Dimension(Constant.CHOICE_BUT_MAX_WIDTH, Constant.CHOICE_BUT_MAX_HEIGHT));
        but.addActionListener(e -> {
            JFileChooser jc = new JFileChooser();
            jc.showSaveDialog(null);
            if (null != jc.getSelectedFile()) {
                filePath = jc.getSelectedFile().getAbsolutePath();
                but.setText(jc.getSelectedFile().getName());
            }else {
                TIPS.setText(Constant.TIPS_NO_CHOICE);
            }
        });
        return but;
    }

    /**
     * 提交文件的按钮
     *
     * @return
     */
    private JButton giveSubmitBut() {
        final JButton but = new JButton(Constant.SUBMIT_BUT_TITLE);
        but.addActionListener(e -> {
            if (StringUtils.isNotEmpty(filePath)) {
                TIPS.setText(CORE.updateAppointFile(filePath));
            }else {
                TIPS.setText(Constant.TIPS_NO_FILE);
            }
        });
        return but;
    }
}
