import java.io.*;
import java.util.ArrayList;

public class EnigmaFile {

    /**
     * The file path of the resources folder
     */
    private static final String FILE_PATH = "res/";

    /**
     * The input file of the machine
     */
    private File inputFile;

    /**
     * The output file of the machine
     */
    private File outputFile;

    /**
     * The name of the input file of the machine
     */
    private String inputFileName;

    /**
     * The name of the output file of the machine
     */
    private String outputFileName;

    /**
     * The input database of the machine
     */
    private ArrayList<String> inputDatabase;

    /**
     * The output database of the machine
     */
    private ArrayList<String> outputDatabase;

    /**
     * An instance of the EnigmaMachine class
     */
    private EnigmaMachine enigmaMachine;

    /**
     * The constructor of the EnigmaFile class.
     * @param inputFileName - the name of the input file
     * @param outputFileName - the name of the output file
     */
    public EnigmaFile(String inputFileName, String outputFileName) {
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
        inputDatabase = new ArrayList<>();
        outputDatabase = new ArrayList<>();
        enigmaMachine = new EnigmaMachine();
        inputFile = new File(FILE_PATH + inputFileName);
        outputFile = new File(FILE_PATH + outputFileName);
    }

    /**
     * Get method for inputFile property
     * @return the input file
     */
    public File getInputFile() {
        return inputFile;
    }

    /**
     * Get method for outputFile property
     * @return the output file
     */
    public File getOutputFile() {
        return outputFile;
    }

    /**
     * Get method for inputDatabase property
     * @return the input database of the machine
     */
    public ArrayList<String> getInputDatabase() {
        return inputDatabase;
    }

    /**
     * Get method for outputDatabase property
     * @return the output database of the machine
     */
    public ArrayList<String> getOutputDatabase() {
        return outputDatabase;
    }

    /**
     * Reads every line of a given file and puts it in an ArrayList database
     * @param file - the file to be read
     */
    public void readFile(File file) {

        // Creating anew instance of ConsoleReader class for colored messages
        ConsoleReader consoleReader = new ConsoleReader();
        try {
            // Checking if the file exists or is a directory
            if (!file.exists() && !file.isDirectory()) {
                // Creates the file if it doesn't exist
                file.createNewFile();
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();

            // Loops through the whole file till the end of it
            while (line != null) {
                inputDatabase.add(line);
                line = reader.readLine();
            }

            // Closes the stream
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.printf("%s Unable to open file %s '%s' ", consoleReader.COLOR_RED, inputFileName, consoleReader.COLOR_RESET);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the content of the output database and puts it into a given file
     * @param file - the file to write in
     */
    public void writeFIle(File file) {

        // Creating anew instance of ConsoleReader class for colored messages
        ConsoleReader consoleReader = new ConsoleReader();
        try {

            // Checking if the file exists or is a directory
            if (!file.exists() && !file.isDirectory()) {
                // Creates the file if it doesn't exist
                file.createNewFile();
            }

            FileOutputStream fout = new FileOutputStream(file);
            PrintStream pout = new PrintStream(fout);

            // Loops through every element of the output database and puts it into a file
            for (String element : outputDatabase) {
                pout.println(element);
            }

            // Closing both streams
            pout.close();
            fout.close();

        } catch (FileNotFoundException e) {
            System.out.printf("%s Unable to open file %s '%s' ", consoleReader.COLOR_RED, inputFileName, consoleReader.COLOR_RESET);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
