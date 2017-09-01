import org.apache.log4j.Logger;

public class WordGuessClient {

    private final static Logger logger = Logger.getLogger(WordGuessClient.class);

    private final static Settings settings = new Settings();

    public static void main(final String[] args) {

        displayControls();
    }

    private static void displayControls() {
        System.out.println("\nControls: new - starts a new game");
        System.out.println("          hint - provides a hint");
        System.out.println("          exit - exits the program");
        System.out.println("         [word] - enter anything else to make a guess");
    }
}
