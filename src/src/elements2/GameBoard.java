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
	private static ArrayList<HorizBone> HorizBones;
	private static ArrayList<VerticBone> VerticBones;
	private static ArrayList<Stone> Stones;
	private static ArrayList<Treasure> Treasures;
	private static ArrayList<ball> balls;
	private static ArrayList<MonsterD> MonstersD;
	private static ArrayList<MonsterA> MonstersA;
	private static ArrayList<MonsterC>MonstersC;
	private static ArrayList<MonsterB>MonstersB;
	private static ArrayList<Skull> Skulls;
	private static ArrayList<Open>Opens;
	private static ArrayList<Missile>Missiles;
	/**
	 * objects declarations
	 */
	HorizBone horizBone;
	VerticBone verticBone;
	Stone stone;
	Treasure treasure;
	MonsterD monsterD;
	MonsterA monsterA;
	MonsterB monsterB;
	MonsterC monsterC;
	ball ball;
	Lorann lorann;
	Missile missile;
	Skull skull;
	Open open;
	FileReader fr;
	/**
	 * variable declaration
	 */
	int score = 0;
	Font levelFont = new Font("SansSerif", Font.BOLD,30);
	int shorter;
	String MonsterDDir;
	String MonsterCDir;
	String MonsterDRir;
	String MonsterADir;
	int Life = 11;
	boolean collisionMonsterA = false;
	boolean collisionMonsterB = false;
	boolean collisionMonsterC = false;
	boolean collisionMonsterD = false;
	int lorannSens;
	boolean keyTyped;
	boolean missilelaunched = false;
	boolean monstreAAlive = false;
	boolean monstreBAlive = false;
	boolean monstreCAlive = false;
	boolean monstreDAlive = false;
/**
 * GameBoard method
 * 
 * use to initiate the game in the GameFrame
 */
	public GameBoard(){
		this.setBackground(Color.BLACK);
		ChangeLevel();
		setFocusable(true);
		addKeyListener(this);
	}
