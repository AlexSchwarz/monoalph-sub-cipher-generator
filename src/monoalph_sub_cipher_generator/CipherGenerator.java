package monoalph_sub_cipher_generator;

import javafx.beans.property.SimpleBooleanProperty;

import java.util.ArrayList;
import java.util.HashMap;

public class CipherGenerator {
    private String infoText;
    HashMap<Character, Character> cipherMapping = new HashMap<>();
    private final SimpleBooleanProperty infoEvent = new SimpleBooleanProperty(true);
    private final static char[] standardAlphabetArray = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

    public CipherGenerator() {
        infoText = "No action performed";
    }

    public void setCipherMapping(String keyWord) {
        keyWord = keyWord.toUpperCase();
        validateKeyWord(keyWord);
        ArrayList<Character> cipherAlphArray = generateCipherAlphArray(keyWord);
        cipherMapping = generateCipherMapping(cipherAlphArray);
    }

    private void validateKeyWord(String keyWord) {
        setInfoText("Validating keyword...");
        if(keyWord.matches("^[a-zA-Z]+$")) {
            setInfoText("-> Keyword valid");
        } else if(keyWord.isEmpty() || keyWord == null) {
            cipherMapping.clear();
            throw new IllegalArgumentException("Invalid Keyword: Empty");
        } else {
            cipherMapping.clear();
            throw new IllegalArgumentException("Invalid Keyword: Illegal characters");
        }
    }

    private ArrayList<Character> generateCipherAlphArray(String keyWord) {
        setInfoText("Generating cipher alphabet...");
        ArrayList<Character> cipherAlphArray = new ArrayList<>();
        for(char character : keyWord.toCharArray()) {
            cipherAlphArray.add(character);
        }
        for(int i = standardAlphabetArray.length-1; i >= 0; i--) {
            cipherAlphArray.add(standardAlphabetArray[i]);
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
            cipherMapping.put(standardAlphabetArray[i], cipherAlphArray.get(i));
        }
        setInfoText("-> Generated mapping");
        return cipherMapping;
    }

    public String getCipherMappingToString() {
        StringBuilder standardAlphabet = new StringBuilder();
        StringBuilder cipherAlphabet = new StringBuilder();
        for(Character character : standardAlphabetArray) {
            standardAlphabet.append(character + " ");
            cipherAlphabet.append(cipherMapping.getOrDefault(character, '#') + " ");
        }
        return standardAlphabet.toString() + "\n" + cipherAlphabet;
    }

    public String generateMessage(String messageInput) throws IllegalArgumentException {
        String message = messageInput.toUpperCase().trim();
        ValidateMessage(message);
        return generateCipherMessage(message, cipherMapping);
    }

    private void ValidateMessage(String message) {
        setInfoText("Validating message...");
        if(message.isEmpty() || message == null) {
            throw new IllegalArgumentException("Invalid Message: Empty");
        } else {
            setInfoText("-> Message valid");
        }
    }

    private String generateCipherMessage(String message, HashMap<Character, Character> cipherMapping) {
        setInfoText("Generate cipher message...");
        char[] messageArray = message.toCharArray();
        StringBuilder sb = new StringBuilder();
        if(!cipherMapping.isEmpty()) {
            for (char character : messageArray) {
                sb.append(cipherMapping.getOrDefault(character, character));
            }
        } else {
            throw new IllegalArgumentException("No valid cipher mapping available");
        }
        setInfoText("-> Generated cipher message");
        return sb.toString();
    }

    //Info Text

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
}
