package src.elements2;

import java.awt.Image;

import javax.swing.ImageIcon;

public class MonsterD extends Decor{

	Image MonsterD;
	String MonsterDDir = "UP";
/**
 * set monsterD constructor
 * @param Startx = base position of verticos in x 
 * @param Starty = base position of verticos in y 
 */
	public MonsterD(int Startx, int Starty){
		x = Startx;
		y = Starty;

		ImageIcon iMonsterD = new ImageIcon("Images/Monstre/monstreD.png");
		MonsterD = iMonsterD.getImage();
	}
		public void setDir(String newDir) {
			this.MonsterDDir = newDir;
		}

		public String getDir() {
			return MonsterDDir;
		}
		
	public Image getImage(){
		return MonsterD;
	}
	/**
	 * definie effects of move method
	 */
		public void Move(){
		if(MonsterDDir == "DOWN"){
			setY(getY()+16);
		}
		else if (MonsterDDir == "RIGHT"){
			setX(getX()+16);
		}
		else if (MonsterDDir == "LEFT"){
			setX(getX()-16);
		}
		else if (MonsterDDir == "RIGHT"){
			setY(getY()-16);
		}
		else if (MonsterDDir == "DIAG UR"){
			setX(getX()+16);
			setY(getY()-16);
		}
		else if (MonsterDDir == "DIAG UL"){
			setX(getX()-16);
			setY(getY()-16);
		}
		else if (MonsterDDir == "DIAG DL"){
			setY(getY()+16);
			setX(getX()-16);
		}
		else if (MonsterDDir == "DIAG DR"){
			setY(getY()+16);
			setX(getX()+16);
		}
	}
}
