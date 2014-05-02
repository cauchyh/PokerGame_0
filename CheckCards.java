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
		highestCard = cards[cards.length - 1];
		pokerType = checkPokerType(cards);
	}

	// return the two parameters
	public int getPokerType()
	{
		return pokerType;
	}

	public int getHighestCard()
	{
		return highestCard();
	}

	public int checkPokerType(Poker[] cards)
	{
		if (isStraightFlush(cards))
		{
			return STRAIGHT_FLUSH;
		}
		if (isFourOfAKind(cards))
		{
			return FOUR_OF_A_KIND;
		}
		if (isFullHouse(cards))
		{
			return FULL_HOUSE;
		}
		if (isFlush(cards))
		{
			return FLUSH;
		}
		if (isStraight(cards))
		{
			return STRAIGHT;
		}
		if (isThreeOfAKind(cards))
		{
			return THREE_OF_A_KIND;
		}
		if (isTwoPair(cards))
		{
			return TWO_PAIR;
		}
		if (isOnePair(cards))
		{
			return ONE_PAIR;
		}
		else // if (isHighCards(cards))
		{
			return HIGH_CARDS;
		}
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
		return (counter == 2) ? true: false;
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
			if (last.number + 1 != cards[i].number)
			{
				return false;
			}
			else
			{
				last = cards[i];
			}
		}
		return true;
	}

	private boolean isFlush(Poker[] cards)
	{
		char suit = cards[0].suit;
		for (Poker card : cards)
		{
			if (card.suit != suit)
			{
				return false;
			}
		}
		return true;
	}

	private boolean isFullHouse(Poker[] cards)
	{
		Poker[] first1 = Arrays.copyOfRange(cards, 0, 3);
		Poker[] last1 = Arrays.copyOfRange(cards, 3, 5);
		Poker[] first2 = Arrays.copyOfRange(cards, 0, 2);
		Poker[] last2 = Arrays.copyOfRange(cards, 2, 5);
		if ((isThreeOfAKind(first1) && isTwoPair(last1)) || (isThreeOfAKind(first2) && isTwoPair(last2)))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	private boolean isFourOfAKind(Poker[] cards)
	{
		int counter = 0;
		Poker last = cards[0];
		for (int i=1; i<cards.length; i++)
		{
			if (cards[i].number == last.number)
			{
				counter ++;
			}
			else
			{
				counter = 0;
				last = cards[i];
			}
		}
		return (counter == 3) ? true : false;
	}

	private boolean isStraightFlush(Poker[] cards)
	{
		return (isStraight(cards) && isFlush(cards)) ? true : false;
	}


}

















