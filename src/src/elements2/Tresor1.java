package elements2;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Tresor1 {
	
	int x,y;
	Image Tresor1;

	public Tresor1(int Startx, int Starty){
		x = Startx;
		y = Starty;

		ImageIcon iTresor1 = new ImageIcon("Images/Tresors/tresor1.png");
		Tresor1 = iTresor1.getImage();

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
		return Tresor1;
	}

}
