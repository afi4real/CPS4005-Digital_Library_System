package model;

public class Book {
    public int id;
    public String title;
    public String author;
    public String category;
    public String status;

    public Book(int id, String title, String author, String category, String status) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.status = status;
    }
}
