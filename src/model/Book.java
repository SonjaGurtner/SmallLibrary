package model;

import javafx.beans.property.*;

public class Book {
    private StringProperty title;
    private StringProperty author;
    private ObjectProperty<BookGenre> genre;
    private ObjectProperty<BookRatingType> rating;
    private IntegerProperty uid;                        //an unique id for identifying the book in the Database

    public Book(String title, String author, BookGenre type, BookRatingType grade, int uid) {
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.genre = new SimpleObjectProperty<>(type);
        this.rating = new SimpleObjectProperty<>(grade);
        this.uid = new SimpleIntegerProperty(uid);
    }

    public StringProperty titleProperty() {
        return title;
    }

    public StringProperty authorProperty() {
        return author;
    }

    public ObjectProperty<BookGenre> genreProperty() {
        return genre;
    }

    public ObjectProperty<BookRatingType> ratingProperty() {
        return rating;
    }

    public IntegerProperty uidProperty() {
        return uid;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public void setGenre(BookGenre genre) {
        this.genre.set(genre);
    }

    public void setRating(BookRatingType rating) {
        this.rating.set(rating);
    }

    public void setUID(int uid) {
        this.uid.set(uid);
    }
}