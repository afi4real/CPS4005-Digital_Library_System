package dao;
import db.DatabaseConnection;
import model.Member;
import java.sql.*;
import java.util.ArrayList;

public class MemberDAO {

    //getting all member records when user opens member tab
    public ArrayList<Member> getAll() {
        ArrayList<Member> list = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM members")) {

            while (rs.next()) {
                list.add(new Member(
                        rs.getInt("member_id"),
                        rs.getString("member_name"),
                        rs.getString("email"),
                        rs.getString("membership_type")
                ));
            }
        } catch (Exception e) {
            System.out.println("Load members error: " + e.getMessage());
        }
        return list;
    }

    //adding new member to the member record
    public void add(String name, String email, String type) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO members (member_name, email, membership_type) VALUES (?, ?, ?)")) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, type);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Add member error: " + e.getMessage());
        }
    }

    //updating existing member record
    public void update(int id, String name, String email, String type) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "UPDATE members SET member_name=?, email=?, membership_type=? WHERE member_id=?")) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, type);
            ps.setInt(4, id);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Update member error: " + e.getMessage());
        }
    }

    //removing member
    public void delete(int id) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM members WHERE member_id=?")) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Delete member error: " + e.getMessage());
        }
    }
}
