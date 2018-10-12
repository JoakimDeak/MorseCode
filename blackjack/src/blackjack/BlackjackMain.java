package blackjack;

public class BlackjackMain {
	
	public BlackjackMain() {
		
		
	}
	
	public void run() {
		
		Dealer dealer = new Dealer();
		
		dealer.createDeck(2);
		dealer.deal();
	}
	
	public static void main(String[] args) {
		
		BlackjackMain main = new BlackjackMain();
		main.run();
	}
}
