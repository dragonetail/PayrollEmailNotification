package com.github.dragonetail.payroll.emailnotification.payrolls.controller;

import com.github.dragonetail.payroll.emailnotification.payrolls.bean.Payroll;
import com.github.dragonetail.payroll.emailnotification.payrolls.model.PayrollTableModel;
import com.github.dragonetail.payroll.emailnotification.payrolls.service.PayrollService;
import com.github.dragonetail.payroll.emailnotification.payrolls.view.PayrollTableBtnPanel;
import com.github.dragonetail.payroll.emailnotification.payrolls.view.PayrollsFrame;
import com.github.dragonetail.payroll.emailnotification.shared.controller.AbstractFrameController;
import com.github.dragonetail.payroll.emailnotification.shared.view.ExcelFileFilter;
import com.github.dragonetail.payroll.emailnotification.util.Notifications;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;

@Controller
public class PayrollsController extends AbstractFrameController {
    private static Logger logger = LoggerFactory.getLogger(PayrollsController.class);
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
        loadPayrolls();
        showPayrollsFrame();
    }

    public Container getContentPane() {
        loadPayrolls();

        return payrollsFrame.getContentPane();
    }

    private void loadPayrolls() {
        List<Payroll> employees = payrollService.findAll();
        payrollTableModel.clear();
        payrollTableModel.addEntities(employees);
    }

    private void showPayrollsFrame() {
        payrollsFrame.setVisible(true);
    }

    private void importPayrolls() {

        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new ExcelFileFilter());
            fileChooser.showOpenDialog(payrollsFrame.getContentPane());
            File file = fileChooser.getSelectedFile();
            if (file == null) {
                return;
            }
            String path = file.getAbsolutePath();
            payrollService.importFile(path);
            loadPayrolls();
        } catch (Exception e) {
            logger.error("Failed to loaded Payrolls.", e);
            Notifications.showFailedToImportPayrolls(e.getMessage());
        }
    }

    private void sendEmail() {
        System.out.println("sendEmail");
    }

    private void saveResult() {
        System.out.println("saveResult");
    }

}
