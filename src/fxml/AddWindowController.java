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
import model.Model;

public class AddWindowController {
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

    private DBManager dbm;

    @FXML
    private void initialize() {
        genreBox.setItems(FXCollections.observableArrayList(BookGenre.values()));
        ratingBox.setItems(FXCollections.observableArrayList(BookRatingType.values()));
    }

    @FXML
    private void saveOnAction() {
        //check if input is given for every property of the book entry
        if (titleField.getText() != null && !titleField.getText().equals("") && authorField.getText() != null && !authorField.getText().equals("") &&
                genreBox.getValue() != null && ratingBox.getValue() != null) {

            Book book = new Book(titleField.getText(), authorField.getText(), genreBox.getValue(), ratingBox.getValue(), Model.INSTANCE.newUID());
            Model.INSTANCE.courses.add(book);
            dbm.addBook(book);

            Stage stage = (Stage) save.getScene().getWindow();
            stage.close();
        }
    }

    public void setDbm(DBManager dbm) {
        this.dbm = dbm;
    }
}