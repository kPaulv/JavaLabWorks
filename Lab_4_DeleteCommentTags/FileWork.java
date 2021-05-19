import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileWork {
    private FileReader fr;
    private FileWriter fw;
    private List<String> myText;
    private List<String> resText;
    private List <String> finalText;

    public void input() throws IOException {
        fr = new FileReader("input.txt");

        try (Scanner sc = new Scanner(fr)) {
            myText = new ArrayList<>();
            resText = new ArrayList<>();
            finalText = new ArrayList<>();
            int i = 0;
            while (sc.hasNextLine()) {
                myText.add(sc.nextLine());
                i++;
            }
            System.out.println("file is now read.");
        }
    }

    public void clearText() {
        for (int i = 0; i < myText.size(); i++) {
            deleteComments(myText.get(i));
        }
    }

    public void finalClear() {
        if (resText.isEmpty()) {
            throw new IndexOutOfBoundsException("empty list!");
        }
        else {
            for (int i = 0; i < resText.size(); i++) {
                //finalDelete(resText.get(i));
                //finalText.add(resText.get(i));
            }
        }
    }



    private void deleteComments(String str) {
        boolean isQuote = false;
        int quoteNum = 0;
        int count = 0;
        int pos = str.length();
        StringBuilder sb = new StringBuilder(str);
        char[] mas = str.toCharArray();

        for(int i = 0; i < mas.length - 1; i++) {
            if(mas[i+1] == '"' ) {
                if (mas[i] == '(' || mas[i] == ' ') {
                    if (quoteNum == 0) {
                        isQuote = true;
                        quoteNum++;
                    }
                    if(mas[i + 1 + 1] == '"') {
                        quoteNum = 0;
                        isQuote = false;
                    }
                }
            }
            if(mas[i] == '"') {
                if (mas[i + 1] == ')' || mas[i + 1] == ';') {
                    if (quoteNum == 1) {
                        isQuote = false;
                        quoteNum = 0;
                    }
                }
            }

            if(isQuote == false) {
                if (mas[i] == '/' && mas[i + 1] == '/') {
                    sb = new StringBuilder(str);
                    sb.delete(i, str.length());
                    str = sb.toString();
                }
            }
        }

        for (int i = 0; i < mas.length - 1; i++) {
            if(mas[i+1] == '"' ) {
                if (mas[i] == '(' || mas[i] == ' ') {
                    if (quoteNum == 0) {
                        isQuote = true;
                        quoteNum++;
                    }
                    if(mas[i + 1 + 1] == '"') {
                        quoteNum = 0;
                        isQuote = false;
                    }
                }
            }
            if(mas[i] == '"') {
                if (mas[i + 1] == ')' || mas[i + 1] == ';') {
                    if (quoteNum == 1) {
                        isQuote = false;
                        quoteNum = 0;
                    }
                }
            }

            if (isQuote == false) {
                if (mas[i] == '/' && mas[i + 1] == '*') {
                    sb = new StringBuilder(str);
                    for (int j = i + 2; j < mas.length - 1; j++) {
                        if (mas[j] == '*' && mas[j + 1] == '/') {
                            pos = j + 1 + 1;
                        }
                    }

                    sb.delete(i, pos);
                    str = sb.toString();
                }
            }

        }



        for(int i = 0; i < mas.length - 1; i++) {
            if(mas[i+1] == '"' ) {
                if (mas[i] == '(' || mas[i] == ' ') {
                    if (quoteNum == 0) {
                        isQuote = true;
                        quoteNum++;
                    }
                    if(mas[i + 1 + 1] == '"') {
                        quoteNum = 0;
                        isQuote = false;
                    }
                }
            }
            if(mas[i] == '"') {
                if (mas[i + 1] == ')' || mas[i + 1] == ';') {
                    if (quoteNum == 1) {
                        isQuote = false;
                        quoteNum = 0;
                    }
                }
            }

            if (isQuote == false) {
                if(mas[i] == ' ' && mas[i + 1] == '*') {
                    sb = new StringBuilder(str);
                    sb.delete(i, str.length());
                    str = sb.toString();
                }
            }
        }

        resText.add(str);
    }

    public void show() {
        for(int i = 0; i < myText.size(); i++) {
            //System.out.println(myText.get(i));
        }
    }

    public void output() throws IOException {
        fw = new FileWriter("output.txt");
        String str;
        for(int i = 0; i < resText.size(); i++) {
            str = resText.get(i);
            fw.write(str);
            fw.append("\n");
        }
        fw.close();
    }

    public void finalOutput() throws IOException {
        fw = new FileWriter("output2.txt");
        String str;

        for(int i = 0; i < finalText.size(); i++) {
            str = finalText.get(i);
            fw.write(str);
            fw.append("\n");
        }
        fw.close();
    }
}
