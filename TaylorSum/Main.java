import java.text.DecimalFormat;



public class Main {
    public static void main(String[] args) {
        try {

            Taylor t = new Taylor();
            DecimalFormat df = new DecimalFormat("#.###");
            System.out.println("Counted by handwritten code: " + df.format(t.countArctg()));
            System.out.println("Counted by Math library: " + df.format(Math.atan(t.x)));
        }
        catch (NumberFormatException e){
            System.out.println("Incorrect input!");
        }
    }
}
