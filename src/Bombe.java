/**
 * Bombe class used for decrypting messages
 */
public class Bombe {

    /**
     * An instance of the EnigmaMachine class
     */
    private EnigmaMachine enigmaMachine;

    /**
     * Constructor for the Bombe class. It initializes the EnigmaMachine
     */
    public Bombe() {
        enigmaMachine = new EnigmaMachine();
    }

    /**
     * Uses brute-force to find the right combination of plugs that display the encoded message
     */
    public void missingPlugsDecryption() {
        // Creating a basic rotor of type "IV"
        BasicRotor basicRotor1 = new BasicRotor("IV");

        // Adding a rotor to the machine on slot "0"
        enigmaMachine.addRotor(basicRotor1, 0);

        // Setting the position on slot "0" to "8"
        enigmaMachine.setPosition(0, 8);

        // Creating a basic rotor of type "III"
        BasicRotor basicRotor2 = new BasicRotor("III");
        // Adding a rotor to the machine on slot "1"
        enigmaMachine.addRotor(basicRotor2, 1);

        // Setting the position on slot "1" to "4"
        enigmaMachine.setPosition(1, 4);

        // Creating a basic rotor of type "II"
        BasicRotor basicRotor3 = new BasicRotor("II");

        // Adding a rotor to the machine on slot "2"
        enigmaMachine.addRotor(basicRotor3, 2);

        // Setting the position on slot "2" to "21"
        enigmaMachine.setPosition(2, 21);

        // Creating a reflector of type "ReflectorI"
        Reflector reflector = new Reflector("ReflectorI");

        // Adding the reflector to the machine
        enigmaMachine.addReflector(reflector);

        for (char plug1 = 'A'; plug1 <= 'Z'; plug1++) {
            for (char plug2 = 'A'; plug2 <= 'Z'; plug2++) {

                enigmaMachine.clearPlugboard();

                enigmaMachine.addPlug('D', plug1);
                enigmaMachine.addPlug('S', plug2);

                // The message that will be encoded
                String lettersToBeEncoded = "JBZAQVEBRPEVPUOBXFLCPJQSYFJI";
                String encodedMessage = "";

                for (int i = 0; i < lettersToBeEncoded.length(); i++) {
                    encodedMessage += enigmaMachine.encodeLetter(lettersToBeEncoded.charAt(i));
                }

                if (encodedMessage.contains("ANSWER")) {
                    System.out.printf("Decoded message: %s\n" ,encodedMessage);
                    System.out.printf("Plug 1: D - %s\n", plug1);
                    System.out.printf("Plug 2: S - %s\n", plug2);
                    break;
                }

            }
        }

        // Clearing the plugboard
        enigmaMachine.clearPlugboard();
    }

