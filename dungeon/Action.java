package dungeon;

import java.util.*;
import dungeon.info.*;

public class Action{
	private Scanner reader;	
	private String input;
	private int validChar;

	// Constructor
	public Action(Scanner reader){
		this.reader = reader;
		this.input = "";
		this.validChar = 0;
	}

	// makeInput
	public void makeInput(){
		System.out.print("Enter your moveset (wasd):");
		this.input = reader.nextLine();
		this.input = this.input.replace(" ", "");
		this.input = this.input.toLowerCase();

		if(this.input.length() > 0)
			this.setValidMoveCount();
	} 

	// isEmptyCommand
	public boolean isEmptyCommand(){ return this.input.length() < 1; }

	// nextPostionX 
	public int nextPositionX(){
		int xMovement = 0;

		for(char c : this.input.toCharArray()){
			if(c == 'a')
				xMovement--;
			else if(c == 'd')
				xMovement++;
		}		

		return xMovement;	
	}

	// nextPostionX 
	public int nextPositionY(){
		int yMovement = 0;

		for(char c : this.input.toCharArray()){
			if(c == 'w')
				yMovement--;
			else if(c == 's')
				yMovement++;
		}		

		return yMovement;	
	}

	// verdict
	public void verdict(Hero hero, int vCount){
		if(!hero.isAlive())
			System.out.println("YOU LOSE");
		else if(vCount < 1)
			System.out.println("YOU WIN");
	}

	// generateRandNo... well, this was useless
	public int generateRandNo(int limit){
		return new Random().nextInt(limit);
	}

	// reRoll
	public int[] reRoll(Map<Integer, Slot> vTracker, int height, int width){
		
		boolean hasDuplicate = false;
		int randHeight = this.generateRandNo(height);
		int randWidth = this.generateRandNo(width);

		do{
			hasDuplicate = false;
			randHeight = this.generateRandNo(height);
			randWidth = this.generateRandNo(width);

			for(Slot s : vTracker.values()){
				if(randHeight == s.getHeight() && randWidth == s.getWidth())
					hasDuplicate = true;
			}

		}while(hasDuplicate);

		return new int[]{randHeight, randWidth};
	}

	// setValidMoveCount
	public void setValidMoveCount(){
		int validChar = 0;
		for(char c : this.input.toCharArray()){
			if(c == 'w' || c == 'a' || c == 's' || c == 'd')
				validChar++;
		}

		this.validChar = validChar;
	}

	// setNextVampStep
	//public int[] setNextVampStep(Slot v, AllVamps allVamp, int height, int width){
	public int[] setNextVampStep(Slot v){

		int[] heightWidth = new int[]{0,0};
	
		//int pickHW = 0;
		int addORsub = 0;

		for(int i = 0; i < this.validChar; i++){
			//pickHW = this.generateRandNo(2);
			addORsub = this.generateRandNo(1000) % 2 == 0 ? 1 : -1 ;
			heightWidth[this.generateRandNo(2)] += addORsub;
		}

		return heightWidth;
	}
}
