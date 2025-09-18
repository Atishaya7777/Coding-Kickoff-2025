package com.coding_kickoff.asciiquest;

/**
 * Represents treasure items that the player can collect
 */
public class Treasure {
    private Position position;
    private int value;
    private boolean collected;

    public Treasure(int x, int y) {
        this.position = new Position(x, y);
        this.value = Utils.randomInt(10, 50);
        this.collected = false;
    }

    public Treasure(int x, int y, int value) {
        this.position = new Position(x, y);
        this.value = value;
        this.collected = false;
    }

    public Position getPosition() {
        return position;
    }

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }

    public int getValue() {
        return value;
    }

    public boolean isCollected() {
        return collected;
    }

    public void collect() {
        collected = true;
    }

    public char getSymbol() {
        return '$';
    }

    /**
     * Check if player is on the same position as this treasure
     * 
     * @param player The player to check
     * @return True if player can collect this treasure
     */
    public boolean canBeCollectedBy(Player player) {
        return !collected && position.equals(player.getPosition());
    }

    /**
     * Attempt to collect this treasure with the given player
     * 
     * @param player The player collecting the treasure
     * @return True if treasure was collected
     */
    public boolean collectBy(Player player) {
        if (canBeCollectedBy(player)) {
            collected = true;
            player.addScore(value);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Treasure{" +
                "position=" + position +
                ", value=" + value +
                ", collected=" + collected +
                '}';
    }
}