import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        FindText findText = new FindText();
        try{
            findText.input();
            findText.findTags();
            findText.findWords();
        }
        catch (IOException e) {
            System.out.println("File error!");
        }
    }
}
