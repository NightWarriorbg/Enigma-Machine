import java.util.ArrayList;

/**
 * Plug board class
 */
public class PlugBoard {

    /**
     * The database where all the plugs are stored in.
     */
    private ArrayList<Plug> plugs;

    /**
     * Get method for the size of the plugs.
     * @return the number of plugs
     */
    public int getNumPlugs() {
        return this.plugs.size();
    }

    /**
     * The default constructor of the Plug class
     */
    public PlugBoard() {
        plugs = new ArrayList<>();
    }

    /**
     * Adds a plug into the plug database if t
     * here aren't any sockets already in use.
     * @param end1 - first end of the plug
     * @param end2 - second end of the plug
     * @return whether you can add a plug or not
     */
    public boolean addPlug(char end1, char end2) {
        Plug newPlug  = new Plug(end1, end2);
        for (Plug plug : plugs) {
            if (plug.clashesWith(newPlug)) {
                return false;
            }
        }
        plugs.add(newPlug);
        return true;
    }

    /**
     * Removes all the plugs from the database
     */
    public void clear() {
        this.plugs.clear();
    }

    /**
     * Encodes a character into the Enigma machine.
     * If there is an appropriately connected
     * plug then the character returned is encoded,
     * otherwise it returns the character it was passed.
     * @param character - a character to be substituted
     * @return the substituted character
     */
    public char substitute(char character) {
        for (Plug plug : plugs) {
            character = plug.encode(character);
        }
        return character;
    }

}
