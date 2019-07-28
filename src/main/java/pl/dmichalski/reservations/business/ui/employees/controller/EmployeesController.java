package pl.dmichalski.reservations.business.ui.employees.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.dmichalski.reservations.business.ui.employees.bean.Employee;
import pl.dmichalski.reservations.business.ui.employees.model.EmployeeTableModel;
import pl.dmichalski.reservations.business.ui.employees.service.EmployeeService;
import pl.dmichalski.reservations.business.ui.employees.view.EmployeeTableBtnPanel;
import pl.dmichalski.reservations.business.ui.employees.view.EmployeesFrame;
import pl.dmichalski.reservations.business.ui.shared.controller.AbstractFrameController;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
public class EmployeesController extends AbstractFrameController {
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
        loadClients();
        showClientsFrame();
    }

    private void loadClients() {
        List<Employee> employees = employeeService.findAll();
        employeeTableModel.clear();
        employeeTableModel.addEntities(employees);
    }

    private void showClientsFrame() {
        employeesFrame.setVisible(true);
    }

    private void importEmployees() {
        System.out.println("importEmployees");
    }

}
