package videoShow.images;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class GetImages {
	public ImageIcon icon;
	
	ArrayList<ImageIcon> imageArray = new ArrayList<ImageIcon>();
	
	public ImageIcon getImage(String imageName){
		icon = new ImageIcon(getClass().getResource(imageName + ".gif"));
		return icon;
		
	}
}