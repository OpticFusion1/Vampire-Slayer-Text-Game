package dungeon.info;

public class Slot{

	private String symbol;
	private boolean alive;
	private int x;
	private int y;

	// Constructor
	public Slot(String symbol){
		this(symbol, 0, 0);
	}

	// Copy Constructor
	public Slot(Slot s){
		this.symbol = s.symbol;
		this.alive = s.alive;
		this.x = s.x;
		this.y = s.y;
	}

	// Overloaded Constructor
	public Slot(String symbol, int x, int y){
		this.symbol = symbol;
		this.alive = true;
		this.x = x;
		this.y = y;
	}

	// accessors
	public String getSymbol(){ return this.symbol; }
	public int getWidth(){ return this.x; };
	public int getHeight(){ return this.y; };
	public boolean isAlive(){ return this.alive; };


	// mutators
	public void updatePosition(int x, int y){
		this.x = x;
		this.y = y;
	}

	// died 
	protected void died(){ this.alive = false; };


	// toString
	@Override
	public String toString(){
		return this.symbol;
	}

}
