package student;

import java.io.IOException;
import org.glassfish.grizzly.http.server.HttpServer;
import student.adventure.GameEngine;
import student.server.AdventureResource;
import student.server.AdventureServer;

/**
 * Main class that contains the main method that calls on the game.
 */
public class Main {

    /**
     * Main method that calls on the game.
     *
     * @param args
     * @throws IOException when errors occur during a data transfer operation, can be when
     *                     reading/writing the json file
     */
    public static void main(String[] args) throws IOException {
        HttpServer server = AdventureServer.createServer(AdventureResource.class);
        server.start();
        GameEngine gameEngine = new GameEngine(0);
        gameEngine.game();
    }
}