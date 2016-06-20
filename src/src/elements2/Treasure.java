package src.elements2;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Treasure extends Decor{

	Image Treasure;
/**
 * set Treasure constructor
 * @param Startx = base position of verticos in x 
 * @param Starty = base position of verticos in y 
 */
	public Treasure(int Startx, int Starty){
		x = Startx;
		y = Starty;

		ImageIcon iTreasure = new ImageIcon("Images/Tresors/tresor1.png");
		Treasure = iTreasure.getImage();

	}

	public Image getImage(){
		return Treasure;
	}

}
