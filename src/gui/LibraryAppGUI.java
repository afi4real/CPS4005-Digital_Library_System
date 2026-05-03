package gui;
import dao.*;
import model.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LibraryAppGUI extends JFrame {

    //dao
    BookDAO bookDAO = new BookDAO();
    MemberDAO memberDAO = new MemberDAO();
    BorrowRecordDAO borrowDAO = new BorrowRecordDAO();

    public LibraryAppGUI() {
        setTitle("St Mary's Digital Library System");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Books", booksPanel());
        tabs.add("Members", membersPanel());
        tabs.add("Borrow Records", borrowPanel());

        add(tabs);
    }

    // -----------books tab ----------------
    private JPanel booksPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        DefaultTableModel model = new DefaultTableModel(
                new String[]{"ID", "Title", "Author", "Category", "Status"}, 0
        );
        JTable table = new JTable(model);
        loadBooks(model);

        //search bar
        JPanel searchPanel = new JPanel();
        JTextField searchField = new JTextField(20);
        JButton searchBtn = new JButton("Search");
        JButton clearBtn = new JButton("Clear");

        searchBtn.addActionListener(e -> searchBooks(model, searchField.getText()));
        clearBtn.addActionListener(e -> {
            searchField.setText("");
            loadBooks(model);
        });

        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchField);
        searchPanel.add(searchBtn);
        searchPanel.add(clearBtn);

        //form section
        JPanel form = new JPanel(new GridLayout(2, 5));
        JTextField id = new JTextField();
        JTextField title = new JTextField();
        JTextField author = new JTextField();
        JTextField category = new JTextField();
        JTextField status = new JTextField();

        form.add(new JLabel("ID"));
        form.add(new JLabel("Title"));
        form.add(new JLabel("Author"));
        form.add(new JLabel("Category"));
        form.add(new JLabel("Status"));

        form.add(id);
        form.add(title);
        form.add(author);
        form.add(category);
        form.add(status);

        //buttons
        JPanel buttons = new JPanel();
        JButton add = new JButton("Add");
        JButton update = new JButton("Update");
        JButton delete = new JButton("Delete");
        JButton refresh = new JButton("Refresh");

        //adding books
        add.addActionListener(e -> {
            bookDAO.add(title.getText(), author.getText(), category.getText(), status.getText());
            loadBooks(model);
            JOptionPane.showMessageDialog(this, "Book added successfully.");
        });

        //updating books
        update.addActionListener(e -> {
            bookDAO.update(Integer.parseInt(id.getText()), title.getText(), author.getText(),
                    category.getText(), status.getText());
            loadBooks(model);
            JOptionPane.showMessageDialog(this, "Book updated.");
        });

        //deleting books
        delete.addActionListener(e -> {
            bookDAO.delete(Integer.parseInt(id.getText()));
            loadBooks(model);
            JOptionPane.showMessageDialog(this, "Book deleted.");
        });

        //refresh function
        refresh.addActionListener(e -> loadBooks(model));

        buttons.add(add);
        buttons.add(update);
        buttons.add(delete);
        buttons.add(refresh);

        //bottom layout with buttons
        JPanel bottom = new JPanel(new BorderLayout());
        bottom.add(form, BorderLayout.CENTER);
        bottom.add(buttons, BorderLayout.SOUTH);

        panel.add(searchPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(bottom, BorderLayout.SOUTH);

        return panel;
    }

    //search function(loop through all books)
    private void searchBooks(DefaultTableModel model, String text) {
        model.setRowCount(0);
        text = text.toLowerCase();

        for (Book b : bookDAO.getAll()) {
            if (b.title.toLowerCase().contains(text) ||
                b.author.toLowerCase().contains(text) ||
                b.category.toLowerCase().contains(text)) {

                model.addRow(new Object[]{b.id, b.title, b.author, b.category, b.status});
            }
        }
    }

    private void loadBooks(DefaultTableModel model) {
        model.setRowCount(0);
        for (Book b : bookDAO.getAll()) {
            model.addRow(new Object[]{b.id, b.title, b.author, b.category, b.status});
        }
    }

    // ---------------members tab ----------------
    private JPanel membersPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        DefaultTableModel model = new DefaultTableModel(
                new String[]{"ID", "Name", "Email", "Type"}, 0
        );
        JTable table = new JTable(model);
        loadMembers(model);

        //search section
        JPanel searchPanel = new JPanel();
        JTextField searchField = new JTextField(20);
        JButton searchBtn = new JButton("Search");
        JButton clearBtn = new JButton("Clear");

        searchBtn.addActionListener(e -> searchMembers(model, searchField.getText()));
        clearBtn.addActionListener(e -> {
            searchField.setText("");
            loadMembers(model);
        });

        searchPanel.add(new JLabel("Search Name:"));
        searchPanel.add(searchField);
        searchPanel.add(searchBtn);
        searchPanel.add(clearBtn);

        //form
        JPanel form = new JPanel(new GridLayout(2, 4));
        JTextField id = new JTextField();
        JTextField name = new JTextField();
        JTextField email = new JTextField();
        JTextField type = new JTextField();

        form.add(new JLabel("ID"));
        form.add(new JLabel("Name"));
        form.add(new JLabel("Email"));
        form.add(new JLabel("Type"));

        form.add(id);
        form.add(name);
        form.add(email);
        form.add(type);

        //buttons in book section
        JPanel buttons = new JPanel();
        JButton add = new JButton("Add");
        JButton update = new JButton("Update");
        JButton delete = new JButton("Delete");
        JButton refresh = new JButton("Refresh");

        //add, update and delete function for books tab
        add.addActionListener(e -> {
            memberDAO.add(name.getText(), email.getText(), type.getText());
            loadMembers(model);
            JOptionPane.showMessageDialog(this, "Member added.");
        });

        update.addActionListener(e -> {
            memberDAO.update(Integer.parseInt(id.getText()), name.getText(), email.getText(), type.getText());
            loadMembers(model);
            JOptionPane.showMessageDialog(this, "Member updated.");
        });

        delete.addActionListener(e -> {
            memberDAO.delete(Integer.parseInt(id.getText()));
            loadMembers(model);
            JOptionPane.showMessageDialog(this, "Member deleted.");
        });

        refresh.addActionListener(e -> loadMembers(model));

        buttons.add(add);
        buttons.add(update);
        buttons.add(delete);
        buttons.add(refresh);

        //layout
        JPanel bottom = new JPanel(new BorderLayout());
        bottom.add(form, BorderLayout.CENTER);
        bottom.add(buttons, BorderLayout.SOUTH);

        panel.add(searchPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(bottom, BorderLayout.SOUTH);

        return panel;
    }

    private void searchMembers(DefaultTableModel model, String text) {
        model.setRowCount(0);
        text = text.toLowerCase();

        for (Member m : memberDAO.getAll()) {
            if (m.name.toLowerCase().contains(text)) {
                model.addRow(new Object[]{m.id, m.name, m.email, m.type});
            }
        }
    }

    private void loadMembers(DefaultTableModel model) {
        model.setRowCount(0);
        for (Member m : memberDAO.getAll()) {
            model.addRow(new Object[]{m.id, m.name, m.email, m.type});
        }
    }

    // -------------borrow book tab----------------
    private JPanel borrowPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        //table and model
        DefaultTableModel model = new DefaultTableModel(
                new String[]{"ID", "Book ID", "Member ID", "Borrow Date", "Due Date", "Status"}, 0
        );
        JTable table = new JTable(model);
        loadBorrow(model);

        //search bar
        JPanel searchPanel = new JPanel();
        JTextField searchField = new JTextField(20);
        JButton searchBtn = new JButton("Search");
        JButton clearBtn = new JButton("Clear");

        searchBtn.addActionListener(e -> searchBorrow(model, searchField.getText()));
        clearBtn.addActionListener(e -> {
            searchField.setText("");
            loadBorrow(model);
        });

        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchField);
        searchPanel.add(searchBtn);
        searchPanel.add(clearBtn);

        //form for borrow panel
        JPanel form = new JPanel(new GridLayout(2, 6));
        JTextField id = new JTextField();
        JTextField bookId = new JTextField();
        JTextField memberId = new JTextField();
        JTextField borrowDate = new JTextField();
        JTextField dueDate = new JTextField();
        JTextField status = new JTextField();

        form.add(new JLabel("ID"));
        form.add(new JLabel("Book ID"));
        form.add(new JLabel("Member ID"));
        form.add(new JLabel("Borrow Date"));
        form.add(new JLabel("Due Date"));
        form.add(new JLabel("Status"));

        form.add(id);
        form.add(bookId);
        form.add(memberId);
        form.add(borrowDate);
        form.add(dueDate);
        form.add(status);

        //buttons
        JPanel buttons = new JPanel();
        JButton add = new JButton("Add");
        JButton update = new JButton("Update");
        JButton delete = new JButton("Delete");
        JButton refresh = new JButton("Refresh");
        JButton returned = new JButton("Mark Returned");

        add.addActionListener(e -> {
            borrowDAO.add(Integer.parseInt(bookId.getText()), Integer.parseInt(memberId.getText()),
                    borrowDate.getText(), dueDate.getText(), status.getText());
            loadBorrow(model);
            JOptionPane.showMessageDialog(this, "Borrow record added.");
        });

        update.addActionListener(e -> {
            borrowDAO.update(Integer.parseInt(id.getText()), Integer.parseInt(bookId.getText()),
                    Integer.parseInt(memberId.getText()), borrowDate.getText(), dueDate.getText(), status.getText());
            loadBorrow(model);
            JOptionPane.showMessageDialog(this, "Borrow record updated.");
        });

        delete.addActionListener(e -> {
            borrowDAO.delete(Integer.parseInt(id.getText()));
            loadBorrow(model);
            JOptionPane.showMessageDialog(this, "Borrow record deleted.");
        });

        returned.addActionListener(e -> {
            try {
                int recordId = Integer.parseInt(id.getText());
                int book = Integer.parseInt(bookId.getText());

                borrowDAO.update(recordId, book, Integer.parseInt(memberId.getText()),
                        borrowDate.getText(), dueDate.getText(), "Returned");

                bookDAO.update(book, "", "", "", "Available");

                loadBorrow(model);
                JOptionPane.showMessageDialog(this, "Book marked as returned.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid record ID.");
            }
        });

        refresh.addActionListener(e -> loadBorrow(model));

        buttons.add(add);
        buttons.add(update);
        buttons.add(delete);
        buttons.add(refresh);
        buttons.add(returned);

        //layout
        JPanel bottom = new JPanel(new BorderLayout());
        bottom.add(form, BorderLayout.CENTER);
        bottom.add(buttons, BorderLayout.SOUTH);

        panel.add(searchPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(bottom, BorderLayout.SOUTH);

        return panel;
    }

    private void searchBorrow(DefaultTableModel model, String text) {
        model.setRowCount(0);
        text = text.toLowerCase();

        for (BorrowRecord r : borrowDAO.getAll()) {
            if (String.valueOf(r.bookId).contains(text) ||
                String.valueOf(r.memberId).contains(text) ||
                r.borrowDate.toLowerCase().contains(text) ||
                r.dueDate.toLowerCase().contains(text) ||
                r.status.toLowerCase().contains(text)) {

                model.addRow(new Object[]{r.id, r.bookId, r.memberId, r.borrowDate, r.dueDate, r.status});
            }
        }
    }

    private void loadBorrow(DefaultTableModel model) {
        model.setRowCount(0);
        for (BorrowRecord r : borrowDAO.getAll()) {
            model.addRow(new Object[]{r.id, r.bookId, r.memberId, r.borrowDate, r.dueDate, r.status});
        }
    }
}
