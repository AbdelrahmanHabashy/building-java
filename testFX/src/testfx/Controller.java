package testfx;

import java.io.IOException;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Controller {
    private Stage stage; 
    private Scene scene; 
    
    public void toScene1(ActionEvent e) throws IOException {
        showIntermediateScreen(e, "Screen1.fxml");
    }
    
    public void toScene2(ActionEvent e) throws IOException {
        showIntermediateScreen(e, "Screen2.fxml");
    }
    
    private void showIntermediateScreen(ActionEvent e, String targetFXML) throws IOException {
        // Load the intermediate screen
        Parent loadingScreen = FXMLLoader.load(getClass().getResource("temp.fxml"));
        
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(loadingScreen);
        stage.setScene(scene);
        stage.show();
        
        // Delay of 0.5 seconds before switching to the target screen
        PauseTransition pause = new PauseTransition(Duration.seconds(0.05));
        pause.setOnFinished(event -> {
            try {
                Parent targetRoot = FXMLLoader.load(getClass().getResource(targetFXML));
                Scene targetScene = new Scene(targetRoot);
                stage.setScene(targetScene);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        
        pause.play();
    }
}
