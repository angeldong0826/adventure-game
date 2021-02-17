package student.adventure;

import com.google.gson.Gson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

public class AdventureTest {
    private static final int ROOM_COUNT = 19;

    Gson gson;
    Reader reader;
    GameEngine gameEngine;
    Layout layout;
    GameEngine game;
    Room currentRoom;
    Room nextRoom;

    @Before
    public void setUp() throws FileNotFoundException {
        gson = new Gson();
        reader = new FileReader("src/main/resources/hendrickhouse.json");
        layout = gson.fromJson(reader, Layout.class);
        gameEngine = new GameEngine();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void sanityCheck() {
        layout.getRooms().get(ROOM_COUNT); // checks if room count is out of bounds and not match up with the json file.
    }

//    @Test(expected = FileNotFoundException.class)
//    public void checkInvalidJsonFile() {
//        reader = new FileReader("src/main/java/hendrickhouse.json");
//    }

    @Test(expected = NullPointerException.class)
    public void test_nullNextRoom() {
        nextRoom = GameState.updateCurrentRoom(layout, )
    }

    @Test
    public void test_goNorth() {
        nextRoom = GameState.updateCurrentRoom(layout, "go noRtH", currentRoom);
        assertEquals("Main Entrance", nextRoom.getName());
    }

    @Test
    public void test_roomNameDisplay() {

    }

    @Test
    public void test_descriptionDisplay() {
    }

    @Test
    public void test_currentRoom() {
    }

    @Test
    public void test_invalidCommand() {
    }

    @Test
    public void test_itemAvailability() {
    }

    @Test
    public void test_unavailableItem() {
    }

    @Test
    public void test_inventoryAccuracy() {
    }

    @Test
    public void test_quit() {
    }

    @Test
    public void test_exit() {
    }

    @Test
    public void test_quitOrExit() {
    }

    @Test
    public void test_examine() {
    }

    @Test
    public void test_take() {
    }

    @Test
    public void test_takeWhenNotAvailable() {
    }

    @Test
    public void test_doubleTake() {
    }

    @Test
    public void test_drop() {
    }

    @Test
    public void test_dropWhenNotAcquired() {
    }

    @Test
    public void test_dropWhenInventoryEmpty() {
    }

    @Test
    public void test_doubleDrop() {
    }
}