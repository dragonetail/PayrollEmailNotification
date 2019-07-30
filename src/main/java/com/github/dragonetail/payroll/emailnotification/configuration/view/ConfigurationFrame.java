package com.github.dragonetail.payroll.emailnotification.configuration.view;

import com.github.dragonetail.payroll.emailnotification.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class ConfigurationFrame extends JDialog {

    private ConfigurationPanel formPanel;
    private ConfigurationBtnPanel formBtnPanel;

    @Autowired
    public ConfigurationFrame(ConfigurationPanel formPanel, ConfigurationBtnPanel formBtnPanel) {
        this.formPanel = formPanel;
        this.formBtnPanel = formBtnPanel;
        setFrameUp();
        initComponents();
        pack();
    }

    private void setFrameUp() {
        setTitle(MessageUtils.getMessage("action.cancel"));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setModal(true);
    }

    private void initComponents() {
        add(formPanel, BorderLayout.CENTER);
        add(formBtnPanel, BorderLayout.SOUTH);
    }

    public ConfigurationPanel getFormPanel() {
        return formPanel;
    }

    public ConfigurationBtnPanel getFormBtnPanel() {
        return formBtnPanel;
    }

}
