package com.yunma.callback;

import com.string.widget.util.RegexUtil;

import javax.swing.*;

public class Code2StringSingleQuoteCallback extends Code2StringCallback {
    @Override
    public String callback(String input, Object crfl) {
        return RegexUtil.splitPlus(input, (String) crfl, "'", false);
    }

    @Override
    public String callbackAdditional(String input, Object crfl) {
        return RegexUtil.splitPlus(input, (String) crfl, "'", true);
    }

    @Override
    public JButton getAdditionalBtn() {
        return new JButton("保持空格");
    }
}
