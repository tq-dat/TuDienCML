import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement {
    public Scanner sr = new Scanner(System.in);

    public void insertFromCommandline(Dictionary dictionary) {

        System.out.print("Nhap so luong tu ban muon nhap: ");
        int n = sr.nextInt();
        System.out.println();

        String word;
        word = sr.nextLine();
        for (int i = 0; i < n; i++) {
            Word newWord = new Word();
            System.out.print("Word " + (i + 1) + " : ");
            word = sr.nextLine();
            if (word != null) {
                newWord.setWord_target(word);
            }
            System.out.println();

            System.out.print("Explain : ");
            word = sr.nextLine();
            if (word != null) {
                newWord.setWord_explain(word);
            }
            System.out.println();

            if(newWord.getWord_target() != null && newWord.getWord_explain() != null){
                int x = 0;
                for (int j = 0; j < dictionary.getDic().size(); j++) {
                    Word word1 = dictionary.getDic().get(j);
                    if (newWord.getWord_target() == word1.getWord_target()) {
                        x = 1;
                        break;
                    }
                }
                if (x == 0) {
                    dictionary.setDic(newWord);
                }
            }else {
                System.out.println("Nhap sai.");
            }

        }

    }

    public void insertFromFile(Dictionary dictionary) throws IOException {
       FileReader fr = new FileReader("dictionaries.txt");
       BufferedReader br = new BufferedReader(fr);

       String line = "";

       while (true) {
           line = br.readLine();
           if (line == null) {
               break;
           }
           int index = 0;
           Word word = new Word();
           String target = "";
           String explain = "";

           for (index = 0; line.charAt(index) != '\t'&& line.charAt(index) != ' '; index++ ) {
               target += line.charAt(index);
           }

           for (; line.charAt(index) == '\t' || line.charAt(index) == ' '; index++);

           explain = line.substring(index);

           if (target != null && explain != null) {
               word.setWord_explain(explain);
               word.setWord_target(target);
           }

           if (word.getWord_explain() != null && word.getWord_target() != null) {
               int n = 0;
               for (int j = 0; j < dictionary.getDic().size(); j++) {
                   Word word1 = dictionary.getDic().get(j);
                   if (word.getWord_target() == word1.getWord_target()) {
                       n = 1;
                       break;
                   }
               }
               if (n == 0) {
                   dictionary.setDic(word);
               }

           }

       }

    }

    public void dictionaryLookup(Dictionary dictionary) {
        System.out.print("Nhập từ bạn muốn tra :");
        String lookup =  sr.nextLine();
        System.out.println();


        for (int i = 0; i < dictionary.getDic().size(); i++) {
            Word word = dictionary.getDic().get(i);
            if (lookup.equals(word.getWord_target())) {
                System.out.println(word.getWord_target() + "\t-->" + word.getWord_explain());
                while (true) {
                    System.out.println("Bạn có muốn đọc từ này không ?");
                    System.out.println("1.YES\n2.NO");
                    int x = sr.nextInt();
                    if (x == 1) {
                        speak(word.getWord_target());
                    }else {
                        break;
                    }
                }
                return;
            }
        }

        System.out.println("Không tìm thấy từ !");
    }

    public void deleteWord (Dictionary dictionary) {
        System.out.print("Nhập từ bạn muốn xóa :");
        String delete =  sr.nextLine();
        System.out.println();



        for (int i = 0; i < dictionary.getDic().size(); i++) {
            Word word = dictionary.getDic().get(i);
            if (delete.equals(word.getWord_target())) {
                dictionary.getDic().remove(i);
                return;
            }
        }

        System.out.println("Không tìm thấy từ!");
    }

    public void fixWord (Dictionary dictionary) {
        System.out.print("Nhap tu :");
        String fix =  sr.nextLine();
        System.out.println();

        System.out.print("Nhập nghĩa mới của từ :");
        String explain =  sr.nextLine();
        System.out.println();

        ArrayList<Word> array;
        array = (ArrayList<Word>) dictionary.getDic();

        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).getWord_target() == fix) {
                dictionary.getDic().get(i).setWord_explain(explain);
                System.out.println("Từ đã được sửa!");
            }
        }

        System.out.println("Khong timf thay tu!");


    }

    public void writerFile(Dictionary dictionary) {
        try {
            FileWriter fw = new FileWriter("dictionaries.txt",false);
            BufferedWriter bw = new BufferedWriter(fw);

            for (Word word : dictionary.getDic()) {
                bw.write(word.getWord_target() + "\t" + word.getWord_explain());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Error " + ex);
        } catch (IOException ex) {
            System.out.println("Error " + ex);
        }
    }

    public void speak (String s) {
        System.setProperty("freetts.voices","com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Voice voice = VoiceManager.getInstance().getVoice("kevin16");

        voice.allocate();
        voice.speak(s);
        voice.deallocate();
    }




}
