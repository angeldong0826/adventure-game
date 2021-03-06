package student.server;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import student.adventure.GameCommand;
import student.adventure.GameEngine;
import student.adventure.GameState;
import student.adventure.Item;

public class HendrickService implements AdventureService {
    private int id;
    private Map<Integer, GameEngine> map;

    public HendrickService() {
        id = 0;
        map = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public Map<Integer, GameEngine> getMap() {
        return map;
    }

    @Override
    public void reset() {
        System.out.println("Reset");
        map.clear();
        id = 0;
    }

    @Override
    public int newGame() throws AdventureException, FileNotFoundException {
        GameEngine gameEngine = new GameEngine();
        try {
            System.out.println("New Game");
            map.put(id, gameEngine);
        } catch (Exception e) {
            throw new AdventureException("Adventure Exception");
        }
        return id++;
    }

    @Override
    public GameStatus getGame(int id) {
        GameStatus gameStatus;
        if (map.containsKey(id)) {
            GameEngine gameEngine = map.get(id);
            GameState gameState = new GameState(gameEngine.getCurrentRoom(), gameEngine.gameState
                .getInventory());
            this.id = id;
            String message = gameEngine.gameState.getCurrentLocation().getDescription();
            String imageUrl = gameEngine.gameState.getCurrentLocation().getImageUrl();
            String videoUrl = gameEngine.gameState.getCurrentLocation().getVideoUrl();
            AdventureState state = new AdventureState(gameEngine.locationHistoryToString(), gameState.inventoryToString());
            List<String> directions = gameEngine.gameState.getCurrentLocation().directionToList();
            List<String> items = gameEngine.gameState.getCurrentLocation().itemToList();

            List<String> dropOptions = new ArrayList<>();
            for (Item inventory: gameEngine.gameState.getInventory()) {
                dropOptions.add(inventory.getItemName());
            }

            Map<String, List<String>> commandOptions = new HashMap<>();
            commandOptions.put("go", directions);
            commandOptions.put("take", items);
            commandOptions.put("drop", dropOptions);

            gameStatus = new GameStatus(false, id, message, imageUrl, videoUrl, state, commandOptions);
        } else {
            gameStatus = new GameStatus(true, id, "","","",new AdventureState("", ""), new HashMap<>());
        }
        return gameStatus;
    }

    @Override
    public boolean destroyGame(int id) {
        System.out.println("Destroy Game");
        return map.remove(id, map.get(id));
    }

    @Override
    public void executeCommand(int id, Command command) {
        System.out.println("Execute Command");
        GameCommand gameCommand =  new GameCommand(command.getCommandName(),
            command.getCommandValue());
        map.get(id).inputExecute(gameCommand);
    }
}
