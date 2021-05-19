import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindText {
    private List<String> myText;
    private List<String> myWords;
    private List<String> myTags;

    public void input() throws IOException {
        myText = new ArrayList<>();
        myWords = new ArrayList<>();
        FileReader fr = new FileReader("input1.html");

        try (Scanner sc = new Scanner(fr)) {
            while (sc.hasNextLine()) {
                myText.add(sc.nextLine().toLowerCase());
            }
        }

        StringTokenizer st;
        fr = new FileReader("input2.in");
        try(Scanner sc = new Scanner(fr)) {
            while(sc.hasNextLine()) {
                st = new StringTokenizer(sc.nextLine(), ";");
                while(st.hasMoreTokens()) {
                    myWords.add(st.nextToken().toLowerCase());
                }
            }
        }
    }

    public void findTags() throws IOException {
        myTags = new ArrayList<>();
        String tag;
        for (String str : myText) {
            Pattern p = Pattern.compile("\\<[^>]*>");
            Matcher m = p.matcher(str);
            while(m.find()) {
                tag = m.group();
                if(tag.charAt(1) != '/' && !(myTags.contains(tag))) {
                    myTags.add(tag);
                }
            }
        }
        Collections.sort(myTags, Comparator.comparingInt(String::length));

        FileWriter fw = new FileWriter("output1.out");
        for(int i = 0; i < myTags.size(); i++) {
            fw.write(myTags.get(i) + " </" + myTags.get(i).substring(1) + "\n");
        }
        fw.close();
    }

    public void findWords() throws IOException {
        FileWriter fw1 = new FileWriter("output2.out");
        FileWriter fw2 = new FileWriter("output3.out");
        int count = 0;

        for(int i = 0; i < myText.size(); i++) {
            myText.add(i, myText.remove(i).replaceAll("\\<[^>]*>", ""));
        }

        for (String word : myWords) {
            for (String line : myText) {
                if(line.contains(word)) {
                    fw1.write(word + " " + myText.indexOf(line) + "\n");
                }
                else {
                    count++;
                }
            }
            if(count == myText.size()) {
                fw1.write(word + " -1" + "\n");
                fw2.write(word + "\n");
            }
            count = 0;
        }
        fw1.close();
        fw2.close();
    }
}