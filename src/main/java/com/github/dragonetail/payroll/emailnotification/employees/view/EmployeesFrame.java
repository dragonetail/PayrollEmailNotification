package com.github.dragonetail.payroll.emailnotification.employees.view;

import com.github.dragonetail.payroll.emailnotification.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class EmployeesFrame extends JFrame {

    private static final int DEFAULT_WIDTH = 750;
    private static final int DEFAULT_HEIGHT = 340;

    private EmployeeTablePanel tablePanel;
    private EmployeeTableBtnPanel tableBtnPanel;

    @Autowired
    public EmployeesFrame(EmployeeTablePanel tablePanel,
                          EmployeeTableBtnPanel tableBtnPanel) {
        this.tablePanel = tablePanel;
        this.tableBtnPanel = tableBtnPanel;

        setFrameUp();
        initComponents();
    }

    private void setFrameUp() {
        setTitle(MessageUtils.getMessage("employees.title"));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        //setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        //setLocationRelativeTo(null);
        setResizable(true);
    }

    private void initComponents() {
        JPanel container = new JPanel(new BorderLayout());
        container.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 6));
        container.add(new JLabel(MessageUtils.getMessage("employees.title")), BorderLayout.NORTH);
        container.add(tablePanel, BorderLayout.CENTER);
        container.add(tableBtnPanel, BorderLayout.SOUTH);

        add(container, BorderLayout.CENTER);
//
//
//        add(new JLabel(MessageUtils.getMessage("employees.title")), BorderLayout.NORTH);
//        add(tablePanel, BorderLayout.CENTER);
//        add(tableBtnPanel, BorderLayout.SOUTH);
    }

    public EmployeeTableBtnPanel getTableBtnPanel() {
        return tableBtnPanel;
    }

}
