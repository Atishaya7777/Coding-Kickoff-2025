package com.coding_kickoff.asciiquest;

/**
 * Represents a monster enemy in the game
 */
public class Monster {
    private Position position;
    private int health;
    private int damage;
    private boolean alive;

    public Monster(int x, int y) {
        this.position = new Position(x, y);
        this.health = Utils.randomInt(20, 40);
        this.damage = Utils.randomInt(10, 25);
        this.alive = true;
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

    public void setPosition(int x, int y) {
        position.setPosition(x, y);
    }

    public void move(int deltaX, int deltaY) {
        position.setX(position.getX() + deltaX);
        position.setY(position.getY() + deltaY);
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            health = 0;
            alive = false;
        }
    }

    public boolean isAlive() {
        return alive;
    }

    public void die() {
        alive = false;
    }

    public char getSymbol() {
        return 'M';
    }

    /**
     * Make the monster move towards the player
     * 
     * @param playerX     Player's X coordinate
     * @param playerY     Player's Y coordinate
     * @param boardWidth  Board width for bounds checking
     * @param boardHeight Board height for bounds checking
     */
    public void moveTowardsPlayer(int playerX, int playerY, int boardWidth, int boardHeight) {
        if (!alive)
            return;

        // Simple AI: move one step towards player. This kinda AI will most certianly
        // not take your job.
        int deltaX = 0;
        int deltaY = 0;

        if (playerX > getX()) {
            deltaX = 1;
        } else if (playerX < getX()) {
            deltaX = -1;
        }

        if (playerY > getY()) {
            deltaY = 1;
        } else if (playerY < getY()) {
            deltaY = -1;
        }

        int newX = getX() + deltaX;
        int newY = getY() + deltaY;

        if (Utils.isInBounds(newX, newY, boardWidth, boardHeight)) {
            move(deltaX, deltaY);
        }
    }

    /**
     * Attack the player if adjacent
     * 
     * @param player The player to attack
     * @return True if attack was successful
     */
    public boolean attackPlayer(Player player) {
        if (!alive || !player.isAlive())
            return false;

        // Check if monster is adjacent to player. Could be one of the eight squares
        // adjacent to a player. The counting is left as an exercise to the reader.
        int distX = Math.abs(getX() - player.getX());
        int distY = Math.abs(getY() - player.getY());

        if (distX <= 1 && distY <= 1 && (distX + distY) > 0) {
            player.takeDamage(damage);
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "Monster{" +
                "position=" + position +
                ", health=" + health +
                ", damage=" + damage +
                ", alive=" + alive +
                '}';
    }
}