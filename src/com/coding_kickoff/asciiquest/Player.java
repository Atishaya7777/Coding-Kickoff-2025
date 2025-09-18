package com.coding_kickoff.asciiquest;

/**
 * Represents the player character in the game
 */
public class Player {
    private Position position;
    private int health;
    private int maxHealth;
    private int score;
    private boolean alive;

    public Player(int startX, int startY) {
        this.position = new Position(startX, startY);
        this.maxHealth = 100;
        this.health = maxHealth;
        this.score = 0;
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

    public int getMaxHealth() {
        return maxHealth;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            health = 0;
            alive = false;
        }
    }

    public void heal(int amount) {
        health += amount;
        if (health > maxHealth) {
            health = maxHealth;
        }
    }

    public int getScore() {
        return score;
    }

    public void addScore(int points) {
        score += points;
    }

    public boolean isAlive() {
        return alive;
    }

    public void die() {
        alive = false;
        health = 0;
    }

    public char getSymbol() {
        return '@';
    }

    @Override
    public String toString() {
        return "Player{" +
                "position=" + position +
                ", health=" + health + "/" + maxHealth +
                ", score=" + score +
                ", alive=" + alive +
                '}';
    }
}