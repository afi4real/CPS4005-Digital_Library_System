package gui;

import javax.swing.*;

public class LibraryAppGUI extends JFrame {

    //dao
    public LibraryAppGUI() {
        setTitle("St Mary's Digital Library System");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabs = new JTabbedPane();

        tabs.add("Books", new JPanel());
        tabs.add("Members", new JPanel());
        tabs.add("Borrow Records", new JPanel());

        add(tabs);
    }
}
