package student.adventure;

import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * GameEngine class where the game happens.
 */
public class GameEngine {

    private static Room currentRoom;
    private static boolean done;
    private static Room nextRoom;
    private static Layout layout;

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public static GameState gameState;
    /**
     * Game method in the game class that calls on the game.
     * @throws FileNotFoundException when no JSON file is found
     */
    public void game() throws FileNotFoundException {
//        Gson gson = new Gson();
//        Reader reader = new FileReader("src/main/resources/hendrickhouse.json");
//        layout = gson.fromJson(reader, Layout.class);
//        currentRoom = layout.getRooms().get(0); // to hold the current room.
//        nextRoom = null; // to hold the next room for my updateCurrentRoom method.
//        gameState = new GameState(currentRoom, new ArrayList<>());
//        boolean done = false; // boolean to control status of game.
        variable();
        Scanner scan = new Scanner(System.in);

        System.out.println(gameState.getCurrentLocation().getDescription());
        gameState.getCurrentLocation().printAvailableDirections();
        gameState.getCurrentLocation().printAvailableItems(gameState.getCurrentLocation());

        while(!done) {
            System.out.print("> ");
            String input = scan.nextLine(); // to hold input as a String.

            console(input);
        }
    }

    public static void variable() throws FileNotFoundException {
        Gson gson = new Gson();
        Reader reader = new FileReader("src/main/resources/hendrickhouse.json");
        layout = gson.fromJson(reader, Layout.class);
        currentRoom = layout.getRooms().get(0); // to hold the current room.
        nextRoom = null; // to hold the next room for my updateCurrentRoom method.
        gameState = new GameState(currentRoom, new ArrayList<>());
        boolean done = false; // boolean to control status of game.
    }

    /**
     * Helper method for testing.
     * @param input as a String
     */
    public static void console(String input) {
        String[] splitInput = input.split("\\s+"); // to hold input split by whitespaces in an arraylist.

        if (input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("exit")) { // quitting the game.
            System.out.println("You have " + input + " the game.");
            return;
        }

        if (input.equalsIgnoreCase("examine")) {
            System.out.println(gameState.getCurrentLocation().getDescription());
            gameState.getCurrentLocation().printAvailableDirections();
            gameState.getCurrentLocation().printAvailableItems(gameState.getCurrentLocation());
            return;
        }

        if (!splitInput[0].equalsIgnoreCase("go") && !splitInput[0].equalsIgnoreCase("take") && !splitInput[0].equalsIgnoreCase("drop")) {
            System.out.println("Invalid command.");
            return;
        }

        if (splitInput[0].equalsIgnoreCase("go")) {
            nextRoom = GameState.updateCurrentRoom(layout, splitInput[1], gameState.getCurrentLocation());
            if (nextRoom == null) {
                System.out.println("You can't go " + splitInput[1] + "!");
                return;
            }
            gameState.setCurrentLocation(nextRoom);
            System.out.println(gameState.getCurrentLocation().getDescription());
            if (!(gameState.getCurrentLocation().getName().equalsIgnoreCase(layout.getEndingRoom()))) {
                gameState.getCurrentLocation().printAvailableDirections();
                gameState.getCurrentLocation().printAvailableItems(gameState.getCurrentLocation());
            }
        }

        if (splitInput[0].equalsIgnoreCase("take")) {
            gameState.take(splitInput[1]);
        }

        if (splitInput[0].equalsIgnoreCase("drop")) {
            gameState.drop(splitInput[1]);
        }

        // end case for game when player reaches ending room.
        if (gameState.getCurrentLocation().getName().equalsIgnoreCase(layout.getEndingRoom())) {
            System.out.println("Congrats! You successfully reached the ending room. Game Over :)");
            done = true;
        }
    }
}
