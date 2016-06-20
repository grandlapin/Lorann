package src.elements2;

import java.awt.Image;

import javax.swing.ImageIcon;

public class MonstreD extends Decor{

	Image MonstreD;
	String MonstreDDir = "HAUT";
/**
 * set monstreD constructor
 * @param Startx = base position of verticos in x 
 * @param Starty = base position of verticos in y 
 */
	public MonstreD(int Startx, int Starty){
		x = Startx;
		y = Starty;

		ImageIcon iMonstreD = new ImageIcon("Images/Monstre/monstreD.png");
		MonstreD = iMonstreD.getImage();
	}
		public void setDir(String newDir) {
			this.MonstreDDir = newDir;
		}

		public String getDir() {
			return MonstreDDir;
		}
		
	public Image getImage(){
		return MonstreD;
	}
	/**
	 * definie effects of move method
	 */
		public void Move(){
		if(MonstreDDir == "BAS"){
			setY(getY()+16);
		}
		else if (MonstreDDir == "DROITE"){
			setX(getX()+16);
		}
		else if (MonstreDDir == "GAUCHE"){
			setX(getX()-16);
		}
		else if (MonstreDDir == "HAUT"){
			setY(getY()-16);
		}
		else if (MonstreDDir == "DIAG HD"){
			setX(getX()+16);
			setY(getY()-16);
		}
		else if (MonstreDDir == "DIAG HG"){
			setX(getX()-16);
			setY(getY()-16);
		}
		else if (MonstreDDir == "DIAG BG"){
			setY(getY()+16);
			setX(getX()-16);
		}
		else if (MonstreDDir == "DIAG BD"){
			setY(getY()+16);
			setX(getX()+16);
		}
	}
}
