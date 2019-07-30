package com.github.dragonetail.payroll.emailnotification.payrolls.model;

import com.github.dragonetail.payroll.emailnotification.util.MessageUtils;
import org.springframework.stereotype.Component;
import com.github.dragonetail.payroll.emailnotification.payrolls.bean.Payroll;
import com.github.dragonetail.payroll.emailnotification.shared.model.DefaultTableModel;

@Component
public class PayrollTableModel extends DefaultTableModel<Payroll> {

    @Override
    public String[] getColumnLabels() {
        return new String[]{
                MessageUtils.getMessage("payroll.column.id"),
                MessageUtils.getMessage("payroll.column.no"),
                MessageUtils.getMessage("payroll.column.name"),
                MessageUtils.getMessage("payroll.column.sentStatus"),
                MessageUtils.getMessage("payroll.column.sentTime"),
                MessageUtils.getMessage("payroll.column.department"),
                MessageUtils.getMessage("payroll.column.level"),
                MessageUtils.getMessage("payroll.column.baseSalary"),
                MessageUtils.getMessage("payroll.column.jobSalary"),
                MessageUtils.getMessage("payroll.column.awardSalary"),
                MessageUtils.getMessage("payroll.column.otherSalary"),
                MessageUtils.getMessage("payroll.column.socialInsurance"),
                MessageUtils.getMessage("payroll.column.housingFund"),
                MessageUtils.getMessage("payroll.column.specialAdditionalDeduction"),
                MessageUtils.getMessage("payroll.column.leavingDeduction"),
                MessageUtils.getMessage("payroll.column.otherDeduction"),
                MessageUtils.getMessage("payroll.column.givingWithTax"),
                MessageUtils.getMessage("payroll.column.tax"),
                MessageUtils.getMessage("payroll.column.giving"),
                MessageUtils.getMessage("payroll.column.remarks")
        };
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Payroll paryroll = entities.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return paryroll.getId();
            case 1:
                return paryroll.getNo();
            case 2:
                return paryroll.getName();
            case 3:
                return paryroll.getSentStatus();
            case 4:
                return paryroll.getSentTime();
            case 5:
                return paryroll.getDepartment();
            case 6:
                return paryroll.getLevel();
            case 7:
                return paryroll.getBaseSalary();
            case 8:
                return paryroll.getJobSalary();
            case 9:
                return paryroll.getAwardSalary();
            case 10:
                return paryroll.getOtherSalary();
            case 11:
                return paryroll.getSocialInsurance();
            case 12:
                return paryroll.getHousingFund();
            case 13:
                return paryroll.getSpecialAdditionalDeduction();
            case 14:
                return paryroll.getLeavingDeduction();
            case 15:
                return paryroll.getOtherDeduction();
            case 16:
                return paryroll.getGivingWithTax();
            case 17:
                return paryroll.getTax();
            case 18:
                return paryroll.getGiving();
            case 19:
                return paryroll.getRemarks();

            default:
                return "";
        }
    }
}
