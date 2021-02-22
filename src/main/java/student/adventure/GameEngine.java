package student.adventure;

import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * GameEngine class where the game happens.
 */
public class GameEngine {

    private static Layout layout;
    private Room currentRoom;
    private boolean done;
    private Room nextRoom;
    public GameState gameState;

    private List<String> currentInventory;
    private List<String> locationHistory;

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }


    /**
     * GameEngine constructor that loads instance variables based on game id.
     * @throws IOException when input or output operation fails
     */
    public GameEngine() {
//        Gson gson = new Gson();
//        Reader reader = new FileReader("src/main/resources/hendrickhouse.json");
//        layout = gson.fromJson(reader, Layout.class);
//
//        currentRoom = layout.getRooms().get(0); // to hold the current room.
//        nextRoom = null; // to hold the next room for my updateCurrentRoom method.
//        gameState = new GameState(currentRoom, new ArrayList<>());
//        boolean done = false; // boolean to control status of game.
        variable();

        currentInventory = new ArrayList<>(); // list of current inventory
        locationHistory = new ArrayList<>(); // list of location history


    }

//    /**
//     * Class that makes command options.
//     */
//    public void createCommandOptions() {
//
//    }

    public void variable() {
        try {
            Gson gson = new Gson();
            Reader reader = new FileReader("src/main/resources/hendrickhouse.json");
            layout = gson.fromJson(reader, Layout.class);
        } catch (Exception e) {}

        currentRoom = layout.getRooms().get(0); // to hold the current room.
        nextRoom = null; // to hold the next room for my updateCurrentRoom method.
        gameState = new GameState(currentRoom, new ArrayList<>());
        done = false; // boolean to control status of game.
    }

    /**
     * Game method in the game class that calls on the game.
     *
     * @throws FileNotFoundException when no JSON file is found
     */
    public void game() throws FileNotFoundException { // RENAME
        variable();
        Scanner scan = new Scanner(System.in);

        System.out.println(gameState.getCurrentLocation().getDescription());
        gameState.getCurrentLocation().printAvailableDirections();
        gameState.getCurrentLocation().returnAvailableItems(gameState.getCurrentLocation());

        while (!done) {
            System.out.print("> ");
            GameCommand input = new GameCommand(scan.nextLine()); // to hold input as a String.
            inputExecute(input);
        }
    }


    public boolean inputExecute(GameCommand command) {

        String name = command.getCommandName();
        String value = command.getCommandValue();
        boolean isCommandValid = false;

        if (name.equalsIgnoreCase("quit") || name
            .equalsIgnoreCase("exit")) { // quitting the game.
            System.out.println("You have " + name + " the game.");
            done = true;
            return true;
        }

        if (name.equalsIgnoreCase("examine")) {
            System.out.println(gameState.getCurrentLocation().getDescription());
            gameState.getCurrentLocation().printAvailableDirections();
            gameState.getCurrentLocation().returnAvailableItems(gameState.getCurrentLocation());
            isCommandValid = true;
        }

        if (!name.equalsIgnoreCase("go") &&
            !name.equalsIgnoreCase("take") &&
            !name.equalsIgnoreCase("drop") &&
            !name.equalsIgnoreCase("examine")) {
            System.out.println("Invalid command.");
            isCommandValid = false;
        }

        if (name.equalsIgnoreCase("go")) {

            System.out.println("value: " + value);
            nextRoom = gameState.updateCurrentRoom(gameState, layout, value, gameState.getCurrentLocation());
            if (nextRoom == null) {
                System.out.println("You can't go " + value + "!");
            } else {
                gameState.setCurrentLocation(nextRoom);
                isCommandValid = true;
                System.out.println(gameState.getCurrentLocation().getDescription());
                if (!(gameState.getCurrentLocation().getName()
                    .equalsIgnoreCase(layout.getEndingRoom()))) {
                    gameState.getCurrentLocation().printAvailableDirections();
                    gameState.getCurrentLocation()
                        .returnAvailableItems(gameState.getCurrentLocation());
                } else if (gameState.getCurrentLocation().getName().equalsIgnoreCase(layout.getEndingRoom())) {
                    System.out.println("Congrats! You successfully reached the ending room. Game Over :)");
                    done = true;
                }
            }
        }

        if (name.equalsIgnoreCase("take")) {
            gameState.take(value);
            isCommandValid = true;
        }

        if (name.equalsIgnoreCase("drop")) {
            gameState.drop(value);
            isCommandValid = true;
        }
        return isCommandValid;
    }
}