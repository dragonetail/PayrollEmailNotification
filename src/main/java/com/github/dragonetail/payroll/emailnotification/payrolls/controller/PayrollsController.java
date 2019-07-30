package com.github.dragonetail.payroll.emailnotification.payrolls.controller;

import com.github.dragonetail.payroll.emailnotification.configuration.bean.Configuration;
import com.github.dragonetail.payroll.emailnotification.configuration.controller.ConfigurationController;
import com.github.dragonetail.payroll.emailnotification.configuration.service.ConfigurationService;
import com.github.dragonetail.payroll.emailnotification.configuration.view.ConfigurationFrame;
import com.github.dragonetail.payroll.emailnotification.employees.bean.Employee;
import com.github.dragonetail.payroll.emailnotification.employees.service.EmployeeService;
import com.github.dragonetail.payroll.emailnotification.payrolls.bean.Payroll;
import com.github.dragonetail.payroll.emailnotification.payrolls.model.PayrollTableModel;
import com.github.dragonetail.payroll.emailnotification.payrolls.service.MailSenderService;
import com.github.dragonetail.payroll.emailnotification.payrolls.service.PayrollService;
import com.github.dragonetail.payroll.emailnotification.payrolls.view.PayrollTableBtnPanel;
import com.github.dragonetail.payroll.emailnotification.payrolls.view.PayrollsFrame;
import com.github.dragonetail.payroll.emailnotification.shared.controller.AbstractFrameController;
import com.github.dragonetail.payroll.emailnotification.shared.view.ExcelFileFilter;
import com.github.dragonetail.payroll.emailnotification.util.DateFormatter;
import com.github.dragonetail.payroll.emailnotification.util.Notifications;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
public class PayrollsController extends AbstractFrameController {
    private static Logger logger = LoggerFactory.getLogger(PayrollsController.class);
    private PayrollsFrame payrollsFrame;
    private PayrollTableModel payrollTableModel;
    private PayrollService payrollService;
    private EmployeeService employeeService;
    private MailSenderService mailSenderService;
    private ConfigurationController configurationController;
    private ConfigurationService configurationService;


    @Autowired
    public PayrollsController(PayrollsFrame payrollsFrame,
                              PayrollTableModel payrollTableModel,
                              PayrollService payrollService,
                              EmployeeService employeeService,
                              MailSenderService mailSenderService, ConfigurationFrame configurationFrame, ConfigurationController configurationController, ConfigurationService configurationService) {
        this.payrollsFrame = payrollsFrame;
        this.payrollTableModel = payrollTableModel;
        this.payrollService = payrollService;
        this.employeeService = employeeService;
        this.mailSenderService = mailSenderService;
        this.configurationController = configurationController;
        this.configurationService = configurationService;
    }

