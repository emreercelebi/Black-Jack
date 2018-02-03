import java.util.*;

public class BlackJackGame {
	
	private HashMap<String, Integer> cardValues;
	private Card[] deck;
	private int top;
	private int userTotal;
	private int computerTotal;
	
	public BlackJackGame(){
		DeckOfCards cards = new DeckOfCards();
		deck = cards.getDeck();
		top = 0;
		cardValues = new HashMap<String,Integer>();
		fillMap(cardValues);
		userTotal = 0;
		computerTotal = 0;
	}
	
	private void fillMap(HashMap<String, Integer> cardValues){
		cardValues.put("Ace", 11);
		cardValues.put("Jack", 10);
		cardValues.put("Queen", 10);
		cardValues.put("King", 10);
		for (int i = 2; i <= 10; i++){
			cardValues.put(i + "", i);
		}
	}
	
	public void start(){
		System.out.println("Ready whenever you are. Press enter to start.");
		Scanner sc = new Scanner(System.in);
		sc.nextLine();
		System.out.println("Dealing now...");
		System.out.println("");
		
		ArrayList<Card> userCards = new ArrayList<Card>();
		ArrayList<Card> computerCards = new ArrayList<Card>();
		
		//pushes true onto stack when ace is drawn; popped when an ace needs to changes its value from 11 to 1
		Stack<Boolean> userAces =  new Stack<Boolean>();
		Stack<Boolean> computerAces =  new Stack<Boolean>();
		
		//deal cards
		dealCards(userCards, userAces, computerCards, computerAces);
		System.out.println("The dealer has a " + computerCards.get(0) + " and a face down card.");
		
		
		while (userTotal <= 21){
			System.out.println("Here are your cards: ");
			for (Card card : userCards){
				System.out.println(card.toString());
			}
			System.out.println("");
			System.out.println("Your current total is: " + userTotal);
			System.out.println("Would you like to hit or stand? (h/s)");
			boolean stand = false;
			while (true) {
				String decision = sc.nextLine().toLowerCase().trim();

				if (decision.equals("h") || decision.equals("hit")){
					userTotal = hit(userCards, userAces, userTotal);
				}
				else if (!(decision.equals("s") || decision.equals("stand"))){
					System.out.println("Please type either hit (h) or stand (s). I am not smart enough to interpret anything else yet.");
					continue;
				}
				else {
					stand = true;
				}
				break;
			}
			if (stand) break;
		}
		System.out.println("Your final hand: ");
		for (Card card : userCards){
			System.out.println(card.toString());
		}
		
		if (userTotal <= 21){
			if (userTotal < 21){
				System.out.println("Your final total is " + userTotal);
			}
			else if (userTotal == 21) {
				System.out.println("Your final total is 21! Nice work!");
			}
			System.out.println("");
			System.out.println("Now it's my turn... Press Enter when you're ready. Remember that I win ties ;)");
			sc.nextLine();
			do {
				System.out.println("Here are my cards: ");
				for (Card card : computerCards){
					System.out.println(card.toString());
				}
				System.out.println("");
				System.out.println("My current total is: " + computerTotal);
				
				if (computerTotal >= 17){
					System.out.println("Since my total is at least 17, I have to stand here.");
				}
				else {
					System.out.println("My total is under 17, so I have to hit.");
					computerTotal = hit(computerCards, computerAces, computerTotal);
				}
				System.out.println("Press Enter to continue");
				sc.nextLine();
			} while (computerTotal < 17);
			System.out.println("Here are my cards: ");
			for (Card card : computerCards){
				System.out.println(card.toString());
			}
			if (computerTotal > 21){
				System.out.println("Well shucks, my total is " + computerTotal + ". You win!");
			}
			else if (computerTotal == 21){
				System.out.println("My total is 21 flat! I win!");
			}
			else {
				System.out.println("My final total is " + computerTotal + ". Yours was " + userTotal + ".");
				if (userTotal > computerTotal){
					System.out.println("You win this round...");
				}
				else {
					System.out.println("I win this round!");
				}
			}
			
		}
		
		else{
			System.out.println("Your final total is " + userTotal + ". Busted :/");
		}
		
				
	}
	
	public void dealCards(ArrayList<Card> userCards, Stack<Boolean> userAces, ArrayList<Card> computerCards, Stack<Boolean> computerAces){
		for (int i = 0; i < 2; i++){
			Card card1 = deck[top++];
			Card card2 = deck[top++];
			userCards.add(card1);
			userTotal += cardValues.get(card1.getRank());
			if (card1.getRank().equals("Ace")){
				userAces.push(true);
			}
			if (userTotal > 21){
				userAces.pop();
				userTotal -= 10;
			}
			computerCards.add(card2);
			computerTotal += cardValues.get(card2.getRank());
			if (card2.getRank().equals("Ace")){
				computerAces.push(true);
			}
			if (computerTotal > 21){
				computerAces.pop();
				computerTotal -= 10;
			}			
		}
	}
	
	public int hit(ArrayList<Card> cards, Stack<Boolean> aces, int total){
		Card card = deck[top++];
		String rank = card.getRank();
		cards.add(card);
		total += cardValues.get(rank);
		if (rank.equals("Ace")){
			aces.push(true);
		}
		if (total > 21) {
			if (!aces.isEmpty()){
				aces.pop();
				total -= 10;
			}
		}
		return total;
	}	
}
