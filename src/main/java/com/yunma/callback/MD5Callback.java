package com.yunma.callback;

import com.common.util.SystemHWUtil;
import com.io.hw.awt.color.CustomColor;
import com.string.widget.util.ValueWidget;
import com.yunma.callback.impl.Callback2;
import com.yunma.panel.UnicodePanel;

import java.awt.*;

/***
 * Unicode 编码
 *
 * @author huangweii
 *         2015年9月30日
 */
public class MD5Callback extends Callback2 {
    private Color bgColor;
    private UnicodePanel unicodePanel;

    public MD5Callback() {
        super();
        bgColor = CustomColor.getMoreLightColor();
    }

    @Override
    public String callback(String input, Object encoding) {
        String chinese;
        try {
            if (ValueWidget.isNullOrEmpty(encoding)) {
                encoding = SystemHWUtil.CURR_ENCODING;
            }
            chinese = SystemHWUtil.getMD5(input, (String) encoding);
            return chinese;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getButtonLabel() {
        return "生成MD5";
    }

    @Override
    public Color getBackGroundColor() {
        return bgColor;
    }

    @Override
    public String getHelpInfo() {
        return "MD5时统一采用UTF-8编码,与网上保持一致";
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
