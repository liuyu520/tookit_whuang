package com.yunma.callback;

import com.io.hw.awt.color.CustomColor;
import com.string.widget.util.ValueWidget;
import com.swing.dialog.UnicodePanel;
import com.swing.dialog.callback.Callback2;

import javax.swing.*;
import java.awt.*;

/***
 * Unicode 编码
 *
 * @author huangweii
 *         2015年9月30日
 */
public class EscapeHTMLCallback extends Callback2 {
    private Color bgColor;
    private UnicodePanel unicodePanel;

    public EscapeHTMLCallback() {
        super();
        bgColor = CustomColor.getMoreLightColor();
    }

    @Override
    public String callback(String input, Object encoding) {
        String escapeHTMLResult;
        try {
            escapeHTMLResult = ValueWidget.escapeHTML(input);
            return escapeHTMLResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getButtonLabel() {
        return "escape HTML";
    }

    @Override
    public Color getBackGroundColor() {
        return bgColor;
    }

    @Override
    public String getHelpInfo() {
        return "< 转为&lt; ,> 转为&gt;";
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
