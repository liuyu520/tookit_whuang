package com.yunma.callback;

import com.common.util.SystemHWUtil;
import com.io.hw.awt.color.CustomColor;
import com.string.widget.util.RandomUtils;
import com.string.widget.util.RegexUtil;
import com.string.widget.util.ValueWidget;
import com.swing.dialog.UnicodePanel;
import com.swing.dialog.callback.Callback2;
import com.time.util.TimeHWUtil;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

/***
 * 通过命令名称查询进程id
 *
 * @author huangweii
 *         2016年1月10日
 */
public class JavaCodeTemplateCallback extends Callback2 {

    public static final String shell_type_search_str = "搜索字符串";
    /***
     * 增加注释
     */
    public static final String java_code_type_double_shift = "Shift双击";
    public static final String java_code_type_return_json = "接口返回json";
    public static final String java_code_type_swing_Border = "swing边框";
    public static final String double_shift = "%s.addKeyListener(new KeyListener() {" + SystemHWUtil.CRLF +
            "    private long lastTimeMillSencond;" + SystemHWUtil.CRLF +
            "    @Override" + SystemHWUtil.CRLF +
            "    public void keyTyped(KeyEvent e) {" + SystemHWUtil.CRLF +
            "    }" + SystemHWUtil.CRLF +
            "    @Override" + SystemHWUtil.CRLF +
            "    public void keyReleased(KeyEvent e) {" + SystemHWUtil.CRLF +
            "    }" + SystemHWUtil.CRLF +
            "    @Override" + SystemHWUtil.CRLF +
            "    public void keyPressed(KeyEvent e) {" + SystemHWUtil.CRLF +
            "        if (EventHWUtil.isJustShiftDown(e)) {" + SystemHWUtil.CRLF +
            "            if (lastTimeMillSencond == 0) {" + SystemHWUtil.CRLF +
            "                lastTimeMillSencond = System.currentTimeMillis();" + SystemHWUtil.CRLF +
            "            } else {" + SystemHWUtil.CRLF +
            "                long currentTime = System.currentTimeMillis();" + SystemHWUtil.CRLF +
            "                if (MenuUtil2.isDoubleClick(currentTime - lastTimeMillSencond )) {" + SystemHWUtil.CRLF +
            "                    System.out.println(\"双击Shift\");" + SystemHWUtil.CRLF +
            "                    //TODO " + SystemHWUtil.CRLF +
            "                    lastTimeMillSencond = 0;" + SystemHWUtil.CRLF +
            "                } else {" + SystemHWUtil.CRLF +
            "                    lastTimeMillSencond = System.currentTimeMillis();" + SystemHWUtil.CRLF +
            "                }" + SystemHWUtil.CRLF +
            "            }" + SystemHWUtil.CRLF +
            "        }" + SystemHWUtil.CRLF +
            "    }" + SystemHWUtil.CRLF +
            "});";
    public static final String return_json = "Map map=new HashMap();" + SystemHWUtil.CRLF +
            "map.put(Constant2.LOGIN_RESULT_KEY, false);" + SystemHWUtil.CRLF +
            "map.put(Constant2.RESPONSE_KEY_ERROR_MESSAGE, \"\");" + SystemHWUtil.CRLF +
            "return HWJacksonUtils.getJsonP(map);";
    public static final String swing_border = "Border borderResultTA%s = BorderFactory.createEtchedBorder(Color.white," + SystemHWUtil.CRLF +
            "new Color(148, 145, 140));" + SystemHWUtil.CRLF +
            "TitledBorder resultTitle%s = new TitledBorder(borderResultTA%s, \"<请输入标题>\");" + SystemHWUtil.CRLF +
            "%s.setBorder(resultTitle%s);";
    public static final String modify_log = "//added by huangweii @%s" + SystemHWUtil.CRLF + "//modified by huangweii @%s";
    public static final String java_code_type_modify_log = "修改记录";
    public static final String java_code_type_IDEA_config="IDEA class配置";
    public static final String java_code_type_Hump_marking = "驼峰标识";
    public static final String IDEA_webapp="%ssrc\\main\\webapp";
    public static final String IDEA_webapp_classes=IDEA_webapp+"\\WEB-INF\\classes";
    public static final String IDEA_classes="%starget\\classes";
    public static final String divide_tab = "\t\t";
    private Color bgColor;
    private UnicodePanel unicodePanel;

    public JavaCodeTemplateCallback() {
        super();
        bgColor = CustomColor.getMoreLightColor();
    }

    public static String doubleShift(String cmd) {
        String result = String.format(double_shift, cmd);
        return result;
    }
    public static String IDEAConfig(String projectPath) {
    	if(ValueWidget.isNullOrEmpty(projectPath)){
    		return null;
    	}
    	if(!projectPath.endsWith("\\")){
    		projectPath=projectPath+"\\";
    	}
        StringBuffer buffer = new StringBuffer();
        String result = String.format(IDEA_webapp, projectPath);
        buffer.append("webapp路径:" + divide_tab + result).append(SystemHWUtil.CRLF);
        buffer.append("classes路径:" + divide_tab + String.format(IDEA_webapp_classes, projectPath)).append(SystemHWUtil.CRLF);
        buffer.append("classes路径:" + divide_tab + String.format(IDEA_classes, projectPath));
        return buffer.toString();
    }

    public static String swingBorder(String scroll) {
        String random = RandomUtils.getRandomStr(3);
        String result = String.format(swing_border, random, random, random, scroll, random);
        return result;
    }

    public static String modifyLog() {
        String datetime = TimeHWUtil.getCurrentDateTime();
        String result = String.format(modify_log, datetime, datetime);
        return result;
    }

    public static String return_json() {
        return return_json;
    }

    public static String humpMarking(String input) {
        if (input.contains("_")) {
            return RegexUtil.humpMarking(input);
        } else {
            return "暂未实现";
        }
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

    @Override
    public String callback(String input, Object encoding) {
        if (ValueWidget.isNullOrEmpty(input)) {
            /*ToastMessage.toast("请输入内容", 1000,Color.RED);
            getUnicodePanel().getInputTextArea().requestFocus();
			return null;*/
            input = UnicodePanel.please_input;
        }
        if (encoding.equals(java_code_type_double_shift)) {
            return doubleShift(input);
        } else if (encoding.equals(java_code_type_return_json)) {
            return return_json();
        } else if (encoding.equals(java_code_type_swing_Border)) {
            return swingBorder(input);
        } else if (encoding.equals(java_code_type_modify_log)) {
            return modifyLog();
        } else if (encoding.equals(java_code_type_IDEA_config)) {
            return IDEAConfig(input);
        } else if (encoding.equals(java_code_type_Hump_marking)) {
            return humpMarking(input);
        }
        return null;
    }

    @Test
    public void test_pid() {
        String cmd = "8005";
        System.out.println(doubleShift(cmd));
//		System.out.println(JavaCodeTemplateCallback.return_json());
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
        encodingComboBox.addItem(java_code_type_double_shift);
        encodingComboBox.addItem(java_code_type_return_json);
        encodingComboBox.addItem(java_code_type_swing_Border);
        encodingComboBox.addItem(java_code_type_modify_log);
        encodingComboBox.addItem(java_code_type_IDEA_config);
        encodingComboBox.addItem(java_code_type_Hump_marking);
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
