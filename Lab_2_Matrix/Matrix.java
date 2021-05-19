import java.util.Scanner;

public class Matrix {
    private int m;
    private int n;
    private int[][] array;

    Matrix() {
        m = 0;
        n = 0;
        array = new int[0][0];
    }
    public void enterSize() {
        Scanner sc = new Scanner(System.in);
        m = Integer.parseInt(sc.next());
        if (m <= 0) throw new IllegalArgumentException();
        n = Integer.parseInt(sc.next());
        if (n <= 0) throw new IllegalArgumentException();
        sc.close();
    }
    public void fillMatrix() {
        array = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                array[i][j] = (int) (Math.random() * 15);
            }
        }
    }

    public void outputMatrix() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
    public void find() {
        int min;
        int[] single = new int [m*n];
        int count; int singleAmount = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                min = array[i][j];
                count = unique(min);
                if(count == 1) {
                    single[singleAmount] = min;
                    singleAmount++;
                }
            }
        }
        min = single[0];
        for (int i = 0; i < singleAmount; i++) {
            if(single[i] < min) {
                min = single[i];
            }
        }
        System.out.println("Minimum unique element: " + min);
    }
    public int unique(int element) {
        int count = 0;
        for(int i = 0; i< m; i++) {
            for (int j = 0; j < n; j++) {
                if(array[i][j] == element) {
                    count++;
                }
            }
        }
        return count;
    }
}
