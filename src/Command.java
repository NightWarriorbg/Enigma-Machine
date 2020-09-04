/**
 * Command class
 */
public class Command {

    /**
     * The name of the command
     */
    private String name;

    /**
     * Number of arguments a command has
     */
    private int argsNumber;

    /**
     * The description of the command
     */
    private String description;

    /**
     * Constructor of the Command class
     * @param name - the name of the command
     * @param argsNumber - the number of arguments
     * @param description - the description of the command
     */
    public Command(String name, int argsNumber, String description) {
        setName(name);
        setArgsNumber(argsNumber);
        setDescription(description);
    }

    /**
     * Get method for name property
     * @return the name of the command
     */
    public String getName() {
        return name;
    }

    /**
     * Set method for name property
     * @param name - the new name of the command
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get method for args property
     * @return the arguments of the command
     */
    public int getArgsNumber() {
        return argsNumber;
    }

    /**
     * Set method for the args property
     * @param argsNumber - the new a number of arguments
     */
    public void setArgsNumber(int argsNumber) {
        this.argsNumber = argsNumber;
    }

    /**
     * Get method for description property
     * @return the description of the command
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set method for description property
     * @param description - the new description of the command
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
