package com.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

/**
 * @author Juan Cubillos
 * @since 2020-04-16
 */

@Component
public class LogBack {

    private static final Logger logger = LoggerFactory.getLogger(LogBack.class);

    public void addLog(Integer type, String nameClass, String nameMethod, String message) {
        final int INFO_MESSAGE = 1;
        String formatMessage = MessageFormat.format("[LOG_INFO][{0}][{1}] {2}", nameClass, nameMethod, message);
        if (type.equals(INFO_MESSAGE)) {
            if (logger.isInfoEnabled()) {
                logger.info(formatMessage);
            }
        } else {
            if (logger.isErrorEnabled()) {
                logger.error(formatMessage);
            }
        }
    }
}
