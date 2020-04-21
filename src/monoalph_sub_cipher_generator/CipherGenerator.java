package monoalph_sub_cipher_generator;

import java.util.ArrayList;
import java.util.HashMap;

public class CipherGenerator {
    private String keyWord;
    private String message;
    private String cipherMessage;
    private final static char[] standardAlphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    HashMap<Character, Character> cipherMapping = new HashMap<>();

    public CipherGenerator(String keyWord, String message) throws IllegalArgumentException {
        initKeyWord(keyWord);
        initMessage(message);
        generateCipherMapping(generateCipherAlphArray());
        generateCipherMessage();
    }

    private void initKeyWord(String keyWord) {
        System.out.println("Validating key word...");
        if(keyWord.isEmpty() || keyWord == null || keyWord.matches("")) {
            throw new IllegalArgumentException("Invalid Keyword: Empty or Null");
        } else {
            this.keyWord = keyWord.toUpperCase();
            System.out.println("-> Key word valid");
        }
    }

    private void initMessage(String message) {
        System.out.println("Validating message...");
        if(message.isEmpty() || message == null) {
            throw new IllegalArgumentException("Invalid Message: Empty or Null");
        } else {
            this.message = message.toUpperCase();
            System.out.println("-> Message valid");
        }
    }

    private ArrayList<Character> generateCipherAlphArray() {
        System.out.println("Generating cipher alphabet...");
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
        System.out.println("Generating map between standard and cipher alphabet...");
        for(int i = 0; i < cipherAlphArray.size(); i++) {
            cipherMapping.put(standardAlphabet[i], cipherAlphArray.get(i));
        }
        System.out.print("-> Generated cipher mapping: ");
        System.out.println(cipherMapping);
    }

    private void generateCipherMessage() {
        System.out.println("Generate cipher message...");
        char[] messageArray = message.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(char character : messageArray) {
            sb.append(cipherMapping.getOrDefault(character, character));
        }
        cipherMessage = sb.toString();
        System.out.print("-> Cipher message: ");
    }

    public String getCipherMessage() {
        return cipherMessage;
    }
}
