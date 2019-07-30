package com.github.dragonetail.payroll.emailnotification.main.view;

import com.github.dragonetail.payroll.emailnotification.employees.controller.EmployeesController;
import com.github.dragonetail.payroll.emailnotification.payrolls.controller.PayrollsController;
import com.github.dragonetail.payroll.emailnotification.util.LookAndFeelUtils;
import com.github.dragonetail.payroll.emailnotification.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class MainMenuFrame extends JFrame {

    private EmployeesController employeesController;
    private PayrollsController payrollsController;


    @Autowired
    public MainMenuFrame( EmployeesController employeesController,
                              PayrollsController payrollsController) {
        this.employeesController = employeesController;
        this.payrollsController = payrollsController;

        setFrameUp();
        initComponents();
        pack();
    }

    private void setFrameUp() {
        getRootPane().setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        setTitle(MessageUtils.getMessage("main.title"));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(new Dimension(1024,640));
        setPreferredSize(new Dimension(1024,640));
        setLocationRelativeTo(null);
        setResizable(true);
        LookAndFeelUtils.setWindowsLookAndFeel();
    }

    private void initComponents() {
        Container employeesControllerContentPane = employeesController.getContentPane();
        Container payrollsControllerContentPane = payrollsController.getContentPane();

        employeesControllerContentPane.setPreferredSize(new Dimension(260,640));

        add(employeesControllerContentPane, BorderLayout.WEST);
        add(payrollsControllerContentPane, BorderLayout.CENTER);

        payrollsControllerContentPane.requestFocus();
    }
}
