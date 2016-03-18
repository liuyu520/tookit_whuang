package com.yunma.panel.callback.impl;

import com.common.util.SystemHWUtil;
import com.string.widget.util.ValueWidget;
import com.swing.component.AssistPopupTextArea;
import com.swing.component.AssistPopupTextField;
import com.swing.dialog.DialogUtil;
import com.yunma.callback.LinuxShellCallback;
import com.yunma.panel.callback.Callback3;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by huangweii on 2016/3/13.
 */
public class FindCallback extends Callback3 {
    public static final String find_file = "find  %s  -maxdepth 2 -type f -iname \"*%s*\"|grep \"%s\" --color=auto";
    public static final String find_file_more = find_file + "|xargs -i cat {}";
    public static final String search_str = "find ./ -maxdepth 3 -type f -iname \"*%s*\"|xargs grep -i -n -r \"%s\" --color=auto";

    public static String find(String filename, String path) {
        if (ValueWidget.isNullOrEmpty(path)) {
            path = "./";
        }
        StringBuffer buffer = new StringBuffer();
        String result = String.format(find_file, path, filename, filename);
        buffer.append("搜索文件:\t" + LinuxShellCallback.divide_tab + result).append(SystemHWUtil.CRLF);
        buffer.append("打印搜索到的文件:" + LinuxShellCallback.divide_tab + String.format(find_file_more, path, filename, filename)).append(SystemHWUtil.CRLF);
        buffer.append("搜索文件:\t" + LinuxShellCallback.divide_tab + String.format(search_str, filename, filename));
        return buffer.toString();
    }

    @Override
    public JButton getBtn1() {
        JButton generateButton = new JButton("生成搜索命令(G)");
        generateButton.setMnemonic('G');
        addEvent(generateButton, true);
        return generateButton;
    }

    private void addEvent(JButton generateButton, final boolean is2Remote) {
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultTextArea.setText(SystemHWUtil.EMPTY);
                if (!DialogUtil.verifyTFEmpty(textField1, getLabel1())) {
                    return;
                }
                if (!DialogUtil.verifyTFEmpty(textField2, getLabel2())) {
                    return;
                }
                String filename = textField1.getText2();
                String path = textField2.getText2();
                String result = find(filename, path);
                resultTextArea.setText(result);
            }
        });
    }

    @Override
    public JButton getBtn2() {
        return null;
    }


    @Override
    public String getLabel1() {
        return "搜索的文件名";
    }

    @Override
    public String getLabel2() {
        return "目录";
    }


    @Override
    public void init(AssistPopupTextField textField1,
                     AssistPopupTextField textField2, AssistPopupTextField textField3,
                     AssistPopupTextField textField4, AssistPopupTextArea resultTextArea) {
        super.init(textField1, textField2, textField3, textField4, resultTextArea);
        this.textField1.placeHolder("例如:catalina.out").setText("catalina.out");
        ;
        this.textField2.placeHolder("例如:/home/whuang/software/").setText("/home/whuang/software/tomcat-7.0.53_tv/");
        ;
    }

}
