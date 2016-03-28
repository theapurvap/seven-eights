// APURVA PATEL
// CS 6364 - 002
// Final Project

import java.lang.*;
import java.util.*;

public class SevenEights
{
	public static void main(String[] args)
	{
		System.out.println("Hi!");
	}
}

class Deck
{
	private Card[] cards;

	public Deck()
	{
		cards = new Card[30];

		cards[0] = new Card(Suite.Spade, Rank.7);
		cards[1] = new Card(Suite.Spade, Rank.8);
		cards[2] = new Card(Suite.Spade, Rank.9);
		cards[3] = new Card(Suite.Spade, Rank.10);
		cards[4] = new Card(Suite.Spade, Rank.Jack);
		cards[5] = new Card(Suite.Spade, Rank.Queen);
		cards[6] = new Card(Suite.Spade, Rank.King);
		cards[7] = new Card(Suite.Spade, Rank.Ace);
		cards[8] = new Card(Suite.Heart, Rank.7);
		cards[9] = new Card(Suite.Heart, Rank.8);
		cards[10] = new Card(Suite.Heart, Rank.9);
		cards[11] = new Card(Suite.Heart, Rank.10);
		cards[12] = new Card(Suite.Heart, Rank.Jack);
		cards[13] = new Card(Suite.Heart, Rank.Queen);
		cards[14] = new Card(Suite.Heart, Rank.King);
		cards[15] = new Card(Suite.Heart, Rank.Ace);
		cards[16] = new Card(Suite.Club, Rank.8);
		cards[17] = new Card(Suite.Club, Rank.9);
		cards[18] = new Card(Suite.Club, Rank.10);
		cards[19] = new Card(Suite.Club, Rank.Jack);
		cards[20] = new Card(Suite.Club, Rank.Queen);
		cards[21] = new Card(Suite.Club, Rank.King);
		cards[22] = new Card(Suite.Club, Rank.Ace);
		cards[23] = new Card(Suite.Diamond, Rank.8);
		cards[24] = new Card(Suite.Diamond, Rank.9);
		cards[25] = new Card(Suite.Diamond, Rank.10);
		cards[26] = new Card(Suite.Diamond, Rank.Jack);
		cards[27] = new Card(Suite.Diamond, Rank.Queen);
		cards[28] = new Card(Suite.Diamond, Rank.King);
		cards[29] = new Card(Suite.Diamond, Rank.Ace);

		shuffle();
	}

	private void shuffle()
	{
		int[][] order = new int[30][2];

		for(int i = 0; i < 30; i++)
		{
			order[i][0] = Math.round(Math.random() * 1000000);
			order[i][1] = i;
		}

		Arrays.sort(order, new Comparator<double[]>() {
		    public int compare(double[] card, double[] card) {
		        double random1 = card[0];
		        double random2 = card[0];
		        return random1.compareTo(random2);
		    }
		});

		Card[] temp = new Card[30];

		for(int i = 0; i < 30; i++)
		{
			temp[i] = cards[order[i][1]];
		}

		cards = temp;
	}
}

class Card
{
	private Suite suite;
	private Rank rank;

	public Card(Suite s, Rank r)
	{
		suite = s;
		rank = r;
	}

	public Suite getSuite()
	{
		return suite;
	}

	public Rank getRank()
	{
		return rank;
	}
}

public enum Suite
{
	Space, Heart, Club, Diamond
}

public enum Rank
{
	7, 8, 9, 10, Jack, Queen, King, Ace
}
