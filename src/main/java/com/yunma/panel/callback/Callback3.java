package com.yunma.panel.callback;

import com.common.util.SystemHWUtil;
import com.swing.component.AssistPopupTextField;

import javax.swing.*;

/**
 * Created by huangweii on 2016/3/12.<br>
 * 统一接口
 */
public abstract class Callback3 {
    /***
     * 第一个文本框
     */
    protected AssistPopupTextField textField1;
    /***
     * 第二个文本框
     */
    protected AssistPopupTextField textField2;
    /***
     * 第三个文本框
     */
    protected AssistPopupTextField textField3;
    /***
     * 第四个文本框
     */
    protected AssistPopupTextField textField4;

    public abstract JButton getBtn1();

    public abstract JButton getBtn2();

    public String getLabel1() {
        return SystemHWUtil.EMPTY;
    }

    public String getLabel2() {
        return SystemHWUtil.EMPTY;
    }

    public String getLabel3() {
        return SystemHWUtil.EMPTY;
    }

    public String getLabel4() {
        return SystemHWUtil.EMPTY;
    }

    /***
     * 是否显示第三个文本框
     *
     * @return
     */
    public boolean showTf3() {
        return false;
    }

    /***
     * 是否显示第四个文本框
     *
     * @return
     */
    public boolean showTf4() {
        return false;
    }

    public boolean showCheckbox() {
        return false;
    }

    public void init(AssistPopupTextField textField1,
                     AssistPopupTextField textField2,
                     AssistPopupTextField textField3,
                     AssistPopupTextField textField4) {
        this.textField1 = textField1;
        this.textField2 = textField2;
        this.textField3 = textField3;
        this.textField4 = textField4;
    }
}
