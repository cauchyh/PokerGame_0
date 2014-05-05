/*
 This class is used to check the combinations of a hand of cards.
 Further, this function defines some of index as for further comparation
 */

import java.util.*;

public class CardsTypeChecker {

    public static enum PokerType {

        HIGH_CARDS,
        ONE_PAIR,
        TWO_PAIR,
        THREE_OF_A_KIND,
        STRAIGHT,
        FLUSH,
        FULL_HOUSE,
        FOUR_OF_A_KIND,
        STRAIGHT_FLUSH,
        ROYAL_STRAIGHT_FLUSH,
    }
    /**
     * @param PokerType - indicating which poker does this hand of cards have
     * such as Flush, Full House, etc.
     * @param highestCard - indicating the highest single card of the hand in
     * case that the pokerTypes are same
     * @param checkerArray - deep copy from the poker array to the local member
     * variable, avoid the sort function make influence for the source data.
     */
    private Poker[] checkerArray;
    private PokerType pokerType;
    private int highestCard = 0;

    public void MakeCheck(Poker[] cards) {

        if (cards != null) {
            /*
             Attenation in here!
             the clone function is a SELECTVIE deep copy function!
             For primitive type(e.g. int, string) and the encapsulate object for primitive type(e.g. Interge)
             It makes deep copy.
             For other object, it makes shallow copy.
            
             The basic idea is the primitive types(and those encapsulate objects) in java are immutable, 
             through the clone just make shallow copy(copy the address), because the object are immutable, 
             the system will make new space for those object and not influence source object.
             */
            checkerArray = cards.clone();
            Arrays.sort(checkerArray);
            pokerType = checkPokerType(checkerArray);
            generateHighestCard();
        }
    }

    // return the two parameters
    public PokerType getPokerType() {
        return pokerType;
    }

    public int getHighestCard() {
        return highestCard;
    }

    public void generateHighestCard() {
        if (pokerType == PokerType.ROYAL_STRAIGHT_FLUSH || pokerType == PokerType.STRAIGHT_FLUSH || pokerType == PokerType.STRAIGHT
                || pokerType == PokerType.HIGH_CARDS || pokerType == PokerType.FLUSH) {
            if (checkerArray[0].getNumber() == 1) {
                highestCard = 14;
            } else {
                highestCard = checkerArray[checkerArray.length - 1].getNumber();
            }
        } else if (pokerType == PokerType.FOUR_OF_A_KIND || pokerType == PokerType.FULL_HOUSE || pokerType == PokerType.THREE_OF_A_KIND) {
            getHighestCardThreeFourKind(checkerArray);
        } else { // if (pokerType == ONE_PAIR || pokerType == TWO_PAIR)
            getHighestCardOnePairTwoPair(checkerArray);
        }
    }
    /*
     return the highest card of Three of a kind and Four of a kind
     */

