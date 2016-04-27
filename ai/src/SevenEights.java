// APURVA PATEL
// CS 6364 - 002
// Final Project

import java.util.*;

public class SevenEights
{
    private final Deck deck;
    private final Suit masterSuit;
    private final ArrayList<Pair> pairs;
    private final Player player;
    private final AI ai;

    public static void main(String[] args)
    {
        SevenEights game = new SevenEights();
        game.game();
    }

    public SevenEights()
    {
        deck = new Deck();
        masterSuit = Suit.values()[(int) (Math.random() * Suit.values().length)];
        pairs = new ArrayList<>();
        player = new Player(masterSuit);
        ai = new AI(masterSuit);
        
        for(int i = 0; i < 5; i++)
        {
            player.deal(deck.draw());
            ai.deal(deck.draw());
        }
        
        System.out.println("-------------------------------");
        System.out.println("|                             |");
        System.out.println("|   S E V E N - E I G H T S   |");
        System.out.println("|                             |");
        System.out.println("-------------------------------");
        System.out.println();
        System.out.println("Master Suit: " + masterSuit);
        System.out.println();        
    }

    public void game()
    {
        double coinToss = Math.random();
        
        if(coinToss < 0.5)
        {
            System.out.println("Player goes first!");
            
            Card playerCard = player.firstMove();
            player.deal(deck.draw());
            Card aiCard = ai.secondMove(playerCard);
            ai.deal(deck.draw());
            pairs.add(new Pair(true, masterSuit, playerCard, aiCard));
            
        }
        else
        {
            System.out.println("AI goes first!");
            
            Card aiCard = ai.firstMove();
            ai.deal(deck.draw());
            Card playerCard = player.secondMove(aiCard);
            player.deal(deck.draw());
            ai.firstMoveResult(playerCard);
            pairs.add(new Pair(false, masterSuit, playerCard, aiCard));
        }
        
        boolean end = false;
        
        while(!end)
        {
            System.out.println("Master suit: " + masterSuit);
            System.out.println();
            
            if(pairs.get(pairs.size() - 1).playerWin())
            {
                System.out.println("Player's turn.");
                
                Card playerCard = player.firstMove();
                player.deal(deck.draw());
                Card aiCard = ai.secondMove(playerCard);
                ai.deal(deck.draw());
                pairs.add(new Pair(true, masterSuit, playerCard, aiCard));
            }
            else
            {
                System.out.println("AI's turn.");
                
                Card aiCard = ai.firstMove();
                ai.deal(deck.draw());
                Card playerCard = player.secondMove(aiCard);
                player.deal(deck.draw());
                ai.firstMoveResult(playerCard);
                pairs.add(new Pair(false, masterSuit, playerCard, aiCard));
            }
            
            if(pairs.size() >= 15)
            {
                end = true;
            }
        }
        
        int playerScore = 0;
        int aiScore = 0;
        
        for(int i = 0; i < pairs.size(); i++)
        {
            if(pairs.get(i).playerWin())
            {
                playerScore++;
            }
            else
            {
                aiScore++;
            }
        }
        
        System.out.println("GAME OVER!");
        System.out.println();
        System.out.println("Player score: " + playerScore);
        System.out.println("AI score: " + aiScore);
        System.out.println();
        
        if(playerScore > aiScore)
        {
            System.out.println("Player wins!!!");
        }
        else
        {
            System.out.println("AI wins!!!");
        }
    }
}

class Player
{
    private Suit masterSuit;
    private ArrayList<Card> hand;
    
    public Player(Suit masterSuit)
    {
        this.masterSuit = masterSuit;
        hand = new ArrayList<>();
    }
    
    public void deal(Card card)
    {
        if(card != null)
        {
            hand.add(card);
        }
    }
    
    public Card firstMove()
    {      
        System.out.print("Player hand:");
        for(int i = 0; i < hand.size(); i++)
        {
            System.out.print(" (" + (i + 1) + ") " + hand.get(i).toString());
        }
        System.out.println();
        System.out.print("Player move (enter card number): ");
        
        int playerPick = new Scanner(System.in).nextInt() - 1;
        
        return hand.remove(playerPick);
    }
    
