package Game_Logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A class represents a monster containing monster current position,
 * the previous position, if it is still alive and its movement logic.
 *
 * @ authors: Wing Yuk Cheung, Yanan Liu, Student ID: 301355600, 301368378, Email: wingyukc@sfu.ca, yla568@sfu.ca
 *
 */

public final class Monster {
    private int current_row;
    private int current_column;
    private int pre_row;
    private int pre_column;
    private boolean alive;

    public Monster(int row, int column) {
        this.current_row = row;
        this.current_column = column;
        this.pre_column = -1;
        this.pre_row = -1;
        this.alive = true;
    }

    public int getCurrent_row() {
        return current_row;
    }


    public int getCurrent_column() {
        return current_column;
    }

    public int[] getMonsterLocation() {
        return new int[]{this.current_row, this.current_column};
    }

    // a method used to check if the monster's next move is surrounded by 3 walls
    public boolean validNextMove(int row, int col, Cell[][] maze) {
        int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        int count = 0;
        for (int[] dir : dirs) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (maze[newRow][newCol].getValue() == 1) {
                count++;
            }
        }
        if (count >= 3) {
            return false;
        } else {
            return true;
        }
    }
    // a method used to make monster select a random valid direction to move
    public void move(Cell[][] maze) {
        // check monster still alive or not
        if (!alive) {
            return;
        }
        //four directions: left, right, up, down
        int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        // a list to store all valid directions to move
        List<int[]> validDir = new ArrayList<>();
        for (int[] dir : dirs) {
            int newRow = this.current_row + dir[0];
            int newCol = this.current_column + dir[1];
            // try finding a location where is not wall, not the previous location, not a place 3 sides surrounded by walls
            // and save it to valid directions list
            if (maze[newRow][newCol].getValue() != 1 && !(newRow == pre_row && newCol == pre_column)
                    && validNextMove(newRow, newCol, maze)) {
                int[] newLocation = new int[]{newRow, newCol};
                validDir.add(newLocation);
            }
        }
        //If there is no valid move choice, the monster has to backtrack
        if (validDir.size() == 0) {
            this.current_row = this.pre_row;
            this.current_column = this.pre_column;
            this.pre_row = this.current_row;
            this.pre_column = this.current_column;
        } else {
            // randomly select a valid direction to move
            Random random = new Random();
            int ranNum = random.nextInt(validDir.size());
            int[] newLoc = validDir.get(ranNum);
            int newRow = newLoc[0];
            int newCol = newLoc[1];
            this.pre_row = this.current_row;
            this.pre_column = this.current_column;
            this.current_row = newRow;
            this.current_column = newCol;
        }
    }

    public void dead() {
        this.current_row = -1;
        this.current_column = -1;
        this.alive = false;
    }

    public boolean isAlive() {
        return this.alive;
    }

}
