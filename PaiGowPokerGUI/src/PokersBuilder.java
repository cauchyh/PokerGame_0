/*
 A class describing a hand of fourteen cards
 where each half of it represents the cards of the user and the computer
 */

import java.util.*;

public class PokersBuilder {
    private final int POKERS_SIZE = 14;
    private final int HAND_SIZE = 7;
    private Poker[] pokersArray = new Poker[POKERS_SIZE];

    /** build the poker array
     *
     */
    public PokersBuilder() {
        generatePokers();
    }

    /**
     * @return return an array of Poker, representing the cards of the user
     */
    public Poker[] getUserCards() {
        return Arrays.copyOfRange(getPokersArray(), 0, HAND_SIZE);
    }

    /**
     * @return return an array of Poker, representing the cards of the computer
     */
    public Poker[] getComputerCards() {
        return Arrays.copyOfRange(getPokersArray(), HAND_SIZE, POKERS_SIZE);
    }

    public void generatePokers() {
        Set<Poker> pokerSet = new HashSet<>(); // use to check duplicated cards
        String[] suits = {"spade", "heart", "diamond", "club"};
        
        Poker temp;
        for( int index = 0 ; index < POKERS_SIZE ;) {
            // generating exclusive 14 cards 
            // the range for nexInt is [0 , n - 1] 
            temp = new Poker(suits[new Random().nextInt(4)], /* here set suit */
                             new Random().nextInt(13) + 1);  /* here set the number */ 
            if (pokerSet.contains(temp) == false) {
                pokersArray[index] = temp;
                pokerSet.add(temp);
                index++;
            }
        }
    }

    /**
     * @return the pokersArray
     */
    public Poker[] getPokersArray() {
        return pokersArray;
    }
}
