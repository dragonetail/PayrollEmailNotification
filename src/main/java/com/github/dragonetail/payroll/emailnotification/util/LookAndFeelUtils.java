package com.github.dragonetail.payroll.emailnotification.util;

import javax.swing.*;

public class LookAndFeelUtils {

    public static void setWindowsLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null,
                    MessageUtils.getMessage("messages.getSystemLookAndFeelClassName")+ e,
                    MessageUtils.getMessage("messages.alert.title"),
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
