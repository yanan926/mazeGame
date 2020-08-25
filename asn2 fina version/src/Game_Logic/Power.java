package Game_Logic;

import java.util.Random;

/**
 * A class represents a Power generated randomly in the map
 *
 * @ authors: Wing Yuk Cheung, Yanan Liu, Student ID: 301355600, 301368378, Email: wingyukc@sfu.ca, yla568@sfu.ca
 */
public class Power {
    private int current_row;
    private int current_column;

    public int getRow() {
        return current_row;
    }

    public int getColumn() {
        return current_column;
    }

    public int[] getPowerLocation() {
        return new int[]{this.getRow(), this.getColumn()};
    }

    public Power(Cell[][] maze, int playerRow, int playerCol) {
        Random x = new Random();
        Random y = new Random();
        int row;
        int column;
        while (true) {
            row = x.nextInt(13) + 1;
            column = y.nextInt(19) + 1;
            if (maze[row][column].getValue() != 1 && row != playerRow && column != playerCol) {
                this.current_row = row;
                this.current_column = column;
                break;
            }
        }
    }
}
