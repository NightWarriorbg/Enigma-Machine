import java.util.Scanner;

public class EnigmaMachine {

    /**
     * The plugboard of the enigma machine
     */
    private PlugBoard plugBoard;

    /**
     * An array with basic rotors
     */
    private BasicRotor basicRotors[];

    /**
     * The reflector of he enigma machine
     */
    private Reflector reflector;

    /**
     * The constructor of the enigma machine.
     * It creates a new plugboard and a basic rotor.
     */
    public EnigmaMachine() {
        plugBoard = new PlugBoard();
        basicRotors = new BasicRotor[3];
    }

    /**
     * Adds a plug to the plugboard
     * @param end1 - first character
     * @param end2 - second character
     */
    public void addPlug(char end1, char end2) {
        plugBoard.addPlug(end1, end2);
    }

    /**
     * Removes all plugs from the plugboard
     */
    public void clearPlugboard() {
        plugBoard.clear();
    }

    /**
     * Adds a new basic rotor to the enigma machine
     * @param basicRotor - the basic rotor to be added
     * @param slot - on which slot should the rotor be added on
     */
    public void addRotor(BasicRotor basicRotor, int slot) {
        basicRotors[slot] = basicRotor;
    }

    /**
     * Gets a rotor in a given slot
     * @param slot - the slot of the rotor
     * @return a rotor on the given slot
     */
    public BasicRotor getRotor(int slot) {
        return basicRotors[slot];
    }

    /**
     * Adds a reflector to the enigma machine
     * @param reflector
     */
    public void addReflector(Reflector reflector) {
        this.reflector = reflector;
    }

    /**
     * Get method for reflector property
     * @return the reflector of the enigma machine
     */
    public Reflector getReflector() {
        return this.reflector;
    }

    /**
     * Sets the position to the rotor by a given one on a given slot
     * @param slot - the slot of the rotor
     * @param position - the position of the rotor
     */
    public void setPosition(int slot, int position) {
        BasicRotor basicRotor = getRotor(slot);
        basicRotor.setPosition(position);
    }

    /**
     * Receives a character and converts it to an integer
     * @param number - a number to be converted
     * @return a character
     */
    private char convertIntToChar(int number) {
        return (char) (number + 'A');
    }

    /**
     * Receives a character and converts it to an integer
     * @param character - a character to be converted
     * @return an integer
     */
    private int convertCharToInt(char character) {
        return character - 'A';
    }

    /**
     * Uses all rotors and reflectors in the machine to encode a given character
     * @param character - the character that will be encoded in the machine
     * @return the encoded character
     */
    public char encodeLetter(char character) {

        // Creating all 3 rotors and setting their slots
        BasicRotor basicRotor1 = getRotor(0);
        BasicRotor basicRotor2 = getRotor(1);
        BasicRotor basicRotor3 = getRotor(2);

        // Inputs a character to the plugboard
        char encodedLetter = plugBoard.substitute(character);

        // The character passes through all 3 basic rotors
        int rotor1Substitution = basicRotor1.substitute(convertCharToInt(encodedLetter));
        int rotor2Substitution = basicRotor2.substitute(rotor1Substitution);
        int rotor3Substitution = basicRotor3.substitute(rotor2Substitution);

        // Adding the character that is outputed from the last rotor to the reflector
        int reflectorSubstitution = reflector.substitute(rotor3Substitution);

        // The character passes thought all the same rotors again in an inverse order
        rotor3Substitution = basicRotor3.substituteBack(reflectorSubstitution);
        rotor2Substitution = basicRotor2.substituteBack(rotor3Substitution);
        rotor1Substitution = basicRotor1.substituteBack(rotor2Substitution);

        // Getting the character to the plugboard again from the last rotor
        encodedLetter = plugBoard.substitute(convertIntToChar(rotor1Substitution));

        // Rotates the position of the  basic rotor
        basicRotor1.rotate();

        return  encodedLetter;

    }

    /**
     * Reads input from the user and adds it to the Enigma Machine
     */
    public void input() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("You can add as many plugs as you want.");
        System.out.println("Type 'stop' to terminate the process.");
        System.out.println("Write the two characters of every plug, separated by space and hit enter to add the next plug. ");

        // Counter for Plug number
        int i = 1;

        // Create an infinite loop that will make it possible for the user to add as many plugs as the he/she want
        while (true) {
            System.out.printf("Plug №%s \n", i);
            String input = scanner.nextLine();

            // If "stop" is written the loop is terminated
            if (input.equalsIgnoreCase("stop")) {
                break;
            }

            addPlug(input.charAt(0), input.charAt(2));

            i++;
        }

        System.out.println("Write the type of the first rotor (I, II, III, IV, V): ");
        String input = scanner.nextLine();

