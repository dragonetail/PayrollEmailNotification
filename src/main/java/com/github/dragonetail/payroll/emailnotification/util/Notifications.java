package com.github.dragonetail.payroll.emailnotification.util;

import javax.swing.*;

public class Notifications {

    public static void showFormValidationAlert(String message) {
        JOptionPane.showMessageDialog(null,
                message,
                MessageUtils.getMessage("messages.information.title"),
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void showFailedToImportEmployees(String message) {
        JOptionPane.showMessageDialog(null,
                MessageUtils.getMessage("messages.failedToImportEmployees") +  message,
                MessageUtils.getMessage("messages.alert.title"),
                JOptionPane.ERROR_MESSAGE);
    }

    public static void showFailedToImportPayrolls(String message) {
        JOptionPane.showMessageDialog(null,
                MessageUtils.getMessage("messages.failedToImportPayrolls") + message,
                MessageUtils.getMessage("messages.alert.title"),
                JOptionPane.ERROR_MESSAGE);
    }
}
