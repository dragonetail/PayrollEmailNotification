package pl.dmichalski.reservations.business.ui.payrolls.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.dmichalski.reservations.business.util.ConstMessagesEN;

import javax.swing.*;
import java.awt.*;

@Component
public class PayrollsFrame extends JFrame {

    private static final int DEFAULT_WIDTH = 750;
    private static final int DEFAULT_HEIGHT = 340;

    private PayrollTablePanel tablePanel;
    private PayrollTableBtnPanel tableBtnPanel;

    @Autowired
    public PayrollsFrame(PayrollTablePanel tablePanel,
                         PayrollTableBtnPanel tableBtnPanel) {
        this.tablePanel = tablePanel;
        this.tableBtnPanel = tableBtnPanel;

        setFrameUp();
        initComponents();
    }

    private void setFrameUp() {
        setTitle(ConstMessagesEN.Labels.CLIENTS);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void initComponents() {
        add(tablePanel, BorderLayout.CENTER);
        add(tableBtnPanel, BorderLayout.SOUTH);
    }

    public PayrollTablePanel getTablePanel() {
        return tablePanel;
    }

    public PayrollTableBtnPanel getTableBtnPanel() {
        return tableBtnPanel;
    }

}
