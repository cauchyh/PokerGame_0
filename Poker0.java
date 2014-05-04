/*
	Define a poker card without Jokers
*/

public class Poker implements Comparable<Poker>
{
	/**
	 * @param suit - suit of the card, including spade, hearts, diamonds, clubs
	 * @param number - number of the card
	 */
	char suit;
	int number;
	public Poker()
	{
		this('c', 2);
		// System.out.println("tag");
	}

	public Poker(char suitInput, int numberInput)
	{
		suit = suitInput;
		number = numberInput;
	}

	public void disPlay()
	{
		System.out.println(suit + number);
	}

	/**
	 * Compare one card with another one
	 * @param card - another card
	 * @return 1 - the original card is bigger
	 * 		   0 - two cards are equal
	 *		  -1 - the original card is smaller
	 */
	@Override
	public int compareTo(Poker card)
	{
		if (number > card.number)
		{
			return 1;
		}
		else if (number == card.number)
		{
			return 0;
		}
		else // number < card.number
		{
			return -1;
		}
	}
}