package com.yunma.callback;

import com.common.util.SystemHWUtil;
import com.io.hw.awt.color.CustomColor;
import com.string.widget.util.ValueWidget;
import com.swing.dialog.UnicodePanel;
import com.swing.dialog.callback.Callback2;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

/***
 * javascript code template
 *
 * @author huangweii
 *         2016年2月1日
 */
public class JavaScriptCodeTemplateCallback extends Callback2 {

    public static final String shell_type_search_str = "搜索字符串";
    /***
     * 增加注释
     */
    public static final String javascript_code_type_whether_exist = "判断变量是否存在";
    public static final String javascript_code_type_return_json = "接口返回json";
    public static final String javascript_code_type_find_pidbyPort = "通过端口查进程";
    public static final String whether_var_exist = "if (typeof(%s)=== \"undefined\")" + SystemHWUtil.CRLF +
            "{" + SystemHWUtil.CRLF +
            "    console.log(false);//没有声明" + SystemHWUtil.CRLF +
            "}else{" + SystemHWUtil.CRLF +
            "    console.log(true);" + SystemHWUtil.CRLF +
            "}";
    public static final String return_json = "Map map=new HashMap();" + SystemHWUtil.CRLF +
            "map.put(Constant2.LOGIN_RESULT_KEY, false);" + SystemHWUtil.CRLF +
            "map.put(Constant2.RESPONSE_KEY_ERROR_MESSAGE, \"\");" + SystemHWUtil.CRLF +
            "return HWJacksonUtils.getJsonP(map);";
    public static final String javascript_code_type_ajax_errorcode = "ajax 错误码";
    public static final String ajax_error_code = "if ((%s.status && %s.status == '401') " + SystemHWUtil.CRLF +
            "    || (%s.statusText && (%s.statusText == 'No Transport'||%s.statusText == 'Unauthorized'))) {" + SystemHWUtil.CRLF +
            "    console.log(%s);" + SystemHWUtil.CRLF +
            "    var currentUrl = window.location.href;" + SystemHWUtil.CRLF +
            "    window.location.href = currentUrl;" + SystemHWUtil.CRLF +
            "    return;" + SystemHWUtil.CRLF +
            "}else if ((%s.status && %s.status == '404') || (%s.statusText && %s.statusText == 'Not Found')){//added by huangweii @2016-01-18" + SystemHWUtil.CRLF +
            "    if(%s.responseText){" + SystemHWUtil.CRLF +
            "        $('body')[0].innerHTML=%s.responseText;" + SystemHWUtil.CRLF +
            "        return;" + SystemHWUtil.CRLF +
            "    }" + SystemHWUtil.CRLF +
            "}";
    public static final String divide_tab = "\t\t";

    private Color bgColor;
    private UnicodePanel unicodePanel;

    public JavaScriptCodeTemplateCallback() {
        super();
        bgColor = CustomColor.getMoreLightColor();
    }

    public static String whetherVarExist(String cmd) {
        String result = String.format(whether_var_exist, cmd);
        return result;
    }

    public static String ajaxErrorCodeDeal(String cmd) {
        if (ValueWidget.isNullOrEmpty(cmd) || UnicodePanel.please_input.equals(cmd)) {
            cmd = "data";
        }
        String result = String.format(ajax_error_code, cmd, cmd, cmd, cmd, cmd, cmd, cmd, cmd, cmd, cmd, cmd, cmd);
        return result;
    }

    public static String return_json() {
        return return_json;
    }

    @Override
    public String callback(String input, Object encoding) {
        if (ValueWidget.isNullOrEmpty(input)) {
            /*ToastMessage.toast("请输入内容", 1000,Color.RED);
			getUnicodePanel().getInputTextArea().requestFocus();
			return null;*/
            input = UnicodePanel.please_input;
        }
        if (encoding.equals(javascript_code_type_whether_exist)) {
            return whetherVarExist(input);
        } else if (encoding.equals(javascript_code_type_ajax_errorcode)) {
            return ajaxErrorCodeDeal(input);
        }
        return null;
    }

	/*public static String find(String cmd){
		StringBuffer buffer=new StringBuffer();
		String result=String.format(find_file,cmd,cmd);
		buffer.append("搜索文件:\t\t"+divide_tab+result).append(SystemHWUtil.CRLF);
		buffer.append("打印搜索到的文件:"+divide_tab+String.format(find_file_more,cmd,cmd)).append(SystemHWUtil.CRLF);
		buffer.append("grep搜索到的文件:"+divide_tab+String.format(find_file_grep,cmd,cmd)).append(SystemHWUtil.CRLF);
		buffer.append("删除搜索到的文件:"+divide_tab+String.format(find_file_and_delete,cmd,cmd));
		return buffer.toString();
	}*/

    @Test
    public void test_pid() {
        String cmd = "data";

        System.out.println(JavaScriptCodeTemplateCallback.ajaxErrorCodeDeal(cmd));
		/*String string="a\nb\n\n\n";
		System.out.println(string);
		string=string.replaceAll("\n+$","");
		System.out.println(string);
		System.out.println(RegexUtil.splitPlus(string, (String)"\n", "\""));*/
    }

    @Override
    public String getButtonLabel() {
        return "获取shell脚本";
    }

    @Override
    public Color getBackGroundColor() {
        return bgColor;
    }

    @Override
    public String getHelpInfo() {
        return null;
    }

    @Override
    public JComboBox<String> getJComboBox() {
        JComboBox<String> encodingComboBox = new JComboBox<String>();
        encodingComboBox.addItem(javascript_code_type_whether_exist);
        encodingComboBox.addItem(javascript_code_type_ajax_errorcode);
        return encodingComboBox;
    }

    @Override
    public UnicodePanel getUnicodePanel() {
        return this.unicodePanel;
    }

    @Override
    public void setUnicodePanel(UnicodePanel unicodePanel) {
        this.unicodePanel = unicodePanel;
    }
}
