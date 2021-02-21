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
import student.adventure.GameEngine;
import student.adventure.Room;

public class HendrickService implements AdventureService {

    private int id;
    private Map<Integer, GameEngine> map;
    //private Map<String, List<String>> commandOptions;

    private final static String DATABASE_URL = "jdbc:sqlite:src/main/resources/adventure.db";
    private Connection dbConnection;

    /**
     * Constructor that initializes game instance ID and map.
     */
    public HendrickService() {
        id = 0;
        map = new HashMap<>();

        try {
            dbConnection = DriverManager.getConnection(DATABASE_URL);
        } catch (SQLException e) {
            dbConnection = null;
        }
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

    }

//    @Override
//    public SortedMap<String, Integer> fetchLeaderboard() {
//        System.out.println("Fetch Leaderboard");
//        try {
//            Statement statement = dbConnection.createStatement();
//            statement.execute("SELECT * FROM board_");
//        } catch (SQLException e) {
//            return null;
//        }
//    }
//
//    /**
//     * Helper method for sorting the leaderboard for fetchLeaderboard.
//     *
//     * @param boardToSet to be set
//     * @return sorted leader board as a linked hashmap
//     */
//    private LinkedHashMap<String, Integer> sortLeaderBoard(ResultSet boardToSet) {
//        Map<String, Integer> board = new HashMap<>();
//
//        try {
//            while (boardToSet.next()) {
//                String name = boardToSet.getString(1);
//                int score = boardToSet.getInt(2);
//                board.put(name, score);
//            }
//        } catch (SQLException e) {
//            return null;
//        }
//        return board.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(
//            Collectors
//                .toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
//    }
}
