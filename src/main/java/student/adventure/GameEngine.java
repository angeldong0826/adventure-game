package student.adventure;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import student.server.AdventureState;
import student.server.GameStatus;


/**
 * GameEngine class where the game happens.
 */
public class GameEngine {

    private static Room currentRoom;
    private static boolean done;
    private static Room nextRoom;
    private static Layout layout;
    public static GameState gameState; // DONT WANT TO BE STATIC
    private GameStatus gameStatus;

    private List<String> currentInventory;
    private List<String> locationHistory;

    private boolean error;
    private int id;
    private String message;
    private String imageUrl;
    private String videoUrl;

    private Map<String, List<String>> commandOptions;

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    /**
     * GameEngine constructor that loads instance variables based on game id.
     * @param id as an int
     * @throws IOException when input or output operation fails
     */
    public GameEngine(int id) throws IOException{
//        Gson gson = new Gson();
//        Reader reader = new FileReader("src/main/resources/hendrickhouse.json");
//        layout = gson.fromJson(reader, Layout.class);
//
//        currentRoom = layout.getRooms().get(0); // to hold the current room.
//        nextRoom = null; // to hold the next room for my updateCurrentRoom method.
//        gameState = new GameState(currentRoom, new ArrayList<>());
//        boolean done = false; // boolean to control status of game.
        GameEngine.variable();

        currentInventory = new ArrayList<>(); // list of current inventory
        locationHistory = new ArrayList<>(); // list of location history

        // initialize parameters for GameStatus in server
        error = false;
        this.id = id;
        message = gameState.getCurrentLocation().getDescription();
        imageUrl = gameState.getCurrentLocation().getImageUrl();
//        videoUrl = gameState.getCurrentLocation().getVideoUrl();
        videoUrl = "";
        commandOptions = new HashMap<>();
        createCommandOptions();

        gameStatus = new GameStatus(error, id, message, imageUrl, videoUrl, new AdventureState(), commandOptions);
    }

    /**
     * Class that makes command options.
     */
    public void createCommandOptions() {
        List<String> directions = new ArrayList<>();
        directions.add(gameState.getCurrentLocation().printAvailableDirections());

        List<String> takeOptions = new ArrayList<>();
        takeOptions.add(gameState.getCurrentLocation().printAvailableItems(gameState.getCurrentLocation()));

        List<String> dropOptions = new ArrayList<>();
        for (Item item: gameState.getInventory()) {
            dropOptions.add(item.getItemName());
        }

        commandOptions.put("quit", Collections.emptyList());
        commandOptions.put("exit", Collections.emptyList());
        commandOptions.put("examine", Collections.emptyList());
        commandOptions.put("go", directions);
        commandOptions.put("take", takeOptions);
        commandOptions.put("drop", dropOptions);

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
     * Game method in the game class that calls on the game.
     *
     * @throws FileNotFoundException when no JSON file is found
     */
    public void game() throws FileNotFoundException { // RENAME
        GameEngine.variable();
        Scanner scan = new Scanner(System.in);

        System.out.println(gameState.getCurrentLocation().getDescription());
        gameState.getCurrentLocation().printAvailableDirections();
        gameState.getCurrentLocation().printAvailableItems(gameState.getCurrentLocation());

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
            gameState.getCurrentLocation().printAvailableItems(gameState.getCurrentLocation());
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
            nextRoom = GameState.updateCurrentRoom(layout, value, gameState.getCurrentLocation());
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
                        .printAvailableItems(gameState.getCurrentLocation());
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

        // end case for game when player reaches ending room.
        if (gameState.getCurrentLocation().getName().equalsIgnoreCase(layout.getEndingRoom())) {
            System.out.println("Congrats! You successfully reached the ending room. Game Over :)");
            done = true;
        }
        return isCommandValid;
    }
}