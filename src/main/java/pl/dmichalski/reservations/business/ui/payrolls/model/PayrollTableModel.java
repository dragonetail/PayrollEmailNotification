package pl.dmichalski.reservations.business.ui.payrolls.model;

import org.springframework.stereotype.Component;
import pl.dmichalski.reservations.business.ui.payrolls.bean.Payroll;
import pl.dmichalski.reservations.business.ui.shared.model.DefaultTableModel;
import pl.dmichalski.reservations.business.util.ConstMessagesEN;

@Component
public class PayrollTableModel extends DefaultTableModel<Payroll> {

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
        Payroll paryroll = entities.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return paryroll.getName();
            case 1:
                return paryroll.getSurname();
            case 2:
                return paryroll.getPesel();
            case 3:
                return paryroll.getPhoneNumber();
            case 4:
                return paryroll.getEmail();
            case 5:
                return paryroll.getAddress();
            default:
                return "";
        }
    }
}
