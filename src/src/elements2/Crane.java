package elements2;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Crane {

	int x,y;
	Image Crane;

	public Crane(int Startx, int Starty){
		x = Startx;
		y = Starty;

		ImageIcon iCrane = new ImageIcon("Images/DecorNonFranchissable/crane.png");
		Crane = iCrane.getImage();

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
		return Crane;
	}

}
