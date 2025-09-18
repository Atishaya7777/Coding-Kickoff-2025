package com.coding_kickoff.asciiquest;

/**
 * Main application entry point for AsciiQuest
 */
public class App {

    public static void main(String[] args) {
        try {
            Game game = new Game();
            game.run();

        } catch (Exception e) {
            System.err.println("ERROR: An error occurred while running the game:");
            e.printStackTrace();

            System.out.println("\nGame crashed! Please try running again.");
            System.out.println(
                    "If the problem persists, check your terminal settings. Not that ti's really going to help you...");
        }
    }
}