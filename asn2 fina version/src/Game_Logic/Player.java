package Game_Logic;


/**
 * A class represents game player including player current position, status(alive or dead)
 * and a move function required a maze as a argument
 *
 * @ authors: Wing Yuk Cheung, Yanan Liu, Student ID: 301355600, 301368378, Email: wingyukc@sfu.ca, yla568@sfu.ca
 */
public class Player {
    private int current_row;
    private int current_column;
    private boolean alive;

    public Player(int row, int column) {
        this.current_row = row;
        this.current_column = column;
        this.alive = true;
    }


    public int getCurrent_row() {
        return this.current_row;
    }

    public int getCurrent_column() {
        return this.current_column;
    }

    public int[] getPlayerLocation() {
        return new int[]{this.current_row, this.current_column};
    }

    public boolean move(Cell[][] maze, char direction) {
        if (direction == 'w') {
            if (maze[this.getCurrent_row() - 1][this.getCurrent_column()].getValue() == 1) {
                return false;
            }
            this.current_row--;
            return true;
        }
        if (direction == 'a') {
            if (maze[this.getCurrent_row()][this.getCurrent_column() - 1].getValue() == 1) {
                return false;
            }
            this.current_column--;
            return true;
        }
        if (direction == 's') {
            if (maze[this.getCurrent_row() + 1][this.getCurrent_column()].getValue() == 1) {
                return false;
            }
            this.current_row++;
            return true;
        }
        if (direction == 'd') {
            if (maze[this.getCurrent_row()][this.getCurrent_column() + 1].getValue() == 1) {
                return false;
            }
            this.current_column++;
            return true;
        }
        return false;
    }

    public void dead() {
        this.alive = false;
    }

    public boolean isAlive() {
        return this.alive;
    }
}
