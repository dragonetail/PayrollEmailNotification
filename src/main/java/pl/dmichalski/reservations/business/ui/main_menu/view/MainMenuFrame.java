package pl.dmichalski.reservations.business.ui.main_menu.view;

import org.springframework.stereotype.Component;
import pl.dmichalski.reservations.business.util.Borders;
import pl.dmichalski.reservations.business.util.ConstMessagesEN;
import pl.dmichalski.reservations.business.util.LookAndFeelUtils;

import javax.swing.*;
import java.awt.*;

@Component
public class MainMenuFrame extends JFrame {

    private JButton formsBtn;
    private JButton reportsBtn;
    private JButton employeesBtn;
    private JButton payrollsBtn;

    public MainMenuFrame() {
        setFrameUp();
        initComponents();
        pack();
    }

    private void setFrameUp() {
        getRootPane().setBorder(Borders.createEmptyBorder());
        setTitle(ConstMessagesEN.Labels.MAIN_MENU);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        LookAndFeelUtils.setWindowsLookAndFeel();
        setLayout(new GridLayout(1, 2, 20, 20));
    }

    private void initComponents() {
        formsBtn = new JButton(ConstMessagesEN.Labels.FORMS);
        reportsBtn = new JButton(ConstMessagesEN.Labels.REPORTS);
        employeesBtn = new JButton(ConstMessagesEN.Labels.EMPLOYEES);
        payrollsBtn = new JButton(ConstMessagesEN.Labels.PAYROLLS);

        add(formsBtn);
        add(reportsBtn);
        add(employeesBtn);
        add(payrollsBtn);
    }

    public JButton getFormsBtn() {
        return formsBtn;
    }

    public JButton getReportsBtn() {
        return reportsBtn;
    }
    public JButton getEmployeesBtn() {
        return employeesBtn;
    }
    public JButton getPayrollsBtn() {
        return payrollsBtn;
    }
}
