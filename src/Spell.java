import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
// Spell object constructor and related fields

public class Spell {
	private String spellName;
	private int spellSpeed;
	private double spellDamage;
	private Sprite image;
	private float spellXPos;
	private float spellYPos;
	private int width;
	private int height;
	private int destinationX;
	private int destinationY;
	private Rectangle rect;
	
	Spell(String name, int spSpeed, double spDam, Sprite spImage, float x, float y, int w, int h, int mouseX, int mouseY)
	{
		spellName = name;
		spellSpeed = spSpeed;
		spellDamage = spDam;
		image = spImage;
		spellXPos = x;
		spellYPos = y;
		width = w;
		height = h;
		destinationX = mouseX;
		destinationY = mouseY;
	}
	
	public Rectangle getRectangle(){
		rect = new Rectangle (spellXPos, spellYPos, width-20, height-5);
		return rect;
	}
	
	public String getSpellName(){
		return spellName;
	}
	
	public int getSpellSpeed(){
		return spellSpeed;
	}
	
	public double getSpellDamage(){
		return spellDamage;
	}
	
	public Sprite getSpellSprite(){
		return image;
	}
	
	public float getSpellXPos(){
		return spellXPos;
	}
	
	public float getSpellYPos(){
		return spellYPos;
	}
	
	public int getSpellHeight(){
		return height;
	}
	
	public int getSpellWidth(){
		return width;
	}
	
	public int getSpellXDes(){
		return destinationX;
	}
	
	public int getSpellYDes(){
		return destinationY;
	}
	
	public void setPosition(float x, float y){ // Updates 'x' and 'y' positions of the spell
		spellXPos = x;
		spellYPos = y;
	}
	
}