/**
 * use to inverse the "missile" direction when space bar is typed or when it hit a wall
 * @param sensEntrant for the missile direction
 */
	public void RetourMissile(String sensEntrant){
		if(sensEntrant == "LEFT"){
			missile.setDir("RIGHT");
		}
		else if(sensEntrant == "RIGHT"){
			missile.setDir("LEFT");
		}
		else if(sensEntrant == "UP"){
			missile.setDir("DOWN");
		}
		else if(sensEntrant == "DOWN"){
			missile.setDir("UP");
		}
		else if(sensEntrant == "DIAG UR"){
			missile.setDir("DIAG DL");
		}
		else if(sensEntrant == "DIAG UL"){
			missile.setDir("DIAG DR");
		}
		else if(sensEntrant == "DIAG DR"){
			missile.setDir("DIAG UL");
		}
		else if(sensEntrant == "DIAG DL"){
			missile.setDir("DIAG UR");
		}
		
	}
	/**
	 * used to pack the move and the collision check
	 */
	public void MovementMissile(){

		missile.Move();	
		CheckCollisionMissile();
		
	}
	/**
	 * used to pack all the methods used by the monsterA movement
	 */
	public void MovementMonsterA(){
		monsterA.Move();
		CheckCollisionMonsterA();
		if (collisionMonsterA == true){
			monsterA.Move();
			collisionMonsterA = false;
			CheckCollisionMonsterA();
		}
	}
	/**
	 * used to pack all the methods used by the monsterB movement
	 */
	public void MovementMonsterB(){
		monsterB.Move();
		CheckCollisionMonsterB();
		if (collisionMonsterB == true){
			monsterB.Move();
			collisionMonsterB = false;
			CheckCollisionMonsterB();
		}
	}
	/**
	 * used to pack all the methods used by the monsterC movement
	 */
	public void MovementMonsterC(){
		monsterC.Move();
		CheckCollisionMonsterC();
		if (collisionMonsterC == true){
			monsterC.Move();
			collisionMonsterC = false;
			CheckCollisionMonsterC();
		}
	}
	/**
	 * used to pack all the methods used by the monsterD movement
	 */
	public void MovementMonsterD(){
		monsterD.Move();
		CheckCollisionMonsterD();
		if (collisionMonsterD == true){
			monsterD.Move();
			collisionMonsterD = false;
			CheckCollisionMonsterD();
		}
	}
	/**
	 * method to choose the best direction to kill Lorann for the monsterD
	 */
	public void DirectionMonsterD(){
		shorter = Math.abs((lorann.getX()-(monsterD.getX()+16)))+Math.abs((lorann.getY()-(monsterD.getY()+0)));
		MonsterDDir = "RIGHT";
		
		if (shorter > Math.abs((lorann.getX()-(monsterD.getX()-16)))+Math.abs((lorann.getY()-(monsterD.getY()-16)))){
			shorter = Math.abs((lorann.getX()-(monsterD.getX()-16)))+Math.abs((lorann.getY()-(monsterD.getY()-16)));
			MonsterDDir = "DIAG UL";
		}
		else if (shorter > Math.abs((lorann.getX()-(monsterD.getX()+16)))+Math.abs((lorann.getY()-(monsterD.getY()-16)))){
			shorter = Math.abs((lorann.getX()-(monsterD.getX()+16)))+Math.abs((lorann.getY()-(monsterD.getY()-16)));
			MonsterDDir = "DIAG UR";
		}
		else if (shorter > Math.abs((lorann.getX()-(monsterD.getX()-16)))+Math.abs((lorann.getY()-(monsterD.getY()+16)))){
			shorter = Math.abs((lorann.getX()-(monsterD.getX()-16)))+Math.abs((lorann.getY()-(monsterD.getY()+16)));
			MonsterDDir = "DIAG DL";
		}
		else if (shorter > Math.abs((lorann.getX()-(monsterD.getX()+16)))+Math.abs((lorann.getY()-(monsterD.getY()+16)))){
			shorter = Math.abs((lorann.getX()-(monsterD.getX()+16)))+Math.abs((lorann.getY()-(monsterD.getY()+16)));
			MonsterDDir = "DIAG DR";
		}
		else if (shorter > Math.abs((lorann.getX()-(monsterD.getX()+0)))+Math.abs((lorann.getY()-(monsterD.getY()+16)))){
			shorter = Math.abs((lorann.getX()-(monsterD.getX()+0)))+Math.abs((lorann.getY()-(monsterD.getY()+16)));
			MonsterDDir = "DOWN";
		}
		else if (shorter > Math.abs((lorann.getX()-(monsterD.getX()-16)))+Math.abs((lorann.getY()-(monsterD.getY()+0)))){
			shorter = Math.abs((lorann.getX()-(monsterD.getX()-16)))+Math.abs((lorann.getY()-(monsterD.getY()+0)));
			MonsterDDir = "LEFT";
		}
		else if (shorter > Math.abs((lorann.getX()-(monsterD.getX()+0)))+Math.abs((lorann.getY()-(monsterD.getY()-16)))){
			shorter = Math.abs((lorann.getX()-(monsterD.getX()+0)))+Math.abs((lorann.getY()-(monsterD.getY()-16)));
			MonsterDDir = "UP";
		}	
	monsterD.setDir(MonsterDDir);
	}
	/**
	 * method to choose the best direction to kill Lorann for the monsterA
	 */
	public void DirectionMonsterA(){
		shorter = Math.abs((lorann.getX()-(monsterA.getX()+16)))+Math.abs((lorann.getY()-(monsterA.getY()+0)));
		MonsterADir = "RIGHT";
		
		if (shorter > Math.abs((lorann.getX()-(monsterA.getX()+0)))+Math.abs((lorann.getY()-(monsterA.getY()+16)))){
			shorter = Math.abs((lorann.getX()-(monsterA.getX()+0)))+Math.abs((lorann.getY()-(monsterA.getY()+16)));
			MonsterADir = "DOWN";
		}
		else if (shorter > Math.abs((lorann.getX()-(monsterA.getX()-16)))+Math.abs((lorann.getY()-(monsterA.getY()+0)))){
			shorter = Math.abs((lorann.getX()-(monsterA.getX()-16)))+Math.abs((lorann.getY()-(monsterA.getY()+0)));
			MonsterADir = "LEFT";
		}
		else if (shorter > Math.abs((lorann.getX()-(monsterA.getX()+0)))+Math.abs((lorann.getY()-(monsterA.getY()-16)))){
			shorter = Math.abs((lorann.getX()-(monsterA.getX()+0)))+Math.abs((lorann.getY()-(monsterA.getY()-16)));
			MonsterADir = "UP";
		}	
	monsterA.setDir(MonsterADir);
	}
	/**
	 * method to choose the best direction to kill Lorann for the monsterB
	 */
	public void DirectionMonsterB(){

		
		shorter = Math.abs((lorann.getX()-(monsterB.getX()-16)))+Math.abs((lorann.getY()-(monsterB.getY()-16)));
			MonsterDRir = "DIAG UL";
			
		if (shorter > Math.abs((lorann.getX()-(monsterB.getX()+16)))+Math.abs((lorann.getY()-(monsterB.getY()-16)))){
			shorter = Math.abs((lorann.getX()-(monsterB.getX()+16)))+Math.abs((lorann.getY()-(monsterB.getY()-16)));
			MonsterDRir = "DIAG UR";
		}
		else if (shorter > Math.abs((lorann.getX()-(monsterB.getX()-16)))+Math.abs((lorann.getY()-(monsterB.getY()+16)))){
			shorter = Math.abs((lorann.getX()-(monsterB.getX()-16)))+Math.abs((lorann.getY()-(monsterB.getY()+16)));
			MonsterDRir = "DIAG DL";
		}
		else if (shorter > Math.abs((lorann.getX()-(monsterB.getX()+16)))+Math.abs((lorann.getY()-(monsterB.getY()+16)))){
			shorter = Math.abs((lorann.getX()-(monsterB.getX()+16)))+Math.abs((lorann.getY()-(monsterB.getY()+16)));
			MonsterDRir = "DIAG DR";
		}
	monsterB.setDir(MonsterDRir);
	}
	/**
	 * method to choose the best direction to kill Lorann for the monsterC
	 */
	public void DirectionMonsterC(){
		shorter = Math.abs((lorann.getX()-(monsterC.getX()+16)))+Math.abs((lorann.getY()-(monsterC.getY()+0)));
		MonsterCDir = "RIGHT";
		
		if (shorter > Math.abs((lorann.getX()-(monsterC.getX()-16)))+Math.abs((lorann.getY()-(monsterC.getY()-16)))){
			shorter = Math.abs((lorann.getX()-(monsterC.getX()-16)))+Math.abs((lorann.getY()-(monsterC.getY()-16)));
			MonsterCDir = "DIAG UL";
		}
		else if (shorter > Math.abs((lorann.getX()-(monsterC.getX()+16)))+Math.abs((lorann.getY()-(monsterC.getY()-16)))){
			shorter = Math.abs((lorann.getX()-(monsterC.getX()+16)))+Math.abs((lorann.getY()-(monsterC.getY()-16)));
			MonsterCDir = "DIAG UR";
		}
		else if (shorter > Math.abs((lorann.getX()-(monsterC.getX()-16)))+Math.abs((lorann.getY()-(monsterC.getY()+16)))){
			shorter = Math.abs((lorann.getX()-(monsterC.getX()-16)))+Math.abs((lorann.getY()-(monsterC.getY()+16)));
			MonsterCDir = "DIAG DL";
		}
		else if (shorter > Math.abs((lorann.getX()-(monsterC.getX()+16)))+Math.abs((lorann.getY()-(monsterC.getY()+16)))){
			shorter = Math.abs((lorann.getX()-(monsterC.getX()+16)))+Math.abs((lorann.getY()-(monsterC.getY()+16)));
			MonsterCDir = "DIAG DR";
		}
		else if (shorter > Math.abs((lorann.getX()-(monsterC.getX()+0)))+Math.abs((lorann.getY()-(monsterC.getY()+16)))){
			shorter = Math.abs((lorann.getX()-(monsterC.getX()+0)))+Math.abs((lorann.getY()-(monsterC.getY()+16)));
			MonsterCDir = "DOWN";
		}
		else if (shorter > Math.abs((lorann.getX()-(monsterC.getX()-16)))+Math.abs((lorann.getY()-(monsterC.getY()+0)))){
			shorter = Math.abs((lorann.getX()-(monsterC.getX()-16)))+Math.abs((lorann.getY()-(monsterC.getY()+0)));
			MonsterCDir = "LEFT";
		}
		else if (shorter > Math.abs((lorann.getX()-(monsterC.getX()+0)))+Math.abs((lorann.getY()-(monsterC.getY()-16)))){
			shorter = Math.abs((lorann.getX()-(monsterC.getX()+0)))+Math.abs((lorann.getY()-(monsterC.getY()-16)));
			MonsterCDir = "UP";
		}	
	monsterC.setDir(MonsterCDir);
	}
	/**
	 * method th generate the map from a txt file and group elements by arraylists
	 */
	public void ChangeLevel(){
		monstreAAlive = false;
		monstreBAlive = false;
		monstreCAlive = false;
		monstreDAlive = false;
		try{
			fr = new FileReader("Maps/" + "salle" + "00" + level + ".txt");
			int x =0,y=0,i=0;

			HorizBones = new ArrayList<HorizBone>( );
			VerticBones = new ArrayList<VerticBone>();
			Stones = new ArrayList<Stone> ();
			Treasures = new ArrayList<Treasure>();
			balls = new ArrayList<ball>() ;
			MonstersA = new ArrayList<MonsterA>() ;
			MonstersB = new ArrayList<MonsterB>() ;
			MonstersC = new ArrayList<MonsterC>() ;
			MonstersD = new ArrayList<MonsterD>() ;
			Skulls = new ArrayList<Skull>();
			Opens = new ArrayList<Open>();
			Missiles = new ArrayList<Missile>();

			while ((i=fr.read()) != -1){
				char strImg = (char) i;

				if (strImg == '@'){
					Game [x][y] = "LORANN";
					lorann = new Lorann(x*16,y*16);
				}
				else if (strImg == 'Q'){
					Game [x][y] = "BALL";
					ball = new ball(x*16,y*16);
					balls.add(ball);
				}
				else if (strImg == 'O'){
					Game [x][y] = "STONE";
					stone = new Stone(x*16,y*16);
					Stones.add(stone);
				}
				else if (strImg == '-'){
					Game [x][y] = "HORIZBONE";
					horizBone = new HorizBone(x*16,y*16);
					HorizBones.add(horizBone);
				}
				else if (strImg == 'I'){
					Game [x][y] = "VERTICBONE";
					verticBone = new VerticBone(x*16,y*16);
					VerticBones.add(verticBone);
				}
				else if (strImg == '1'){
					Game [x][y] = "TREASURE";
					treasure = new Treasure(x*16,y*16);
					Treasures.add(treasure);
				}
				else if (strImg == 'A'){
					Game [x][y] = "MONSTERA";
					monsterA = new MonsterA(x*16,y*16);
					MonstersA.add(monsterA);
					monstreAAlive = true;
				}
				else if (strImg == 'B'){
					Game [x][y] = "MONSTERB";
					monsterB = new MonsterB(x*16,y*16);
					MonstersB.add(monsterB);
					monstreBAlive = true;
				}
				else if (strImg == 'C'){
					Game [x][y] = "MONSTERC";
					monsterC = new MonsterC(x*16,y*16);
					MonstersC.add(monsterC);
					monstreCAlive = true;
				}
				else if (strImg == 'D'){
					Game [x][y] = "MONSTERD";
					monsterD = new MonsterD(x*16,y*16);
					MonstersD.add(monsterD);
					monstreDAlive = true;
				}
				else if (strImg == 'Y'){
					Game [x][y] = "SKULL";
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
	public void ChangeLevel2(){
		try{
			fr = new FileReader("Maps/" + "salle" + "00" + level + ".txt");
			int x =0,y=0,i=0;

			Opens = new ArrayList<Open>();

			while ((i=fr.read()) != -1){
				char strImg = (char) i;

				if (strImg == 'Y'){
					Game [x][y] = "OPEN";
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
		for (int i =0; i< VerticBones.size(); i++){
			verticBone = (VerticBone) VerticBones.get(i);
			g2d.drawImage(verticBone.getImage(), verticBone.getX(), verticBone.getY(), null);
		}
		for (int i =0; i< HorizBones.size(); i++){
			horizBone = (HorizBone) HorizBones.get(i);
			g2d.drawImage(horizBone.getImage(), horizBone.getX(), horizBone.getY(), null);
		}
		for (int i =0; i< Treasures.size(); i++){
			treasure = (Treasure) Treasures.get(i);
			g2d.drawImage(treasure.getImage(), treasure.getX(), treasure.getY(), null);
		}
		for (int i =0; i< balls.size(); i++){
			ball = (ball) balls.get(i);
			g2d.drawImage(ball.getImage(), ball.getX(), ball.getY(), null);
		}
		for (int i =0; i< MonstersA.size(); i++){
			monsterA = (MonsterA) MonstersA.get(i);
			g2d.drawImage(monsterA.getImage(), monsterA.getX(), monsterA.getY(), null);
		}
		for (int i =0; i< MonstersB.size(); i++){
			monsterB = (MonsterB) MonstersB.get(i);
			g2d.drawImage(monsterB.getImage(), monsterB.getX(), monsterB.getY(), null);
		}
		for (int i =0; i< MonstersC.size(); i++){
			monsterC = (MonsterC) MonstersC.get(i);
			g2d.drawImage(monsterC.getImage(), monsterC.getX(), monsterC.getY(), null);
		}
		for (int i =0; i< MonstersD.size(); i++){
			monsterD = (MonsterD) MonstersD.get(i);
			g2d.drawImage(monsterD.getImage(), monsterD.getX(), monsterD.getY(), null);
		}
		for (int i =0; i< Skulls.size(); i++){
			skull = (Skull) Skulls.get(i);
			g2d.drawImage(skull.getImage(), skull.getX(), skull.getY(), null);
		}
		for (int i =0; i< Opens.size(); i++){
			open = (Open) Opens.get(i);
			g2d.drawImage(open.getImage(), open.getX(), open.getY(), null);
		}
		for (int i =0; i< Missiles.size(); i++){
			missile = (Missile) Missiles.get(i);
			g2d.drawImage(missile.getImage(), missile.getX(), missile.getY(), null);
		}
		g.setColor(Color.RED);
		g.setFont(levelFont);
		g.drawString("Score : " + score + " Life : " + Life + " <3", 100, 250);
		
		try{
			g2d.drawImage(lorann.getImage(), lorann.getX(), lorann.getY(), null);
			/*g2d.drawImage(missile.getImage(), missile.getX(), missile.getY(), null);*/
		}
		catch (Exception ex){}

	}
	/**
	 * method to check collision from "missile" to elements
	 */
	public void CheckCollisionMissile(){

		Rectangle missileRec;
		missileRec = missile.getBounds();

		for(int i=0;i<balls.size();i++){
			ball = (ball) balls.get(i);
			Rectangle murRec = ball.getBounds();

			if(missileRec.intersects(murRec)){
				if (missile.getDir() == "DOWN"){
					missile.setY(missile.getY() - 16 );
					RetourMissile(missile.getDir());
				}
				else if (missile.getDir() == "UP"){
					missile.setY(missile.getY() + 16 );
					RetourMissile(missile.getDir());

				}
				else if (missile.getDir() == "LEFT"){
					missile.setX(missile.getX() + 16 );
					RetourMissile(missile.getDir());
				}
				else if (missile.getDir() == "RIGHT"){
					missile.setX(missile.getX() - 16 );
					RetourMissile(missile.getDir());

				}
				else if (missile.getDir() == "DIAG DL"){
					missile.setX(missile.getX() + 16 );
					missile.setY(missile.getY() - 16 );
					RetourMissile(missile.getDir());

				}
				else if (missile.getDir() == "DIAG DR"){
					missile.setX(missile.getX() - 16 );
					missile.setY(missile.getY() - 16 );
					RetourMissile(missile.getDir());

				}
				else if (missile.getDir() == "DIAG UL"){
					missile.setX(missile.getX() + 16 );
					missile.setY(missile.getY() + 16 );
					RetourMissile(missile.getDir());

				}
				else if (missile.getDir() == "DIAG UR"){
					missile.setX(missile.getX() - 16 );
					missile.setY(missile.getY() + 16 );
					RetourMissile(missile.getDir());

				}
			}
		}
		for(int i=0;i<Stones.size();i++){
			stone = (Stone) Stones.get(i);
			Rectangle murRec = stone.getBounds();

			if(missileRec.intersects(murRec)){
				if (missile.getDir() == "DOWN"){
					missile.setY(missile.getY() - 16 );
					RetourMissile(missile.getDir());
				}
				else if (missile.getDir() == "UP"){
					missile.setY(missile.getY() + 16 );
					RetourMissile(missile.getDir());

				}
				else if (missile.getDir() == "LEFT"){
					missile.setX(missile.getX() + 16 );
					RetourMissile(missile.getDir());
				}
				else if (missile.getDir() == "RIGHT"){
					missile.setX(missile.getX() - 16 );
					RetourMissile(missile.getDir());

				}
				else if (missile.getDir() == "DIAG DL"){
					missile.setX(missile.getX() + 16 );
					missile.setY(missile.getY() - 16 );
					RetourMissile(missile.getDir());

				}
				else if (missile.getDir() == "DIAG DR"){
					missile.setX(missile.getX() - 16 );
					missile.setY(missile.getY() - 16 );
					RetourMissile(missile.getDir());

				}
				else if (missile.getDir() == "DIAG UL"){
					missile.setX(missile.getX() + 16 );
					missile.setY(missile.getY() + 16 );
					RetourMissile(missile.getDir());

				}
				else if (missile.getDir() == "DIAG UR"){
					missile.setX(missile.getX() - 16 );
					missile.setY(missile.getY() + 16 );
					RetourMissile(missile.getDir());

				}
			}
		}
		for(int i=0;i<HorizBones.size();i++){
			horizBone = (HorizBone) HorizBones.get(i);
			Rectangle murRec = horizBone.getBounds();

			if(missileRec.intersects(murRec)){
				if (missile.getDir() == "DOWN"){
					missile.setY(missile.getY() - 16 );
					RetourMissile(missile.getDir());
				}
				else if (missile.getDir() == "UP"){
					missile.setY(missile.getY() + 16 );
					RetourMissile(missile.getDir());

				}
				else if (missile.getDir() == "LEFT"){
					missile.setX(missile.getX() + 16 );
					RetourMissile(missile.getDir());
				}
				else if (missile.getDir() == "RIGHT"){
					missile.setX(missile.getX() - 16 );
					RetourMissile(missile.getDir());

				}
				else if (missile.getDir() == "DIAG DL"){
					missile.setX(missile.getX() + 16 );
					missile.setY(missile.getY() - 16 );
					RetourMissile(missile.getDir());

				}
				else if (missile.getDir() == "DIAG DR"){
					missile.setX(missile.getX() - 16 );
					missile.setY(missile.getY() - 16 );
					RetourMissile(missile.getDir());

				}
				else if (missile.getDir() == "DIAG UL"){
					missile.setX(missile.getX() + 16 );
					missile.setY(missile.getY() + 16 );
					RetourMissile(missile.getDir());

				}
				else if (missile.getDir() == "DIAG UR"){
					missile.setX(missile.getX() - 16 );
					missile.setY(missile.getY() + 16 );
					RetourMissile(missile.getDir());

				}
			}
		}
		for(int i=0;i<VerticBones.size();i++){
			verticBone = (VerticBone) VerticBones.get(i);
			Rectangle murRec = verticBone.getBounds();

			if(missileRec.intersects(murRec)){
				if (missile.getDir() == "DOWN"){
					missile.setY(missile.getY() - 16 );
					RetourMissile(missile.getDir());
				}
				else if (missile.getDir() == "UP"){
					missile.setY(missile.getY() + 16 );
					RetourMissile(missile.getDir());

				}
				else if (missile.getDir() == "LEFT"){
					missile.setX(missile.getX() + 16 );
					RetourMissile(missile.getDir());
				}
				else if (missile.getDir() == "RIGHT"){
					missile.setX(missile.getX() - 16 );
					RetourMissile(missile.getDir());

				}
				else if (missile.getDir() == "DIAG DL"){
					missile.setX(missile.getX() + 16 );
					missile.setY(missile.getY() - 16 );
					RetourMissile(missile.getDir());

				}
				else if (missile.getDir() == "DIAG DR"){
					missile.setX(missile.getX() - 16 );
					missile.setY(missile.getY() - 16 );
					RetourMissile(missile.getDir());

				}
				else if (missile.getDir() == "DIAG UL"){
					missile.setX(missile.getX() + 16 );
					missile.setY(missile.getY() + 16 );
					RetourMissile(missile.getDir());

				}
				else if (missile.getDir() == "DIAG UR"){
					missile.setX(missile.getX() - 16 );
					missile.setY(missile.getY() + 16 );
					RetourMissile(missile.getDir());

				}
			}
		}
		for(int i=0;i<Skulls.size();i++){
			skull = (Skull) Skulls.get(i);
			Rectangle murRec = skull.getBounds();

			if(missileRec.intersects(murRec)){
				if (missile.getDir() == "DOWN"){
					missile.setY(missile.getY() - 16 );
					RetourMissile(missile.getDir());
				}
				else if (missile.getDir() == "UP"){
					missile.setY(missile.getY() + 16 );
					RetourMissile(missile.getDir());

				}
				else if (missile.getDir() == "LEFT"){
					missile.setX(missile.getX() + 16 );
					RetourMissile(missile.getDir());
				}
				else if (missile.getDir() == "RIGHT"){
					missile.setX(missile.getX() - 16 );
					RetourMissile(missile.getDir());

				}
				else if (missile.getDir() == "DIAG DL"){
					missile.setX(missile.getX() + 16 );
					missile.setY(missile.getY() - 16 );
					RetourMissile(missile.getDir());

				}
				else if (missile.getDir() == "DIAG DR"){
					missile.setX(missile.getX() - 16 );
					missile.setY(missile.getY() - 16 );
					RetourMissile(missile.getDir());

				}
				else if (missile.getDir() == "DIAG UL"){
					missile.setX(missile.getX() + 16 );
					missile.setY(missile.getY() + 16 );
					RetourMissile(missile.getDir());

				}
				else if (missile.getDir() == "DIAG UR"){
					missile.setX(missile.getX() - 16 );
					missile.setY(missile.getY() + 16 );
					RetourMissile(missile.getDir());

				}
			}
		}
			Rectangle murRec = lorann.getBounds();

			if(missileRec.intersects(murRec)){
				if (missile.getDir() == "DOWN"){
					Missiles.remove(0);
					missilelaunched = false;
				}
				else if (missile.getDir() == "UP"){
					Missiles.remove(0);
					missilelaunched = false;

				}
				else if (missile.getDir() == "LEFT"){
					Missiles.remove(0);
					missilelaunched = false;
				}
				else if (missile.getDir() == "RIGHT"){
					Missiles.remove(0);
					missilelaunched = false;

				}
				else if (missile.getDir() == "DIAG DL"){
					Missiles.remove(0);
					missilelaunched = false;

				}
				else if (missile.getDir() == "DIAG DR"){
					Missiles.remove(0);
					missilelaunched = false;

				}
				else if (missile.getDir() == "DIAG UL"){
					Missiles.remove(0);
					missilelaunched = false;

				}
				else if (missile.getDir() == "DIAG UR"){
					Missiles.remove(0);
					missilelaunched = false;

				}
			}
			
	
			for(int i=0;i<MonstersD.size();i++){
				monsterD = (MonsterD) MonstersD.get(i);
				Rectangle murRec1 = monsterD.getBounds();

				if(missileRec.intersects(murRec1)){
					if (missile.getDir() == "DOWN"){
						MonstersD.remove(i);
						missilelaunched = false;
						Missiles.remove(0);
						monsterD.setX(20);
					}
					else if (missile.getDir() == "UP"){
						MonstersD.remove(i);
						missilelaunched = false;
						Missiles.remove(0);
						monsterD.setX(20);

					}
					else if (missile.getDir() == "LEFT"){
						MonstersD.remove(i);
						missilelaunched = false;
						Missiles.remove(0);
						monsterD.setX(20);
					}
					else if (missile.getDir() == "RIGHT"){
						MonstersD.remove(i);
						missilelaunched = false;
						Missiles.remove(0);
						monsterD.setX(20);

					}
					else if (missile.getDir() == "DIAG DL"){
						MonstersD.remove(i);
						missilelaunched = false;
						Missiles.remove(0);
						monsterD.setX(20);

					}
					else if (missile.getDir() == "DIAG DR"){
						MonstersD.remove(i);
						missilelaunched = false;
						Missiles.remove(0);
						monsterD.setX(20);

					}
					else if (missile.getDir() == "DIAG UL"){
						MonstersD.remove(i);
						missilelaunched = false;
						Missiles.remove(0);
						monsterD.setX(20);

					}
					else if (missile.getDir() == "DIAG UR"){
						MonstersD.remove(i);
						missilelaunched = false;
						Missiles.remove(0);
						monsterD.setX(20);

					}
				}
			}
				
		
	for(int i=0;i<MonstersA.size();i++){
		monsterA = (MonsterA) MonstersA.get(i);
		Rectangle murRec1 = monsterA.getBounds();

		if(missileRec.intersects(murRec1)){
			if (missile.getDir() == "DOWN"){
				MonstersA.remove(i);
				missilelaunched = false;
				Missiles.remove(0);
				monsterA.setX(20);
			}
			else if (missile.getDir() == "UP"){
				MonstersA.remove(i);
				missilelaunched = false;
				Missiles.remove(0);
				monsterA.setX(20);

			}
			else if (missile.getDir() == "LEFT"){
				MonstersA.remove(i);
				missilelaunched = false;
				Missiles.remove(0);
				monsterA.setX(20);
			}
			else if (missile.getDir() == "RIGHT"){
				MonstersA.remove(i);
				missilelaunched = false;
				Missiles.remove(0);
				monsterA.setX(20);

			}
			else if (missile.getDir() == "DIAG DL"){
				MonstersA.remove(i);
				missilelaunched = false;
				Missiles.remove(0);
				monsterA.setX(20);

			}
			else if (missile.getDir() == "DIAG DR"){
				MonstersA.remove(i);
				missilelaunched = false;
				Missiles.remove(0);
				monsterA.setX(20);

			}
			else if (missile.getDir() == "DIAG UL"){
				MonstersA.remove(i);
				missilelaunched = false;
				Missiles.remove(0);
				monsterA.setX(20);

			}
			else if (missile.getDir() == "DIAG UR"){
				MonstersA.remove(i);
				missilelaunched = false;
				Missiles.remove(0);
				monsterA.setX(20);

			}
		}
	}
		

	for(int i=0;i<MonstersB.size();i++){
		monsterB = (MonsterB) MonstersB.get(i);
		Rectangle murRec1 = monsterB.getBounds();

		if(missileRec.intersects(murRec1)){
			if (missile.getDir() == "DOWN"){
				MonstersB.remove(i);
				missilelaunched = false;
				Missiles.remove(0);
				monsterB.setX(20);
			}
			else if (missile.getDir() == "UP"){
				MonstersB.remove(i);
				missilelaunched = false;
				Missiles.remove(0);
				monsterB.setX(20);

			}
			else if (missile.getDir() == "LEFT"){
				MonstersB.remove(i);
				missilelaunched = false;
				Missiles.remove(0);
				monsterB.setX(20);
			}
			else if (missile.getDir() == "RIGHT"){
				MonstersB.remove(i);
				missilelaunched = false;
				Missiles.remove(0);
				monsterB.setX(20);

			}
			else if (missile.getDir() == "DIAG DL"){
				MonstersB.remove(i);
				missilelaunched = false;
				Missiles.remove(0);
				monsterB.setX(20);

			}
			else if (missile.getDir() == "DIAG DR"){
				MonstersB.remove(i);
				missilelaunched = false;
				Missiles.remove(0);
				monsterB.setX(20);

			}
			else if (missile.getDir() == "DIAG UL"){
				MonstersB.remove(i);
				missilelaunched = false;
				Missiles.remove(0);
				monsterB.setX(20);

			}
			else if (missile.getDir() == "DIAG UR"){
				MonstersB.remove(i);
				missilelaunched = false;
				Missiles.remove(0);
				monsterB.setX(20);

			}
		}
	}
	for(int i=0;i<MonstersC.size();i++){
		monsterC = (MonsterC) MonstersC.get(i);
		Rectangle murRec1 = monsterC.getBounds();

		if(missileRec.intersects(murRec1)){
			if (missile.getDir() == "DOWN"){
				MonstersC.remove(i);
				missilelaunched = false;
				Missiles.remove(0);
				monsterC.setX(20);
			}
			else if (missile.getDir() == "UP"){
				MonstersC.remove(i);
				missilelaunched = false;
				Missiles.remove(0);
				monsterC.setX(20);

			}
			else if (missile.getDir() == "LEFT"){
				MonstersC.remove(i);
				missilelaunched = false;
				Missiles.remove(0);
				monsterC.setX(20);
			}
			else if (missile.getDir() == "RIGHT"){
				MonstersC.remove(i);
				missilelaunched = false;
				Missiles.remove(0);
				monsterC.setX(20);

			}
			else if (missile.getDir() == "DIAG DL"){
				MonstersC.remove(i);
				missilelaunched = false;
				Missiles.remove(0);
				monsterC.setX(20);

			}
			else if (missile.getDir() == "DIAG DR"){
				MonstersC.remove(i);
				missilelaunched = false;
				Missiles.remove(0);
				monsterC.setX(20);

			}
			else if (missile.getDir() == "DIAG UL"){
				MonstersC.remove(i);
				missilelaunched = false;
				Missiles.remove(0);
				monsterC.setX(20);

			}
			else if (missile.getDir() == "DIAG UR"){
				MonstersC.remove(i);
				missilelaunched = false;
				Missiles.remove(0);
				monsterC.setX(20);

			}
		}
	}
		
}
	/**
	 * method to check collision from monsterD to elements
	 */
	public void CheckCollisionMonsterD(){

		Rectangle monstreDRec;
		monstreDRec = monsterD.getBounds();

		for(int i=0;i<Stones.size();i++){
			stone = (Stone) Stones.get(i);
			Rectangle murRec = stone.getBounds();

			if(monstreDRec.intersects(murRec)){
				if (monsterD.getDir() == "DOWN"){
					monsterD.setY(monsterD.getY() - 16 );
					DirectionMonsterD();
					collisionMonsterD = true;
				}
				else if (monsterD.getDir() == "UP"){
					monsterD.setY(monsterD.getY() + 16 );
					DirectionMonsterD();
					collisionMonsterD = true;
				}
				else if (monsterD.getDir() == "LEFT"){
					monsterD.setX(monsterD.getX() + 16 );
					DirectionMonsterD();
					collisionMonsterD = true;
				}
				else if (monsterD.getDir() == "RIGHT"){
					monsterD.setX(monsterD.getX() - 16 );
					DirectionMonsterD();
					collisionMonsterD = true;
				}
				else if (monsterD.getDir() == "DIAG DL"){
					monsterD.setX(monsterD.getX() + 16 );
					monsterD.setY(monsterD.getY() - 16 );
					DirectionMonsterD();
					collisionMonsterD = true;
				}
				else if (monsterD.getDir() == "DIAG DR"){
					monsterD.setX(monsterD.getX() - 16 );
					monsterD.setY(monsterD.getY() - 16 );
					DirectionMonsterD();
					collisionMonsterD = true;
				}
				else if (monsterD.getDir() == "DIAG UL"){
					monsterD.setX(monsterD.getX() + 16 );
					monsterD.setY(monsterD.getY() + 16 );
					DirectionMonsterD();
					collisionMonsterD = true;
				}
				else if (monsterD.getDir() == "DIAG UR"){
					monsterD.setX(monsterD.getX() - 16 );
					monsterD.setY(monsterD.getY() + 16 );
					DirectionMonsterD();
					collisionMonsterD = true;
				}
			}
		}
		for(int i=0;i<HorizBones.size();i++){
			horizBone = (HorizBone) HorizBones.get(i);
			Rectangle murRec = horizBone.getBounds();

			if(monstreDRec.intersects(murRec)){
				if (monsterD.getDir() == "DOWN"){
					monsterD.setY(monsterD.getY() - 16 );
					DirectionMonsterD();
					collisionMonsterD = true;
				}
				else if (monsterD.getDir() == "UP"){
					monsterD.setY(monsterD.getY() + 16 );
					DirectionMonsterD();
					collisionMonsterD = true;
				}
				else if (monsterD.getDir() == "LEFT"){
					monsterD.setX(monsterD.getX() + 16 );
					DirectionMonsterD();
					collisionMonsterD = true;
				}
				else if (monsterD.getDir() == "RIGHT"){
					monsterD.setX(monsterD.getX() - 16 );
					DirectionMonsterD();
					collisionMonsterD = true;
				}
				else if (monsterD.getDir() == "DIAG DL"){
					monsterD.setX(monsterD.getX() + 16 );
					monsterD.setY(monsterD.getY() - 16 );
					DirectionMonsterD();
					collisionMonsterD = true;
				}
				else if (monsterD.getDir() == "DIAG DR"){
					monsterD.setX(monsterD.getX() - 16 );
					monsterD.setY(monsterD.getY() - 16 );
					DirectionMonsterD();
					collisionMonsterD = true;
				}
				else if (monsterD.getDir() == "DIAG UL"){
					monsterD.setX(monsterD.getX() + 16 );
					monsterD.setY(monsterD.getY() + 16 );
					DirectionMonsterD();
					collisionMonsterD = true;
				}
				else if (monsterD.getDir() == "DIAG UR"){
					monsterD.setX(monsterD.getX() - 16 );
					monsterD.setY(monsterD.getY() + 16 );
					DirectionMonsterD();
					collisionMonsterD = true;
				}
			}
		}
		for(int i=0;i<balls.size();i++){
			ball = (ball) balls.get(i);
			Rectangle murRec = ball.getBounds();

			if(monstreDRec.intersects(murRec)){
				if (monsterD.getDir() == "DOWN"){
					monsterD.setY(monsterD.getY() - 16 );
					DirectionMonsterD();
					collisionMonsterD = true;
				}
				else if (monsterD.getDir() == "UP"){
					monsterD.setY(monsterD.getY() + 16 );
					DirectionMonsterD();
					collisionMonsterD = true;
				}
				else if (monsterD.getDir() == "LEFT"){
					monsterD.setX(monsterD.getX() + 16 );
					DirectionMonsterD();
					collisionMonsterD = true;
				}
				else if (monsterD.getDir() == "RIGHT"){
					monsterD.setX(monsterD.getX() - 16 );
					DirectionMonsterD();
					collisionMonsterD = true;
				}
				else if (monsterD.getDir() == "DIAG DL"){
					monsterD.setX(monsterD.getX() + 16 );
					monsterD.setY(monsterD.getY() - 16 );
					DirectionMonsterD();
					collisionMonsterD = true;
				}
				else if (monsterD.getDir() == "DIAG DR"){
					monsterD.setX(monsterD.getX() - 16 );
					monsterD.setY(monsterD.getY() - 16 );
					DirectionMonsterD();
					collisionMonsterD = true;
				}
				else if (monsterD.getDir() == "DIAG UL"){
					monsterD.setX(monsterD.getX() + 16 );
					monsterD.setY(monsterD.getY() + 16 );
					DirectionMonsterD();
					collisionMonsterD = true;
				}
				else if (monsterD.getDir() == "DIAG UR"){
					monsterD.setX(monsterD.getX() - 16 );
					monsterD.setY(monsterD.getY() + 16 );
					DirectionMonsterD();
					collisionMonsterD = true;
				}
			}
		}
		for(int i=0;i<VerticBones.size();i++){
			verticBone = (VerticBone) VerticBones.get(i);
			Rectangle murRec = verticBone.getBounds();

			if(monstreDRec.intersects(murRec)){
				if (monsterD.getDir() == "DOWN"){
					monsterD.setY(monsterD.getY() - 16 );
					DirectionMonsterD();
					collisionMonsterD = true;
				}
				else if (monsterD.getDir() == "UP"){
					monsterD.setY(monsterD.getY() + 16 );
					DirectionMonsterD();
					collisionMonsterD = true;
				}
				else if (monsterD.getDir() == "LEFT"){
					monsterD.setX(monsterD.getX() + 16 );
					DirectionMonsterD();
					collisionMonsterD = true;
				}
				else if (monsterD.getDir() == "RIGHT"){
					monsterD.setX(monsterD.getX() - 16 );
					DirectionMonsterD();
					collisionMonsterD = true;
				}
				else if (monsterD.getDir() == "DIAG DL"){
					monsterD.setX(monsterD.getX() + 16 );
					monsterD.setY(monsterD.getY() - 16 );
					DirectionMonsterD();
					collisionMonsterD = true;
				}
				else if (monsterD.getDir() == "DIAG DR"){
					monsterD.setX(monsterD.getX() - 16 );
					monsterD.setY(monsterD.getY() - 16 );
					DirectionMonsterD();
					collisionMonsterD = true;
				}
				else if (monsterD.getDir() == "DIAG UL"){
					monsterD.setX(monsterD.getX() + 16 );
					monsterD.setY(monsterD.getY() + 16 );
					DirectionMonsterD();
					collisionMonsterD = true;
				}
				else if (monsterD.getDir() == "DIAG UR"){
					monsterD.setX(monsterD.getX() - 16 );
					monsterD.setY(monsterD.getY() + 16 );
					DirectionMonsterD();
					collisionMonsterD = true;
				}
			}
		}
		for(int i=0;i<Skulls.size();i++){
			skull = (Skull) Skulls.get(i);
			Rectangle murRec = skull.getBounds();

			if(monstreDRec.intersects(murRec)){
				if (monsterD.getDir() == "DOWN"){
					monsterD.setY(monsterD.getY() - 16 );
					DirectionMonsterD();
					collisionMonsterD = true;
				}
				else if (monsterD.getDir() == "UP"){
					monsterD.setY(monsterD.getY() + 16 );
					DirectionMonsterD();
					collisionMonsterD = true;
				}
				else if (monsterD.getDir() == "LEFT"){
					monsterD.setX(monsterD.getX() + 16 );
					DirectionMonsterD();
					collisionMonsterD = true;
				}
				else if (monsterD.getDir() == "RIGHT"){
					monsterD.setX(monsterD.getX() - 16 );
					DirectionMonsterD();
					collisionMonsterD = true;
				}
				else if (monsterD.getDir() == "DIAG DL"){
					monsterD.setX(monsterD.getX() + 16 );
					monsterD.setY(monsterD.getY() - 16 );
					DirectionMonsterD();
					collisionMonsterD = true;
				}
				else if (monsterD.getDir() == "DIAG DR"){
					monsterD.setX(monsterD.getX() - 16 );
					monsterD.setY(monsterD.getY() - 16 );
					DirectionMonsterD();
					collisionMonsterD = true;
				}
				else if (monsterD.getDir() == "DIAG UL"){
					monsterD.setX(monsterD.getX() + 16 );
					monsterD.setY(monsterD.getY() + 16 );
					DirectionMonsterD();
					collisionMonsterD = true;
				}
				else if (monsterD.getDir() == "DIAG UR"){
					monsterD.setX(monsterD.getX() - 16 );
					monsterD.setY(monsterD.getY() + 16 );
					DirectionMonsterD();
					collisionMonsterD = true;
				}
			}
		}
			Rectangle murRec = lorann.getBounds();

			if(monstreDRec.intersects(murRec)){
				if (monsterD.getDir() == "DOWN"){
					ChangeLevel();
					Life--;
					
				}
				else if (monsterD.getDir() == "UP"){
					ChangeLevel();
					Life--;
				}
				else if (monsterD.getDir() == "LEFT"){
					ChangeLevel();
					Life--;
				}
				else if (monsterD.getDir() == "RIGHT"){
					ChangeLevel();
					Life--;
				}
				else if (monsterD.getDir() == "DIAG DL"){
					ChangeLevel();
					Life--;
				}
				else if (monsterD.getDir() == "DIAG DR"){
					ChangeLevel();
					Life--;
				}
				else if (monsterD.getDir() == "DIAG UL"){
					ChangeLevel();
					Life--;
				}
				else if (monsterD.getDir() == "DIAG UR"){
					ChangeLevel();
					Life--;
				}
			}
		}
	/**
	 * method to check collision from monsterA to elements
	 */
	public void CheckCollisionMonsterA(){

		Rectangle monstreARec;
		monstreARec = monsterA.getBounds();

		for(int i=0;i<Stones.size();i++){
			stone = (Stone) Stones.get(i);
			Rectangle murRec = stone.getBounds();

			if(monstreARec.intersects(murRec)){
				if (monsterA.getDir() == "DOWN"){
					monsterA.setY(monsterA.getY() - 16 );
					DirectionMonsterA();
					collisionMonsterA = true;
				}
				else if (monsterA.getDir() == "UP"){
					monsterA.setY(monsterA.getY() + 16 );
					DirectionMonsterA();
					collisionMonsterA = true;
				}
				else if (monsterA.getDir() == "LEFT"){
					monsterA.setX(monsterA.getX() + 16 );
					DirectionMonsterA();
					collisionMonsterA = true;
				}
				else if (monsterA.getDir() == "RIGHT"){
					monsterA.setX(monsterA.getX() - 16 );
					DirectionMonsterA();
					collisionMonsterA = true;
				}
				else if (monsterA.getDir() == "DIAG DL"){
					monsterA.setX(monsterA.getX() + 16 );
					monsterA.setY(monsterA.getY() - 16 );
					DirectionMonsterA();
					collisionMonsterA = true;
				}
				else if (monsterA.getDir() == "DIAG DR"){
					monsterA.setX(monsterA.getX() - 16 );
					monsterA.setY(monsterA.getY() - 16 );
					DirectionMonsterA();
					collisionMonsterA = true;
				}
				else if (monsterA.getDir() == "DIAG UL"){
					monsterA.setX(monsterA.getX() + 16 );
					monsterA.setY(monsterA.getY() + 16 );
					DirectionMonsterA();
					collisionMonsterA = true;
				}
				else if (monsterA.getDir() == "DIAG UR"){
					monsterA.setX(monsterA.getX() - 16 );
					monsterA.setY(monsterA.getY() + 16 );
					DirectionMonsterA();
					collisionMonsterA = true;
				}
			}
		}
		for(int i=0;i<HorizBones.size();i++){
			horizBone = (HorizBone) HorizBones.get(i);
			Rectangle murRec = horizBone.getBounds();

			if(monstreARec.intersects(murRec)){
				if (monsterA.getDir() == "DOWN"){
					monsterA.setY(monsterA.getY() - 16 );
					DirectionMonsterA();
					collisionMonsterA = true;
				}
				else if (monsterA.getDir() == "UP"){
					monsterA.setY(monsterA.getY() + 16 );
					DirectionMonsterA();
					collisionMonsterA = true;
				}
				else if (monsterA.getDir() == "LEFT"){
					monsterA.setX(monsterA.getX() + 16 );
					DirectionMonsterA();
					collisionMonsterA = true;
				}
				else if (monsterA.getDir() == "RIGHT"){
					monsterA.setX(monsterA.getX() - 16 );
					DirectionMonsterA();
					collisionMonsterA = true;
				}
				else if (monsterA.getDir() == "DIAG DL"){
					monsterA.setX(monsterA.getX() + 16 );
					monsterA.setY(monsterA.getY() - 16 );
					DirectionMonsterA();
					collisionMonsterA = true;
				}
				else if (monsterA.getDir() == "DIAG DR"){
					monsterA.setX(monsterA.getX() - 16 );
					monsterA.setY(monsterA.getY() - 16 );
					DirectionMonsterA();
					collisionMonsterA = true;
				}
				else if (monsterA.getDir() == "DIAG UL"){
					monsterA.setX(monsterA.getX() + 16 );
					monsterA.setY(monsterA.getY() + 16 );
					DirectionMonsterA();
					collisionMonsterA = true;
				}
				else if (monsterA.getDir() == "DIAG UR"){
					monsterA.setX(monsterA.getX() - 16 );
					monsterA.setY(monsterA.getY() + 16 );
					DirectionMonsterA();
					collisionMonsterA = true;
				}
			}
		}
		for(int i=0;i<balls.size();i++){
			ball = (ball) balls.get(i);
			Rectangle murRec = ball.getBounds();

			if(monstreARec.intersects(murRec)){
				if (monsterA.getDir() == "DOWN"){
					monsterA.setY(monsterA.getY() - 16 );
					DirectionMonsterA();
					collisionMonsterA = true;
				}
				else if (monsterA.getDir() == "UP"){
					monsterA.setY(monsterA.getY() + 16 );
					DirectionMonsterA();
					collisionMonsterA = true;
				}
				else if (monsterA.getDir() == "LEFT"){
					monsterA.setX(monsterA.getX() + 16 );
					DirectionMonsterA();
					collisionMonsterA = true;
				}
				else if (monsterA.getDir() == "RIGHT"){
					monsterA.setX(monsterA.getX() - 16 );
					DirectionMonsterA();
					collisionMonsterA = true;
				}
				else if (monsterA.getDir() == "DIAG DL"){
					monsterA.setX(monsterA.getX() + 16 );
					monsterA.setY(monsterA.getY() - 16 );
					DirectionMonsterA();
					collisionMonsterA = true;
				}
				else if (monsterA.getDir() == "DIAG DR"){
					monsterA.setX(monsterA.getX() - 16 );
					monsterA.setY(monsterA.getY() - 16 );
					DirectionMonsterA();
					collisionMonsterA = true;
				}
				else if (monsterA.getDir() == "DIAG UL"){
					monsterA.setX(monsterA.getX() + 16 );
					monsterA.setY(monsterA.getY() + 16 );
					DirectionMonsterA();
					collisionMonsterA = true;
				}
				else if (monsterA.getDir() == "DIAG UR"){
					monsterA.setX(monsterA.getX() - 16 );
					monsterA.setY(monsterA.getY() + 16 );
					DirectionMonsterA();
					collisionMonsterA = true;
				}
			}
		}
		for(int i=0;i<VerticBones.size();i++){
			verticBone = (VerticBone) VerticBones.get(i);
			Rectangle murRec = verticBone.getBounds();

			if(monstreARec.intersects(murRec)){
				if (monsterA.getDir() == "DOWN"){
					monsterA.setY(monsterA.getY() - 16 );
					DirectionMonsterA();
					collisionMonsterA = true;
				}
				else if (monsterA.getDir() == "UP"){
					monsterA.setY(monsterA.getY() + 16 );
					DirectionMonsterA();
					collisionMonsterA = true;
				}
				else if (monsterA.getDir() == "LEFT"){
					monsterA.setX(monsterA.getX() + 16 );
					DirectionMonsterA();
					collisionMonsterA = true;
				}
				else if (monsterA.getDir() == "RIGHT"){
					monsterA.setX(monsterA.getX() - 16 );
					DirectionMonsterA();
					collisionMonsterA = true;
				}
				else if (monsterA.getDir() == "DIAG DL"){
					monsterA.setX(monsterA.getX() + 16 );
					monsterA.setY(monsterA.getY() - 16 );
					DirectionMonsterA();
					collisionMonsterA = true;
				}
				else if (monsterA.getDir() == "DIAG DR"){
					monsterA.setX(monsterA.getX() - 16 );
					monsterA.setY(monsterA.getY() - 16 );
					DirectionMonsterA();
					collisionMonsterA = true;
				}
				else if (monsterA.getDir() == "DIAG UL"){
					monsterA.setX(monsterA.getX() + 16 );
					monsterA.setY(monsterA.getY() + 16 );
					DirectionMonsterA();
					collisionMonsterA = true;
				}
				else if (monsterA.getDir() == "DIAG UR"){
					monsterA.setX(monsterA.getX() - 16 );
					monsterA.setY(monsterA.getY() + 16 );
					DirectionMonsterA();
					collisionMonsterA = true;
				}
			}
		}
		for(int i=0;i<Skulls.size();i++){
			skull = (Skull) Skulls.get(i);
			Rectangle murRec = skull.getBounds();

			if(monstreARec.intersects(murRec)){
				if (monsterA.getDir() == "DOWN"){
					monsterA.setY(monsterA.getY() - 16 );
					DirectionMonsterA();
					collisionMonsterA = true;
				}
				else if (monsterA.getDir() == "UP"){
					monsterA.setY(monsterA.getY() + 16 );
					DirectionMonsterA();
					collisionMonsterA = true;
				}
				else if (monsterA.getDir() == "LEFT"){
					monsterA.setX(monsterA.getX() + 16 );
					DirectionMonsterA();
					collisionMonsterA = true;
				}
				else if (monsterA.getDir() == "RIGHT"){
					monsterA.setX(monsterA.getX() - 16 );
					DirectionMonsterA();
					collisionMonsterA = true;
				}
				else if (MonsterADir == "DIAG DL"){
					monsterA.setX(monsterA.getX() + 16 );
					monsterA.setY(monsterA.getY() - 16 );
					DirectionMonsterA();
					collisionMonsterA = true;
				}
				else if (MonsterADir == "DIAG DR"){
					monsterA.setX(monsterA.getX() - 16 );
					monsterA.setY(monsterA.getY() - 16 );
					DirectionMonsterA();
					collisionMonsterA = true;
				}
				else if (MonsterADir == "DIAG UL"){
					monsterA.setX(monsterA.getX() + 16 );
					monsterA.setY(monsterA.getY() + 16 );
					DirectionMonsterA();
					collisionMonsterA = true;
				}
				else if (MonsterADir == "DIAG UR"){
					monsterA.setX(monsterA.getX() - 16 );
					monsterA.setY(monsterA.getY() + 16 );
					DirectionMonsterA();
					collisionMonsterA = true;
				}
			}
		}
		for(int i=0;i<Missiles.size();i++){
			missile = (Missile) Missiles.get(i);
			Rectangle murRec = missile.getBounds();

			if(monstreARec.intersects(murRec)){
				if (monsterA.getDir() == "DOWN"){
					MonstersA.remove(0);
				}
				else if (monsterA.getDir() == "UP"){
					MonstersC.remove(0);
				}
				else if (monsterA.getDir() == "LEFT"){
					MonstersC.remove(0);
				}
				else if (monsterA.getDir() == "RIGHT"){
					MonstersC.remove(0);
				}
				else if (monsterA.getDir() == "DIAG DL"){
					MonstersC.remove(0);
				}
				else if (monsterA.getDir() == "DIAG DR"){
					MonstersC.remove(0);
				}
				else if (monsterA.getDir() == "DIAG UL"){
					MonstersC.remove(0);
				}
				else if (monsterA.getDir() == "DIAG UR"){
					MonstersC.remove(0);
				}
			}
		}
			Rectangle murRec = lorann.getBounds();

			if(monstreARec.intersects(murRec)){
				if (monsterA.getDir() == "DOWN"){
					ChangeLevel();
					Life--;
					
				}
				else if (monsterA.getDir() == "UP"){
					ChangeLevel();
					Life--;
				}
				else if (monsterA.getDir() == "LEFT"){
					ChangeLevel();
					Life--;
				}
				else if (monsterA.getDir() == "RIGHT"){
					ChangeLevel();
					Life--;
				}
				else if (monsterA.getDir() == "DIAG DL"){
					ChangeLevel();
					Life--;
				}
				else if (monsterA.getDir() == "DIAG DR"){
					ChangeLevel();
					Life--;
				}
				else if (monsterA.getDir() == "DIAG UL"){
					ChangeLevel();
					Life--;
				}
				else if (monsterA.getDir() == "DIAG UR"){
					ChangeLevel();
					Life--;
				}
			}
		}
	/**
	 * method to check collision from monsterB to elements
	 */
	public void CheckCollisionMonsterB(){

		Rectangle monstreBRec;
		monstreBRec = monsterB.getBounds();

		for(int i=0;i<Stones.size();i++){
			stone = (Stone) Stones.get(i);
			Rectangle murRec = stone.getBounds();

			if(monstreBRec.intersects(murRec)){
				if (monsterB.getDir() == "DOWN"){
					monsterB.setY(monsterB.getY() - 16 );
					DirectionMonsterB();
					collisionMonsterB = true;
				}
				else if (monsterB.getDir() == "UP"){
					monsterB.setY(monsterB.getY() + 16 );
					DirectionMonsterB();
					collisionMonsterB = true;
				}
				else if (monsterB.getDir() == "LEFT"){
					monsterB.setX(monsterB.getX() + 16 );
					DirectionMonsterB();
					collisionMonsterB = true;
				}
				else if (monsterB.getDir() == "RIGHT"){
					monsterB.setX(monsterB.getX() - 16 );
					DirectionMonsterB();
					collisionMonsterB = true;
				}
				else if (monsterB.getDir() == "DIAG DL"){
					monsterB.setX(monsterB.getX() + 16 );
					monsterB.setY(monsterB.getY() - 16 );
					DirectionMonsterB();
					collisionMonsterB = true;
				}
				else if (monsterB.getDir() == "DIAG DR"){
					monsterB.setX(monsterB.getX() - 16 );
					monsterB.setY(monsterB.getY() - 16 );
					DirectionMonsterB();
					collisionMonsterB = true;
				}
				else if (monsterB.getDir() == "DIAG UL"){
					monsterB.setX(monsterB.getX() + 16 );
					monsterB.setY(monsterB.getY() + 16 );
					DirectionMonsterB();
					collisionMonsterB = true;
				}
				else if (monsterB.getDir() == "DIAG UR"){
					monsterB.setX(monsterB.getX() - 16 );
					monsterB.setY(monsterB.getY() + 16 );
					DirectionMonsterB();
					collisionMonsterB = true;
				}
			}
		}
		for(int i=0;i<HorizBones.size();i++){
			horizBone = (HorizBone) HorizBones.get(i);
			Rectangle murRec = horizBone.getBounds();

			if(monstreBRec.intersects(murRec)){
				if (monsterB.getDir() == "DOWN"){
					monsterB.setY(monsterB.getY() - 16 );
					DirectionMonsterB();
					collisionMonsterB = true;
				}
				else if (monsterB.getDir() == "UP"){
					monsterB.setY(monsterB.getY() + 16 );
					DirectionMonsterB();
					collisionMonsterB = true;
				}
				else if (monsterB.getDir() == "LEFT"){
					monsterB.setX(monsterB.getX() + 16 );
					DirectionMonsterB();
					collisionMonsterB = true;
				}
				else if (monsterB.getDir() == "RIGHT"){
					monsterB.setX(monsterB.getX() - 16 );
					DirectionMonsterB();
					collisionMonsterB = true;
				}
				else if (monsterB.getDir() == "DIAG DL"){
					monsterB.setX(monsterB.getX() + 16 );
					monsterB.setY(monsterB.getY() - 16 );
					DirectionMonsterB();
					collisionMonsterB = true;
				}
				else if (monsterB.getDir() == "DIAG DR"){
					monsterB.setX(monsterB.getX() - 16 );
					monsterB.setY(monsterB.getY() - 16 );
					DirectionMonsterB();
					collisionMonsterB = true;
				}
				else if (monsterB.getDir() == "DIAG UL"){
					monsterB.setX(monsterB.getX() + 16 );
					monsterB.setY(monsterB.getY() + 16 );
					DirectionMonsterB();
					collisionMonsterB = true;
				}
				else if (monsterB.getDir() == "DIAG UR"){
					monsterB.setX(monsterB.getX() - 16 );
					monsterB.setY(monsterB.getY() + 16 );
					DirectionMonsterB();
					collisionMonsterB = true;
				}
			}
		}
		for(int i=0;i<balls.size();i++){
			ball = (ball) balls.get(i);
			Rectangle murRec = ball.getBounds();

			if(monstreBRec.intersects(murRec)){
				if (monsterB.getDir() == "DOWN"){
					monsterB.setY(monsterB.getY() - 16 );
					DirectionMonsterB();
					collisionMonsterB = true;
				}
				else if (monsterB.getDir() == "UP"){
					monsterB.setY(monsterB.getY() + 16 );
					DirectionMonsterB();
					collisionMonsterB = true;
				}
				else if (monsterB.getDir() == "LEFT"){
					monsterB.setX(monsterB.getX() + 16 );
					DirectionMonsterB();
					collisionMonsterB = true;
				}
				else if (monsterB.getDir() == "RIGHT"){
					monsterB.setX(monsterB.getX() - 16 );
					DirectionMonsterB();
					collisionMonsterB = true;
				}
				else if (monsterB.getDir() == "DIAG DL"){
					monsterB.setX(monsterB.getX() + 16 );
					monsterB.setY(monsterB.getY() - 16 );
					DirectionMonsterB();
					collisionMonsterB = true;
				}
				else if (monsterB.getDir() == "DIAG DR"){
					monsterB.setX(monsterB.getX() - 16 );
					monsterB.setY(monsterB.getY() - 16 );
					DirectionMonsterB();
					collisionMonsterB = true;
				}
				else if (monsterB.getDir() == "DIAG UL"){
					monsterB.setX(monsterB.getX() + 16 );
					monsterB.setY(monsterB.getY() + 16 );
					DirectionMonsterB();
					collisionMonsterB = true;
				}
				else if (monsterB.getDir() == "DIAG UR"){
					monsterB.setX(monsterB.getX() - 16 );
					monsterB.setY(monsterB.getY() + 16 );
					DirectionMonsterB();
					collisionMonsterB = true;
				}
			}
		}
		for(int i=0;i<VerticBones.size();i++){
			verticBone = (VerticBone) VerticBones.get(i);
			Rectangle murRec = verticBone.getBounds();

			if(monstreBRec.intersects(murRec)){
				if (monsterB.getDir() == "DOWN"){
					monsterB.setY(monsterB.getY() - 16 );
					DirectionMonsterB();
					collisionMonsterB = true;
				}
				else if (monsterB.getDir() == "UP"){
					monsterB.setY(monsterB.getY() + 16 );
					DirectionMonsterB();
					collisionMonsterB = true;
				}
				else if (monsterB.getDir() == "LEFT"){
					monsterB.setX(monsterB.getX() + 16 );
					DirectionMonsterB();
					collisionMonsterB = true;
				}
				else if (monsterB.getDir() == "RIGHT"){
					monsterB.setX(monsterB.getX() - 16 );
					DirectionMonsterB();
					collisionMonsterB = true;
				}
				else if (monsterB.getDir() == "DIAG DL"){
					monsterB.setX(monsterB.getX() + 16 );
					monsterB.setY(monsterB.getY() - 16 );
					DirectionMonsterB();
					collisionMonsterB = true;
				}
				else if (monsterB.getDir() == "DIAG DR"){
					monsterB.setX(monsterB.getX() - 16 );
					monsterB.setY(monsterB.getY() - 16 );
					DirectionMonsterB();
					collisionMonsterB = true;
				}
				else if (monsterB.getDir() == "DIAG UL"){
					monsterB.setX(monsterB.getX() + 16 );
					monsterB.setY(monsterB.getY() + 16 );
					DirectionMonsterB();
					collisionMonsterB = true;
				}
				else if (monsterB.getDir() == "DIAG UR"){
					monsterB.setX(monsterB.getX() - 16 );
					monsterB.setY(monsterB.getY() + 16 );
					DirectionMonsterB();
					collisionMonsterB = true;
				}
			}
		}
		for(int i=0;i<Skulls.size();i++){
			skull = (Skull) Skulls.get(i);
			Rectangle murRec = skull.getBounds();

			if(monstreBRec.intersects(murRec)){
				if (monsterB.getDir() == "DOWN"){
					monsterB.setY(monsterB.getY() - 16 );
					DirectionMonsterB();
					collisionMonsterB = true;
				}
				else if (monsterB.getDir() == "UP"){
					monsterB.setY(monsterB.getY() + 16 );
					DirectionMonsterB();
					collisionMonsterB = true;
				}
				else if (monsterB.getDir() == "LEFT"){
					monsterB.setX(monsterB.getX() + 16 );
					DirectionMonsterB();
					collisionMonsterB = true;
				}
				else if (monsterB.getDir() == "RIGHT"){
					monsterB.setX(monsterB.getX() - 16 );
					DirectionMonsterB();
					collisionMonsterB = true;
				}
				else if (monsterB.getDir() == "DIAG DL"){
					monsterB.setX(monsterB.getX() + 16 );
					monsterB.setY(monsterB.getY() - 16 );
					DirectionMonsterB();
					collisionMonsterB = true;
				}
				else if (monsterB.getDir() == "DIAG DR"){
					monsterB.setX(monsterB.getX() - 16 );
					monsterB.setY(monsterB.getY() - 16 );
					DirectionMonsterB();
					collisionMonsterB = true;
				}
				else if (monsterB.getDir() == "DIAG UL"){
					monsterB.setX(monsterB.getX() + 16 );
					monsterB.setY(monsterB.getY() + 16 );
					DirectionMonsterB();
					collisionMonsterB = true;
				}
				else if (monsterB.getDir() == "DIAG UR"){
					monsterB.setX(monsterB.getX() - 16 );
					monsterB.setY(monsterB.getY() + 16 );
					DirectionMonsterB();
					collisionMonsterB = true;
				}
			}
		}
			Rectangle murRec = lorann.getBounds();

			if(monstreBRec.intersects(murRec)){
				if (monsterB.getDir() == "DOWN"){
					ChangeLevel();
					Life--;
					
				}
				else if (monsterB.getDir() == "UP"){
					ChangeLevel();
					Life--;
				}
				else if (monsterB.getDir() == "LEFT"){
					ChangeLevel();
					Life--;
				}
				else if (monsterB.getDir() == "RIGHT"){
					ChangeLevel();
					Life--;
				}
				else if (monsterB.getDir() == "DIAG DL"){
					ChangeLevel();
					Life--;
				}
				else if (monsterB.getDir() == "DIAG DR"){
					ChangeLevel();
					Life--;
				}
				else if (monsterB.getDir() == "DIAG UL"){
					ChangeLevel();
					Life--;
				}
				else if (monsterB.getDir() == "DIAG UR"){
					ChangeLevel();
					Life--;
				}
			}
		}
	/**
	 * method to check collision from monsterC to elements
	 */
	public void CheckCollisionMonsterC(){

		Rectangle monstreCRec;
		monstreCRec = monsterC.getBounds();

		for(int i=0;i<Stones.size();i++){
			stone = (Stone) Stones.get(i);
			Rectangle murRec = stone.getBounds();

			if(monstreCRec.intersects(murRec)){
				if (monsterC.getDir() == "DOWN"){
					monsterC.setY(monsterC.getY() - 16 );
					DirectionMonsterC();
					collisionMonsterC = true;
				}
				else if (monsterC.getDir() == "UP"){
					monsterC.setY(monsterC.getY() + 16 );
					DirectionMonsterC();
					collisionMonsterC = true;
				}
				else if (monsterC.getDir() == "LEFT"){
					monsterC.setX(monsterC.getX() + 16 );
					DirectionMonsterC();
					collisionMonsterC = true;
				}
				else if (monsterC.getDir() == "RIGHT"){
					monsterC.setX(monsterC.getX() - 16 );
					DirectionMonsterC();
					collisionMonsterC = true;
				}
				else if (monsterC.getDir() == "DIAG DL"){
					monsterC.setX(monsterC.getX() + 16 );
					monsterC.setY(monsterC.getY() - 16 );
					DirectionMonsterC();
					collisionMonsterC = true;
				}
				else if (monsterC.getDir() == "DIAG DR"){
					monsterC.setX(monsterC.getX() - 16 );
					monsterC.setY(monsterC.getY() - 16 );
					DirectionMonsterC();
					collisionMonsterC = true;
				}
				else if (monsterC.getDir() == "DIAG UL"){
					monsterC.setX(monsterC.getX() + 16 );
					monsterC.setY(monsterC.getY() + 16 );
					DirectionMonsterC();
					collisionMonsterC = true;
				}
				else if (monsterC.getDir() == "DIAG UR"){
					monsterC.setX(monsterC.getX() - 16 );
					monsterC.setY(monsterC.getY() + 16 );
					DirectionMonsterC();
					collisionMonsterC = true;
				}
			}
		}
		for(int i=0;i<HorizBones.size();i++){
			horizBone = (HorizBone) HorizBones.get(i);
			Rectangle murRec = horizBone.getBounds();

			if(monstreCRec.intersects(murRec)){
				if (monsterC.getDir() == "DOWN"){
					monsterC.setY(monsterC.getY() - 16 );
					DirectionMonsterC();
					collisionMonsterC = true;
				}
				else if (monsterC.getDir() == "UP"){
					monsterC.setY(monsterC.getY() + 16 );
					DirectionMonsterC();
					collisionMonsterC = true;
				}
				else if (monsterC.getDir() == "LEFT"){
					monsterC.setX(monsterC.getX() + 16 );
					DirectionMonsterC();
					collisionMonsterC = true;
				}
				else if (monsterC.getDir() == "RIGHT"){
					monsterC.setX(monsterC.getX() - 16 );
					DirectionMonsterC();
					collisionMonsterC = true;
				}
				else if (monsterC.getDir() == "DIAG DL"){
					monsterC.setX(monsterC.getX() + 16 );
					monsterC.setY(monsterC.getY() - 16 );
					DirectionMonsterC();
					collisionMonsterC = true;
				}
				else if (monsterC.getDir() == "DIAG DR"){
					monsterC.setX(monsterC.getX() - 16 );
					monsterC.setY(monsterC.getY() - 16 );
					DirectionMonsterC();
					collisionMonsterC = true;
				}
				else if (monsterC.getDir() == "DIAG UL"){
					monsterC.setX(monsterC.getX() + 16 );
					monsterC.setY(monsterC.getY() + 16 );
					DirectionMonsterC();
					collisionMonsterC = true;
				}
				else if (monsterC.getDir() == "DIAG UR"){
					monsterC.setX(monsterC.getX() - 16 );
					monsterC.setY(monsterC.getY() + 16 );
					DirectionMonsterC();
					collisionMonsterC = true;
				}
			}
		}
		for(int i=0;i<balls.size();i++){
			ball = (ball) balls.get(i);
			Rectangle murRec = ball.getBounds();

			if(monstreCRec.intersects(murRec)){
				if (monsterC.getDir() == "DOWN"){
					monsterC.setY(monsterC.getY() - 16 );
					DirectionMonsterC();
					collisionMonsterC = true;
				}
				else if (monsterC.getDir() == "UP"){
					monsterC.setY(monsterC.getY() + 16 );
					DirectionMonsterC();
					collisionMonsterC = true;
				}
				else if (monsterC.getDir() == "LEFT"){
					monsterC.setX(monsterC.getX() + 16 );
					DirectionMonsterC();
					collisionMonsterC = true;
				}
				else if (monsterC.getDir() == "RIGHT"){
					monsterC.setX(monsterC.getX() - 16 );
					DirectionMonsterC();
					collisionMonsterC = true;
				}
				else if (monsterC.getDir() == "DIAG DL"){
					monsterC.setX(monsterC.getX() + 16 );
					monsterC.setY(monsterC.getY() - 16 );
					DirectionMonsterC();
					collisionMonsterC = true;
				}
				else if (monsterC.getDir() == "DIAG DR"){
					monsterC.setX(monsterC.getX() - 16 );
					monsterC.setY(monsterC.getY() - 16 );
					DirectionMonsterC();
					collisionMonsterC = true;
				}
				else if (monsterC.getDir() == "DIAG UL"){
					monsterC.setX(monsterC.getX() + 16 );
					monsterC.setY(monsterC.getY() + 16 );
					DirectionMonsterC();
					collisionMonsterC = true;
				}
				else if (monsterC.getDir() == "DIAG UR"){
					monsterC.setX(monsterC.getX() - 16 );
					monsterC.setY(monsterC.getY() + 16 );
					DirectionMonsterC();
					collisionMonsterC = true;
				}
			}
		}
		for(int i=0;i<VerticBones.size();i++){
			verticBone = (VerticBone) VerticBones.get(i);
			Rectangle murRec = verticBone.getBounds();

			if(monstreCRec.intersects(murRec)){
				if (monsterC.getDir() == "DOWN"){
					monsterC.setY(monsterC.getY() - 16 );
					DirectionMonsterC();
					collisionMonsterC = true;
				}
				else if (monsterC.getDir() == "UP"){
					monsterC.setY(monsterC.getY() + 16 );
					DirectionMonsterC();
					collisionMonsterC = true;
				}
				else if (monsterC.getDir() == "LEFT"){
					monsterC.setX(monsterC.getX() + 16 );
					DirectionMonsterC();
					collisionMonsterC = true;
				}
				else if (monsterC.getDir() == "RIGHT"){
					monsterC.setX(monsterC.getX() - 16 );
					DirectionMonsterC();
					collisionMonsterC = true;
				}
				else if (monsterC.getDir() == "DIAG DL"){
					monsterC.setX(monsterC.getX() + 16 );
					monsterC.setY(monsterC.getY() - 16 );
					DirectionMonsterC();
					collisionMonsterC = true;
				}
				else if (monsterC.getDir() == "DIAG DR"){
					monsterC.setX(monsterC.getX() - 16 );
					monsterC.setY(monsterC.getY() - 16 );
					DirectionMonsterC();
					collisionMonsterC = true;
				}
				else if (monsterC.getDir() == "DIAG UL"){
					monsterC.setX(monsterC.getX() + 16 );
					monsterC.setY(monsterC.getY() + 16 );
					DirectionMonsterC();
					collisionMonsterC = true;
				}
				else if (monsterC.getDir() == "DIAG UR"){
					monsterC.setX(monsterC.getX() - 16 );
					monsterC.setY(monsterC.getY() + 16 );
					DirectionMonsterC();
					collisionMonsterC = true;
				}
			}
		}
		for(int i=0;i<Skulls.size();i++){
			skull = (Skull) Skulls.get(i);
			Rectangle murRec = skull.getBounds();

			if(monstreCRec.intersects(murRec)){
				if (monsterC.getDir() == "DOWN"){
					monsterC.setY(monsterC.getY() - 16 );
					DirectionMonsterC();
					collisionMonsterC = true;
				}
				else if (monsterC.getDir() == "UP"){
					monsterC.setY(monsterC.getY() + 16 );
					DirectionMonsterC();
					collisionMonsterC = true;
				}
				else if (monsterC.getDir() == "LEFT"){
					monsterC.setX(monsterC.getX() + 16 );
					DirectionMonsterC();
					collisionMonsterC = true;
				}
				else if (monsterC.getDir() == "RIGHT"){
					monsterC.setX(monsterC.getX() - 16 );
					DirectionMonsterC();
					collisionMonsterC = true;
				}
				else if (monsterC.getDir() == "DIAG DL"){
					monsterC.setX(monsterC.getX() + 16 );
					monsterC.setY(monsterC.getY() - 16 );
					DirectionMonsterC();
					collisionMonsterC = true;
				}
				else if (monsterC.getDir() == "DIAG DR"){
					monsterC.setX(monsterC.getX() - 16 );
					monsterC.setY(monsterC.getY() - 16 );
					DirectionMonsterC();
					collisionMonsterC = true;
				}
				else if (monsterC.getDir() == "DIAG UL"){
					monsterC.setX(monsterC.getX() + 16 );
					monsterC.setY(monsterC.getY() + 16 );
					DirectionMonsterC();
					collisionMonsterC = true;
				}
				else if (monsterC.getDir() == "DIAG UR"){
					monsterC.setX(monsterC.getX() - 16 );
					monsterC.setY(monsterC.getY() + 16 );
					DirectionMonsterC();
					collisionMonsterC = true;
				}
			}
		}
		for(int i=0;i<Missiles.size();i++){
			missile = (Missile) Missiles.get(i);
			Rectangle murRec = missile.getBounds();

			if(monstreCRec.intersects(murRec)){
				if (monsterC.getDir() == "DOWN"){
					MonstersC.remove(0);
				}
				else if (monsterC.getDir() == "UP"){
					MonstersC.remove(0);
				}
				else if (monsterC.getDir() == "LEFT"){
					MonstersC.remove(0);
				}
				else if (monsterC.getDir() == "RIGHT"){
					MonstersC.remove(0);
				}
				else if (monsterC.getDir() == "DIAG DL"){
					MonstersC.remove(0);
				}
				else if (monsterC.getDir() == "DIAG DR"){
					MonstersC.remove(0);
				}
				else if (monsterC.getDir() == "DIAG UL"){
					MonstersC.remove(0);
				}
				else if (monsterC.getDir() == "DIAG UR"){
					MonstersC.remove(0);
				}
			}
		}
			Rectangle murRec = lorann.getBounds();

			if(monstreCRec.intersects(murRec)){
				if (monsterC.getDir() == "DOWN"){
					ChangeLevel();
					Life--;
					
				}
				else if (monsterC.getDir() == "UP"){
					ChangeLevel();
					Life--;
				}
				else if (monsterC.getDir() == "LEFT"){
					ChangeLevel();
					Life--;
				}
				else if (monsterC.getDir() == "RIGHT"){
					ChangeLevel();
					Life--;
				}
				else if (monsterC.getDir() == "DIAG DL"){
					ChangeLevel();
					Life--;
				}
				else if (monsterC.getDir() == "DIAG DR"){
					ChangeLevel();
					Life--;
				}
				else if (monsterC.getDir() == "DIAG UL"){
					ChangeLevel();
					Life--;
				}
				else if (monsterC.getDir() == "DIAG UR"){
					ChangeLevel();
					Life--;
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
				if (lorann.getDir() == "DOWN"){
					lorann.setY(lorann.getY() - 16 );
				}
				else if (lorann.getDir() == "UP"){
					lorann.setY(lorann.getY() + 16 );
				}
				else if (lorann.getDir() == "LEFT"){
					lorann.setX(lorann.getX() + 16 );
				}
				else if (lorann.getDir() == "RIGHT"){
					lorann.setX(lorann.getX() - 16 );
				}
				else if (lorann.getDir() == "DIAG UL"){
					lorann.setX(lorann.getX() + 16 );
					lorann.setY(lorann.getY() + 16 );
				}
				else if (lorann.getDir() == "DIAG UR"){
					lorann.setX(lorann.getX() - 16 );
					lorann.setY(lorann.getY() + 16 );
				}
				else if (lorann.getDir() == "DIAG DL"){
					lorann.setX(lorann.getX() + 16 );
					lorann.setY(lorann.getY() - 16 );
				}
				else if (lorann.getDir() == "DIAG DR"){
					lorann.setX(lorann.getX() - 16 );
					lorann.setY(lorann.getY() - 16 );
				}
			}
		}
		for(int i=0;i<HorizBones.size();i++){
			horizBone = (HorizBone) HorizBones.get(i);
			Rectangle horizosRec = horizBone.getBounds();

			if(lorannRec.intersects(horizosRec)){
				if (lorann.getDir() == "DOWN"){
					lorann.setY(lorann.getY() - 16 );
				}
				else if (lorann.getDir() == "UP"){
					lorann.setY(lorann.getY() + 16 );
				}
				else if (lorann.getDir() == "LEFT"){
					lorann.setX(lorann.getX() + 16 );
				}
				else if (lorann.getDir() == "RIGHT"){
					lorann.setX(lorann.getX() - 16 );
				}
				else if (lorann.getDir() == "DIAG UL"){
					lorann.setX(lorann.getX() + 16 );
					lorann.setY(lorann.getY() + 16 );
				}
				else if (lorann.getDir() == "DIAG UR"){
					lorann.setX(lorann.getX() - 16 );
					lorann.setY(lorann.getY() + 16 );
				}
				else if (lorann.getDir() == "DIAG DL"){
					lorann.setX(lorann.getX() + 16 );
					lorann.setY(lorann.getY() - 16 );
				}
				else if (lorann.getDir() == "DIAG DR"){
					lorann.setX(lorann.getX() - 16 );
					lorann.setY(lorann.getY() - 16 );
				}
			}
	}
		for(int i=0;i<VerticBones.size();i++){
			verticBone = (VerticBone) VerticBones.get(i);
			Rectangle verticosRec = verticBone.getBounds();

			if(lorannRec.intersects(verticosRec)){
				if (lorann.getDir() == "DOWN"){
					lorann.setY(lorann.getY() - 16 );
				}
				else if (lorann.getDir() == "UP"){
					lorann.setY(lorann.getY() + 16 );
				}
				else if (lorann.getDir() == "LEFT"){
					lorann.setX(lorann.getX() + 16 );
				}
				else if (lorann.getDir() == "RIGHT"){
					lorann.setX(lorann.getX() - 16 );
				}
				else if (lorann.getDir() == "DIAG UL"){
					lorann.setX(lorann.getX() + 16 );
					lorann.setY(lorann.getY() + 16 );
				}
				else if (lorann.getDir() == "DIAG UR"){
					lorann.setX(lorann.getX() - 16 );
					lorann.setY(lorann.getY() + 16 );
				}
				else if (lorann.getDir() == "DIAG DL"){
					lorann.setX(lorann.getX() + 16 );
					lorann.setY(lorann.getY() - 16 );
				}
				else if (lorann.getDir() == "DIAG DR"){
					lorann.setX(lorann.getX() - 16 );
					lorann.setY(lorann.getY() - 16 );
				}
			}
	}
		for(int i=0;i<Skulls.size();i++){
			skull = (Skull) Skulls.get(i);
			Rectangle craneRec = skull.getBounds();

			if(lorannRec.intersects(craneRec)){
				if (lorann.getDir() == "DOWN"){
					lorann.setY(lorann.getY() - 16 );
				}
				else if (lorann.getDir() == "UP"){
					lorann.setY(lorann.getY() + 16 );
				}
				else if (lorann.getDir() == "LEFT"){
					lorann.setX(lorann.getX() + 16 );
				}
				else if (lorann.getDir() == "RIGHT"){
					lorann.setX(lorann.getX() - 16 );
				}
				else if (lorann.getDir() == "DIAG UL"){
					lorann.setX(lorann.getX() + 16 );
					lorann.setY(lorann.getY() + 16 );
				}
				else if (lorann.getDir() == "DIAG UR"){
					lorann.setX(lorann.getX() - 16 );
					lorann.setY(lorann.getY() + 16 );
				}
				else if (lorann.getDir() == "DIAG DL"){
					lorann.setX(lorann.getX() + 16 );
					lorann.setY(lorann.getY() - 16 );
				}
				else if (lorann.getDir() == "DIAG DR"){
					lorann.setX(lorann.getX() - 16 );
					lorann.setY(lorann.getY() - 16 );
				}
			}
	}
		for(int i=0;i<balls.size();i++){
			ball = (ball) balls.get(i);
			Rectangle bouleRec = ball.getBounds();

			if(lorannRec.intersects(bouleRec)){
				if (lorann.getDir() == "DOWN"){
					balls.remove(i);
					ChangeLevel2();
				}
				else if (lorann.getDir() == "UP"){
					balls.remove(i);
					ChangeLevel2();
				}
				else if (lorann.getDir() == "LEFT"){
					balls.remove(i);
					ChangeLevel2();
				}
				else if (lorann.getDir() == "RIGHT"){
					balls.remove(i);
					ChangeLevel2();
				}
				else if (lorann.getDir() == "DIAG UL"){
					balls.remove(i);
					ChangeLevel2();
				}
				else if (lorann.getDir() == "DIAG UR"){
					balls.remove(i);
					ChangeLevel2();
				}
				else if (lorann.getDir() == "DIAG DL"){
					balls.remove(i);
					ChangeLevel2();
				}
				else if (lorann.getDir() == "DIAG DR"){
					balls.remove(i);
					ChangeLevel2();
				}
				
			}
			
	}
		for(int i=0;i<Opens.size();i++){
			open = (Open) Opens.get(i);
			Rectangle ouverteRec = open.getBounds();

			if(lorannRec.intersects(ouverteRec)){
				Life += 2;
				 level++;
				 ChangeLevel();
			}
	}
		for(int i=0;i<Treasures.size();i++){
			treasure = (Treasure) Treasures.get(i);
			Rectangle tresor1Rec = treasure.getBounds();

			if(lorannRec.intersects(tresor1Rec)){
				if (lorann.getDir() == "DOWN"){
					score += 10;
					 Treasures.remove(i);
				}
				else if (lorann.getDir() == "UP"){
					score += 10;
					Treasures.remove(i);
				}
				else if (lorann.getDir() == "LEFT"){
					score += 10;
					Treasures.remove(i);
				}
				else if (lorann.getDir() == "RIGHT"){
					score += 10;
					Treasures.remove(i);
				}
				else if (lorann.getDir() == "DIAG UL"){
					score += 10;
					Treasures.remove(i);
				}
				else if (lorann.getDir() == "DIAG UR"){
					score += 10;
					Treasures.remove(i);
				}
				else if (lorann.getDir() == "DIAG DL"){
					score += 10;
					Treasures.remove(i);
				}
				else if (lorann.getDir() == "DIAG DR"){
					score += 10;
					Treasures.remove(i);
				}
			}
	}
		for(int i=0;i<MonstersD.size();i++){
			monsterD = (MonsterD) MonstersD.get(i);
			Rectangle monstreDRec = monsterD.getBounds();

			if(lorannRec.intersects(monstreDRec)){
				if (lorann.getDir() == "DOWN"){
					Life--;
					ChangeLevel();
				}
				else if (lorann.getDir() == "UP"){
					ChangeLevel();
					Life--;
				}
				else if (lorann.getDir() == "LEFT"){
					ChangeLevel();
					Life--;
				}
				else if (lorann.getDir() == "RIGHT"){
					ChangeLevel();
					Life--;
				}
				else if (lorann.getDir() == "DIAG UL"){
					ChangeLevel();
					Life--;
				}
				else if (lorann.getDir() == "DIAG UR"){
					ChangeLevel();
					Life--;
				}
				else if (lorann.getDir() == "DIAG DL"){
					ChangeLevel();
					Life--;
				}
				else if (lorann.getDir() == "DIAG DR"){
					ChangeLevel();
					Life--;
				}
			}
	}
		for(int i=0;i<MonstersA.size();i++){
			monsterA = (MonsterA) MonstersA.get(i);
			Rectangle monstreARec = monsterA.getBounds();

			if(lorannRec.intersects(monstreARec)){
				if (lorann.getDir() == "DOWN"){
					Life--;
					ChangeLevel();
				}
				else if (lorann.getDir() == "UP"){
					ChangeLevel();
					Life--;
				}
				else if (lorann.getDir() == "LEFT"){
					ChangeLevel();
					Life--;
				}
				else if (lorann.getDir() == "RIGHT"){
					ChangeLevel();
					Life--;
				}
				else if (lorann.getDir() == "DIAG UL"){
					ChangeLevel();
					Life--;
				}
				else if (lorann.getDir() == "DIAG UR"){
					ChangeLevel();
					Life--;
				}
				else if (lorann.getDir() == "DIAG DL"){
					ChangeLevel();
					Life--;
				}
				else if (lorann.getDir() == "DIAG DR"){
					ChangeLevel();
					Life--;
				}
			}
	}
		for(int i=0;i<MonstersC.size();i++){
			monsterC = (MonsterC) MonstersC.get(i);
			Rectangle monstreCRec = monsterC.getBounds();

			if(lorannRec.intersects(monstreCRec)){
				if (lorann.getDir() == "DOWN"){
					Life--;
					ChangeLevel();
				}
				else if (lorann.getDir() == "UP"){
					ChangeLevel();
					Life--;
				}
				else if (lorann.getDir() == "LEFT"){
					ChangeLevel();
					Life--;
				}
				else if (lorann.getDir() == "RIGHT"){
					ChangeLevel();
					Life--;
				}
				else if (lorann.getDir() == "DIAG UL"){
					ChangeLevel();
					Life--;
				}
				else if (lorann.getDir() == "DIAG UR"){
					ChangeLevel();
					Life--;
				}
				else if (lorann.getDir() == "DIAG DL"){
					ChangeLevel();
					Life--;
				}
				else if (lorann.getDir() == "DIAG DR"){
					ChangeLevel();
					Life--;
				}
			}
	}
		for(int i=0;i<MonstersB.size();i++){
			monsterB = (MonsterB) MonstersB.get(i);
			Rectangle monstreBRec = monsterB.getBounds();

			if(lorannRec.intersects(monstreBRec)){
				if (lorann.getDir() == "DOWN"){
					Life--;
					ChangeLevel();
				}
				else if (lorann.getDir() == "UP"){
					ChangeLevel();
					Life--;
				}
				else if (lorann.getDir() == "LEFT"){
					ChangeLevel();
					Life--;
				}
				else if (lorann.getDir() == "RIGHT"){
					ChangeLevel();
					Life--;
				}
				else if (lorann.getDir() == "DIAG UL"){
					ChangeLevel();
					Life--;
				}
				else if (lorann.getDir() == "DIAG UR"){
					ChangeLevel();
					Life--;
				}
				else if (lorann.getDir() == "DIAG DL"){
					ChangeLevel();
					Life--;
				}
				else if (lorann.getDir() == "DIAG DR"){
					ChangeLevel();
					Life--;
				}
			}
	}
		for(int i=0;i<Skulls.size();i++){
			skull = (Skull) Skulls.get(i);
			Rectangle craneRec = skull.getBounds();

			if(lorannRec.intersects(craneRec)){
				if (lorann.getDir() == "DOWN"){
					Life--;
					ChangeLevel();
				}
				else if (lorann.getDir() == "UP"){
					ChangeLevel();
					Life--;
				}
				else if (lorann.getDir() == "LEFT"){
					ChangeLevel();
					Life--;
				}
				else if (lorann.getDir() == "RIGHT"){
					ChangeLevel();
					Life--;
				}
				else if (lorann.getDir() == "DIAG UL"){
					ChangeLevel();
					Life--;
				}
				else if (lorann.getDir() == "DIAG UR"){
					ChangeLevel();
					Life--;
				}
				else if (lorann.getDir() == "DIAG DL"){
					ChangeLevel();
					Life--;
				}
				else if (lorann.getDir() == "DIAG DR"){
					ChangeLevel();
					Life--;
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
			lorann.setDir("DIAG DR");
			lorann.Move();
			CheckCollision();
		}
		else if (Touche == KeyEvent.VK_NUMPAD9){
			lorann.setDir("DIAG UR");
			lorann.Move();
			CheckCollision();
		}
		else if (Touche == KeyEvent.VK_NUMPAD7){
			lorann.setDir("DIAG UL");
			lorann.Move();
			CheckCollision();
		}
		else if (Touche == KeyEvent.VK_NUMPAD1){
			lorann.setDir("DIAG DL");
			lorann.Move();
			CheckCollision();
		}
		else if (Touche == KeyEvent.VK_R){
			ChangeLevel();
			Life--;
		}
		if (Touche == KeyEvent.VK_NUMPAD2){
			lorann.setDir("DOWN");
			lorann.Move();
			CheckCollision();
			
		}
		else if (Touche == KeyEvent.VK_NUMPAD8){
			lorann.setDir("UP");
			lorann.Move();
			CheckCollision();
		}
		else if (Touche == KeyEvent.VK_NUMPAD4){
			lorann.setDir("LEFT");
			lorann.Move();
			CheckCollision();
		}
		else if (Touche == KeyEvent.VK_NUMPAD6){
			lorann.setDir("RIGHT");
			lorann.Move();
			CheckCollision();
		}
		else if (Touche == KeyEvent.VK_SPACE){
			if (missilelaunched == false){
				if(lorann.getDir()== "UP")
				{
			try{
				Game [10][10] = "MISSILE";
				missile = new Missile((10*16),(10*16));
				Missiles.add(missile);
				missile.setX(lorann.getX());
				missile.setY(lorann.getY()+16);
				missile.setDir("DOWN");
				missilelaunched = true;
			}
			catch (Exception ex){}
			repaint();
			}
				if(lorann.getDir()== "DOWN")
				{
			try{
				Game [10][10] = "MISSILE";
				missile = new Missile((10*16),(10*16));
				Missiles.add(missile);
				missile.setX(lorann.getX());
				missile.setY(lorann.getY()-16);
				missile.setDir("UP");
				missilelaunched = true;
			}
			catch (Exception ex){}
			repaint();
			}
				if(lorann.getDir()== "LEFT")
				{
			try{
				Game [10][10] = "MISSILE";
				missile = new Missile((10*16),(10*16));
				Missiles.add(missile);
				missile.setX(lorann.getX()+16);
				missile.setY(lorann.getY());
				missile.setDir("RIGHT");
				missilelaunched = true;
			}
			catch (Exception ex){}
			repaint();
			}
				if(lorann.getDir()== "RIGHT")
				{
			try{
				Game [10][10] = "MISSILE";
				missile = new Missile((10*16),(10*16));
				Missiles.add(missile);
				missile.setX(lorann.getX()-16);
				missile.setY(lorann.getY());
				missile.setDir("LEFT");
				missilelaunched = true;
			}
			catch (Exception ex){}
			repaint();
			}
				if(lorann.getDir()== "DIAG UL")
				{
			try{
				Game [10][10] = "MISSILE";
				missile = new Missile((10*16),(10*16));
				Missiles.add(missile);
				missile.setX(lorann.getX()+16);
				missile.setY(lorann.getY()+16);
				missile.setDir("DIAG DR");
				missilelaunched = true;
			}
			catch (Exception ex){}
			repaint();
			}
				if(lorann.getDir()== "DIAG UR")
				{
			try{
				Game [10][10] = "MISSILE";
				missile = new Missile((10*16),(10*16));
				Missiles.add(missile);
				missile.setX(lorann.getX()-16);
				missile.setY(lorann.getY()+16);
				missile.setDir("DIAG DL");
				missilelaunched = true;
			}
			catch (Exception ex){}
			repaint();
			}
				if(lorann.getDir()== "DIAG DL")
				{
			try{
				Game [10][10] = "MISSILE";
				missile = new Missile((10*16),(10*16));
				Missiles.add(missile);
				missile.setX(lorann.getX()+16);
				missile.setY(lorann.getY()-16);
				missile.setDir("DIAG UR");
				missilelaunched = true;
			}
			catch (Exception ex){}
			repaint();
			}
				if(lorann.getDir()== "DIAG DR")
				{
			try{
				Game [10][10] = "MISSILE";
				missile = new Missile((10*16),(10*16));
				Missiles.add(missile);
				missile.setX(lorann.getX()-16);
				missile.setY(lorann.getY()+16);
				missile.setDir("DIAG UL");
				missilelaunched = true;
			}
			catch (Exception ex){}
			repaint();
			}
			}
			else
				RetourMissile(missile.getDir());
		}
		else{
			if(lorannSens != 8){
			lorannSens++;	
			}
			else 
				lorannSens = 1;
			keyTyped = false;
		}
		
		
		repaint();
		if (monstreAAlive == true){
		MovementMonsterA();
		}
		if (monstreBAlive == true){
			MovementMonsterB();
			}
		if (monstreCAlive == true){
			MovementMonsterC();
			}
		if (monstreDAlive == true){
			MovementMonsterD();
			}

		if (missilelaunched == true)
		{
		MovementMissile();
		}
		if (Life == 0){
			level = 1;
			ChangeLevel();
			Life = 11;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		int Touche = arg0.getKeyCode();


		if (Touche == KeyEvent.VK_W){
			level++;
			repaint();
		}
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
	}
	
}