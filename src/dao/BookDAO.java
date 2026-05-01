package dao;
import db.DatabaseConnection;
import model.Book;
import java.sql.*;
import java.util.ArrayList;

public class BookDAO {

    // returning book to loop through in the GUI
    public ArrayList<Book> getAll() {
        ArrayList<Book> list = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM books")) {

            while (rs.next()) {
                    //model constructor
                list.add(new Book(
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("category"),
                        rs.getString("availability_status")
                ));
            }
        } catch (Exception e) {
            System.out.println("Load books error: " + e.getMessage());
        }
        return list;
    }

    public void add(String title, String author, String category, String status) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO books (title, author, category, availability_status) VALUES (?, ?, ?, ?)")) {

            ps.setString(1, title);
            ps.setString(2, author);
            ps.setString(3, category);
            ps.setString(4, status);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Add book error: " + e.getMessage());
        }
    }

    //update method
    public void update(int id, String title, String author, String category, String status) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "UPDATE books SET title=?, author=?, category=?, availability_status=? WHERE book_id=?")) {

            ps.setString(1, title);
            ps.setString(2, author);
            ps.setString(3, category);
            ps.setString(4, status);
            ps.setInt(5, id);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Update book error: " + e.getMessage());
        }
    }

    //delete method only requiring id
    public void delete(int id) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM books WHERE book_id=?")) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Delete book error: " + e.getMessage());
        }
    }
}
