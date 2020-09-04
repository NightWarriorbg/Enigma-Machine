/**
 * Plug class
 */
public class Plug {

    /**
     * The first end socket of the plug.
     */
    private char end1;

    /**
     * The second end socket of the plug.
     */
    private char end2;

    /**
     * Get method for end1 property.
     * @return the first end of the plug
     */
    public char getEnd1() {
        return this.end1;
    }

    /**
     * Set method for end1 property.
     * @param end1 - first end of the plug
     */
    public void setEnd1(char end1) {
        this.end1 = end1;
    }

    /**
     * Get method for end2 property.
     * @return second end of the plug
     */
    public char getEnd2() {
        return this.end2;
    }

    /**
     * Set method for end2 property.
     * @param end2 - second end of the plug
     */
    public void setEnd2(char end2) {
        this.end2 = end2;
    }

    /**
     * Main constructor for Plug class..
     * @param end1 - first end of the plug
     * @param end2 - second end of the plug
     */
    public Plug(char end1, char end2) {
        setEnd1(end1);
        setEnd2(end2);
    }

    /**
     * Encodes a given character.
     * @param letterIn - the letter to be encoded
     * @return the encoded letter
     */
    public char encode(char letterIn) {
        if (getEnd1() != letterIn && getEnd2() != letterIn) {
            return letterIn;
        } else if (letterIn == getEnd1()) {
            return getEnd2();
        }
        return getEnd1();
    }

    /**
     * Checks if any of the sockets is already in use.
     * @param plugin - a plug
     * @return whether the plug clashes with another plug
     */
    public boolean clashesWith(Plug plugin) {
        if (getEnd1() == plugin.getEnd1() || getEnd1() == plugin.getEnd2() || getEnd2() == plugin.getEnd1() || getEnd2() == plugin.getEnd2()) {
            return true;
        }
        return false;
    }

}
