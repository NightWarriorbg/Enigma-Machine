/**
 * The class of the basic rotor
 */
public class BasicRotor extends Rotor {

    /**
     * The inverse map of thr basic rotor
     */
    private int [] inverseMapping = new int[ROTORSIZE];

    /**
     * Mapping for BasicRotor of type "I"
     */
    protected int[] I = {4, 10, 12, 5, 11, 6, 3, 16, 21, 25, 13, 19, 14, 22, 24, 7, 23, 20, 18, 15, 0, 8, 1, 17, 2, 9};

    /**
     * Mapping for BasicRotor of type "II"
     */
    protected int[] II = {0, 9, 3, 10, 18, 8, 17, 20, 23, 1, 11, 7, 22, 19, 12, 2, 16, 6, 25, 13, 15, 24, 5, 21, 14, 4};

    /**
     * Mapping for BasicRotor of type "III"
     */
    protected int[] III = {1, 3, 5, 7, 9, 11, 2, 15, 17, 19, 23, 21, 25, 13, 24, 4, 8, 22, 6, 0, 10, 12, 20, 18, 16, 14};

    /**
     * Mapping for BasicRotor of type "IV"
     */
    protected int[] IV = {4, 18, 14, 21, 15, 25, 9, 0, 24, 16, 20, 8, 17, 7, 23, 11, 13, 5, 19, 6, 10, 3, 2, 12, 22, 1};

    /**
     * Mapping for BasicRotor of type "V"
     */
    protected int [] V = {21, 25, 1, 17, 6, 8, 19, 24, 20, 15, 18, 3, 13, 7, 11, 23, 0, 22, 12, 9, 16, 14, 5, 4, 2, 10};

    /**
     * The constructor of the BasicRotor class
     * @param type - the type of the rotor
     */
    public BasicRotor(String type) {
        super(type);
        initialise(type);
    }



    /**
     * {@inheritDoc}
     */
    @Override
    public void initialise(String type) {

        // Checking which rotor is which and adding the correct mapping for it
        if (name[0].equals(type)) {
            mapping = I;
        } else if(name[1].equals(type)) {
            mapping = II;
        } else if(name[2].equals(type)) {
            mapping = III;
        } else if(this.name[3].equals(type)) {
            mapping = IV;
        } else if (name[4].equals(type)){
            mapping = V;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int substitute(int n) {
        return performSubstitution(n, mapping);
    }

    /**
     * Substitutes the letter back using an inverse mapping
     * @param n - the index of the letter that needs to be substituted back
     * @return the index of the letter before substitution
     */
    public int substituteBack(int n) {
        return performSubstitution(n, createInverseMapping());
    }

    /** @TODO: Fix this code when I have more time.
     * NB! - Not working properly!!!!
     * Creates a new array with the new mapping after adding the elements in a rearranged way
     * @param n - the index of the letter to substitute
     * @param mapping - the mapping used to perform the process
     * @return the rearranged array
        private int performSubstitution(int n, int mapping[]) {
        // Check if the position is zero and just return this position in the mapping
        if (getPosition() == 0) {
            return mapping[n];
        } else {
            // If the position is not zero move all elements to the left in a new array
            int[] newMapping = new int[ROTORSIZE];
            int j = getPosition() - 1;
            for (int i = mapping.length - 1; i > mapping.length - 1 - getPosition(); i--) {
                newMapping[j] = mapping[i];
                j--;
            }
            j = getPosition();
            for (int i = 0; i < mapping.length - getPosition(); i++) {
                newMapping[j] = mapping[i];
                j++;
            }
            return newMapping[n];
        }
    }*/

    /**
     * Performs a substitution of a number using a mapping
     * @param n - the number to be substituted
     * @param mapping - the mapping used for the substitution
     * @return the substituted number
     */
    private int performSubstitution(int n, int mapping[]) {
        // Initializing the position
        int position = n - getPosition();

        if (0 > position) {
            position += ROTORSIZE;
        }

        int newPosition = mapping[position] + getPosition();

        if (newPosition >= ROTORSIZE) {
            newPosition -= ROTORSIZE;
        }

        return newPosition;
    }

    /**
     * Creates an inverse mapping
     * @return an inverse map
     */
    public int[] createInverseMapping() {
        for (int i = 0; i < mapping.length; i++) {
            inverseMapping[mapping[i]] = i;
        }
        return inverseMapping;
    }

    /**
     * Rotates the position of the rotor
     */
    public void rotate() {
        if (getPosition() == ROTORSIZE) {
            setPosition(0);
        }
        setPosition(getPosition()+1);
    }


}
