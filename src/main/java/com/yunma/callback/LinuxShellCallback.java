package com.yunma.callback;

import com.common.util.SystemHWUtil;
import com.io.hw.awt.color.CustomColor;
import com.string.widget.util.ValueWidget;
import com.swing.dialog.toast.ToastMessage;
import com.yunma.callback.impl.Callback2;
import com.yunma.panel.UnicodePanel;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

/***
 * 通过命令名称查询进程id
 *
 * @author huangweii
 *         2016年1月10日
 */
public class LinuxShellCallback extends Callback2 {
    /***
     * grep 不要-R 参数
     */
    public static final String find_pid_shell = "ps -ef |grep -i \"%s\" |grep -v grep|awk '{print $2}'";
    public static final String killpid = find_pid_shell + "|xargs kill -9 2> /dev/null";
    public static final String killpid2 = find_pid_shell + "|xargs -i kill -9 {}";
    public static final String service_status = "service %s status";
    public static final String service_status2 = "/etc/init.d/%s status";
    public static final String service_stop = "service %s stop";
    public static final String service_start = "service %s start";
    public static final String chkconfig_query = "chkconfig --list %s";
    public static final String chkconfig_on = "chkconfig %s --level 35 on";
    public static final String chkconfig_off = "chkconfig %s --level 35 off";
    public static final String chkconfig_del_service = "chkconfig --del %s";
    public static final String add_comment = "sed -i 's/^\\([^#].*%s\\)/# \\1/' <文件名>";
    public static final String add_comment2 = "sed -i 's/^\\(%s\\)/# \\1/' <文件名>";
    public static final String del_comment = "sed -i 's/^#[[:space:]]*//' <文件名>";
    public static final String find_file = "find  <目录名>  -maxdepth 2 -user root -type f -iname \"*%s*\"|grep \"%s\" --color=auto";
    public static final String find_file_more = find_file + "|xargs -i cat {}";
    public static final String find_file_grep = find_file_more + "| grep -i -r '<请输入>' | cut -d \" \" -f 1,2,3,9,10,11,12 | sort | uniq";
    public static final String find_file_and_delete = find_file + "|xargs rm -fr";
    public static final String search_str = "find ./  -maxdepth 2 -type f|xargs grep -i -n -r \"%s\" --color=auto";
    public static final String tail_head = "tail -n %s %s |head -n %s";
    public static final String tail_head_grep = tail_head + "|grep -i -r -n \"<要搜索的字符串>\"  --color=auto";
    public static final String text_line = "wc -l <文件名> ";
    public static final String tail_real_lineNo = "lines=`wc -l %s|awk {'print $1'}`;echo \"序号应增加:$(($lines-%s))\"";
    /***
     * -m 表示最大匹配行数
     */
    public static final String search_str_grep = "grep -i -r -n -m 300 \"%s\" ./* --color=auto";
    public static final String find_pid_by_port = "netstat -anp |grep \":%s[ ]\\+\"|awk -F\" \"   {'print $6\"\\t\"$7'}";
    public static final String find_pid_by_port_onlyPid = find_pid_by_port + "|cut -d\"/\" -f1|sed 's/^.*[[:space:]]\\+\\([[:digit:]]\\+\\)/\\1/'";
    public static final String start_tomcat_cron = "#!/bin/sh" + SystemHWUtil.CRLF +
            "$grep_result" + SystemHWUtil.CRLF +
            "grep_result=`ps -ef |grep tomcat|grep \"%s\"|grep -v \"grep\"|awk '{print $2}'`" + SystemHWUtil.CRLF +
            "if [ x\"$grep_result\" = x\"\" ];then" + SystemHWUtil.CRLF +
            "	catalina_home2=%s" + SystemHWUtil.CRLF +
            "	CATALINA_HOME=$catalina_home2" + SystemHWUtil.CRLF +
            "	cd $catalina_home2/bin" + SystemHWUtil.CRLF +
            "	./startup.sh" + SystemHWUtil.CRLF +
            "else" + SystemHWUtil.CRLF +
            "	echo \"tomcat(pid:$grep_result) is running...\"  " + SystemHWUtil.CRLF +
            "fi";
    public static final String modify_tomcat_port = "sed -i 's/\\(<Connector[[:space:]]\\+port=\"\\)[[:digit:]]\\+\\(\"\\)/\\1%s\\2/' %s";
    //	public static final String show_tomcat_port="grep -n \"<Connector.*port\"  %s  |sed -e 's/<Connector[[:space:]]\\+port=\"\\([[:digit:]]\\+.*\\).*/\\1/'";
    public static final String show_tomcat_port = "sed '/<!--/{:a;/-->/!{N;ba}};/<!--/d'  %s |grep -v \"AJP/1.3\" | grep -n \"<Connector.*port\" |sed -e 's/<Connector[[:space:]]\\+port=\"\\([[:digit:]]\\+.*\\).*/\\1/'";
    public static final String shell_type_killpid = "获取进程ID";
    public static final String shell_type_service_status = "服务状态";
    public static final String shell_type_service_chkconfig = "服务是否自启动";
    public static final String shell_type_search_str = "搜索字符串";
    /***
     * 增加注释
     */
    public static final String shell_type_comment = "增加注释";
    public static final String shell_type_find_file = "搜索文件";
    public static final String shell_type_find_pidbyPort = "通过端口查进程";
    public static final String shell_type_tail_head = "倒序截断文件指定行数";
    public static final String shell_type_start_tomcat_cron = "定时启动tomcat";
    public static final String shell_type_modify_tomcat_port = "修改tomcat端口号";
    public static final String shell_type_del_xml_comment = "删除<!-- --> 注释";

