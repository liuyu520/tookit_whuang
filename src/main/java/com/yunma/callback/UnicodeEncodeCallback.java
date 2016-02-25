package com.yunma.callback;

import com.common.util.SystemHWUtil;
import com.io.hw.awt.color.CustomColor;
import com.kunlunsoft.unicode2chinese.Conversion;
import com.yunma.callback.impl.Callback2;
import com.yunma.panel.UnicodePanel;

import java.awt.*;

/***
 * Unicode 编码
 *
 * @author huangweii
 *         2015年9月30日
 */
public class UnicodeEncodeCallback extends Callback2 {
    private Color bgColor;
    private UnicodePanel unicodePanel;

    public UnicodeEncodeCallback() {
        super();
        bgColor = CustomColor.getMoreLightColor();
    }

    @Override
    public String callback(String input, Object encoding) {
        String chinese = Conversion.chinaToUnicode(input, true);
        String chinesePercent = Conversion.chinaToUnicode(input, true, "%");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(chinese).append(SystemHWUtil.CRLF);
        stringBuffer.append(chinesePercent);
        return stringBuffer.toString();
    }

    @Override
    public String getButtonLabel() {
        return "Unicode编码";
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
