import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringWork {
    private String myStr;
    private String request;
    String res;

    public void input() throws IOException {
        try(Scanner sc = new Scanner(new File("input.txt"))) {
            while(sc.hasNextLine()) {
                myStr = sc.nextLine();
            }
            System.out.println(myStr);
        }
    }

    public void deleteParentheses() {
        res = myStr;
        int index;
        int indOpen;
        int indClose;
        StringBuilder sb;
        String temp;

        Pattern p = Pattern.compile("\\(.*?\\)");
        Matcher m = p.matcher(res);
        Pattern p1 = Pattern.compile("\\(+");
        Matcher m1 = p1.matcher(res);
        Pattern p2 = Pattern.compile("\\)+");
        Matcher m2 = p2.matcher(res);
        while (m.find()) {
            temp = m.group();
            index = res.indexOf(temp);
            sb = new StringBuilder(res);
            sb.delete(index, index + temp.length());
            res = sb.toString();
            if(m2.find()) {
                indClose = res.indexOf(m2.group());
                if(m1.find()) {
                    indOpen = res.indexOf(m1.group());
                    if(indClose >= index && indClose < indOpen) {
                        sb = new StringBuilder(res);
                        sb.delete(index, indClose + 1);
                        res = sb.toString();
                    }
                }
            }
        }

        //res = res.replaceAll("\\(.*?\\)", "");
        /*if(m1.find()) {
            res = res.replaceAll("\\(+", "");
        }
        if(m2.find()) {
            res = res.replaceAll("\\)+", "");
        }*/

        System.out.println(res);
        request = "deleteParentheses()";
    }

    public void deleteDigits() {
        res = myStr;
        String temp;
        int index;
        StringBuilder sb;
        Pattern p = Pattern.compile("(\\d{3,})");
        Matcher m = p.matcher(res);
        System.out.println(res);

        /*if(m.find()) {
            temp = m.group();
            System.out.println();
            System.out.print(temp + ", ");
            index = s.indexOf(temp);
            System.out.println("index = " + index);
            sb = new StringBuilder(s);
            sb.delete(index, index + temp.length());
            s = sb.toString();
        }*/

        System.out.println();
        while(m.find()) {
            temp = m.group();
            System.out.print("temp: " + temp + ", ");
            index = res.indexOf(temp);
            System.out.println("index = " + index);
            sb = new StringBuilder(res);
            sb.delete(index, index + temp.length());
            res = sb.toString();
        }
        System.out.println();
        System.out.println(res);
        request = "deleteDigits()";
    }

    public void deleteZeros() {
        res = myStr;
        String temp;
        int index;
        StringBuilder sb;
        Pattern p = Pattern.compile("\\D0+");
        Matcher m = p.matcher(res);
        System.out.println(res);
        System.out.println();

        while(m.find()) {
            temp = m.group();
            System.out.print("temp: " + temp + ", ");
            index = res.indexOf(temp);
            System.out.println("index = " + index);
            sb = new StringBuilder(res);
            sb.delete(index + 1, index + temp.length());
            res = sb.toString();
        }

        System.out.println();
        System.out.println(res);
        request = "deleteZeros()";
    }

    public void output() throws IOException {
        try(FileWriter fw = new FileWriter("output.txt", true)) {
            fw.write(request + ":\n");
            fw.write(res + "\n\n");
        }
    }
}
