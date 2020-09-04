/**
 * Rotor class
 */

public abstract class Rotor {

    /**
     * The name of the rotor used to identify the type
     */
    public String type;

    /**
     * The type of rotor
     */
    protected String name[] = {"I", "II", "III", "IV","V", "ReflectorI", "ReflectorII"};

    /**
     * The current position of the rotor with value between
     * 0 and 25 representing the letters of the alphabet.
     */
    private int position;

    /**
     * The mapping of the rotor.
     */
    protected int[] mapping = new int[ROTORSIZE];

    /**
     * Defines the number of positions on a rotor (26).
     */
    protected static final int ROTORSIZE = 26;

    /**
     * The main constructor of the Rotor class
     * @param type - the type of the rotor
     */
    public Rotor(String type) {
        this.type = type;
    }

    /**
     * Get method for name property
     * @return the name of the rotor
     */
    public String getType() {
        return type;
    }

    /**
     * Set method for name property
     * @param type - the new name of he rotor
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Get method for position property.
     * @return currentPosition
     */
    public int getPosition() {
        return this.position;
    }

    /**
     * Set method for position property.
     * @param position
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * Initialises the rotor deciding what kind of rotor it is using its type
     * @param type
     */
    public abstract void initialise(String type);

    /**
     * Changes the mapping of the rotor considering its position
     * and returns the "n" element using the new mapping.
     */
    public abstract int substitute(int n);

}
