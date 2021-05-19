import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class UrlCheck {

    private List<String> html;
    private List<String> file;

    public UrlCheck() {
        html = new ArrayList<>();
        file = new ArrayList<>();
    }

    public void file() throws IOException {
        String link;
        URL url;
        try (Scanner sc = new Scanner(new File("input.html"))) {
            while (sc.hasNextLine()) {
                link = sc.nextLine();
                url = new URL(link);

                URLConnection urlConnection = url.openConnection();

                if (urlConnection.getContentLength() != -1) {
                    System.out.println(urlConnection.getContentLength() + " bytes");
                    file.add(link + " — " + urlConnection.getContentLength() + " bytes.");
                } else {
                    System.out.println("empty.");
                    file.add(link + " — empty.");
                }
            }
        }
    }

    public void checkFile() throws IOException {
        try (Scanner sc = new Scanner(new File("input.html"))) {
            String sLink;
            URL link;

            while (sc.hasNextLine()) {

                sLink = sc.nextLine();
                link = new URL(sLink);

                HttpURLConnection c = (HttpURLConnection) link.openConnection();
                c.setRequestMethod("HEAD");

                if (c.getContentLength() != -1) {
                    System.out.println(c.getContentLength());
                } else {
                    System.out.println("none");
                }
                c.disconnect();
            }
        }
    }

    public void html() throws IOException {
        String link;
        URL url;
        String temp;
        try(Scanner sc = new Scanner(new File("input.html"))) {
            while(sc.hasNextLine()) {
                link = sc.nextLine();
                url = new URL(link);
                try(InputStream is = url.openStream()) {
                    Scanner scanner = new Scanner(is);
                    temp = scanner.useDelimiter("\\A").next();
                    if(!temp.contains("<title>") || !temp.contains("</title>")){
                        System.out.println("no title");
                        html.add(link + " — no title");
                    }
                    else {
                        temp = temp.substring(temp.indexOf("<title>") + 7, temp.indexOf("</title>"));
                        System.out.println(temp);
                        html.add(link + " — \"" + temp + "\"");
                    }
                }
            }
        }
    }

    public void output() throws IOException {
        try(FileWriter fw1 = new FileWriter("output1.txt");
                FileWriter fw2 = new FileWriter("output2.txt")) {
            ListIterator<String> it = file.listIterator();
            String temp;
            for(String item : html) {
                if(!item.contains("no title")) {
                    fw1.write(item + "\n");
                }
                else {
                    fw2.write(/*item.substring(0, item.indexOf(" "))*/ item + "\n");
                }
                temp = it.next();
                if(!temp.contains("empty")) {
                    fw1.write(temp + "\n");
                }
                else {
                    fw2.write(/*temp.substring(0, temp.indexOf(" "))*/ temp + "\n");
                }
            }
        }
    }

}
