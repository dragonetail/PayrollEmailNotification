package pl.dmichalski.reservations.business.ui.payrolls.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.dmichalski.reservations.business.ui.payrolls.bean.Payroll;
import pl.dmichalski.reservations.business.ui.payrolls.model.PayrollTableModel;
import pl.dmichalski.reservations.business.ui.payrolls.service.PayrollService;
import pl.dmichalski.reservations.business.ui.payrolls.view.PayrollTableBtnPanel;
import pl.dmichalski.reservations.business.ui.payrolls.view.PayrollsFrame;
import pl.dmichalski.reservations.business.ui.shared.controller.AbstractFrameController;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.util.List;

@Controller
public class PayrollsController extends AbstractFrameController {
    private PayrollsFrame payrollsFrame;
    private PayrollTableModel payrollTableModel;
    private PayrollService payrollService;

    @Autowired
    public PayrollsController(PayrollsFrame payrollsFrame,
                              PayrollTableModel payrollTableModel,
                              PayrollService payrollService) {
        this.payrollsFrame = payrollsFrame;
        this.payrollTableModel = payrollTableModel;
        this.payrollService = payrollService;
    }

    @PostConstruct
    private void prepareListeners() {
        PayrollTableBtnPanel tableBtnPanel = payrollsFrame.getTableBtnPanel();

        registerAction(tableBtnPanel.getImportBtn(), (e) -> importPayrolls());
        registerAction(tableBtnPanel.getSendEmailBtn(), (e) -> sendEmail());
        registerAction(tableBtnPanel.getSaveResultBtn(), (e) -> saveResult());
    }

    @Override
    public void prepareAndOpenFrame() {
        loadClients();
        showPayrollsFrame();
    }

    private void loadClients() {
        List<Payroll> employees = payrollService.findAll();
        payrollTableModel.clear();
        payrollTableModel.addEntities(employees);
    }

    private void showPayrollsFrame() {
        payrollsFrame.setVisible(true);
    }

    private void importPayrolls() {
        System.out.println("importPayrolls");
    }

    private void sendEmail() {
        System.out.println("sendEmail");
    }

    private void saveResult() {
        System.out.println("saveResult");
    }

}
