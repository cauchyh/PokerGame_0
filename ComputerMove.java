/*
	This class mainly includes the methods the computer will take after it has 
	received its hand, including split the 7 cards into 5+2
	And how to identify the 5 cards out of 7. 
*/

public class ComputerMove
{
	final static HIGH_HAND_NUMBER = 5;
	final static LOW_HAND_NUMBER = 2;
	Poker[] highHand = new Poker[5];
	Poker[] lowHand = new Poker[2];

	public ComputerMove(Poker[] cards)
	{
		setTwoHand(cards, highHand, lowHand);
	}


	private void setTwoHand(Poker[] cards, Poker[] highHand, Poker[] lowHand)
	{
		// Use a naive and sometimes wrong approach first
		lowHand = Arrays.copyOfRange(cards, 0, LOW_HAND_NUMBER);
		highHand = Arrays.copyOfRange(cards, LOW_HAND_NUMBER, HIGH_HAND_NUMBER);
	}

}