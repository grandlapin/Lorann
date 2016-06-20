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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * table creation
	 */
	String Game[][] = new String [20][12];
	int level = 1;
	/**
	 * lists of differents elements used to check collision 
	 */
	private static ArrayList<HorizBone> Horizoss;
	private static ArrayList<VerticBone> Verticoss;
	private static ArrayList<Stone> Stones;
	private static ArrayList<Treasure> Tresors1;
	private static ArrayList<ball> balls;
	private static ArrayList<MonstreD> MonstresD;
	private static ArrayList<MonstreA> MonstresA;
	private static ArrayList<MonstreC>MonstresC;
	private static ArrayList<MonstreB>MonstresB;
	private static ArrayList<Skull> Skulls;
	private static ArrayList<Open>Opens;
	private static ArrayList<Projectile>Projectiles;
	/**
	 * objects declarations
	 */
	HorizBone horizBone;
	VerticBone verticBone;
	Stone stone;
	Treasure treasure;
	MonstreD monstreD;
	MonstreA monstreA;
	MonstreB monstreB;
	MonstreC monstreC;
	ball ball;
	Lorann lorann;
	Projectile projectile;
	Skull skull;
	Open open;
	FileReader fr;
	/**
	 * variable declaration
	 */
	int score = 0;
	Font levelFont = new Font("SansSerif", Font.BOLD,30);
	int plusCourt;
	String MonstreDDir;
	String MonstreCDir;
	String MonstreBDir;
	String MonstreADir;
	/*String ProjectileDir = "GAUCHE";*/
	int Vie = 11;
	boolean collisionMonstreA = false;
	boolean collisionMonstreB = false;
	boolean collisionMonstreC = false;
	boolean collisionMonstreD = false;
	int lorannSens;
	boolean touchePriseEnCompte;
	boolean projectilelance = false;
	boolean monstreAPresent = false;
	boolean monstreBPresent = false;
	boolean monstreCPresent = false;
	boolean monstreDPresent = false;
/**
 * GameBoard method
 * 
 * use to initiate the game in the GameFrame
 */
	public GameBoard(){
		this.setBackground(Color.BLACK);
		ChangerLevel();
		setFocusable(true);
		addKeyListener(this);
	}
