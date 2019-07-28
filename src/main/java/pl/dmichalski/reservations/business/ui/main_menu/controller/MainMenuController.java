package pl.dmichalski.reservations.business.ui.main_menu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.dmichalski.reservations.business.ui.employees.controller.EmployeesController;
import pl.dmichalski.reservations.business.ui.forms.forms.controller.FormsController;
import pl.dmichalski.reservations.business.ui.main_menu.view.MainMenuFrame;
import pl.dmichalski.reservations.business.ui.payrolls.controller.PayrollsController;
import pl.dmichalski.reservations.business.ui.reports.reports.controller.ReportsController;
import pl.dmichalski.reservations.business.ui.shared.controller.AbstractFrameController;

@Controller
public class MainMenuController extends AbstractFrameController {

    private MainMenuFrame mainMenuFrame;
    private FormsController formsController;
    private ReportsController reportsController;
    private EmployeesController employeesController;
    private PayrollsController payrollsController;


    @Autowired
    public MainMenuController(MainMenuFrame mainMenuFrame,
                              FormsController formsController,
                              ReportsController reportsController,
                              EmployeesController employeesController,
                              PayrollsController payrollsController) {
        this.mainMenuFrame = mainMenuFrame;
        this.formsController = formsController;
        this.reportsController = reportsController;
        this.employeesController = employeesController;
        this.payrollsController = payrollsController;
    }

    public void prepareAndOpenFrame() {
        registerAction(mainMenuFrame.getFormsBtn(), (e) -> openFormsWindow());
        registerAction(mainMenuFrame.getReportsBtn(), (e) -> openReportsWindow());
        registerAction(mainMenuFrame.getEmployeesBtn(), (e) -> openEmployeesWindow());
        registerAction(mainMenuFrame.getPayrollsBtn(), (e) -> openPayrollsWindow());
        mainMenuFrame.setVisible(true);
    }

    private void openFormsWindow() {
        formsController.prepareAndOpenFrame();
    }

    private void openReportsWindow() {
        reportsController.prepareAndOpenFrame();
    }

    private void openEmployeesWindow() {
        employeesController.prepareAndOpenFrame();
    }

    private void openPayrollsWindow() {
        payrollsController.prepareAndOpenFrame();
    }


}
