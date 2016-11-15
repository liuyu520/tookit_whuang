package com.yunma.dialog;

import com.common.util.WindowUtil;
import com.string.widget.util.ValueWidget;
import com.swing.component.AssistPopupTextField;
import com.swing.dialog.GenericDialog;
import com.swing.dialog.SearchInputDialog;
import com.swing.dialog.toast.ToastMessage;
import com.swing.menu.MenuUtil2;
import com.swing.messagebox.GUIUtil23;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

public class SearchDialog extends GenericDialog {

    private static final long serialVersionUID = 5380855381490259223L;
    private final JPanel contentPanel = new JPanel();
    private AssistPopupTextField textArea;

    /**
     * Create the dialog.
     */
    public SearchDialog(final JFrame frame, final JTabbedPane tabbedPane, String keyword) {
        setModal(true);
        setTitle("搜索");
        setLoc(450, 100);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));
        textArea = new AssistPopupTextField(false/*needSearch*/);
        textArea.placeHolder("请输入关键字,不区分大小写");
        contentPanel.add(textArea, BorderLayout.NORTH);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            JButton searchButton = new JButton("搜索");
            searchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String json = textArea.getText2();
                    if (ValueWidget.isNullOrEmpty(json)) {
                        GUIUtil23.alert("请输入关键字.");
                        return;
                    }
                    String keyWord = textArea.getText2();
                    if (!ValueWidget.isNullOrEmpty(keyWord)) {
                        Set<Integer> searchResult = MenuUtil2.searchAction(keyWord, tabbedPane, true/*includeActionName*/);
                        if (searchResult == null) return;
                        //如果只有一个就直接选中
                        int resultSize = searchResult.size();
                        if (resultSize == 0) {//没有搜索到
                            ToastMessage.toast("无结果", 1000, Color.red);
                        } else if (resultSize == 1) {//仅搜索到一条记录
//                                 searchSuccess(searchResult.iterator().next());
                            tabbedPane.setSelectedIndex(searchResult.iterator().next());
                            SearchDialog.this.close2();
                        } else {
                            MenuUtil2.searchResultList(frame, tabbedPane, searchResult, SearchDialog.this, 100);
                            SearchDialog.this.close2();
                        }
                    }
                }
            });
            buttonPane.add(searchButton);
            getRootPane().setDefaultButton(searchButton);
            JButton cancelButton = new JButton("Cancel");
            cancelButton.setActionCommand("Cancel");
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SearchDialog.this.dispose();
                }
            });
            buttonPane.add(cancelButton);
        }
        if (ValueWidget.isNullOrEmpty(keyword)) {
            keyword = WindowUtil.getSysClipboardText();
            if (!ValueWidget.isNullOrEmpty(keyword) && keyword.length() < 20) {//太长的字符串就忽略
                textArea.setText(keyword);
//                textArea.selectAll();
            }
        } else {
            textArea.setText(keyword);
//            textArea.selectAll();
        }

    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            SearchInputDialog dialog = new SearchInputDialog(null, null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AssistPopupTextField getTextArea() {
        return textArea;
    }

    public void setTextArea(AssistPopupTextField textArea) {
        this.textArea = textArea;
    }
}
