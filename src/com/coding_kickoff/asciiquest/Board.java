package com.coding_kickoff.asciiquest;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the game board and handles rendering
 */
public class Board {
    private int width;
    private int height;
    private char[][] grid;
    private Player player;
    private List<Monster> monsters;
    private List<Treasure> treasures;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new char[height][width];
        this.monsters = new ArrayList<>();
        this.treasures = new ArrayList<>();
        initializeGrid();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addMonster(Monster monster) {
        monsters.add(monster);
    }

    public void addTreasure(Treasure treasure) {
        treasures.add(treasure);
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public List<Treasure> getTreasures() {
        return treasures;
    }

    /**
     * Check if a position is valid for movement (not a wall)
     * 
     * @param x X coordinate
     * @param y Y coordinate
     * @return True if position is valid for movement
     */
    // This style of commenting is called JavaDoc, you should always try to do this:
    // https://docs.oracle.com/javase/8/docs/technotes/tools/windows/javadoc.html
    public boolean isValidPosition(int x, int y) {
        if (!Utils.isInBounds(x, y, width, height)) {
            return false;
        }
        return grid[y][x] != '#';
    }

    /**
     * Check if there's a monster at the given position
     * 
     * @param x X coordinate
     * @param y Y coordinate
     * @return The monster at the position, or null if none
     */
    public Monster getMonsterAt(int x, int y) {
        for (Monster monster : monsters) {
            if (monster.isAlive() && monster.getX() == x && monster.getY() == y) {
                return monster;
            }
        }
        return null;
    }

    /**
     * Remove dead monsters from the board
     */
    public void removeDeadMonsters() {
        monsters.removeIf(monster -> !monster.isAlive());
    }

    /**
     * Remove collected treasures from the board
     */
    public void removeCollectedTreasures() {
        treasures.removeIf(Treasure::isCollected);
    }

    /**
     * Render the current state of the board
     */
    public void render() {
        char[][] renderGrid = new char[height][width];

        for (int y = 0; y < height; y++) {
            System.arraycopy(grid[y], 0, renderGrid[y], 0, width);
        }

        for (Treasure treasure : treasures) {
            if (!treasure.isCollected()) {
                renderGrid[treasure.getY()][treasure.getX()] = treasure.getSymbol();
            }
        }

        for (Monster monster : monsters) {
            if (monster.isAlive()) {
                renderGrid[monster.getY()][monster.getX()] = monster.getSymbol();
            }
        }

        if (player != null && player.isAlive()) {
            renderGrid[player.getY()][player.getX()] = player.getSymbol();
        }

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                char cell = renderGrid[y][x];
                String coloredCell;

                switch (cell) {
                    case '@': // Player
                        coloredCell = Color.player(String.valueOf(cell));
                        break;
                    case 'M': // Monster
                        coloredCell = Color.monster(String.valueOf(cell));
                        break;
                    case '$': // Treasure
                        coloredCell = Color.treasure(String.valueOf(cell));
                        break;
                    case '#': // Wall
                        coloredCell = Color.wall(String.valueOf(cell));
                        break;
                    case '.': // Floor
                        coloredCell = Color.floor(String.valueOf(cell));
                        break;
                    default:
                        coloredCell = String.valueOf(cell);
                        break;
                }

                System.out.print(coloredCell);
            }
            System.out.println();
        }
    }

    /**
     * Print game statistics
     */
    public void printStats() {
        if (player != null) {
            System.out.println("Health: " + player.getHealth() + "/" + player.getMaxHealth() +
                    " | Score: " + player.getScore() +
                    " | Treasures left: " + getTreasureCount() +
                    " | Monsters left: " + getAliveMonsterCount());
        }
    }

    public int getTreasureCount() {
        return (int) treasures.stream().filter(t -> !t.isCollected()).count();
    }

    public int getAliveMonsterCount() {
        return (int) monsters.stream().filter(Monster::isAlive).count();
    }

    /**
     * Generate random empty position for spawning entities
     * 
     * @return A position that's empty and valid
     */
    public Position getRandomEmptyPosition() {
        int attempts = 0;
        int maxAttempts = 100;

        while (attempts < maxAttempts) {
            int x = Utils.randomInt(1, width - 2);
            int y = Utils.randomInt(1, height - 2);

            if (isValidPosition(x, y) && isPositionEmpty(x, y)) {
                return new Position(x, y);
            }
            attempts++;
        }

        // Fallback to center if no empty position found
        return new Position(width / 2, height / 2);
    }

    private void initializeGrid() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = '.';
            }
        }

        // DOCTORS WITHOUT BORDERS
        for (int x = 0; x < width; x++) {
            grid[0][x] = '#'; // Top border
            grid[height - 1][x] = '#'; // Bottom border
        }
        for (int y = 0; y < height; y++) {
            grid[y][0] = '#'; // Left border
            grid[y][width - 1] = '#'; // Right border
        }
    }

    private boolean isPositionEmpty(int x, int y) {
        if (player != null && player.getX() == x && player.getY() == y) {
            return false;
        }

        for (Monster monster : monsters) {
            if (monster.isAlive() && monster.getX() == x && monster.getY() == y) {
                return false;
            }
        }

        for (Treasure treasure : treasures) {
            if (!treasure.isCollected() && treasure.getX() == x && treasure.getY() == y) {
                return false;
            }
        }

        return true;
    }
}