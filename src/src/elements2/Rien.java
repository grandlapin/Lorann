package elements2;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Rien {


	int x,y;
	Image Rien;

	public Rien(int Startx, int Starty){
		x = Startx;
		y = Starty;

		ImageIcon iRien = new ImageIcon("Images/rien.png");
		Rien = iRien.getImage();

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
		return Rien;
	}
}
