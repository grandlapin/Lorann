package elements2;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Pierre {

	int x,y;
	Image Pierre;

	public Pierre(int Startx, int Starty){
		x = Startx;
		y = Starty;

		ImageIcon iPierre = new ImageIcon("Images/DecorNonFranchissable/pierre.png");
		Pierre = iPierre.getImage();

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
		return Pierre;
	}

}
