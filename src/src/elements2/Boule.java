package elements2;


import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Boule {

	
	int x,y;
	Image Boule;

	public Boule(int Startx, int Starty){
		x = Startx;
		y = Starty;

		ImageIcon iBoule = new ImageIcon("Images/DecorFranchissable/boule.png");
		Boule = iBoule.getImage();

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
		return Boule;
	}
}