        BasicRotor basicRotor1 = new BasicRotor(input);
        addRotor(basicRotor1,0);
        System.out.println("Set the position of the rotor(0-25): ");
        String pos = scanner.nextLine();
        setPosition(0, Integer.parseInt(pos));

        System.out.println();

        System.out.println("Write the type of the second rotor (I, II, III, IV, V): ");
        input = scanner.nextLine();

        BasicRotor basicRotor2 = new BasicRotor(input);
        addRotor(basicRotor2,1);
        System.out.println("Set the position of the rotor(0-25): ");
        pos = scanner.nextLine();
        setPosition(1, Integer.parseInt(pos));

        System.out.println();

        System.out.println("Write the type of the second rotor (I, II, III, IV, V): ");
        input = scanner.nextLine();

        BasicRotor basicRotor3 = new BasicRotor(input);
        addRotor(basicRotor3,2);
        System.out.println("Set the position of the rotor(0-25): ");
        pos = scanner.nextLine();
        setPosition(2, Integer.parseInt(pos));

        System.out.println();

        System.out.println("Write the type of the reflector (ReflectorI, ReflectorII): ");
        input = scanner.nextLine();
        Reflector reflector = new Reflector(input);
        addReflector(reflector);

        System.out.println();

        System.out.println("Message to encode:");

        // Makes the message upper case in order to be correctly encoded
        String messageToEncode = scanner.nextLine().toUpperCase();

