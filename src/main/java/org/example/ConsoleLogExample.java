package org.example;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsoleLogExample {

    private static final Logger logger = Logger.getLogger(ConsoleLogExample.class.getName());

    public static void main(String[] args) {
        // System.out.println(): Prints the string to the console, followed by a new line.
        System.out.println("Hello from System.out.println()");

        // System.out.print(): Prints the string to the console without a new line.
        // Multiple calls to System.out.print() will print on the same line.
        System.out.print("This is from System.out.print(), part 1. ");
        System.out.print("This is from System.out.print(), part 2.\n");

        // System.err.println(): Prints the string to the standard error stream, followed by a new line.
        // Typically used for error messages.
        System.err.println("This is an error message from System.err.println()");

        // Using java.util.logging.Logger: A more configurable way to log messages.
        // Different log levels (INFO, WARNING, SEVERE, etc.) can be used.
        logger.info("This is an informational message from the logger.");
        logger.warning("This is a warning message from the logger.");
        logger.log(Level.SEVERE, "This is a severe message from the logger.");
    }
}
