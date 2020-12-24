package fxml;

import db.DBManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Book;
import model.BookGenre;
import model.BookRatingType;

public class EditWindowController {

    @FXML
    private TextField titleField;
    @FXML
    private TextField authorField;
    @FXML
    private ComboBox<BookGenre> genreBox;
    @FXML
    private ComboBox<BookRatingType> ratingBox;
    @FXML
    private Button save;

    private Book selectedBook;
    private DBManager dbm;

    @FXML
    private void initialize() {
        genreBox.setItems(FXCollections.observableArrayList(BookGenre.values()));
        ratingBox.setItems(FXCollections.observableArrayList(BookRatingType.values()));
    }

    public Book getSelectedBook() {
        return selectedBook;
    }

    public void setSelectedBook(Book selectedBook) {
        this.selectedBook = selectedBook;
        if (this.selectedBook != null) {
            setTextFields(selectedBook);
        }
    }

    private void setTextFields(Book selectedBook) {
        titleField.setText(selectedBook.titleProperty().getValue());
        authorField.setText(selectedBook.authorProperty().getValue());
        genreBox.setValue(selectedBook.genreProperty().getValue());
        ratingBox.setValue(selectedBook.ratingProperty().getValue());
    }

    @FXML
    private void saveOnAction() {
        //checks for preventing user to delete the name/etc
        if (!titleField.getText().equals("") && !authorField.getText().equals("") && genreBox.getValue() != null && ratingBox.getValue() != null) {
            selectedBook.setTitle(titleField.getText());
            selectedBook.setAuthor(authorField.getText());
            selectedBook.setGenre(genreBox.getValue());
            selectedBook.setRating(ratingBox.getValue());

            dbm.editBook(selectedBook);

            Stage stage = (Stage) save.getScene().getWindow();
            stage.close();
        }
    }


    public void setDbm(DBManager dbm) {
        this.dbm = dbm;
    }
}