        // Encodes the message and shows it in the console
        encodeMessage(messageToEncode);
    }

    /**
     * Runs all the tests of the enigma machine
     */
    public void start() {
        // First test
        System.out.println("Test1:");
        test1();

        System.out.println();

        // Second test
        System.out.println("Test2:");
        test2();

        System.out.println();

        // EnigmaFile test
        testEnigmaFile();

        //System.out.println();

        // Third test
        System.out.println("Test3:");
        test3();

        System.out.println();

        // Creating a new Bombe
        Bombe bombe = new Bombe();

        // First test for the Bombe
        System.out.println("First Bombe test");
        bombe.missingPlugsDecryption();

        System.out.println();

        // Second test for the Bombe
        System.out.println("Second Bombe test");
        bombe.missingRotorsDecryption();

        System.out.println();

        // Third test for the Bombe
        System.out.println("Third Bombe test");
        bombe.missingTypeDecryption();

        System.out.println();

    }

    /**
     * First test of the machine
     */
    private void test1() {

        // Adding 3 plugs
        addPlug('A', 'M');
        addPlug('G', 'L');
        addPlug('E', 'T');

        // Creating a basic rotor of type "I"
        BasicRotor basicRotor1 = new BasicRotor("I");

        // Adding a rotor to the machine on slot "0"
        addRotor(basicRotor1, 0);

        // Setting the position on slot "0" to "6"
        setPosition(0, 6);

        // Creating a basic rotor of type "II"
        BasicRotor basicRotor2 = new BasicRotor("II");

        // Adding a rotor to the machine on slot "1"
        addRotor(basicRotor2, 1);

        // Setting the position on slot "1" to "12"
        setPosition(1, 12);

        // Creating a basic rotor of type "III"
        BasicRotor basicRotor3 = new BasicRotor("III");

        // Adding a rotor to the machine on slot "2"
        addRotor(basicRotor3, 2);

        // Setting the position on slot "2" to "5"
        setPosition(2, 5);

        // Creating a reflector of type "ReflectorI"
        Reflector reflector = new Reflector("ReflectorI");

        // Adding the reflector to the machine
        addReflector(reflector);

        // Encodes and outputs the message
        encodeMessage("GFWIQH");

        // Clearing the plugboard
        clearPlugboard();

        System.out.println();
    }

    /**
     * Second test of the machine
     */
    private void test2() {

        // Adding 4 plugs
        addPlug('B', 'C');
        addPlug('R', 'I');
        addPlug('S', 'M');
        addPlug('A', 'F')   ;

        // Creating a basic rotor of type "IV"
        BasicRotor basicRotor1 = new BasicRotor("IV");

        // Adding a rotor to the machine on slot "0"
        addRotor(basicRotor1, 0);

        // Setting the position of slot "0" to "23"
        setPosition(0, 23);

        // Creating a basic rotor of type "V"
        BasicRotor basicRotor2 = new BasicRotor("V");

        // Adding a rotor to the machine on slot "1"
        addRotor(basicRotor2, 1);

        // Setting the position of slot "1" to "4"
        setPosition(1, 4);

        // Creating a basic rotor of type "V"
        BasicRotor basicRotor3 = new BasicRotor("II");

        // Adding a rotor to the machine on slot "2"
        addRotor(basicRotor3, 2);

        // Setting the position of slot "2" to "9"
        setPosition(2, 9);

        // Creating a reflector of type "ReflectorII"
        Reflector reflector = new Reflector("ReflectorII");

        // Adding the reflector to the machine
        addReflector(reflector);

        // Encodes and outputs the message
        encodeMessage("GACIG");

        // Clearing the plugboard
        clearPlugboard();

        System.out.println();
    }

    /**
     * Test for the file I/O system
     */
    private void testEnigmaFile() {

        // Creating a new instance of the EnigmaFile class
        EnigmaFile enigmaFile = new EnigmaFile("input.txt", "output.txt");

        // Adding 3 plugs
        addPlug('A', 'M');
        addPlug('G', 'L');
        addPlug('E', 'T');

        // Creating a basic rotor of type "I"
        BasicRotor basicRotor1 = new BasicRotor("I");

        // Adding a rotor to the machine on slot "0"
        addRotor(basicRotor1, 0);

        // Setting the position on slot "0" to "6"
        setPosition(0, 6);

        // Creating a basic rotor of type "II"
        BasicRotor basicRotor2 = new BasicRotor("II");

        // Adding a rotor to the machine on slot "1"
        addRotor(basicRotor2, 1);

        // Setting the position on slot "1" to "12"
        setPosition(1, 12);

        // Creating a basic rotor of type "III"
        BasicRotor basicRotor3 = new BasicRotor("III");

        // Adding a rotor to the machine on slot "2"
        addRotor(basicRotor3, 2);

        // Setting the position on slot "2" to "5"
        setPosition(2, 5);

        // Creating a reflector of type "ReflectorI"
        Reflector reflector = new Reflector("ReflectorI");

        // Adding the reflector to the machine
        addReflector(reflector);

        // Reads the information for the given file
        enigmaFile.readFile(enigmaFile.getInputFile());

        // Goes through every element of the input database, decodes it and adds it to te output database
        for (String element : enigmaFile.getInputDatabase()) {
            String encodedMessage = "";
            for(int i = 0; i < element.length(); i++) {
                encodedMessage += encodeLetter(element.charAt(i));
            }
            enigmaFile.getOutputDatabase().add(encodedMessage);
        }

        // Puts the content of the output database into a file
        enigmaFile.writeFIle(enigmaFile.getOutputFile());

        // Clears the plugboard
        clearPlugboard();

    }

    private void test3() {

        // Add 1 plug
        addPlug('Q', 'F');

        // Creating a turnover rotor of type "I"
        TurnoverRotor turnoverRotor1 = new TurnoverRotor("I");

        // Adding a rotor to the machine on slot "0"
        addRotor(turnoverRotor1, 0);

        // Setting the position of the turnover rotor on slot "0" to "23"
        setPosition(0, 23);

        // Creating a turnover rotor of type "II"
        TurnoverRotor turnoverRotor2 = new TurnoverRotor("II");

        // Adding a rotor to the machine on slot "0"
        addRotor(turnoverRotor2, 1);

        // Setting the position of the turnover rotor on slot "1" to "11"
        setPosition(1, 11);

        // Creating a turnover rotor of type "III"
        TurnoverRotor turnoverRotor3 = new TurnoverRotor("III");

        // Adding a rotor to the machine on slot "0"
        addRotor(turnoverRotor3, 2);

        // Setting the position of the turnover rotor on slot "2" to "7"
        setPosition(2, 7);

        // Setting the next rotor of the first turnover rotor
        turnoverRotor1.setNextRotor(turnoverRotor2);

        // Setting the next rotor of the second turnover rotor
        turnoverRotor2.setNextRotor(turnoverRotor3);

        // Creating a reflector of type "ReflectorI"
        Reflector reflector = new Reflector("ReflectorI");

        // Adding the reflector to the machine
        addReflector(reflector);

        // Encodes and outputs the message
        encodeMessage("OJVAYFGUOFIVOTAYRNIWJYQWMXUEJGXYGIFT");

        // Clearing the plugboard
        clearPlugboard();

        System.out.println();

    }

    /**
     * Input test
     */
    public void inputTest() {

    }

    /**
     * The test for the extension
     *  TODO: Add check whether the elements are initialised in the right order
     *  TODO: Finish the Console interface later this month when I have more time
     */
    private void testConsoleReader() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (true) {

            ConsoleReader consoleReader = new ConsoleReader(input);
            consoleReader.welcomeMessage();
            consoleReader.initialiseCommands();


            if (input.equals("exit")) {
                break;
            }
        }
    }

    public void encodeMessage(String message) {
        for (int i = 0; i < message.length(); i++) {
            System.out.print(encodeLetter(message.charAt(i)));
        }
    }
}
