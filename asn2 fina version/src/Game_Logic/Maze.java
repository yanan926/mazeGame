package Game_Logic;

/**
 * A immutable class used to generate a valid maze. We used dfs algorithm to create the maze, randomly
 * remove some walls to create some loop and check if it is valid.
 *
 * @ authors: Wing Yuk Cheung, Yanan Liu, Student ID: 301355600, 301368378, Email: wingyukc@sfu.ca, yla568@sfu.ca
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public final class Maze {
    private final int WIDTH = 20;
    private final int HEIGHT = 15;
    private Cell[][] maze;

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }

    public Cell[][] generateValidMaze() {
        while (true) {
            this.maze = generateMaze();
            if (validMaze(this.maze)) {
                break;
            }
        }
        return this.maze;
    }

    private Cell[][] generateMaze() {
        // set predetermined open cell's value as '0' and wall's value as '1'
        Cell[][] maze = new Cell[HEIGHT][WIDTH];
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                Cell cell = new Cell();
                if (i % 2 == 0) {
                    cell.setValue(1);
                } else {
                    if (j % 2 == 0) {
                        cell.setValue(1);
                    } else {
                        cell.setValue(0);
                    }
                }
                cell.setRow(i);
                cell.setCol(j);

                maze[i][j] = cell;
            }
        }

        //Using dfs algorithm requires odd number maze width and height.
        // However,the game required maze width is even.
        // So we decide to swap the last col with the 18th col to make the last column a predetermined wall

        swapLastCol(maze);
        //we use a stack to apply dfs algorithm
        Stack<Cell> stack = new Stack<Cell>();
        // set the first visited cell
        Cell start = maze[1][1];
        start.setVisited();
        //push the first visited cell to stack
        stack.push(start);

        while (!stack.isEmpty()) {
            // neiList is the current cell's unvisited neighbor cell list
            List<Cell> neiList = new ArrayList<Cell>();
            Cell cur = stack.pop();
            //add four directions' unvisited neighbor cell to the neighbor cell list

            //Up
            if (cur.getRow() - 2 > 0) {
                Cell up = maze[cur.getRow() - 2][cur.getCol()];
                if (!up.isVisited())
                    neiList.add(up);
            }
            //Down
            if (cur.getRow() + 2 < HEIGHT) {
                Cell down = maze[cur.getRow() + 2][cur.getCol()];
                if (!down.isVisited())
                    neiList.add(down);
            }

            //Left
            if (cur.getCol() - 2 > 0) {
                Cell left = maze[cur.getRow()][cur.getCol() - 2];
                if (!left.isVisited())
                    neiList.add(left);
            }

            //Right
            //Since we swap the last column with 18th column, the 17th column and 18th column become one open cell
            // the last right cell should be 17th
            if (cur.getCol() + 2 < WIDTH - 1) {
                Cell right = maze[cur.getRow()][cur.getCol() + 2];
                if (!right.isVisited())
                    neiList.add(right);
            }
            // if the neighbor cell list's length bigger than 0, randomly select one to break the wall
            // between two open cells
            if (neiList.size() != 0) {
                stack.push(cur);
                Cell nextCell = neiList.get(new Random().nextInt(neiList.size()));
                //break the wall by setting the cell's value between two open cells 0
                maze[(cur.getRow() + nextCell.getRow()) / 2][(cur.getCol() + nextCell.getCol()) / 2].setValue(0);
                nextCell.setVisited();
                stack.push(nextCell);
            }
        }
        //Remove some of the inner walls of the maze to add cycles (loops) to the maze.
        // We decide to break random two cells from even number row;
        for (int i = 2; i < maze.length - 1; i += 2) {
            List<Cell> list = new ArrayList<>();
            for (int j = 1; j < maze[0].length - 1; j++) {
                if (maze[i][j].getValue() == 1) {
                    list.add(maze[i][j]);
                }
            }
            if (list.size() > 0) {
                Cell pickedCell1 = list.get(new Random().nextInt(list.size()));
                pickedCell1.setValue(0);
                Cell pickedCell2 = list.get(new Random().nextInt(list.size()));
                pickedCell2.setValue(0);
            }
        }

        return maze;


    }

    //A method used for swapping the last column with 18th column to make the last column's number is all "1"
    private void swapLastCol(Cell[][] maze) {
        int width = maze[0].length;
        for (int i = 0; i < maze.length; i++) {
            int temp = maze[i][width - 1].getValue();
            maze[i][width - 1].setValue(maze[i][width - 2].getValue());
            maze[i][width - 2].setValue(temp);
        }
    }

    // A method checking if there is four neighbor cells having same value
    public boolean validMaze(Cell[][] maze) {
        for (int i = 1; i < maze.length; i++) {
            for (int j = 1; j < maze[0].length; j++) {
                int up = i - 1;
                int left = j - 1;
                int val = maze[i][j].getValue();
                if (maze[up][j].getValue() == val && maze[i][left].getValue() == val && maze[up][left].getValue() == val) {
                    return false;
                }
            }
        }
        return true;
    }


}
