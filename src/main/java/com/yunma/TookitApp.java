package com.yunma;

import com.common.util.SystemHWUtil;
import com.io.hw.awt.color.CustomColor;
import com.swing.component.AssistPopupTextArea;
import com.swing.component.ComponentUtil;
import com.swing.component.QRCodePanel;
import com.swing.dialog.DialogUtil;
import com.swing.dialog.GenericFrame;
import com.swing.dialog.UnicodePanel;
import com.swing.dialog.callback.Callback2;
import com.swing.event.EventHWUtil;
import com.swing.menu.MenuUtil2;
import com.yunma.callback.*;
import com.yunma.panel.ScpGenericPane;
import com.yunma.panel.callback.Callback3;
import com.yunma.panel.callback.impl.FindCallback;
import com.yunma.panel.callback.impl.NginxCallback;
import com.yunma.panel.callback.impl.ScpCallback;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TookitApp extends GenericFrame {

    private static final long serialVersionUID = -5589368355582776494L;
    /***
     * <action,class>
     */
    public static Map<String, Callback2> callbackMap = new HashMap<String, Callback2>();
    public static Map<String, Callback3> callback3Map = new HashMap<String, Callback3>();

    static {
        callbackMap.put("unicode_de", new UnicodeDecodeCallback());//解码,结果是中文
        callbackMap.put("unicode_en", new UnicodeEncodeCallback());//编码
        callbackMap.put("base64_en", new Base64EncodeCallback());//编码
        callbackMap.put("base64_de", new Base64DecodeCallback());//解码
        callbackMap.put("md5", new MD5Callback());//MD5
        callbackMap.put("code2str", new Code2StringCallback());//code2str 双引号
        callbackMap.put("code2strsingle", new Code2StringSingleQuoteCallback());//code2str 单引号
        callbackMap.put("json_beautify", new JsonBeautifyCallback());//json_beautify
        callbackMap.put("html_escape", new EscapeHTMLCallback());//html escape
        callbackMap.put("html_unescape", new UnescapeHTMLCallback());//html unescape
        callbackMap.put("linux_killpid", new LinuxShellCallback());//linux 杀死进程
        callbackMap.put("java_code", new JavaCodeTemplateCallback());//java code
        callbackMap.put("javascript_code", new JavaScriptCodeTemplateCallback());//javascript code

        callback3Map.put("linux_scp", new ScpCallback());//linux_scp
        callback3Map.put("linux_nginx", new NginxCallback());//生成Nginx
        callback3Map.put("linux_find", new FindCallback());//linux find
    }

    private JPanel contentPane;
    private AssistPopupTextArea toEncodeTextArea;
    /***
     * URL编码时指定的编码
     */
    private JComboBox<String> encodingComboBox;
    /***
     * 编码之后的结果
     */
    private JTextArea resultTxtrAa;
    /***
     * URL解码的输入
     */
    private AssistPopupTextArea encodedTextArea_1;
    /***
     * URL解码时指定的编码
     */
    private JComboBox<String> encoding2ComboBox;
    /***
     * URL解码的结果
     */
    private JTextArea resultTextArea_1;
    private JTabbedPane tabbedPane;
    private int defaultTabbedIndex = 0;
    /***
     * tab 的序号,用于使用快捷键Shift+Tab 切换Tab
     */
    private List<Integer> indexList = new ArrayList<Integer>();

    {
        indexList.add(defaultTabbedIndex);
    }

    /**
     * Create the frame.
     */
    public TookitApp() {
        DialogUtil.lookAndFeel2();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("工具箱");
        try {
            setIcon("com/yunma/img/logo.png", TookitApp.class);
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        fullScreen2(this);
        setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        contentPane.add(tabbedPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        tabbedPane.addTab("URL编码", null, panel, null);
        panel.setLayout(new BorderLayout(0, 0));

        JSplitPane splitPane = new JSplitPane();
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        int screenHeight = getScreenHeight();
        splitPane.setDividerLocation((screenHeight - 120) / 2);
        splitPane.setDividerSize(5);
        splitPane.setContinuousLayout(true);
        splitPane.setOneTouchExpandable(true);
        panel.add(splitPane);

        JPanel panel_4 = new JPanel();
        splitPane.setLeftComponent(panel_4);
        panel_4.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        panel_4.add(scrollPane);

        toEncodeTextArea = new AssistPopupTextArea();
        toEncodeTextArea.setLineWrap(true);
        toEncodeTextArea.setWrapStyleWord(true);
        toEncodeTextArea.setBackground(CustomColor.red_little_more);
        scrollPane.setViewportView(toEncodeTextArea);
        toEncodeTextArea.addKeyListener(new KeyListener() {
            private long lastTimeMillSencond;

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (EventHWUtil.isJustShiftDown(e)) {
                    if (lastTimeMillSencond == 0) {
                        lastTimeMillSencond = System.currentTimeMillis();
                    } else {
                        long currentTime = System.currentTimeMillis();
                        if (MenuUtil2.isDoubleClick(currentTime - lastTimeMillSencond)) {
                            System.out.println("双击Shift");
                            urlEncodeAction();
                            lastTimeMillSencond = 0;
                        } else {
                            lastTimeMillSencond = System.currentTimeMillis();
                        }
                    }
                }
            }
        });

        JPanel panel_5 = new JPanel();
        splitPane.setRightComponent(panel_5);
        panel_5.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane_1 = new JScrollPane();
        panel_5.add(scrollPane_1, BorderLayout.CENTER);

        resultTxtrAa = new RSyntaxTextArea();
        resultTxtrAa.setBackground(CustomColor.red_little_more);
        resultTxtrAa.setLineWrap(true);
        resultTxtrAa.setWrapStyleWord(true);
        scrollPane_1.setViewportView(resultTxtrAa);

        JPanel panel_6 = new JPanel();
        panel_5.add(panel_6, BorderLayout.NORTH);

        encodingComboBox = ComponentUtil.getEncodingComboBox();
        encodingComboBox.setSelectedIndex(1);
        panel_6.add(encodingComboBox);

        JButton urlEncodeButton = new JButton("编码");
        urlEncodeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                urlEncodeAction();
            }
        });
        panel_6.add(urlEncodeButton);

        JButton copyButton = ComponentUtil.getCopyBtn(resultTxtrAa);
        panel_6.add(copyButton);

        JPanel panel_1 = new JPanel();
        tabbedPane.addTab("URL解码", null, panel_1, null);
        panel_1.setLayout(new BorderLayout(0, 0));

        JSplitPane splitPane_1 = new JSplitPane();
        splitPane_1.setDividerLocation((screenHeight - 120) / 2);
        splitPane_1.setDividerSize(5);
        splitPane_1.setContinuousLayout(true);
        splitPane_1.setOneTouchExpandable(true);
        splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
        panel_1.add(splitPane_1);

        JPanel panel_7 = new JPanel();
        splitPane_1.setLeftComponent(panel_7);
        panel_7.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane_2 = new JScrollPane();
        panel_7.add(scrollPane_2);

        encodedTextArea_1 = new AssistPopupTextArea();
        encodedTextArea_1.setLineWrap(true);
        encodedTextArea_1.setWrapStyleWord(true);
        scrollPane_2.setViewportView(encodedTextArea_1);
        encodedTextArea_1.addKeyListener(new KeyListener() {
            private long lastTimeMillSencond;

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (EventHWUtil.isJustShiftDown(e)) {
                    if (lastTimeMillSencond == 0) {
                        lastTimeMillSencond = System.currentTimeMillis();
                    } else {
                        long currentTime = System.currentTimeMillis();
                        if (MenuUtil2.isDoubleClick(currentTime - lastTimeMillSencond)) {
                            System.out.println("双击Shift");
                            urlDecodeAction();
                            lastTimeMillSencond = 0;
                        } else {
                            lastTimeMillSencond = System.currentTimeMillis();
                        }
                    }
                }
            }
        });

        JPanel panel_8 = new JPanel();
        splitPane_1.setRightComponent(panel_8);
        panel_8.setLayout(new BorderLayout(0, 0));

        JPanel panel_9 = new JPanel();
        panel_8.add(panel_9, BorderLayout.NORTH);

        encoding2ComboBox = ComponentUtil.getEncodingComboBox();
        encoding2ComboBox.setSelectedIndex(1);
        panel_9.add(encoding2ComboBox);

        JButton button_1 = new JButton("解码");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                urlDecodeAction();
            }
        });
        panel_9.add(button_1);
        resultTextArea_1 = new RSyntaxTextArea();
        resultTextArea_1.setLineWrap(true);
        resultTextArea_1.setWrapStyleWord(true);
        JButton button = ComponentUtil.getCopyBtn(resultTextArea_1);
        panel_9.add(button);

        JScrollPane scrollPane_3 = new JScrollPane();
        panel_8.add(scrollPane_3, BorderLayout.CENTER);


        scrollPane_3.setViewportView(resultTextArea_1);


        tabbedPane.addTab("Unicode编码", null, new UnicodePanel("unicode_en", callbackMap, screenHeight), null);

        tabbedPane.addTab("Unicode解码", null, new UnicodePanel("unicode_de", callbackMap, screenHeight), null);

        tabbedPane.addTab("Base64编码", null, new UnicodePanel("base64_en", callbackMap, screenHeight), null);

        tabbedPane.addTab("Base64解码", null, new UnicodePanel("base64_de", callbackMap, screenHeight), null);
        tabbedPane.addTab("生成MD5值", null, new UnicodePanel("md5", callbackMap, screenHeight), "生成MD5");
        tabbedPane.addTab("code2str 双引号", null, new UnicodePanel("code2str", callbackMap, screenHeight), null);
        tabbedPane.addTab("code2str 单引号", null, new UnicodePanel("code2strsingle", callbackMap, screenHeight), null);
        tabbedPane.addTab("Json 格式化", null, new UnicodePanel("json_beautify", callbackMap, screenHeight), null);
        tabbedPane.addTab("HTML escape", null, new UnicodePanel("html_escape", callbackMap, screenHeight), null);
        tabbedPane.addTab("HTML unescape", null, new UnicodePanel("html_unescape", callbackMap, screenHeight), null);
        tabbedPane.addTab("获取Linux shell脚本", null, new UnicodePanel("linux_killpid", callbackMap, screenHeight), null);
        tabbedPane.addTab("Java code template", null, new UnicodePanel("java_code", callbackMap, screenHeight), null);
        tabbedPane.addTab("Javascript code template", null, new UnicodePanel("javascript_code", callbackMap, screenHeight), null);
        QRCodePanel qrCodePanel = new QRCodePanel(this);
        tabbedPane.addTab("生成二维码", null, qrCodePanel, null);
        tabbedPane.addTab("Linux scp", null, new ScpGenericPane("linux_scp", callback3Map), null);
        tabbedPane.addTab("Linux 生成nginx脚本", null, new ScpGenericPane("linux_nginx", callback3Map), null);
        tabbedPane.addTab("Linux find命令", null, new ScpGenericPane("linux_find", callback3Map), null);

        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
                int selectedIndex = tabbedPane.getSelectedIndex();
                indexList.add(selectedIndex);
