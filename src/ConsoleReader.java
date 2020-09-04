import java.util.ArrayList;

/**
 * THIS CLASS IS NOT FINISHED!
 */
public class ConsoleReader {

    /**
     * Initializing constants for the different colors that can be used in the console
     */
    public static final String COLOR_RESET = "\u001B[0m";
    public static final String COLOR_BLACK = "\u001B[30m";
    public static final String COLOR_RED = "\u001B[31m";
    public static final String COLOR_GREEN = "\u001B[32m";
    public static final String COLOR_YELLOW = "\u001B[33m";
    public static final String COLOR_BLUE = "\u001B[34m";
    public static final String COLOR_PURPLE = "\u001B[35m";
    public static final String COLOR_CYAN = "\u001B[36m";
    public static final String COLOR_WHITE = "\u001B[37m";

    /**
     * The input of the console
     */
    private String input;

    /**
     * The current command of the ConsoleReader
     */
    private String command;

    /**
     * The arguments of the current command
     */
    private String[] commandArgs;

    /**
     * The database with available commands
     */
    private ArrayList<Command> commands;

    /**
     * An instance of the EnigmaMachine class
     */
    EnigmaMachine enigmaMachine;

    /**
     * The constructor of the ConsoleReader class
     * @param input - the input of the console
     */
    public ConsoleReader(String input) {
        commands = new ArrayList<>();
        enigmaMachine = new EnigmaMachine();
        setInput(input);
        command = input.split(" ")[0]; // Gets the first element of the array
        commandArgs = input.split("(?<=\\\\s\\\\S{1,100})\\\\s"); // Splits the array after the second space on every space
        addCommands();
    }

    /**
     * Empty constructor
     */
    public ConsoleReader() {
    }

    /**
     * Set method for input property
     * @param input - the new input of the console
     */
    private void setInput(String input) {
        this.input = input;
    }

    /**
     * The welcome message of the console
     */
    public void welcomeMessage() {
        System.out.println("Welcome to the console interface of the Enigma Machine. From here you can add plugs, type and specify the initial position of the rotors.");
        System.out.printf("For more information type %s help %s. \n", COLOR_GREEN, COLOR_RESET);
    }

    /**
     * Adds all the commands that will be used in the console with a suitable description
     */
    private void addCommands() {
        commands.add(new Command("help", 0,"Displays all available commands and their descriptions."));
        commands.add(new Command("exit", 0, "Terminates the console."));
        commands.add(new Command("add_plug", 2, "Adds a new plug to the plugboard. Ex: 'add_plug A A' - creates a new plugboard with characters 'A' and 'A'."));
    }

    /**
     * Checking whether the input is correct
     */
    private void verifyInput() {
        for (Command command : commands) {
            if (!command.getName().equals(this.command)) {
                System.out.printf(" %s Invalid command. Please type %s help %s for more info.", COLOR_RED, COLOR_GREEN, COLOR_RED);
            } else if (command.getArgsNumber() != commandArgs.length) {
                System.out.printf(" %s Invalid number of arguments. Please type %s help %s for more info.", COLOR_RED, COLOR_GREEN, COLOR_RED);
            }
        }
    }

    /**
     * Initializing all the different commands in the console implementation
     */
    public void initialiseCommands() {
        verifyInput();
        if (this.command.equals("help")) {
            commandHelp();
        } else if (this.command.equals("add_plugin")) {
            commandAddPlug(commandArgs[0].charAt(0), commandArgs[1].charAt(0));
        }
    }

    /**
     * Adds a new plug to the Enigma Machine
     * @param char1 - first character
     * @param char2 - second character
     */
    public void commandAddPlug(char char1, char char2) {
        enigmaMachine.addPlug(char1, char2);
    }

    /**
     * Shows all the available commands in the console and their descriptions
     */
    private void commandHelp() {
        System.out.println("All the available commands in the console are: ");
        for (Command command : commands) {
            System.out.printf("Command: %s | Description: %s \n", command.getName(), command.getDescription());
        }
    }

    private void commandAddRotorType(String type) {

    }
}
