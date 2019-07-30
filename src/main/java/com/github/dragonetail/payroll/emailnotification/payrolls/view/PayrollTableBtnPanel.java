package com.github.dragonetail.payroll.emailnotification.payrolls.view;

import com.github.dragonetail.payroll.emailnotification.util.MessageUtils;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class PayrollTableBtnPanel extends JPanel {

    private JButton importBtn;
    private JButton testEmailBtn;
    private JButton sendEmailBtn;
//    private JButton saveResultBtn;
    private JButton updateConfigBtn;

    public PayrollTableBtnPanel() {
        initComponents();
    }

    private void initComponents() {
        importBtn = new JButton(MessageUtils.getMessage("action.import"));
        add(importBtn);

        testEmailBtn = new JButton(MessageUtils.getMessage("action.testEmail"));
        add(testEmailBtn);

        sendEmailBtn = new JButton(MessageUtils.getMessage("action.sendEmail"));
        add(sendEmailBtn);

//        saveResultBtn = new JButton(MessageUtils.getMessage("action.saveResult"));
//        add(saveResultBtn);

        updateConfigBtn = new JButton(MessageUtils.getMessage("action.updateConfig"));
        add(updateConfigBtn);
    }

    public JButton getImportBtn() {
        return importBtn;
    }

    public JButton getTestEmailBtn() {
        return testEmailBtn;
    }

    public JButton getSendEmailBtn() {
        return sendEmailBtn;
    }

//    public JButton getSaveResultBtn() {
//        return saveResultBtn;
//    }
    public JButton getUpdateConfigBtn() {
        return updateConfigBtn;
    }
}
