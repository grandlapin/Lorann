package src.elements2;


import java.awt.Image;

import javax.swing.ImageIcon;

public class ball extends Decor{

	Image ball;
/**
 * set ball constructor
 * @param Startx = base position of verticos in x 
 * @param Starty = base position of verticos in y 
 */
	public ball(int Startx, int Starty){
		x = Startx;
		y = Starty;

		ImageIcon iball = new ImageIcon("Images/DecorFranchissable/boule.png");
		ball = iball.getImage();
	}
	public Image getImage(){
		return ball;
	}
}
