/**
 * The TurnoverRotor class
 */
public class TurnoverRotor extends BasicRotor {

    /**
     * The position of the turnover rotor
     */
    private int turnoverPosition;

    /**
     * Stores the next rotor if the rotor is placed on slot "0" or "1"
     */
    private BasicRotor nextRotor;

    /**
     * The constructor of the TurnoverRotor class
     * @param type - the type of the rotor
     */
    public TurnoverRotor(String type) {
        super(type);
        initialisePosition(type);
    }

    /**
     * Set method for nextRotor property
     * @param nextRotor - the next rotor
     */
    public void setNextRotor(BasicRotor nextRotor) {
        this.nextRotor = nextRotor;
    }

    /**
     * Initialises the position of the turnover rotor using its type
     * @param type - the type of the rotor
     */
    public void initialisePosition(String type) {
        int positions[] = {24, 12, 3, 17, 7};

        if (name[0].equals(type)) {
            turnoverPosition = positions[0];
        } else if (name[1].equals(type)) {
            turnoverPosition = positions[1];
        } else if (name[2].equals(type)) {
            turnoverPosition = positions[2];
        } else if (name[3].equals(type)) {
            turnoverPosition = positions[3];
        } else if (name[4].equals(type)) {
            turnoverPosition = positions[4];
        }

    }

    /**
     * Rotates the turnover rotor
     */
    public void rotate() {
        super.rotate();
        if (this.getPosition() == turnoverPosition && nextRotor != null) {
            nextRotor.rotate();
        }

    }
}
