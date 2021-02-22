package student.adventure;

/**
 * GameCommand class that processes information from user command input.
 */
public class GameCommand {

    private final String commandName;
    private final String commandValue;

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

    public GameCommand(String command) {
        String[] splitCommand = command
            .split("\\s+"); // to hold input split by whitespaces in an arraylist
        String name = "";
        String value = "";

        if (splitCommand.length > 0) {
            name = splitCommand[0];
            value = splitCommand[splitCommand.length - 1];

//            for (int index = 1; index < splitCommand.length; index++) {
//                value.append(splitCommand[index]);
//                // value.append(splitCommand[index] + " ");
//            }
        }
        commandName = name;
        commandValue = value;
    }
}
