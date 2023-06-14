import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PopUpsConfirmations {

	
	public static void showAddPopUp(TreeViewGenerator treeViewGenerator, TreeItem<String> selectedItem) {
		Stage window = new Stage();
		
		window.setTitle("---Add Layer PopUp---");
		
		BorderPane mainLayout = new BorderPane();
		
		final Text text = new Text(25, 225, "Add aDd adD ADD!!!!!!!");
		text.setFill(Color.DARKGREEN);
		text.setBlendMode(BlendMode.COLOR_BURN);
		text.setFont(Font.font(java.awt.Font.DIALOG_INPUT, FontWeight.THIN, 45));
		final Reflection reflection = new Reflection();
		reflection.setFraction(1.0);
		text.setEffect(reflection);

		TextField layerNameTF = new TextField();
		layerNameTF.setPromptText("Enter the new layer name then : ");
		
	    HBox bottomLayout = new HBox();
	    
	    Button btnYes = new Button("Yes");
		
	    btnYes.setOnAction( e-> {
	    	
	    	TreeItem<String> newItem = new TreeItem<String>(layerNameTF.getText());
	    	
	    	treeViewGenerator.createChild(selectedItem, newItem);
	    	
	    	window.close();
	    });
	    
	    Button btnNo = new Button("No");
	    
	    btnNo.setOnAction( e-> {
	    	e.consume();
	    	window.close();
	    } );
	    
	    
	    window.setOnCloseRequest( e-> {
	    	e.consume();
	    	window.close();
	    } );
	    
	    bottomLayout.getChildren().addAll(btnYes, btnNo);
	    
		mainLayout.setTop(text);
		
		mainLayout.setCenter(layerNameTF);
		
		mainLayout.setBottom(bottomLayout);
		
		Scene scene = new Scene(mainLayout);
		
		window.setScene(scene);
		window.show();
	}
	
	
	
	public static void showDeletePopUp(TreeViewGenerator treeViewGenerator, TreeItem<String> selectedItem) {
		Stage window = new Stage();
		
		window.setTitle("---Add Layer PopUp---");
		
		BorderPane mainLayout = new BorderPane();
		
		final Text text = new Text(25, 225, "Delete Baby you");
		text.setFill(Color.DARKRED);
		text.setBlendMode(BlendMode.COLOR_BURN);
		text.setFont(Font.font(java.awt.Font.DIALOG_INPUT, FontWeight.BOLD, 45));
		final Reflection reflection = new Reflection();
		reflection.setFraction(1.0);
		text.setEffect(reflection);

		
	    HBox bottomLayout = new HBox();
	    
	    Button btnYes = new Button("Yes");
		
	    btnYes.setOnAction( e-> {

	    	treeViewGenerator.deleteChild(treeViewGenerator.getTreeView(), selectedItem);
	    	
	    	window.close();
	    });
	    
	    Button btnNo = new Button("No");
	    
	    btnNo.setOnAction( e-> {
	    	e.consume();
	    	window.close();
	    } );
	    
	    
	    window.setOnCloseRequest( e-> {
	    	e.consume();
	    	window.close();
	    } );
	    
	    bottomLayout.getChildren().addAll(btnYes, btnNo);
	    
		mainLayout.setTop(text);
		
		mainLayout.setBottom(bottomLayout);
		
		Scene scene = new Scene(mainLayout);
		
		window.setScene(scene);
		window.show();
	}
	
	
}
