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


	/*
		Provide 9 method to determine which type a hand of pokers is.
		The 9 types are provided above as final static.
	*/
	private boolean isHighCards(Poker[] cards)
	{
		return true;
	}

	private boolean isOnePair(Poker[] cards)
	{
		Poker last = cards[0];
		for (int i=1; i<cards.length; i++)
		{
			if (cards[i].number == last.number)
			{
				return true;
			}
			else
			{
				last = cards[i];
			}
		}
		return false;
	}

	private boolean isTwoPair(Poker[] cards)
	{
		int counter = 0; // count whether it is "two" pair
		Poker last = cards[0];
		for (int i=1; i<cards.length; i++)
		{
			if (cards[i].number == last.number)
			{
				counter++;
				if (i < cards.length-1)
				{
					last = cards[i+1];
					i++;
				}
				else
				{
					break;
				}
			}
			else
			{
				last = cards[i];
			}
		}
		(counter == 2) ? return true: return false;
	}

	private boolean isThreeOfAKind(Poker[] cards)
	{
		int counter = 0;
		Poker last = cards[0];
		for (int i=1; i<cards.length; i++)
		{
			if (counter == 2)
			{
				return true;
			}
			if (cards[i] == last.number)
			{
				counter++;
			}
			else
			{
				last = cards[i];
				counter = 0;
			}
		}
		return false;
	}

	private boolean isStraight(Poker[] cards)
	{
		Poker last = cards[0];
		for (int i=1; i<cards.length; i++)
		{
			
		}
	}


}

















