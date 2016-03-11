package com.yunma.callback;

import com.io.hw.awt.color.CustomColor;
import com.string.widget.util.RegexUtil;
import com.swing.dialog.UnicodePanel;
import com.swing.dialog.callback.Callback2;

import javax.swing.*;
import java.awt.*;

/***
 * 拼接代码
 *
 * @author huangweii
 *         2015年10月25日
 */
public class Code2StringCallback extends Callback2 {
    private Color bgColor;
    private UnicodePanel unicodePanel;

    public Code2StringCallback() {
        super();
        bgColor = CustomColor.getMoreLightColor();
    }

    @Override
    public String callback(String input, Object crfl) {
        return RegexUtil.splitPlus(input, (String) crfl, "\"", false);
    }

    @Override
    public String getButtonLabel() {
        return "拼接";
    }

    @Override
    public Color getBackGroundColor() {
        return bgColor;
    }

    @Override
    public JComboBox<String> getJComboBox() {
        JComboBox<String> CRFLComboBox = new JComboBox<String>();
        CRFLComboBox.addItem("\\r\\n");
        CRFLComboBox.addItem("\\n");
        CRFLComboBox.addItem("\\r");
        CRFLComboBox.addItem("\\n\\r");
        CRFLComboBox.setSelectedIndex(1);
        return CRFLComboBox;
    }

    @Override
    public UnicodePanel getUnicodePanel() {
        return null;
    }

    @Override
    public void setUnicodePanel(UnicodePanel unicodePanel) {
    }

    @Override
    public String callbackAdditional(String input, Object crfl) {
        return RegexUtil.splitPlus(input, (String) crfl, "\"", true);
    }

    @Override
    public JButton getAdditionalBtn() {
        return new JButton("保持空格");
    }

}
