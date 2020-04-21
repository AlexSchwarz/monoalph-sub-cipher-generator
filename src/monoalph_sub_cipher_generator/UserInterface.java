package monoalph_sub_cipher_generator;

import java.util.Scanner;

public class UserInterface {

    public void displayText(String text) {
        System.out.println(text);
    }

    public void displayInfo(String text) {
        System.out.println("INFO: " + text + "\n");
    }

    public void displayError(String text) {
        System.out.println("ERROR: " + text + "\n");
    }

    public String getInput() {
        return readInput();
    }

    private String readInput() {
        Scanner scanner = new Scanner(System.in);
        String consoleInput = "";
        consoleInput += scanner.nextLine();
        return consoleInput;
    }
}
