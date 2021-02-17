package student.adventure;

import java.io.IOException;

/**
 * Main class that contains the main method that calls on the game.
 */
public class Main {
    /**
     * Main method that calls on the game.
     * @param args
     * @throws IOException when errors occur during a data transfer operation, can be when reading/writing the json file
     */
    public static void main(String[] args) throws IOException {
        GameEngine gameEngine = new GameEngine();
        gameEngine.game();
    }
}