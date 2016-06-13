package src.elements2;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Tresor2 {
	
	int x,y;
	Image Tresor2;

	public Tresor2(int Startx, int Starty){
		x = Startx;
		y = Starty;

		ImageIcon iTresor2 = new ImageIcon("Images/Tresors/tresor2.png");
		Tresor2 = iTresor2.getImage();

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
		return Tresor2;
	}

}
