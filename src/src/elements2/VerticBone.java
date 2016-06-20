package src.elements2;

import java.awt.Image;
import javax.swing.ImageIcon;

public class VerticBone extends Decor{

	Image VerticBone;
/**
 * set verticos constructor
 * @param Startx = base position of verticos in x 
 * @param Starty = base position of verticos in y 
 */
	public VerticBone(int Startx, int Starty){
		x = Startx;
		y = Starty;

		ImageIcon iVerticBone = new ImageIcon("Images/DecorNonFranchissable/Verticos.png");
		VerticBone = iVerticBone.getImage();

	}


	public Image getImage(){
		return VerticBone;
	}

}
