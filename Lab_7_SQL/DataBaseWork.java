import org.json.JSONObject;

import java.io.*;
import java.sql.*;
import java.util.Scanner;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class DataBaseWork {

    private static final String url = "jdbc:mysql://localhost/mydbcompanies?serverTimezone=Europe/Moscow&useSSL=false";
    private static final String username = "root";
    private static final String password = "24zhivebelarusnjanzyshpionkulakeyver05";

    private static final Logger logger = Logger.getLogger("log");

    static {
        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("logging.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    DataBaseWork() {

    }

    public void createDataBase() {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String createDB = "CREATE TABLE MyComp (name VARCHAR(100), shortTitle VARCHAR(100), dateUpdate VARCHAR(100), " +
                    "address VARCHAR(100), dateFoundation VARCHAR(100), countEmployees INT, auditor VARCHAR(100), phone VARCHAR(100), " +
                    "email VARCHAR(100), branch VARCHAR(100), activity VARCHAR(100), link VARCHAR(100))";

            Statement statement = connection.createStatement();
            statement.executeUpdate(createDB);
            logger.info("dataBase created");
        } catch (SQLException e) {
            System.out.println("SQL ERROR!" + e.getMessage().toUpperCase());
        }
    }

    public void fillDataBase() throws IOException, SQLException {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            try (Scanner sc = new Scanner(new File("input.csv"))) {
                while (sc.hasNextLine()) {
                    try (Scanner scWords = new Scanner(sc.nextLine()).useDelimiter(";")) {
                        String sqlCommand = "INSERT INTO MyComp (name, shortTitle, dateUpdate, " +
                                "address, dateFoundation, countEmployees, auditor, phone, " +
                                "email, branch, activity, link) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
                        PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
                        preparedStatement.setString(1, scWords.next());
                        preparedStatement.setString(2, scWords.next());
                        preparedStatement.setString(3, scWords.next());
                        preparedStatement.setString(4, scWords.next());
                        preparedStatement.setString(5, scWords.next());
                        preparedStatement.setInt(6, Integer.parseInt(scWords.next()));
                        preparedStatement.setString(7, scWords.next());
                        preparedStatement.setString(8, scWords.next());
                        preparedStatement.setString(9, scWords.next());
                        preparedStatement.setString(10, scWords.next());
                        preparedStatement.setString(11, scWords.next());
                        preparedStatement.setString(12, scWords.next());

                        preparedStatement.executeUpdate();
                    }
                }
            }
            logger.info("Data was loaded");
        }
    }

    public void request() throws IOException, SQLException {
        try (Scanner sc = new Scanner(new File("requests.txt"))) {
            String request;
            int pos = 0;
            while (sc.hasNextLine()) {
                request = sc.nextLine();
                pos++;
                find(request, pos);
            }
        }
    }

    private void find(String request, int pos) throws IOException, SQLException {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(request);
            ResultSetMetaData rsmd = resultSet.getMetaData();
            JSONObject jsRet = new JSONObject();
            JSONObject jsOut;

            int c_num = 0;

            try (FileWriter fw = new FileWriter("request" + pos + ".xml")) {
                fw.write("<companies>" + "\n");

                while (resultSet.next()) {
                    int i = 1;
                    jsOut = new JSONObject();
                    fw.write("<company_" + c_num + ">" + "\n");

                    try {
                        while (true) {
                            jsOut.put(rsmd.getColumnName(i), resultSet.getString(i));
                            fw.write("<" + rsmd.getColumnName(i) + ">" + resultSet.getString(i) + "</" + rsmd.getColumnName(i) + ">" + "\n");
                            i++;
                        }
                    } catch (Exception e) {
                    }

                    jsRet.put("company_" + c_num, jsOut);
                    fw.write("</company_" + c_num + ">" + "\n");
                    c_num++;
                }
                fw.write("</companies>" + "\n");
            }

            try (FileWriter fw = new FileWriter("request" + pos + ".json")) {
                fw.write(jsRet.toString());
            }
            logger.info(c_num + " companies were found by the request: " + request);
        }
    }

    public void deleteDataBase() throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            Statement st = connection.createStatement();
            st.executeUpdate("DROP TABLE IF EXISTS MyComp");
        }
        logger.info("Database was deleted");
    }
}
