package pl.dmichalski.reservations.business.ui.employees.view;

import org.springframework.stereotype.Component;
import pl.dmichalski.reservations.business.util.ConstMessagesEN;

import javax.swing.*;

@Component
public class EmployeeTableBtnPanel extends JPanel {

    private JButton importBtn;

    public EmployeeTableBtnPanel() {
        initComponents();
    }

    private void initComponents() {
        importBtn = new JButton(ConstMessagesEN.Labels.IMPORT_BTN);
        add(importBtn);
    }

    public JButton getImportBtn() {
        return importBtn;
    }
}
