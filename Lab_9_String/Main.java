import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        StringWork stringWork = new StringWork();
        try {
            stringWork.input();
            stringWork.deleteParentheses();
            stringWork.output();
            stringWork.deleteDigits();
            stringWork.output();
            stringWork.deleteZeros();
            stringWork.output();
        }
        catch (IOException e) {
            System.out.println("File error!");
        }
    }
}