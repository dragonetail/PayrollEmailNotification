package com.github.dragonetail.payroll.emailnotification.util;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class JTableUtils {

    public static void setColumnWidths(JTable table, int... widths) {
        TableColumnModel columnModel = table.getColumnModel();
        for (int i = 0; i < widths.length; i++) {
            if (i < columnModel.getColumnCount()) {
                TableColumn modelColumn = columnModel.getColumn(i);
                modelColumn.setResizable(true);
                modelColumn.setMinWidth(widths[i]);
                modelColumn.setPreferredWidth(widths[i]);
            } else break;
        }
    }
}
