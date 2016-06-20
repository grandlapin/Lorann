package src.elements2;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Lorann extends Decor implements Movable{
	String LorannDir = "DOWN";
	Image Lorann;
	/**
	 * select the path to the pictures
	 */
	ImageIcon iLorannDown = new ImageIcon ("Images/Lorann/lorann_bas.png");
	ImageIcon iLorannRight = new ImageIcon ("Images/Lorann/lorann_droite.png");
	ImageIcon iLorannLeft = new ImageIcon ("Images/Lorann/lorann_gauche.png");
	ImageIcon iLorannUp = new ImageIcon ("Images/Lorann/lorann_haut.png");
	ImageIcon iLorannDownRight = new ImageIcon ("Images/Lorann/lorann_diag_BD.png");
	ImageIcon iLorannDownLeft = new ImageIcon ("Images/Lorann/lorann_diag_BG.png");
	ImageIcon iLorannUpRight = new ImageIcon ("Images/Lorann/lorann_diag_HD.png");
	ImageIcon iLorannUpLeft = new ImageIcon ("Images/Lorann/lorann_diag_HG.png");
	/**
	 * set lorann constructor
	 * @param Startx = base position of verticos in x 
	 * @param Starty = base position of verticos in y 
	 */
	public Lorann(int Startx, int Starty){
		x = Startx;
		y = Starty;
	}


	public String getDir() {
		return LorannDir;
	}



	public void setDir(String newDir) {
		this.LorannDir = newDir;
	}
	/**
	 * definie effects of move method
	 */
	public Image getImage(){
		if(LorannDir == "DOWN"){
			Lorann = iLorannDown.getImage();
		}
		else if (LorannDir == "RIGHT"){
			Lorann = iLorannRight.getImage();
		}
		else if (LorannDir == "LEFT"){
			Lorann = iLorannLeft.getImage();
		}
		else if (LorannDir == "Haut"){
			Lorann = iLorannUp.getImage();
		}		
		else if (LorannDir == "DIAG UR"){
			Lorann = iLorannUpRight.getImage();
		}
		else if (LorannDir == "DIAG UL"){
			Lorann = iLorannUpLeft.getImage();
		}
		else if (LorannDir == "DIAG DL"){
			Lorann = iLorannDownLeft.getImage();
		}
		else if (LorannDir == "DIAG DR"){
			Lorann = iLorannDownRight.getImage();
		}
		return Lorann;
	}

	public void Move(){
		if(LorannDir == "DOWN"){
			this.y += 16;
		}
		else if (LorannDir == "RIGHT"){
			this.x += 16;
		}
		else if (LorannDir == "LEFT"){
			this.x -= 16;
		}
		else if (LorannDir == "UP"){
			this.y -= 16;
		}
		else if (LorannDir == "DIAG UR"){
			this.x += 16;
			this.y -= 16;
		}
		else if (LorannDir == "DIAG UL"){
			this.x -= 16;
			this.y -= 16;
		}
		else if (LorannDir == "DIAG DL"){
			this.y += 16;
			this.x -= 16;
		}
		else if (LorannDir == "DIAG DR"){
			this.y += 16;
			this.x += 16;
		}
	}

}
