package elements2;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Verticos {
	
	int x,y;
	Image Verticos;

	public Verticos(int Startx, int Starty){
		x = Startx;
		y = Starty;

		ImageIcon iVerticos = new ImageIcon("Images/DecorNonFranchissable/Verticos.png");
		Verticos = iVerticos.getImage();

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
		return Verticos;
	}

}
