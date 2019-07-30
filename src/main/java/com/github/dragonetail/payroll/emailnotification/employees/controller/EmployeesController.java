package com.github.dragonetail.payroll.emailnotification.employees.controller;

import com.github.dragonetail.payroll.emailnotification.employees.bean.Employee;
import com.github.dragonetail.payroll.emailnotification.employees.model.EmployeeTableModel;
import com.github.dragonetail.payroll.emailnotification.employees.service.EmployeeService;
import com.github.dragonetail.payroll.emailnotification.employees.view.EmployeeTableBtnPanel;
import com.github.dragonetail.payroll.emailnotification.employees.view.EmployeesFrame;
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
public class EmployeesController extends AbstractFrameController {
    private static Logger logger = LoggerFactory.getLogger(EmployeesController.class);
    private EmployeesFrame employeesFrame;
    private EmployeeTableModel employeeTableModel;
    private EmployeeService employeeService;

    @Autowired
    public EmployeesController(EmployeesFrame employeesFrame,
                               EmployeeTableModel employeeTableModel,
                               EmployeeService employeeService) {
        this.employeesFrame = employeesFrame;
        this.employeeTableModel = employeeTableModel;
        this.employeeService = employeeService;
    }

    @PostConstruct
    private void prepareListeners() {
        EmployeeTableBtnPanel tableBtnPanel = employeesFrame.getTableBtnPanel();

        registerAction(tableBtnPanel.getImportBtn(), (e) -> importEmployees());
    }

    @Override
    public void prepareAndOpenFrame() {
        loadEmployees();
        showClientsFrame();
    }

    public Container getContentPane() {
        loadEmployees();

        return employeesFrame.getContentPane();
    }

    private void loadEmployees() {
        List<Employee> employees = employeeService.findAll();
        employeeTableModel.clear();
        employeeTableModel.addEntities(employees);
    }

    private void showClientsFrame() {
        employeesFrame.setVisible(true);
    }

    private void importEmployees() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new ExcelFileFilter());
            fileChooser.showOpenDialog(employeesFrame.getContentPane());
            File file = fileChooser.getSelectedFile();
            if (file == null) {
                return;
            }
            String path = file.getAbsolutePath();
            employeeService.importFile(path);
            loadEmployees();
        } catch (Exception e) {
            logger.error("Failed to loaded Employees.", e);
            Notifications.showFailedToImportEmployees(e.getMessage());
        }
    }

}
