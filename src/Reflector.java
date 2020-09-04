/**
 * Reflector class
 */
public class Reflector extends Rotor {

    /**
     * Main constructor of the Reflector class
     * @param type - the type of the Reflector
     */
    public Reflector(String type) {
        super(type);
        initialise(type);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void initialise(String type) {

        // The types of reflector mapping
        int ReflectorI[] = {24, 17, 20, 7, 16, 18, 11, 3, 15, 23, 13, 6, 14, 10, 12, 8, 4, 1, 5, 25, 2, 22, 21, 9, 0, 19};
        int ReflectorII[]= {5, 21, 15, 9, 8, 0, 14, 24, 4, 3, 17, 25, 23, 22, 6, 2, 19, 10, 20, 16, 18, 1, 13, 12, 7, 11};

        // Checking which reflector is which and adding the correct mapping for it
        if (name[5].equals(type)) {
            mapping = ReflectorI;
        } else if (name[6].equals(type)) {
            mapping = ReflectorII;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int substitute(int n) {
        return mapping[n];
    }


}
