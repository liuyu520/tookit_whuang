package com.yunma.callback;

import com.common.util.SystemHWUtil;
import com.io.hw.awt.color.CustomColor;
import com.string.widget.util.ValueWidget;
import com.swing.dialog.UnicodePanel;
import com.swing.dialog.callback.Callback2;

import java.awt.*;

/***
 * Unicode 编码
 *
 * @author huangweii
 *         2015年9月30日
 */
public class Base64DecodeCallback extends Callback2 {
    private Color bgColor;
    private UnicodePanel unicodePanel;

    public Base64DecodeCallback() {
        super();
        bgColor = CustomColor.getMoreLightColor();
    }

    @Override
    public String callback(String input, Object encoding) {
        if (ValueWidget.isNullOrEmpty(input)) {
            return null;
        }
        String chinese;
        if (ValueWidget.isNullOrEmpty(encoding)) {
            encoding = SystemHWUtil.CURR_ENCODING;
        }
        try {
            byte[] decodeBaseBytes = SystemHWUtil.decodeBase64(input);
            if (!ValueWidget.isNullOrEmpty(decodeBaseBytes)) {
                chinese = new String(decodeBaseBytes, (String) encoding);
                return chinese;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getButtonLabel() {
        return "Base64解码";
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
