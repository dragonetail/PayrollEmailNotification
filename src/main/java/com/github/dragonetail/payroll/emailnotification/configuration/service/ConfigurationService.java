package com.github.dragonetail.payroll.emailnotification.configuration.service;

import com.github.dragonetail.payroll.emailnotification.configuration.bean.Configuration;
import com.github.dragonetail.payroll.emailnotification.util.DateFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

@Service
public class ConfigurationService {
    private static Logger logger = LoggerFactory.getLogger(ConfigurationService.class);
    private Configuration configuration = new Configuration();

    private JavaMailSender javaMailSender;

    @Autowired
    public ConfigurationService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @PostConstruct
    public void init() {
        Date referenceDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(referenceDate);
        calendar.add(Calendar.MONTH, -1);
        configuration.setPayrollMonth(DateFormatter.formatMonth(calendar.getTime()));

        saveConfiguration(configuration);
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void saveConfiguration(Configuration configuration) {
        this.configuration = configuration;

        JavaMailSenderImpl mailSender = (JavaMailSenderImpl) javaMailSender;
        mailSender.setHost(configuration.getSmtpHost());
        mailSender.setPort(configuration.getSmtpPort());

        mailSender.setUsername(configuration.getSmtpUsername());
        mailSender.setPassword(configuration.getSmtpPassword());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", configuration.isSmtpAuth());
        props.put("mail.smtp.starttls.enable", "false");
        props.put("mail.debug", "true");
    }
}
