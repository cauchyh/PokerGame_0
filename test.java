// Use to test some of the basic functions of the program

import java.util.*;
// import 
public class test
{
	public static void main(String[] args)
	{
		Poker[] cards = new Poker[7];
		cards[0] = new Poker("a", 2);
		cards[1] = new Poker("sspade", 2);
		cards[2] = new Poker("spdade", 3);
		cards[3] = new Poker("spagde", 4);
		cards[4] = new Poker("c", 6);
		// System.out.println();
		cards[5] = new Poker("spadde", 7);
		cards[6] = new Poker("spadeg", 7);
		CheckCards checker = new CheckCards(cards);
		ComputerMove.computerSplitCard(cards);

		System.out.println("cardType: " + checker.getPokerType());
		System.out.println("highestcard: " + checker.getHighestCard());

		// Poker[] cards0 = new Poker[7];
		// cards0[0] = new Poker("c", 11);
		// cards0[1] = new Poker("c", 11);
		// cards0[2] = new Poker("spade", 1);
		// cards0[3] = new Poker("spade", 1);
		// cards0[4] = new Poker("c", 11);
		// cards0[5] = new Poker("spade", 12);
		// cards0[6] = new Poker("spade", 12);

		// // ComputerMove.computerSplitCard(cards0);
		
		// // int res = ComputerMove.compareCards(cards, cards0);
		// // System.out.println();
		// // System.out.println(res);
		for (Poker temp : cards)
		{
			System.out.println(temp.getNumber());
		}


		// PokersBuilder build1 = new PokersBuilder();
		// Poker[] hand = build1.getPokersArray();
		// Poker[] user = hand.getUserCards(); // the card of the user
		// CheckCards checker = new CheckCards(cards);
		// System.out.println(checker.getPokerType());
		// System.out.println(checker.getHighestCard());
		// checker()
		// System.out.println(hand[0].);
		// for (Poker temp : hand)
		// {
		// 	System.out.println(temp.getNumber());
		// }
		// Poker[] cards = new Poker[3];
		// cards[0] = new Poker();
		// cards[1] = new Poker();
		// cards[2] = new Poker();
		// cards[0].number = 21;
		// cards[1].number = 13;
		// cards[2].number = 10;
		// Arrays.sort(cards);
		// System.out.println(cards[0].number);
	}
}