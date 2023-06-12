package main;
import java.util.Iterator;

import javax.security.auth.callback.ConfirmationCallback;

import ConfirmBox.ConfirmBoxDisplay;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class SampleTreeView {
	//TreeView
	private ObservableList<TreeItem<String>> mainChildren = FXCollections.observableArrayList();
	private ObservableList<TreeItem<String>> layerItems = FXCollections.observableArrayList();
	
	
	
	private TreeItem<String> root = new TreeItem<String>();
	private TreeView<String> treeView = new TreeView<String>();
	
	String[] treeViewItemNames = { "Objects", "Disk", "TitleLayer", "WMS", "WMFS", "WFS", "Google", "Yandex", "Bing", "OviHere" };
	
	String[] dummyLayersAlreadyCreated = { "Test1", "test layer nejo", "destiny" }; 

	public SampleTreeView() {
		super();
		init_theTreeView();
	}

	private void init_theTreeView() {
		initRoot_LayerTreeView();
		TreeItem<String> child;
		for(String treeViewItemName : treeViewItemNames) {
			child = new TreeItem<String>(treeViewItemName);
			child.setGraphic(new CheckBox());
			mainChildren.add(child);
		}

		root.setExpanded(true);
		root.getChildren().addAll(mainChildren);
		
		root.getChildren().get(0).getChildren().addAll(layerItems);

		root.getChildren().get(0).setExpanded(true);
		
		
		treeView = new TreeView<String>(root);
		treeView.setShowRoot(false);
		treeView.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);
		 
		GridPane.setConstraints(treeView, 0, 1);
	}

	

	private void initRoot_LayerTreeView() {
		TreeItem<String> childLayer;
		for(String childLayerName: dummyLayersAlreadyCreated) {
			childLayer = new TreeItem<String>(childLayerName);
			childLayer.setGraphic(new CheckBox());
			layerItems.add(childLayer);
		}
	}
	
	public VBox getTreeView() {
		
		VBox vboxBottom = new VBox();
		
		vboxBottom.setPrefWidth(200);
		
		vboxBottom.getChildren().add(this.treeView);

		return vboxBottom;
	
	}

	public void addLayerToTheLayerList(String layerTitle) {
	    TreeItem<String> newTreeItem = new TreeItem<>(layerTitle);
	    newTreeItem.setGraphic(new CheckBox());
	    
	    if(searchOnTheLayerItemlist(newTreeItem)) {
	    	ConfirmBoxDisplay.displayAlertBox_layerAlreadyThere();
	    }else {
	    	layerItems.add(newTreeItem);
		    TreeItem<String> firstChild = this.treeView.getRoot().getChildren().get(0);
		    firstChild.getChildren().setAll(layerItems);
	    }
	}
	
	
	public void deleteLayerFromLayerList() {
		ObservableList<TreeItem<String>> allLayers, layersSelected;
		layersSelected = FXCollections.observableArrayList();
		allLayers = treeView.getRoot().getChildren().get(0).getChildren();
		
		for(TreeItem<String> selectedItem : allLayers) {
			CheckBox checkBox = (CheckBox) selectedItem.getGraphic();
			
			if(checkBox.isSelected()) {
				layersSelected.add(selectedItem);
			}
			
		}
		
		if(layersSelected.size() == 0) {
			ConfirmBoxDisplay.selectLayerFirstToDeleteAlert();
		}else {
			if(ConfirmBoxDisplay.displayConfirmationForDeleteOperation()) {
				layersSelected.forEach(allLayers::removeAll);
				layerItems.removeAll(layersSelected);
				System.out.println("Layers : " + layersSelected.toString() + " are deleted from the list");		
			}
		}
	}
	
	
	public void clearAllLayersFromTheList() {
		ObservableList<TreeItem<String>> allLayers;
		allLayers = treeView.getRoot().getChildren().get(0).getChildren();
		
		allLayers.removeAll(layerItems);
		
		layerItems.clear();
		
		System.out.println("After clearing all the items inside layer items" + layerItems.toString());
		
	}
	
	
	public void renameTheSelectedLayer() {
		ObservableList<TreeItem<String>> allLayers, layersSelected;
		layersSelected = FXCollections.observableArrayList();
		allLayers = treeView.getRoot().getChildren().get(0).getChildren();
		
		for(TreeItem<String> selectedItem : allLayers) {
			CheckBox checkBox = (CheckBox) selectedItem.getGraphic();
			
			if(checkBox.isSelected()) {
				layersSelected.add(selectedItem);
			}			
		}
		if(layersSelected.size() > 1) {
			ConfirmBoxDisplay.multipleItemSelectionToRenameAlert();
		}else if(layersSelected.size() == 0) {
			ConfirmBoxDisplay.noSelectedItemToRenamAlert();
		}else {
			TreeItem<String> selectedLayer = layersSelected.get(0);
			String newName = ConfirmBoxDisplay.renameLayer_getOutput(selectedLayer.getValue());
			
			TreeItem<String> newItem = new TreeItem<>(newName);
 			
			if(searchOnTheLayerItemlist(newItem)) {
			    ConfirmBoxDisplay.displayAlertBox_layerAlreadyThere();
			}else {
				selectedLayer.setValue(newName);
				
				System.out.println("After rename process layer list items are : " + layerItems.toString());
			}
		}
	}
	
	
	private boolean searchOnTheLayerItemlist(TreeItem<String> treeItem) {
		
		FilteredList<TreeItem<String>> filteredData = new FilteredList<>(layerItems);
		
		filteredData.setPredicate( item -> item.getValue().equals(treeItem.getValue()));
		

		System.out.println("Filtered data : " + filteredData);
		
		
		if(filteredData.size() > 0) {
			return true;
		}else {
			return false;
		}
	}
	
}