    private void getHighestCardThreeFourKind(Poker[] cards) {
        // int counter = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (Poker card : cards) {
            if (!map.containsKey(card.getNumber())) {
                map.put(card.getNumber(), 1);
            } else {
                int newValue = map.get(card.getNumber()) + 1;
                map.put(card.getNumber(), map.get(card.getNumber()) + 1);
            }
        }
        for (Poker card : cards) {
            if (map.get(card.getNumber()) > 2) {
                highestCard =  card.getNumber();
            }
        }
    }

    /*
     Return the highest card of One Pair and Two Pair
     */
    private void getHighestCardOnePairTwoPair(Poker[] cards) {
        int res = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (Poker card : cards) {
            if (!map.containsKey(card.getNumber())) {
                map.put(card.getNumber(), 1);
            } else {
                map.put(card.getNumber(), map.get(card.getNumber()) + 1);
            }
        }
        for (Poker card : cards) {
            if (map.get(card.getNumber()) == 2) {
                if (card.getNumber() > res) {
                    res = card.getNumber();
                }
            }
        }
        highestCard =  res;
    }

    public PokerType checkPokerType(Poker[] cards) {
        if (isRoyalStraightFlush(cards)) {
            return PokerType.ROYAL_STRAIGHT_FLUSH;
        }
        if (isStraightFlush(cards)) {
            return PokerType.STRAIGHT_FLUSH;
        }
        if (isFourOfAKind(cards)) {
            return PokerType.FOUR_OF_A_KIND;
        }
        if (isFullHouse(cards)) {
            return PokerType.FULL_HOUSE;
        }
        if (isFlush(cards)) {
            return PokerType.FLUSH;
        }
        if (isStraight(cards)) {
            return PokerType.STRAIGHT;
        }
        if (isThreeOfAKind(cards)) {
            return PokerType.THREE_OF_A_KIND;
        }
        if (isTwoPair(cards)) {
            return PokerType.TWO_PAIR;
        }
        if (isOnePair(cards)) {
            return PokerType.ONE_PAIR;
        } else // if (isHighCards(cards))
        {
            return PokerType.HIGH_CARDS;
        }
    }

    /*
     Provide 9 method to determine which type a hand of pokers is.
     The 9 types are provided above as final static.
     */
    private boolean isHighCards(Poker[] cards) {
        return true;
    }

    private boolean isOnePair(Poker[] cards) {
        Poker last = cards[0];
        for (int i = 1; i < cards.length; i++) {
            if (Objects.equals(cards[i].getNumber(), last.getNumber())) {
                return true;
            } else {
                last = cards[i];
            }
        }
        return false;
    }

    public boolean isTwoPair(Poker[] cards) {
        if (cards.length < 5) {
            return false;
        }
        int counter = 0; // count whether it is "two" pair
        Poker last = cards[0];
        for (int i = 1; i < cards.length; i++) {
            if (Objects.equals(cards[i].getNumber(), last.getNumber())) {
                counter++;
                if (i < cards.length - 1) {
                    last = cards[i + 1];
                    i++;
                } else {
                    break;
                }
            } else {
                last = cards[i];
            }
        }
        return (counter == 2);
    }

    private boolean isThreeOfAKind(Poker[] cards) {
        if (cards.length < 5) {
            return false;
        }
        int counter = 0;
        Poker last = cards[0];
        for (int i = 1; i < cards.length; i++) {
            if (Objects.equals(cards[i].getNumber(), last.getNumber())) {
                counter++;
            } else {
                last = cards[i];
                counter = 0;
            }
            if (counter == 2) {
                return true;
            }
        }
        return false;
    }

    private boolean isStraight(Poker[] cards) {
        if (cards.length < 5) {
            return false;
        }
        if (cards[0].getNumber() == 1 && cards[1].getNumber() == 10) {// A, K, Q, J, 10 Straight	
            int[] check = {1, 10, 11, 12, 13};
            for (int i = 0; i < cards.length; i++) {
                if (cards[i].getNumber() != check[i]) {
                    return false;
                }
            }
            return true;
        } else {
            Poker last = cards[0];
            for (int i = 1; i < cards.length; i++) {
                if (last.getNumber() + 1 != cards[i].getNumber()) {
                    return false;
                } else {
                    last = cards[i];
                }
            }
            return true;
        }
    }

    private boolean isFlush(Poker[] cards) {
        if (cards.length < 5) {
            return false;
        }
        String suit = cards[0].getSuit();
        for (Poker card : cards) {
            if (!card.getSuit().equals(suit)) {
                return false;
            }
        }
        return true;
    }

    private boolean isFullHouse(Poker[] cards) {
        if (cards.length < 5) {
            return false;
        }
        Poker[] first1 = Arrays.copyOfRange(cards, 0, 3);
        Poker[] last1 = Arrays.copyOfRange(cards, 3, 5);
        Poker[] first2 = Arrays.copyOfRange(cards, 0, 2);
        Poker[] last2 = Arrays.copyOfRange(cards, 2, 5);
        return (isThreeOfAKind(first1) && isOnePair(last1)) || (isThreeOfAKind(last2) && isOnePair(first2));
    }

    private boolean isFourOfAKind(Poker[] cards) {
        if (cards.length < 5) {
            return false;
        }
        int counter = 0;
        Poker last = cards[0];
        for (int i = 1; i < cards.length; i++) {
            if (Objects.equals(cards[i].getNumber(), last.getNumber())) {
                counter++;
            } else {
                counter = 0;
                last = cards[i];
            }
        }
        return (counter == 3);
    }

    private boolean isStraightFlush(Poker[] cards) {
        return (isStraight(cards) && isFlush(cards));
    }

    private boolean isRoyalStraightFlush(Poker[] cards) {
        if (cards.length < 5) {
            return false;
        }
        int[] check = {1, 10, 11, 12, 13};
        if (!isFlush(cards)) {
            return false;
        } else {
            for (int i = 0; i < cards.length; i++) {
                if (cards[i].getNumber() != check[i]) {
                    return false;
                }
            }
            return true;
        }
    }
}
