package com.coding_kickoff.asciiquest;

/**
 * Main game logic and game loop
 */
public class Game {
    // HYPERPARAMETERS WOOOOH!! (You'll often hear this term in the machine learning
    // course. It's all about hyperparameter optimization these days...)
    private static final int BOARD_WIDTH = 20;
    private static final int BOARD_HEIGHT = 15;
    private static final int NUM_MONSTERS = 3;
    private static final int NUM_TREASURES = 5;
    private Board board;

    private Player player;
    private InputHandler inputHandler;
    private boolean gameRunning;
    private boolean playerWon;

    public Game() {
        this.inputHandler = new InputHandler();
        initializeGame();
    }

    /**
     * Main game loop
     */
    public void run() {
        printWelcomeMessage();

        while (gameRunning) {
            Utils.clearScreen();
            board.render();
            board.printStats();
            System.out.println();

            // Check win/lose conditions
            if (checkGameEnd()) {
                break;
            }

            String command = inputHandler.getCommand();

            if (inputHandler.isQuitCommand(command)) {
                gameRunning = false;
                System.out.println("Thanks for playing!");
                break;
            }

            int[] movement = inputHandler.getMovement(command);
            if (movement != null) {
                processPlayerMove(movement[0], movement[1]);

                // Only process monster turns if player is still alive
                if (player.isAlive()) {
                    processMonsterTurns();
                    processCollisions();
                }
            } else {
                System.out.println("Invalid command! Use w/a/s/d to move, q to quit.");
                Utils.sleep(1000);
            }
        }

        showGameOverScreen();
        inputHandler.close();
    }

    public Player getPlayer() {
        return player;
    }

    public Board getBoard() {
        return board;
    }

    public boolean isGameRunning() {
        return gameRunning;
    }

    public boolean hasPlayerWon() {
        return playerWon;
    }

    /**
     * This function does 3 things:
     * 1. Generates the board and sets the player at a random position.
     * 2. Generates monsters and sets them at random positions *across the board*.
     * (Hehe, get it? - In case you didn't, it's this cool cafe:
     * https://acrosstheboardcafe.com/, you should definitely check it out!)
     * 3. Generates treasures and sets them at random positions around the board.
     * (Not a fan of two time repeaters, hehe.)
     */
    private void initializeGame() {
        board = new Board(BOARD_WIDTH, BOARD_HEIGHT);

        Position playerPos = board.getRandomEmptyPosition();
        player = new Player(playerPos.getX(), playerPos.getY());
        board.setPlayer(player);

        for (int i = 0; i < NUM_MONSTERS; i++) {
            Position monsterPos = board.getRandomEmptyPosition();
            Monster monster = new Monster(monsterPos.getX(), monsterPos.getY());
            board.addMonster(monster);
        }

        for (int i = 0; i < NUM_TREASURES; i++) {
            Position treasurePos = board.getRandomEmptyPosition();
            Treasure treasure = new Treasure(treasurePos.getX(), treasurePos.getY());
            board.addTreasure(treasure);
        }

        gameRunning = true;
        playerWon = false;
    }

    private void processPlayerMove(int deltaX, int deltaY) {
        int newX = player.getX() + deltaX;
        int newY = player.getY() + deltaY;

        if (board.isValidPosition(newX, newY)) {
            Monster monster = board.getMonsterAt(newX, newY);
            if (monster != null) {
                System.out.println("You attack a monster!");
                monster.takeDamage(Utils.randomInt(15, 30));

                if (!monster.isAlive()) {
                    System.out.println("You defeated the monster!");
                    player.addScore(25);
                } else {
                    System.out.println("The monster is wounded!");
                }

                Utils.sleep(1000);
            } else {
                player.setPosition(newX, newY);

                for (Treasure treasure : board.getTreasures()) {
                    if (treasure.collectBy(player)) {
                        System.out.println("You found treasure worth " + treasure.getValue()
                                + " points! What are you going to do with it?");
                        Utils.sleep(500);
                        break;
                    }
                }
            }
        } else {
            System.out.println("You can't move there!");
            Utils.sleep(500);
        }
    }

