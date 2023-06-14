import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TreeV_Main extends Application{

	Stage window;
	
	SceneController sceneController = new SceneController();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
	
		window = primaryStage;
		
		window.setTitle("--Featured Tree View--");
		
		window.setScene(sceneController.generateScene());
		
		window.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
