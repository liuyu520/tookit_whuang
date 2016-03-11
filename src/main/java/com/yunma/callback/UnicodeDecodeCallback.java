package com.yunma.callback;

import com.io.hw.awt.color.CustomColor;
import com.kunlunsoft.unicode2chinese.Conversion;
import com.swing.dialog.UnicodePanel;
import com.swing.dialog.callback.Callback2;

import java.awt.*;

/***
 * Unicode 解码
 *
 * @author huangweii
 *         2015年9月30日
 */
public class UnicodeDecodeCallback extends Callback2 {

    private Color bgColor;
    private UnicodePanel unicodePanel;

    public UnicodeDecodeCallback() {
        super();
        bgColor = CustomColor.getMoreLightColor();
    }

    @Override
    public String callback(String input, Object encoding) {
        String chinese = Conversion.unicodeToChinese(input);
        return chinese;
    }

    @Override
    public String getButtonLabel() {
        return "Unicode解码";
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
