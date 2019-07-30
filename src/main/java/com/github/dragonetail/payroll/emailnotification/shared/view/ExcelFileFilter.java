package com.github.dragonetail.payroll.emailnotification.shared.view;


import javax.swing.filechooser.FileFilter;
import java.io.File;

public class ExcelFileFilter extends FileFilter {
    public final static String xls = "xls";
    public final static String xlsx = "xlsx";

    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = getExtension(f);
        if (extension != null) {
            if (extension.equals(ExcelFileFilter.xls) ||
                    extension.equals(ExcelFileFilter.xlsx) ) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    public String getDescription() {
        return "Excel File(xls, xlsx)";
    }

    private  String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }
}