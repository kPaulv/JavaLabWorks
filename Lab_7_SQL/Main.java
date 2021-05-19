import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        try {
            DataBaseWork dataBaseWork = new DataBaseWork();
            dataBaseWork.createDataBase();
            dataBaseWork.fillDataBase();
            dataBaseWork.request();
            dataBaseWork.deleteDataBase();
        }
        catch (FileNotFoundException e) {
            System.out.println("File error!" + e.getMessage().toUpperCase());
        }
        catch (IOException e) {
            System.out.println("File error!");
        }
    }
}
