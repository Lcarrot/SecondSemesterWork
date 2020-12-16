package clientUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class MainController extends Application {

    private RoomsController roomsController;
    private AddRoomController addRoomController;
    private static Stage stage;
    MediaPlayer mediaPlayer;


    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        stage.setTitle("Hello world");
        InputStream iconStream = getClass().getResourceAsStream("/img/icon.jpg");
        Image image = new Image(iconStream);
        stage.getIcons().add(image);
        stage.setWidth(1200);
        stage.setHeight(700);

        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/formFX/MainFX.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        stage.setScene(new Scene(root));
        stage.setMinHeight(600);
        stage.setMinWidth(1000);

        if ((mediaPlayer == null)) {
            Media sound = new Media(String.valueOf(getClass().getResource("/music/menu.mp3")));
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setAutoPlay(true);
            mediaPlayer.setCycleCount(-1);
        }

        stage.show();

    }

    @FXML
    private void clickCommunicate(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/formFX/ListRooms.fxml"));
        Parent root = loader.load();
        roomsController = loader.getController();
        roomsController.init(this, stage);
        stage.setScene(new Scene(root));

    }

    @FXML
    private void clickPlay(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/formFX/AddRoom.fxml"));
        Parent root = loader.load();
        addRoomController = loader.getController();
        addRoomController.init(this, stage);
        stage.setScene(new Scene(root));
    }


    @FXML
    private void clickClose(ActionEvent event) throws Exception {
        stage.close();
    }


    public static void main(String[] args) {
        Application.launch(args);
    }
}

