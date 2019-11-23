package dungeon.info;

public class Vamp extends Slot{

	// Constructor
	public Vamp(int x, int y){
		super("v", x, y);	
	}

	// // Copy Constructor
	public Vamp(Vamp v){ super(v); }

	// getPosition - return [height, width]
	public int[] getPosition(){
		int[] posArr = new int[2];
		posArr[0] = super.getHeight();
		posArr[1] = super.getWidth();

		return posArr;
	}
}
