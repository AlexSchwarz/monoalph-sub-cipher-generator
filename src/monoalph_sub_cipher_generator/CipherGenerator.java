package monoalph_sub_cipher_generator;

import javafx.beans.property.SimpleBooleanProperty;

import java.util.ArrayList;
import java.util.HashMap;

public class CipherGenerator {
    private String keyWord;
    private String message;
    private String cipherMessage;
    private String infoText;
    HashMap<Character, Character> cipherMapping = new HashMap<>();

    private final SimpleBooleanProperty infoEvent = new SimpleBooleanProperty(true);
    private final static char[] standardAlphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

    public CipherGenerator() {

    }

    public void generate(String keyWord, String message) throws IllegalArgumentException {
        initKeyWord(keyWord);
        initMessage(message);
        generateCipherMapping(generateCipherAlphArray());
        generateCipherMessage();
    }

    private void setInfoText(String text) {
        this.infoText = text;
        triggerEvent(infoEvent);
    }

    private void triggerEvent(SimpleBooleanProperty booleanProperty) {
        booleanProperty.set(!booleanProperty.getValue());
    }

    public SimpleBooleanProperty getInfoEvent() {
        return infoEvent;
    }

    public String getInfoText() {
        return infoText;
    }

    private void initKeyWord(String keyWord) {
        setInfoText("Validating keyword...");
        if(keyWord.matches("^[a-zA-Z]+$")) {
            this.keyWord = keyWord.toUpperCase();
            setInfoText("-> Keyword valid");
        } else if(keyWord.isEmpty() || keyWord == null) {
            throw new IllegalArgumentException("Invalid Keyword: Empty or Null");
        } else {
            throw new IllegalArgumentException("Invalid Keyword: Illegal characters");
        }
    }

    private void initMessage(String message) {
        setInfoText("Validating message...");
        if(message.isEmpty() || message == null) {
            throw new IllegalArgumentException("Invalid Message: Empty or Null");
        } else {
            this.message = message.toUpperCase();
            setInfoText("-> Message valid");
        }
    }

    private ArrayList<Character> generateCipherAlphArray() {
        setInfoText("Generating cipher alphabet...");
        ArrayList<Character> cipherAlphArray = new ArrayList<>();
        for(char character : keyWord.toCharArray()) {
            cipherAlphArray.add(character);
        }
        for(char character : standardAlphabet) {
            cipherAlphArray.add(character);
        }
        cipherAlphArray = removeDuplicatesChars(cipherAlphArray);
        return cipherAlphArray;
    }

    private ArrayList<Character> removeDuplicatesChars(ArrayList<Character> listWithDuplicates) {
        ArrayList<Character> listWithoutDuplicates = new ArrayList<>();
        for(char character : listWithDuplicates) {
            if(!listWithoutDuplicates.contains(character)) {
                listWithoutDuplicates.add(character);
            }
        }
        return listWithoutDuplicates;
    }

    private void generateCipherMapping(ArrayList<Character> cipherAlphArray) {
        setInfoText("Generating alphabet mapping...");
        for(int i = 0; i < cipherAlphArray.size(); i++) {
            cipherMapping.put(standardAlphabet[i], cipherAlphArray.get(i));
        }
        //setInfoText("-> Generated cipher mapping: ");
        //System.out.println(cipherMapping);
    }

    private void generateCipherMessage() {
        setInfoText("Generate cipher message...");
        char[] messageArray = message.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(char character : messageArray) {
            sb.append(cipherMapping.getOrDefault(character, character));
        }
        cipherMessage = sb.toString();
        setInfoText("-> Cipher message generation successful");
    }

    public String getCipherMessage() {
        return cipherMessage;
    }
}
