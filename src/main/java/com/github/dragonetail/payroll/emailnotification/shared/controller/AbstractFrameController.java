package com.github.dragonetail.payroll.emailnotification.shared.controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class AbstractFrameController {

    public abstract void prepareAndOpenFrame();

    public abstract Container getContentPane();

    protected void registerAction(JButton button, ActionListener listener) {
        button.addActionListener(listener);
    }

}
