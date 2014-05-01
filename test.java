// Use to test some of the basic functions of the program

import java.util.*;
// import 
public class test
{
	public static void main(String[] args)
	{
		Poker[] cards = new Poker[3];
		cards[0] = new Poker();
		cards[1] = new Poker();
		cards[2] = new Poker();
		cards[0].number = 21;
		cards[1].number = 13;
		cards[2].number = 10;
		Arrays.sort(cards);
		System.out.println(cards[0].number);
	}
}