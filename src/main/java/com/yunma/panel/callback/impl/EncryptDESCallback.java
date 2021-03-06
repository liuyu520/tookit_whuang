package com.yunma.panel.callback.impl;

import com.common.util.SystemHWUtil;
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
public class EncryptDESCallback extends Callback3 {
    @Override
    public JButton getBtn1() {
        JButton generateButton = new JButton("加密");
        generateButton.setMnemonic('E');
        addEvent(generateButton);
        return generateButton;
    }

    /***
     * 加密
     * @param generateButton
     */
    private void addEvent(JButton generateButton) {
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!DialogUtil.verifyTFEmpty(textField1, getLabel1())) {
                    return;
                }
                if (!DialogUtil.verifyTFEmpty(textField2, getLabel2())) {
                    return;
                }
                String key = textField1.getText2();
                String plainText = textField2.getText2();
                try {
                    String encrypted = SystemHWUtil.encryptDES(plainText, key);
                    resultTextArea.setText(encrypted);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    @Override
    public JButton getBtn2() {
        JButton generateButton = new JButton("解密");
        generateButton.setMnemonic('D');
        decrypt(generateButton);
        return generateButton;
    }

    private void decrypt(JButton generateButton) {
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!DialogUtil.verifyTFEmpty(textField1, getLabel1())) {
                    return;
                }
                if (!DialogUtil.verifyTFEmpty(textField3, getLabel3())) {
                    return;
                }
                String key = textField1.getText2();
                String encrypted = textField3.getText2();
                try {
                    String plainText = SystemHWUtil.decryptDES(encrypted, key);
                    resultTextArea.setText(plainText);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }


    @Override
    public String getLabel1() {
        return "key";
    }

    @Override
    public String getLabel2() {
        return "明文";
    }

    @Override
    public String getLabel3() {
        return "密文";
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
        this.textField1.placeHolder("8个字符");//key
        this.textField2.placeHolder("请输入明文");
        this.textField3.placeHolder("请输入密文");
        this.textField2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getBtn1().doClick();
            }
        });
        this.textField3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getBtn2().doClick();
            }
        });
    }

}
