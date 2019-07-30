package com.github.dragonetail.payroll.emailnotification.employees.view;

import com.github.dragonetail.payroll.emailnotification.employees.model.EmployeeTableModel;
import com.github.dragonetail.payroll.emailnotification.shared.view.FixedColumnTable;
import com.github.dragonetail.payroll.emailnotification.util.JTableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class EmployeeTablePanel extends JPanel {

    private EmployeeTableModel tableModel;

    private JTable table;

    @Autowired
    EmployeeTablePanel(EmployeeTableModel tableModel) {
        this.tableModel = tableModel;

        setPanelUp();
        initComponents();
    }

    private void setPanelUp() {
        setLayout(new BorderLayout());
    }

    private void initComponents() {
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        JTableUtils.setColumnWidths(table,
                30,
                80,
                60,
                160,
                80);

        JScrollPane paneWithTable = new JScrollPane(table);
        paneWithTable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        new FixedColumnTable(3, paneWithTable);

        add(paneWithTable, BorderLayout.CENTER);
    }
}
