package Blackjack;

import java.util.Scanner;
public class Blackjack {
	public static void main(String[] args){

		Scanner input = new Scanner(System.in);
		
		
		int points = 0;
		int points2 = 0;
		
		// assigns two random card values to dealer and player
		int card1= (int)(2 + Math.random()*10);
		int card2= (int)(2 + Math.random()*10);
		
		int card3= (int)(2 + Math.random()*10);
		int card4= (int)(2 + Math.random()*10);
		
		
		points = card1+card2;
		points2 = card3+card4;
		
		System.out.println("Your two card values are: " + card1 +", " +card2 + ". Your total hand is " + points + " points.");
		
		
		int x = 1;
		int y = 1;
		
		// asks player if he/she wants to draw more cards
		while (x!=0){
			
			System.out.println("Press 1 to draw another card. Press 0 to stop.");
			int answer = input.nextInt();
			if (answer==1){
				int nextCard=(int)(1+ Math.random()*10);
				points = nextCard + points;
				System.out.println("Your drawn card has a value of " + nextCard+". Your total hand is " + points + " points.");
				if (points>21){
					System.out.println("You went over! You lose.");
					x=x-1;				
				}
			}
			
			// when player stops drawing cards
			else {
				
				// asks dealer if he/she wants to draw more cards
				while (y!=0){
					
					int z = (int)(Math.random()*2);
					System.out.println("Dealer is at a value of "+ points2);
					
					//dealer will keep drawing if his/her hand is lower than 17 points
					if (points2<17){
						z=1;
					}
					
					// auto stop if dealer hits 21
					if (points2==21){
						z=0;
					}
					
					
					if (z==1){
						int nextCard2=(int)(Math.random()*11);
						points2=points2+nextCard2;
						if (points2>21){
							System.out.println("Dealer went over! You win");
							x=x-1;
							break;
						}
					}
					// when the dealer stops drawing cards, when z==0
					else {
						if(points>points2){
							System.out.println("Your hand: " + points + " Dealer's hand: " + points2 + " You win!");
						}
						else if(points<points2){
							System.out.println("Your hand: " + points + " Dealer's hand: " + points2 + " You lose!");
						}
						else {
							System.out.println("Your hand: " + points + " Dealer's hand: " + points2 + " It's a draw!");
						}
						x=x-1;
						break;
					}
				}
			}
		}
		
		
		
		
		
	}
}
		
		



