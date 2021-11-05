import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Dictionary dictionary = new Dictionary();
        DictionaryCommandline dicCml = new DictionaryCommandline();

        dicCml.dictionaryAdvanced(dictionary);
    }
}

