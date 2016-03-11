package com.yunma.callback;

import com.io.hw.awt.color.CustomColor;
import com.string.widget.util.ValueWidget;
import com.swing.dialog.UnicodePanel;
import com.swing.dialog.callback.Callback2;

import javax.swing.*;
import java.awt.*;

/***
 * UnescapeHTML
 *
 * @author huangweii
 *         2015年9月30日
 */
public class UnescapeHTMLCallback extends Callback2 {
    private Color bgColor;
    private UnicodePanel unicodePanel;

    public UnescapeHTMLCallback() {
        super();
        bgColor = CustomColor.getMoreLightColor();
    }

    @Override
    public String callback(String input, Object encoding) {
        String escapeHTMLResult;
        try {
            escapeHTMLResult = ValueWidget.unescapeHTML(input);
            return escapeHTMLResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getButtonLabel() {
        return "unescape HTML";
    }

    @Override
    public Color getBackGroundColor() {
        return bgColor;
    }

    @Override
    public String getHelpInfo() {
        return "&lt; 转为 < , &gt; 转为> ";
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
