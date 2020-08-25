package Game_Logic;

/**
 * A class representing the Game and the full logic of the game,
 * including all the rules and basic set up,and controlling how the game run by.
 * It contains functions updating player and monsters' status and
 * checking the game is over (win or lose) or not every time it completed a movement.
 *
 * @ authors: Wing Yuk Cheung, Yanan Liu, Student ID: 301355600, 301368378, Email: wingyukc@sfu.ca, yla568@sfu.ca
 */

public class Game {
    private int monster_to_be_killed = 3;
    private int powers_in_possession = 0;
    private int monster_alive = 3;
    private Cell[][] maze;
    private Player player;
    private Monster[] monsters;
    private Power power;
    private boolean gameOver;
    private boolean win;
    private boolean lose;

    // Game basic set up
    public Game() {
        this.maze = new Maze().generateValidMaze();
        this.player = new Player(1, 1);
        this.monsters = new Monster[3];
        this.monsters[0] = new Monster(1, 18);
        this.monsters[1] = new Monster(13, 1);
        this.monsters[2] = new Monster(13, 18);
        this.power = new Power(maze, this.player.getCurrent_row(), this.player.getCurrent_column());
        this.gameOver = false;
        this.win = false;
        this.lose = false;
    }

    // move the player
    // return true if the movement valid
    // return false if fail
    public boolean movePlayer(char key) {
        if (this.player.move(maze, key)) {
            this.check_meet_monster();
            if (!this.isGameOver()) {
                this.grab_power();
                this.check_win();
                this.moveMonsters();
            }
            return true;
        }
        return false;
    }

    // Check if the player is same position with monster
    // If having a power, kill the monster
    // If not, player got eaten, game over
    public void check_meet_monster() {
        for (int i = 0; i < monsters.length; i++) {
            if (this.player.getCurrent_row() == this.monsters[i].getCurrent_row() &&
                    this.player.getCurrent_column() == this.monsters[i].getCurrent_column()) {
                if (this.powers_in_possession > 0) {
                    this.monsters[i].dead();
                    this.monster_alive--;
                    this.monster_to_be_killed--;
                    this.powers_in_possession--;
                } else {
                    this.player.dead();
                    this.lose = true;
                    this.gameOver = true;
                }
            }
        }
    }


    //  monsters move and check if their positions are same as player, check if player wins or loses
    public void moveMonsters() {
        for (int i = 0; i < monsters.length; i++) {
            monsters[i].move(this.maze);
        }
        this.check_meet_monster();
        this.check_win();
    }

    // If player get the power, increase power in hand and generates a new power
    public void grab_power() {
        if (player.getCurrent_row() == power.getRow() &&
                player.getCurrent_column() == power.getColumn()) {
            this.powers_in_possession++;
            this.power = new Power(maze, this.player.getCurrent_row(), this.player.getCurrent_column());
        }
    }


    // check if the player wins and set gameOver if the player wins
    public void check_win() {
        if (this.monster_to_be_killed == 0) {
            this.win = true;
            this.gameOver = true;
        }
    }

    // When user input 'c'
    public void cheating() {
        this.setMonster_to_be_killed(1);
    }

    // When user input 'm'
    // clean the map vision
    public void clean_map() {
        for (int i = 0; i < this.maze.length; i++) {
            for (int j = 0; j < this.maze[0].length; j++) {
                this.maze[i][j].setMist();
            }
        }
    }

    public boolean isGameOver() {
        return this.gameOver;
    }

    public boolean isWin() {
        return this.win;
    }

    public boolean isLose() {
        return this.lose;
    }

    public int getMonster_to_be_killed() {
        return this.monster_to_be_killed;
    }

    public int getPowers_in_possession() {
        return this.powers_in_possession;
    }

    public int getMonster_alive() {
        return this.monster_alive;
    }

    public Cell[][] getMaze() {
        return this.maze;
    }

    public int getMazeWidth() {
        return this.maze[0].length;
    }

    public int getMazeHeight() {
        return this.maze.length;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Monster getMonster(int num) {
        return this.monsters[num];
    }

    public Power getPower() {
        return this.power;
    }

    public void setMazeMist(int row, int col) {
        this.maze[row][col].setMist();
    }

    public void setMonster_to_be_killed(int monster_to_be_killed) {
        this.monster_to_be_killed = monster_to_be_killed;
    }


}
