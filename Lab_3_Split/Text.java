import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Text {
    private int amount;
    private String[] text;

    Text() {
        amount = 0;
        text = new String[0];
    }

    public void inputAmount() {
        Scanner sc = new Scanner(System.in);
        amount = sc.nextInt();
        if (amount <= 0) {
            throw new IllegalArgumentException("Wrong value! Your text is empty!");
        }
    }

    public void inputText() {
        Scanner sc = new Scanner(System.in);
        text = new String[amount];
        String s;
        for(int i = 0; i < amount; i++) {
            s = sc.nextLine();
            if (s.isEmpty()) {
                throw new StringIndexOutOfBoundsException("Error! Your string is empty!");
            }
            text[i] = s;
        }
    }

    public void outputText() {
        for(int i = 0; i < amount; i++) {
            split(text[i]);
        }
    }

    public void split(String str) {
        int count = 0;

        String[] temp = str.split("[, .]+");
        for (String a : temp) {
            count++;
        }

        String element;
        for(int i = 0; i < count; i ++) {
            int j = i;
            while(j > 0 && temp[j].length() < temp[j-1].length()) {
                element = temp[j-1];
                temp[j-1] = temp[j];
                temp[j] = element;
                j = j - 1;
            }
        }

        str = temp[0] + " ";
        for(int i = 1; i < count; i++) {
            str += temp[i] + " ";
        }
        System.out.println(str);
    }
}
