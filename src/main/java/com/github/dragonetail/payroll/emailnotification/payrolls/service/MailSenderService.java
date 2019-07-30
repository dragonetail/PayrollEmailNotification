package com.github.dragonetail.payroll.emailnotification.payrolls.service;

import com.github.dragonetail.payroll.emailnotification.payrolls.bean.Payroll;
import com.github.dragonetail.payroll.emailnotification.util.DateFormatter;
import com.github.dragonetail.payroll.emailnotification.util.HtmlToPdf;
import com.github.dragonetail.payroll.emailnotification.util.MessageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

@Service
public class MailSenderService {
    private static Logger logger = LoggerFactory.getLogger(MailSenderService.class);

    private JavaMailSender javaMailSender;
    private TemplateEngine emailTemplateEngine;

    @Autowired
    public MailSenderService(JavaMailSender javaMailSender, TemplateEngine emailTemplateEngine) {
        this.javaMailSender = javaMailSender;
        this.emailTemplateEngine = emailTemplateEngine;
    }

    public void sendTemplateMail(Payroll payroll, String fromAddress,String toAddress, String userPassword, String ownerPassword,  String payrollMonth) throws Exception {
        String title = MessageUtils.getMessage("payrolls.title");

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        helper.setFrom(fromAddress);
        helper.setBcc(fromAddress);
        helper.setTo(toAddress);
        helper.setSubject(payrollMonth + title+ "-" + payroll.getName() );

        Context context = new Context();
        context.setVariable("payroll", payroll);
        context.setVariable("payrollMonth", payrollMonth);
        context.setVariable("today", DateFormatter.formatDate(new Date()));

        String body =  emailTemplateEngine.process("mail/body", context);

        helper.setText(body, true);
        logger.info(body);


        File attachedPdf = File.createTempFile("payroll", ".pdf");
        String attachedContent =  emailTemplateEngine.process("mail/attached", context);
        logger.info(attachedContent);

        HtmlToPdf.htmo2Pdf(attachedContent, attachedPdf, userPassword, ownerPassword);

        FileSystemResource file
                = new FileSystemResource(attachedPdf);
        helper.addAttachment(title + "-" + payroll.getName() + ".pdf", file, "application/pdf");

        javaMailSender.send(mimeMessage);

        logger.info("Mail sent to: " + toAddress);
    }

}
