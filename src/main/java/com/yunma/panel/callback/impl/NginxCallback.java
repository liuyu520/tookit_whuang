package com.yunma.panel.callback.impl;

import com.common.util.SystemHWUtil;
import com.string.widget.util.ValueWidget;
import com.swing.component.AssistPopupTextArea;
import com.swing.component.AssistPopupTextField;
import com.swing.dialog.DialogUtil;
import com.yunma.panel.callback.Callback3;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by huangweii on 2016/3/13.
 */
public class NginxCallback extends Callback3 {

    @Override
    public JButton getBtn1() {
        JButton generateButton = new JButton("生成nginx脚本(G)");
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
                String targetUrl = textField1.getText2();
                String stubUrl = textField2.getText2();
                String result = ValueWidget.getNginxDispatch(targetUrl, stubUrl);
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
        return "目标接口";
    }

    @Override
    public String getLabel2() {
        return "stub ip";
    }


    @Override
    public void init(AssistPopupTextField textField1,
                     AssistPopupTextField textField2, AssistPopupTextField textField3,
                     AssistPopupTextField textField4, AssistPopupTextArea resultTextArea) {
        super.init(textField1, textField2, textField3, textField4, resultTextArea);
        this.textField1.placeHolder("例如:/api/v1/appstore/getOrderItemDisplayInfo");
        this.textField2.placeHolder("例如:www.yhskyc.com:8083");
    }

}
