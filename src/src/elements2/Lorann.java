package src.elements2;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Lorann {

	
	int x,y;
	String LorannDir = "BAS";
	Image Mario;
	ImageIcon iLorannBas = new ImageIcon ("Images/Lorann/lorann_bas.png");
	ImageIcon iLorannDroite = new ImageIcon ("Images/Lorann/lorann_droite.png");
	ImageIcon iLorannGauche = new ImageIcon ("Images/Lorann/lorann_gauche.png");
	ImageIcon iLorannHaut = new ImageIcon ("Images/Lorann/lorann_haut.png");
	ImageIcon iLorannBasDroit = new ImageIcon ("Images/Lorann/lorann_diag_BD.png");
	ImageIcon iLorannBasGauche = new ImageIcon ("Images/Lorann/lorann_diag_BG.png");
	ImageIcon iLorannHautDroit = new ImageIcon ("Images/Lorann/lorann_diag_HD.png");
	ImageIcon iLorannHautGauche = new ImageIcon ("Images/Lorann/lorann_diag_HG.png");
	
	public Lorann(int Startx, int Starty){
		x = Startx;
		y = Starty;
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
	
	public String getDir() {
		return LorannDir;
	}
	
	public void setX(int newX) {
		this.x = newX;
	}
	
	public void setY(int newY) {
		this.y = newY;
	}
	
	public void setDir(String newDir) {
		this.LorannDir = newDir;
	}

	public Image getImage(){
		if(LorannDir == "BAS"){
			Mario = iLorannBas.getImage();
		}
		else if (LorannDir == "DROITE"){
			Mario = iLorannDroite.getImage();
		}
		else if (LorannDir == "GAUCHE"){
			Mario = iLorannGauche.getImage();
		}
		else if (LorannDir == "Haut"){
			Mario = iLorannHaut.getImage();
		}		
		else if (LorannDir == "DIAG HD"){
			Mario = iLorannHautDroit.getImage();
		}
		else if (LorannDir == "DIAG HG"){
			Mario = iLorannGauche.getImage();
		}
		else if (LorannDir == "DIAG BG"){
			Mario = iLorannHaut.getImage();
		}
		else if (LorannDir == "DIAG BD"){
			Mario = iLorannHaut.getImage();
		}
		return Mario;
	}

	public void Move(){
		if(LorannDir == "BAS"){
			this.y += 16;
		}
		else if (LorannDir == "DROITE"){
			this.x += 16;
		}
		else if (LorannDir == "GAUCHE"){
			this.x -= 16;
		}
		else if (LorannDir == "HAUT"){
			this.y -= 16;
		}
		else if (LorannDir == "DIAG HD"){
			this.x += 16;
			this.y -= 16;
		}
		else if (LorannDir == "DIAG HG"){
			this.x -= 16;
			this.y -= 16;
		}
		else if (LorannDir == "DIAG BG"){
			this.y += 16;
			this.x -= 16;
		}
		else if (LorannDir == "DIAG BD"){
			this.y += 16;
			this.x += 16;
		}
	}

}
