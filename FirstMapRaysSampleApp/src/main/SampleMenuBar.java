package main;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class SampleMenuBar {

	private MenuBar topMenuBar = new MenuBar();
	
	public SampleMenuBar() {
		initMenuBar();
	}
	
	private void initMenuBar() {
		topMenuBar.getMenus().addAll(getModsMenu(), getDimensionMenu());
	}

	private Menu getModsMenu() {
		
		Menu topMenuMods = new Menu("_Mod");
		
		String[] modsMenuNames = { "_Pan", "_Rotate", "_Select", "_Zoom" };
		
		MenuItem menuItem;
		
		for(String modMenuName: modsMenuNames) {
			menuItem = new MenuItem(modMenuName);
			
			topMenuMods.getItems().add(menuItem);			
		}
				
		return topMenuMods;
	}
	
	
	private Menu getDimensionMenu() {
		
		Menu topMenuDimensions = new Menu("_Dimensions");
		
		MenuItem mi_2d = new MenuItem("2D");
		MenuItem mi_3d = new MenuItem("3D");
		
		topMenuDimensions.getItems().addAll(mi_2d, mi_3d);
		
		mi_2d.setOnAction( e-> {
			System.out.println("2D feature is activated");
		} );
		
		mi_3d.setOnAction( e-> { 
			System.out.println("3D feature is activated");
		} );
		
		return topMenuDimensions;
		
		
	}
	
	public MenuBar getTopMenuBar() {
		return topMenuBar;
	}
	
	
}