    public Card secondMove(Card opponentCard)
    {      
        System.out.print("Player hand:");
        for(int i = 0; i < hand.size(); i++)
        {
            System.out.print(" (" + (i + 1) + ") " + hand.get(i).toString());
        }
        System.out.println();
        
        int playerPick;
        do
        {
            System.out.print("Player move (enter card number): ");
            playerPick = new Scanner(System.in).nextInt() - 1;
            
            ArrayList<Card> allowedCards = new ArrayList<>();
            for(Card card : hand)
            {
                if(opponentCard.suit == card.suit)
                {
                    allowedCards.add(card);
                }
            }
            
            if(allowedCards.size() > 0)
            {
                if(!allowedCards.contains(hand.get(playerPick)))
                {
                    playerPick = -1;
                    System.out.println("Invalid move! You have " + allowedCards.size() + " card(s) with the same suit as the card played by the opponent.");
                }
            }
        }
        while(playerPick < 0);
        
        return hand.remove(playerPick);
    }
}

class AI
{
    private final Suit masterSuit;
    private final ArrayList<Card> hand;
    private ArrayList<Card> possibleOpponentCards;
    
    public AI(Suit masterSuit)
    {
        this.masterSuit = masterSuit;
        hand = new ArrayList<>();
        possibleOpponentCards = new ArrayList<>();
        
        possibleOpponentCards.add(new Card(Suit.Spade, Rank.Seven));
        possibleOpponentCards.add(new Card(Suit.Spade, Rank.Eight));
        possibleOpponentCards.add(new Card(Suit.Spade, Rank.Nine));
        possibleOpponentCards.add(new Card(Suit.Spade, Rank.Ten));
        possibleOpponentCards.add(new Card(Suit.Spade, Rank.Jack));
        possibleOpponentCards.add(new Card(Suit.Spade, Rank.Queen));
        possibleOpponentCards.add(new Card(Suit.Spade, Rank.King));
        possibleOpponentCards.add(new Card(Suit.Spade, Rank.Ace));
        possibleOpponentCards.add(new Card(Suit.Heart, Rank.Seven));
        possibleOpponentCards.add(new Card(Suit.Heart, Rank.Eight));
        possibleOpponentCards.add(new Card(Suit.Heart, Rank.Nine));
        possibleOpponentCards.add(new Card(Suit.Heart, Rank.Ten));
        possibleOpponentCards.add(new Card(Suit.Heart, Rank.Jack));
        possibleOpponentCards.add(new Card(Suit.Heart, Rank.Queen));
        possibleOpponentCards.add(new Card(Suit.Heart, Rank.King));
        possibleOpponentCards.add(new Card(Suit.Heart, Rank.Ace));
        possibleOpponentCards.add(new Card(Suit.Club, Rank.Eight));
        possibleOpponentCards.add(new Card(Suit.Club, Rank.Nine));
        possibleOpponentCards.add(new Card(Suit.Club, Rank.Ten));
        possibleOpponentCards.add(new Card(Suit.Club, Rank.Jack));
        possibleOpponentCards.add(new Card(Suit.Club, Rank.Queen));
        possibleOpponentCards.add(new Card(Suit.Club, Rank.King));
        possibleOpponentCards.add(new Card(Suit.Club, Rank.Ace));
        possibleOpponentCards.add(new Card(Suit.Diamond, Rank.Eight));
        possibleOpponentCards.add(new Card(Suit.Diamond, Rank.Nine));
        possibleOpponentCards.add(new Card(Suit.Diamond, Rank.Ten));
        possibleOpponentCards.add(new Card(Suit.Diamond, Rank.Jack));
        possibleOpponentCards.add(new Card(Suit.Diamond, Rank.Queen));
        possibleOpponentCards.add(new Card(Suit.Diamond, Rank.King));
        possibleOpponentCards.add(new Card(Suit.Diamond, Rank.Ace));
    }
    
    public void deal(Card card)
    {
        if(card != null)
        {
            updatePossibleOpponentCards(card);
            hand.add(card);
        }
    }
    
