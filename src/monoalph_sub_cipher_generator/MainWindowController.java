package monoalph_sub_cipher_generator;

import javafx.application.Platform;
import javafx.fxml.FXML;
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
    public void generate() {
        textAreaInfo.clear();
        textAreaOutputMessage.clear();

        CipherGenerator cipherGenerator = new CipherGenerator();
        setListener(cipherGenerator);

        String inputKeyWord = textFieldKeyWord.getText();
        String inputMessage = textAreaInputMessage.getText();

        try {
            cipherGenerator.generate(inputKeyWord, inputMessage);
            textAreaOutputMessage.setText(cipherGenerator.getCipherMessage());
        } catch (IllegalArgumentException e) {
            setTextAreaInfo("-> " + e.getMessage());
        }
    }

    private void setListener(CipherGenerator cipherGenerator) {
        cipherGenerator.getInfoEvent().addListener(event -> setTextAreaInfo(cipherGenerator.getInfoText()));
    }



}
