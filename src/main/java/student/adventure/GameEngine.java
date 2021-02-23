package student.adventure;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
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
    private List<String> locationHistory = new ArrayList<>();
    public String DEFAULT_JSON = "src/main/resources/hendrickhouse.json";

    public List<String> getLocationHistory() {
        return locationHistory;
    }

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
     * Method that casts my arraylist of location history into a String in order to pass into server.
     * @return location history as a String
     */
    public String locationHistoryToString() {
        String[] historyToReturn = new String[locationHistory.size()];
        for (int i = 0; i < historyToReturn.length; i++) {
            historyToReturn[i] = locationHistory.get(i);
        }
        return String.join(", ", historyToReturn);
    }

    /**
     * Constructor that calls on the initiated variables.
     */
    public GameEngine() throws FileNotFoundException {
        variable(DEFAULT_JSON);
    }

    /**
     * Method that stores initial values that game calls on.
     */
    public void variable(String fileName) throws FileNotFoundException {
        Gson gson = new Gson();
        if (fileName == null) {
            throw new NullPointerException();
        }
        if (fileName.isEmpty()) {
            throw new IllegalArgumentException();
        }

        // deserialization
        Reader reader = new FileReader(fileName);
        layout = gson.fromJson(reader, Layout.class);

        if (layout == null) {
            throw new NullPointerException();
        }
        layout.isValidLayout();

        List<Room> rooms = layout.getRooms();
        List<String> roomNames = new ArrayList<>();
        for (Room room : rooms) {
            room.isValidRoom();
            roomNames.add(room.getName());
        }
        for (Room room : rooms) {
            for (Direction direction : room.getDirections()) {
                direction.isValidDirection();
                    if (!roomNames.contains(direction.getRoom())) {
                        throw new JsonSyntaxException("Direction leads to invalid room.");
                    }
            }
            for (Item item : room.getItems()) {
                item.isValidItem();
            }
        }

        currentRoom = layout.getRooms().get(0); // to hold the current room.
        nextRoom = null; // to hold the next room for my updateCurrentRoom method.
        gameState = new GameState(currentRoom, new ArrayList<>());
        done = false; // boolean to control status of game.
        locationHistory.add(layout.getRooms().get(0).getName());
    }

    /**
     * Console method in the game class that calls on the game.
     */
    public void console() throws FileNotFoundException {
        variable(DEFAULT_JSON);
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

    /**
     * Method that executes users' input commands
     * @param command as a GameCommand
     * @return
     */
    public boolean inputExecute(GameCommand command) {

        String name = command.getCommandName();
        String value = command.getCommandValue();
        boolean isCommandValid = false;

        if (name.equalsIgnoreCase("quit") ||
            name.equalsIgnoreCase("exit")) { // quitting the game.
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

            nextRoom = gameState.updateCurrentRoom(gameState, layout, value, gameState.getCurrentLocation());
            if (nextRoom == null) {
                System.out.println("You can't go " + value + "!");
            } else {
                gameState.setCurrentLocation(nextRoom);
                isCommandValid = true;
                if (!locationHistory.contains(nextRoom.getName())) {
                    locationHistory.add(nextRoom.getName());
                }

                System.out.println(gameState.getCurrentLocation().getDescription());
                if (!(gameState.getCurrentLocation().getName()
                    .equalsIgnoreCase(layout.getEndingRoom()))) {
                    gameState.getCurrentLocation().printAvailableDirections();
                    gameState.getCurrentLocation().printAvailableItems(gameState.getCurrentLocation());
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

        // winning case
        if (gameState.getCurrentLocation().getName().equalsIgnoreCase(layout.getEndingRoom())) {
            System.out.println("Congrats! You successfully reached the ending room. Game Over :)");
            done = true;
        }
        return isCommandValid;
    }
}