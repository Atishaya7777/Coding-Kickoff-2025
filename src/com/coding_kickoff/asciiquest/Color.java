package com.coding_kickoff.asciiquest;

/**
 * Color utility class for terminal text coloring
 * Updated version with ANSI color codes enabled
 */
public class Color {
    // ANSI Color Codes. ANSI: https://en.wikipedia.org/wiki/ANSI_escape_code, you
    // should familiarize yourself with this. It's pretty interesting stuff.
    public static final String RESET = "\033[0m";
    public static final String BLACK = "\033[30m";
    public static final String RED = "\033[31m";
    public static final String GREEN = "\033[32m";
    public static final String YELLOW = "\033[33m";
    public static final String BLUE = "\033[34m";
    public static final String PURPLE = "\033[35m";
    public static final String CYAN = "\033[36m";
    public static final String WHITE = "\033[37m";

    public static final String BRIGHT_BLACK = "\033[90m";
    public static final String BRIGHT_RED = "\033[91m";
    public static final String BRIGHT_GREEN = "\033[92m";
    public static final String BRIGHT_YELLOW = "\033[93m";
    public static final String BRIGHT_BLUE = "\033[94m";
    public static final String BRIGHT_PURPLE = "\033[95m";
    public static final String BRIGHT_CYAN = "\033[96m";
    public static final String BRIGHT_WHITE = "\033[97m";

    public static final String BG_BLACK = "\033[40m";
    public static final String BG_RED = "\033[41m";
    public static final String BG_GREEN = "\033[42m";
    public static final String BG_YELLOW = "\033[43m";
    public static final String BG_BLUE = "\033[44m";
    public static final String BG_PURPLE = "\033[45m";
    public static final String BG_CYAN = "\033[46m";
    public static final String BG_WHITE = "\033[47m";

    // Text styles
    public static final String BOLD = "\033[1m";
    public static final String DIM = "\033[2m";
    public static final String UNDERLINE = "\033[4m";
    public static final String BLINK = "\033[5m";
    public static final String REVERSE = "\033[7m";

    /**
     * Colorize text with the given color
     * 
     * @param text  The text to colorize
     * @param color The color code
     * @return The colorized text with reset at the end
     */
    public static String colorize(String text, String color) {
        return color + text + RESET;
    }

    /**
     * Apply multiple styles to text
     * 
     * @param text   The text to style
     * @param styles Variable number of style codes
     * @return The styled text with reset at the end
     */
    public static String style(String text, String... styles) {
        StringBuilder styled = new StringBuilder();
        for (String style : styles) {
            styled.append(style);
        }
        styled.append(text).append(RESET);
        return styled.toString();
    }

    /**
     * Reset color formatting
     * 
     * @return ANSI reset code
     */
    public static String reset() {
        return RESET;
    }

    // Convenience methods for common game elements
    public static String player(String text) {
        return colorize(text, BRIGHT_BLUE + BOLD);
    }

    public static String monster(String text) {
        return colorize(text, BRIGHT_RED + BOLD);
    }

    public static String treasure(String text) {
        return colorize(text, BRIGHT_YELLOW + BOLD);
    }

    public static String wall(String text) {
        return colorize(text, WHITE);
    }

    public static String floor(String text) {
        return colorize(text, DIM + WHITE);
    }

    public static String health(String text) {
        return colorize(text, GREEN);
    }

    public static String lowHealth(String text) {
        return colorize(text, RED);
    }

    public static String score(String text) {
        return colorize(text, CYAN);
    }
}