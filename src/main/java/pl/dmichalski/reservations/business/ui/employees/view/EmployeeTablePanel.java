package pl.dmichalski.reservations.business.ui.employees.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.dmichalski.reservations.business.ui.employees.model.EmployeeTableModel;
import pl.dmichalski.reservations.business.ui.forms.client.model.ClientTableModel;

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

        JScrollPane paneWithTable = new JScrollPane(table);
        add(paneWithTable, BorderLayout.CENTER);
    }

    public JTable getTable() {
        return table;
    }

}
