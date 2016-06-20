package src.elements2;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Stone extends Decor{

	Image Stone;
/**
 * set stone constructor
 * @param Startx = base position of verticos in x 
 * @param Starty = base position of verticos in y 
 */
	public Stone(int Startx, int Starty){
		x = Startx;
		y = Starty;

		ImageIcon iStone = new ImageIcon("Images/DecorNonFranchissable/pierre.png");
		Stone = iStone.getImage();

	}


	public Image getImage(){
		return Stone;
	}
}