    private void processMonsterTurns() {
        for (Monster monster : board.getMonsters()) {
            if (monster.isAlive()) {
                monster.moveTowardsPlayer(player.getX(), player.getY(),
                        board.getWidth(), board.getHeight());
            }
        }

        board.removeDeadMonsters();
    }

    private void processCollisions() {
        // Check if any monster can attack the player
        for (Monster monster : board.getMonsters()) {
            if (monster.attackPlayer(player)) {
                System.out.println("A monster attacks you for " + monster.getDamage() + " damage!");
                Utils.sleep(1000);

                if (!player.isAlive()) {
                    System.out.println("You have been defeated!");
                    Utils.sleep(1000);
                    break;
                }
            }
        }
    }

    /**
     * One of two options:
     * 1. Player dies.
     * 2. Player gets all of the treasures.
     * It's all or nothing baby.
     */
    private boolean checkGameEnd() {
        if (!player.isAlive()) {
            gameRunning = false;
            playerWon = false;
            return true;
        }

        if (board.getTreasureCount() == 0) {
            gameRunning = false;
            playerWon = true;
            System.out.println("Congratulations! You collected all the treasure!");
            Utils.sleep(2000);
            return true;
        }

        return false;
    }

    private void printWelcomeMessage() {
        Utils.clearScreen();
        System.out.println("========================================");
        System.out.println("       Welcome to AsciiQuest!         ");
        System.out.println("========================================");
        System.out.println();
        System.out.println("You are the hero (@) on a quest!");
        System.out.println("Collect all treasure ($) while avoiding monsters (M)!");
        System.out.println();
        System.out.println("Controls:");
        System.out.println("  W - Move Up");
        System.out.println("  A - Move Left");
        System.out.println("  S - Move Down");
        System.out.println("  D - Move Right");
        System.out.println("  Q - Quit Game");
        System.out.println();
        System.out.println("Tips:");
        System.out.println("- Walk into monsters to attack them!");
        System.out.println("- Watch your health - monsters will attack you!");
        System.out.println("- Collect all treasure or defeat all monsters to win!");
        System.out.println();

        inputHandler.waitForEnter();
    }

    private void showGameOverScreen() {
        Utils.clearScreen();
        System.out.println("========================================"); // Oof, this took a while to type out. I hope
                                                                        // you appreciate the nice ASCII visuals.
        System.out.println("            GAME OVER                  ");
        System.out.println("========================================");
        System.out.println();

        if (playerWon) {
            System.out.println(" VICTORY! ");
            System.out.println(
                    "You have successfully completed this dungeon! Could there perhaps be more waiting for you....?");
        } else if (!player.isAlive()) {
            System.out.println(" DEFEAT ");
            System.out.println("gg...");
        } else {
            System.out.println("Quest abandoned. The dungeon cries to be freed!");
        }

        System.out.println();
        System.out.println("Final Stats:");
        System.out.println("- Final Score: " + player.getScore());
        System.out.println("- Health Remaining: " + player.getHealth() + "/" + player.getMaxHealth());
        System.out
                .println("- Treasures Collected: " + (NUM_TREASURES - board.getTreasureCount()) + "/" + NUM_TREASURES);
        System.out
                .println("- Monsters Defeated: " + (NUM_MONSTERS - board.getAliveMonsterCount()) + "/" + NUM_MONSTERS);

        if (playerWon) {
            int bonus = 100;
            if (player.getHealth() == player.getMaxHealth()) {
                bonus += 50; // Perfect health bonus
            }
            System.out.println("- Victory Bonus: " + bonus + " points");
            player.addScore(bonus);
            System.out.println("- TOTAL SCORE: " + player.getScore());
        }

        System.out.println();
        System.out.println("Thank you for playing AsciiQuest! I hope you continue to use Git and GitHub!");
    }
}