    public static final String divide_tab = "\t\t";
    private Color bgColor;
    private UnicodePanel unicodePanel;

    public LinuxShellCallback() {
        super();
        bgColor = CustomColor.getMoreLightColor();
    }

    public static String killPid(String cmd) {
        StringBuffer buffer = new StringBuffer();
        String result = String.format(find_pid_shell, cmd);
        buffer.append("查询pid:" + divide_tab + result).append(SystemHWUtil.CRLF);
        buffer.append("杀死进程:" + divide_tab + String.format(killpid, cmd)).append(SystemHWUtil.CRLF);
        buffer.append("杀死进程:" + divide_tab + String.format(killpid2, cmd));
        return buffer.toString();
    }

    public static String modifyTomcatPort(String cmd) {
        String[] port_tomcatHome = cmd.split("[\\s]");
        if (port_tomcatHome.length < 2) {
            ToastMessage.toast("格式:80 /home/whuang/apache-tomcat-7.0.53/conf/server.xml", 3000, Color.red);
            return SystemHWUtil.EMPTY;
        }
        String port = port_tomcatHome[0];
        String tomcatHome = port_tomcatHome[1];
        String confXml = "conf/server.xml";
        if (!tomcatHome.endsWith(confXml)) {
            if (!tomcatHome.endsWith("/")) {
                tomcatHome = tomcatHome + "/";
            }
            tomcatHome += confXml;
        }

        StringBuffer buffer = new StringBuffer();
        buffer.append("修改端口:" + divide_tab + String.format(modify_tomcat_port, port, tomcatHome)).append(SystemHWUtil.CRLF);
        buffer.append("查看端口:" + divide_tab + String.format(show_tomcat_port, tomcatHome));
        return buffer.toString();
    }

    public static String startTomcatCron(String tomcatHome) {
        return String.format(start_tomcat_cron, tomcatHome, tomcatHome);
    }

    public static String find(String cmd) {
        StringBuffer buffer = new StringBuffer();
        String result = String.format(find_file, cmd, cmd);
        buffer.append("搜索文件:\t\t" + divide_tab + result).append(SystemHWUtil.CRLF);
        buffer.append("打印搜索到的文件:" + divide_tab + String.format(find_file_more, cmd, cmd)).append(SystemHWUtil.CRLF);
        buffer.append("grep搜索到的文件:" + divide_tab + String.format(find_file_grep, cmd, cmd)).append(SystemHWUtil.CRLF);
        buffer.append("删除搜索到的文件:" + divide_tab + String.format(find_file_and_delete, cmd, cmd));
        return buffer.toString();
    }

    public static String searchStr(String cmd) {
        if (null != cmd) {
            cmd = cmd.replace(".", "\\.");
        }
        StringBuffer buffer = new StringBuffer();
        String result = String.format(search_str, cmd, cmd);
        buffer.append("搜索字符串:\t" + divide_tab + result).append(SystemHWUtil.CRLF);
        buffer.append("搜索字符串:\t" + divide_tab + String.format(search_str_grep, cmd));
        return buffer.toString();
    }

    public static String findPidByPort(String cmd) {
        StringBuffer buffer = new StringBuffer();
        String result = String.format(find_pid_by_port, cmd, cmd);
        buffer.append("通过端口找进程:" + divide_tab + result).append(SystemHWUtil.CRLF);
        buffer.append("只显示进程ID:\t\t" + String.format(find_pid_by_port_onlyPid, cmd));
        return buffer.toString();
    }

