package com.github.dragonetail.payroll.emailnotification.employees.view;

import com.github.dragonetail.payroll.emailnotification.util.MessageUtils;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class EmployeeTableBtnPanel extends JPanel {

    private JButton importBtn;

    public EmployeeTableBtnPanel() {
        initComponents();
    }

    private void initComponents() {
        importBtn = new JButton(MessageUtils.getMessage("action.import"));
        add(importBtn);
    }

    public JButton getImportBtn() {
        return importBtn;
    }
}
