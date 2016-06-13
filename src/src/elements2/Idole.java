package elements2;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Idole {

	int x,y;
	Image Idole;

	public Idole(int Startx, int Starty){
		x = Startx;
		y = Starty;

		ImageIcon iIdole = new ImageIcon("Images/DecorFranchissable/idole.png");
		Idole = iIdole.getImage();

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
		return Idole;
	}
}