    public Card firstMove()
    {
        ArrayList<Double> scores = new ArrayList<>();
        
        for(Card card : hand)
        {
            int possibleWins = 0;
            
            for(Card opponentCard : possibleOpponentCards)
            {
                if(card.beats(masterSuit, opponentCard))
                {
                    possibleWins++;
                }
            }
            
            double rank = ((double) possibleWins) / ((double) (card.rank.ordinal() + 1));
            
            if(card.suit == masterSuit)
            {
                rank *= 0.5;
            }
            
            scores.add(rank);
            
            // print heuristics
            //System.out.println(card.toString() + " " + rank);
        }
        
        double best = -1.0;
        int index = -1;
        for(int i = 0; i < scores.size(); i++)
        {
            if(best < scores.get(i))
            {
                best = scores.get(i);
                index = i;
            }
        }
        
        System.out.println("AI played " + hand.get(index).toString() + ".");
        return hand.remove(index);
    }
    
    public void firstMoveResult(Card opponentCard)
    {
        updatePossibleOpponentCards(opponentCard);
    }
    
    public Card secondMove(Card opponentCard)
    {
        updatePossibleOpponentCards(opponentCard);  
        
        ArrayList<Card> strong = new ArrayList<>();
        ArrayList<Card> weak = new ArrayList<>();

        for(Card card : hand)
        {
            if(card.suit == opponentCard.suit)
            {
                strong.add(card);
            }
            else
            {
                weak.add(card);
            }
        }
        
        if(strong.size() > 0)
        {
            ArrayList<Double> scores = new ArrayList<>();
            
            for(Card card : strong)
            {
                double rank;
                
                if(card.beats(masterSuit, opponentCard))
                {
                    rank = (double) (Rank.values().length - card.rank.ordinal());
                }
                else
                {
                    rank = (double) ((card.rank.ordinal() + 1) * -1);
                }
                
                scores.add(rank);
                
                // print heuristics
                //System.out.println(card.toString() + " " + rank);
            }
            
            double best = -1 * Rank.values().length;
            int index = -1;
            for(int i = 0; i < scores.size(); i++)
            {
                if(best < scores.get(i))
                {
                    best = scores.get(i);
                    index = i;
                }
            }
            
            System.out.println("AI played " + hand.get(hand.indexOf(strong.get(index))).toString() + ".");
            return hand.remove(hand.indexOf(strong.get(index)));
        }
        else
        {
            ArrayList<Double> scores = new ArrayList<>();
            
            for(Card card : weak)
            {
                double rank = (double) (Rank.values().length - card.rank.ordinal());
                
                if(card.suit == masterSuit)
                {
                    rank *= 0.5;
                }
                
                scores.add(rank);
                
                // print heuristics
                //System.out.println(card.toString() + " " + rank);
            }
            
            double best = -1.0;
            int index = -1;
            for(int i = 0; i < scores.size(); i++)
            {
                if(best < scores.get(i))
                {
                    best = scores.get(i);
                    index = i;
                }
            }
            
            System.out.println("AI played " + hand.get(hand.indexOf(weak.get(index))).toString() + ".");
            return hand.remove(hand.indexOf(weak.get(index)));
        }
    }
    
    private void updatePossibleOpponentCards(Card card)
    {
        for(int i = 0; i < possibleOpponentCards.size(); i++)
        {
            if(possibleOpponentCards.get(i).equals(card))
            {
                possibleOpponentCards.remove(i);
            }
        }
    }
}

class Deck
{
    private ArrayList<Card> cards;

