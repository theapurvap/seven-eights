// APURVA PATEL
// CS 6364 - 002
// Final Project

import java.lang.*;
import java.util.*;

public class SevenEights
{
	private Deck deck;

	public static void main(String[] args)
	{
		System.out.println("Hi!");
	}

	public SevenEights()
	{
		deck = new Deck();
	}

	public void game()
	{
		// TODO
	}
}

class Deck
{
	private ArrayList<Card> cards;

	public Deck()
	{
		cards = new ArrayList<Card>();

		cards.add(new Card(Suite.Spade, Rank.Seven));
		cards.add(new Card(Suite.Spade, Rank.Eight));
		cards.add(new Card(Suite.Spade, Rank.Nine));
		cards.add(new Card(Suite.Spade, Rank.Ten));
		cards.add(new Card(Suite.Spade, Rank.Jack));
		cards.add(new Card(Suite.Spade, Rank.Queen));
		cards.add(new Card(Suite.Spade, Rank.King));
		cards.add(new Card(Suite.Spade, Rank.Ace));
		cards.add(new Card(Suite.Heart, Rank.Seven));
		cards.add(new Card(Suite.Heart, Rank.Eight));
		cards.add(new Card(Suite.Heart, Rank.Nine));
		cards.add(new Card(Suite.Heart, Rank.Ten));
		cards.add(new Card(Suite.Heart, Rank.Jack));
		cards.add(new Card(Suite.Heart, Rank.Queen));
		cards.add(new Card(Suite.Heart, Rank.King));
		cards.add(new Card(Suite.Heart, Rank.Ace));
		cards.add(new Card(Suite.Club, Rank.Eight));
		cards.add(new Card(Suite.Club, Rank.Nine));
		cards.add(new Card(Suite.Club, Rank.Ten));
		cards.add(new Card(Suite.Club, Rank.Jack));
		cards.add(new Card(Suite.Club, Rank.Queen));
		cards.add(new Card(Suite.Club, Rank.King));
		cards.add(new Card(Suite.Club, Rank.Ace));
		cards.add(new Card(Suite.Diamond, Rank.Eight));
		cards.add(new Card(Suite.Diamond, Rank.Nine));
		cards.add(new Card(Suite.Diamond, Rank.Ten));
		cards.add(new Card(Suite.Diamond, Rank.Jack));
		cards.add(new Card(Suite.Diamond, Rank.Queen));
		cards.add(new Card(Suite.Diamond, Rank.King));
		cards.add(new Card(Suite.Diamond, Rank.Ace));

		shuffle();
	}

	private void shuffle()
	{
		ArrayList<Card> tempcards = new ArrayList<Card>();

		while(cards.size() > 0)
		{
			if (Math.random() < 0.5)
			{
				tempcards.add(cards.remove(0));
			}
			else
			{
				tempcards.add(cards.remove(cards.size() - 1));
			}
		}

		cards = tempcards;
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

enum Suite
{
	Spade, Heart, Club, Diamond
}

enum Rank
{
	Seven, Eight, Nine, Ten, Jack, Queen, King, Ace
}