//                System.out.println(indexList);
            }
        });

        JPanel statusPanel_10 = new JPanel();
        contentPane.add(statusPanel_10, BorderLayout.SOUTH);
        addGlobalKey();
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TookitApp frame = new TookitApp();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void urlDecodeAction() {
        if (!DialogUtil.verifyTFEmpty(encodedTextArea_1, "原内容")) {
            return;
        }
        String requestCharset = (String) encoding2ComboBox.getSelectedItem();
        try {
            String result = URLDecoder.decode(encodedTextArea_1.getText(), requestCharset);
            resultTextArea_1.setText(result);
            resultTextArea_1.setForeground(CustomColor.getColor());
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
    }

    private void urlEncodeAction() {
        if (!DialogUtil.verifyTFEmpty(toEncodeTextArea, "原内容")) {
            return;
        }
        String requestCharset = (String) encodingComboBox.getSelectedItem();
        try {
            String encodedStr = URLEncoder.encode(toEncodeTextArea.getText(), requestCharset);
            resultTxtrAa.setText(encodedStr);
            resultTxtrAa.setForeground(CustomColor.getColor());
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
    }

    /***
     * 获取当前选中的RequestPanel
     *
     * @return
     */
    public UnicodePanel getCurrentRequestPanel() {
        Component comp = this.tabbedPane.getSelectedComponent();
        if (comp instanceof UnicodePanel) {
            return (UnicodePanel) comp;
        } else {
            return null;
        }
    }

    /***
     * 获取上一次Tab的序号
     *
     * @return
     */
    private int getLastIndex() {
        int length = indexList.size();
        if (length < 2) {
            return SystemHWUtil.NEGATIVE_ONE;
        }
        return indexList.get(length - 2);
    }

    /***
     * 增加全局快捷键Shift+Tab
     */
    private void addGlobalKey() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        // 注册应用程序全局键盘事件, 所有的键盘事件都会被此事件监听器处理.
        toolkit.addAWTEventListener(
                new java.awt.event.AWTEventListener() {
                    //					private long lastTimeMillSencond;
                    public void eventDispatched(AWTEvent event) {
                        if (event.getClass() == KeyEvent.class) {
                            KeyEvent kE = ((KeyEvent) event);
                            // 处理按键事件 Shift+Tab
                            if ((kE.getKeyCode() == KeyEvent.VK_TAB)
                                    && (((InputEvent) event)
                                    .isControlDown()) && kE.getID() == KeyEvent.KEY_PRESSED) {
                                System.out.println("111");
                                int lastIndex = getLastIndex();
                                System.out.println("lastIndex:" + lastIndex);
                                if (lastIndex < 0) {
                                    return;
                                }
//								indexList.add(tabbedPane.getSelectedIndex());
                                tabbedPane.setSelectedIndex(lastIndex);
                            } else if ((kE.getKeyCode() == KeyEvent.VK_EQUALS)
                                    && (((InputEvent) event)
                                    .isControlDown()) && kE.getID() == KeyEvent.KEY_PRESSED) {
                                System.out.println("Ctrl+");
                                UnicodePanel unicodePanel = getCurrentRequestPanel();
                                if (null != unicodePanel) {
                                    unicodePanel.enlarge2();
                                }
                            } else if ((kE.getKeyCode() == KeyEvent.VK_MINUS)
                                    && (((InputEvent) event)
                                    .isControlDown()) && kE.getID() == KeyEvent.KEY_PRESSED) {
                                System.out.println("Ctrl-");
                                UnicodePanel unicodePanel = getCurrentRequestPanel();
                                if (null != unicodePanel) {
                                    unicodePanel.reduce2();
                                }
                            } else if ((kE.getKeyCode() == KeyEvent.VK_K)
                                    && (((InputEvent) event)
                                    .isControlDown()) && kE.getID() == KeyEvent.KEY_PRESSED) {
                                /*if (lastTimeMillSencond == 0) {
									lastTimeMillSencond = System.currentTimeMillis();
								} else {
									long currentTime = System.currentTimeMillis();
									if (MenuUtil2.isDoubleClick(currentTime - lastTimeMillSencond )) {
										System.out.println("AWTEventListener,双击Shift");

										lastTimeMillSencond = 0;
									} else {
										lastTimeMillSencond = System.currentTimeMillis();
									}
								}*/
                                UnicodePanel unicodePanel = getCurrentRequestPanel();
                                if (null != unicodePanel) {
                                    unicodePanel.getInputTextArea().requestFocus();
                                }
                            }
                        }
                    }
                }, java.awt.AWTEvent.KEY_EVENT_MASK);
    }
}
