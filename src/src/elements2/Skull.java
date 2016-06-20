package src.elements2;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Skull extends Decor{

	Image Crane;
/**
 * set crane constructor
 * @param Startx =  base position of verticos in x 
 * @param Starty =  base position of verticos in y 
 */
	public Skull(int Startx, int Starty){
		x = Startx;
		y = Starty;

		ImageIcon iCrane = new ImageIcon("Images/DecorNonFranchissable/crane.png");
		Crane = iCrane.getImage();

	}


	public Image getImage(){
		return Crane;
	}

}
