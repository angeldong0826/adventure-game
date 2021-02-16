package student.adventure;

import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

/**
 * IMPLEMENT
 */
public class GameEngine {
    /**
     * IMPLEMENT
     * @throws FileNotFoundException
     */
    public void game() throws FileNotFoundException {
        Gson gson = new Gson();
        Reader reader = new FileReader("src/main/resources/hendrickhouse.json");
        Layout layout = gson.fromJson(reader, Layout.class);
        Room currentRoom = layout.getRooms().get(0);
        Room nextRoom = null;
        boolean done = false;
        List<String> inventories = new ArrayList<>();
        Scanner scan = new Scanner(System.in);

        while(!done) {
            System.out.println(currentRoom.getDescription());
            currentRoom.returnAvailableDirections();
            currentRoom.returnAvailableItems();
            System.out.print("> ");
            String input = scan.nextLine(); // to hold input as a String.
            String[] splitInput = input.split(" "); // to hold input split by whitespace in an arraylist.

            if (input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("exit")) { // quitting the game.
                System.out.println("You have " + input + " the game.");
                break;
            }

            if (input.equalsIgnoreCase("examine")) {
                continue;
            }

            if (!splitInput[0].equalsIgnoreCase("go") && !splitInput[0].equalsIgnoreCase("take") && !splitInput[0].equalsIgnoreCase("drop")) {
                System.out.println("Invalid command.");
                continue;
            }
            if (splitInput[0].equalsIgnoreCase("go")) {
                nextRoom = Helper.updateCurrentRoom(layout, splitInput[1], currentRoom);
                if (nextRoom == null) {
                    System.out.println("I can't go " + splitInput[1] + "!");
                    continue;
                }
                currentRoom = nextRoom;
            }
            if (splitInput[0].equalsIgnoreCase("take")) {

            }
        }
        // exit game.
    }
}
