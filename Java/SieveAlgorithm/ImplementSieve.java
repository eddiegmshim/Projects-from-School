import java.util.Scanner;

public class ImplementSieve {
	
	public static void main (String[] args){

		
		System.out.println("Please enter upper bound:");
		Scanner input = new Scanner(System.in);
		int userInput = input.nextInt();
		
		Sieve newSieve = new Sieve(userInput);
		newSieve.primesTo(userInput);
	}

}
