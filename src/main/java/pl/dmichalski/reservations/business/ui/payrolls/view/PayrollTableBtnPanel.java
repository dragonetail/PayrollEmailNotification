package pl.dmichalski.reservations.business.ui.payrolls.view;

import org.springframework.stereotype.Component;
import pl.dmichalski.reservations.business.util.ConstMessagesEN;

import javax.swing.*;

@Component
public class PayrollTableBtnPanel extends JPanel {

    private JButton importBtn;
    private JButton sendEmailBtn;
    private JButton saveResultBtn;

    public PayrollTableBtnPanel() {
        initComponents();
    }

    private void initComponents() {
        importBtn = new JButton(ConstMessagesEN.Labels.IMPORT_BTN);
        add(importBtn);

        sendEmailBtn = new JButton(ConstMessagesEN.Labels.SEND_EMAIL_BTN);
        add(sendEmailBtn);

        saveResultBtn = new JButton(ConstMessagesEN.Labels.SAVE_RESULT_BTN);
        add(saveResultBtn);
    }

    public JButton getImportBtn() {
        return importBtn;
    }

    public JButton getSendEmailBtn() {
        return sendEmailBtn;
    }

    public JButton getSaveResultBtn() {
        return saveResultBtn;
    }
}
