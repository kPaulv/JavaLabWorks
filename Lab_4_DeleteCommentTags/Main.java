import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        try {
            FileWork fileWork = new FileWork();
            fileWork.input();
            fileWork.clearText();
            fileWork.output();
        }
        catch (IOException e) {
            System.out.println("File error!");
        }
    }
}
