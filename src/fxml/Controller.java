package fxml;

import db.DBManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Book;
import model.BookGenre;
import model.BookRatingType;
import model.Model;

import java.io.IOException;

public class Controller {

    @FXML
    private TableView<Book> bookView;
    @FXML
    private TableColumn<Book, String> titleCol;
    @FXML
    private TableColumn<Book, String> authorCol;
    @FXML
    private TableColumn<Book, BookGenre> genreCol;
    @FXML
    private TableColumn<Book, BookRatingType> ratingCol;
    @FXML
    private Button add;
    @FXML
    private Button edit;
    @FXML
    private Button remove;

    private DBManager dbm;

    public Controller() {
    }

    @FXML
    public void initialize() {
        bookView.setItems(Model.INSTANCE.courses);
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        genreCol.setCellValueFactory(new PropertyValueFactory<>("genre"));
        ratingCol.setCellValueFactory(new PropertyValueFactory<>("rating"));
    }

    //open the add dialog to enter a new book and its properties
    public void addOnAction(ActionEvent actionEvent) {
        try {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("add.fxml"));
            Scene scene = new Scene(loader.load(), 410, 300);
            AddWindowController adw = loader.getController();
            adw.setDbm(dbm);
            Stage primaryStage = new Stage();
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.initOwner(scene.getWindow());
            primaryStage.setTitle("Add");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //open the edit dialog to edit the properties of the respective entry
    public void editOnAction(ActionEvent actionEvent) {
        try {
            if (!Model.INSTANCE.courses.isEmpty()) {
                //you can only edit book entries that already exist
                final FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("edit.fxml"));
                Scene scene = new Scene(loader.load(), 410, 300);
                EditWindowController editWindow = loader.getController();
                editWindow.setSelectedBook(bookView.getSelectionModel().getSelectedItem());
                editWindow.setDbm(dbm);
                Stage primaryStage = new Stage();
                primaryStage.initModality(Modality.APPLICATION_MODAL);
                primaryStage.initOwner(scene.getWindow());
                primaryStage.setTitle("Edit");
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Remove the entry from the database
    public void removeOnAction(ActionEvent actionEvent) {
        Book remove = bookView.getSelectionModel().getSelectedItem();
        dbm.removeBook(remove);
        if (remove != null) {
            Model.INSTANCE.getIds().remove(remove.uidProperty().getValue());
            Model.INSTANCE.courses.remove(bookView.getSelectionModel().getSelectedIndex());
        }
    }

    public void setDbm(DBManager dbm) {
        this.dbm = dbm;
    }
}