import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class FooterGenerator {

	
	HBox layout = new HBox();

	public FooterGenerator() {
		initFooter();
	}
	
	
	public void initFooter() {
		
		generateFooter();
		
	}
	
	public void generateFooter() {
		
		Button btn1 = new Button("Add");
		
		Button btn2 = new Button("Delete");
		
		btn2.setOnAction( e-> {
			System.out.println("Delete Button is clicked");
		} );
		
		Button btn3 = new Button("Rename");
		
		btn3.setOnAction( e-> {
			System.out.println("Rename Button is clicked");
		} );
		
		layout.getChildren().addAll(btn1, btn2, btn3); 
		
	}
	
	public HBox getFooter() {
		return layout;
	}
	
	
	
}
