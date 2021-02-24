package student.adventure;

import com.google.gson.Gson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import com.google.gson.JsonSyntaxException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import javax.validation.constraints.Null;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for overall game backend.
 */
public class AdventureTest {
    private static final int ROOM_COUNT = 19;

    Gson gson;
    Reader reader;
    GameEngine gameEngine = new GameEngine();
    Layout layout;
    GameState gameState;
    Room currentRoom;
    Room nextRoom;
    int id;
    GameCommand gameCommand;

    public AdventureTest() throws FileNotFoundException {
    }

    @Before
    public void setUp() throws IOException {
        gson = new Gson();
        reader = new FileReader(gameEngine.DEFAULT_JSON);
        layout = gson.fromJson(reader, Layout.class);
        currentRoom = layout.getRooms().get(0);
        nextRoom = null;
        gameState = new GameState(currentRoom, new ArrayList<>());
        gameEngine = new GameEngine();
        gameEngine.variable(gameEngine.DEFAULT_JSON);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void sanityCheck() {
        layout.getRooms().get(ROOM_COUNT); // checks if room count is out of bounds and not match up with the json file.
    }

    // Json test units
    @Test(expected = NullPointerException.class)
    public void test_invalidJson() throws FileNotFoundException {
        gameEngine.variable("src/main/resources/siebel.json");
    }

    @Test(expected = NullPointerException.class)
    public void test_nullJson() throws FileNotFoundException {
        gameEngine.variable(null);
    }

    @Test(expected = FileNotFoundException.class)
    public void test_invalidJsonName() throws FileNotFoundException {
        gameEngine.variable("thisisaninvalidjson");
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_emptyJsonName() throws FileNotFoundException {
        gameEngine.variable("");
    }

    @Test(expected = NullPointerException.class)
    public void test_emptyJsonFile() throws FileNotFoundException {
        gameEngine.variable("src/main/resources/empty.json");
    }

    @Test(expected = JsonSyntaxException.class)
    public void test_malfunctionJson() throws FileNotFoundException {
        gameEngine.variable("src/main/resources/malformedjson.json");
    }

    @Test(expected = JsonSyntaxException.class)
    public void test_badHendrickJson() throws FileNotFoundException {
        gameEngine.variable("src/main/resources/badhendrickhouse.json");
    }

    @Test
    public void test_validJson() throws FileNotFoundException {
        gameEngine.variable("src/main/resources/hendrickhouse.json");
    }

    // test white spaces
    @Test
    public void test_whiteSpaceBeforeCommand() {
        gameCommand = new GameCommand("   go  nORtH");
        gameEngine.inputExecute(gameCommand);
        assertEquals("Main Entrance", gameEngine.gameState.getCurrentLocation().getName());
    }

    @Test
    public void test_whiteSpaceBetweenCommand() {
        gameCommand = new GameCommand("go            nORtH");
        gameEngine.inputExecute(gameCommand);
        assertEquals("Main Entrance", gameEngine.gameState.getCurrentLocation().getName());
    }

    @Test
    public void test_whiteSpaceAfterCommand() {
        gameCommand = new GameCommand("go nORtH       ");
        gameEngine.inputExecute(gameCommand);
        assertEquals("Main Entrance", gameEngine.gameState.getCurrentLocation().getName());
    }

    @Test
    public void test_goValidDirection() {
        gameCommand = new GameCommand("go nORtH");
        gameEngine.inputExecute(gameCommand);
        assertEquals("Main Entrance", gameEngine.gameState.getCurrentLocation().getName());
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