/**
 * use to inverse the "missile" direction when space bar is typed or when it hit a wall
 * @param sensEntrant for the missile direction
 */
	public void RetourProjectile(String sensEntrant){
		if(sensEntrant == "GAUCHE"){
			projectile.setDir("DROITE");
		}
		else if(sensEntrant == "DROITE"){
			projectile.setDir("GAUCHE");
		}
		else if(sensEntrant == "HAUT"){
			projectile.setDir("BAS");
		}
		else if(sensEntrant == "BAS"){
			projectile.setDir("HAUT");
		}
		else if(sensEntrant == "DIAG HD"){
			projectile.setDir("DIAG BG");
		}
		else if(sensEntrant == "DIAG HG"){
			projectile.setDir("DIAG BD");
		}
		else if(sensEntrant == "DIAG BD"){
			projectile.setDir("DIAG HG");
		}
		else if(sensEntrant == "DIAG BG"){
			projectile.setDir("DIAG HD");
		}
		
	}
	/**
	 * used to pack the move and the collision check
	 */
	public void MouvementProjectile(){

		projectile.Move();	
		CheckCollisionProjectile();
		
	}
	/**
	 * used to pack all the methods used by the monsterA movement
	 */
	public void MouvementMonstreA(){
		monstreA.Move();
		CheckCollisionMonstreA();
		if (collisionMonstreA == true){
			monstreA.Move();
			collisionMonstreA = false;
			CheckCollisionMonstreA();
		}
	}
	/**
	 * used to pack all the methods used by the monsterB movement
	 */
	public void MouvementMonstreB(){
		monstreB.Move();
		CheckCollisionMonstreB();
		if (collisionMonstreB == true){
			monstreB.Move();
			collisionMonstreB = false;
			CheckCollisionMonstreB();
		}
	}
	/**
	 * used to pack all the methods used by the monsterC movement
	 */
	public void MouvementMonstreC(){
		monstreC.Move();
		CheckCollisionMonstreC();
		if (collisionMonstreC == true){
			monstreC.Move();
			collisionMonstreC = false;
			CheckCollisionMonstreC();
		}
	}
	/**
	 * used to pack all the methods used by the monsterD movement
	 */
	public void MouvementMonstreD(){
		monstreD.Move();
		CheckCollisionMonstreD();
		if (collisionMonstreD == true){
			monstreD.Move();
			collisionMonstreD = false;
			CheckCollisionMonstreD();
		}
	}
	/**
	 * method to choose the best direction to kill Lorann for the monsterD
	 */
	public void DirectionMonstreD(){
		plusCourt = Math.abs((lorann.getX()-(monstreD.getX()+16)))+Math.abs((lorann.getY()-(monstreD.getY()+0)));
		MonstreDDir = "DROITE";
		
		if (plusCourt > Math.abs((lorann.getX()-(monstreD.getX()-16)))+Math.abs((lorann.getY()-(monstreD.getY()-16)))){
			plusCourt = Math.abs((lorann.getX()-(monstreD.getX()-16)))+Math.abs((lorann.getY()-(monstreD.getY()-16)));
			MonstreDDir = "DIAG HG";
		}
		else if (plusCourt > Math.abs((lorann.getX()-(monstreD.getX()+16)))+Math.abs((lorann.getY()-(monstreD.getY()-16)))){
			plusCourt = Math.abs((lorann.getX()-(monstreD.getX()+16)))+Math.abs((lorann.getY()-(monstreD.getY()-16)));
			MonstreDDir = "DIAG HD";
		}
		else if (plusCourt > Math.abs((lorann.getX()-(monstreD.getX()-16)))+Math.abs((lorann.getY()-(monstreD.getY()+16)))){
			plusCourt = Math.abs((lorann.getX()-(monstreD.getX()-16)))+Math.abs((lorann.getY()-(monstreD.getY()+16)));
			MonstreDDir = "DIAG BG";
		}
		else if (plusCourt > Math.abs((lorann.getX()-(monstreD.getX()+16)))+Math.abs((lorann.getY()-(monstreD.getY()+16)))){
			plusCourt = Math.abs((lorann.getX()-(monstreD.getX()+16)))+Math.abs((lorann.getY()-(monstreD.getY()+16)));
			MonstreDDir = "DIAG BD";
		}
		else if (plusCourt > Math.abs((lorann.getX()-(monstreD.getX()+0)))+Math.abs((lorann.getY()-(monstreD.getY()+16)))){
			plusCourt = Math.abs((lorann.getX()-(monstreD.getX()+0)))+Math.abs((lorann.getY()-(monstreD.getY()+16)));
			MonstreDDir = "BAS";
		}
		else if (plusCourt > Math.abs((lorann.getX()-(monstreD.getX()-16)))+Math.abs((lorann.getY()-(monstreD.getY()+0)))){
			plusCourt = Math.abs((lorann.getX()-(monstreD.getX()-16)))+Math.abs((lorann.getY()-(monstreD.getY()+0)));
			MonstreDDir = "GAUCHE";
		}
		else if (plusCourt > Math.abs((lorann.getX()-(monstreD.getX()+0)))+Math.abs((lorann.getY()-(monstreD.getY()-16)))){
			plusCourt = Math.abs((lorann.getX()-(monstreD.getX()+0)))+Math.abs((lorann.getY()-(monstreD.getY()-16)));
			MonstreDDir = "HAUT";
		}	
	monstreD.setDir(MonstreDDir);
	}
	/**
	 * method to choose the best direction to kill Lorann for the monsterA
	 */
	public void DirectionMonstreA(){
		plusCourt = Math.abs((lorann.getX()-(monstreA.getX()+16)))+Math.abs((lorann.getY()-(monstreA.getY()+0)));
		MonstreADir = "DROITE";
		
		if (plusCourt > Math.abs((lorann.getX()-(monstreA.getX()+0)))+Math.abs((lorann.getY()-(monstreA.getY()+16)))){
			plusCourt = Math.abs((lorann.getX()-(monstreA.getX()+0)))+Math.abs((lorann.getY()-(monstreA.getY()+16)));
			MonstreADir = "BAS";
		}
		else if (plusCourt > Math.abs((lorann.getX()-(monstreA.getX()-16)))+Math.abs((lorann.getY()-(monstreA.getY()+0)))){
			plusCourt = Math.abs((lorann.getX()-(monstreA.getX()-16)))+Math.abs((lorann.getY()-(monstreA.getY()+0)));
			MonstreADir = "GAUCHE";
		}
		else if (plusCourt > Math.abs((lorann.getX()-(monstreA.getX()+0)))+Math.abs((lorann.getY()-(monstreA.getY()-16)))){
			plusCourt = Math.abs((lorann.getX()-(monstreA.getX()+0)))+Math.abs((lorann.getY()-(monstreA.getY()-16)));
			MonstreADir = "HAUT";
		}	
	monstreA.setDir(MonstreADir);
	}
	/**
	 * method to choose the best direction to kill Lorann for the monsterB
	 */
	public void DirectionMonstreB(){

		
		plusCourt = Math.abs((lorann.getX()-(monstreB.getX()-16)))+Math.abs((lorann.getY()-(monstreB.getY()-16)));
			MonstreBDir = "DIAG HG";
			
		if (plusCourt > Math.abs((lorann.getX()-(monstreB.getX()+16)))+Math.abs((lorann.getY()-(monstreB.getY()-16)))){
			plusCourt = Math.abs((lorann.getX()-(monstreB.getX()+16)))+Math.abs((lorann.getY()-(monstreB.getY()-16)));
			MonstreBDir = "DIAG HD";
		}
		else if (plusCourt > Math.abs((lorann.getX()-(monstreB.getX()-16)))+Math.abs((lorann.getY()-(monstreB.getY()+16)))){
			plusCourt = Math.abs((lorann.getX()-(monstreB.getX()-16)))+Math.abs((lorann.getY()-(monstreB.getY()+16)));
			MonstreBDir = "DIAG BG";
		}
		else if (plusCourt > Math.abs((lorann.getX()-(monstreB.getX()+16)))+Math.abs((lorann.getY()-(monstreB.getY()+16)))){
			plusCourt = Math.abs((lorann.getX()-(monstreB.getX()+16)))+Math.abs((lorann.getY()-(monstreB.getY()+16)));
			MonstreBDir = "DIAG BD";
		}
	monstreB.setDir(MonstreBDir);
	}
	/**
	 * method to choose the best direction to kill Lorann for the monsterC
	 */
	public void DirectionMonstreC(){
		plusCourt = Math.abs((lorann.getX()-(monstreC.getX()+16)))+Math.abs((lorann.getY()-(monstreC.getY()+0)));
		MonstreCDir = "DROITE";
		
		if (plusCourt > Math.abs((lorann.getX()-(monstreC.getX()-16)))+Math.abs((lorann.getY()-(monstreC.getY()-16)))){
			plusCourt = Math.abs((lorann.getX()-(monstreC.getX()-16)))+Math.abs((lorann.getY()-(monstreC.getY()-16)));
			MonstreCDir = "DIAG HG";
		}
		else if (plusCourt > Math.abs((lorann.getX()-(monstreC.getX()+16)))+Math.abs((lorann.getY()-(monstreC.getY()-16)))){
			plusCourt = Math.abs((lorann.getX()-(monstreC.getX()+16)))+Math.abs((lorann.getY()-(monstreC.getY()-16)));
			MonstreCDir = "DIAG HD";
		}
		else if (plusCourt > Math.abs((lorann.getX()-(monstreC.getX()-16)))+Math.abs((lorann.getY()-(monstreC.getY()+16)))){
			plusCourt = Math.abs((lorann.getX()-(monstreC.getX()-16)))+Math.abs((lorann.getY()-(monstreC.getY()+16)));
			MonstreCDir = "DIAG BG";
		}
		else if (plusCourt > Math.abs((lorann.getX()-(monstreC.getX()+16)))+Math.abs((lorann.getY()-(monstreC.getY()+16)))){
			plusCourt = Math.abs((lorann.getX()-(monstreC.getX()+16)))+Math.abs((lorann.getY()-(monstreC.getY()+16)));
			MonstreCDir = "DIAG BD";
		}
		else if (plusCourt > Math.abs((lorann.getX()-(monstreC.getX()+0)))+Math.abs((lorann.getY()-(monstreC.getY()+16)))){
			plusCourt = Math.abs((lorann.getX()-(monstreC.getX()+0)))+Math.abs((lorann.getY()-(monstreC.getY()+16)));
			MonstreCDir = "BAS";
		}
		else if (plusCourt > Math.abs((lorann.getX()-(monstreC.getX()-16)))+Math.abs((lorann.getY()-(monstreC.getY()+0)))){
			plusCourt = Math.abs((lorann.getX()-(monstreC.getX()-16)))+Math.abs((lorann.getY()-(monstreC.getY()+0)));
			MonstreCDir = "GAUCHE";
		}
		else if (plusCourt > Math.abs((lorann.getX()-(monstreC.getX()+0)))+Math.abs((lorann.getY()-(monstreC.getY()-16)))){
			plusCourt = Math.abs((lorann.getX()-(monstreC.getX()+0)))+Math.abs((lorann.getY()-(monstreC.getY()-16)));
			MonstreCDir = "HAUT";
		}	
	monstreC.setDir(MonstreCDir);
	}
	/**
	 * method th generate the map from a txt file and group elements by arraylists
	 */
	public void ChangerLevel(){
		monstreAPresent = false;
		monstreBPresent = false;
		monstreCPresent = false;
		monstreDPresent = false;
		try{
			fr = new FileReader("Maps/" + "salle" + "00" + level + ".txt");
			int x =0,y=0,i=0;

			Horizoss = new ArrayList<HorizBone>( );
			Verticoss = new ArrayList<VerticBone>();
			Stones = new ArrayList<Stone> ();
			Tresors1 = new ArrayList<Treasure>();
			balls = new ArrayList<ball>() ;
			MonstresA = new ArrayList<MonstreA>() ;
			MonstresB = new ArrayList<MonstreB>() ;
			MonstresC = new ArrayList<MonstreC>() ;
			MonstresD = new ArrayList<MonstreD>() ;
			Skulls = new ArrayList<Skull>();
			Opens = new ArrayList<Open>();
			Projectiles = new ArrayList<Projectile>();

			while ((i=fr.read()) != -1){
				char strImg = (char) i;

				if (strImg == '@'){
					Game [x][y] = "LORANN";
					lorann = new Lorann(x*16,y*16);
				}
				else if (strImg == 'Q'){
					Game [x][y] = "BOULE";
					ball = new ball(x*16,y*16);
					balls.add(ball);
				}
				else if (strImg == 'O'){
					Game [x][y] = "STONE";
					stone = new Stone(x*16,y*16);
					Stones.add(stone);
				}
				else if (strImg == '-'){
					Game [x][y] = "HORIZOS";
					horizBone = new HorizBone(x*16,y*16);
					Horizoss.add(horizBone);
				}
				else if (strImg == 'I'){
					Game [x][y] = "VERTICOS";
					verticBone = new VerticBone(x*16,y*16);
					Verticoss.add(verticBone);
				}
				else if (strImg == '1'){
					Game [x][y] = "TRESOR1";
					treasure = new Treasure(x*16,y*16);
					Tresors1.add(treasure);
				}
				else if (strImg == 'A'){
					Game [x][y] = "MONSTREA";
					monstreA = new MonstreA(x*16,y*16);
					MonstresA.add(monstreA);
					monstreAPresent = true;
				}
				else if (strImg == 'B'){
					Game [x][y] = "MONSTREB";
					monstreB = new MonstreB(x*16,y*16);
					MonstresB.add(monstreB);
					monstreBPresent = true;
				}
				else if (strImg == 'C'){
					Game [x][y] = "MONSTREC";
					monstreC = new MonstreC(x*16,y*16);
					MonstresC.add(monstreC);
					monstreCPresent = true;
				}
				else if (strImg == 'D'){
					Game [x][y] = "MONSTRED";
					monstreD = new MonstreD(x*16,y*16);
					MonstresD.add(monstreD);
					monstreDPresent = true;
				}
				else if (strImg == 'Y'){
					Game [x][y] = "CRANE";
					skull = new Skull(x*16,y*16);
					Skulls.add(skull);
					
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
		Opens = new ArrayList<Open>() ;
		open = new Open(lorann.getX()*16,lorann.getY()*16);
}
	/**
	 * method to change the level when the ball is taken
	 */
	public void ChangerLevel2(){
		try{
			fr = new FileReader("Maps/" + "salle" + "00" + level + ".txt");
			int x =0,y=0,i=0;

			Opens = new ArrayList<Open>();

			while ((i=fr.read()) != -1){
				char strImg = (char) i;

				if (strImg == 'Y'){
					Game [x][y] = "OUVERTE";
					open = new Open(x*16,y*16);
					Opens.add(open);
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
/**
 * method to paint graphics from the table
 */
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;

		for (int i =0; i< Stones.size(); i++){
			stone = (Stone) Stones.get(i);
			g2d.drawImage(stone.getImage(), stone.getX(), stone.getY(), null);
		}
		for (int i =0; i< Verticoss.size(); i++){
			verticBone = (VerticBone) Verticoss.get(i);
			g2d.drawImage(verticBone.getImage(), verticBone.getX(), verticBone.getY(), null);
		}
		for (int i =0; i< Horizoss.size(); i++){
			horizBone = (HorizBone) Horizoss.get(i);
			g2d.drawImage(horizBone.getImage(), horizBone.getX(), horizBone.getY(), null);
		}
		for (int i =0; i< Tresors1.size(); i++){
			treasure = (Treasure) Tresors1.get(i);
			g2d.drawImage(treasure.getImage(), treasure.getX(), treasure.getY(), null);
		}
		for (int i =0; i< balls.size(); i++){
			ball = (ball) balls.get(i);
			g2d.drawImage(ball.getImage(), ball.getX(), ball.getY(), null);
		}
		for (int i =0; i< MonstresA.size(); i++){
			monstreA = (MonstreA) MonstresA.get(i);
			g2d.drawImage(monstreA.getImage(), monstreA.getX(), monstreA.getY(), null);
		}
		for (int i =0; i< MonstresB.size(); i++){
			monstreB = (MonstreB) MonstresB.get(i);
			g2d.drawImage(monstreB.getImage(), monstreB.getX(), monstreB.getY(), null);
		}
		for (int i =0; i< MonstresC.size(); i++){
			monstreC = (MonstreC) MonstresC.get(i);
			g2d.drawImage(monstreC.getImage(), monstreC.getX(), monstreC.getY(), null);
		}
		for (int i =0; i< MonstresD.size(); i++){
			monstreD = (MonstreD) MonstresD.get(i);
			g2d.drawImage(monstreD.getImage(), monstreD.getX(), monstreD.getY(), null);
		}
		for (int i =0; i< Skulls.size(); i++){
			skull = (Skull) Skulls.get(i);
			g2d.drawImage(skull.getImage(), skull.getX(), skull.getY(), null);
		}
		for (int i =0; i< Opens.size(); i++){
			open = (Open) Opens.get(i);
			g2d.drawImage(open.getImage(), open.getX(), open.getY(), null);
		}
		for (int i =0; i< Projectiles.size(); i++){
			projectile = (Projectile) Projectiles.get(i);
			g2d.drawImage(projectile.getImage(), projectile.getX(), projectile.getY(), null);
		}
		g.setColor(Color.RED);
		g.setFont(levelFont);
		g.drawString("Score : " + score + " Vie : " + Vie + " <3", 100, 250);
		
		try{
			g2d.drawImage(lorann.getImage(), lorann.getX(), lorann.getY(), null);
			/*g2d.drawImage(projectile.getImage(), projectile.getX(), projectile.getY(), null);*/
		}
		catch (Exception ex){}

	}
	/**
	 * method to check collision from "missile" to elements
	 */
	public void CheckCollisionProjectile(){

		Rectangle projectileRec;
		projectileRec = projectile.getBounds();

		for(int i=0;i<balls.size();i++){
			ball = (ball) balls.get(i);
			Rectangle murRec = ball.getBounds();

			if(projectileRec.intersects(murRec)){
				if (projectile.getDir() == "BAS"){
					projectile.setY(projectile.getY() - 16 );
					RetourProjectile(projectile.getDir());
				}
				else if (projectile.getDir() == "HAUT"){
					projectile.setY(projectile.getY() + 16 );
					RetourProjectile(projectile.getDir());

				}
				else if (projectile.getDir() == "GAUCHE"){
					projectile.setX(projectile.getX() + 16 );
					RetourProjectile(projectile.getDir());
				}
				else if (projectile.getDir() == "DROITE"){
					projectile.setX(projectile.getX() - 16 );
					RetourProjectile(projectile.getDir());

				}
				else if (projectile.getDir() == "DIAG BG"){
					projectile.setX(projectile.getX() + 16 );
					projectile.setY(projectile.getY() - 16 );
					RetourProjectile(projectile.getDir());

				}
				else if (projectile.getDir() == "DIAG BD"){
					projectile.setX(projectile.getX() - 16 );
					projectile.setY(projectile.getY() - 16 );
					RetourProjectile(projectile.getDir());

				}
				else if (projectile.getDir() == "DIAG HG"){
					projectile.setX(projectile.getX() + 16 );
					projectile.setY(projectile.getY() + 16 );
					RetourProjectile(projectile.getDir());

				}
				else if (projectile.getDir() == "DIAG HD"){
					projectile.setX(projectile.getX() - 16 );
					projectile.setY(projectile.getY() + 16 );
					RetourProjectile(projectile.getDir());

				}
			}
		}
		for(int i=0;i<Stones.size();i++){
			stone = (Stone) Stones.get(i);
			Rectangle murRec = stone.getBounds();

			if(projectileRec.intersects(murRec)){
				if (projectile.getDir() == "BAS"){
					projectile.setY(projectile.getY() - 16 );
					RetourProjectile(projectile.getDir());
				}
				else if (projectile.getDir() == "HAUT"){
					projectile.setY(projectile.getY() + 16 );
					RetourProjectile(projectile.getDir());

				}
				else if (projectile.getDir() == "GAUCHE"){
					projectile.setX(projectile.getX() + 16 );
					RetourProjectile(projectile.getDir());
				}
				else if (projectile.getDir() == "DROITE"){
					projectile.setX(projectile.getX() - 16 );
					RetourProjectile(projectile.getDir());

				}
				else if (projectile.getDir() == "DIAG BG"){
					projectile.setX(projectile.getX() + 16 );
					projectile.setY(projectile.getY() - 16 );
					RetourProjectile(projectile.getDir());

				}
				else if (projectile.getDir() == "DIAG BD"){
					projectile.setX(projectile.getX() - 16 );
					projectile.setY(projectile.getY() - 16 );
					RetourProjectile(projectile.getDir());

				}
				else if (projectile.getDir() == "DIAG HG"){
					projectile.setX(projectile.getX() + 16 );
					projectile.setY(projectile.getY() + 16 );
					RetourProjectile(projectile.getDir());

				}
				else if (projectile.getDir() == "DIAG HD"){
					projectile.setX(projectile.getX() - 16 );
					projectile.setY(projectile.getY() + 16 );
					RetourProjectile(projectile.getDir());

				}
			}
		}
		for(int i=0;i<Horizoss.size();i++){
			horizBone = (HorizBone) Horizoss.get(i);
			Rectangle murRec = horizBone.getBounds();

			if(projectileRec.intersects(murRec)){
				if (projectile.getDir() == "BAS"){
					projectile.setY(projectile.getY() - 16 );
					RetourProjectile(projectile.getDir());
				}
				else if (projectile.getDir() == "HAUT"){
					projectile.setY(projectile.getY() + 16 );
					RetourProjectile(projectile.getDir());

				}
				else if (projectile.getDir() == "GAUCHE"){
					projectile.setX(projectile.getX() + 16 );
					RetourProjectile(projectile.getDir());
				}
				else if (projectile.getDir() == "DROITE"){
					projectile.setX(projectile.getX() - 16 );
					RetourProjectile(projectile.getDir());

				}
				else if (projectile.getDir() == "DIAG BG"){
					projectile.setX(projectile.getX() + 16 );
					projectile.setY(projectile.getY() - 16 );
					RetourProjectile(projectile.getDir());

				}
				else if (projectile.getDir() == "DIAG BD"){
					projectile.setX(projectile.getX() - 16 );
					projectile.setY(projectile.getY() - 16 );
					RetourProjectile(projectile.getDir());

				}
				else if (projectile.getDir() == "DIAG HG"){
					projectile.setX(projectile.getX() + 16 );
					projectile.setY(projectile.getY() + 16 );
					RetourProjectile(projectile.getDir());

				}
				else if (projectile.getDir() == "DIAG HD"){
					projectile.setX(projectile.getX() - 16 );
					projectile.setY(projectile.getY() + 16 );
					RetourProjectile(projectile.getDir());

				}
			}
		}
		for(int i=0;i<Verticoss.size();i++){
			verticBone = (VerticBone) Verticoss.get(i);
			Rectangle murRec = verticBone.getBounds();

			if(projectileRec.intersects(murRec)){
				if (projectile.getDir() == "BAS"){
					projectile.setY(projectile.getY() - 16 );
					RetourProjectile(projectile.getDir());
				}
				else if (projectile.getDir() == "HAUT"){
					projectile.setY(projectile.getY() + 16 );
					RetourProjectile(projectile.getDir());

				}
				else if (projectile.getDir() == "GAUCHE"){
					projectile.setX(projectile.getX() + 16 );
					RetourProjectile(projectile.getDir());
				}
				else if (projectile.getDir() == "DROITE"){
					projectile.setX(projectile.getX() - 16 );
					RetourProjectile(projectile.getDir());

				}
				else if (projectile.getDir() == "DIAG BG"){
					projectile.setX(projectile.getX() + 16 );
					projectile.setY(projectile.getY() - 16 );
					RetourProjectile(projectile.getDir());

				}
				else if (projectile.getDir() == "DIAG BD"){
					projectile.setX(projectile.getX() - 16 );
					projectile.setY(projectile.getY() - 16 );
					RetourProjectile(projectile.getDir());

				}
				else if (projectile.getDir() == "DIAG HG"){
					projectile.setX(projectile.getX() + 16 );
					projectile.setY(projectile.getY() + 16 );
					RetourProjectile(projectile.getDir());

				}
				else if (projectile.getDir() == "DIAG HD"){
					projectile.setX(projectile.getX() - 16 );
					projectile.setY(projectile.getY() + 16 );
					RetourProjectile(projectile.getDir());

				}
			}
		}
		for(int i=0;i<Skulls.size();i++){
			skull = (Skull) Skulls.get(i);
			Rectangle murRec = skull.getBounds();

			if(projectileRec.intersects(murRec)){
				if (projectile.getDir() == "BAS"){
					projectile.setY(projectile.getY() - 16 );
					RetourProjectile(projectile.getDir());
				}
				else if (projectile.getDir() == "HAUT"){
					projectile.setY(projectile.getY() + 16 );
					RetourProjectile(projectile.getDir());

				}
				else if (projectile.getDir() == "GAUCHE"){
					projectile.setX(projectile.getX() + 16 );
					RetourProjectile(projectile.getDir());
				}
				else if (projectile.getDir() == "DROITE"){
					projectile.setX(projectile.getX() - 16 );
					RetourProjectile(projectile.getDir());

				}
				else if (projectile.getDir() == "DIAG BG"){
					projectile.setX(projectile.getX() + 16 );
					projectile.setY(projectile.getY() - 16 );
					RetourProjectile(projectile.getDir());

				}
				else if (projectile.getDir() == "DIAG BD"){
					projectile.setX(projectile.getX() - 16 );
					projectile.setY(projectile.getY() - 16 );
					RetourProjectile(projectile.getDir());

				}
				else if (projectile.getDir() == "DIAG HG"){
					projectile.setX(projectile.getX() + 16 );
					projectile.setY(projectile.getY() + 16 );
					RetourProjectile(projectile.getDir());

				}
				else if (projectile.getDir() == "DIAG HD"){
					projectile.setX(projectile.getX() - 16 );
					projectile.setY(projectile.getY() + 16 );
					RetourProjectile(projectile.getDir());

				}
			}
		}
			Rectangle murRec = lorann.getBounds();

			if(projectileRec.intersects(murRec)){
				if (projectile.getDir() == "BAS"){
					Projectiles.remove(0);
					projectilelance = false;
				}
				else if (projectile.getDir() == "HAUT"){
					Projectiles.remove(0);
					projectilelance = false;

				}
				else if (projectile.getDir() == "GAUCHE"){
					Projectiles.remove(0);
					projectilelance = false;
				}
				else if (projectile.getDir() == "DROITE"){
					Projectiles.remove(0);
					projectilelance = false;

				}
				else if (projectile.getDir() == "DIAG BG"){
					Projectiles.remove(0);
					projectilelance = false;

				}
				else if (projectile.getDir() == "DIAG BD"){
					Projectiles.remove(0);
					projectilelance = false;

				}
				else if (projectile.getDir() == "DIAG HG"){
					Projectiles.remove(0);
					projectilelance = false;

				}
				else if (projectile.getDir() == "DIAG HD"){
					Projectiles.remove(0);
					projectilelance = false;

				}
			}
			
	
			for(int i=0;i<MonstresD.size();i++){
				monstreD = (MonstreD) MonstresD.get(i);
				Rectangle murRec1 = monstreD.getBounds();

				if(projectileRec.intersects(murRec1)){
					if (projectile.getDir() == "BAS"){
						MonstresD.remove(i);
						projectilelance = false;
						Projectiles.remove(0);
						monstreD.setX(20);
					}
					else if (projectile.getDir() == "HAUT"){
						MonstresD.remove(i);
						projectilelance = false;
						Projectiles.remove(0);
						monstreD.setX(20);

					}
					else if (projectile.getDir() == "GAUCHE"){
						MonstresD.remove(i);
						projectilelance = false;
						Projectiles.remove(0);
						monstreD.setX(20);
					}
					else if (projectile.getDir() == "DROITE"){
						MonstresD.remove(i);
						projectilelance = false;
						Projectiles.remove(0);
						monstreD.setX(20);

					}
					else if (projectile.getDir() == "DIAG BG"){
						MonstresD.remove(i);
						projectilelance = false;
						Projectiles.remove(0);
						monstreD.setX(20);

					}
					else if (projectile.getDir() == "DIAG BD"){
						MonstresD.remove(i);
						projectilelance = false;
						Projectiles.remove(0);
						monstreD.setX(20);

					}
					else if (projectile.getDir() == "DIAG HG"){
						MonstresD.remove(i);
						projectilelance = false;
						Projectiles.remove(0);
						monstreD.setX(20);

					}
					else if (projectile.getDir() == "DIAG HD"){
						MonstresD.remove(i);
						projectilelance = false;
						Projectiles.remove(0);
						monstreD.setX(20);

					}
				}
			}
				
		
	for(int i=0;i<MonstresA.size();i++){
		monstreA = (MonstreA) MonstresA.get(i);
		Rectangle murRec1 = monstreA.getBounds();

		if(projectileRec.intersects(murRec1)){
			if (projectile.getDir() == "BAS"){
				MonstresA.remove(i);
				projectilelance = false;
				Projectiles.remove(0);
				monstreA.setX(20);
			}
			else if (projectile.getDir() == "HAUT"){
				MonstresA.remove(i);
				projectilelance = false;
				Projectiles.remove(0);
				monstreA.setX(20);

			}
			else if (projectile.getDir() == "GAUCHE"){
				MonstresA.remove(i);
				projectilelance = false;
				Projectiles.remove(0);
				monstreA.setX(20);
			}
			else if (projectile.getDir() == "DROITE"){
				MonstresA.remove(i);
				projectilelance = false;
				Projectiles.remove(0);
				monstreA.setX(20);

			}
			else if (projectile.getDir() == "DIAG BG"){
				MonstresA.remove(i);
				projectilelance = false;
				Projectiles.remove(0);
				monstreA.setX(20);

			}
			else if (projectile.getDir() == "DIAG BD"){
				MonstresA.remove(i);
				projectilelance = false;
				Projectiles.remove(0);
				monstreA.setX(20);

			}
			else if (projectile.getDir() == "DIAG HG"){
				MonstresA.remove(i);
				projectilelance = false;
				Projectiles.remove(0);
				monstreA.setX(20);

			}
			else if (projectile.getDir() == "DIAG HD"){
				MonstresA.remove(i);
				projectilelance = false;
				Projectiles.remove(0);
				monstreA.setX(20);

			}
		}
	}
		

	for(int i=0;i<MonstresB.size();i++){
		monstreB = (MonstreB) MonstresB.get(i);
		Rectangle murRec1 = monstreB.getBounds();

		if(projectileRec.intersects(murRec1)){
			if (projectile.getDir() == "BAS"){
				MonstresB.remove(i);
				projectilelance = false;
				Projectiles.remove(0);
				monstreB.setX(20);
			}
			else if (projectile.getDir() == "HAUT"){
				MonstresB.remove(i);
				projectilelance = false;
				Projectiles.remove(0);
				monstreB.setX(20);

			}
			else if (projectile.getDir() == "GAUCHE"){
				MonstresB.remove(i);
				projectilelance = false;
				Projectiles.remove(0);
				monstreB.setX(20);
			}
			else if (projectile.getDir() == "DROITE"){
				MonstresB.remove(i);
				projectilelance = false;
				Projectiles.remove(0);
				monstreB.setX(20);

			}
			else if (projectile.getDir() == "DIAG BG"){
				MonstresB.remove(i);
				projectilelance = false;
				Projectiles.remove(0);
				monstreB.setX(20);

			}
			else if (projectile.getDir() == "DIAG BD"){
				MonstresB.remove(i);
				projectilelance = false;
				Projectiles.remove(0);
				monstreB.setX(20);

			}
			else if (projectile.getDir() == "DIAG HG"){
				MonstresB.remove(i);
				projectilelance = false;
				Projectiles.remove(0);
				monstreB.setX(20);

			}
			else if (projectile.getDir() == "DIAG HD"){
				MonstresB.remove(i);
				projectilelance = false;
				Projectiles.remove(0);
				monstreB.setX(20);

			}
		}
	}
	for(int i=0;i<MonstresC.size();i++){
		monstreC = (MonstreC) MonstresC.get(i);
		Rectangle murRec1 = monstreC.getBounds();

		if(projectileRec.intersects(murRec1)){
			if (projectile.getDir() == "BAS"){
				MonstresC.remove(i);
				projectilelance = false;
				Projectiles.remove(0);
				monstreC.setX(20);
			}
			else if (projectile.getDir() == "HAUT"){
				MonstresC.remove(i);
				projectilelance = false;
				Projectiles.remove(0);
				monstreC.setX(20);

			}
			else if (projectile.getDir() == "GAUCHE"){
				MonstresC.remove(i);
				projectilelance = false;
				Projectiles.remove(0);
				monstreC.setX(20);
			}
			else if (projectile.getDir() == "DROITE"){
				MonstresC.remove(i);
				projectilelance = false;
				Projectiles.remove(0);
				monstreC.setX(20);

			}
			else if (projectile.getDir() == "DIAG BG"){
				MonstresC.remove(i);
				projectilelance = false;
				Projectiles.remove(0);
				monstreC.setX(20);

			}
			else if (projectile.getDir() == "DIAG BD"){
				MonstresC.remove(i);
				projectilelance = false;
				Projectiles.remove(0);
				monstreC.setX(20);

			}
			else if (projectile.getDir() == "DIAG HG"){
				MonstresC.remove(i);
				projectilelance = false;
				Projectiles.remove(0);
				monstreC.setX(20);

			}
			else if (projectile.getDir() == "DIAG HD"){
				MonstresC.remove(i);
				projectilelance = false;
				Projectiles.remove(0);
				monstreC.setX(20);

			}
		}
	}
		
}
	/**
	 * method to check collision from monstreD to elements
	 */
	public void CheckCollisionMonstreD(){

		Rectangle monstreDRec;
		monstreDRec = monstreD.getBounds();

		for(int i=0;i<Stones.size();i++){
			stone = (Stone) Stones.get(i);
			Rectangle murRec = stone.getBounds();

			if(monstreDRec.intersects(murRec)){
				if (monstreD.getDir() == "BAS"){
					monstreD.setY(monstreD.getY() - 16 );
					DirectionMonstreD();
					collisionMonstreD = true;
				}
				else if (monstreD.getDir() == "HAUT"){
					monstreD.setY(monstreD.getY() + 16 );
					DirectionMonstreD();
					collisionMonstreD = true;
				}
				else if (monstreD.getDir() == "GAUCHE"){
					monstreD.setX(monstreD.getX() + 16 );
					DirectionMonstreD();
					collisionMonstreD = true;
				}
				else if (monstreD.getDir() == "DROITE"){
					monstreD.setX(monstreD.getX() - 16 );
					DirectionMonstreD();
					collisionMonstreD = true;
				}
				else if (monstreD.getDir() == "DIAG BG"){
					monstreD.setX(monstreD.getX() + 16 );
					monstreD.setY(monstreD.getY() - 16 );
					DirectionMonstreD();
					collisionMonstreD = true;
				}
				else if (monstreD.getDir() == "DIAG BD"){
					monstreD.setX(monstreD.getX() - 16 );
					monstreD.setY(monstreD.getY() - 16 );
					DirectionMonstreD();
					collisionMonstreD = true;
				}
				else if (monstreD.getDir() == "DIAG HG"){
					monstreD.setX(monstreD.getX() + 16 );
					monstreD.setY(monstreD.getY() + 16 );
					DirectionMonstreD();
					collisionMonstreD = true;
				}
				else if (monstreD.getDir() == "DIAG HD"){
					monstreD.setX(monstreD.getX() - 16 );
					monstreD.setY(monstreD.getY() + 16 );
					DirectionMonstreD();
					collisionMonstreD = true;
				}
			}
		}
		for(int i=0;i<Horizoss.size();i++){
			horizBone = (HorizBone) Horizoss.get(i);
			Rectangle murRec = horizBone.getBounds();

			if(monstreDRec.intersects(murRec)){
				if (monstreD.getDir() == "BAS"){
					monstreD.setY(monstreD.getY() - 16 );
					DirectionMonstreD();
					collisionMonstreD = true;
				}
				else if (monstreD.getDir() == "HAUT"){
					monstreD.setY(monstreD.getY() + 16 );
					DirectionMonstreD();
					collisionMonstreD = true;
				}
				else if (monstreD.getDir() == "GAUCHE"){
					monstreD.setX(monstreD.getX() + 16 );
					DirectionMonstreD();
					collisionMonstreD = true;
				}
				else if (monstreD.getDir() == "DROITE"){
					monstreD.setX(monstreD.getX() - 16 );
					DirectionMonstreD();
					collisionMonstreD = true;
				}
				else if (monstreD.getDir() == "DIAG BG"){
					monstreD.setX(monstreD.getX() + 16 );
					monstreD.setY(monstreD.getY() - 16 );
					DirectionMonstreD();
					collisionMonstreD = true;
				}
				else if (monstreD.getDir() == "DIAG BD"){
					monstreD.setX(monstreD.getX() - 16 );
					monstreD.setY(monstreD.getY() - 16 );
					DirectionMonstreD();
					collisionMonstreD = true;
				}
				else if (monstreD.getDir() == "DIAG HG"){
					monstreD.setX(monstreD.getX() + 16 );
					monstreD.setY(monstreD.getY() + 16 );
					DirectionMonstreD();
					collisionMonstreD = true;
				}
				else if (monstreD.getDir() == "DIAG HD"){
					monstreD.setX(monstreD.getX() - 16 );
					monstreD.setY(monstreD.getY() + 16 );
					DirectionMonstreD();
					collisionMonstreD = true;
				}
			}
		}
		for(int i=0;i<balls.size();i++){
			ball = (ball) balls.get(i);
			Rectangle murRec = ball.getBounds();

			if(monstreDRec.intersects(murRec)){
				if (monstreD.getDir() == "BAS"){
					monstreD.setY(monstreD.getY() - 16 );
					DirectionMonstreD();
					collisionMonstreD = true;
				}
				else if (monstreD.getDir() == "HAUT"){
					monstreD.setY(monstreD.getY() + 16 );
					DirectionMonstreD();
					collisionMonstreD = true;
				}
				else if (monstreD.getDir() == "GAUCHE"){
					monstreD.setX(monstreD.getX() + 16 );
					DirectionMonstreD();
					collisionMonstreD = true;
				}
				else if (monstreD.getDir() == "DROITE"){
					monstreD.setX(monstreD.getX() - 16 );
					DirectionMonstreD();
					collisionMonstreD = true;
				}
				else if (monstreD.getDir() == "DIAG BG"){
					monstreD.setX(monstreD.getX() + 16 );
					monstreD.setY(monstreD.getY() - 16 );
					DirectionMonstreD();
					collisionMonstreD = true;
				}
				else if (monstreD.getDir() == "DIAG BD"){
					monstreD.setX(monstreD.getX() - 16 );
					monstreD.setY(monstreD.getY() - 16 );
					DirectionMonstreD();
					collisionMonstreD = true;
				}
				else if (monstreD.getDir() == "DIAG HG"){
					monstreD.setX(monstreD.getX() + 16 );
					monstreD.setY(monstreD.getY() + 16 );
					DirectionMonstreD();
					collisionMonstreD = true;
				}
				else if (monstreD.getDir() == "DIAG HD"){
					monstreD.setX(monstreD.getX() - 16 );
					monstreD.setY(monstreD.getY() + 16 );
					DirectionMonstreD();
					collisionMonstreD = true;
				}
			}
		}
		for(int i=0;i<Verticoss.size();i++){
			verticBone = (VerticBone) Verticoss.get(i);
			Rectangle murRec = verticBone.getBounds();

			if(monstreDRec.intersects(murRec)){
				if (monstreD.getDir() == "BAS"){
					monstreD.setY(monstreD.getY() - 16 );
					DirectionMonstreD();
					collisionMonstreD = true;
				}
				else if (monstreD.getDir() == "HAUT"){
					monstreD.setY(monstreD.getY() + 16 );
					DirectionMonstreD();
					collisionMonstreD = true;
				}
				else if (monstreD.getDir() == "GAUCHE"){
					monstreD.setX(monstreD.getX() + 16 );
					DirectionMonstreD();
					collisionMonstreD = true;
				}
				else if (monstreD.getDir() == "DROITE"){
					monstreD.setX(monstreD.getX() - 16 );
					DirectionMonstreD();
					collisionMonstreD = true;
				}
				else if (monstreD.getDir() == "DIAG BG"){
					monstreD.setX(monstreD.getX() + 16 );
					monstreD.setY(monstreD.getY() - 16 );
					DirectionMonstreD();
					collisionMonstreD = true;
				}
				else if (monstreD.getDir() == "DIAG BD"){
					monstreD.setX(monstreD.getX() - 16 );
					monstreD.setY(monstreD.getY() - 16 );
					DirectionMonstreD();
					collisionMonstreD = true;
				}
				else if (monstreD.getDir() == "DIAG HG"){
					monstreD.setX(monstreD.getX() + 16 );
					monstreD.setY(monstreD.getY() + 16 );
					DirectionMonstreD();
					collisionMonstreD = true;
				}
				else if (monstreD.getDir() == "DIAG HD"){
					monstreD.setX(monstreD.getX() - 16 );
					monstreD.setY(monstreD.getY() + 16 );
					DirectionMonstreD();
					collisionMonstreD = true;
				}
			}
		}
		for(int i=0;i<Skulls.size();i++){
			skull = (Skull) Skulls.get(i);
			Rectangle murRec = skull.getBounds();

			if(monstreDRec.intersects(murRec)){
				if (monstreD.getDir() == "BAS"){
					monstreD.setY(monstreD.getY() - 16 );
					DirectionMonstreD();
					collisionMonstreD = true;
				}
				else if (monstreD.getDir() == "HAUT"){
					monstreD.setY(monstreD.getY() + 16 );
					DirectionMonstreD();
					collisionMonstreD = true;
				}
				else if (monstreD.getDir() == "GAUCHE"){
					monstreD.setX(monstreD.getX() + 16 );
					DirectionMonstreD();
					collisionMonstreD = true;
				}
				else if (monstreD.getDir() == "DROITE"){
					monstreD.setX(monstreD.getX() - 16 );
					DirectionMonstreD();
					collisionMonstreD = true;
				}
				else if (monstreD.getDir() == "DIAG BG"){
					monstreD.setX(monstreD.getX() + 16 );
					monstreD.setY(monstreD.getY() - 16 );
					DirectionMonstreD();
					collisionMonstreD = true;
				}
				else if (monstreD.getDir() == "DIAG BD"){
					monstreD.setX(monstreD.getX() - 16 );
					monstreD.setY(monstreD.getY() - 16 );
					DirectionMonstreD();
					collisionMonstreD = true;
				}
				else if (monstreD.getDir() == "DIAG HG"){
					monstreD.setX(monstreD.getX() + 16 );
					monstreD.setY(monstreD.getY() + 16 );
					DirectionMonstreD();
					collisionMonstreD = true;
				}
				else if (monstreD.getDir() == "DIAG HD"){
					monstreD.setX(monstreD.getX() - 16 );
					monstreD.setY(monstreD.getY() + 16 );
					DirectionMonstreD();
					collisionMonstreD = true;
				}
			}
		}
			Rectangle murRec = lorann.getBounds();

			if(monstreDRec.intersects(murRec)){
				if (monstreD.getDir() == "BAS"){
					ChangerLevel();
					Vie--;
					
				}
				else if (monstreD.getDir() == "HAUT"){
					ChangerLevel();
					Vie--;
				}
				else if (monstreD.getDir() == "GAUCHE"){
					ChangerLevel();
					Vie--;
				}
				else if (monstreD.getDir() == "DROITE"){
					ChangerLevel();
					Vie--;
				}
				else if (monstreD.getDir() == "DIAG BG"){
					ChangerLevel();
					Vie--;
				}
				else if (monstreD.getDir() == "DIAG BD"){
					ChangerLevel();
					Vie--;
				}
				else if (monstreD.getDir() == "DIAG HG"){
					ChangerLevel();
					Vie--;
				}
				else if (monstreD.getDir() == "DIAG HD"){
					ChangerLevel();
					Vie--;
				}
			}
		}
	/**
	 * method to check collision from monstreA to elements
	 */
	public void CheckCollisionMonstreA(){

		Rectangle monstreARec;
		monstreARec = monstreA.getBounds();

		for(int i=0;i<Stones.size();i++){
			stone = (Stone) Stones.get(i);
			Rectangle murRec = stone.getBounds();

			if(monstreARec.intersects(murRec)){
				if (monstreA.getDir() == "BAS"){
					monstreA.setY(monstreA.getY() - 16 );
					DirectionMonstreA();
					collisionMonstreA = true;
				}
				else if (monstreA.getDir() == "HAUT"){
					monstreA.setY(monstreA.getY() + 16 );
					DirectionMonstreA();
					collisionMonstreA = true;
				}
				else if (monstreA.getDir() == "GAUCHE"){
					monstreA.setX(monstreA.getX() + 16 );
					DirectionMonstreA();
					collisionMonstreA = true;
				}
				else if (monstreA.getDir() == "DROITE"){
					monstreA.setX(monstreA.getX() - 16 );
					DirectionMonstreA();
					collisionMonstreA = true;
				}
				else if (monstreA.getDir() == "DIAG BG"){
					monstreA.setX(monstreA.getX() + 16 );
					monstreA.setY(monstreA.getY() - 16 );
					DirectionMonstreA();
					collisionMonstreA = true;
				}
				else if (monstreA.getDir() == "DIAG BD"){
					monstreA.setX(monstreA.getX() - 16 );
					monstreA.setY(monstreA.getY() - 16 );
					DirectionMonstreA();
					collisionMonstreA = true;
				}
				else if (monstreA.getDir() == "DIAG HG"){
					monstreA.setX(monstreA.getX() + 16 );
					monstreA.setY(monstreA.getY() + 16 );
					DirectionMonstreA();
					collisionMonstreA = true;
				}
				else if (monstreA.getDir() == "DIAG HD"){
					monstreA.setX(monstreA.getX() - 16 );
					monstreA.setY(monstreA.getY() + 16 );
					DirectionMonstreA();
					collisionMonstreA = true;
				}
			}
		}
		for(int i=0;i<Horizoss.size();i++){
			horizBone = (HorizBone) Horizoss.get(i);
			Rectangle murRec = horizBone.getBounds();

			if(monstreARec.intersects(murRec)){
				if (monstreA.getDir() == "BAS"){
					monstreA.setY(monstreA.getY() - 16 );
					DirectionMonstreA();
					collisionMonstreA = true;
				}
				else if (monstreA.getDir() == "HAUT"){
					monstreA.setY(monstreA.getY() + 16 );
					DirectionMonstreA();
					collisionMonstreA = true;
				}
				else if (monstreA.getDir() == "GAUCHE"){
					monstreA.setX(monstreA.getX() + 16 );
					DirectionMonstreA();
					collisionMonstreA = true;
				}
				else if (monstreA.getDir() == "DROITE"){
					monstreA.setX(monstreA.getX() - 16 );
					DirectionMonstreA();
					collisionMonstreA = true;
				}
				else if (monstreA.getDir() == "DIAG BG"){
					monstreA.setX(monstreA.getX() + 16 );
					monstreA.setY(monstreA.getY() - 16 );
					DirectionMonstreA();
					collisionMonstreA = true;
				}
				else if (monstreA.getDir() == "DIAG BD"){
					monstreA.setX(monstreA.getX() - 16 );
					monstreA.setY(monstreA.getY() - 16 );
					DirectionMonstreA();
					collisionMonstreA = true;
				}
				else if (monstreA.getDir() == "DIAG HG"){
					monstreA.setX(monstreA.getX() + 16 );
					monstreA.setY(monstreA.getY() + 16 );
					DirectionMonstreA();
					collisionMonstreA = true;
				}
				else if (monstreA.getDir() == "DIAG HD"){
					monstreA.setX(monstreA.getX() - 16 );
					monstreA.setY(monstreA.getY() + 16 );
					DirectionMonstreA();
					collisionMonstreA = true;
				}
			}
		}
		for(int i=0;i<balls.size();i++){
			ball = (ball) balls.get(i);
			Rectangle murRec = ball.getBounds();

			if(monstreARec.intersects(murRec)){
				if (monstreA.getDir() == "BAS"){
					monstreA.setY(monstreA.getY() - 16 );
					DirectionMonstreA();
					collisionMonstreA = true;
				}
				else if (monstreA.getDir() == "HAUT"){
					monstreA.setY(monstreA.getY() + 16 );
					DirectionMonstreA();
					collisionMonstreA = true;
				}
				else if (monstreA.getDir() == "GAUCHE"){
					monstreA.setX(monstreA.getX() + 16 );
					DirectionMonstreA();
					collisionMonstreA = true;
				}
				else if (monstreA.getDir() == "DROITE"){
					monstreA.setX(monstreA.getX() - 16 );
					DirectionMonstreA();
					collisionMonstreA = true;
				}
				else if (monstreA.getDir() == "DIAG BG"){
					monstreA.setX(monstreA.getX() + 16 );
					monstreA.setY(monstreA.getY() - 16 );
					DirectionMonstreA();
					collisionMonstreA = true;
				}
				else if (monstreA.getDir() == "DIAG BD"){
					monstreA.setX(monstreA.getX() - 16 );
					monstreA.setY(monstreA.getY() - 16 );
					DirectionMonstreA();
					collisionMonstreA = true;
				}
				else if (monstreA.getDir() == "DIAG HG"){
					monstreA.setX(monstreA.getX() + 16 );
					monstreA.setY(monstreA.getY() + 16 );
					DirectionMonstreA();
					collisionMonstreA = true;
				}
				else if (monstreA.getDir() == "DIAG HD"){
					monstreA.setX(monstreA.getX() - 16 );
					monstreA.setY(monstreA.getY() + 16 );
					DirectionMonstreA();
					collisionMonstreA = true;
				}
			}
		}
		for(int i=0;i<Verticoss.size();i++){
			verticBone = (VerticBone) Verticoss.get(i);
			Rectangle murRec = verticBone.getBounds();

			if(monstreARec.intersects(murRec)){
				if (monstreA.getDir() == "BAS"){
					monstreA.setY(monstreA.getY() - 16 );
					DirectionMonstreA();
					collisionMonstreA = true;
				}
				else if (monstreA.getDir() == "HAUT"){
					monstreA.setY(monstreA.getY() + 16 );
					DirectionMonstreA();
					collisionMonstreA = true;
				}
				else if (monstreA.getDir() == "GAUCHE"){
					monstreA.setX(monstreA.getX() + 16 );
					DirectionMonstreA();
					collisionMonstreA = true;
				}
				else if (monstreA.getDir() == "DROITE"){
					monstreA.setX(monstreA.getX() - 16 );
					DirectionMonstreA();
					collisionMonstreA = true;
				}
				else if (monstreA.getDir() == "DIAG BG"){
					monstreA.setX(monstreA.getX() + 16 );
					monstreA.setY(monstreA.getY() - 16 );
					DirectionMonstreA();
					collisionMonstreA = true;
				}
				else if (monstreA.getDir() == "DIAG BD"){
					monstreA.setX(monstreA.getX() - 16 );
					monstreA.setY(monstreA.getY() - 16 );
					DirectionMonstreA();
					collisionMonstreA = true;
				}
				else if (monstreA.getDir() == "DIAG HG"){
					monstreA.setX(monstreA.getX() + 16 );
					monstreA.setY(monstreA.getY() + 16 );
					DirectionMonstreA();
					collisionMonstreA = true;
				}
				else if (monstreA.getDir() == "DIAG HD"){
					monstreA.setX(monstreA.getX() - 16 );
					monstreA.setY(monstreA.getY() + 16 );
					DirectionMonstreA();
					collisionMonstreA = true;
				}
			}
		}
		for(int i=0;i<Skulls.size();i++){
			skull = (Skull) Skulls.get(i);
			Rectangle murRec = skull.getBounds();

			if(monstreARec.intersects(murRec)){
				if (monstreA.getDir() == "BAS"){
					monstreA.setY(monstreA.getY() - 16 );
					DirectionMonstreA();
					collisionMonstreA = true;
				}
				else if (monstreA.getDir() == "HAUT"){
					monstreA.setY(monstreA.getY() + 16 );
					DirectionMonstreA();
					collisionMonstreA = true;
				}
				else if (monstreA.getDir() == "GAUCHE"){
					monstreA.setX(monstreA.getX() + 16 );
					DirectionMonstreA();
					collisionMonstreA = true;
				}
				else if (monstreA.getDir() == "DROITE"){
					monstreA.setX(monstreA.getX() - 16 );
					DirectionMonstreA();
					collisionMonstreA = true;
				}
				else if (MonstreADir == "DIAG BG"){
					monstreA.setX(monstreA.getX() + 16 );
					monstreA.setY(monstreA.getY() - 16 );
					DirectionMonstreA();
					collisionMonstreA = true;
				}
				else if (MonstreADir == "DIAG BD"){
					monstreA.setX(monstreA.getX() - 16 );
					monstreA.setY(monstreA.getY() - 16 );
					DirectionMonstreA();
					collisionMonstreA = true;
				}
				else if (MonstreADir == "DIAG HG"){
					monstreA.setX(monstreA.getX() + 16 );
					monstreA.setY(monstreA.getY() + 16 );
					DirectionMonstreA();
					collisionMonstreA = true;
				}
				else if (MonstreADir == "DIAG HD"){
					monstreA.setX(monstreA.getX() - 16 );
					monstreA.setY(monstreA.getY() + 16 );
					DirectionMonstreA();
					collisionMonstreA = true;
				}
			}
		}
		for(int i=0;i<Projectiles.size();i++){
			projectile = (Projectile) Projectiles.get(i);
			Rectangle murRec = projectile.getBounds();

			if(monstreARec.intersects(murRec)){
				if (monstreA.getDir() == "BAS"){
					MonstresA.remove(0);
				}
				else if (monstreA.getDir() == "HAUT"){
					MonstresC.remove(0);
				}
				else if (monstreA.getDir() == "GAUCHE"){
					MonstresC.remove(0);
				}
				else if (monstreA.getDir() == "DROITE"){
					MonstresC.remove(0);
				}
				else if (monstreA.getDir() == "DIAG BG"){
					MonstresC.remove(0);
				}
				else if (monstreA.getDir() == "DIAG BD"){
					MonstresC.remove(0);
				}
				else if (monstreA.getDir() == "DIAG HG"){
					MonstresC.remove(0);
				}
				else if (monstreA.getDir() == "DIAG HD"){
					MonstresC.remove(0);
				}
			}
		}
			Rectangle murRec = lorann.getBounds();

			if(monstreARec.intersects(murRec)){
				if (monstreA.getDir() == "BAS"){
					ChangerLevel();
					Vie--;
					
				}
				else if (monstreA.getDir() == "HAUT"){
					ChangerLevel();
					Vie--;
				}
				else if (monstreA.getDir() == "GAUCHE"){
					ChangerLevel();
					Vie--;
				}
				else if (monstreA.getDir() == "DROITE"){
					ChangerLevel();
					Vie--;
				}
				else if (monstreA.getDir() == "DIAG BG"){
					ChangerLevel();
					Vie--;
				}
				else if (monstreA.getDir() == "DIAG BD"){
					ChangerLevel();
					Vie--;
				}
				else if (monstreA.getDir() == "DIAG HG"){
					ChangerLevel();
					Vie--;
				}
				else if (monstreA.getDir() == "DIAG HD"){
					ChangerLevel();
					Vie--;
				}
			}
		}
	/**
	 * method to check collision from monstreB to elements
	 */
	public void CheckCollisionMonstreB(){

		Rectangle monstreBRec;
		monstreBRec = monstreB.getBounds();

		for(int i=0;i<Stones.size();i++){
			stone = (Stone) Stones.get(i);
			Rectangle murRec = stone.getBounds();

			if(monstreBRec.intersects(murRec)){
				if (monstreB.getDir() == "BAS"){
					monstreB.setY(monstreB.getY() - 16 );
					DirectionMonstreB();
					collisionMonstreB = true;
				}
				else if (monstreB.getDir() == "HAUT"){
					monstreB.setY(monstreB.getY() + 16 );
					DirectionMonstreB();
					collisionMonstreB = true;
				}
				else if (monstreB.getDir() == "GAUCHE"){
					monstreB.setX(monstreB.getX() + 16 );
					DirectionMonstreB();
					collisionMonstreB = true;
				}
				else if (monstreB.getDir() == "DROITE"){
					monstreB.setX(monstreB.getX() - 16 );
					DirectionMonstreB();
					collisionMonstreB = true;
				}
				else if (monstreB.getDir() == "DIAG BG"){
					monstreB.setX(monstreB.getX() + 16 );
					monstreB.setY(monstreB.getY() - 16 );
					DirectionMonstreB();
					collisionMonstreB = true;
				}
				else if (monstreB.getDir() == "DIAG BD"){
					monstreB.setX(monstreB.getX() - 16 );
					monstreB.setY(monstreB.getY() - 16 );
					DirectionMonstreB();
					collisionMonstreB = true;
				}
				else if (monstreB.getDir() == "DIAG HG"){
					monstreB.setX(monstreB.getX() + 16 );
					monstreB.setY(monstreB.getY() + 16 );
					DirectionMonstreB();
					collisionMonstreB = true;
				}
				else if (monstreB.getDir() == "DIAG HD"){
					monstreB.setX(monstreB.getX() - 16 );
					monstreB.setY(monstreB.getY() + 16 );
					DirectionMonstreB();
					collisionMonstreB = true;
				}
			}
		}
		for(int i=0;i<Horizoss.size();i++){
			horizBone = (HorizBone) Horizoss.get(i);
			Rectangle murRec = horizBone.getBounds();

			if(monstreBRec.intersects(murRec)){
				if (monstreB.getDir() == "BAS"){
					monstreB.setY(monstreB.getY() - 16 );
					DirectionMonstreB();
					collisionMonstreB = true;
				}
				else if (monstreB.getDir() == "HAUT"){
					monstreB.setY(monstreB.getY() + 16 );
					DirectionMonstreB();
					collisionMonstreB = true;
				}
				else if (monstreB.getDir() == "GAUCHE"){
					monstreB.setX(monstreB.getX() + 16 );
					DirectionMonstreB();
					collisionMonstreB = true;
				}
				else if (monstreB.getDir() == "DROITE"){
					monstreB.setX(monstreB.getX() - 16 );
					DirectionMonstreB();
					collisionMonstreB = true;
				}
				else if (monstreB.getDir() == "DIAG BG"){
					monstreB.setX(monstreB.getX() + 16 );
					monstreB.setY(monstreB.getY() - 16 );
					DirectionMonstreB();
					collisionMonstreB = true;
				}
				else if (monstreB.getDir() == "DIAG BD"){
					monstreB.setX(monstreB.getX() - 16 );
					monstreB.setY(monstreB.getY() - 16 );
					DirectionMonstreB();
					collisionMonstreB = true;
				}
				else if (monstreB.getDir() == "DIAG HG"){
					monstreB.setX(monstreB.getX() + 16 );
					monstreB.setY(monstreB.getY() + 16 );
					DirectionMonstreB();
					collisionMonstreB = true;
				}
				else if (monstreB.getDir() == "DIAG HD"){
					monstreB.setX(monstreB.getX() - 16 );
					monstreB.setY(monstreB.getY() + 16 );
					DirectionMonstreB();
					collisionMonstreB = true;
				}
			}
		}
		for(int i=0;i<balls.size();i++){
			ball = (ball) balls.get(i);
			Rectangle murRec = ball.getBounds();

			if(monstreBRec.intersects(murRec)){
				if (monstreB.getDir() == "BAS"){
					monstreB.setY(monstreB.getY() - 16 );
					DirectionMonstreB();
					collisionMonstreB = true;
				}
				else if (monstreB.getDir() == "HAUT"){
					monstreB.setY(monstreB.getY() + 16 );
					DirectionMonstreB();
					collisionMonstreB = true;
				}
				else if (monstreB.getDir() == "GAUCHE"){
					monstreB.setX(monstreB.getX() + 16 );
					DirectionMonstreB();
					collisionMonstreB = true;
				}
				else if (monstreB.getDir() == "DROITE"){
					monstreB.setX(monstreB.getX() - 16 );
					DirectionMonstreB();
					collisionMonstreB = true;
				}
				else if (monstreB.getDir() == "DIAG BG"){
					monstreB.setX(monstreB.getX() + 16 );
					monstreB.setY(monstreB.getY() - 16 );
					DirectionMonstreB();
					collisionMonstreB = true;
				}
				else if (monstreB.getDir() == "DIAG BD"){
					monstreB.setX(monstreB.getX() - 16 );
					monstreB.setY(monstreB.getY() - 16 );
					DirectionMonstreB();
					collisionMonstreB = true;
				}
				else if (monstreB.getDir() == "DIAG HG"){
					monstreB.setX(monstreB.getX() + 16 );
					monstreB.setY(monstreB.getY() + 16 );
					DirectionMonstreB();
					collisionMonstreB = true;
				}
				else if (monstreB.getDir() == "DIAG HD"){
					monstreB.setX(monstreB.getX() - 16 );
					monstreB.setY(monstreB.getY() + 16 );
					DirectionMonstreB();
					collisionMonstreB = true;
				}
			}
		}
		for(int i=0;i<Verticoss.size();i++){
			verticBone = (VerticBone) Verticoss.get(i);
			Rectangle murRec = verticBone.getBounds();

			if(monstreBRec.intersects(murRec)){
				if (monstreB.getDir() == "BAS"){
					monstreB.setY(monstreB.getY() - 16 );
					DirectionMonstreB();
					collisionMonstreB = true;
				}
				else if (monstreB.getDir() == "HAUT"){
					monstreB.setY(monstreB.getY() + 16 );
					DirectionMonstreB();
					collisionMonstreB = true;
				}
				else if (monstreB.getDir() == "GAUCHE"){
					monstreB.setX(monstreB.getX() + 16 );
					DirectionMonstreB();
					collisionMonstreB = true;
				}
				else if (monstreB.getDir() == "DROITE"){
					monstreB.setX(monstreB.getX() - 16 );
					DirectionMonstreB();
					collisionMonstreB = true;
				}
				else if (monstreB.getDir() == "DIAG BG"){
					monstreB.setX(monstreB.getX() + 16 );
					monstreB.setY(monstreB.getY() - 16 );
					DirectionMonstreB();
					collisionMonstreB = true;
				}
				else if (monstreB.getDir() == "DIAG BD"){
					monstreB.setX(monstreB.getX() - 16 );
					monstreB.setY(monstreB.getY() - 16 );
					DirectionMonstreB();
					collisionMonstreB = true;
				}
				else if (monstreB.getDir() == "DIAG HG"){
					monstreB.setX(monstreB.getX() + 16 );
					monstreB.setY(monstreB.getY() + 16 );
					DirectionMonstreB();
					collisionMonstreB = true;
				}
				else if (monstreB.getDir() == "DIAG HD"){
					monstreB.setX(monstreB.getX() - 16 );
					monstreB.setY(monstreB.getY() + 16 );
					DirectionMonstreB();
					collisionMonstreB = true;
				}
			}
		}
		for(int i=0;i<Skulls.size();i++){
			skull = (Skull) Skulls.get(i);
			Rectangle murRec = skull.getBounds();

			if(monstreBRec.intersects(murRec)){
				if (monstreB.getDir() == "BAS"){
					monstreB.setY(monstreB.getY() - 16 );
					DirectionMonstreB();
					collisionMonstreB = true;
				}
				else if (monstreB.getDir() == "HAUT"){
					monstreB.setY(monstreB.getY() + 16 );
					DirectionMonstreB();
					collisionMonstreB = true;
				}
				else if (monstreB.getDir() == "GAUCHE"){
					monstreB.setX(monstreB.getX() + 16 );
					DirectionMonstreB();
					collisionMonstreB = true;
				}
				else if (monstreB.getDir() == "DROITE"){
					monstreB.setX(monstreB.getX() - 16 );
					DirectionMonstreB();
					collisionMonstreB = true;
				}
				else if (monstreB.getDir() == "DIAG BG"){
					monstreB.setX(monstreB.getX() + 16 );
					monstreB.setY(monstreB.getY() - 16 );
					DirectionMonstreB();
					collisionMonstreB = true;
				}
				else if (monstreB.getDir() == "DIAG BD"){
					monstreB.setX(monstreB.getX() - 16 );
					monstreB.setY(monstreB.getY() - 16 );
					DirectionMonstreB();
					collisionMonstreB = true;
				}
				else if (monstreB.getDir() == "DIAG HG"){
					monstreB.setX(monstreB.getX() + 16 );
					monstreB.setY(monstreB.getY() + 16 );
					DirectionMonstreB();
					collisionMonstreB = true;
				}
				else if (monstreB.getDir() == "DIAG HD"){
					monstreB.setX(monstreB.getX() - 16 );
					monstreB.setY(monstreB.getY() + 16 );
					DirectionMonstreB();
					collisionMonstreB = true;
				}
			}
		}
			Rectangle murRec = lorann.getBounds();

			if(monstreBRec.intersects(murRec)){
				if (monstreB.getDir() == "BAS"){
					ChangerLevel();
					Vie--;
					
				}
				else if (monstreB.getDir() == "HAUT"){
					ChangerLevel();
					Vie--;
				}
				else if (monstreB.getDir() == "GAUCHE"){
					ChangerLevel();
					Vie--;
				}
				else if (monstreB.getDir() == "DROITE"){
					ChangerLevel();
					Vie--;
				}
				else if (monstreB.getDir() == "DIAG BG"){
					ChangerLevel();
					Vie--;
				}
				else if (monstreB.getDir() == "DIAG BD"){
					ChangerLevel();
					Vie--;
				}
				else if (monstreB.getDir() == "DIAG HG"){
					ChangerLevel();
					Vie--;
				}
				else if (monstreB.getDir() == "DIAG HD"){
					ChangerLevel();
					Vie--;
				}
			}
		}
	/**
	 * method to check collision from monstreC to elements
	 */
	public void CheckCollisionMonstreC(){

		Rectangle monstreCRec;
		monstreCRec = monstreC.getBounds();

		for(int i=0;i<Stones.size();i++){
			stone = (Stone) Stones.get(i);
			Rectangle murRec = stone.getBounds();

			if(monstreCRec.intersects(murRec)){
				if (monstreC.getDir() == "BAS"){
					monstreC.setY(monstreC.getY() - 16 );
					DirectionMonstreC();
					collisionMonstreC = true;
				}
				else if (monstreC.getDir() == "HAUT"){
					monstreC.setY(monstreC.getY() + 16 );
					DirectionMonstreC();
					collisionMonstreC = true;
				}
				else if (monstreC.getDir() == "GAUCHE"){
					monstreC.setX(monstreC.getX() + 16 );
					DirectionMonstreC();
					collisionMonstreC = true;
				}
				else if (monstreC.getDir() == "DROITE"){
					monstreC.setX(monstreC.getX() - 16 );
					DirectionMonstreC();
					collisionMonstreC = true;
				}
				else if (monstreC.getDir() == "DIAG BG"){
					monstreC.setX(monstreC.getX() + 16 );
					monstreC.setY(monstreC.getY() - 16 );
					DirectionMonstreC();
					collisionMonstreC = true;
				}
				else if (monstreC.getDir() == "DIAG BD"){
					monstreC.setX(monstreC.getX() - 16 );
					monstreC.setY(monstreC.getY() - 16 );
					DirectionMonstreC();
					collisionMonstreC = true;
				}
				else if (monstreC.getDir() == "DIAG HG"){
					monstreC.setX(monstreC.getX() + 16 );
					monstreC.setY(monstreC.getY() + 16 );
					DirectionMonstreC();
					collisionMonstreC = true;
				}
				else if (monstreC.getDir() == "DIAG HD"){
					monstreC.setX(monstreC.getX() - 16 );
					monstreC.setY(monstreC.getY() + 16 );
					DirectionMonstreC();
					collisionMonstreC = true;
				}
			}
		}
		for(int i=0;i<Horizoss.size();i++){
			horizBone = (HorizBone) Horizoss.get(i);
			Rectangle murRec = horizBone.getBounds();

			if(monstreCRec.intersects(murRec)){
				if (monstreC.getDir() == "BAS"){
					monstreC.setY(monstreC.getY() - 16 );
					DirectionMonstreC();
					collisionMonstreC = true;
				}
				else if (monstreC.getDir() == "HAUT"){
					monstreC.setY(monstreC.getY() + 16 );
					DirectionMonstreC();
					collisionMonstreC = true;
				}
				else if (monstreC.getDir() == "GAUCHE"){
					monstreC.setX(monstreC.getX() + 16 );
					DirectionMonstreC();
					collisionMonstreC = true;
				}
				else if (monstreC.getDir() == "DROITE"){
					monstreC.setX(monstreC.getX() - 16 );
					DirectionMonstreC();
					collisionMonstreC = true;
				}
				else if (monstreC.getDir() == "DIAG BG"){
					monstreC.setX(monstreC.getX() + 16 );
					monstreC.setY(monstreC.getY() - 16 );
					DirectionMonstreC();
					collisionMonstreC = true;
				}
				else if (monstreC.getDir() == "DIAG BD"){
					monstreC.setX(monstreC.getX() - 16 );
					monstreC.setY(monstreC.getY() - 16 );
					DirectionMonstreC();
					collisionMonstreC = true;
				}
				else if (monstreC.getDir() == "DIAG HG"){
					monstreC.setX(monstreC.getX() + 16 );
					monstreC.setY(monstreC.getY() + 16 );
					DirectionMonstreC();
					collisionMonstreC = true;
				}
				else if (monstreC.getDir() == "DIAG HD"){
					monstreC.setX(monstreC.getX() - 16 );
					monstreC.setY(monstreC.getY() + 16 );
					DirectionMonstreC();
					collisionMonstreC = true;
				}
			}
		}
		for(int i=0;i<balls.size();i++){
			ball = (ball) balls.get(i);
			Rectangle murRec = ball.getBounds();

			if(monstreCRec.intersects(murRec)){
				if (monstreC.getDir() == "BAS"){
					monstreC.setY(monstreC.getY() - 16 );
					DirectionMonstreC();
					collisionMonstreC = true;
				}
				else if (monstreC.getDir() == "HAUT"){
					monstreC.setY(monstreC.getY() + 16 );
					DirectionMonstreC();
					collisionMonstreC = true;
				}
				else if (monstreC.getDir() == "GAUCHE"){
					monstreC.setX(monstreC.getX() + 16 );
					DirectionMonstreC();
					collisionMonstreC = true;
				}
				else if (monstreC.getDir() == "DROITE"){
					monstreC.setX(monstreC.getX() - 16 );
					DirectionMonstreC();
					collisionMonstreC = true;
				}
				else if (monstreC.getDir() == "DIAG BG"){
					monstreC.setX(monstreC.getX() + 16 );
					monstreC.setY(monstreC.getY() - 16 );
					DirectionMonstreC();
					collisionMonstreC = true;
				}
				else if (monstreC.getDir() == "DIAG BD"){
					monstreC.setX(monstreC.getX() - 16 );
					monstreC.setY(monstreC.getY() - 16 );
					DirectionMonstreC();
					collisionMonstreC = true;
				}
				else if (monstreC.getDir() == "DIAG HG"){
					monstreC.setX(monstreC.getX() + 16 );
					monstreC.setY(monstreC.getY() + 16 );
					DirectionMonstreC();
					collisionMonstreC = true;
				}
				else if (monstreC.getDir() == "DIAG HD"){
					monstreC.setX(monstreC.getX() - 16 );
					monstreC.setY(monstreC.getY() + 16 );
					DirectionMonstreC();
					collisionMonstreC = true;
				}
			}
		}
		for(int i=0;i<Verticoss.size();i++){
			verticBone = (VerticBone) Verticoss.get(i);
			Rectangle murRec = verticBone.getBounds();

			if(monstreCRec.intersects(murRec)){
				if (monstreC.getDir() == "BAS"){
					monstreC.setY(monstreC.getY() - 16 );
					DirectionMonstreC();
					collisionMonstreC = true;
				}
				else if (monstreC.getDir() == "HAUT"){
					monstreC.setY(monstreC.getY() + 16 );
					DirectionMonstreC();
					collisionMonstreC = true;
				}
				else if (monstreC.getDir() == "GAUCHE"){
					monstreC.setX(monstreC.getX() + 16 );
					DirectionMonstreC();
					collisionMonstreC = true;
				}
				else if (monstreC.getDir() == "DROITE"){
					monstreC.setX(monstreC.getX() - 16 );
					DirectionMonstreC();
					collisionMonstreC = true;
				}
				else if (monstreC.getDir() == "DIAG BG"){
					monstreC.setX(monstreC.getX() + 16 );
					monstreC.setY(monstreC.getY() - 16 );
					DirectionMonstreC();
					collisionMonstreC = true;
				}
				else if (monstreC.getDir() == "DIAG BD"){
					monstreC.setX(monstreC.getX() - 16 );
					monstreC.setY(monstreC.getY() - 16 );
					DirectionMonstreC();
					collisionMonstreC = true;
				}
				else if (monstreC.getDir() == "DIAG HG"){
					monstreC.setX(monstreC.getX() + 16 );
					monstreC.setY(monstreC.getY() + 16 );
					DirectionMonstreC();
					collisionMonstreC = true;
				}
				else if (monstreC.getDir() == "DIAG HD"){
					monstreC.setX(monstreC.getX() - 16 );
					monstreC.setY(monstreC.getY() + 16 );
					DirectionMonstreC();
					collisionMonstreC = true;
				}
			}
		}
		for(int i=0;i<Skulls.size();i++){
			skull = (Skull) Skulls.get(i);
			Rectangle murRec = skull.getBounds();

			if(monstreCRec.intersects(murRec)){
				if (monstreC.getDir() == "BAS"){
					monstreC.setY(monstreC.getY() - 16 );
					DirectionMonstreC();
					collisionMonstreC = true;
				}
				else if (monstreC.getDir() == "HAUT"){
					monstreC.setY(monstreC.getY() + 16 );
					DirectionMonstreC();
					collisionMonstreC = true;
				}
				else if (monstreC.getDir() == "GAUCHE"){
					monstreC.setX(monstreC.getX() + 16 );
					DirectionMonstreC();
					collisionMonstreC = true;
				}
				else if (monstreC.getDir() == "DROITE"){
					monstreC.setX(monstreC.getX() - 16 );
					DirectionMonstreC();
					collisionMonstreC = true;
				}
				else if (monstreC.getDir() == "DIAG BG"){
					monstreC.setX(monstreC.getX() + 16 );
					monstreC.setY(monstreC.getY() - 16 );
					DirectionMonstreC();
					collisionMonstreC = true;
				}
				else if (monstreC.getDir() == "DIAG BD"){
					monstreC.setX(monstreC.getX() - 16 );
					monstreC.setY(monstreC.getY() - 16 );
					DirectionMonstreC();
					collisionMonstreC = true;
				}
				else if (monstreC.getDir() == "DIAG HG"){
					monstreC.setX(monstreC.getX() + 16 );
					monstreC.setY(monstreC.getY() + 16 );
					DirectionMonstreC();
					collisionMonstreC = true;
				}
				else if (monstreC.getDir() == "DIAG HD"){
					monstreC.setX(monstreC.getX() - 16 );
					monstreC.setY(monstreC.getY() + 16 );
					DirectionMonstreC();
					collisionMonstreC = true;
				}
			}
		}
		for(int i=0;i<Projectiles.size();i++){
			projectile = (Projectile) Projectiles.get(i);
			Rectangle murRec = projectile.getBounds();

			if(monstreCRec.intersects(murRec)){
				if (monstreC.getDir() == "BAS"){
					MonstresC.remove(0);
				}
				else if (monstreC.getDir() == "HAUT"){
					MonstresC.remove(0);
				}
				else if (monstreC.getDir() == "GAUCHE"){
					MonstresC.remove(0);
				}
				else if (monstreC.getDir() == "DROITE"){
					MonstresC.remove(0);
				}
				else if (monstreC.getDir() == "DIAG BG"){
					MonstresC.remove(0);
				}
				else if (monstreC.getDir() == "DIAG BD"){
					MonstresC.remove(0);
				}
				else if (monstreC.getDir() == "DIAG HG"){
					MonstresC.remove(0);
				}
				else if (monstreC.getDir() == "DIAG HD"){
					MonstresC.remove(0);
				}
			}
		}
			Rectangle murRec = lorann.getBounds();

			if(monstreCRec.intersects(murRec)){
				if (monstreC.getDir() == "BAS"){
					ChangerLevel();
					Vie--;
					
				}
				else if (monstreC.getDir() == "HAUT"){
					ChangerLevel();
					Vie--;
				}
				else if (monstreC.getDir() == "GAUCHE"){
					ChangerLevel();
					Vie--;
				}
				else if (monstreC.getDir() == "DROITE"){
					ChangerLevel();
					Vie--;
				}
				else if (monstreC.getDir() == "DIAG BG"){
					ChangerLevel();
					Vie--;
				}
				else if (monstreC.getDir() == "DIAG BD"){
					ChangerLevel();
					Vie--;
				}
				else if (monstreC.getDir() == "DIAG HG"){
					ChangerLevel();
					Vie--;
				}
				else if (monstreC.getDir() == "DIAG HD"){
					ChangerLevel();
					Vie--;
				}
			}
		}
/**
 * method to check collision from Lorann to elements
 */
	public void CheckCollision(){

		Rectangle lorannRec;
		lorannRec = lorann.getBounds();

		for(int i=0;i<Stones.size();i++){
			stone = (Stone) Stones.get(i);
			Rectangle murRec = stone.getBounds();

			if(lorannRec.intersects(murRec)){
				if (lorann.getDir() == "BAS"){
					lorann.setY(lorann.getY() - 16 );
				}
				else if (lorann.getDir() == "HAUT"){
					lorann.setY(lorann.getY() + 16 );
				}
				else if (lorann.getDir() == "GAUCHE"){
					lorann.setX(lorann.getX() + 16 );
				}
				else if (lorann.getDir() == "DROITE"){
					lorann.setX(lorann.getX() - 16 );
				}
				else if (lorann.getDir() == "DIAG HG"){
					lorann.setX(lorann.getX() + 16 );
					lorann.setY(lorann.getY() + 16 );
				}
				else if (lorann.getDir() == "DIAG HD"){
					lorann.setX(lorann.getX() - 16 );
					lorann.setY(lorann.getY() + 16 );
				}
				else if (lorann.getDir() == "DIAG BG"){
					lorann.setX(lorann.getX() + 16 );
					lorann.setY(lorann.getY() - 16 );
				}
				else if (lorann.getDir() == "DIAG BD"){
					lorann.setX(lorann.getX() - 16 );
					lorann.setY(lorann.getY() - 16 );
				}
			}
		}
		for(int i=0;i<Horizoss.size();i++){
			horizBone = (HorizBone) Horizoss.get(i);
			Rectangle horizosRec = horizBone.getBounds();

			if(lorannRec.intersects(horizosRec)){
				if (lorann.getDir() == "BAS"){
					lorann.setY(lorann.getY() - 16 );
				}
				else if (lorann.getDir() == "HAUT"){
					lorann.setY(lorann.getY() + 16 );
				}
				else if (lorann.getDir() == "GAUCHE"){
					lorann.setX(lorann.getX() + 16 );
				}
				else if (lorann.getDir() == "DROITE"){
					lorann.setX(lorann.getX() - 16 );
				}
				else if (lorann.getDir() == "DIAG HG"){
					lorann.setX(lorann.getX() + 16 );
					lorann.setY(lorann.getY() + 16 );
				}
				else if (lorann.getDir() == "DIAG HD"){
					lorann.setX(lorann.getX() - 16 );
					lorann.setY(lorann.getY() + 16 );
				}
				else if (lorann.getDir() == "DIAG BG"){
					lorann.setX(lorann.getX() + 16 );
					lorann.setY(lorann.getY() - 16 );
				}
				else if (lorann.getDir() == "DIAG BD"){
					lorann.setX(lorann.getX() - 16 );
					lorann.setY(lorann.getY() - 16 );
				}
			}
	}
		for(int i=0;i<Verticoss.size();i++){
			verticBone = (VerticBone) Verticoss.get(i);
			Rectangle verticosRec = verticBone.getBounds();

			if(lorannRec.intersects(verticosRec)){
				if (lorann.getDir() == "BAS"){
					lorann.setY(lorann.getY() - 16 );
				}
				else if (lorann.getDir() == "HAUT"){
					lorann.setY(lorann.getY() + 16 );
				}
				else if (lorann.getDir() == "GAUCHE"){
					lorann.setX(lorann.getX() + 16 );
				}
				else if (lorann.getDir() == "DROITE"){
					lorann.setX(lorann.getX() - 16 );
				}
				else if (lorann.getDir() == "DIAG HG"){
					lorann.setX(lorann.getX() + 16 );
					lorann.setY(lorann.getY() + 16 );
				}
				else if (lorann.getDir() == "DIAG HD"){
					lorann.setX(lorann.getX() - 16 );
					lorann.setY(lorann.getY() + 16 );
				}
				else if (lorann.getDir() == "DIAG BG"){
					lorann.setX(lorann.getX() + 16 );
					lorann.setY(lorann.getY() - 16 );
				}
				else if (lorann.getDir() == "DIAG BD"){
					lorann.setX(lorann.getX() - 16 );
					lorann.setY(lorann.getY() - 16 );
				}
			}
	}
		for(int i=0;i<Skulls.size();i++){
			skull = (Skull) Skulls.get(i);
			Rectangle craneRec = skull.getBounds();

			if(lorannRec.intersects(craneRec)){
				if (lorann.getDir() == "BAS"){
					lorann.setY(lorann.getY() - 16 );
				}
				else if (lorann.getDir() == "HAUT"){
					lorann.setY(lorann.getY() + 16 );
				}
				else if (lorann.getDir() == "GAUCHE"){
					lorann.setX(lorann.getX() + 16 );
				}
				else if (lorann.getDir() == "DROITE"){
					lorann.setX(lorann.getX() - 16 );
				}
				else if (lorann.getDir() == "DIAG HG"){
					lorann.setX(lorann.getX() + 16 );
					lorann.setY(lorann.getY() + 16 );
				}
				else if (lorann.getDir() == "DIAG HD"){
					lorann.setX(lorann.getX() - 16 );
					lorann.setY(lorann.getY() + 16 );
				}
				else if (lorann.getDir() == "DIAG BG"){
					lorann.setX(lorann.getX() + 16 );
					lorann.setY(lorann.getY() - 16 );
				}
				else if (lorann.getDir() == "DIAG BD"){
					lorann.setX(lorann.getX() - 16 );
					lorann.setY(lorann.getY() - 16 );
				}
			}
	}
		for(int i=0;i<balls.size();i++){
			ball = (ball) balls.get(i);
			Rectangle bouleRec = ball.getBounds();

			if(lorannRec.intersects(bouleRec)){
				if (lorann.getDir() == "BAS"){
					balls.remove(i);
					ChangerLevel2();
				}
				else if (lorann.getDir() == "HAUT"){
					balls.remove(i);
					ChangerLevel2();
				}
				else if (lorann.getDir() == "GAUCHE"){
					balls.remove(i);
					ChangerLevel2();
				}
				else if (lorann.getDir() == "DROITE"){
					balls.remove(i);
					ChangerLevel2();
				}
				else if (lorann.getDir() == "DIAG HG"){
					balls.remove(i);
					ChangerLevel2();
				}
				else if (lorann.getDir() == "DIAG HD"){
					balls.remove(i);
					ChangerLevel2();
				}
				else if (lorann.getDir() == "DIAG BG"){
					balls.remove(i);
					ChangerLevel2();
				}
				else if (lorann.getDir() == "DIAG BD"){
					balls.remove(i);
					ChangerLevel2();
				}
				
			}
			
	}
		for(int i=0;i<Opens.size();i++){
			open = (Open) Opens.get(i);
			Rectangle ouverteRec = open.getBounds();

			if(lorannRec.intersects(ouverteRec)){
				Vie += 2;
				 level++;
				 ChangerLevel();
			}
	}
		for(int i=0;i<Tresors1.size();i++){
			treasure = (Treasure) Tresors1.get(i);
			Rectangle tresor1Rec = treasure.getBounds();

			if(lorannRec.intersects(tresor1Rec)){
				if (lorann.getDir() == "BAS"){
					score += 10;
					 Tresors1.remove(i);
				}
				else if (lorann.getDir() == "HAUT"){
					score += 10;
					Tresors1.remove(i);
				}
				else if (lorann.getDir() == "GAUCHE"){
					score += 10;
					Tresors1.remove(i);
				}
				else if (lorann.getDir() == "DROITE"){
					score += 10;
					Tresors1.remove(i);
				}
				else if (lorann.getDir() == "DIAG HG"){
					score += 10;
					Tresors1.remove(i);
				}
				else if (lorann.getDir() == "DIAG HD"){
					score += 10;
					Tresors1.remove(i);
				}
				else if (lorann.getDir() == "DIAG BG"){
					score += 10;
					Tresors1.remove(i);
				}
				else if (lorann.getDir() == "DIAG BD"){
					score += 10;
					Tresors1.remove(i);
				}
			}
	}
		for(int i=0;i<MonstresD.size();i++){
			monstreD = (MonstreD) MonstresD.get(i);
			Rectangle monstreDRec = monstreD.getBounds();

			if(lorannRec.intersects(monstreDRec)){
				if (lorann.getDir() == "BAS"){
					Vie--;
					ChangerLevel();
				}
				else if (lorann.getDir() == "HAUT"){
					ChangerLevel();
					Vie--;
				}
				else if (lorann.getDir() == "GAUCHE"){
					ChangerLevel();
					Vie--;
				}
				else if (lorann.getDir() == "DROITE"){
					ChangerLevel();
					Vie--;
				}
				else if (lorann.getDir() == "DIAG HG"){
					ChangerLevel();
					Vie--;
				}
				else if (lorann.getDir() == "DIAG HD"){
					ChangerLevel();
					Vie--;
				}
				else if (lorann.getDir() == "DIAG BG"){
					ChangerLevel();
					Vie--;
				}
				else if (lorann.getDir() == "DIAG BD"){
					ChangerLevel();
					Vie--;
				}
			}
	}
		for(int i=0;i<MonstresA.size();i++){
			monstreA = (MonstreA) MonstresA.get(i);
			Rectangle monstreARec = monstreA.getBounds();

			if(lorannRec.intersects(monstreARec)){
				if (lorann.getDir() == "BAS"){
					Vie--;
					ChangerLevel();
				}
				else if (lorann.getDir() == "HAUT"){
					ChangerLevel();
					Vie--;
				}
				else if (lorann.getDir() == "GAUCHE"){
					ChangerLevel();
					Vie--;
				}
				else if (lorann.getDir() == "DROITE"){
					ChangerLevel();
					Vie--;
				}
				else if (lorann.getDir() == "DIAG HG"){
					ChangerLevel();
					Vie--;
				}
				else if (lorann.getDir() == "DIAG HD"){
					ChangerLevel();
					Vie--;
				}
				else if (lorann.getDir() == "DIAG BG"){
					ChangerLevel();
					Vie--;
				}
				else if (lorann.getDir() == "DIAG BD"){
					ChangerLevel();
					Vie--;
				}
			}
	}
		for(int i=0;i<MonstresC.size();i++){
			monstreC = (MonstreC) MonstresC.get(i);
			Rectangle monstreCRec = monstreC.getBounds();

			if(lorannRec.intersects(monstreCRec)){
				if (lorann.getDir() == "BAS"){
					Vie--;
					ChangerLevel();
				}
				else if (lorann.getDir() == "HAUT"){
					ChangerLevel();
					Vie--;
				}
				else if (lorann.getDir() == "GAUCHE"){
					ChangerLevel();
					Vie--;
				}
				else if (lorann.getDir() == "DROITE"){
					ChangerLevel();
					Vie--;
				}
				else if (lorann.getDir() == "DIAG HG"){
					ChangerLevel();
					Vie--;
				}
				else if (lorann.getDir() == "DIAG HD"){
					ChangerLevel();
					Vie--;
				}
				else if (lorann.getDir() == "DIAG BG"){
					ChangerLevel();
					Vie--;
				}
				else if (lorann.getDir() == "DIAG BD"){
					ChangerLevel();
					Vie--;
				}
			}
	}
		for(int i=0;i<MonstresB.size();i++){
			monstreB = (MonstreB) MonstresB.get(i);
			Rectangle monstreBRec = monstreB.getBounds();

			if(lorannRec.intersects(monstreBRec)){
				if (lorann.getDir() == "BAS"){
					Vie--;
					ChangerLevel();
				}
				else if (lorann.getDir() == "HAUT"){
					ChangerLevel();
					Vie--;
				}
				else if (lorann.getDir() == "GAUCHE"){
					ChangerLevel();
					Vie--;
				}
				else if (lorann.getDir() == "DROITE"){
					ChangerLevel();
					Vie--;
				}
				else if (lorann.getDir() == "DIAG HG"){
					ChangerLevel();
					Vie--;
				}
				else if (lorann.getDir() == "DIAG HD"){
					ChangerLevel();
					Vie--;
				}
				else if (lorann.getDir() == "DIAG BG"){
					ChangerLevel();
					Vie--;
				}
				else if (lorann.getDir() == "DIAG BD"){
					ChangerLevel();
					Vie--;
				}
			}
	}
		for(int i=0;i<Skulls.size();i++){
			skull = (Skull) Skulls.get(i);
			Rectangle craneRec = skull.getBounds();

			if(lorannRec.intersects(craneRec)){
				if (lorann.getDir() == "BAS"){
					Vie--;
					ChangerLevel();
				}
				else if (lorann.getDir() == "HAUT"){
					ChangerLevel();
					Vie--;
				}
				else if (lorann.getDir() == "GAUCHE"){
					ChangerLevel();
					Vie--;
				}
				else if (lorann.getDir() == "DROITE"){
					ChangerLevel();
					Vie--;
				}
				else if (lorann.getDir() == "DIAG HG"){
					ChangerLevel();
					Vie--;
				}
				else if (lorann.getDir() == "DIAG HD"){
					ChangerLevel();
					Vie--;
				}
				else if (lorann.getDir() == "DIAG BG"){
					ChangerLevel();
					Vie--;
				}
				else if (lorann.getDir() == "DIAG BD"){
					ChangerLevel();
					Vie--;
				}
			}
			}
	}
/**
 * method to take the key pressed as events and initiate the effetcs on each entry
 */
	@Override
	public void keyPressed(KeyEvent arg0) {

		
		int Touche = arg0.getKeyCode();


		if (Touche == KeyEvent.VK_NUMPAD3){
			lorann.setDir("DIAG BD");
			lorann.Move();
			CheckCollision();
		}
		else if (Touche == KeyEvent.VK_NUMPAD9){
			lorann.setDir("DIAG HD");
			lorann.Move();
			CheckCollision();
		}
		else if (Touche == KeyEvent.VK_NUMPAD7){
			lorann.setDir("DIAG HG");
			lorann.Move();
			CheckCollision();
		}
		else if (Touche == KeyEvent.VK_NUMPAD1){
			lorann.setDir("DIAG BG");
			lorann.Move();
			CheckCollision();
		}
		else if (Touche == KeyEvent.VK_R){
			ChangerLevel();
			Vie--;
		}
		if (Touche == KeyEvent.VK_NUMPAD2){
			lorann.setDir("BAS");
			lorann.Move();
			CheckCollision();
			
		}
		else if (Touche == KeyEvent.VK_NUMPAD8){
			lorann.setDir("HAUT");
			lorann.Move();
			CheckCollision();
		}
		else if (Touche == KeyEvent.VK_NUMPAD4){
			lorann.setDir("GAUCHE");
			lorann.Move();
			CheckCollision();
		}
		else if (Touche == KeyEvent.VK_NUMPAD6){
			lorann.setDir("DROITE");
			lorann.Move();
			CheckCollision();
		}
		else if (Touche == KeyEvent.VK_SPACE){
			if (projectilelance == false){
				if(lorann.getDir()== "HAUT")
				{
			try{
				Game [10][10] = "PROJECTILE";
				projectile = new Projectile((10*16),(10*16));
				Projectiles.add(projectile);
				projectile.setX(lorann.getX());
				projectile.setY(lorann.getY()+16);
				projectile.setDir("BAS");
				projectilelance = true;
			}
			catch (Exception ex){}
			repaint();
			}
				if(lorann.getDir()== "BAS")
				{
			try{
				Game [10][10] = "PROJECTILE";
				projectile = new Projectile((10*16),(10*16));
				Projectiles.add(projectile);
				projectile.setX(lorann.getX());
				projectile.setY(lorann.getY()-16);
				projectile.setDir("HAUT");
				projectilelance = true;
			}
			catch (Exception ex){}
			repaint();
			}
				if(lorann.getDir()== "GAUCHE")
				{
			try{
				Game [10][10] = "PROJECTILE";
				projectile = new Projectile((10*16),(10*16));
				Projectiles.add(projectile);
				projectile.setX(lorann.getX()+16);
				projectile.setY(lorann.getY());
				projectile.setDir("DROITE");
				projectilelance = true;
			}
			catch (Exception ex){}
			repaint();
			}
				if(lorann.getDir()== "DROITE")
				{
			try{
				Game [10][10] = "PROJECTILE";
				projectile = new Projectile((10*16),(10*16));
				Projectiles.add(projectile);
				projectile.setX(lorann.getX()-16);
				projectile.setY(lorann.getY());
				projectile.setDir("GAUCHE");
				projectilelance = true;
			}
			catch (Exception ex){}
			repaint();
			}
				if(lorann.getDir()== "DIAG HG")
				{
			try{
				Game [10][10] = "PROJECTILE";
				projectile = new Projectile((10*16),(10*16));
				Projectiles.add(projectile);
				projectile.setX(lorann.getX()+16);
				projectile.setY(lorann.getY()+16);
				projectile.setDir("DIAG BD");
				projectilelance = true;
			}
			catch (Exception ex){}
			repaint();
			}
				if(lorann.getDir()== "DIAG HD")
				{
			try{
				Game [10][10] = "PROJECTILE";
				projectile = new Projectile((10*16),(10*16));
				Projectiles.add(projectile);
				projectile.setX(lorann.getX()-16);
				projectile.setY(lorann.getY()+16);
				projectile.setDir("DIAG BG");
				projectilelance = true;
			}
			catch (Exception ex){}
			repaint();
			}
				if(lorann.getDir()== "DIAG BG")
				{
			try{
				Game [10][10] = "PROJECTILE";
				projectile = new Projectile((10*16),(10*16));
				Projectiles.add(projectile);
				projectile.setX(lorann.getX()+16);
				projectile.setY(lorann.getY()-16);
				projectile.setDir("DIAG HD");
				projectilelance = true;
			}
			catch (Exception ex){}
			repaint();
			}
				if(lorann.getDir()== "DIAG BD")
				{
			try{
				Game [10][10] = "PROJECTILE";
				projectile = new Projectile((10*16),(10*16));
				Projectiles.add(projectile);
				projectile.setX(lorann.getX()-16);
				projectile.setY(lorann.getY()+16);
				projectile.setDir("DIAG HG");
				projectilelance = true;
			}
			catch (Exception ex){}
			repaint();
			}
			}
			else
				RetourProjectile(projectile.getDir());
		}
		else{
			if(lorannSens != 8){
			lorannSens++;	
			}
			else 
				lorannSens = 1;
			touchePriseEnCompte = false;
		}
		
		
		repaint();
		if (monstreAPresent == true){
		MouvementMonstreA();
		}
		if (monstreBPresent == true){
			MouvementMonstreB();
			}
		if (monstreCPresent == true){
			MouvementMonstreC();
			}
		if (monstreDPresent == true){
			MouvementMonstreD();
			}

		if (projectilelance == true)
		{
		MouvementProjectile();
		}
		if (Vie == 0){
			level = 1;
			ChangerLevel();
			Vie = 11;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}
	@Override
	public void keyTyped(KeyEvent arg0) {
	}
	
}