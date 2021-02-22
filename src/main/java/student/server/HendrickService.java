package student.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import student.adventure.GameCommand;
import student.adventure.GameEngine;
import student.adventure.Item;

public class HendrickService implements AdventureService {
    private int id = 0;
    private Map<Integer, GameEngine> map = new HashMap<>();

    @Override
    public void reset() {
        System.out.println("Reset");
        map.clear();
        id = 0;
    }

    @Override
    public int newGame() throws AdventureException {
        GameEngine gameEngine = new GameEngine();
        try {
            System.out.println("New Game");
            map.put(id, gameEngine);
        } catch (Exception e) {
//            throw new AdventureException("Adventure Exception");
        }
        return id++;
    }

    @Override
    public GameStatus getGame(int id) {
        GameStatus gameStatus;
        if (map.containsKey(id)) {
            GameEngine gameEngine = map.get(id);
            this.id = id;
            String message = gameEngine.gameState.getCurrentLocation().getDescription();
            String imageUrl = gameEngine.gameState.getCurrentLocation().getImageUrl();
//          videoUrl = gameState.getCurrentLocation().getVideoUrl();
            String videoUrl = "";
            Map<String, List<String>> commandOptions = new HashMap<>();

            List<String> directions = (gameEngine.gameState.getCurrentLocation().directionToList());

            List<String> takeOptions = new ArrayList<>();
            takeOptions.add(gameEngine.gameState.getCurrentLocation().
                returnAvailableItems(gameEngine.gameState.getCurrentLocation()));

            List<String> dropOptions = new ArrayList<>();
            for (Item item: gameEngine.gameState.getInventory()) {
                dropOptions.add(item.getItemName());
            }

            commandOptions.put("go", directions);
            commandOptions.put("take", takeOptions);
            commandOptions.put("drop", dropOptions);

            gameStatus = new GameStatus(false, id, message, imageUrl, videoUrl, new AdventureState(), commandOptions);
        } else {
            gameStatus = new GameStatus(true, id, "","","",new AdventureState(), new HashMap<>());
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
        GameCommand gameCommand = new GameCommand(command.getCommandName(),
            command.getCommandValue());
        map.get(id).inputExecute(gameCommand);
    }
}
