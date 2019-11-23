import dungeon.*;
import java.util.Scanner;

public class Main{
	static int height = 3;
	static int width = 3;
	static int battery = 100;
	static int vamps = 8;
	static boolean easy = false;

	public static void main(String[] args){
		Scanner reader = new Scanner(System.in);

		printGameInfo();
		pregameConfig(reader); // remove this for debugging

		if(!easy)
			System.out.println("EASY MODE ACTIVATED!!!!");


		// Scanner, height, width, life, vampCount, vampiresMove
		Dungeon dungeon = new Dungeon(reader,height,width,battery,vamps,easy);

		boolean gameOver = false;
		while(dungeon.getTimer() > 0 && !gameOver){
			dungeon.printRound();
			gameOver = dungeon.gameState();
		}
	
	}

	public static void printGameInfo(){
		System.out.println("*** Welcome To Vampire Slayer Game! ***\n");
		System.out.println("First, you will choose field dimension, number of vampires, battery life, and difficulty.\n");

		System.out.println("  Press:  \n" +
						   "\t'w' to move up\n" + 
						   "\t'a' to move left\n" +
						   "\t's' to move down\n" +
						   "\t'd' to move right\n");

		System.out.println("You may enter multiple characters in one move, but you will not be able to move beyond corners.");

		System.out.println("In easy mode, vampires will stay still; in hard mode, they will randomly move as much as your did.");

		System.out.println("Defeat all vampires before battery runs out.\n");
	}

	public static void pregameConfig(Scanner reader){

		int progress = 0;
		String userInput = "";
		while(progress < 5){
			if(progress == 0){
				System.out.print("Enter the height of map: ");

				userInput = reader.nextLine();
				if(isInt(userInput)){
					height = Integer.parseInt(userInput);
					progress++;
				}
			}	
			else if(progress == 1){
				System.out.print("Enter the width of map: ");

				userInput = reader.nextLine();
				if(isInt(userInput)){
					width = Integer.parseInt(userInput);
					progress++;
				}
			}
			else if(progress == 2){
				System.out.print("How many ememies?: ");
				userInput = reader.nextLine();

				if(isInt(userInput)){
					vamps = Integer.parseInt(userInput);

					if(vamps > (height * width) / 2)
						System.err.println("Too many vampires... try again");
					else
						progress++;		
				}
			}
			else if(progress == 3){
				System.out.print("Enter battery life: ");

				userInput = reader.nextLine();
				if(isInt(userInput)){
					battery = Integer.parseInt(userInput);
					progress++;
				}
			}
			else if(progress == 4){
				System.out.print("Enter 'Easy' for easy mode. Otherwise it will be hard mode: ");
				String choice = reader.nextLine();
				choice = choice.replace(" ", "");
				choice = choice.toLowerCase();

				if(!choice.contains("easy"))
					easy = true;

				progress++;
			}

			if(!isInt(userInput)){
				System.err.println("\nPlease enter whole number\n");
			}
		}

	}

	// isInt - checks if string is int type (source: https://stackoverflow.com/questions/237159/whats-the-best-way-to-check-if-a-string-represents-an-integer-in-java?page=1&tab=votes#tab-top)
	public static boolean isInt(String str){
		
		if(str == null || str.length() == 0)
			return false;

		int i = 0;
		if(str.charAt(i) =='-'){
			if(str.length() == 1)
				return false;

			i = 1;
		}

		for(; i < str.length(); i++){
			char c = str.charAt(i);

			if(c < '0' || c > '9')
				return false;
		}

		return true;
	}


}
