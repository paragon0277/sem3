package se.kth.iv1350.progExe.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Handles the logging of exceptions to a file.
 */
public class LogHandler {
    private static final String FILE_PATH = "POS-log.txt";
    private PrintWriter logWriter;

    /**
     * Initializes a new instance of the LogHandler.
     * Opens the log file for writing. If the file does not exist, it will be created.
     */
    public LogHandler() {
        try {
            logWriter = new PrintWriter(new FileWriter(FILE_PATH, true), true);
        } catch (IOException e) {
            System.out.println("Could not initialize log writer.");
            e.printStackTrace();
        }
    }

    /**
     * Writes a log entry describing a thrown exception.
     *
     * @param exception The exception to be logged.
     */
    public void logException(Exception exception) {
        StringBuilder logMessage = new StringBuilder();
        logMessage.append(getCurrentTime());
        logMessage.append(", Exception occurred: ");
        logMessage.append(exception.getMessage());
        logWriter.println(logMessage);
        exception.printStackTrace(logWriter);
        logWriter.println();
    }

    /**
     * Gets the current time formatted as a string.
     *
     * @return The current time formatted as a string.
     */
    private String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return now.format(dateTimeFormatter);
    }
}
