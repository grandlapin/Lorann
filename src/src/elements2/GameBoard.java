package src.elements2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GameBoard extends JPanel implements KeyListener{

	String Game[][] = new String [20][12];
	int level = 1;
	private static ArrayList<Rien> Riens;
	private static ArrayList<Horizos> Horizoss;
	private static ArrayList<Verticos> Verticoss;
	private static ArrayList<Pierre> Pierres;
	private static ArrayList<Tresor1> Tresors1;
	private static ArrayList<Tresor2> Tresors2;
	private static ArrayList<Tresor3> Tresors3;
	private static ArrayList<Tombe> Tombes;
	/*private static ArrayList<Boule> Boules;
	private static ArrayList<Idole> Idoles;
	private static ArrayList<MonstreD> MonstresD;
	private static ArrayList<Crane> Cranes;*/
	Rien rien;
	Horizos horizos;
	Verticos verticos;
	Pierre pierre;
	Tresor1 tresor1;
	Tresor2 tresor2;
	Tresor3 tresor3;
	MonstreD monstreD;
	Boule boule;
	Lorann lorann;
	Idole idole;
	Crane crane;
	Tombe tombe;
	FileReader fr;

	public GameBoard(){
		ChangerLevel();
		setFocusable(true);
		addKeyListener(this);

	}

	public void ChangerLevel(){
		try{
			fr = new FileReader("Maps/" + "salle" + "00" + level + ".txt");
			int x =0,y=0,i=0;

			Horizoss = new ArrayList<Horizos>( );
			Verticoss = new ArrayList<Verticos>();
			Pierres = new ArrayList<Pierre> ();
			Tresors1 = new ArrayList<Tresor1>();
			Tresors2 = new ArrayList<Tresor2> ();
			Tresors3 = new ArrayList<Tresor3> ();
			Riens = new ArrayList<Rien> ();
			/*Boules = new ArrayList<Boule>() ;
			Idoles = new ArrayList<Idole>() ;
			MonstresD = new ArrayList<MonstreD>() ;
			Cranes = new ArrayList<Crane>();*/

			while ((i=fr.read()) != -1){
				char strImg = (char) i;

				if (strImg == ' '){
					Game [x][y] = "RIEN";
					rien = new Rien(x*16,y*16);
					Riens.add(rien);
				}
				else if (strImg == '@'){
					Game [x][y] = "LORANN";
					lorann = new Lorann(x*16,y*16);
				}
				else if (strImg == 'Q'){
					Game [x][y] = "BOULE";
					boule = new Boule(x*16,y*16);
					//Boules.add(boule);
				}
				else if (strImg == 'O'){
					Game [x][y] = "PIERRE";
					pierre = new Pierre(x*16,y*16);
					Pierres.add(pierre);
				}
				else if (strImg == '-'){
					Game [x][y] = "HORIZOS";
					horizos = new Horizos(x*16,y*16);
					Horizoss.add(horizos);
				}
				else if (strImg == 'I'){
					Game [x][y] = "VERTICOS";
					verticos = new Verticos(x*16,y*16);
					Verticoss.add(verticos);
				}
				else if (strImg == '1'){
					Game [x][y] = "TRESOR1";
					tresor1 = new Tresor1(x*16,y*16);
					Tresors1.add(tresor1);
				}
				else if (strImg == '2'){
					Game [x][y] = "TRESOR2";
					tresor2 = new Tresor2(x*16,y*16);
					Tresors2.add(tresor2);
				}
				else if (strImg == '3'){
					Game [x][y] = "TRESOR3";
					tresor3 = new Tresor3(x*16,y*16);
					Tresors3.add(tresor3);
				}
				else if (strImg == 'W'){
					Game [x][y] = "IDOLE";
					idole = new Idole(x*16,y*16);
					//Idoles.add(idole);
				}
				else if (strImg == 'D'){
					Game [x][y] = "MONSTRED";
					monstreD = new MonstreD(x*16,y*16);
					//MonstresD.add(monstreD);
				}
				else if (strImg == 'Y'){
					Game [x][y] = "CRANE";
					crane = new Crane(x*16,y*16);
					//Cranes.add(crane);
				}
				else if (strImg == 'Y'){
					Game [x][y] = "TOMBE";
					tombe = new Tombe(x*16,y*16);
					Tombes.add(tombe);
				}
				else if (strImg == '\r' || strImg == '\n'){
					x--;
				}
				if (x == 19){
					y++;
					x = 0;
				}
				else {
					x++;
				}
			}
		}
		catch (Exception ex){}
		repaint();
	}

	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;

		for (int i =0; i< Pierres.size(); i++){
			pierre = (Pierre) Pierres.get(i);
			g2d.drawImage(pierre.getImage(), pierre.getX(), pierre.getY(), null);
		}
		for (int i =0; i< Verticoss.size(); i++){
			verticos = (Verticos) Verticoss.get(i);
			g2d.drawImage(verticos.getImage(), verticos.getX(), verticos.getY(), null);
		}
		for (int i =0; i< Horizoss.size(); i++){
			horizos = (Horizos) Horizoss.get(i);
			g2d.drawImage(horizos.getImage(), horizos.getX(), horizos.getY(), null);
		}
		for (int i =0; i< Tresors1.size(); i++){
			tresor1 = (Tresor1) Tresors1.get(i);
			g2d.drawImage(tresor1.getImage(), tresor1.getX(), tresor1.getY(), null);
		}
		for (int i =0; i< Tresors2.size(); i++){
			tresor2 = (Tresor2) Tresors2.get(i);
			g2d.drawImage(tresor2.getImage(), tresor2.getX(), tresor2.getY(), null);
		}
		for (int i =0; i< Tresors3.size(); i++){
			tresor3 = (Tresor3) Tresors3.get(i);
			g2d.drawImage(tresor3.getImage(), tresor3.getX(), tresor3.getY(), null);
		}
		for (int i =0; i< Riens.size(); i++){
			rien = (Rien) Riens.get(i);
			g2d.drawImage(rien.getImage(), rien.getX(), rien.getY(), null);
		}
		for (int i =0; i< Riens.size(); i++){
			rien = (Rien) Riens.get(i);
			g2d.drawImage(rien.getImage(), rien.getX(), rien.getY(), null);
		}
		/*
		for (int i =0; i< Idoles.size(); i++){
			idole = (Idole) Idoles.get(i);
			g2d.drawImage(idole.getImage(), idole.getX(), idole.getY(), null);
		}
		for (int i =0; i< Boules.size(); i++){
			boule = (Boule) Boules.get(i);
			g2d.drawImage(boule.getImage(), boule.getX(), boule.getY(), null);
		}
		for (int i =0; i< MonstresD.size(); i++){
			monstreD = (MonstreD) MonstresD.get(i);
			g2d.drawImage(monstreD.getImage(), monstreD.getX(), monstreD.getY(), null);
		}
		for (int i =0; i< Cranes.size(); i++){
			crane = (Crane) Cranes.get(i);
			g2d.drawImage(crane.getImage(), crane.getX(), crane.getY(), null);
		}*/
		try{
			g2d.drawImage(lorann.getImage(), lorann.getX(), lorann.getY(), null);
			g2d.drawImage(boule.getImage(), boule.getX(), boule.getY(), null);
			g2d.drawImage(monstreD.getImage(), monstreD.getX(), monstreD.getY(), null);
			g2d.drawImage(idole.getImage(), idole.getX(), idole.getY(), null);
			g2d.drawImage(crane.getImage(), crane.getX(), crane.getY(), null);
		}
		catch (Exception ex){}

	}
	/*
	public void CheckCollision(){
		Rectangle marioRec;
		marioRec = lorann.getBounds();

		for(int i=0;i<Pierres.size();i++){
			pierre = (Pierre) Pierres.get(i);
			Rectangle murRec = pierre.getBounds();

			if(marioRec.intersects(murRec)){
				if (lorann.getDir() == "BAS"){
					lorann.setY(lorann.getY() - 34 );
				}
				else if (lorann.getDir() == "HAUT"){
					lorann.setY(lorann.getY() + 34 );
				}
				else if (lorann.getDir() == "GAUCHE"){
					lorann.setX(lorann.getX() + 34 );
				}
				else if (lorann.getDir() == "DROITE"){
					lorann.setX(lorann.getX() - 34 );
				}
			}
		}
		for (int i = 0; i<Caisses.size();i++){
			caisse = (Caisse) Caisses.get(i);
			Rectangle caisseRec = caisse.getBounds();

			if(marioRec.intersects(caisseRec)){
				if (mario.getDir() == "BAS"){
					if(Game[caisseRec.x /34][(caisseRec.y +34) / 34] != "MUR"){
						caisse.setY(caisse.getY() + 34 );
					}
					else if(Game[caisseRec.x /34][(caisseRec.y +34) / 34] == "MUR"){
						mario.setY(mario.getY() - 34 );
					}
				}
				else if (mario.getDir() == "HAUT"){
					if(Game[caisseRec.x /34][(caisseRec.y - 34) / 34] != "MUR"){
						caisse.setY(caisse.getY() - 34 );
					}
					else if(Game[caisseRec.x /34][(caisseRec.y - 34) / 34] == "MUR"){
						mario.setY(mario.getY() + 34 );
					}
				}
				else if (mario.getDir() == "GAUCHE"){
					if(Game[(caisseRec.x - 34) / 34][caisseRec.y / 34] != "MUR"){
						caisse.setX(caisse.getX() - 34 );
					}
					else if(Game[(caisseRec.x - 34) / 34][caisseRec.y / 34] == "MUR"){
						mario.setX(mario.getX() + 34 );
					}
				}
				else if (mario.getDir() == "DROITE"){
					if(Game[(caisseRec.x + 34) / 34][caisseRec.y / 34] != "MUR"){
						caisse.setX(caisse.getX() + 34 );
					}
					else if (Game[(caisseRec.x + 34) / 34][caisseRec.y / 34] == "MUR"){
						mario.setX(mario.getX() - 34 );
					}
				}
			}
		}
	}*/

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		int Touche = arg0.getKeyCode();

		if (Touche == KeyEvent.VK_DOWN){
			lorann.setDir("BAS");
			lorann.Move();
			//CheckCollision();
		}
		else if (Touche == KeyEvent.VK_UP){
			lorann.setDir("HAUT");
			lorann.Move();
			//CheckCollision();
		}
		else if (Touche == KeyEvent.VK_LEFT){
			lorann.setDir("GAUCHE");
			lorann.Move();
			//CheckCollision();
		}
		else if (Touche == KeyEvent.VK_RIGHT){
			lorann.setDir("DROITE");
			lorann.Move();
			//CheckCollision();
		}
		if (Touche == KeyEvent.VK_DOWN && Touche == KeyEvent.VK_RIGHT){
			lorann.setDir("DIAG BD");
			lorann.Move();
			//CheckCollision();
		}
		else if (Touche == KeyEvent.VK_UP && Touche == KeyEvent.VK_RIGHT){
			lorann.setDir("DIAG HD");
			lorann.Move();
			//CheckCollision();
		}
		else if (Touche == KeyEvent.VK_UP && Touche == KeyEvent.VK_LEFT){
			lorann.setDir("DIAG HG");
			lorann.Move();
			//CheckCollision();
		}
		else if (Touche == KeyEvent.VK_DOWN && Touche == KeyEvent.VK_LEFT){
			lorann.setDir("DIAG BD");
			lorann.Move();
			//CheckCollision();
		}
		else if (Touche == KeyEvent.VK_R){
			ChangerLevel();
		}
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}

