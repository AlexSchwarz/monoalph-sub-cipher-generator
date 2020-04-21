package monoalph_sub_cipher_generator;

public class CipherGenerator {
    private String keyWord;
    private String message;
    private char[] standardAlphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};

    public CipherGenerator(String keyWord, String message) throws IllegalArgumentException {
        initKeyWord(keyWord);
        initMessage(message);
    }

    private void initKeyWord(String string) {
        if(string.isEmpty() || string == null) {
            throw new IllegalArgumentException("Invalid Keyword: Empty or Null");
        } else {
            this.keyWord = keyWord;
        }
    }

    private void initMessage(String message) {
        if(message.isEmpty() || message == null) {
            throw new IllegalArgumentException("Invalid Message: Empty or Null");
        } else {
            this.message = message;
        }
    }

    private void generateCipherAlphabet() {

    }
}
