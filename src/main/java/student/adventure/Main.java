package student.adventure;

import java.io.IOException;

/**
 * Main class that calls on the game.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        GameEngine gameEngine = new GameEngine();
        gameEngine.game();
    }
}