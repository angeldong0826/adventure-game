package student.adventure;

import com.google.gson.Gson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import com.google.gson.JsonSyntaxException;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Null;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import student.server.Command;

/**
 * Test class for overall game backend.
 */
public class AdventureTest {
    private static final int ROOM_COUNT = 19;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

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
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() throws FileNotFoundException {
        System.setOut(originalOut);
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

    // test command input white space & case
    @Test
    public void test_whiteSpaceBeforeCommand() {
        gameCommand = new GameCommand("   go  north");
        gameEngine.inputExecute(gameCommand);
        assertEquals("Main Entrance", gameEngine.gameState.getCurrentLocation().getName());
    }

    @Test
    public void test_whiteSpaceBetweenCommand() {
        gameCommand = new GameCommand("go            north");
        gameEngine.inputExecute(gameCommand);
        assertEquals("Main Entrance", gameEngine.gameState.getCurrentLocation().getName());
    }

    @Test
    public void test_whiteSpaceAfterCommand() {
        gameCommand = new GameCommand("go north       ");
        gameEngine.inputExecute(gameCommand);
        assertEquals("Main Entrance", gameEngine.gameState.getCurrentLocation().getName());
    }

    @Test
    public void test_whiteSpaceCombo() {
        gameCommand = new GameCommand("  go     north       ");
        gameEngine.inputExecute(gameCommand);
        assertEquals("Main Entrance", gameEngine.gameState.getCurrentLocation().getName());
    }

    @Test
    public void test_ignoreCase() {
        gameCommand = new GameCommand("gO NORtH");
        gameEngine.inputExecute(gameCommand);
        assertEquals("Main Entrance", gameEngine.gameState.getCurrentLocation().getName());
    }

    // test display
    @Test
    public void test_startingRoomNameDisplay() {
        String name = layout.getRooms().get(0).getName();
        String expected = "Green Street";
        assertEquals(expected, name);
    }

    @Test
    public void test_startingRoomDescriptionDisplay() {
        String description = layout.getRooms().get(0).getDescription();
        String expected = "You are on Green Street, outside Hendrick House.";
        assertEquals(expected, description);
    }

    @Test
    public void test_validDirectionDisplay() {
        String direction = currentRoom.printAvailableDirections();
        assertEquals("From here, you can go: North", direction);
    }

    @Test
    public void test_availableItemDisplay() {
        String item = currentRoom.printAvailableItems(currentRoom);
        assertEquals("Items available: GlassDoor", item);
    }

    // test updates
    @Test
    public void test_currentRoom() {
        gameCommand = new GameCommand("go nORtH");
        gameEngine.inputExecute(gameCommand);
        assertEquals("Main Entrance", gameEngine.gameState.getCurrentLocation().getName());
    }

    // test input command
    @Test(expected = NullPointerException.class)
    public void test_nullCommand() {
        gameEngine.inputExecute(new GameCommand(null));
    }

    @Test(expected = NullPointerException.class)
    public void test_emptyCommand() {
        gameEngine.inputExecute(new GameCommand(""));
    }

    @Test
    public void test_invalidCommand() {
        gameEngine.inputExecute(new GameCommand("helpmeiamdying"));
        assertFalse(gameEngine.isDone());
    }

    @Test
    public void test_quit() {
        gameEngine.inputExecute(new GameCommand("quit"));
        assertTrue(gameEngine.isDone());
    }

    @Test
    public void test_exit() {
        gameEngine.inputExecute(new GameCommand("exit"));
        assertTrue(gameEngine.isDone());
    }

    @Test
    public void test_goValidDirection() {
        assertTrue(gameEngine.inputExecute(new GameCommand("go north")));
    }

    @Test
    public void test_goInvalidDirection() {
        assertFalse(gameEngine.inputExecute(new GameCommand("go south")));
    }

    @Test
    public void test_examine() {
        gameEngine.inputExecute(new GameCommand("examine"));
        String output = "You are on Green Street, outside Hendrick House.\n"
            + "From here, you can go: North\n"
            + "Items available: GlassDoor\n";
        assertEquals(output, outContent.toString());
    }

    @Test
    public void test_takeAvailableItem() {
        gameEngine.inputExecute(new GameCommand("take","glassdoor"));
        assert gameEngine.getGameState().getInventory().get(0).getItemName().equalsIgnoreCase("glassdoor");
    }

    @Test
    public void test_takeUnavailableItem() {
        gameEngine.inputExecute(new GameCommand("take", "urmom"));
        String output = "There is no urmom in the room.\n";
        assertEquals(output, outContent.toString());
    }

    @Test
    public void test_dropAvailableItem() {
        gameEngine.inputExecute(new GameCommand("drop", "glassdoor"));
        assert !(gameEngine.getGameState().inventoryToString().contains("glassdoor"));
        assert !(gameState.getCurrentLocation().getItems().contains("glassdoor"));
    }

    @Test
    public void test_dropUnavailableItem() {
        gameEngine.inputExecute(new GameCommand("drop", "urmom"));
        String output = "You don't have urmom!\n";
        assertEquals(output, outContent.toString());
    }
}