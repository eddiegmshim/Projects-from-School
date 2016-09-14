package SieveAlgorithm;

import java.util.*;
import java.lang.*;

public class Sieve {
	private ArrayStack numbers = new ArrayStack();
	private ArrayStack primes = new ArrayStack();
	private ArrayStack eliminated = new ArrayStack();
	private ArrayStack primesReversed = new ArrayStack();
	

	public Sieve(int upperBound){

		this.numbers = numbers;
		this.primes = primes;
		this.eliminated = eliminated;
		this.primesReversed = primesReversed;
		
		
		//create arraystack from 2 through n flipped into numbers stack, so that numbers.top()=2
		for(int i = upperBound; i>=2; i--){
			numbers.push(new Integer (i));
		}
		

		
		int p = 2;
		
		//looping through all elements of numbers stack (p) which are <= sqrt(upperBound) and deleting all multiples which are 
		//multiples of p
		while (p<= Math.sqrt(upperBound)){
			
			//push p, which is guaranteed prime, into primes stack
			primes.push(numbers.top());
			//pop off p
			numbers.pop();
			
			//loop through and delete all multiples of p from numbers stack 
			while (!numbers.isEmpty()){
				if (((int)numbers.top() % p) !=0){
					eliminated.push((int)numbers.top());
					numbers.pop();
				}
				else {
					numbers.pop();
				}
			}
			
			//put non multiples of p, which are stored in eliminated stack, back into numbers stack
			while (!eliminated.isEmpty()){
				numbers.push((int)eliminated.top());
				eliminated.pop();
			}
			
			//resets p as numbers.top()
			p = (int)numbers.top();
				
			}
		
		//adding the primes >= sqrt(n) into primes 
		while (!numbers.isEmpty()){
			primes.push(numbers.top());
			numbers.pop();
		}
		
		//reverse it so the order is from 2 to n
		while(!primes.isEmpty()){
			primesReversed.push(primes.top());
			primes.pop();
		}

				
		
	}
	
	
	//method primesTo which prints the numbers of all primes upto n 
	public void primesTo(int n){
		
		if (n>=2){
			String output = "";
			while (!this.primesReversed.isEmpty()){
				output = output + this.primesReversed.top() + ", ";
				this.primesReversed.pop();
			}
			//parse the output so the last ", " is cut
			output = output.substring(0, output.length()-2);
			System.out.println("Primes up to " + n + " are: " + output);
		} 
		
		//error for when user inputs n less than 2
		else {
			System.out.println("Error: Input must be greater or equal to 2");
		}
		
	}
}


	

	

