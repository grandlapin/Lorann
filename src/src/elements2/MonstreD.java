package elements2;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class MonstreD {

	int x,y;
	Image MonstreD;

	public MonstreD(int Startx, int Starty){
		x = Startx;
		y = Starty;

		ImageIcon iMonstreD = new ImageIcon("Images/Monstre/monstreD.png");
		MonstreD = iMonstreD.getImage();

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
		return MonstreD;
	}
}
