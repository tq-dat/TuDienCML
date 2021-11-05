import java.util.ArrayList;

public class Dictionary {
    private ArrayList<Word> words = new ArrayList<Word>();

    public ArrayList<Word> getDic() {
        return words;
    }

    public void setDic(Word word) {
        this.words.add(word);
    }


}
