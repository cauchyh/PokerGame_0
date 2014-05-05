/*
	This class is used to check the combinations of a hand of cards.
	Further, this function defines some of index as for further comparation
*/
import java.util.*;
public class CheckCards
{
	final static int HIGH_CARDS = 1; // solved
	final static int ONE_PAIR = 2;
	final static int TWO_PAIR = 3;
	final static int THREE_OF_A_KIND = 4; // solved
	final static int STRAIGHT = 5; // solved
	final static int FLUSH = 6; // solved
	final static int FULL_HOUSE = 7; // solved
	final static int FOUR_OF_A_KIND = 8; // solved
	final static int STRAIGHT_FLUSH = 9; // solved
	final static int ROYAL_STRAIGHT_FLUSH = 10; // solved
	/**
	 * @param pokerType - indicating which poker does this hand of cards have
	 * 					  such as Flush, Full House, etc.
	 * @param highestCard - indicating the highest single card of the hand in
	 * 						case that the pokerTypes are same
	 */
	private int pokerType = 0;
	private int highestCard = 0;
	
	public CheckCards(Poker[] cards)	
	{
		Arrays.sort(cards);
		pokerType = checkPokerType(cards);
		highestCard = generateHighestCard(cards);
	}

	// return the two parameters
	public int getPokerType()
	{
		return pokerType;
	}

	public int getHighestCard()
	{
		return highestCard;
	}

	private int generateHighestCard(Poker[] cards)
	{
		if (pokerType == ROYAL_STRAIGHT_FLUSH || pokerType == STRAIGHT_FLUSH || pokerType == STRAIGHT ||
			pokerType == HIGH_CARDS || pokerType == FLUSH) {
				if (cards[0].getNumber() == 1){
					return 14;
				} else {
					return cards[cards.length - 1].getNumber();
				} 
		} else if (pokerType == FOUR_OF_A_KIND || pokerType == FULL_HOUSE || pokerType == THREE_OF_A_KIND){
			return getHighestCardThreeFourKind(cards);			
		} else { // if (pokerType == ONE_PAIR || pokerType == TWO_PAIR)
			return getHighestCardThreeFourKind(cards);
		}
	}
	/*
		return the highest card of Three of a kind and Four of a kind
	*/
	private int getHighestCardThreeFourKind(Poker[] cards)
	{
		// int counter = 0;
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i=0; i<cards.length; i++){
			if (!map.containsKey(cards[i].getNumber())){
				map.put(cards[i].getNumber(), 0);				
			} else {
				int newValue = map.get(cards[i].getNumber()) + 1;
				map.put(cards[i].getNumber(), map.get(cards[i].getNumber()) + 1);
			}
		}
		for (int i=0; i<cards.length; i++){
			if (map.get(cards[i].getNumber()) > 2){
				return cards[i].getNumber();
			}
		}
		return -1;
	}

	/*
		Return the highest card of One Pair and Two Pair
	*/
	private int getHighestCardOnePairTwoPair(Poker[] cards)
	{
		int res = 0;
		HashMap<Integer,Integer> map = new HashMap<Integer, Integer>();
		for (int i=0; i<cards.length; i++){
			if (!map.containsKey(cards[i].getNumber())){
				map.put(cards[i].getNumber(), 0);				
			} else {
				int newValue = map.get(cards[i].getNumber()) + 1;
				map.put(cards[i].getNumber(), map.get(cards[i].getNumber()) + 1);
			}
		}
		for (int i=0; i<cards.length; i++){
			if (map.get(cards[i].getNumber()) == 2){
				if (cards[i].getNumber() > res){
					res = cards[i].getNumber();
				}
			}
		}
		return res;
	}

	public int checkPokerType(Poker[] cards)
	{
		if (isRoyalStraightFlush(cards))
		{
			return ROYAL_STRAIGHT_FLUSH;
		}
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
			if (cards[i].getNumber() == last.getNumber())
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

	public boolean isTwoPair(Poker[] cards)
	{
		if (cards.length < 5){
			return false;
		}
		int counter = 0; // count whether it is "two" pair
		Poker last = cards[0];
		for (int i=1; i<cards.length; i++)
		{
			if (cards[i].getNumber() == last.getNumber())
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
		if (cards.length < 5){
			return false;
		}
		int counter = 0;
		Poker last = cards[0];
		for (int i=1; i<cards.length; i++)
		{
			if (cards[i].getNumber() == last.getNumber())
			{
				counter++;
			}
			else
			{
				last = cards[i];
				counter = 0;
			}
			if (counter == 2)
			{
				return true;
			}
		}
		return false;
	}

	private boolean isStraight(Poker[] cards)
	{
		if (cards.length < 5){
			return false;
		}
		if (cards[0].getNumber() == 1 && cards[1].getNumber() == 10)
		{// A, K, Q, J, 10 Straight	
			int[] check = {1, 10, 11, 12, 13};
			for (int i=0; i<cards.length; i++)
			{
				if (cards[i].getNumber() != check[i])
				{
					return false;
				}
			}
			return true;
		}
		else
		{
			Poker last = cards[0];
			for (int i=1; i<cards.length; i++)
			{
				if (last.getNumber() + 1 != cards[i].getNumber())
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
	}

	private boolean isFlush(Poker[] cards)
	{
		if (cards.length < 5){
			return false;
		}
		String suit = cards[0].getSuit();
		for (Poker card : cards)
		{
			if (!card.getSuit().equals(suit))
			{
				return false;
			}
		}
		return true;
	}

	private boolean isFullHouse(Poker[] cards)
	{
		if (cards.length < 5){
			return false;
		}
		Poker[] first1 = Arrays.copyOfRange(cards, 0, 3);
		Poker[] last1 = Arrays.copyOfRange(cards, 3, 5);
		Poker[] first2 = Arrays.copyOfRange(cards, 0, 2);
		Poker[] last2 = Arrays.copyOfRange(cards, 2, 5);
		if ((isThreeOfAKind(first1) && isOnePair(last1)) || (isThreeOfAKind(last2) && isOnePair(first2)))
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
		if (cards.length < 5){
			return false;
		}
		int counter = 0;
		Poker last = cards[0];
		for (int i=1; i<cards.length; i++)
		{
			if (cards[i].getNumber() == last.getNumber())
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

	private boolean isRoyalStraightFlush(Poker[] cards)
	{
		if (cards.length < 5){
			return false;
		}
		int[] check = {1, 10, 11, 12, 13};
		if (!isFlush(cards))
		{
			return false;
		}
		else
		{
			for (int i=0; i<cards.length; i++)
			{
				if (cards[i].getNumber() != check[i])
				{
					return false;
				}
			}
			return true;
		}
	}

}

















