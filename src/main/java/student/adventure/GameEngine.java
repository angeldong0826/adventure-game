package student.adventure;

import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;


public class GameEngine {
    Layout layout;
    List<String> input; // to hold input in an arraylist of Strings.

    public void Game() throws FileNotFoundException {
        Gson gson = new Gson();
        Reader reader = new FileReader("src/main/resources/hendrickhouse.json");
        layout = gson.fromJson(reader, Layout.class);
        Room startingRoom = layout.getRooms().get(0);
        Room currentRoom = startingRoom;
        boolean done = true;


        while(!done) {
            System.out.println(startingRoom.getDescription());
            System.out.println("From here, you can go: " + returnAvailableDirections(currentRoom) + ".");
            System.out.println("Items visible: " + );
            System.out.print("> ");
            Scanner scan = new Scanner(System.in);
            input.add(scan.next());
            if (input.get(0).equals("quit") || input.get(0).equals("exit")) { // quitting the game.
                done = false;
            }
            if (input.get(0).equals("go")) {
                if (returnAvailableDirections(currentRoom).contains(input.get(1))) {

                }
            }
        }
        // exit game.
    }

    // helper method that returns all available directions from a room.
    private List<String> returnAvailableDirections(Room room) {
        List<String> availableDirections = new ArrayList<>(); // to hold input room's available directions.
        for (int i = 0; i < layout.getRooms().size(); i++) {
            if (room.getName().equals(layout.getRooms().get(i).getName())) {
                availableDirections.add(layout.getRooms().get(i).getDirections().get(i).getDirectionName());
            }
        }
        return availableDirections;
    }
}
