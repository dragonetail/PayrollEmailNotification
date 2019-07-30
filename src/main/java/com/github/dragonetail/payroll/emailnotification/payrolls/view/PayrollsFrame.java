package com.github.dragonetail.payroll.emailnotification.payrolls.view;

import com.github.dragonetail.payroll.emailnotification.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class PayrollsFrame extends JFrame {

    private static final int DEFAULT_WIDTH = 750;
    private static final int DEFAULT_HEIGHT = 340;

    private PayrollTablePanel tablePanel;
    private PayrollTableBtnPanel tableBtnPanel;

    @Autowired
    public PayrollsFrame(PayrollTablePanel tablePanel,
                         PayrollTableBtnPanel tableBtnPanel) {
        this.tablePanel = tablePanel;
        this.tableBtnPanel = tableBtnPanel;

        setFrameUp();
        initComponents();
    }

    private void setFrameUp() {
        setTitle(MessageUtils.getMessage("payrolls.title"));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        //setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        //setLocationRelativeTo(null);
        setResizable(true);
    }

    private void initComponents() {
        JPanel container = new JPanel(new BorderLayout());
        container.setBorder(BorderFactory.createEmptyBorder(0, 6, 0, 0));
        container.add(new JLabel(MessageUtils.getMessage("payrolls.title")), BorderLayout.NORTH);
        container.add(tablePanel, BorderLayout.CENTER);
        container.add(tableBtnPanel, BorderLayout.SOUTH);

        add(container, BorderLayout.CENTER);
    }

    public PayrollTableBtnPanel getTableBtnPanel() {
        return tableBtnPanel;
    }

}
