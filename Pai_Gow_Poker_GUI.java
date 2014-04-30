// Paul Fodor
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Scanner;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.util.Scanner;

public class Pai_Gow_Poker_GUI extends JFrame {
	int rounds = 0;
	int state = 0; 	// 0 is beginning of the round; 1 is after the 7 cards are displayed; 2 is after the user selects 1 card
					// 3 is after the user selects the second card and the winner is announced at the end of round 
	int purse = 50;
	JPanel p0 = new JPanel();
	JPanel p01 = new JPanel();
	JPanel p02 = new JPanel();
	JPanel p1 = new JPanel();
	JPanel p12 = new JPanel();
	JPanel p2 = new JPanel();
	JPanel p21 = new JPanel();
	JPanel p211 = new JPanel();
	JPanel p212 = new JPanel();
	JPanel p22 = new JPanel();
	JPanel p221 = new JPanel();
	JPanel p222 = new JPanel();
	JPanel p3 = new JPanel();
	JPanel p32 = new JPanel();
	JPanel p4 = new JPanel();
	JPanel p5 = new JPanel();
	JPanel p6 = new JPanel();
	JLabel l1 = new JLabel();
	JLabel l21 = new JLabel();
	JLabel l22 = new JLabel();
	JLabel[] icons1 = new JLabel[7];
	JLabel[] icons12 = new JLabel[2];
	JLabel[] icons2 = new JLabel[7];
	JLabel[] icons22 = new JLabel[2];
	int bet = 0;
	JLabel l41 = new JLabel();
	JButton b41 = new JButton("$1");
	JButton b42 = new JButton("$2");
	JButton b43 = new JButton("$3");
	JButton b44 = new JButton("$4");
	JButton b45 = new JButton("$5");
	JLabel l42 = new JLabel();
	JLabel l5 = new JLabel();
	JButton b51 = new JButton("Continue");
	JLabel l6 = new JLabel();
	int[][] previous_cards = new int[50][2];
	int nop = 0;
	int[][] hand1 = new int[7][2]; // human hand
	int[][] hand21 = new int[2][2]; // human hand
	int no1 = 0; //number of cards
	int[][] hand2 = new int[7][2]; // computer
	int[][] hand22 = new int[2][2]; // computer 2pair
	int no2 = 0;
	// Constructor
	Pai_Gow_Poker_GUI() {
		this.setSize(1000, 800);
		this.setLayout(new GridLayout(2, 1));
		setTitle("Spring 2014 Pai Gow Poker");
		this.add(p2);
		this.add(p3);
		p3.setLayout(new GridLayout(5, 1));
		p3.add(p0);
		p3.add(p1);
		p3.add(p4);
		p3.add(p5);
		p3.add(p6);
		//j11.setIcon(card_to_ImageIcon(sorted1[0]));
		rounds = 1;
		p0.setLayout(new GridLayout(1, 2));
		p0.add(p01);
		p0.add(p02);
		p01.add(l21);
		p02.add(l22);
		p01.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		p02.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		p1.add(l1);
		p2.setLayout(new GridLayout(2, 2));
		p2.add(p211);
		p2.add(p221);
		p2.add(p212);
		p2.add(p222);
		p211.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		p221.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		p212.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		p222.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		p211.setLayout(new GridLayout(1, 7));
		p221.setLayout(new GridLayout(1, 5));
		p4.add(l41);
		p4.add(b41);
		p4.add(b42);
		p4.add(b43);
		p4.add(b44);
		p4.add(b45);
		p4.add(l42);
		l41.setText("Bet: ");
		p5.add(l5);
		l5.setText("Action: ");
		p5.add(b51);
		p6.add(l6);
		//addActionListerner means whenever an event occurs, do this function, there's an addActionListener object
		//when the event happens, it calls action performed in the actionListenerObject
		//ActionListener() is an anonymous object because it takes name straight from the object name
		b41.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshDisplay("b41");
			}
		});
		b42.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshDisplay("b42");
			}
		});
		b43.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshDisplay("b43");
			}
		});
		b44.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshDisplay("b44");
			}
		});
		b45.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshDisplay("b45");
			}
		});
		b51.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshDisplay("b51");
			}
		});
		// ACTIONS
		l1.setText("Round " + rounds + "          " + "Action: continue");
		// generate player hand
		for(no1=0; no1<7; no1++){
			hand1[no1] = generate_card(previous_cards, nop);
			previous_cards[nop][0] = hand1[no1][0];
			previous_cards[nop][1] = hand1[no1][1];
			icons1[no1] = new JLabel();
			addMouseListener(icons1[no1],""+no1);
			icons1[no1].setIcon(card_to_ImageIcon(hand1[no1]));
			icons1[no1].setSize(10, 10);
			p211.add(icons1[no1]);
			nop++;
		}
		// generate computer hard
		int[][] hand2_temp = new int[7][2];
		for(no2=0; no2<7; no2++){
			hand2_temp[no2] = generate_card(previous_cards, nop);
			previous_cards[nop][0] = hand2_temp[no2][0];
			previous_cards[nop][1] = hand2_temp[no2][1];
			nop++;
		}
		hand2_temp = sort_hand(hand2_temp);
		// extract the 2-pair (smallest 2 cards)
		hand22[0] = hand2_temp[0];
		hand22[1] = hand2_temp[1];
		icons22[0] = new JLabel();
		icons22[0].setIcon(card_to_ImageIcon(hand22[0]));
		p222.add(icons22[0]);
		icons22[1] = new JLabel();
		icons22[1].setIcon(card_to_ImageIcon(hand22[1]));
		p222.add(icons22[1]);
		// extract the 5 high cards hand
		for(no2=0; no2<5; no2++){
			hand2[no2] = hand2_temp[no2+2];
			icons2[no2] = new JLabel();
			icons2[no2].setIcon(card_to_ImageIcon(hand2[no2]));
			p221.add(icons2[no2]);
		}
		l21.setText("User - purse=" + purse);
		l22.setText("Computer");
		bet = 1;
		l42.setText("Bet: $" + bet);
		l6.setText("Result:        => New purse: $   ");
		pack();
		setVisible(true);
		repaint();
	}
	
	public void addMouseListener(JLabel l, final String s){
		l.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
            	refreshDisplay(s); // To do: remove card no1 from hand1 and put it in hand12 (the 2-pair hand for the user) 
            }
			@Override
			public void mouseClicked(MouseEvent arg0) {}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mousePressed(MouseEvent arg0) {}
		});
	}
	

	public static void main(String[] args) throws IOException {
		Pai_Gow_Poker_GUI pg = new Pai_Gow_Poker_GUI();
	}

	public void refreshDisplay(String option) {
		System.out.println("Option: " + option);
		if (option.equals("0") || option.equals("1") || option.equals("2") || option.equals("3") || option.equals("4") ||
				option.equals("5") || option.equals("6")){
			// To do: remove card no1 from hand1 and put it in hand12 (the 2-pair hand for the user)
			
		} else if (option.equals("b41") || option.equals("b42") || option.equals("b43") || option.equals("b44") || option.equals("b45")) {
			// implement betting
			if (option.equals("b41")) {
				bet = 1;
			} else if (option.equals("b42")) {
				bet = 2;
			} else if (option.equals("b43")) {
				bet = 3;
			} else if (option.equals("b44")) {
				bet = 4;
			} else {
				bet = 5;
			}
			l42.setText("Bet: $" + bet);
			b41.disable();
			b42.disable();
			b51.enable();
			//b6.disable();
		} else if (option.equals("b51") && state == 0) { // To do: Where your continue happens

		} else if (option.equals("b51") && state == 3) { 
			state = 0;
			if (rounds == 10) {
				System.exit(1);
			}
			rounds++;
			no1 = 0;
			no2 = 0;
			nop = 0; 
			p211.removeAll();
			p221.removeAll();
			// ACTIONS
			l1.setText("Round " + rounds + "          " + "Action: continue");
			l6.setText("Result:        => New purse: $   ");
		} else {
			System.out.println("Invalid option: " + option);
		}
		repaint();
	}

	public static ImageIcon card_to_ImageIcon(int[] c) {
		String fileString = "images/Playing_card_";
		if (c[1] == 1) {
			fileString += "heart";
		} else if (c[1] == 2) {
			fileString += "diamond";
		} else if (c[1] == 3) {
			fileString += "club";
		} else {
			fileString += "spade";
		}
		fileString += "_";
		if (2 <= c[0] && c[0] <= 10) {
			fileString += c[0];
		} else if (c[0] == 11) {
			fileString += "J";
		} else if (c[0] == 12) {
			fileString += "Q";
		} else if (c[0] == 13) {
			fileString += "K";
		} else {
			fileString += "A";
		}
		fileString += ".jpg";
		ImageIcon card_image = new ImageIcon(fileString);
		return card_image;
	}

	public static int[] generate_card(int[][] previous_cards, int nop) {
		boolean duplicate = false;
		int[] card = new int[2];
		do {
			duplicate = false;
			card[0] = (int) (Math.random() * 13 + 2);
			card[1] = (int) (Math.random() * 4 + 1);
			// compare all the previous hands with the current hand
			for (int i = 0; i < nop; i++) {
				if (card[0] == previous_cards[i][0] && card[1] == previous_cards[i][1]) {
					duplicate = true;
				}
			}
		} while (duplicate);
		return card;
	}

	public static int[] generate_card() {
		int[] card = new int[2];
		card[0] = (int) (Math.random() * 13 + 2);
		card[1] = (int) (Math.random() * 4 + 1);
		return card;
	}

	public static String card_to_String(int[] c) {
		String card = "";
		if (2 <= c[0] && c[0] <= 10) {
			card += c[0];
		} else if (c[0] == 11) {
			card += "Jack";
		} else if (c[0] == 12) {
			card += "Queen";
		} else if (c[0] == 13) {
			card += "king";
		} else if (c[0] == 14) {
			card += "Ace";
		} else {
			card += "card_to_String error: card number=" + c[0];
		}
		card += " of ";
		if (c[1] == 1) {
			card += "hearts";
		} else if (c[1] == 2) {
			card += "diamonds";
		} else if (c[1] == 2) {
			card += "clubs";
		} else {
			card += "spades";
		}
		return card;
	}

	// compare_2_cards returns: -1 for card1<card2; 0 for card1=card2 ; 1 for card1>card2
	public static int compare_2_cards(int[] card1, int[] card2){
		if(card1[1]<card2[1])
			return -1;
		else if(card1[1]>card2[1])
			return 1;
		else{
			// the two cards are the same suit, so compare number
			if(card1[0]<card2[0])
				return -1;
			else if(card1[0]>card2[0])
				return 1;
			else 
				return 0; // the two cards are the same
		}
	}
	public static void print_hand(int[][] hand){
		System.out.print(card_to_String(hand[0])+", ");
		System.out.print(card_to_String(hand[1])+", ");
		System.out.print(card_to_String(hand[2])+", ");
		System.out.print(card_to_String(hand[3])+", ");
		System.out.print(card_to_String(hand[4]));
	}

	public static int[][] sort_hand(int[][] hand){
		int[][] sorted = new int[hand.length][2];
		for(int i=0; i<hand.length; i++){
			sorted[i][0]=hand[i][0];
			sorted[i][1]=hand[i][1];
		}
		for(int i=0; i<hand.length; i++){
			int minIndex = i;
			int[] min = new int[2];
			min[0] = sorted[i][0];
			min[1] = sorted[i][1];
			for(int j=i; j<hand.length; j++)
				if(min[0]>sorted[j][0]){
					minIndex = j;
					min[0] = sorted[j][0];
					min[1] = sorted[j][1];
				}
			sorted[minIndex][0] = sorted[i][0];
			sorted[minIndex][1] = sorted[i][1];
			sorted[i][0] = min[0];
			sorted[i][1] = min[1];
		}
		return sorted;
	}
	public static void print_identify_hand(int identify_hand){
		if(identify_hand==1)
			System.out.print("(straight flush)");
		else if(identify_hand==2)
			System.out.print("(four of a kind)");
		else if(identify_hand==3)
			System.out.print("(full house)");
		else if(identify_hand==3)
			System.out.print("(four of a kind)");
		else if(identify_hand==4)
			System.out.print("(flush)");
		else if(identify_hand==5)
			System.out.print("(straight)");
		else if(identify_hand==6)
			System.out.print("(three of a kind)");
		else if(identify_hand==7)
			System.out.print("(two pairs)");
		else if(identify_hand==8)
			System.out.print("(one pair)");
		else
			System.out.print("(nothing - high hand comparison)");
	}
	public static String identify_hand_String(int identify_hand){
		if(identify_hand==1)
			return("(straight flush)");
		else if(identify_hand==2)
			return("(four of a kind)");
		else if(identify_hand==3)
			return("(full house)");
		else if(identify_hand==3)
			return("(four of a kind)");
		else if(identify_hand==4)
			return("(flush)");
		else if(identify_hand==5)
			return("(straight)");
		else if(identify_hand==6)
			return("(three of a kind)");
		else if(identify_hand==7)
			return("(two pairs)");
		else if(identify_hand==8)
			return("(one pair)");
		else
			return("(nothing - high hand comparison)");
	}
	public static int compare_hands(int[][] hand1,int[][] hand2){
		int identify_hand1 = identify_hand(hand1);
		//print_identify_hand(identify_hand1);
		int identify_hand2 = identify_hand(hand2);
		//print_identify_hand(identify_hand2);
		if(identify_hand1<identify_hand2)
			return -1;
		else if(identify_hand1>identify_hand2)
			return 1;
		else{ // we have the same poker hand type
			// 1-straight flush
			if(identify_hand1==1){
				// identify the higher pair in hand1
				int pair_number1, pair_number2;
				pair_number1=hand1[4][0];
				pair_number2=hand2[4][0];
				if(pair_number1<pair_number2){ // compare the card number
					return 1;
				}else if(pair_number1>pair_number2){
					return -1;
				}else{
					return 0;
				}
			} else
			// 2-4 of a kind
			if(identify_hand1==2){
				// identify the higher pair in hand1
				int pair_number1, pair_number2;
				pair_number1=hand1[2][0];
				pair_number2=hand2[2][0];
				if(pair_number1<pair_number2){ // compare the card number
					return 1;
				}else if(pair_number1>pair_number2){
					return -1;
				}else{
					return 0;
				}
			} else
			// 3-full house: 3 of a kind + of a kind 2
			if(identify_hand1==3){
				// identify the higher pair in hand1
				int pair_number1, pair_number2;
				if(hand1[0][0]==hand1[1][0] && hand1[1][0]==hand1[2][0] && hand1[3][0]==hand1[4][0]) // 3-2
					pair_number1=hand1[0][0];
				else pair_number1=hand1[4][0];
				if(hand2[0][0]==hand2[1][0] && hand2[1][0]==hand2[2][0] && hand2[3][0]==hand2[4][0]) // 3-2
					pair_number2=hand2[0][0];
				else pair_number2=hand2[4][0];
				if(pair_number1<pair_number2){ // compare the card number
					return 1;
				}else if(pair_number1>pair_number2){
					return -1;
				}else{
					return 0;
				}
			} else
			// 4-flush
			if(identify_hand1==4){
				// identify the higher pair in hand1
				int pair_number1, pair_number2;
				pair_number1=hand1[4][0];
				pair_number2=hand2[4][0];
				if(pair_number1<pair_number2){ // compare the card number
					return 1;
				}else if(pair_number1>pair_number2){
					return -1;
				}else{
					return 0;
				}
			} else
			// 5-Straight
			if(identify_hand1==5){
				// identify the higher pair in hand1
				int pair_number1, pair_number2;
				pair_number1=hand1[4][0];
				pair_number2=hand2[4][0];
				if(pair_number1<pair_number2){ // compare the card number
					return 1;
				}else if(pair_number1>pair_number2){
					return -1;
				}else{
					return 0;
				}
			} else
			// 6-three of a kind
			if(identify_hand1==6){
				// identify the higher pair in hand1
				int pair_number1, pair_number2;
				pair_number1=hand1[2][0];
				pair_number2=hand2[2][0];
				if(pair_number1<pair_number2){ // compare the card number
					return 1;
				}else if(pair_number1>pair_number2){
					return -1;
				}else{
					return 0;
				}
			} else
			// 7-two pairs
			if(identify_hand1==7){
				// identify the higher pair in hand1
				int pair_number1, pair_number2;
				pair_number1=hand1[3][0];
				pair_number2=hand2[3][0];
				if(pair_number1<pair_number2){ // compare the card number
					return 1;
				}else if(pair_number1>pair_number2){
					return -1;
				}else{
					return 0;
				}
			} else
			// 8-one pair
			if(identify_hand1==8){
				// identify the pair one number
				int pair_number1, pair_number2;
				if(hand1[0][0]==hand1[1][0]) // 2-1-1-1 
					pair_number1=hand1[0][0];
				else if(hand1[1][0]==hand1[2][0]) // 1-2-1-1
					pair_number1=hand1[1][0];
				else if(hand1[2][0]==hand1[3][0]) // 1-1-2-1 
					pair_number1=hand1[2][0];
				else pair_number1=hand1[3][0];
				// identify the pair 2 number
				if(hand2[0][0]==hand2[1][0]) // 2-1-1-1 
					pair_number2=hand2[0][0];
				else if(hand2[1][0]==hand2[2][0]) // 1-2-1-1
					pair_number2=hand2[1][0];
				else pair_number2=hand2[3][0];
				if(pair_number1<pair_number2){ // compare the card number
					return 1;
				}else if(pair_number1>pair_number2){
					return -1;
				}else{
					return 0;
				}
			} else
			// 9-high card
			if(identify_hand1==9){
				if(hand1[4][0]<hand2[4][0]){ // compare the card number
					return 1;
				}else if(hand1[4][0]>hand2[4][0]){
					return -1;
				}else{ // same card number - compare suite
					if(hand1[4][1]<hand2[4][1]){ // compare suite
						return 1;
					}else if(hand1[4][1]>hand2[4][1]){
						return -1;
					}	
					return 0;
				}
			}
			return 1;
		}
	}
	// return if we identify any poker hand: 1 Straight flush; 2 Four of a kind; 3 Full house; 4 Flush; 5 Straight; 6 Three of a kind; 7 Two pair; 8 One pair; 9 High card
	public static int identify_hand(int[][] hand){
		// consider that the hand is already sorted
		// 1-Straight flush
		if(hand[0][1]==hand[1][1] && hand[1][1]==hand[2][1] && hand[2][1]==hand[3][1] && hand[3][1]==hand[4][1] && // compare that they have the same suit 
				hand[0][0]+1==hand[1][0] && hand[1][0]+1==hand[2][0] && hand[2][0]+1==hand[3][0] && hand[3][0]+1==hand[4][0]) // compare card numbers
			return 1;
		// 2-four of a kind
		//   the cards are ordered, so the first or the last cards can be different
		if(hand[0][0]==hand[1][0] && hand[1][0]==hand[2][0] && hand[2][0]==hand[3][0]) // compare card numbers
			return 2;
		if(hand[1][0]==hand[2][0] && hand[2][0]==hand[3][0] && hand[3][0]==hand[4][0]) // compare card numbers
			return 2;
		// 3-full house: 3 of a kind + of a kind 2
		//   the cards are ordered, so we can have 2 kinds of full houses: 3-2 or 2-3 
		if(hand[0][0]==hand[1][0] && hand[1][0]==hand[2][0] && hand[3][0]==hand[4][0]) // 3-2
			return 3;
		if(hand[0][0]==hand[1][0] && hand[2][0]==hand[3][0] && hand[3][0]==hand[4][0]) // 2-3
			return 3;
		// 4-flush
		if(hand[0][1]==hand[1][1] && hand[1][1]==hand[2][1] && hand[2][1]==hand[3][1] && hand[3][1]==hand[4][1]) // compare that they have the same suit 
			return 4;
		// 5-Straight
		if(hand[0][0]+1==hand[1][0] && hand[1][0]+1==hand[2][0] && hand[2][0]+1==hand[3][0] && hand[3][0]+1==hand[4][0]) // compare card numbers
			return 5;		
		// 6-three of a kind
		//   the cards are ordered, so we can have: 3-1-1 or 1-3-1 or 1-1-3
		if(hand[0][0]==hand[1][0] && hand[1][0]==hand[2][0]) // 3-1-1
			return 6;
		if(hand[1][0]==hand[2][0] && hand[2][0]==hand[3][0]) // 1-3-1
			return 6;
		if(hand[2][0]==hand[3][0] && hand[3][0]==hand[4][0]) // 3-1-1
			return 6;
		// 7-two pairs
		//   the cards are ordered, so we can have: 2-2-1 or 2-1-2 or 1-2-2
		if(hand[0][0]==hand[1][0] && hand[2][0]==hand[3][0]) // 2-2-1
			return 7;
		if(hand[0][0]==hand[1][0] && hand[3][0]==hand[4][0]) // 2-1-2
			return 7;
		if(hand[1][0]==hand[2][0] && hand[3][0]==hand[4][0]) // 1-2-2
			return 7;
		// 8-one pair
		//   the cards are ordered, so we can have: 2-1-1-1 or 1-2-1-1 or 1-1-2-1 or 1-1-1-2
		if(hand[0][0]==hand[1][0]) // 2-1-1-1 
			return 8;
		if(hand[1][0]==hand[2][0]) // 1-2-1-1
			return 8;
		if(hand[2][0]==hand[3][0]) // 1-1-2-1 
			return 8;
		if(hand[3][0]==hand[4][0]) // 1-1-1-2 
			return 8;
		// 9-nothing
		return 9;
	}
}
