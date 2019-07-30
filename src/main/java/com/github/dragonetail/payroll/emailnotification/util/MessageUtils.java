package com.github.dragonetail.payroll.emailnotification.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class MessageUtils {
    private static Logger logger = LoggerFactory.getLogger(MessageUtils.class);

    public static String getMessage(String result, Object... params) {
        ReloadableResourceBundleMessageSource messageSource;
        messageSource = new ReloadableResourceBundleMessageSource();
        //messageSource.setCacheSeconds(-1);
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
        messageSource.setBasenames("/i18n/messages");

        String message = "";
        try {
            Locale locale = LocaleContextHolder.getLocale();
            message = messageSource.getMessage(result, params, locale);
        } catch (Exception e) {
            logger.error("parse message error! ", e);
            return "NOT-FOUND-MESSAGE";
        }
        return message;
    }
}
