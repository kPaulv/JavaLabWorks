import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Enter amount of strings: ");
        Text text = new Text();
        try {
            text.inputAmount();
            System.out.println("Enter text: ");
            text.inputText();
            text.outputText();
        }
        catch (InputMismatchException e) {
            System.out.println("you must enter a number!");
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        catch (StringIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error! Your string is empty!");
        }
    }
}
