package src.elements2;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Horizos {
	
	int x,y;
	Image Horizos;

	public Horizos(int Startx, int Starty){
		x = Startx;
		y = Starty;

		ImageIcon iHorizos = new ImageIcon("Images/DecorNonFranchissable/horizos.png");
		Horizos = iHorizos.getImage();

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
		return Horizos;
	}

}
