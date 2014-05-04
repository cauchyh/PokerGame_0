
import java.util.Objects;

/*
 Define a poker card without Jokers
 */
public class Poker implements Comparable<Poker> {

    /**
     * @param suit - suit of the card, including spade, hearts, diamonds, clubs
     * @param number - is the pair of number
     */
    private String suit;
    private Integer number;

    public Poker(String suitInput, Integer numberInput) {
        suit = suitInput;
        number = numberInput;
    }

    /**
     * Convert the number to String
     *
     * @return make cases for J, Q, K
     */
    public String getNumberString() {
        if (2 <= getNumber() && getNumber() <= 10) {
            return Integer.toString(getNumber());
        } else {
            if (getNumber() == 1) {
                return "A";
            } else if (getNumber() == 11) {
                return "J";
            } else if (getNumber() == 12) {
                return "Q";
            } else if (getNumber() == 13) {
                return "K";
            } else {
                return null;
            }
        }
    }

    /**
     * Compare one card with another one
     *
     * @param card - another card
     * @return 1 - the original card is bigger 0 - two cards are equal -1 - the
     * original card is smaller
     */
    @Override
    public int compareTo(Poker card) {
        return this.number.compareTo(card.getNumber());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + number;
        result = prime * result + ((suit == null) ? 0 : suit.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Poker other = (Poker) obj;
        if (!Objects.equals(number, other.number)) {
            return false;
        }
        if (suit == null) {
            if (other.suit != null) {
                return false;
            }
        } else if (!suit.equals(other.suit)) {
            return false;
        }
        return true;
    }

    /**
     * Swap two poker
     *
     * @param a - one card
     * @param b - another card
     */
    public static void swapTwoPoker(Poker a, Poker b) {
        if (a.equals(b) == false) {
            Poker temp = new Poker(a.getSuit(), a.getNumber());
            a.setSuit(b.getSuit());
            a.setNumber(b.getNumber());
            b.setSuit(temp.getSuit());
            b.setNumber(temp.getNumber());
        }
    }

    /**
     * @return the suit
     */
    public String getSuit() {
        return suit;
    }

    /**
     * @return the number
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * @param suit the suit to set
     */
    public void setSuit(String suit) {
        this.suit = suit;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

}
