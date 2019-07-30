package com.github.dragonetail.payroll.emailnotification.payrolls.view;

import com.github.dragonetail.payroll.emailnotification.payrolls.model.PayrollTableModel;
import com.github.dragonetail.payroll.emailnotification.shared.view.FixedColumnTable;
import com.github.dragonetail.payroll.emailnotification.util.JTableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class PayrollTablePanel extends JPanel {

    private PayrollTableModel tableModel;

    private JTable table;

    @Autowired
    PayrollTablePanel(PayrollTableModel tableModel) {
        this.tableModel = tableModel;

        setPanelUp();
        initComponents();
    }

    private void setPanelUp() {
        setLayout(new BorderLayout());
    }

    private void initComponents() {
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        JTableUtils.setColumnWidths(table,
                30,
                80,
                60,
                80,//
                80,//
                80,//部门
                40,//级别
                80,//基本工资
                80,//职位工资
                80,//评价工资
                80,//其他应发
                80,//社会保险
                80,//住房公积金
                80,//专项附加扣除
                80,//请假
                80,//其他应扣
                80,//税前应付
                80,//个人所得税
                80,//实发金额
                160//备注
        );

        JScrollPane paneWithTable = new JScrollPane(table);
        new FixedColumnTable(4, paneWithTable);
        add(paneWithTable, BorderLayout.CENTER);
    }
}
