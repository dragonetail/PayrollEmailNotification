package com.github.dragonetail.payroll.emailnotification.configuration.view;

import com.github.dragonetail.payroll.emailnotification.configuration.bean.Configuration;
import com.github.dragonetail.payroll.emailnotification.util.MessageUtils;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class ConfigurationPanel extends JPanel {

    private static final int LAYOUT_ROWS = 9;
    private static final int LAYOUT_COLS = 2;
    private static final int HORIZONTAL_GAP = 0;
    private static final int VERTICAL_GAP = 10;
    private static final int TEXT_FIELD_COLUMNS = 20;

    private JTextField payrollMonthField;
    private JTextField pdfOwnerPasswordField;
    private JTextField emailForTestField;
    private JTextField emailFromAddressField;
    private JTextField smtpHostField;
    private JTextField smtpPortField;
    private JTextField smtpUsernameField;
    private JTextField smtpPasswordField;
    private JCheckBox smtpAuthField;

    public ConfigurationPanel() {
        setPanelUp();
        initComponents();
    }

    private void setPanelUp() {
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        setLayout(new GridLayout(LAYOUT_ROWS, LAYOUT_COLS, HORIZONTAL_GAP, VERTICAL_GAP));
    }

    private void initComponents() {
        {
            JLabel label = new JLabel(MessageUtils.getMessage("configuration.field.payrollMonth"));
            payrollMonthField = new JTextField(TEXT_FIELD_COLUMNS);
            add(label);
            add(payrollMonthField);
        }
        {
            JLabel label = new JLabel(MessageUtils.getMessage("configuration.field.emailForTest"));
            emailForTestField = new JTextField(TEXT_FIELD_COLUMNS);
            add(label);
            add(emailForTestField);
        }
        {
            JLabel label = new JLabel(MessageUtils.getMessage("configuration.field.emailFromAddress"));
            emailFromAddressField = new JTextField(TEXT_FIELD_COLUMNS);
            add(label);
            add(emailFromAddressField);
        }
        {
            JLabel label = new JLabel(MessageUtils.getMessage("configuration.field.smtpUsername"));
            smtpUsernameField = new JTextField(TEXT_FIELD_COLUMNS);
            add(label);
            add(smtpUsernameField);
        }
        {
            JLabel label = new JLabel(MessageUtils.getMessage("configuration.field.smtpPassword"));
            smtpPasswordField = new JTextField(TEXT_FIELD_COLUMNS);
            add(label);
            add(smtpPasswordField);
        }
        {
            JLabel label = new JLabel(MessageUtils.getMessage("configuration.field.smtpHost"));
            smtpHostField = new JTextField(TEXT_FIELD_COLUMNS);
            add(label);
            add(smtpHostField);
        }
        {
            JLabel label = new JLabel(MessageUtils.getMessage("configuration.field.smtpPort"));
            smtpPortField = new JTextField(TEXT_FIELD_COLUMNS);
            add(label);
            add(smtpPortField);
        }
        {
            JLabel label = new JLabel(MessageUtils.getMessage("configuration.field.smtpAuth"));
            smtpAuthField = new JCheckBox();
            add(label);
            add(smtpAuthField);
        }
        {
            JLabel label = new JLabel(MessageUtils.getMessage("configuration.field.pdfOwnerPassword"));
            pdfOwnerPasswordField = new JTextField(TEXT_FIELD_COLUMNS);
            add(label);
            add(pdfOwnerPasswordField);
        }

    }

    public Configuration getEntityFromForm() {
        Configuration configuration = new Configuration();

        configuration.setPayrollMonth(payrollMonthField.getText());
        configuration.setPdfOwnerPassword(pdfOwnerPasswordField.getText());
        configuration.setEmailForTest(emailForTestField.getText());
        configuration.setEmailFromAddress(emailFromAddressField.getText());
        configuration.setSmtpHost(smtpHostField.getText());
        configuration.setSmtpPort(Integer.valueOf(smtpPortField.getText()));
        configuration.setSmtpUsername(smtpUsernameField.getText());
        configuration.setSmtpPassword(smtpPasswordField.getText());
        configuration.setSmtpAuth(smtpAuthField.isSelected());

        return configuration;
    }

    public void resetForm(Configuration configuration) {
        payrollMonthField.setText(configuration.getPayrollMonth());
        pdfOwnerPasswordField.setText(configuration.getPdfOwnerPassword());
        emailForTestField.setText(configuration.getEmailForTest());
        emailFromAddressField.setText(configuration.getEmailFromAddress());
        smtpHostField.setText(configuration.getSmtpHost());
        smtpPortField.setText(String.valueOf(configuration.getSmtpPort()));
        smtpUsernameField.setText(configuration.getSmtpUsername());
        smtpPasswordField.setText(configuration.getSmtpPassword());
        smtpAuthField.setSelected(configuration.isSmtpAuth());
    }
}
