package com.github.dragonetail.payroll.emailnotification.employees.model;

import com.github.dragonetail.payroll.emailnotification.employees.bean.Employee;
import com.github.dragonetail.payroll.emailnotification.shared.model.DefaultTableModel;
import com.github.dragonetail.payroll.emailnotification.util.MessageUtils;
import org.springframework.stereotype.Component;

@Component
public class EmployeeTableModel extends DefaultTableModel<Employee> {

    @Override
    public String[] getColumnLabels() {
        return new String[]{
                MessageUtils.getMessage("employee.column.id"),
                MessageUtils.getMessage("employee.column.no"),
                MessageUtils.getMessage("employee.column.name"),
                MessageUtils.getMessage("employee.column.email"),
                MessageUtils.getMessage("employee.column.password")
        };
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Employee employee = entities.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return employee.getId();
            case 1:
                return employee.getNo();
            case 2:
                return employee.getName();
            case 3:
                return employee.getEmail();
            case 4:
                return employee.getPassword();
            default:
                return "";
        }
    }
}
