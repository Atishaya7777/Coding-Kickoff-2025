package com.coding_kickoff.asciiquest;

import java.util.Random;

/**
 * Utility class with helper methods for the game
 */
public class Utils {
    private static final Random random = new Random();

    /**
     * Clear the console screen
     */
    public static void clearScreen() {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); // This is a good way to start
                                                                                      // doing COMP 3430:
                                                                                      // https://www.baeldung.com/java-lang-processbuilder-api
                                                                                      // (Not really, don't do as I say,
                                                                                      // do as I do...is that really how
                                                                                      // the saying goes?)
            } else {
                // Unix-based systems
                System.out.print("\033[2J\033[H"); // Wtf? Weird escape code shenanigans are happening here... I
                                                   // definitely didn't google stackoverflow for these codes.
                System.out.flush();
            }
        } catch (Exception e) {
            // Fallback: print multiple newlines; Stupid but works eh?
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }

    /**
     * Generate a random integer between min and max (inclusive)
     * 
     * @param min Minimum value
     * @param max Maximum value
     * @return Random integer in range
     */
    public static int randomInt(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * Generate a random boolean
     * 
     * @return Random boolean value
     */
    public static boolean randomBoolean() {
        return random.nextBoolean();
    }

    /**
     * Generate a random boolean with specified probability
     * 
     * @param probability Probability of returning true (0.0 to 1.0)
     * @return Random boolean based on probability
     */
    public static boolean randomBoolean(double probability) {
        return random.nextDouble() < probability;
    }

    /**
     * Pause execution for specified milliseconds
     * 
     * @param milliseconds Time to sleep
     */
    public static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds); // Multi-threaded game hehe. (Not really. :(((( Someday tho??? :eyes)
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Check if a position is within bounds of the board
     * 
     * @param x      X coordinate
     * @param y      Y coordinate
     * @param width  Board width
     * @param height Board height
     * @return True if position is within bounds
     */
    public static boolean isInBounds(int x, int y, int width, int height) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }
}