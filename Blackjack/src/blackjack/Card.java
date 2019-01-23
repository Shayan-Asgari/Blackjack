package blackjack;

public class Card 
{
	private Suit suit;
	private Value value;
	
	public Card(Suit suit, Value value)
	{
		this.suit = suit;
		this.value = value;
	}
	
	public Value getValue()
	{
		return this.value;
	}
	public Suit getSuit()
	{
		return this.suit;
	}
	
	public String toString()
	{
		return this.suit.toString() + " - " + this.value.toString();
	}
	
	public static void main(String[] args)
	{
		
		
	}


}
