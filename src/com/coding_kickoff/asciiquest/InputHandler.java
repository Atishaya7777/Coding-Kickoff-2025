package com.coding_kickoff.asciiquest;

import java.util.Scanner;

/**
 * Handles user input for the game
 */
public class InputHandler {
    private Scanner scanner;

    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Get the next command from the user
     * 
     * @return The user's input as a string, trimmed and in lowercase
     */
    public String getCommand() {
        System.out.print("Enter command (w/a/s/d to move, q to quit): ");
        return scanner.nextLine().trim().toLowerCase();
    }

    /**
     * Get movement deltas based on user input
     * 
     * @param command The command string
     * @return An array with [deltaX, deltaY], or null if invalid command
     */
    public int[] getMovement(String command) {
        switch (command) {
            case "w":
            case "up":
                return new int[] { 0, -1 };
            case "s":
            case "down":
                return new int[] { 0, 1 };
            case "a":
            case "left":
                return new int[] { -1, 0 };
            case "d":
            case "right":
                return new int[] { 1, 0 };
            default:
                return null;
        }
    }

    /**
     * Check if the command is a quit command
     * 
     * @param command The command to check
     * @return True if it's a quit command
     */
    public boolean isQuitCommand(String command) {
        return command.equals("q") || command.equals("quit") || command.equals("exit");
    }

    /**
     * Wait for the user to press Enter
     */
    public void waitForEnter() {
        System.out.print("Press Enter to continue...");
        scanner.nextLine();
    }

    /**
     * Ask user if they want to play again
     * 
     * @return True if user wants to play again
     */
    public boolean askPlayAgain() {
        System.out.print("Do you want to play again? (y/n): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("y") || response.equals("yes");
    }

    /**
     * Close the scanner
     */
    public void close() {
        scanner.close();
    }
}