package dungeon;

import dungeon.info.*;
import java.util.*;

public class Dungeon{
	private Action action;
	private int timer;
	private Slot[][] dMap;
	private Hero hero;
	private AllVamps allVamps;
	private Map<Integer, Slot> vampTracker;
	private boolean gameover;
	private boolean vampiresMove;

	// Constructor
	public Dungeon(Scanner reader, int height, int width, int timer, int vamps, boolean vampMove){
		this.dMap = new Slot[height][width];
		this.timer = timer + 1;
		this.gameover = false;
		this.vampiresMove = vampMove;

		// Start action
		this.action = new Action(reader);

		// populate empty slots
		for(int i = 0; i < this.dMap.length; i++){
			for(int j = 0; j < this.dMap[i].length; j++){
				this.dMap[i][j] = new EmptySlot(i, j);
			}
		}

		// vampTracker initialization
		this.vampTracker = new HashMap<Integer, Slot>();

		// set hero
		this.hero = new Hero(this.timer);
		this.dMap[0][0] = this.hero;
		this.vampTracker.put(0, this.hero);

		// set vampires
		this.allVamps = new AllVamps();

		for(int v = 1; v <= vamps; v++){

			// get valid coordinate
			int[] newPos = action.reRoll(vampTracker, height, width);
			int vHeight = newPos[0];
			int vWidth = newPos[1];

			// populate vampire to map
			this.dMap[vHeight][vWidth] = new Vamp(vWidth, vHeight);
			this.allVamps.add(this.dMap[vHeight][vWidth]);

			// update vampire tracker
			this.vampTracker.put(v, this.dMap[vHeight][vWidth]);
		}
	}

	// accessors
	public int getTimer(){ return this.hero.getTimer(); }

	// moveHero
	public void moveHero(){
		int destinationX = this.action.nextPositionX() + this.hero.getWidth();
		int destinationY = this.action.nextPositionY() + this.hero.getHeight();
		destinationX = this.limitMovement(destinationX, this.dMap[0].length);
		destinationY = this.limitMovement(destinationY, this.dMap.length);

		if( this.dMap[destinationY][destinationX].isAlive() )
			this.killVillan(destinationY, destinationX, this.dMap[destinationY][destinationX]);

		swap(this.hero, destinationX, destinationY);
	}

	// killVillan
	public void killVillan(int targetHeight, int targetWeight, Slot v){
		this.allVamps.remove(v);

		// remove from tracker
		for(int i = 0; i < this.vampTracker.size(); i++){
			if(v.equals(this.vampTracker.get(i)))
				this.vampTracker.remove(i);
		}

		this.dMap[targetHeight][targetWeight] = new EmptySlot(targetHeight, targetWeight);
	}

	// moveVillan
	public void moveVillan(){
		for(Slot v : this.allVamps.getList()){

			boolean noOverlap = false;
			int newHeight = v.getHeight();
			int newWidth = v.getWidth();

			// infinite loop when there's no input
			while(!noOverlap){

				// generate steps needed for next coordinate
				int[] nextPos = this.action.setNextVampStep(v);

				// add above relative to current position
				newHeight += nextPos[0];
				newWidth += nextPos[1];

				// adjust to boundary
				newHeight = this.limitMovement(newHeight, this.dMap.length);
				newWidth = this.limitMovement(newWidth, this.dMap[0].length);

				if(!this.allVamps.coordinateOccupied(newHeight, newWidth, this.hero))
					noOverlap = true;
			}

			swap(v, newWidth, newHeight);
		}
	}

	// swap
	public void swap(Slot start, int nextWidth, int nextHeight){
		Slot temp = new Slot(start); // copy constructor via inheritance can't be abstract class

		// move physical location
		this.dMap[temp.getHeight()][temp.getWidth()] = this.dMap[nextHeight][nextWidth];
		this.dMap[nextHeight][nextWidth] = start;

		// update cooridinate info
		start.updatePosition(nextWidth, nextHeight);
		dMap[temp.getHeight()][temp.getWidth()].updatePosition(temp.getWidth(), temp.getHeight());
	}

	// limitMovement
	public int limitMovement(int coordinate, int boundary){
		if(coordinate < 0)
			coordinate = 0;
		else if(coordinate >= boundary)
			coordinate = boundary - 1;

		return coordinate;
	}

	// printAliveStatus
	public void printAliveStatus(){
		System.out.println(this.hero.getSymbol() + " " + this.hero.getHeight() + " " + this.hero.getWidth());

		for(Slot v : this.allVamps.getList()){
			System.out.println(v.getSymbol() + " " + v.getHeight() + " " + v.getWidth());
		}
	}

	// printDungeonMap
	public void printDungeonMap(){

		for(Slot[] dRow : this.dMap){
			for(Slot dIdx : dRow)
				System.out.print(dIdx);

			System.out.println();
		}	

		// for next game
		System.out.println("\n");
	}

	// printRound
	public void printRound(){

		System.out.println("Time Left: " + (this.hero.getTimer() - 1) + "\n");
		hero.reduceTimer();

		this.printAliveStatus();
		System.out.println("\n");
		this.printDungeonMap();

		this.gameover = this.gameState();
		if(!this.gameover)
			this.action.makeInput();
		
		if(!this.action.isEmptyCommand()){
			this.moveHero();

			if(this.vampiresMove)
				this.moveVillan();
		}

		// win, lose, or continue
		this.action.verdict(this.hero, this.allVamps.getCount());
		System.out.println();
		
	}

	// gameState
	public boolean gameState(){
		return (!hero.isAlive() || this.allVamps.getCount() < 1);
	}
}
