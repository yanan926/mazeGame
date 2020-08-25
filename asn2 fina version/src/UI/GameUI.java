package UI;

import Game_Logic.Game;

import java.util.Scanner;

/**
 * A class represents the game's interface containing a char array as its interface,
 * a Game and a TextMenu as its field variable.
 * It support displaying the map of the game and also getting user's input to play the game
 *
 * @ authors: Wing Yuk Cheung, Yanan Liu, Student ID: 301355600, 301368378, Email: wingyukc@sfu.ca, yla568@sfu.ca
 */

public class GameUI {
    private Game game;
    private TextMenu menu;
    private char[][] ui;

    public GameUI() {
        this.game = new Game();
        this.menu = new TextMenu();
        this.ui = new char[this.game.getMazeHeight()][this.game.getMazeWidth()];
        this.makeOutsideWallsVisible();
    }

    //At the beginning of the game, the outer walls need to be revealed.
    private void makeOutsideWallsVisible() {
        int height = this.ui.length;
        int width = this.ui[0].length;
        for (int i = 0; i < width; i++) {
            this.game.setMazeMist(0, i);
            this.game.setMazeMist(height - 1, i);
        }
        for (int i = 0; i < height; i++) {
            this.game.setMazeMist(i, 0);
            this.game.setMazeMist(i, width - 1);
        }
    }

    // After getting user's input, the user interface needs to be updated
    private void updateUI() {
        int[] monster1Location = this.game.getMonster(0).getMonsterLocation();
        int[] monster2Location = this.game.getMonster(1).getMonsterLocation();
        int[] monster3Location = this.game.getMonster(2).getMonsterLocation();
        int[] playerLocation = this.game.getPlayer().getPlayerLocation();
        int[] powerLocation = this.game.getPower().getPowerLocation();


        for (int i = 0; i < this.ui.length; i++) {
            for (int j = 0; j < this.ui[0].length; j++) {
                if (i == playerLocation[0] && j == playerLocation[1]) {
                    if (this.game.getPlayer().isAlive()) {
                        this.ui[i][j] = '@';
                    } else {
                        this.ui[i][j] = 'X';
                    }
                    ;
                    this.setNeiMist(i, j);
                } else if ((i == monster1Location[0] && j == monster1Location[1]) ||
                        (i == monster2Location[0] && j == monster2Location[1]) ||
                        (i == monster3Location[0] && j == monster3Location[1])
                ) {
                    this.ui[i][j] = '!';
                } else if (i == powerLocation[0] && j == powerLocation[1]) {
                    this.ui[i][j] = '$';
                } else if (game.getMaze()[i][j].inMist()) {
                    ui[i][j] = '.';
                } else if (game.getMaze()[i][j].getValue() == 1) {
                    ui[i][j] = '#';
                } else if (game.getMaze()[i][j].getValue() == 0) {
                    ui[i][j] = ' ';
                }
            }
        }

    }

    // a method used for revealing the players's neighbor 8 directions' value
    private void setNeiMist(int row, int col) {
        int height = this.game.getMazeHeight();
        int width = this.game.getMazeWidth();
        int[][] dirs = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        int i = 0;
        for (int[] dir : dirs) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (newRow >= 0 && newRow < height && newCol >= 0 && newCol < width) {
                this.game.setMazeMist(newRow, newCol);
            }
        }
    }

    public void playGame() {
        Scanner console = new Scanner(System.in);
        while (!this.game.isGameOver()) {
            menu.display_current_state(this.game.getMonster_to_be_killed(), this.game.getPowers_in_possession(), this.game.getMonster_alive());
            this.updateUI();
            this.displayUI();
            menu.ask_input();
            char key = console.next().charAt(0);
            key = Character.toLowerCase(key);
            if (key == '?') {
                menu.display_purpose();
            } else if (key == 'c') {
                game.cheating();
            } else if (key == 'm') {
                game.clean_map();
            } else if (key == 'w' || key == 'a' || key == 's' || key == 'd') {
                if (game.movePlayer(key)) {
                    int[] location = this.game.getPlayer().getPlayerLocation();
                    this.game.setMazeMist(location[0], location[1]);
                } else {
                    menu.display_invalid_move();
                }
            } else {
                menu.display_invalid_input();
            }
        }
        this.game.clean_map();
        this.updateUI();
        this.displayUI();
        if (this.game.isWin()) {
            menu.display_current_state(this.game.getMonster_to_be_killed(), this.game.getPowers_in_possession(), this.game.getMonster_alive());
            this.menu.display_win();
        } else {
            menu.display_current_state(this.game.getMonster_to_be_killed(), this.game.getPowers_in_possession(), this.game.getMonster_alive());
            this.menu.display_lose();
        }
    }


    private void displayUI() {
        for (int i = 0; i < this.ui.length; i++) {
            for (int j = 0; j < this.ui[0].length; j++) {
                System.out.print(this.ui[i][j] + " ");
            }
            System.out.println();
        }
    }


}
