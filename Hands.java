/*
	A class describing a hand of seven cards
*/

import java.util.*;

public class Hands implements Comparable<Hands>
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

	public int getPokerPoints()
	{

	}

	private void generateHands(Poker[] hand)
	{
		Random ranGenerator = new Random();
		Set<Poker> pokerSet = new HashSet<Poker>(); // use to check duplicated cards
		char[] suits = {'s', 'h', 'd', 'c'};
		int index = 0;
		Poker temp;
		while (index < 14)
		{
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