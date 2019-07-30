package com.github.dragonetail.payroll.emailnotification.main.controller;

import com.github.dragonetail.payroll.emailnotification.employees.controller.EmployeesController;
import com.github.dragonetail.payroll.emailnotification.main.view.MainMenuFrame;
import com.github.dragonetail.payroll.emailnotification.payrolls.controller.PayrollsController;
import com.github.dragonetail.payroll.emailnotification.shared.controller.AbstractFrameController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.awt.*;

@Controller
public class MainMenuController extends AbstractFrameController {

    private MainMenuFrame mainMenuFrame;


    @Autowired
    public MainMenuController(MainMenuFrame mainMenuFrame) {
        this.mainMenuFrame = mainMenuFrame;
    }

    public void prepareAndOpenFrame() {
        mainMenuFrame.setVisible(true);
    }

    public Container getContentPane(){
        return mainMenuFrame.getContentPane();
    }
}
