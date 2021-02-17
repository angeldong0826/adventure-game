package student.adventure;

import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import sun.lwawt.macosx.CSystemTray;

/**
 * GameEngine class where the game happens.
 */
public class GameEngine {
    public static GameState gameState;
    /**
     * Game method in the game class that calls on the game.
     * @throws FileNotFoundException when no JSON file is found
     */
    public void game() throws FileNotFoundException {
        Gson gson = new Gson();
        Reader reader = new FileReader("src/main/resources/hendrickhouse.json");
        Layout layout = gson.fromJson(reader, Layout.class);
        Room currentRoom = layout.getRooms().get(0); // to hold the current room.
        Room nextRoom = null; // to hold the next room for my updateCurrentRoom method.
        gameState = new GameState(currentRoom, new ArrayList<>());
        boolean done = false;
        Scanner scan = new Scanner(System.in);
        System.out.println(gameState.getCurrentLocation().getDescription());
        gameState.getCurrentLocation().returnAvailableDirections();
        gameState.getCurrentLocation().printAvailableItems(gameState.getCurrentLocation());
        while(!done) {
            System.out.print("> ");
            String input = scan.nextLine(); // to hold input as a String.
            String[] splitInput = input.split("\\s+"); // to hold input split by whitespace in an arraylist.

            if (input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("exit")) { // quitting the game.
                System.out.println("You have " + input + " the game.");
                break;
            }

            if (input.equalsIgnoreCase("examine")) {
                System.out.println(gameState.getCurrentLocation().getDescription());
                gameState.getCurrentLocation().returnAvailableDirections();
                gameState.getCurrentLocation().printAvailableItems(gameState.getCurrentLocation());
                continue;
            }

            if (!splitInput[0].equalsIgnoreCase("go") && !splitInput[0].equalsIgnoreCase("take") && !splitInput[0].equalsIgnoreCase("drop")) {
                System.out.println("Invalid command.");
                continue;
            }

            if (splitInput[0].equalsIgnoreCase("go")) {
                nextRoom = GameState.updateCurrentRoom(layout, splitInput[1], gameState.getCurrentLocation());
                if (nextRoom == null) {
                    System.out.println("You can't go " + splitInput[1] + "!");
                    continue;
                }
                gameState.setCurrentLocation(nextRoom);
                System.out.println(gameState.getCurrentLocation().getDescription());
                gameState.getCurrentLocation().returnAvailableDirections();
                gameState.getCurrentLocation().printAvailableItems(gameState.getCurrentLocation());
            }

            if (splitInput[0].equalsIgnoreCase("take")) {
                gameState.take(splitInput[1]);
            }

            if (splitInput[0].equalsIgnoreCase("drop")) {
                gameState.drop(splitInput[1]);
            }

            if (gameState.getCurrentLocation().getName().equalsIgnoreCase(layout.getEndingRoom())) {
                System.out.println("Congrats! You successfully reached the ending room. Game Over :)");
                done = true;
            }
        }
    }
}
