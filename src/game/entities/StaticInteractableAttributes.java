package game.entities;

public class StaticInteractableAttributes {

	public static final int TYPE_SCORE = 0;
	public static final int TYPE_HEALTH = 1;

	public int type = StaticInteractableAttributes.TYPE_SCORE; //true for good>bonus and false for bad-> decrease life
	public int amount = 0;
	public boolean diaspearAfterInteraction = false;
	
	public StaticInteractableAttributes(int whichType, int whichAmount, boolean disapear){
		this.type = whichType;
		this.amount = whichAmount;
		this.diaspearAfterInteraction = disapear;
	}
}
