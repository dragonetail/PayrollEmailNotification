package com.github.dragonetail.payroll.emailnotification.configuration.view;

import com.github.dragonetail.payroll.emailnotification.util.MessageUtils;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class ConfigurationBtnPanel extends JPanel {
    private JButton saveBtn;
    private JButton cancelBtn;

    public ConfigurationBtnPanel() {
        initComponents();
    }

    private void initComponents() {
        saveBtn = new JButton(MessageUtils.getMessage("action.save"));
        add(saveBtn);

        cancelBtn = new JButton(MessageUtils.getMessage("action.cancel"));
        add(cancelBtn);
    }

    public JButton getSaveBtn() {
        return saveBtn;
    }

    public JButton getCancelBtn() {
        return cancelBtn;
    }

}
