// APURVA PATEL
// CS 6364 - 002
// Final Project

import java.util.*;

public class SevenEights
{
    private final Deck deck;
    private final Suite masterSuite;
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
        masterSuite = Suite.values()[(int) (Math.random() * Suite.values().length)];
        pairs = new ArrayList<>();
        player = new Player(masterSuite);
        ai = new AI(masterSuite);
        
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
        System.out.println("Master Suite: " + masterSuite);
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
            pairs.add(new Pair(true, masterSuite, playerCard, aiCard));
            
        }
        else
        {
            System.out.println("AI goes first!");
            
            Card aiCard = ai.firstMove();
            ai.deal(deck.draw());
            Card playerCard = player.secondMove(aiCard);
            player.deal(deck.draw());
            ai.firstMoveResult(playerCard);
            pairs.add(new Pair(false, masterSuite, playerCard, aiCard));
        }
        
        boolean end = false;
        
        while(!end)
        {
            System.out.println("Master suite: " + masterSuite);
            System.out.println();
            
            if(pairs.get(pairs.size() - 1).playerWin())
            {
                System.out.println("Player's turn.");
                
                Card playerCard = player.firstMove();
                player.deal(deck.draw());
                Card aiCard = ai.secondMove(playerCard);
                ai.deal(deck.draw());
                pairs.add(new Pair(true, masterSuite, playerCard, aiCard));
            }
            else
            {
                System.out.println("AI's turn.");
                
                Card aiCard = ai.firstMove();
                ai.deal(deck.draw());
                Card playerCard = player.secondMove(aiCard);
                player.deal(deck.draw());
                ai.firstMoveResult(playerCard);
                pairs.add(new Pair(false, masterSuite, playerCard, aiCard));
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
    private Suite masterSuite;
    private ArrayList<Card> hand;
    
    public Player(Suite masterSuite)
    {
        this.masterSuite = masterSuite;
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
                if(opponentCard.suite == card.suite)
                {
                    allowedCards.add(card);
                }
            }
            
            if(allowedCards.size() > 0)
            {
                if(!allowedCards.contains(hand.get(playerPick)))
                {
                    playerPick = -1;
                    System.out.println("Invalid move! You have " + allowedCards.size() + " card(s) with the same suite as the card played by the opponent.");
                }
            }
        }
        while(playerPick < 0);
        
        return hand.remove(playerPick);
    }
}

class AI
{
    private final Suite masterSuite;
    private final ArrayList<Card> hand;
    private ArrayList<Card> possibleOpponentCards;
    
    public AI(Suite masterSuite)
    {
        this.masterSuite = masterSuite;
        hand = new ArrayList<>();
        possibleOpponentCards = new ArrayList<>();
        
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
                if(card.beats(masterSuite, opponentCard))
                {
                    possibleWins++;
                }
            }
            
            double rank = ((double) possibleWins) / ((double) (card.rank.ordinal() + 1));
            
            if(card.suite == masterSuite)
            {
                rank *= 0.5;
            }
            
            scores.add(rank);
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
            if(card.suite == opponentCard.suite)
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
                
                if(card.beats(masterSuite, opponentCard))
                {
                    rank = (double) (Rank.values().length - card.rank.ordinal());
                }
                else
                {
                    rank = (double) ((card.rank.ordinal() + 1) * -1);
                }
                
                scores.add(rank);
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
                
                if(card.suite == masterSuite)
                {
                    rank *= 0.5;
                }
                
                scores.add(rank);
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
    public final Suite suite;
    public final Rank rank;

    public Card(Suite suite, Rank rank)
    {
        this.suite = suite;
        this.rank = rank;
    }
    
    public boolean equals(Card card)
    {
        return this.suite == card.suite && this.rank == card.rank;
    }
    
    public String toString()
    {
        return "[" + rank + " of " + suite + "]";
    }
    
    public boolean beats(Suite masterSuite, Card card)
    {
        if(this.suite == masterSuite && card.suite != masterSuite)
        {
            return true;
        }
        else if(this.suite != masterSuite && card.suite == masterSuite)
        {
            return false;
        }
        else
        {
            if(this.suite == card.suite)
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
    private final Suite masterSuite;
    private final Card playerCard;
    private final Card aiCard;
    
    public Pair(Boolean playerMove, Suite masterSuite, Card playerCard, Card aiCard)
    {
        this.playerMove = playerMove;
        this.masterSuite = masterSuite;
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
            return playerCard.beats(masterSuite, aiCard);
        }
        else
        {
            return !aiCard.beats(masterSuite, playerCard);
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