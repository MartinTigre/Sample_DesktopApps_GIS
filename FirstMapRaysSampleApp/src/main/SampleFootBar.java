package main;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class SampleFootBar {

	private HBox footBar = new HBox();

	public SampleFootBar() {}


	public HBox getCompleteFootBar() {
		
		Button btn1 = new Button("Yükseklik Ver");
		Button btn2 = new Button("Bluemarble Rename");
		Button btn3 = new Button("Bluemarble Sil");

		Button btn4 = new Button("SubName WMS");
		
		footBar.setPadding(new Insets(10, 10, 10, 10));
		footBar.setSpacing(10);
		footBar.setAlignment(Pos.CENTER);
		footBar.getChildren().addAll(btn1, btn2, btn3, btn4);
		
		
		
		
		return footBar;
	}

	public HBox createSourceHBox() {
        HBox hbox = new HBox();
        hbox.setPrefSize(50, 50);
        hbox.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #1fbfc0, #11424d);");
        return hbox;
    }
	
	public ScaleTransition createScaleTransition(Pane source, HBox target) {
        target.setScaleX(0);
        target.setScaleY(0);
        target.setLayoutX(source.getLayoutX());
        target.setLayoutY(source.getLayoutY());

        source.getChildren().add(target);

        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1), target);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);
        scaleTransition.setOnFinished(event -> {
            source.getChildren().remove(target);
        });

        return scaleTransition;
    }


	
}
