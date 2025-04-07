package modmate.log;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * A logging utility that wraps around java.util.logging.Logger.
 * Provides methods for logging messages at different levels (info, warning, severe).
 * All logs are stored in a single file chronologically.
 */
public class LogUtil {
    private static final String LOG_FILE = "logs/log.txt"; // Updated to a simpler relative path
    private static FileHandler sharedFileHandler; // Shared FileHandler for all LogUtil instances
    private final Logger logger;

    private static synchronized FileHandler getSharedFileHandler() {
        if (sharedFileHandler == null) {
            try {
                Path logFilePath = Paths.get(LOG_FILE);
                Files.createDirectories(logFilePath.getParent());

                sharedFileHandler = new FileHandler(LOG_FILE, true);
                sharedFileHandler.setFormatter(new SimpleFormatter());
            } catch (IOException e) {
                System.err.println("Failed to initialize shared FileHandler for logging: " + e.getMessage());
            }
        }
        return sharedFileHandler;
    }

    /**
     * Constructs a Log instance for the given class.
     *
     * @param clazz The class for which the logger is created.
     */
    public LogUtil(Class<?> clazz) {
        this.logger = Logger.getLogger(clazz.getCanonicalName());

        FileHandler handler = getSharedFileHandler();
        if (handler != null) {
            this.logger.addHandler(handler);
        }

        this.logger.setUseParentHandlers(false); // Disable console logging by default
    }

    /**
     * Logs an informational message.
     *
     * @param message The message to log.
     */
    public void info(String message) {
        logger.log(Level.INFO, message);
    }

    /**
     * Logs a warning message.
     *
     * @param message The message to log.
     */
    public void warning(String message) {
        logger.log(Level.WARNING, message);
    }

    /**
     * Logs a severe error message.
     *
     * @param message The message to log.
     */
    public void severe(String message) {
        logger.log(Level.SEVERE, message);
    }
}
