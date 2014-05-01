/*
	This class is used to check the combinations of a hand of cards.
	Further, this function defines some of index as for further comparation
*/

public class CheckCards
{
	final static HIGH_CARDS = 1;
	final static ONE_PAIR = 2;
	final static TWO_PAIR = 3;
	final static THREE_OF_A_KIND = 4;
	final static STRAIGHT = 5;
	final static FLUSH = 6;
	final static FULL_HOUSE = 7;
	final static FOUR_OF_A_KIND = 8;
	final static STRAIGHT_FLUSH = 9;

	/**
	 * @param pokerType - indicating which poker does this hand of cards have
	 * 					  such as Flush, Full House, etc.
	 * @param highestCard - indicating the highest single card of the hand in
	 * 						case that the pokerTypes are same
	 */
	int pokerType = 0;
	int highestCard = 0;
	
	public CheckCards(Poker[] cards)	
	{
		Arrays.sort(cards);


	}

	private boolean isHighCards(Poker[] cards)
	{
		return true;
	}

	private boolean isOnePair(Poker[] cards)
	{
		int last = cards[0];
		for ()
	}

}