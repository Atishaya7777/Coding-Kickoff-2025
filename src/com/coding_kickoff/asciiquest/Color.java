package com.coding_kickoff.asciiquest;

/**
 * Color utility class for terminal text coloring
 * Since this is the non-color branch, all color methods return empty strings
 */
public class Color {
    // ANSI Color Codes (disabled for non-color version)
    public static final String RESET = "";
    public static final String BLACK = "";
    public static final String RED = "";
    public static final String GREEN = "";
    public static final String YELLOW = "";
    public static final String BLUE = "";
    public static final String PURPLE = "";
    public static final String CYAN = "";
    public static final String WHITE = "";
    
    // Bright colors - Have fun with it! :)
    public static final String BRIGHT_BLACK = "";
    public static final String BRIGHT_RED = "";
    public static final String BRIGHT_GREEN = "";
    public static final String BRIGHT_YELLOW = "";
    public static final String BRIGHT_BLUE = "";
    public static final String BRIGHT_PURPLE = "";
    public static final String BRIGHT_CYAN = "";
    public static final String BRIGHT_WHITE = "";
    
    /**
     * Colorize text with the given color (returns plain text in non-color version)
     * @param text The text to colorize
     * @param color The color code
     * @return The plain text without color formatting
     */
    public static String colorize(String text, String color) {
        return text;
    }
    
    /**
     * Reset color formatting (no-op in non-color version)
     * @return Empty string
     */
    public static String reset() {
        return "";
    }
}