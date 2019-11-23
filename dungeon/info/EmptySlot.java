package dungeon.info;

public class EmptySlot extends Slot{

	// Constructor
	public EmptySlot(int x, int y){
		super(".", x, y);
		super.died();
	};

	// Copy Constructor
	public EmptySlot(EmptySlot e){ super(e); }
}
