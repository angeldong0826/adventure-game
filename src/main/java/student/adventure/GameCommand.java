package student.adventure;

/**
 * GameCommand class that processes information from user command input.
 */
public class GameCommand {

    private final String commandName;
    private final String commandValue;

    /**
     * Constructor that initializes variables command name and command value.
     * @param setCommandName as a String
     * @param setCommandValue as a String
     */
    public GameCommand(String setCommandName, String setCommandValue) {
        commandName = setCommandName;
        commandValue = setCommandValue;
    }

    public String getCommandName() {
        return commandName;
    }

    public String getCommandValue() {
        return commandValue;
    }

    /**
     * Constructor that splits input command into name and value.
     * @param command as a String
     */
    public GameCommand(String command) {
        String[] splitCommand = command.split("\\s+"); // to hold input split by whitespaces in an arraylist
        String name = "";
        String value = "";

        if (splitCommand.length > 0) {
            name = splitCommand[0];
            value = splitCommand[splitCommand.length - 1];
        }
        commandName = name;
        commandValue = value;
    }
}
