import java.util.*;
public class Game {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Hello, would you like to play some BlackJack? (Y/N)");
		boolean playing = false;
		while (true){
			String answer = s.nextLine().toLowerCase().trim();
			if (answer.equals("yes") || answer.equals("y")) playing = true;
			else if (!(answer.equals("no") || answer.equals("n"))){
				System.out.println("Please answer either yes (y) or no (n). I am not smart enough to interpret anything else yet.");
				continue;
			}
			break;

		}
		if (playing){
			while (true){
				BlackJackGame game = new BlackJackGame();
				game.start();
				boolean playingAgain = false;
				while (true){
					System.out.println("Would you like to play again? (y/n)");
					String answer = s.nextLine().toLowerCase().trim();
					if (answer.equals("yes") || answer.equals("y")) playingAgain = true;
					else if (!(answer.equals("no") || answer.equals("n"))){
						System.out.println("Please answer either yes (y) or no (n). I am not smart enough to interpret anything else yet.");
						continue;
					}
					break;
				}
				if (!playingAgain){
					System.out.println("Thanks for playing!");
					break;
				}
				
			}
		}
		else System.out.println("Bummer. Come back when you're not scared.");
	}
	
	
	
	

}
