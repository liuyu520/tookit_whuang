package com.yunma.panel.callback.impl;

import com.swing.component.AssistPopupTextArea;
import com.swing.component.AssistPopupTextField;
import com.swing.dialog.DialogUtil;
import com.yunma.panel.callback.Callback3;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by huangweii on 2016/3/12.
 */
public class ScpCallback extends Callback3 {
    /***
     * 从远程拷贝到本地
     */
    public static final String commond_scp2loc = "scp -r  root@%s:%s %s -P22";
    /***
     * 从本地拷贝到远程
     */
    public static final String commond_scp2remote = "scp -r  %s root@%s:%s -P22";

    @Override
    public JButton getBtn1() {
        JButton generateButton = new JButton("从本地复制到远程");
        addEvent(generateButton, true);
        return generateButton;
    }

    private void addEvent(JButton generateButton, final boolean is2Remote) {
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!DialogUtil.verifyTFEmpty(textField1, getLabel1())) {
                    return;
                }
                if (!DialogUtil.verifyTFEmpty(textField2, getLabel2())) {
                    return;
                }
                String remoteIp = textField1.getText2();
                String remotePath = textField2.getText2();
                String localPath = textField3.getText2();
                String result = null;
                if (is2Remote) {
                    result = String.format(commond_scp2remote, localPath, remoteIp, remotePath);
                } else {
                    result = String.format(commond_scp2loc, remoteIp, remotePath, localPath);
                }
                resultTextArea.setText(result);
            }
        });
    }

    @Override
    public JButton getBtn2() {
        JButton generateButton = new JButton("从远程复制到本地");
        addEvent(generateButton, false);
        return generateButton;
    }


    @Override
    public String getLabel1() {
        return "远程ip";
    }

    @Override
    public String getLabel3() {
        return "本地路径";
    }

    @Override
    public String getLabel2() {
        return "远程路径";
    }

    @Override
    public String getLabel4() {
        return "本地ip";
    }

    @Override
    public boolean showTf3() {
        return true;
    }

    @Override
    public boolean showTf4() {
        return false;
    }

    @Override
    public void init(AssistPopupTextField textField1,
                     AssistPopupTextField textField2, AssistPopupTextField textField3,
                     AssistPopupTextField textField4, AssistPopupTextArea resultTextArea) {
        super.init(textField1, textField2, textField3, textField4, resultTextArea);
        this.textField1.placeHolder("远程linux的ip").setText("123.57.250.51");
        this.textField2.placeHolder("远程文件的路径").setText("/home/whuang/software/apache-tomcat-7.0.53.tar.gz");
        this.textField3.placeHolder("本地文件的路径").setText("/home/whuang/software/tomcat");
    }

}
