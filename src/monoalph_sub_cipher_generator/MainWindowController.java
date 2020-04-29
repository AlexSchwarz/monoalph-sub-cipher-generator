package monoalph_sub_cipher_generator;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MainWindowController {
    @FXML TextArea textAreaInputMessage;
    @FXML TextArea textAreaOutputMessage;
    @FXML TextArea textAreaInfo;
    @FXML TextArea textAreaCipherAlphabet;
    @FXML TextField textFieldKeyWord;
    CipherGenerator cipherGenerator;

    @FXML
    public void initialize() {
        cipherGenerator = new CipherGenerator();
        textFieldKeyWord.textProperty().addListener((observable, oldValue, newValue) -> setCipherMapping(textFieldKeyWord.getText()));
        cipherGenerator.getInfoEvent().addListener(event -> setTextAreaInfo(cipherGenerator.getInfoText()));
    }

    private void setCipherMapping(String keyWord) {
        textAreaInfo.clear();
        try {
            cipherGenerator.setCipherMapping(keyWord);
            textAreaCipherAlphabet.setText(cipherGenerator.getCipherMappingToString());
        } catch (IllegalArgumentException e){
            setTextAreaInfo("-> " + e.getMessage());
            textAreaCipherAlphabet.clear();
        }
    }

    @FXML
    public void close() { Platform.exit();}

    @FXML
    public void clearTextFieldKeyWord() {
        textFieldKeyWord.clear();
    }

    @FXML
    public void clearTextAreaMessageInput() {
        textAreaInputMessage.clear();
        textAreaOutputMessage.clear();
    }

    public void setTextAreaInfo(String string) {
        textAreaInfo.appendText(string + "\n");
    }

    @FXML
    public void clearAllText() {
        textAreaOutputMessage.clear();
        textAreaInputMessage.clear();
        textAreaInfo.clear();
        textFieldKeyWord.clear();
    }

    @FXML void displayAboutText() {
        textAreaInfo.clear();
        setTextAreaInfo("Description:\nA monoalphabetic substitution cipher encrypts a message by generating a new alphabet" +
                " using a personal keyword.\nLetters in the keyword are put to the front of the new alphabet," +
                " which pushes back any remaining letters.\nUsage:\nEnter a keyword at the top of the window" +
                " and watch the new alphabet be generated.\nOnce you are happy with your keyword you can enter a " +
                " message to be encrypted.\nPress 'Generate' to create the cipher message");
    }

    @FXML
    public void generateMessage() {
        textAreaOutputMessage.clear();
        textAreaInfo.clear();
        String inputMessage = textAreaInputMessage.getText();
        try {
            textAreaOutputMessage.setText(cipherGenerator.generateMessage(inputMessage));
        } catch (IllegalArgumentException e) {
            setTextAreaInfo("-> " + e.getMessage());
            textAreaOutputMessage.clear();
        }
    }
}