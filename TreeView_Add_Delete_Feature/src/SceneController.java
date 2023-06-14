import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.BorderPane;

public class SceneController {

	public Scene generateScene() {
		
		Scene scene;
		
		BorderPane mainLayout = new BorderPane();
		
		//it may be inside of the constructor.
		TreeViewGenerator treeViewGenerator = new TreeViewGenerator();	
		
		//it may not be inside of the constructor, we can create inside of the class and call it!
		mainLayout.setCenter(treeViewGenerator.getTreeViewLayout());

		FooterGenerator footerGenerator = new FooterGenerator();
		
		
		//I wanted to handle button operations in another class. Here...
		footerGenerator.getFooter().getChildren().forEach(node -> {
			if(node instanceof Button) {
				Button button = (Button) node;
				
				
				//Add Button Operation
				if(button.getText().equals("Add")) {
					button.setOnAction( e-> {
						TreeItem<String> treeItemSelected = treeViewGenerator.createListenerForTreeView();
				
						
						//We are checking if the added element is child of the first item of the treeView
						if(isDescendant(treeItemSelected, treeViewGenerator.getTreeView().getTreeItem(0))) {
							
							//I wanted to add this item inside of another method
							
							PopUpsConfirmations.showAddPopUp(treeViewGenerator, treeItemSelected);
							
						}
						
					} );
				}
				
				
				if(button.getText().equals("Delete")) {
					button.setOnAction( e-> {
			
						TreeItem<String> treeItemSelected = treeViewGenerator.createListenerForTreeView();
						
						//I do not want to delete the "Object" layer because it sees the object layer as the child of itself and deletes it
						
						if(isDescendant(treeItemSelected, treeViewGenerator.getTreeView().getTreeItem(0)) && !(treeItemSelected.getValue().equals("Objects"))) {
							
							PopUpsConfirmations.showDeletePopUp(treeViewGenerator, treeItemSelected);
							
						}
						
					} );
				}
			}
		});
		
		mainLayout.setBottom(footerGenerator.getFooter());
		
		scene = new Scene(mainLayout);
		
		return scene;
		
	}


	//method checking if it is child of
	private boolean isDescendant(TreeItem<String> item, TreeItem<String> potentialParent) {
      if (item == potentialParent) {
	        return true;
	    }

	    for (TreeItem<String> child : potentialParent.getChildren()) {
	        if (isDescendant(item, child)) {
	            return true;
	        }
	    }

	    return false;
	}
	
}
