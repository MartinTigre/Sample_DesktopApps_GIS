package main;

import CircularMenu.RadialMenu;
import CircularMenu.RadialMenuContainer;
import CircularMenu.RadialMenuItem;
import ConfirmBox.ConfirmBoxDisplay;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class RightSideMenu_TreeView {
	
	SampleTreeView sampleTreeView = new SampleTreeView();

	public VBox getRightSideMenuBarContainer() {
		VBox vboxTop = new VBox();
		vboxTop.getChildren().add(getTopMenuBar());

		vboxTop.setPrefWidth(250);
		vboxTop.setPrefHeight(200);
		
		vboxTop.setAlignment(Pos.BOTTOM_CENTER);

		VBox vboxBottom = sampleTreeView.getTreeView();
		
		VBox totalContainer = new VBox();
		
		totalContainer.getChildren().addAll(vboxTop, vboxBottom);
		
		return totalContainer;
		
	}

	private Group getTopMenuBar() {	
			/*********************************
			 * 
			 *Radial Menu 
			 * 
			 ***************************************/
			
		RadialMenu radialMenu = new RadialMenu(20, 80, 180, 3);
	
		radialMenu.setId("radial-menu-container");
	
	        
		RadialMenuContainer firstContainer = new RadialMenuContainer();
		firstContainer.setText("_Layer");
		firstContainer.setChildrenCenterOnParent(true);
		RadialMenuContainer secondContainer = new RadialMenuContainer();
		secondContainer.setText("_Object");
		secondContainer.setChildrenCenterOnParent(true);
		RadialMenuContainer thirdContainer = new RadialMenuContainer();
		thirdContainer.setText("_Print");
	//        RadialMenuItem fourthItem = new RadialMenuItem();
	//        fourthItem.setText("Item");
	//        RadialMenuItem sixthItem = new RadialMenuItem();
	//        sixthItem.setText("Item");
	
		RadialMenuItem firstContainerSecondStage = new RadialMenuItem();
		firstContainerSecondStage.setText("_Add");  
		
		firstContainerSecondStage.setOnMouseClicked( event->{
			
			handleAddLayerOperation();
			
		} );
		
		//I make rename editable
		
		RadialMenuItem firstContainerSecondStage2 = new RadialMenuItem();
		firstContainerSecondStage2.setText("_Rename");
		
		firstContainerSecondStage2.setOnMouseClicked( event -> {
			
			handleRenameOperation();
			
		} );
		
		RadialMenuItem firstContainerSecondStage3 = new RadialMenuItem();
		firstContainerSecondStage3.setText("_Clear");
		
		firstContainerSecondStage3.setOnMouseClicked( event -> {
			
			handleClearOperation();
			
		} );
		
		RadialMenuItem firstContainerSecondStage4 = new RadialMenuItem();
		firstContainerSecondStage4.setText("_Delete");
		
		firstContainerSecondStage4.setOnMouseClicked( event-> {
			
			handleDeleteOperation();
			
		} );
		
		
		firstContainer.addItem(firstContainerSecondStage);
		firstContainer.addItem(firstContainerSecondStage2);
		firstContainer.addItem(firstContainerSecondStage3);
		firstContainer.addItem(firstContainerSecondStage4);
		
		RadialMenuItem secondContainerSecondStage = new RadialMenuItem();
		secondContainerSecondStage.setText("_Polygon");
	//        RadialMenuItem secondContainerSecondStage2 = new RadialMenuItem();
	//        secondContainerSecondStage2.setText("Item");
	//        RadialMenuItem secondContainerSecondStage3 = new RadialMenuItem();
	//        secondContainerSecondStage3.setText("Item");
	//        RadialMenuItem secondContainerSecondStage4 = new RadialMenuItem();
	//        secondContainerSecondStage4.setText("Item");
		secondContainer.addItem(secondContainerSecondStage);
	//        secondContainer.addItem(secondContainerSecondStage2);
	//        secondContainer.addItem(secondContainerSecondStage3);
	//        secondContainer.addItem(secondContainerSecondStage4);
	
		RadialMenuItem thirdContainerSecondStage = new RadialMenuItem();
		thirdContainerSecondStage.setText("_Print Tree");
	//        RadialMenuItem thirdContainerSecondStage2 = new RadialMenuItem();
	//        thirdContainerSecondStage2.setText("Item");
	//        RadialMenuItem thirdContainerSecondStage3 = new RadialMenuItem();
	//        thirdContainerSecondStage3.setText("Item");
	//        RadialMenuItem thirdContainerSecondStage4 = new RadialMenuItem();
	//        thirdContainerSecondStage4.setText("Item");
		thirdContainer.addItem(thirdContainerSecondStage);
	//        thirdContainer.addItem(thirdContainerSecondStage2);
	//        thirdContainer.addItem(thirdContainerSecondStage3);
	//        thirdContainer.addItem(thirdContainerSecondStage4);
	
		radialMenu.addRootItem(firstContainer);
		radialMenu.addRootItem(secondContainer);
		radialMenu.addRootItem(thirdContainer);
	//        radialMenu.addRootItem(fourthItem);
	//        radialMenu.addRootItem(sixthItem);
	//        radialMenu.setLayoutX(700);
	//        radialMenu.setLayoutY(100);
	        
	
	        
	        
		Group group = new Group();
	
		group.getChildren().add(radialMenu);
	        
	     
	    	/*********************************
			 * 
			 *Radial Menu 
			 * 
			 ***************************************/
		GridPane.setConstraints(group, 0, 0);
	 
		return group;
			
	}
	
	
	private void handleAddLayerOperation() {
		ConfirmBoxDisplay.display(sampleTreeView);
	}
	
	
	private void handleDeleteOperation() {
		sampleTreeView.deleteLayerFromLayerList();		
	}

	private void handleClearOperation() {
		sampleTreeView.clearAllLayersFromTheList();
	}
	
	private void handleRenameOperation() {
		sampleTreeView.renameTheSelectedLayer();
	}
	
}
