package ConfirmBox;

import main.SampleTreeView;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ConfirmBoxDisplay {
	private static boolean answer_delete;
	
	private static String newName = "";
	
	private static BooleanProperty answer_delete_prop = new SimpleBooleanProperty();
	
	public static void display(SampleTreeView sampleTreeView) {
		
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		
		window.initStyle(StageStyle.UNDECORATED);
		
		window.setWidth(375);
		
		//Add Layer Window Buttons
		
		Button addBtn = new Button("Add");
		
		Button cancelBtn = new Button("Cancel");
		
	
		//Layer Information
		
		Label layerNameLabel = new Label("Enter the Layer name : ");
		
		TextField layerNameTF = new TextField();
		
		HBox layerInfoContainer = new HBox();
		
		layerInfoContainer.getChildren().addAll(layerNameLabel, layerNameTF);
		

		HBox btnContainer = new HBox();
		
		btnContainer.getChildren().addAll(addBtn, cancelBtn);
		btnContainer.setMargin(addBtn, new Insets(10,10,10,10));
		btnContainer.setAlignment(Pos.CENTER);

		VBox layout = new VBox();
		
		layout.setId("add-confirm-container");
		
		layout.setPadding(new Insets(20, 20, 20, 20));
		
		layout.getChildren().addAll(layerInfoContainer, btnContainer);		
		
		//button actions
		
		addBtn.setOnAction( e-> {
			System.out.println("layer '" + layerNameTF.getText() + "' is added to the layer list");
			
			sampleTreeView.addLayerToTheLayerList(layerNameTF.getText());
			
			layerNameTF.setText("");
			window.close();
			layout.getChildren().removeAll(layerInfoContainer, btnContainer);
		} );
		
		cancelBtn.setOnAction( e-> {
			e.consume();
			window.close();
			layout.getChildren().removeAll(layerInfoContainer, btnContainer);
		} );
		
		window.setOnCloseRequest( e-> {
			e.consume();
			window.close();
			layout.getChildren().removeAll(layerInfoContainer, btnContainer);
			
		});
		
		Scene scene = new Scene(layout, Color.rgb(32,155,157));
		
		scene.getStylesheets().add("ConfirmboxesStyleSheet.css");
		
		window.setScene(scene);
		
		window.show();
	}
	
	
	public static boolean displayConfirmationForDeleteOperation() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure you want to delete this item");
       
        Image image = new Image("futuristic_question_mark-modified.png");
        
        ImageView imageView = new ImageView(image);
        
        imageView.setFitWidth(70);
        imageView.setFitHeight(70);

        StackPane stackPane = new StackPane();
     
        stackPane.getChildren().add(imageView);
        
        alert.setGraphic(stackPane);

        
        ButtonType confirmButton = new ButtonType("Delete", ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(confirmButton, cancelButton);
        return alert.showAndWait().filter(buttonType -> buttonType == confirmButton).isPresent();
	}
	
	
	public static String renameLayer_getOutput(String oldName) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("New Layer Name For " + oldName );
        alert.setHeaderText("Rename Item");
        ButtonType renameBtn = new ButtonType("Rename", ButtonData.OK_DONE);
        ButtonType cancelBtn = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

        
        alert.getButtonTypes().setAll(renameBtn, cancelBtn);
        
        Label labelRename = new Label("Enter the new Name for " + oldName + " : ");
        TextField renameTF = new TextField();
        
        HBox buttonContainer = new HBox();
        HBox textContainer = new HBox();

        textContainer.getChildren().addAll(labelRename, renameTF);
        
        VBox totalContainer = new VBox();
        
        totalContainer.getChildren().addAll(buttonContainer, textContainer);
        DialogPane dialogPane = alert.getDialogPane();
        
        dialogPane.setContent(totalContainer);
        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == renameBtn) {
               newName = renameTF.getText();
            } else if (buttonType == cancelBtn) {
                newName = oldName;
            }
        });
        return newName;
		
	}
	
	
	public static void multipleItemSelectionToRenameAlert() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Alert!!!");
        alert.setHeaderText("Please Select only one layer to rename");
        ButtonType closeButton = new ButtonType("Close", ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(closeButton);
        alert.showAndWait();
	}
	
	
	public static void selectLayerFirstToDeleteAlert() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Alert!!!");
        alert.setHeaderText("Please select item to delete!");
        ButtonType closeButton = new ButtonType("Close", ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(closeButton);
        alert.showAndWait();
	}
	
	public static void noSelectedItemToRenamAlert() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Alert!!!");
        alert.setHeaderText("Please select one item to rename");
        ButtonType closeButton = new ButtonType("Close", ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(closeButton);
        alert.showAndWait();
	}
	
	
	public static void displayAlertBox_layerAlreadyThere() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Alert!!!");
        alert.setHeaderText("There is already a layer with the same name");
        ButtonType closeButton = new ButtonType("Close", ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(closeButton);
        alert.showAndWait();
	}
	
	public static boolean getAnswerForDeletion() {
		return answer_delete;
	}
	
	public static BooleanProperty getAnswerDeleteProperty() {
		return answer_delete_prop;
	}
	
}
