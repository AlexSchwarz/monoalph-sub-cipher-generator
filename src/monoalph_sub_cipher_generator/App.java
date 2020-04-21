package monoalph_sub_cipher_generator;

public class App {
    private Config config;
    private UserInterface userInterface;

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
        userInterface.displayText("Monoalphabetic Substitution Cipher Generator");
    }

    private void run() {
        try {
            CipherGenerator cipherGenerator = new CipherGenerator(userInterface.getInput(), userInterface.getInput());
        } catch (IllegalArgumentException e) {
            userInterface.displayError(e.getMessage());
        }
    }
}