    @PostConstruct
    private void prepareListeners() {
        PayrollTableBtnPanel tableBtnPanel = payrollsFrame.getTableBtnPanel();

        registerAction(tableBtnPanel.getImportBtn(), (e) -> importPayrolls());

        registerAction(tableBtnPanel.getTestEmailBtn(), (e) -> sendTestEmail());
        registerAction(tableBtnPanel.getSendEmailBtn(), (e) -> sendEmail());
//        registerAction(tableBtnPanel.getSaveResultBtn(), (e) -> saveResult());
        registerAction(tableBtnPanel.getUpdateConfigBtn(), (e) -> showConfiguration());
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
        List<Payroll> payrolls = payrollService.findAll();
        payrollTableModel.clear();
        payrollTableModel.addEntities(payrolls);
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

    private void sendTestEmail() {
        sendEmailInternal(true);
    }

    private void sendEmail() {
        sendEmailInternal(false);
    }

    private void sendEmailInternal(boolean isTest) {
        List<Payroll> payrolls = payrollService.findAll();
        sendEmailInternalAsync(isTest, payrolls, -1, false);

//        Configuration configuration = configurationService.getConfiguration();
//        String emailForTest = configuration.getEmailForTest();
//        String fromAddress = configuration.getEmailFromAddress();
//        String ownerPassword = configuration.getPdfOwnerPassword();
//        String payrollMonth = configuration.getPayrollMonth();
//
//        List<Payroll> payrolls = payrollService.findAll();
//        int pos = -1;
//        Payroll payroll = payrolls.get(pos);
//        String no = payroll.getNo();
//        Employee employee = employeeService.get(no);
//        if (employee == null) {
//            payroll.setSentStatus("Failed");
//            payrollTableModel.fireTableDataChanged();
//
//            continue;
//        }
//
//        String toAddress = employee.getEmail();
//        if (isTest) {
//            toAddress = emailForTest;
//        }
//
//        String userPassword = employee.getPassword();
//
//        payroll.setSentStatus("Sending");
//        payrollTableModel.fireTableDataChanged();
//
//
//
//        try {
//            mailSenderService.sendTemplateMail(payroll, fromAddress, toAddress, userPassword, ownerPassword, payrollMonth);
//        } catch (Exception e) {
//            logger.warn("Failed to send email: " + toAddress, e);
//        }
//        payroll.setSentStatus("Sent");
//        payroll.setSentTime(DateFormatter.formatTime(new Date()));
//        payrollTableModel.fireTableDataChanged();
    }

    private void sendEmailInternalAsync(final boolean isTest, final List<Payroll> payrolls,
                                        final int pos, final boolean isSkip) {
        Configuration configuration = configurationService.getConfiguration();
        String emailForTest = configuration.getEmailForTest();
        String fromAddress = configuration.getEmailFromAddress();
        String ownerPassword = configuration.getPdfOwnerPassword();
        String payrollMonth = configuration.getPayrollMonth();

        new SwingWorker<Integer, Void>() {
            @Override
            protected Integer doInBackground() {
                if (pos < 0) {
                    return -1;
                }
                if (isSkip) {
                    return pos;
                }

                Payroll payroll = payrolls.get(pos);
                String no = payroll.getNo();
                Employee employee = employeeService.get(no);

                String toAddress = employee.getEmail();
                if (isTest) {
                    toAddress = emailForTest;
                }

                String userPassword = employee.getPassword();

                try {
                    mailSenderService.sendTemplateMail(payroll, fromAddress, toAddress, userPassword, ownerPassword, payrollMonth);
                    payroll.setSentStatus("Sent");
                } catch (Exception e) {
                    logger.warn("Failed to send email: " + toAddress, e);
                    payroll.setSentStatus("Error");
                }

                payroll.setSentTime(DateFormatter.formatTime(new Date()));

                return pos;
            }

            @Override
            protected void done() {
                int curPos = -1;
                try {
                    curPos = get();
                } catch (InterruptedException e) {
                    logger.warn("Failed to execute in background. ", e);
                    return;
                } catch (ExecutionException e) {
                    logger.warn("Failed to execute in background. ", e);
                    return;
                }

                payrollTableModel.fireTableDataChanged();

                curPos = curPos + 1;
                if (curPos >= payrolls.size()) {
                    logger.info("Finished to send emails in background. ");
                    return;
                }

                Payroll payroll = payrolls.get(curPos);
                String no = payroll.getNo();
                Employee employee = employeeService.get(no);
                if (employee == null) {
                    payroll.setSentStatus("Failed");
                    payrollTableModel.fireTableDataChanged();

                    sendEmailInternalAsync(isTest, payrolls, curPos, true);
                }

                payroll.setSentStatus("Sending");
                payrollTableModel.fireTableDataChanged();
                sendEmailInternalAsync(isTest, payrolls, curPos, false);
            }
        }.execute();
    }


//    private void saveResult() {
//        System.out.println("saveResult");
//    }

    private void showConfiguration() {
        configurationController.prepareAndOpenFrame();
    }

}
