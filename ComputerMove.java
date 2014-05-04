/*
	This class mainly includes the methods the computer will take after it has 
	received its hand, including split the 7 cards into 5+2
	And how to identify the 5 cards out of 7. 
*/
import java.util.*;
public class ComputerMove
{
	public static void computerSplitCard(Poker[] cards)
	{
		Arrays.sort(cards);
		Poker[] highHand = new Poker[5];
		Poker[] lowHand = new Poker[2];
		int maxType = 0;
		int lowRange = 0;
		CheckCards checker;
		for (int i=0; i<3; i++)
		{
			Poker[] subCards = Arrays.copyOfRange(cards, i, i+5);
			checker = new CheckCards(subCards);
			if (checker.getPokerType() > maxType)
			{
				maxType = checker.getPokerType();
				highHand = subCards;
				lowRange = i;
			}
		}
		for (int i=0; i<lowRange; i++)
		{
			lowHand[i] = cards[i];
		}
		for (int i=lowRange+5; i<cards.length; i++)
		{
			lowHand[i-5] = cards[i];
		}

		// Rearrange the sequence of cards
		for (int i=0; i<highHand.length; i++)
		{
			cards[i] = highHand[i];
		}
		for (int i=5; i<lowHand.length; i++)
		{
			cards[i] = lowHand[i-5];
		}


	}

}