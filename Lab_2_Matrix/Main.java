public class Main {
    public static void main(String[] args) {
        Matrix matrix = new Matrix();
        System.out.println("Enter matrix size: ");
        try {
            matrix.enterSize();
            matrix.fillMatrix();
            matrix.outputMatrix();
            matrix.find();
        }
        catch (NumberFormatException e) {
            System.out.println("error! you must enter a number!");
        }
        catch (IllegalArgumentException e) {
            System.out.println("error! wrong value!");
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Out of bound!");
        }
    }
}