    public static String getTail_head(String cmd) {
        String format = "格式:5-4 test2.txt";
        if (UnicodePanel.please_input.equals(cmd)) {
            ToastMessage.toast(format, 3000, Color.RED);
            return null;
        }
        String[] lines_fileName = cmd.split("[\\s]+");
        if (lines_fileName.length < 2) {
            ToastMessage.toast(format, 3000, Color.RED);
            return null;
        }
        String[] strs = lines_fileName[0].split("[-\\s]");
        String fileName = lines_fileName[1];
        int tail = Integer.parseInt(strs[0]);
        int lines = Integer.parseInt(strs[1]);
        StringBuffer buffer = new StringBuffer();
        String result = String.format(tail_head, tail, fileName, lines);
        buffer.append("尾部截取指定行数:" + divide_tab + result).append(SystemHWUtil.CRLF);
        buffer.append("尾部截取并搜索:\t" + divide_tab + String.format(tail_head_grep, tail, fileName, lines)).append(SystemHWUtil.CRLF);
//		buffer.append("文件总行数:\t\t"+divide_tab+String.format(text_line)).append(SystemHWUtil.CRLF);
        buffer.append("grep结果序号应增加:\t" + divide_tab + String.format(tail_real_lineNo, fileName, tail)).append(SystemHWUtil.CRLF);
        buffer.append("说明:\t第二个参数表示行数");
        return buffer.toString();
    }

    public static String addComment(String cmd) {
        cmd = cmd.replace("/", "\\/");
        StringBuffer buffer = new StringBuffer();
        String result = String.format(add_comment, cmd);
        buffer.append("增加注释:" + divide_tab + result).append(SystemHWUtil.CRLF);
        buffer.append("增加注释:" + divide_tab + String.format(add_comment2, cmd)).append(SystemHWUtil.CRLF);
        buffer.append("删除注释:" + divide_tab + String.format(del_comment, cmd));
        return buffer.toString();
    }

    public static String chkconfig(String cmd) {
        StringBuffer buffer = new StringBuffer();
        String result = String.format(chkconfig_query, cmd);
        buffer.append("查询是否自启动:" + divide_tab + result).append(SystemHWUtil.CRLF);
        buffer.append("自启动:\t" + divide_tab + String.format(chkconfig_on, cmd)).append(SystemHWUtil.CRLF);
        buffer.append("不自启动:\t" + divide_tab + String.format(chkconfig_off, cmd)).append(SystemHWUtil.CRLF);
        buffer.append("删除服务:\t" + divide_tab + String.format(chkconfig_del_service, cmd));
        return buffer.toString();
    }

    public static String serviceStatus(String cmd) {
        StringBuffer buffer = new StringBuffer();
        String status = String.format(service_status, cmd);
        String status2 = String.format(service_status2, cmd);
        buffer.append("查询状态:" + divide_tab + status).append(SystemHWUtil.CRLF);
        buffer.append("查询状态:" + divide_tab + status2).append(SystemHWUtil.CRLF);
        buffer.append("停止服务:" + divide_tab + String.format(service_stop, cmd)).append(SystemHWUtil.CRLF);
        buffer.append("启动服务:" + divide_tab + String.format(service_start, cmd));
        return buffer.toString();
    }

    @Override
    public String callback(String input, Object encoding) {
        if (ValueWidget.isNullOrEmpty(input)) {
            /*ToastMessage.toast("请输入内容", 1000,Color.RED);
			getUnicodePanel().getInputTextArea().requestFocus();
			return null;*/
            input = UnicodePanel.please_input;
        }
        if (encoding.equals(shell_type_killpid)) {
            return killPid(input);
        } else if (encoding.equals(shell_type_service_status)) {
            return serviceStatus(input);
        } else if (encoding.equals(shell_type_service_chkconfig)) {
            return chkconfig(input);
        } else if (encoding.equals(shell_type_comment)) {
            return addComment(input);
        } else if (encoding.equals(shell_type_find_file)) {
            return find(input);
        } else if (encoding.equals(shell_type_search_str)) {
            return searchStr(input);
        } else if (encoding.equals(shell_type_find_pidbyPort)) {
            return findPidByPort(input);
        } else if (encoding.equals(shell_type_tail_head)) {
            return getTail_head(input);
        } else if (encoding.equals(shell_type_start_tomcat_cron)) {
            return startTomcatCron(input);
        } else if (encoding.equals(shell_type_modify_tomcat_port)) {
            return modifyTomcatPort(input);
        }
        return null;
    }

    @Test
    public void test_pid() {
        String cmd = "81 /home/whuang/software/apache/apache-tomcat-7.0.53/conf/server.xml";

        System.out.println(LinuxShellCallback.modifyTomcatPort(cmd));
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
        encodingComboBox.addItem(shell_type_killpid);
        encodingComboBox.addItem(shell_type_service_status);
        encodingComboBox.addItem(shell_type_service_chkconfig);
        encodingComboBox.addItem(shell_type_comment);
        encodingComboBox.addItem(shell_type_find_file);
        encodingComboBox.addItem(shell_type_search_str);
        encodingComboBox.addItem(shell_type_find_pidbyPort);
        encodingComboBox.addItem(shell_type_tail_head);
        encodingComboBox.addItem(shell_type_start_tomcat_cron);
        encodingComboBox.addItem(shell_type_modify_tomcat_port);
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
