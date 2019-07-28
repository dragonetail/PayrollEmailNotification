package pl.dmichalski.reservations.business.ui.employees.model;

import org.springframework.stereotype.Component;
import pl.dmichalski.reservations.business.entity.Client;
import pl.dmichalski.reservations.business.ui.employees.bean.Employee;
import pl.dmichalski.reservations.business.ui.shared.model.DefaultTableModel;
import pl.dmichalski.reservations.business.util.ConstMessagesEN;

@Component
public class EmployeeTableModel extends DefaultTableModel<Employee> {

    @Override
    public String[] getColumnLabels() {
        return new String[]{
                ConstMessagesEN.Labels.NAME,
                ConstMessagesEN.Labels.SURNAME,
                ConstMessagesEN.Labels.PESEL,
                ConstMessagesEN.Labels.PHONE_NUMBER,
                ConstMessagesEN.Labels.EMAIL,
                ConstMessagesEN.Labels.ADDRESS};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Employee employee = entities.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return employee.getName();
            case 1:
                return employee.getSurname();
            case 2:
                return employee.getPesel();
            case 3:
                return employee.getPhoneNumber();
            case 4:
                return employee.getEmail();
            case 5:
                return employee.getAddress();
            default:
                return "";
        }
    }
}
