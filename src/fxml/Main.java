package fxml;

import db.ConnectionManager;
import db.DBManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Model;

import java.sql.Connection;
import java.sql.SQLException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Connection conn = ConnectionManager.connect();
        DBManager dbm = new DBManager(conn);
        dbm.setUpTable();

        Model.INSTANCE.loadData(dbm.getData());
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("mainUI.fxml"));
        final Parent root = loader.load();
        Controller controller = loader.getController();
        controller.setDbm(dbm);

        primaryStage.setTitle("SmallLibrary");
        primaryStage.setScene(new Scene(root, 595, 500));
        primaryStage.show();

        primaryStage.setOnCloseRequest(e -> {
            try {
                conn.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
    }
}