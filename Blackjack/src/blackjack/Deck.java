package blackjack;

import java.util.ArrayList;
import java.util.Random;

public class Deck
{
	private ArrayList<Card> deck;
	
	public Deck()
	{
		deck = new ArrayList<>(52);
	}
	
	public void createFullDeck()
	{
		//For every suit
		for(Suit s: Suit.values())
		{
			//For every value
			for(Value v: Value.values())
			{
				// add a new card to deck
				this.deck.add(new Card(s,v));
			}
		}
	}
	
	public void moveAllToDeck(Deck playerDeck, Deck dealerDeck)
	{
		for(Card c:playerDeck.getDeck())
		{
			this.deck.add(c);
		}
		for(Card c: dealerDeck.getDeck())
		{
			this.deck.add(c);
		}
	}
	
	public void shuffle()
	{
		ArrayList<Card> newDeck = new ArrayList<>(52);
		Random r = new Random();
		int sizeOfOriginalDeck = deck.size();
		for(int i = 0; i<sizeOfOriginalDeck; i++)
		{
			int randomInt = r.nextInt(this.deck.size());
			Card cardToRemove = deck.remove(randomInt);
			newDeck.add(cardToRemove);
		}
		this.deck = newDeck;
		
		
	}
	
	public ArrayList<Card> getDeck()
	{
		return this.deck;
	}
	
	public int getDeckSize()
	{
		return this.deck.size();
	}
	
	public void draw(Deck gameDeck)
	{	
		Random r = new Random();
		int randomInt = r.nextInt(deckSize(gameDeck));
		this.deck.add(gameDeck.remove(randomInt));
	}
	
	private Card remove(int i)
	{
		Card c = this.deck.remove(i);
		return c;
	}
	
	public int deckSize(Deck gameDeck)
	{
		return gameDeck.deck.size();
		
	}
	public Card getCard(int i)
	{
		return this.deck.get(i);
	}
	
	public int getDeckValue()
	{
		int totalValue = 0;
		int aces = 0;
		for(Card c : this.deck)
		{
			switch (c.getValue())
			{
			case TWO: totalValue += 2; break;
			case THREE: totalValue += 3; break;
			case FOUR: totalValue += 4; break;
			case FIVE: totalValue += 5; break;
			case SIX: totalValue += 6; break;
			case SEVEN: totalValue += 7; break;
			case EIGHT: totalValue += 8; break;
			case NINE: totalValue += 9; break;
			case TEN: totalValue += 10; break;
			case JACK: totalValue += 10; break;
			case QUEEN: totalValue += 10; break;
			case KING: totalValue += 10; break;
			case ACE: aces += 1; break;
			}
		}
		for(int i = 0; i < aces; i++)
		{
			if (totalValue > 10)
			{
				totalValue += 1;
			}
			else
			{
				totalValue += 11;
			}
		}
		return totalValue;
	}
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		for(Card c: this.deck)
		{
			builder.append(c);
			// new line OR you can do "\n"
			builder.append(System.getProperty("line.separator"));
		}
		return builder.toString();
	}
}
