package model;

public class BorrowRecord {
    public int id;
    public int bookId;
    public int memberId;
    public String borrowDate;
    public String dueDate;
    public String status;

    public BorrowRecord(int id, int bookId, int memberId, String borrowDate, String dueDate, String status) {
        this.id = id;
        this.bookId = bookId;
        this.memberId = memberId;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.status = status;
    }
}
