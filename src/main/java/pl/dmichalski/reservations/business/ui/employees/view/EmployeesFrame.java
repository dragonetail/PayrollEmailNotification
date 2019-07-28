package pl.dmichalski.reservations.business.ui.employees.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.dmichalski.reservations.business.ui.forms.client.view.ClientTablePanel;
import pl.dmichalski.reservations.business.ui.forms.client.view.TableBtnPanel;
import pl.dmichalski.reservations.business.util.Borders;
import pl.dmichalski.reservations.business.util.ConstMessagesEN;
import pl.dmichalski.reservations.business.util.LookAndFeelUtils;

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
        setTitle(ConstMessagesEN.Labels.CLIENTS);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void initComponents() {
        add(tablePanel, BorderLayout.CENTER);
        add(tableBtnPanel, BorderLayout.SOUTH);
    }

    public EmployeeTablePanel getTablePanel() {
        return tablePanel;
    }

    public EmployeeTableBtnPanel getTableBtnPanel() {
        return tableBtnPanel;
    }

}
