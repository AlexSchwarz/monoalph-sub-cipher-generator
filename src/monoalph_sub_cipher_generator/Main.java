package monoalph_sub_cipher_generator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage primaryStage;

    public static void main(String[] args) {
            launch(args);
        }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        mainWindow();
    }

    private void mainWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
            Pane rootPane = loader.load();
            Scene scene = new Scene(rootPane);
            primaryStage.setMinWidth(350);
            primaryStage.setMinHeight(350);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}





/*
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
 */
