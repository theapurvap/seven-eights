// APURVA PATEL
// CS 6364 - 002
// Final Project

import java.lang.*;
import java.util.*;

public class SevenEights
{
    private Deck deck;
    private Suite masterSuite;
    private ArrayList<Pair> pairs;

    public static void main(String[] args)
    {
        System.out.println("Hi!");
        
        SevenEights game = new SevenEights();
    }

    public SevenEights()
    {
        deck = new Deck();
        masterSuite = Suite.values()[(int) (Math.random() * Suite.values().length)];
        pairs = new ArrayList<>();
    }

    public void game()
    {
        // TODO
    }
}

class Player
{
    private Suite masterSuite;
    private ArrayList<Card> hand;
    
    public Player(Suite masterSuite)
    {
        this.masterSuite = masterSuite;
        hand = new ArrayList<>();
    }
}

class AI
{
    private Suite masterSuite;
    private ArrayList<Card> hand;
    private ArrayList<Card> possibleOpponentCards;
    
    public AI(Suite masterSuite)
    {
        this.masterSuite = masterSuite;
        hand = new ArrayList<>();
        
        possibleOpponentCards.add(new Card(Suite.Spade, Rank.Seven));
        possibleOpponentCards.add(new Card(Suite.Spade, Rank.Eight));
        possibleOpponentCards.add(new Card(Suite.Spade, Rank.Nine));
        possibleOpponentCards.add(new Card(Suite.Spade, Rank.Ten));
        possibleOpponentCards.add(new Card(Suite.Spade, Rank.Jack));
        possibleOpponentCards.add(new Card(Suite.Spade, Rank.Queen));
        possibleOpponentCards.add(new Card(Suite.Spade, Rank.King));
        possibleOpponentCards.add(new Card(Suite.Spade, Rank.Ace));
        possibleOpponentCards.add(new Card(Suite.Heart, Rank.Seven));
        possibleOpponentCards.add(new Card(Suite.Heart, Rank.Eight));
        possibleOpponentCards.add(new Card(Suite.Heart, Rank.Nine));
        possibleOpponentCards.add(new Card(Suite.Heart, Rank.Ten));
        possibleOpponentCards.add(new Card(Suite.Heart, Rank.Jack));
        possibleOpponentCards.add(new Card(Suite.Heart, Rank.Queen));
        possibleOpponentCards.add(new Card(Suite.Heart, Rank.King));
        possibleOpponentCards.add(new Card(Suite.Heart, Rank.Ace));
        possibleOpponentCards.add(new Card(Suite.Club, Rank.Eight));
        possibleOpponentCards.add(new Card(Suite.Club, Rank.Nine));
        possibleOpponentCards.add(new Card(Suite.Club, Rank.Ten));
        possibleOpponentCards.add(new Card(Suite.Club, Rank.Jack));
        possibleOpponentCards.add(new Card(Suite.Club, Rank.Queen));
        possibleOpponentCards.add(new Card(Suite.Club, Rank.King));
        possibleOpponentCards.add(new Card(Suite.Club, Rank.Ace));
        possibleOpponentCards.add(new Card(Suite.Diamond, Rank.Eight));
        possibleOpponentCards.add(new Card(Suite.Diamond, Rank.Nine));
        possibleOpponentCards.add(new Card(Suite.Diamond, Rank.Ten));
        possibleOpponentCards.add(new Card(Suite.Diamond, Rank.Jack));
        possibleOpponentCards.add(new Card(Suite.Diamond, Rank.Queen));
        possibleOpponentCards.add(new Card(Suite.Diamond, Rank.King));
        possibleOpponentCards.add(new Card(Suite.Diamond, Rank.Ace));
    }
    
    public void deal(Card card)
    {
        UpdatePossibleOpponentCards(card);
        hand.add(card);
    }
    
    public Card firstMove()
    {
        
    }
    
    public void firstMoveResult(Card opponentCard)
    {
        UpdatePossibleOpponentCards(opponentCard);
    }
    
    public Card secondMove(Card opponentCard)
    {
        UpdatePossibleOpponentCards(opponentCard);        
    }
    
    private void UpdatePossibleOpponentCards(Card card)
    {
        for(int i = 0; i < possibleOpponentCards.size(); i++)
        {
            if(possibleOpponentCards.get(i).suite == card.suite && possibleOpponentCards.get(i).rank == card.rank)
            {
                possibleOpponentCards.remove(i);
            }
        }
    }
    
    private double firstMoveCalculation(Card card)
    {
        int possiblePairs = 0;
        
        for(int i = 0; i < possibleOpponentCards.size(); i++)
        {
            if(card.suite == masterSuite && possibleOpponentCards.get(i).suite != masterSuite)
            {
                possiblePairs++;
            }
            else
            {
                if(card.suite != masterSuite && possibleOpponentCards.get(i).suite == masterSuite)
                {
                    return card.rank.ordinal() > possibleOpponentCards.get(i).rank.ordinal();
                }
            }
        }
    }
    
    private double SecondMoveCalculation(Card opponentCard)
    {
        
    }
}

class Deck
{
    private ArrayList<Card> cards;

    public Deck()
    {
        cards = new ArrayList<>();

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
        ArrayList<Card> tempcards = new ArrayList<>();

        while(cards.size() > 0)
        {
            tempcards.add(cards.remove((int) (Math.random() * cards.size())));
        }

        cards = tempcards;
    }
    
    public Card draw()
    {
        if(cards.size() > 0)
        {
            return cards.remove(0);
        }
        else
        {
            return null;
        }
    }
}

class Card
{
    public final Suite suite;
    public final Rank rank;

    public Card(Suite suite, Rank rank)
    {
        this.suite = suite;
        this.rank = rank;
    }
}

class Pair
{
    private final Boolean playerMove;
    private final Suite masterSuite;
    private final Card playerCard;
    private final Card aiCard;
    
    public Pair(Boolean playerMove, Suite masterSuite, Card playerCard, Card aiCard)
    {
        this.playerMove = playerMove;
        this.masterSuite = masterSuite;
        this.playerCard = playerCard;
        this.aiCard = aiCard;
    }
    
    public boolean playerWin()
    {
        if(playerCard.suite == masterSuite && aiCard.suite != masterSuite)
        {
            return true;
        }
        else if(playerCard.suite != masterSuite && aiCard.suite == masterSuite)
        {
            return false;
        }
        else
        {
            if(playerCard.suite == aiCard.suite)
            {
                return playerCard.rank.ordinal() > aiCard.rank.ordinal();
            }
            else
            {
                return playerMove;
            }
        }
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