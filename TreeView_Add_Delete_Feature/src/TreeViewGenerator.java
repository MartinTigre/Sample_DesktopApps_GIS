import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;

public class TreeViewGenerator {

	TreeView<String> treeView = new TreeView<>();

	String[] treeViewItemNames = { "Objects", "Disk", "TitleLayer", "WMS", "WMFS", "WFS", "Google", "Yandex", "Bing", "OviHere" };
	
	String[] dummyLayerNames = { "Nejo loves watermelon", "Banana is awesome", "Hell yeah baby! Avocado" };
	
	ObservableList<TreeItem<String>> mainLayers = FXCollections.observableArrayList();
	
	ObservableList<TreeItem<String>> layerItems = FXCollections.observableArrayList();
	
	VBox layout = new VBox();
	
//	TreeItemHandler treeItemHandler = new TreeItemHandler();
	
	TreeItem<String> selectedItem = null;
	
	public TreeViewGenerator() {
		initTreeView();
	}
	
	public void initTreeView() {
		//There will be some DataBase operations!!! 
		//For now, there only be a dummy db generation
		
		init_mainRoot();
		
	}
	
	
	public void init_mainRoot() {
		
		TreeItem<String> rootLayer = new TreeItem<String>();
		
		TreeItem<String> childLayer;
		
		for(String childLayer_Name: treeViewItemNames) {
			childLayer = new TreeItem<String>(childLayer_Name);
			childLayer.setGraphic(new CheckBox());
			mainLayers.add(childLayer);
		}
		
		rootLayer.setExpanded(true);
		rootLayer.getChildren().addAll(mainLayers);
	
		treeView.setRoot(rootLayer);
		treeView.setShowRoot(false);
		initLayerItems_Layer();
	}
	
	public void initLayerItems_Layer() {
		TreeItem<String> childItem_Layer;
		
		for(String childLayer_Name: dummyLayerNames) {
			childItem_Layer = new TreeItem<String>(childLayer_Name);
			childItem_Layer.setGraphic(new CheckBox());
			layerItems.add(childItem_Layer);
		}
		
		treeView.getTreeItem(0).getChildren().addAll(layerItems);
		treeView.getTreeItem(0).setExpanded(true);
	}
	
	public VBox getTreeViewLayout(){
		
		layout.getChildren().add(treeView);
		
		return layout;
	}
	
	public TreeView<String> getTreeView(){
		return treeView;
	}
	
	public TreeItem<String> createListenerForTreeView() {
		
		treeView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		return treeView.getSelectionModel().getSelectedItem();
		
	}

	public void createChild(TreeItem<String> selectedItem, TreeItem<String> newItem) {
		
		selectedItem.setExpanded(true);

		newItem.setGraphic(new CheckBox());
		
		selectedItem.getChildren().add(newItem);
		
	}
	
	
	public void deleteChild(TreeView<String> treeView, TreeItem<String> selectedItem) {
		if (selectedItem != null) {
			
			//If the parent is null, parent's parent is null and it is not logical to delete that item
			
			TreeItem<String> parent = selectedItem.getParent();
            if (parent != null) {
                parent.getChildren().remove(selectedItem);
                
            //Program will not enter else part as we say do not delete the first item of the tree view but in case of any update, it is written    
            } else {
                treeView.setRoot(null);
            }
        }
	}
	
}
