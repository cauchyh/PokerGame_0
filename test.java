// Use to test some of the basic functions of the program

import java.util.*;
// import 
public class test
{
	public static void main(String[] args)
	{
		Poker[] cards = new Poker[5];
		cards[0] = new Poker("spade", 10);
		cards[1] = new Poker("spade", 12);
		cards[2] = new Poker("spade", 8);
		cards[3] = new Poker("spade", 9);
		cards[4] = new Poker("spade", 11);
		// PokersBuilder build1 = new PokersBuilder();
		// Poker[] hand = build1.getPokersArray();
		// Poker[] user = hand.getUserCards(); // the card of the user
		CheckCards checker = new CheckCards(cards);
		System.out.println(checker.getPokerType());
		System.out.println(checker.getHighestCard());
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