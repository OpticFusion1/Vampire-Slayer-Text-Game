package dungeon.info;

public class Hero extends Slot{
	private int timer;
	
	// Constructor
	public Hero(int timer){
		super("@");
		this.timer = timer;
	}

	// Copy Constructor
	public Hero(Hero h){
		super(h);
		this.timer = h.timer;
	}

	// accessor
	public int getTimer(){ return this.timer; }

	// reduceTimer
	public void reduceTimer(){
		--this.timer;

		if (this.timer < 1)
			super.died();	
	}

	// isDead 
	public boolean isDead(){
		return super.isAlive();
	}
}
