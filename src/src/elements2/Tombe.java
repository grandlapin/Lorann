package src.elements2;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Tombe {

	int x,y;
	Image Tombe;

	public Tombe(int Startx, int Starty){
		x = Startx;
		y = Starty;

		ImageIcon iTombe = new ImageIcon("Images/DecorNonFranchissable/tombe.png");
		Tombe = iTombe.getImage();

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
		return Tombe;
	}

}
