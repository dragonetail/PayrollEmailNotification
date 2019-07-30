package com.github.dragonetail.payroll.emailnotification.configuration.controller;

import com.github.dragonetail.payroll.emailnotification.configuration.bean.Configuration;
import com.github.dragonetail.payroll.emailnotification.configuration.service.ConfigurationService;
import com.github.dragonetail.payroll.emailnotification.configuration.view.ConfigurationBtnPanel;
import com.github.dragonetail.payroll.emailnotification.configuration.view.ConfigurationFrame;
import com.github.dragonetail.payroll.emailnotification.shared.controller.AbstractFrameController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.awt.*;

@Controller
public class ConfigurationController extends AbstractFrameController {
    private static Logger logger = LoggerFactory.getLogger(ConfigurationController.class);
    private ConfigurationFrame configurationFrame;
    private ConfigurationService configurationService;

    @Autowired
    public ConfigurationController(ConfigurationFrame configurationFrame,
                                   ConfigurationService configurationService) {
        this.configurationFrame = configurationFrame;
        this.configurationService = configurationService;
    }

    @PostConstruct
    private void prepareListeners() {
        ConfigurationBtnPanel formBtnPanel = configurationFrame.getFormBtnPanel();

        registerAction(formBtnPanel.getSaveBtn(), (e) -> saveEntity());
        registerAction(formBtnPanel.getCancelBtn(), (e) -> closeModalWindow());
    }

    @Override
    public void prepareAndOpenFrame() {
        configurationFrame.getFormPanel().resetForm(configurationService.getConfiguration());
        showFrame();
    }

    public Container getContentPane() {
        throw new IllegalStateException("不正确的访问。");
    }

    private void showFrame() {
        configurationFrame.setVisible(true);
    }

    private void saveEntity() {
        Configuration configuration = configurationFrame.getFormPanel().getEntityFromForm();

        //TODO check error
//            Notifications.showFormValidationAlert(validationError.getMessage());

        configurationService.saveConfiguration(configuration);
        closeModalWindow();
    }

    private void closeModalWindow() {
        configurationFrame.dispose();
    }

}
