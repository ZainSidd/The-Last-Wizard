
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
//Enemy object constructor and related fields

public class Enemy {
	private String enName;
	private int enemySpeed;
	private double enemyHealth;
	private Sprite image;
	private int currentXPos;
	private int currentYPos;
	private int width;
	private int height;
	private Rectangle rect;
	private Sprite healthBar;
	
	Enemy(String name, int enSpeed, double enHP, Sprite enImage, int x, int y, int w, int h, Sprite hpBar) // Enemy object constructor
	{
		enName = name;
		enemySpeed = enSpeed;
		enemyHealth = enHP;
		image = enImage;
		currentXPos = x;
		currentYPos = y;
		width = w;
		height = h;
		healthBar = hpBar;
	}
	
	public Rectangle getRectangle(String enName){
			rect = new Rectangle (currentXPos-5, currentYPos, width, height);
			if (enName == "ghost"){ // Manually fixing the hitbox size of the ghost
				rect = new Rectangle (currentXPos-20, currentYPos, width+15, height);
			}
			else if (enName == "dkKnight"){
				rect = new Rectangle (currentXPos-10, currentYPos, width, height);
			}
			return rect;		
	}
	
	public String getName(){ // Returns speed of the enemy
		return enName;
	}
	
	public int getEnemySpeed(){ // Returns speed of the enemy
		return enemySpeed;
	}
	
	public double getEnemyHealth(){ // Returns health of the enemy
		return enemyHealth;
	}
	
	public Sprite getEnemySprite(){
		return image;
	}
	
	public int getCurrentX(){
		return currentXPos;
	}
	
	public int getCurrentY(){
		return currentYPos;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public Sprite getHPBar(){
		return healthBar;
	}
	
	public int multiplier(String name){
		int multiplier = 1;
		if (name == "wolf"){
			multiplier = 4;
		}
		else if (name == "goblin"){
			multiplier = 2;
		}
		return multiplier;
	}
	
	public int positioner(String name){
		int positioner = 5;
		if (name == "dkKnight"){
			positioner = 10;
		}
		return positioner;
	}
	
	public void Move(int x, int y){ // Updates the position of the enemy
		currentXPos = x;
		currentYPos = y;
	}
	
	public void speedDown(){ // For the ice ability, this will debuff the enemy's speed
		enemySpeed--;
	}
	
	public void reduceEnemyHP(double damage){
		enemyHealth -= damage;
	}

}
