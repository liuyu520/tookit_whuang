package com.yunma.panel;

import com.swing.component.AssistPopupTextArea;
import com.swing.component.AssistPopupTextField;
import com.swing.component.ComponentUtil;
import com.swing.dialog.GenericPanel;
import com.yunma.panel.callback.Callback3;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Map;

public class ScpGenericPane extends GenericPanel {

    private JPanel contentPane;
    private AssistPopupTextField textField1;
    private AssistPopupTextField textField2;
    private AssistPopupTextField textField3;
    private JLabel label_1;
    private AssistPopupTextField textField4;
    private JPanel panel;
    private JButton generateButton;
    private JScrollPane scrollPane;
    private AssistPopupTextArea resultTextArea;
    private JButton button;
    private JCheckBox checkBox;

    private Callback3 callback3;

    /**
     * Launch the application.
     */
    /*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScpGenericPane frame = new ScpGenericPane();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

    /**
     * Create the frame.
     */
    public ScpGenericPane(final String action, final Map<String, Callback3> callback3Map) {
        this.callback3 = callback3Map.get(action);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = this;
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPane);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{0, 0, 0};
        gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
        gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl_contentPane);

        JLabel lblip = new JLabel(callback3.getLabel1());
        GridBagConstraints gbc_lblip = new GridBagConstraints();
        gbc_lblip.insets = new Insets(0, 0, 5, 5);
        gbc_lblip.anchor = GridBagConstraints.WEST;
        gbc_lblip.gridx = 0;
        gbc_lblip.gridy = 0;
        contentPane.add(lblip, gbc_lblip);

        textField1 = new AssistPopupTextField();
        GridBagConstraints gbc_textField1 = new GridBagConstraints();
        gbc_textField1.insets = new Insets(0, 0, 5, 0);
        gbc_textField1.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField1.gridx = 1;
        gbc_textField1.gridy = 0;
        contentPane.add(textField1, gbc_textField1);
        textField1.setColumns(10);

        JLabel lblip_2 = new JLabel(callback3.getLabel2());
        GridBagConstraints gbc_lblip_1 = new GridBagConstraints();
        gbc_lblip_1.anchor = GridBagConstraints.WEST;
        gbc_lblip_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblip_1.gridx = 0;
        gbc_lblip_1.gridy = 1;
        contentPane.add(lblip_2, gbc_lblip_1);

        textField2 = new AssistPopupTextField();
        GridBagConstraints gbc_textField2 = new GridBagConstraints();
        gbc_textField2.insets = new Insets(0, 0, 5, 0);
        gbc_textField2.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField2.gridx = 1;
        gbc_textField2.gridy = 1;
        contentPane.add(textField2, gbc_textField2);
        textField2.setColumns(10);

        if (callback3.showTf3()) {
            JLabel label3 = new JLabel(callback3.getLabel3());
            GridBagConstraints gbc_label = new GridBagConstraints();
            gbc_label.anchor = GridBagConstraints.WEST;
            gbc_label.insets = new Insets(0, 0, 5, 5);
            gbc_label.gridx = 0;
            gbc_label.gridy = 2;
            contentPane.add(label3, gbc_label);

            textField3 = new AssistPopupTextField();
            GridBagConstraints gbc_textField3 = new GridBagConstraints();
            gbc_textField3.insets = new Insets(0, 0, 5, 0);
            gbc_textField3.fill = GridBagConstraints.HORIZONTAL;
            gbc_textField3.gridx = 1;
            gbc_textField3.gridy = 2;
            contentPane.add(textField3, gbc_textField3);
            textField3.setColumns(10);
        }

        if (callback3.showTf4()) {
            label_1 = new JLabel(callback3.getLabel4());
            GridBagConstraints gbc_label_1 = new GridBagConstraints();
            gbc_label_1.anchor = GridBagConstraints.WEST;
            gbc_label_1.insets = new Insets(0, 0, 5, 5);
            gbc_label_1.gridx = 0;
            gbc_label_1.gridy = 3;
            contentPane.add(label_1, gbc_label_1);

            textField4 = new AssistPopupTextField();
            GridBagConstraints gbc_textField4 = new GridBagConstraints();
            gbc_textField4.insets = new Insets(0, 0, 5, 0);
            gbc_textField4.fill = GridBagConstraints.HORIZONTAL;
            gbc_textField4.gridx = 1;
            gbc_textField4.gridy = 3;
            contentPane.add(textField4, gbc_textField4);
            textField4.setColumns(10);
        }


        panel = new JPanel();
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.insets = new Insets(0, 0, 5, 0);
        gbc_panel.gridwidth = 2;
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 4;
        contentPane.add(panel, gbc_panel);

        if (callback3.showCheckbox()) {
            checkBox = new JCheckBox("复选框");
            panel.add(checkBox);
        }


        generateButton = callback3.getBtn1();
        panel.add(generateButton);

        button = callback3.getBtn2();
        if (null != button) {
            panel.add(button);
        }
        resultTextArea = new AssistPopupTextArea();
        JButton copyBtn = ComponentUtil.getCopyBtn(resultTextArea, "复制结果(C)");
        copyBtn.setMnemonic('C');
        panel.add(copyBtn);

        scrollPane = new JScrollPane();
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.gridwidth = 2;
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 0;
        gbc_scrollPane.gridy = 5;
        contentPane.add(scrollPane, gbc_scrollPane);


        scrollPane.setViewportView(resultTextArea);
        end();
    }

    private void end() {
        callback3.init(textField1, textField2, textField3, textField4, resultTextArea);
    }
}
