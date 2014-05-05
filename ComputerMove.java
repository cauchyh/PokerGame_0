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
		// Poker[] result = cards.clone();
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
		// test the result
		// System.out.println("maxtype: " + maxType);
		// for (Poker temp : lowHand) 
		// {
		// 	System.out.println(temp.getNumber());
		// }

		// Rearrange the sequence of cards
		for (int i=0; i<highHand.length; i++)
		{
			cards[i] = highHand[i];
		}
		for (int i=0; i<lowHand.length; i++)
		{
			cards[i+5] = lowHand[i];
		}
	}



	/**
	 *  @param userCards - splited 7 user cards, 5 for high hand, rest 2 for low hand
	 *  @param computerCards - splited 7 computer cards, 5 for high hand, rest 2 for low hand
	 *	@return 1 - indicates the user wins
	 *          0 - draw game
	 *		   -1 - computer win 
	 */
	public static int compareCards(Poker[] userCards, Poker[] computerCards)
	{
		CheckCards userHighHand = new CheckCards(Arrays.copyOfRange(userCards, 0, 5));
		CheckCards userLowHand = new CheckCards(Arrays.copyOfRange(userCards, 5, 7));
		CheckCards computerHighHand = new CheckCards(Arrays.copyOfRange(computerCards, 0, 5));
		CheckCards computerLowHand = new CheckCards(Arrays.copyOfRange(computerCards, 5, 7));
		// System.out.println("userLowHand Type:" + userLowHand.getPokerType());
		// System.out.println("computerLowHand Type:" + computerLowHand.getPokerType());
		/*
			Compare the high hand of the user and the computer first,
			The higher wins the round one. 
			During both high and low of comparasions, compare the card type first,
			if the card types are the same, compare the highest card. Draw if they 
			are still the same.
			Moreover, compare both high hand and the low hand. Only winning
			both hands could win the round.
			On each hand, ties go to the banker (for example, if a player's five-card 
			hand loses to the banker and his two-card hand ties the banker 
			then the player loses); this gives the banker a small advantage.
			If the player fouls his hand, meaning that the low hand outranks his high hand,
			or that there are an incorrect number of cards in each hand, 
			there will be a penalty: either re-arrangement of the hand according to house 
			rules or forfeiture of the hand.
		*/

		/*
			Define a userPoint in order to determine the final result. 
			According to the rule, a new rule is established here thus the final
			result can well fit the original card rule.
			User win: +1 points; Tie: +0 point; User lose: -1 points;
		*/
		int userPoints = 0;		
		// Compare the high hand first
		if (userHighHand.getPokerType() > computerHighHand.getPokerType()){
			userPoints += 1;
		} else if (userHighHand.getPokerType() < computerHighHand.getPokerType()){
			userPoints -= 1;
		} else if (userHighHand.getHighestCard() > computerHighHand.getHighestCard()){
			userPoints += 1;
		} else if (userHighHand.getHighestCard() < computerHighHand.getHighestCard()){
			userPoints -= 1;
		}
		// System.out.println("userPoints: " + userPoints);

		if (userLowHand.getPokerType() > computerLowHand.getPokerType()){
			userPoints += 1;
		} else if (userLowHand.getPokerType() < computerLowHand.getPokerType()){
			userPoints -= 1;
		} else if (userLowHand.getHighestCard() > computerLowHand.getHighestCard()){
			userPoints += 1;
		} else if (userLowHand.getHighestCard() < computerLowHand.getHighestCard()){
			userPoints -= 1;
		}
		// System.out.println("userPoints: " + userPoints);
		if (userPoints == 2){
			return 1;
		} else if (userPoints < 2 && userPoints >= 0){
			return 0;
		} else { 
		// tie one and lose one, the player lose the game
			return -1;
		}


	}

}













