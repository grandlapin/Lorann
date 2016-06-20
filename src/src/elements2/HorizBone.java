package src.elements2;

import java.awt.Image;

import javax.swing.ImageIcon;

public class HorizBone extends Decor{

	Image HorizBone;
/**
 * set horizos constructor
 * @param Startx = base position of verticos in x 
 * @param Starty = base position of verticos in y 
 */
	public HorizBone(int Startx, int Starty){
		x = Startx;
		y = Starty;

		ImageIcon iHorizBone = new ImageIcon("Images/DecorNonFranchissable/horizos.png");
		HorizBone = iHorizBone.getImage();

	}


	public Image getImage(){
		return HorizBone;
	}

}