    /**
     * Uses brute-force to find the right combination of rotors that display the encoded message
     */
    public void missingRotorsDecryption() {
        // Adding 2 plugs
        enigmaMachine.addPlug('H', 'L');
        enigmaMachine.addPlug('G', 'P');

        // Creating a basic rotor of type "V"
        BasicRotor basicRotor1 = new BasicRotor("V");

        // Adding a rotor to the machine on slot "0"
        enigmaMachine.addRotor(basicRotor1, 0);

        // Creating a basic rotor of type "III"
        BasicRotor basicRotor2 = new BasicRotor("III");

        // Adding a rotor to the machine on slot "1"
        enigmaMachine.addRotor(basicRotor2, 1);

        // Creating a basic rotor of type "II"
        BasicRotor basicRotor3 = new BasicRotor("II");

        // Adding a rotor to the machine on slot "1"
        enigmaMachine.addRotor(basicRotor3, 2);

        // Creating a reflector of type "ReflectorI"
        Reflector reflector = new Reflector("ReflectorI");

        // Adding the reflector to the machine
        enigmaMachine.addReflector(reflector);

        for (int positionI = 0; positionI < 26; positionI++) {
            for (int positionJ = 0; positionJ < 26; positionJ++) {
                for (int positionK = 0; positionK < 26; positionK++) {
                    // Setting the position on slot "0" to "positionI"
                    enigmaMachine.setPosition(0, positionI);

                    // Setting the position on slot "1" to "positionJ"
                    enigmaMachine.setPosition(1, positionJ);

                    // Setting the position on slot "1" to "positionJ"
                    enigmaMachine.setPosition(2, positionK);

                    // The message that will be encoded
                    String lettersToBeEncoded = "AVPBLOGHFRLTFELQEZQINUAXHTJMXDWERTTCHLZTGBFUPORNHZSLGZMJNEINTBSTBPPQFPMLSVKPETWFD";
                    String encodedMessage = "";

                    for (int i = 0; i < lettersToBeEncoded.length(); i++) {
                        encodedMessage += enigmaMachine.encodeLetter(lettersToBeEncoded.charAt(i));
                    }

                    if (encodedMessage.contains("ELECTRIC")) {
                        System.out.printf("Decoded message: %s\n" ,encodedMessage);
                        System.out.printf("Position for basic rotor 1: %s\n", positionI);
                        System.out.printf("Position for basic rotor 2: %s\n", positionJ);
                        System.out.printf("Position for basic rotor 3: %s\n", positionK);
                        break;
                    }
                }
            }
        }
    }

    /**
     * Uses brute-force to find the right combination of rotor types that display the encoded message
     */
    public void missingTypeDecryption() {
        // Adding 2 plugs
        enigmaMachine.addPlug('M', 'F');
        enigmaMachine.addPlug('O', 'I');

        // Creating a reflector of type "ReflectorI"
        Reflector reflector = new Reflector("ReflectorI");

        // Adding the reflector to the machine
        enigmaMachine.addReflector(reflector);

        String rotorTypes[] = {"I", "II", "III", "IV", "V"};

        for (int i = 0; i < rotorTypes.length; i++) {
            // Creating a basic rotor of type "rotorTypes[i]"
            BasicRotor basicRotor1 = new BasicRotor(rotorTypes[i]);

            // Adding a rotor to the machine on slot "0"
            enigmaMachine.addRotor(basicRotor1, 0);

            for (int j = 0; j < rotorTypes.length; j++) {
                // Creating a basic rotor of type "rotorTypes[j]"
                BasicRotor basicRotor2 = new BasicRotor(rotorTypes[j]);

                // Adding a rotor to the machine on slot "1"
                enigmaMachine.addRotor(basicRotor2, 1);

                for (int k = 0; k < rotorTypes.length; k++) {
                    enigmaMachine.setPosition(0, 22);
                    enigmaMachine.setPosition(1, 24);

                    // Creating a basic rotor of type "rotorTypes[k]"
                    BasicRotor basicRotor3 = new BasicRotor(rotorTypes[k]);

                    // Adding a rotor to the machine on slot "1"
                    enigmaMachine.addRotor(basicRotor3, 2);

                    // Setting the position on slot "1" to "23"
                    enigmaMachine.setPosition(2, 23);

                    // The message that will be encoded
                    String lettersToBeEncoded = "WMTIOMNXDKUCQCGLNOIBUYLHSFQSVIWYQCLRAAKZNJBOYWW";
                    String encodedMessage = "";

                    for (int l = 0; l < lettersToBeEncoded.length(); l++) {
                        encodedMessage += enigmaMachine.encodeLetter(lettersToBeEncoded.charAt(l));
                    }

                    if (encodedMessage.contains("JAVA")) {
                        System.out.printf("Encoded message: %s\n", encodedMessage);
                        System.out.printf("The of the first rotor : %s\n", rotorTypes[i]);
                        System.out.printf("The of the second rotor : %s\n", rotorTypes[j]);
                        System.out.printf("The of the third rotor : %s\n", rotorTypes[k]);
                        break;
                    }
                }
            }
        }
    }
}