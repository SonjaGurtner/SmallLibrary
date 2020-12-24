package db;

import model.Book;
import model.BookGenre;
import model.BookRatingType;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DBManager {
    private final Connection conn;

    public DBManager(Connection conn) {
        this.conn = conn;
    }

    public void setUpTable() {
        try {
            Statement st = conn.createStatement();
            st.execute("CREATE TABLE BOOKS(UID INTEGER PRIMARY KEY, Title VARCHAR(60), Author VARCHAR(60), Genre INTEGER, Rating INTEGER)");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addBook(Book b) {
        int uid = b.uidProperty().getValue();
        String title = b.titleProperty().getValue();
        String author = b.authorProperty().getValue();
        int genre = b.genreProperty().getValue().ordinal();
        int rating = b.ratingProperty().getValue().ordinal();

        try {
            PreparedStatement st = conn.prepareStatement("INSERT INTO BOOKS(UID, Title, Author, Genre, Rating) VALUES(?, ?, ?, ?, ?)");
            st.setInt(1, uid);
            st.setString(2, title);
            st.setString(3, author);
            st.setInt(4, genre);
            st.setInt(5, rating);
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editBook(Book b) {
        int uid = b.uidProperty().getValue();
        String title = b.titleProperty().getValue();
        String author = b.authorProperty().getValue();
        int genre = b.genreProperty().getValue().ordinal();
        int rating = b.ratingProperty().getValue().ordinal();

        try {
            PreparedStatement st = conn.prepareStatement("UPDATE BOOKS SET Title = ?, Author = ?, Genre = ?, Rating = ? WHERE UID = ?");
            st.setString(1, title);
            st.setString(2, author);
            st.setInt(3, genre);
            st.setInt(4, rating);
            st.setInt(5, uid);
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> getData() {
        List<Book> data = new LinkedList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet result = st.executeQuery("SELECT * FROM BOOKS");
            while (result.next()) {
                String title = result.getString("Title");
                String author = result.getString("Author");
                BookGenre genre = BookGenre.values()[result.getInt("Genre")];
                BookRatingType rating = BookRatingType.values()[result.getInt("Rating")];
                int uid = result.getInt("UID");
                data.add(new Book(title, author, genre, rating, uid));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void removeBook(Book remove) {
        try {
            int rem = remove.uidProperty().getValue();
            PreparedStatement st = conn.prepareStatement("DELETE FROM BOOKS WHERE UID = ?");
            st.setInt(1, rem);
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}