package sql.demo;

import java.sql.*;

public class Main {

    public static final String DB_URL = "jdbc:h2:/d:/JAVA/Projects/SQLDemo/db/stockExchange";
    public static final String DB_Driver = "org.h2.Driver";

    public static void main(String[] args) {
	// write your code here
        try {
            Class.forName(DB_Driver);   //Проверяем наличие JDBC драйвера для работы с БД
            Connection connection = DriverManager.getConnection(DB_URL);    //соединениесБД
            System.out.println("Соединение с СУБД выполнено.");
            Statement statement = connection.createStatement();
            //statement.executeUpdate("drop table Books");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Books (id MEDIUMINT NOT NULL AUTO_INCREMENT, " +
                                    "name CHAR(30) NOT NULL, PRIMARY KEY(id));");
            statement.executeUpdate("insert into Books (name) values('Inferno')");
            statement.executeUpdate("insert into Books set name = 'Solomon key'");
            connection.close();       // отключение от БД
            System.out.println("Отключение от СУБД выполнено.");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();    // обработка ошибки  Class.forName
            System.out.println("JDBC драйвер для СУБД не найден!");
        }
        catch (SQLException e) {
            e.printStackTrace();    // обработка ошибок  DriverManager.getConnection
            System.out.println("Ошибка SQL !");
        }
    }
}
