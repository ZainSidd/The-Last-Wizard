import java.util.ArrayList;
//Contains arrayList of Enemy objects and methods to return relevant values

public class Enemies {
	private ArrayList<Enemy> enemies; // Array list that holds all the enemies currently on the screen

	Enemies(){
		enemies = new ArrayList<Enemy>();	
	}
	
	public void addEnemy (Enemy a) // Add a new enemy to the arrayList
	{
		enemies.add(a);
	}
	
	Enemy getEnemy(int index) // Gets the reference of the enemy object
	{
		return enemies.get(index);
	}
	
	public void enemyDead(int index) // When the enemy is dead
	{
		enemies.remove(index);
	}
	
	public int enemiesArrayListSize() // Size of the enemies arrayList
	{
		return enemies.size();
	}
}