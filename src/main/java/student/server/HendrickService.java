package student.server;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.stream.Collectors;
import javax.swing.plaf.nimbus.State;
import student.adventure.GameCommand;
import student.adventure.GameEngine;
import student.adventure.Room;

public class HendrickService implements AdventureService {

    private int id;
    private Map<Integer, GameEngine> map;


    /**
     * Constructor that initializes game id and game map.
     */
    public HendrickService() {
        id = 0;
        map = new HashMap<>();
    }

    @Override
    public void reset() {
        System.out.println("Reset");
        map.clear();
        id = 0;
    }

    @Override
    public int newGame() throws AdventureException {
        try {
            System.out.println("New Game");
            map.put(id, new GameEngine(id));
        } catch (Exception e) {
            throw new AdventureException("Adventure Exception");
        }
        return id++;
    }

    @Override
    public GameStatus getGame(int id) {
        return map.get(id).getGameStatus();
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
