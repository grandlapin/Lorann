package src.elements2;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Tresor3 {
	
	int x,y;
	Image Tresor3;

	public Tresor3(int Startx, int Starty){
		x = Startx;
		y = Starty;

		ImageIcon iTresor3 = new ImageIcon("Images/Tresors/tresor3.png");
		Tresor3 = iTresor3.getImage();

	}

	public Rectangle getBounds(){
		Rectangle Box = new Rectangle (x,y,16,16);
		return Box;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}

	public Image getImage(){
		return Tresor3;
	}

}
