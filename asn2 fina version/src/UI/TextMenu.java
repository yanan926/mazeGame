package UI;

/**
 * A class used for displaying user game instructions and current player status for the game.
 *
 * @ authors: Wing Yuk Cheung, Yanan Liu, Student ID: 301355600, 301368378, Email: wingyukc@sfu.ca, yla568@sfu.ca
 */

import Game_Logic.Game;

public class TextMenu {

    // Display the game purpose when input = m
    public void display_purpose() {
        System.out.println("DIRECTIONS:\n\tKill 3 Monsters!");
        System.out.println("Legend:\n\t#: Wall\n\t@: You (a hero)\n\t"
                + "!: Monster\n\t$: Power\n\t.: Unexplored space");
        System.out.println("Moves:\n\tUse W (up), A (left), S (down) "
                + "and D (right) to move.\n\t (You must press enter after each move).\n");
    }

    // Display the current state of the game
    public void display_current_state(int monster_to_beKilled, int power, int monsterAlive) {
        //game.displayMap();
        System.out.println("Total number of monsters to be killed: " + monster_to_beKilled);
        System.out.println("Number of powers currently in possession: " + power);
        System.out.println("Number of monsters alive: " + monsterAlive);
    }

    // Ask for input
    public void ask_input() {
        System.out.println("Enter your move [WASD?]: ");
    }

    // Invalid input
    public void display_invalid_input() {
        System.out.println("Invalid Input!");
    }

    // When player win
    public void display_win() {
        System.out.println("You Win!!!");
    }

    // When player lose
    public void display_lose() {
        System.out.println("I'm sorry, you have been eaten!");
    }

    public void display_invalid_move() {
        System.out.println("invalid move: you cannot move through walls!");
    }

}
