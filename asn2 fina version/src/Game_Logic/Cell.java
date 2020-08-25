package Game_Logic;

/**
 * A class represent cell of the Maze, contains a field of it's
 * row, column, visited for checking if the cell is
 * visited and a boolean value for representing if the value need to be hided
 *
 * @ authors: Wing Yuk Cheung, Yanan Liu, Student ID: 301355600, 301368378, Email: wingyukc@sfu.ca, yla568@sfu.ca
 */


public class Cell {
    private boolean inMist;
    private int row;
    private int col;
    private int value;
    private boolean visited;

    public Cell() {
        this.value = 0;
        this.inMist = true;
        this.visited = false;
        this.inMist = true;
    }

    public int getValue() {
        return this.value;
    }

    public void setMist() {
        this.inMist = false;
    }

    public boolean inMist() {
        return this.inMist;
    }

    public void setValue(int num) {
        this.value = num;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setVisited() {
        this.visited = true;
    }

    public boolean isVisited() {
        return this.visited;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

}
