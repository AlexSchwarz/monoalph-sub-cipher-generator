package monoalph_sub_cipher_generator;

public class App {
    private Config config;
    private UserInterface userInterface;
    private boolean running = true;

    public static void main(String[] args) {
        App app = new App();
        app.init();
        app.run();
    }

    public App() {
        config = new Config();
        userInterface = new UserInterface();
    }

    private void init() {
        userInterface.displayText("\nMONOALPHABETIC SUBSTITUTION CIPHER");
    }

    private void run() {
        while(running) {
            System.out.println("\nEncode a message! \n");
            System.out.print("Message Input: ");
            String inputMessage = userInterface.getInput();
            System.out.print("Key Word Input: ");
            String inputKeyWord = userInterface.getInput();
            try {
                CipherGenerator cipherGenerator = new CipherGenerator(inputKeyWord, inputMessage);
                userInterface.displayText(cipherGenerator.getCipherMessage());
            } catch (IllegalArgumentException e) {
                userInterface.displayError(e.getMessage());
            }
        }
    }
}
