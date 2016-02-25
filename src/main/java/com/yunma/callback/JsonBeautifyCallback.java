package com.yunma.callback;

import com.io.hw.awt.color.CustomColor;
import com.io.hw.json.JsonTool;
import com.yunma.callback.impl.Callback2;
import com.yunma.panel.UnicodePanel;

import javax.swing.*;
import java.awt.*;

/***
 * Unicode 编码
 *
 * @author huangweii
 *         2015年9月30日
 */
public class JsonBeautifyCallback extends Callback2 {
    private Color bgColor;
    private UnicodePanel unicodePanel;

    public JsonBeautifyCallback() {
        super();
        bgColor = CustomColor.getMoreLightColor();
    }

    @Override
    public String callback(String input, Object encoding) {
        String result = JsonTool.formatJson(input, "      ");
        return result;
    }

    @Override
    public String getButtonLabel() {
        return "格式化json";
    }

    @Override
    public Color getBackGroundColor() {
        return bgColor;
    }

    @Override
    public JComboBox<String> getJComboBox() {
        return null;
    }

    @Override
    public UnicodePanel getUnicodePanel() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setUnicodePanel(UnicodePanel unicodePanel) {
        // TODO Auto-generated method stub

    }

}
