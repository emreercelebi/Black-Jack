
public class DeckOfCards {
	
	private Card[] deck;
	private String[] suits = {"Spades","Clubs","Hearts","Diamonds"};
	private String[] ranks = {"Ace","2","3","4","5","6","7","8","9","10","Jack","Queen","King"};
	
	public DeckOfCards() {
		//creates each card and stores them into an array which will represent the deck
		deck = new Card[52];
		int index = 0;
		for (int i = 0; i < suits.length; i++){
			for (int j = 0; j < ranks.length; j++){
				Card card = new Card(suits[i], ranks[j]);
				deck[index] = card;
				index++;
			}
		}
		shuffle();
	}
	
	public Card[] getDeck(){
		return deck;
	}
	
	
	//shuffles deck array using Fisher-Yates shuffle algorithm
	private void shuffle(){
		for (int i = deck.length-1; i >= 0; i--){
			int num = (int)(Math.random()*(i+1));
			Card temp = deck[i];
			deck[i] = deck[num];
			deck[num] = temp;
		}
	}
}
