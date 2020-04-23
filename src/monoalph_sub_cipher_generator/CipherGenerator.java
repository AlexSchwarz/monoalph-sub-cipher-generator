package monoalph_sub_cipher_generator;

import javafx.beans.property.SimpleBooleanProperty;

import java.util.ArrayList;
import java.util.HashMap;

public class CipherGenerator {
    private String infoText;
    private final SimpleBooleanProperty infoEvent = new SimpleBooleanProperty(true);
    private final static char[] standardAlphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

    public CipherGenerator() {
        infoText = "No action performed";
    }

    public String generate(String keyWordInput, String messageInput) throws IllegalArgumentException {
        validateKeyWord(keyWordInput);
        String keyWord = keyWordInput.toUpperCase();
        ValidateMessage(messageInput);
        String message = messageInput.toUpperCase();
        HashMap<Character, Character> cipherMapping = generateCipherMapping(generateCipherAlphArray(keyWord));
        return generateCipherMessage(message, cipherMapping);
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

    private void validateKeyWord(String keyWord) {
        setInfoText("Validating keyword...");
        if(keyWord.matches("^[a-zA-Z]+$")) {
            setInfoText("-> Keyword valid");
        } else if(keyWord.isEmpty() || keyWord == null) {
            throw new IllegalArgumentException("Invalid Keyword: Empty or Null");
        } else {
            throw new IllegalArgumentException("Invalid Keyword: Illegal characters");
        }
    }

    private void ValidateMessage(String message) {
        setInfoText("Validating message...");
        if(message.isEmpty() || message == null) {
            throw new IllegalArgumentException("Invalid Message: Empty or Null");
        } else {
            setInfoText("-> Message valid");
        }
    }

    private ArrayList<Character> generateCipherAlphArray(String keyWord) {
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

    private HashMap<Character, Character> generateCipherMapping(ArrayList<Character> cipherAlphArray) {
        setInfoText("Generating alphabet mapping...");
        HashMap<Character, Character> cipherMapping = new HashMap<>();
        for(int i = 0; i < cipherAlphArray.size(); i++) {
            cipherMapping.put(standardAlphabet[i], cipherAlphArray.get(i));
        }
        return cipherMapping;
        //setInfoText("-> Generated cipher mapping: ");
        //System.out.println(cipherMapping);
    }

    private String generateCipherMessage(String message, HashMap<Character, Character> cipherMapping) {
        setInfoText("Generate cipher message...");
        String cipherMessage;
        char[] messageArray = message.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(char character : messageArray) {
            sb.append(cipherMapping.getOrDefault(character, character));
        }
        cipherMessage = sb.toString();
        setInfoText("-> Cipher message generation successful");
        return cipherMessage;
    }
}
