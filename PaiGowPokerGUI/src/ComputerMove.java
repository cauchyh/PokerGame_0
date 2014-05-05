/*
 This class mainly includes the methods the computer will take after it has 
 received its hand, including split the 7 cards into 5+2
 And how to identify the 5 cards out of 7. 
 */

import java.util.*;

public class ComputerMove {

    static CardsTypeChecker checker = new CardsTypeChecker();

    public static void computerSplitCard(Poker[] cards) {
        CardsTypeChecker.PokerType maxType;
        maxType = CardsTypeChecker.PokerType.HIGH_CARDS;
        int highestCard = 0;
        Poker[] highHand = new Poker[5];
	Poker[] lowHand = new Poker[2];
        int lowRange = 0;
        
        Arrays.sort(cards);
        
        for (int i = 0; i < 3; i++) {
            Poker[] subCards = Arrays.copyOfRange(cards, i, i + 5);
            checker.MakeCheck(subCards);
            CardsTypeChecker.PokerType subType = checker.getPokerType();
            
            if (subType.compareTo(maxType) > 0 || (subType.compareTo(maxType) == 0 && checker.getHighestCard() > highestCard)) {
                maxType = checker.getPokerType();
                highestCard = checker.getHighestCard();
                highHand = subCards;
                lowRange = i;
            }
        }
	
        // copy the low hand card into the array lowHand.
        // sine the the two cards might be seperated in head and tail
        for (int i = 0; i < lowRange; i++) {
            lowHand[i] = cards[i];
        }
        for (int i = lowRange + 5; i < cards.length; i++) {
            lowHand[i - 5] = cards[i];
        }

        // Rearrange the sequence of cards
        for (int i = 0; i < highHand.length; i++) {
            cards[i] = highHand[i];
        }
        for (int i = 0; i < lowHand.length; i++) {
            cards[i + 5] = lowHand[i];
        }

    }

    private static void printOut(Poker[] cards) {
        for (Poker card : cards) {
            System.out.print(card.getNumberString());
        }
        System.out.println();
    }

    /**
     *
     * @param userHighCards
     * @param userLowCards
     * @param computerHighCards
     * @param computerLowCards
     * @return the compare res
     */

    public static int compareCards(Poker[] userHighCards, Poker[] userLowCards, Poker[] computerHighCards, Poker[] computerLowCards) {
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
        checker.MakeCheck(userHighCards);
        CardsTypeChecker.PokerType userHighCardsTpye = checker.getPokerType();
        int userHighHeadHighestCards = checker.getHighestCard();
        System.out.println("userHighHeadHighestCards: " + userHighHeadHighestCards);

        checker.MakeCheck(userLowCards);
        CardsTypeChecker.PokerType userLowCardsTpye = checker.getPokerType();
        int userLowHeadHighestCards = checker.getHighestCard();
        System.out.println("userLowHeadHighestCards: " + userLowHeadHighestCards);

        checker.MakeCheck(computerHighCards);
        CardsTypeChecker.PokerType computerHighCardsTpye = checker.getPokerType();
        int computerHighHeadHighestCards = checker.getHighestCard();
        System.out.println("computerHighHeadHighestCards: " + computerHighHeadHighestCards);

        checker.MakeCheck(computerLowCards);
        CardsTypeChecker.PokerType computerLowCardsTpye = checker.getPokerType();
        int computerLowHeadHighestCards = checker.getHighestCard();
        System.out.println("computerLowHeadHighestCards: " + computerLowHeadHighestCards);

        if (userHighCardsTpye.compareTo(computerHighCardsTpye) > 0) {
            userPoints += 1;
        } else if (userHighCardsTpye.compareTo(computerHighCardsTpye) < 0) {
            userPoints -= 1;
        } else if (userHighHeadHighestCards > computerHighHeadHighestCards) {
            userPoints += 1;
        } else if (userHighHeadHighestCards < computerHighHeadHighestCards) {
            userPoints -= 1;
        }

        if (userLowCardsTpye.compareTo(computerLowCardsTpye) > 0) {
            userPoints += 1;
        } else if (userLowCardsTpye.compareTo(computerLowCardsTpye) < 0) {
            userPoints -= 1;
        } else if (userLowHeadHighestCards > computerLowHeadHighestCards) {
            userPoints += 1;
        } else if (userLowHeadHighestCards < computerLowHeadHighestCards) {
            userPoints -= 1;
        }

        // System.out.println("userPoints: " + userPoints);
        if (userPoints == 2) {
            return 1;
        } else if (userPoints < 2 && userPoints >= 0) {
            return 0;
        } else {
            // tie one and lose one, the player lose the game
            return -1;
        }
    }
}
