package monoalph_sub_cipher_generator;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MainWindowController {
    @FXML TextArea textAreaInputMessage;
    @FXML TextArea textAreaOutputMessage;
    @FXML TextArea textAreaInfo;
    @FXML TextField textFieldKeyWord;

    @FXML
    public void close() { Platform.exit();}

    @FXML
    public void clearTextFieldKeyWord() {
        textFieldKeyWord.clear();
    }

    @FXML
    public void clearTextAreaMessageInput() {
        textAreaInputMessage.clear();
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
        setTextAreaInfo("Place Holder");
    }

    @FXML
    public void generate() {
        textAreaInfo.clear();
        textAreaOutputMessage.clear();

        CipherGenerator cipherGenerator = new CipherGenerator();
        setListener(cipherGenerator);

        String inputKeyWord = textFieldKeyWord.getText();
        String inputMessage = textAreaInputMessage.getText();

        try {
            textAreaOutputMessage.setText(cipherGenerator.generate(inputKeyWord, inputMessage));
        } catch (IllegalArgumentException e) {
            setTextAreaInfo("-> " + e.getMessage());
        }
    }

    private void setListener(CipherGenerator cipherGenerator) {
        cipherGenerator.getInfoEvent().addListener(event -> setTextAreaInfo(cipherGenerator.getInfoText()));
    }



}
