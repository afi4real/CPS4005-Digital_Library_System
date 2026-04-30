package db;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    //creating and returning connection to sqlite database
    public static Connection getConnection() {
    try {
        return DriverManager.getConnection("jdbc:sqlite:db/library.db");
    } catch (Exception e) {
        //printing error
        System.out.println("Database error: " + e.getMessage());
        return null; //dao methods will handle failure
    }
}

}
