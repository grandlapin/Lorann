package src.elements2;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Open extends Decor{

	

	Image Open;
/**
 * set Open constructor
 * @param Startx = base position of verticos in x 
 * @param Starty = base position of verticos in y 
 */
	public Open(int Startx, int Starty){
		x = Startx;
		y = Starty;

		ImageIcon iOpen = new ImageIcon("Images/DecorFranchissable/ouverte.png");
		Open = iOpen.getImage();

	}

	public Image getImage(){
		return Open;
	}
}