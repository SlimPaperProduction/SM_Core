package org.Smok3ALot.SM_Core.Logging;

import org.Smok3ALot.SM_Core.SM_Core;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ErrorHandler {

    private SM_Core core;
    private final Logger logger;

    public ErrorHandler(SM_Core core, Logger logger) {
        this.core = core;
        this.logger = logger;
    }

    // Allgemeine Log-Nachricht
    public void logInfo(String module, String message) {
        logger.log(Level.INFO, format(module, message));
    }

    // Warnung
    public void logWarning(String module, String message) {
        logger.log(Level.WARNING, format(module, message));
    }

    // Fehler mit Exception
    public void logError(String module, String message, Throwable throwable) {
        logger.log(Level.SEVERE, format(module, message), throwable);
    }

    // Nur Fehlertext
    public void logError(String module, String message) {
        logger.log(Level.SEVERE, format(module, message));
    }

    // Formatierung mit Zeitstempel
    private String format(String module, String message) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return "[" + timestamp + "][" + module + "] " + message;
    }
}
