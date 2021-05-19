import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        UrlCheck urlCheck = new UrlCheck();
        try {
            urlCheck.file();
            urlCheck.html();
            urlCheck.output();
        }
        catch (IOException e) {
            System.out.println("file eror.");
        }
    }
}
