package dao;
import db.DatabaseConnection;
import model.BorrowRecord;
import java.sql.*;
import java.util.ArrayList;

public class BorrowRecordDAO {

    //loading all record everytime user open boorow tab
    public ArrayList<BorrowRecord> getAll() {
        ArrayList<BorrowRecord> list = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM borrow_records")) {

            while (rs.next()) {
                list.add(new BorrowRecord(
                        rs.getInt("record_id"),
                        rs.getInt("book_id"),
                        rs.getInt("member_id"),
                        rs.getString("borrow_date"),
                        rs.getString("due_date"),
                        rs.getString("return_status")
                ));
            }
        } catch (Exception e) {
            //printing error
            System.out.println("Load borrow records error: " + e.getMessage());
        }
        return list;
    }

    //adding in bowworw record
    public void add(int bookId, int memberId, String borrowDate, String dueDate, String status) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO borrow_records (book_id, member_id, borrow_date, due_date, return_status) VALUES (?, ?, ?, ?, ?)")) {

            ps.setInt(1, bookId);
            ps.setInt(2, memberId);
            ps.setString(3, borrowDate);
            ps.setString(4, dueDate);
            ps.setString(5, status);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Add borrow record error: " + e.getMessage());
        }
    }

    //updating a existing record
    public void update(int id, int bookId, int memberId, String borrowDate, String dueDate, String status) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "UPDATE borrow_records SET book_id=?, member_id=?, borrow_date=?, due_date=?, return_status=? WHERE record_id=?")) {

            ps.setInt(1, bookId);
            ps.setInt(2, memberId);
            ps.setString(3, borrowDate);
            ps.setString(4, dueDate);
            ps.setString(5, status);
            ps.setInt(6, id);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Update borrow record error: " + e.getMessage());
        }
    }

    public void delete(int id) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM borrow_records WHERE record_id=?")) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Delete borrow record error: " + e.getMessage());
        }
    }
}
