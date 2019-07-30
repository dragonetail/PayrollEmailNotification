package com.github.dragonetail.payroll.emailnotification.util;

import com.itextpdf.text.pdf.BaseFont;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfWriter;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.pdf.PDFEncryption;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class HtmlToPdf {

    public static void htmo2Pdf(String content, File output, String userPassword, String ownerPassword) throws DocumentException, IOException {
        ITextRenderer renderer = new ITextRenderer();
        renderer.setPDFEncryption(new PDFEncryption(userPassword.getBytes(), ownerPassword.getBytes(),
                PdfWriter.ALLOW_SCREENREADERS));

        ITextFontResolver fontResolver = renderer.getFontResolver();
        //设置字体，否则不支持中文,在html中使用字体，html{ font-family: SimSun;}
        fontResolver.addFont("fonts/SimSun.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        renderer.setDocumentFromString(content);
        renderer.layout();
        renderer.createPDF(new FileOutputStream(output));
    }

}