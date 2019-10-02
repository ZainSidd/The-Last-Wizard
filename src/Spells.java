import java.util.ArrayList;
// Contains arrayList of Spell objects and methods to return relevant values

public class Spells {
	private ArrayList<Spell> spells; // arrayList containing reference to all spell objects on the screen

	Spells(){
		spells = new ArrayList<Spell>();	
	}
	
	public void addSpell (Spell a)
	{
		spells.add(a);
	}
	
	Spell getSpell(int index)
	{
		return spells.get(index);
	}
	
	public void removeSpell(int index)
	{
		spells.remove(index);
	}
	
	public int spellsArrayListSize()
	{
		return spells.size();
	}
	
}
