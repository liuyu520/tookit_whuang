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
public class Base64EncodeCallback extends Callback2 {
    private Color bgColor;
    private UnicodePanel unicodePanel;

    public Base64EncodeCallback() {
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
            chinese = SystemHWUtil.encodeBase64(input.getBytes((String) encoding));
            return chinese;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getButtonLabel() {
        return "Base64编码";
    }

    @Override
    public Color getBackGroundColor() {
        return bgColor;
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
