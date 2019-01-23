package blackjack;

import java.util.Scanner;

public class BlackJack
{
	//runs application
	public static void main(String[] args)
	{
		Deck gameDeck = new Deck();
		gameDeck.createFullDeck();
		Scanner scan = new Scanner(System.in); 
		System.out.print("How much money would you like to put in to join the game? You must put in at least 10 dollars. " +"\n"
				+"If you wish to leave press any Non-Integer key to quit" + " \n" );
		boolean joinGame = false;
		double amount = Integer.MAX_VALUE;
		boolean gameOver = false;
		do
		{
			
			System.out.print("Enter your answer here : ");
			if(scan.hasNextDouble())
			{
				amount = scan.nextDouble();
			}
			else
			{
				System.out.print("Sorry to see you go.");
				break;
			}
			if(amount<10)
			{
				System.out.println("Invalid entry requirements. Please enter a valid input or press Q to quit.");
				
			}
			else if(amount>=10 && amount <1000)
			{
				System.out.println("Very well");
				joinGame = true;
			}
			else if(amount>=1000 && amount <=5000)
			{
				System.out.println("Looks like you have some dinero");
				joinGame = true;
			}
			else
			{
				joinGame = true;
			}
		}
		while(!joinGame);
		
		
		double playerBet = 0;
		while(joinGame && amount>=10)
		{
			Deck playerDeck = new Deck();
			playerDeck.draw(gameDeck);
			playerDeck.draw(gameDeck);
			
			Deck dealerDeck = new Deck();
			
			dealerDeck.draw(gameDeck);
			dealerDeck.draw(gameDeck);
			boolean reasonableInput = false;
			System.out.println("\n"+"You have $" + amount + ". How much would you like to bet?");
			System.out.print("Enter your answer here: ");
			do 
			{
				if(scan.hasNextDouble())
				{
					playerBet  = scan.nextDouble();
					if(!(playerBet>amount))
						reasonableInput = true;
					else
					{
						System.out.println("You do not have that much money bud. Please enter a valid input");
					}
				}
				else
				{ 
					System.out.print("Please enter a valid input " );
					scan.next();
				}
			}
			while(!reasonableInput);
			
			boolean bust = false;
			boolean invalidResponse = true;
			String choice ="";
			while(!bust)
			{
				StringBuilder hand = new StringBuilder();
				for(int i = 0; i<playerDeck.getDeckSize();i++)
				{	
					if(i == playerDeck.getDeckSize()-1)
						hand.append("Card " +(i+1) + ": "+ playerDeck.getCard(i));
					else
						hand.append("Card " +(i+1) + ": "+ playerDeck.getCard(i)+ " ");
					
				}
				System.out.println("Your current hand: " + hand.toString()+". " + "Your current hand value is " + playerDeck.getDeckValue()+ ".");
				System.out.print("Press H for hit or S to stay: ");
				do
				{
					String response2 = scan.next();
					if(response2.equalsIgnoreCase("h")|response2.equalsIgnoreCase("hit"))
					{
						invalidResponse = false;
						choice = "h";
					}
					else if(response2.equalsIgnoreCase("s")|response2.equalsIgnoreCase("stay"))
					{
						invalidResponse = false;
						choice = "s";
					}
					else
					{
						System.out.println("Invalid choice. Please try again");
					}
				}
				while(invalidResponse);
				if(choice == "h")
				{
					playerDeck.draw(gameDeck);
					System.out.println("You drew a " + playerDeck.getCard(playerDeck.getDeckSize()-1) );			
					if(playerDeck.getDeckValue()>21)
					{
						System.out.println("You bust");
						bust = true;
					
					}
				}
				else
				{
					break;
				}
				
			}
			boolean dealerBust= false;
			
			while(dealerDeck.getDeckValue()<17 &&!dealerBust && !bust)
			{	
				System.out.println("Dealer's current hand: " + dealerDeck.toString());
				System.out.println("Dealer's value is: "+ dealerDeck.getDeckValue());
				
				dealerDeck.draw(gameDeck);
				if(dealerDeck.getDeckValue()>21)
				{
					dealerBust =true;
				}
			}
			System.out.println("Dealer's current hand: " + dealerDeck.toString());
			System.out.println("Dealer's value is: "+ dealerDeck.getDeckValue());
			
			System.out.println("Your current hand : " + playerDeck.toString());
			System.out.println("The value of your hand is: "+ playerDeck.getDeckValue());
			if(dealerDeck.getDeckValue() == playerDeck.getDeckValue() && !dealerBust && !bust )
			{
				System.out.println("Round ended in tie, so you get half the pot.");
				amount += (playerBet/2.0);
			}
			else if(bust == true)
			{
				amount -=playerBet;
				System.out.println("Congratulations, you played yourself");
			}
			else if(dealerBust == true) // at this point we know we did not bust, so we have to see if the dealer busted
			{
				System.out.println("You Win!");
				amount+= playerBet;
			}
			else //At this point we know no one busted
			{
				if(dealerDeck.getDeckValue() < playerDeck.getDeckValue())
				{
					System.out.println("You Win!");
					amount += playerBet;
				}
				else
				{
					System.out.println("Congratulations, you played yourself");
					amount -= playerBet;
				}
			}
			
			gameDeck.moveAllToDeck(playerDeck, dealerDeck);
			gameDeck.shuffle();
		}
		
	}
	
}