    public Deck()
    {
        cards = new ArrayList<>();

        cards.add(new Card(Suit.Spade, Rank.Seven));
        cards.add(new Card(Suit.Spade, Rank.Eight));
        cards.add(new Card(Suit.Spade, Rank.Nine));
        cards.add(new Card(Suit.Spade, Rank.Ten));
        cards.add(new Card(Suit.Spade, Rank.Jack));
        cards.add(new Card(Suit.Spade, Rank.Queen));
        cards.add(new Card(Suit.Spade, Rank.King));
        cards.add(new Card(Suit.Spade, Rank.Ace));
        cards.add(new Card(Suit.Heart, Rank.Seven));
        cards.add(new Card(Suit.Heart, Rank.Eight));
        cards.add(new Card(Suit.Heart, Rank.Nine));
        cards.add(new Card(Suit.Heart, Rank.Ten));
        cards.add(new Card(Suit.Heart, Rank.Jack));
        cards.add(new Card(Suit.Heart, Rank.Queen));
        cards.add(new Card(Suit.Heart, Rank.King));
        cards.add(new Card(Suit.Heart, Rank.Ace));
        cards.add(new Card(Suit.Club, Rank.Eight));
        cards.add(new Card(Suit.Club, Rank.Nine));
        cards.add(new Card(Suit.Club, Rank.Ten));
        cards.add(new Card(Suit.Club, Rank.Jack));
        cards.add(new Card(Suit.Club, Rank.Queen));
        cards.add(new Card(Suit.Club, Rank.King));
        cards.add(new Card(Suit.Club, Rank.Ace));
        cards.add(new Card(Suit.Diamond, Rank.Eight));
        cards.add(new Card(Suit.Diamond, Rank.Nine));
        cards.add(new Card(Suit.Diamond, Rank.Ten));
        cards.add(new Card(Suit.Diamond, Rank.Jack));
        cards.add(new Card(Suit.Diamond, Rank.Queen));
        cards.add(new Card(Suit.Diamond, Rank.King));
        cards.add(new Card(Suit.Diamond, Rank.Ace));
        
        shuffle();
    }
    
    private void shuffle()
    {
        ArrayList<int[]> sortTable = new ArrayList<>();
        
        for(int i = 0; i < 30; i++)
        {
            int[] sortEntry = {i, (int) Math.pow(Math.random() * cards.size(), 2)};
            sortTable.add(sortEntry);
        }
        
        for(int i = 0; i < cards.size(); i++)
        {
            for(int j = 1; j < cards.size(); j++)
            {
                if(sortTable.get(j)[1] < sortTable.get(j - 1)[1])
                {
                    int[] tempEntry = sortTable.get(j);
                    sortTable.set(j, sortTable.get(j - 1));
                    sortTable.set(j - 1, tempEntry);
                }
            }
        }
        
        ArrayList<Card> tempCards = cards;
        cards = new ArrayList<>();
        for(int[] sortEntry : sortTable)
        {
            cards.add(tempCards.get(sortEntry[0]));
        }
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
    public final Suit suit;
    public final Rank rank;

    public Card(Suit suit, Rank rank)
    {
        this.suit = suit;
        this.rank = rank;
    }
    
    public boolean equals(Card card)
    {
        return this.suit == card.suit && this.rank == card.rank;
    }
    
    public String toString()
    {
        return "[" + rank + " of " + suit + "]";
    }
    
    public boolean beats(Suit masterSuit, Card card)
    {
        if(this.suit == masterSuit && card.suit != masterSuit)
        {
            return true;
        }
        else if(this.suit != masterSuit && card.suit == masterSuit)
        {
            return false;
        }
        else
        {
            if(this.suit == card.suit)
            {
                return this.rank.ordinal() > card.rank.ordinal();
            }
            else
            {
                return true;
            }
        }
    }
}

class Pair
{
    private final Boolean playerMove;
    private final Suit masterSuit;
    private final Card playerCard;
    private final Card aiCard;
    
    public Pair(Boolean playerMove, Suit masterSuit, Card playerCard, Card aiCard)
    {
        this.playerMove = playerMove;
        this.masterSuit = masterSuit;
        this.playerCard = playerCard;
        this.aiCard = aiCard;
        
        System.out.println();
        if(playerWin())
        {
            System.out.println("Player wins the pair!");
        }
        else
        {
            System.out.println("AI wins the pair!");
        }
        System.out.println();
    }
    
    public boolean playerWin()
    {
        if(playerMove)
        {
            return playerCard.beats(masterSuit, aiCard);
        }
        else
        {
            return !aiCard.beats(masterSuit, playerCard);
        }
    }
}

enum Suit
{
    Spade, Heart, Club, Diamond
}

enum Rank
{
    Seven, Eight, Nine, Ten, Jack, Queen, King, Ace
}