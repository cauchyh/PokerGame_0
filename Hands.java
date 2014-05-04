/*
	A class describing a hand of fourteen cards
	where each half of it represents the cards of the user and the computer
*/

import java.util.*;

public class Hands implements
{
	Poker[] hand = new Poker[14];
	public Hands()
	{
		generateHands(hand);
	}

	/**
	 * @return return an array of Poker, representing the cards of the user
	 */
	public Poker[] getUserCards()
	{
		return Arrays.copyOfRange(hand, 0, 7);
	}

	/**
	 * @return return an array of Poker, representing the cards of the computer
	 */
	public Poker[] getComputerCards()
	{
		return Arrays.copyOfRange(hand, 7, 14);
	}

	@Override
	public int compareTo(Hands hand)
	{

	}

	//   Calculate the points of the cards, higher ranked cards will have a higher points
	// 	In this algorithm, different hands of combinations are not specifically pointed as string
	// 	In particular, higher rank will have exponanially higher points
	
	// public int getFiveCardPoints(Poker[] cards)
	// {

	// }

	private void generateHands(Poker[] hand)
	{
		Random ranGenerator = new Random();
		Set<Poker> pokerSet = new HashSet<Poker>(); // use to check duplicated cards
		char[] suits = {'s', 'h', 'd', 'c'};
		int index = 0;
		Poker temp;
		while (index < 14)
		{
			// generating exclusive 14 cards 
			int suitNum = ranGenerator.nextInt(4);
			int number = ranGenerator.nextInt(13) + 1;
			temp = new Poker(suits[suitNum], number);
			if (!set.contains(temp))
			{
				hand[index] = temp;
				set.add(temp);
				index ++;
			}
		}

	}


}