
import java.io.IOException;

import java.util.Scanner;

public class DictionaryCommandline {
    public  Scanner sr = new Scanner(System.in);

    public void showAllWords(Dictionary dictionary) {
        System.out.println("No\t|English\t|Vietnamese");
        for (int i = 0; i < dictionary.getDic().size(); i++) {
            Word word = dictionary.getDic().get(i);
            System.out.print(i + " \t|" + word.getWord_target());
            for (int j = 0; j < 12 - word.getWord_target().length(); j++) System.out.print(" ");
            System.out.println("|" + word.getWord_explain());
        }
    }

    public void dictionarySearcher(Dictionary dictionary) {
        System.out.print("Nhập từ :");
        String s = sr.nextLine();
        System.out.println();

        for (int i = 0; i < dictionary.getDic().size(); i++) {
            if (s == dictionary.getDic().get(i).getWord_target().substring(0,s.length())) {
                System.out.println(dictionary.getDic().get(i).getWord_target());
            }
        }

    }


    public void dictionaryAdvanced(Dictionary dictionary) throws IOException {
        DictionaryManagement management = new DictionaryManagement();

        System.out.print(
                        "1. Thêm dữ liệu từ file\n" +
                        "2. In ra toàn bộ từ điển\n" +
                        "3. Tra từ \n" +
                        "4. Thêm từ\n" +
                        "5. Xóa từ\n" +
                        "6. Thoát\n" +
                        "Nhập lựa chọn của bạn :" );

        int x = sr.nextInt();
        System.out.println();

        switch (x) {
            case 1:
                management.insertFromFile(dictionary);
                dictionaryAdvanced(dictionary);
                break;
            case 2:
                showAllWords(dictionary);
                dictionaryAdvanced(dictionary);
                break;
            case 3:
                management.dictionaryLookup(dictionary);
                dictionaryAdvanced(dictionary);
                break;
            case 4:
                management.insertFromCommandline(dictionary);
                management.writerFile(dictionary);
                dictionaryAdvanced(dictionary);;
            case 5:
                management.deleteWord(dictionary);
                management.writerFile(dictionary);
                dictionaryAdvanced(dictionary);
                break;
            case 6:
                System.exit(0);
                break;
            default:
                dictionaryAdvanced(dictionary);
                break;


        }
    }

}
