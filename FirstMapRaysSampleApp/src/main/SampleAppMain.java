package main;
import CircularMenu.RadialMenu;
import CircularMenu.RadialMenuContainer;
import CircularMenu.RadialMenuItem;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SampleAppMain extends Application{

	
	Stage window;
	
	Scene mainScene;
	
	BorderPane mainLayout = new BorderPane();

	private boolean flag_OCR = false;
	
	private boolean flag_OCB = false; 
	
	private HBox previousLayout_Bottom; 
	
	@Override
	public void start(Stage primaryStage) throws Exception {
	

		SampleMenuBar sampleMenuBar = new SampleMenuBar();

		RightSideMenu_TreeView rightSideMenu_TreeView = new RightSideMenu_TreeView();
		
		SampleFootBar sampleFootBar = new SampleFootBar();
		
		window = primaryStage;
		
		window.setTitle("1. GIS sample App");

		mainLayout.setPadding(new Insets(10, 10, 10, 10));

		//getting the screen width
		Rectangle2D screenBounds = Screen.getPrimary().getBounds();
		double screenWidth = screenBounds.getWidth();
		double screenHeight = screenBounds.getHeight();
		
		
		Button btnOpenCloseRight = new Button("-> | | <-");
		
		Button btnOpenCloseBottom = new Button("Close Bottom Tab");
		
		
		btnOpenCloseRight.setAlignment(Pos.CENTER_RIGHT);
		
		//top menu bar declaration
		HBox topMenuBarContainer = new HBox();
		topMenuBarContainer.getChildren().addAll(sampleMenuBar.getTopMenuBar(), btnOpenCloseRight, btnOpenCloseBottom);
		topMenuBarContainer.setMinWidth(0);
		topMenuBarContainer.setMaxWidth(screenWidth);
		mainLayout.setTop(topMenuBarContainer);
		
		//setting class name for css
		topMenuBarContainer.getStyleClass().add("top-menubar-container");

		//right side treeView and menu bar declaration
		VBox rightSideMenus = new VBox();
		
		
		rightSideMenus.getChildren().addAll(rightSideMenu_TreeView.getRightSideMenuBarContainer());

		rightSideMenus.getStyleClass().add("right-side-menus-container");
		rightSideMenus.setMaxHeight(screenHeight);
		rightSideMenus.setMinHeight(0);
		mainLayout.setRight(rightSideMenus);
		
		
		double scaleXRight = mainLayout.getRight().getScaleX();
	
		btnOpenCloseRight.setOnAction(event -> {

		    if(flag_OCR) {
				flag_OCR = false;
				
				mainLayout.setRight(rightSideMenus);
				
				TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), mainLayout.getRight());
				transition.setFromX(mainScene.getWidth());
				transition.setToX(scaleXRight);
	            transition.setOnFinished(finishedEvent -> {
	            	mainLayout.setRight(rightSideMenus);
	            });
	            transition.play();
			}else {
				flag_OCR = true;
				TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), mainLayout.getRight());
				transition.setToX(mainScene.getWidth());
				   transition.setOnFinished(e -> {
				       mainLayout.setRight(null);
				});
				   
				   
				TranslateTransition transitionCenter   = new TranslateTransition(Duration.seconds(0.5), mainLayout.getCenter());
				
				transitionCenter.setByX(mainScene.getWidth());			
				transitionCenter.play();
				
				transition.play();
			}
		    
		});
		

		HBox initialHbox = sampleFootBar.createSourceHBox();
		
		HBox completedHbox = sampleFootBar.getCompleteFootBar();
		
		mainLayout.setBottom(initialHbox);
		previousLayout_Bottom = initialHbox;
		
		initialHbox.setAlignment(Pos.BOTTOM_LEFT);
		
		initialHbox.setOnMouseClicked( event -> {
			ScaleTransition scaleTransition = sampleFootBar.createScaleTransition(initialHbox, completedHbox);
	        scaleTransition.play();
	        
	        mainLayout.setBottom(completedHbox);
	        previousLayout_Bottom = completedHbox;
	        
		} );
		
		
		completedHbox.setOnMouseClicked( event -> {
			ScaleTransition scaleTransition = sampleFootBar.createScaleTransition(completedHbox, initialHbox);
	        scaleTransition.play();
	        
	        mainLayout.setBottom(initialHbox);
	        previousLayout_Bottom = initialHbox;
		});
		

		double scaleYBottom = mainLayout.getBottom().getScaleY();
		btnOpenCloseBottom.setOnAction( event -> {
			if(flag_OCB) {
				flag_OCB = false;
				
				mainLayout.setBottom(previousLayout_Bottom);
				
				TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), mainLayout.getBottom());
				transition.setFromY(mainScene.getHeight());
				transition.setToY(scaleYBottom);
	            transition.setOnFinished(finishedEvent -> {
	            	mainLayout.setBottom(previousLayout_Bottom);
	            });
	            transition.play();
				
			}else {
				flag_OCB = true;
				TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), mainLayout.getBottom());
				transition.setToY(mainScene.getHeight());
				   transition.setOnFinished(e -> {
				       mainLayout.setBottom(null);
				});
				transition.play();
			}
		} );
		
		mainScene = new Scene(mainLayout, 800, 600);
		
		mainScene.getStylesheets().add("style.css");
		
		window.setScene(mainScene);
		
		window.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
