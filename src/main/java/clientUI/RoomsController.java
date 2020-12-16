package clientUI;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class RoomsController{

    private Application parent;
    private static Stage stage;
    @FXML
    Button buttonInputMessage;
    @FXML
    ListView listRoom;
    @FXML
    TextArea messageField;


    public void init(Application parent, Stage stage) throws IOException {
        this.parent = parent;
        this.stage = stage;
        listRoom.setItems(FXCollections.observableArrayList("Java", "JavaScript", "C#", "Python"));
    }

    @FXML
    private void back(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/formFX/MainFX.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
    }

    @FXML
    private void update(ActionEvent event) throws Exception{
        listRoom.setItems(FXCollections.observableArrayList(new Random().toString(),new Random().toString(),
                new Random().toString(), new Random().toString(),new Random().toString()));
    }

    @FXML
    private void inputMessage(ActionEvent event){
        //input message
    }

}
