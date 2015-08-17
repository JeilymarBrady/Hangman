/*
Name:  Jeilymar
Assignment:  lab 07
Title: Hangman
Course: CSCE144 
Section: 1 - Hauser
Lab Section: 2 - Hauser
Semester: Spring 2014 
Instructor: Hauser
Date:  12 April 2014
Sources consulted:   
Known Bugs: none
Program Description: This program plays hangman, computer vs. user
Creativity: none
Instructions: javac HGM.java then java HGM then follow on screen instructions
*/

import java.util.Scanner;

public class HGM
{
	/**
	*@param  
	*@return welcome, userGuess, displayResult, 
	*/
	public static void main(String[] args)
	{
		Scanner keyboard = new Scanner(System.in); 
		System.out.println("Hello and welcome to the game of Hangman!");
		boolean won = true;
		int numGuesses = 0;
		int wordLength = 0;
		String word = "";
		char[] userGuess; 
		char answer = 'y';
		
		
		while(answer =='y' || answer == 'Y') {
			String secret = secretWord();
			userGuess = asterisk(secret) ;
			System.out.print("\nThe word you are guessing has " + secret.length() + " letters: ") ;
			displayArray(userGuess) ;
			numGuesses = secret.length() ;
			
			while( numGuesses > 0 && correctGuess( userGuess) == false ) {
				numGuesses = doOneGuess(secret, userGuess, numGuesses, keyboard  ) ;
			}
			
			if (correctGuess( userGuess) == true ) 
				System.out.println("\nYou guessed the word: " + secret) ;
			else
				System.out.print("You lost!") ;
			
			
			System.out.println("\nWould you like to play again (y/n)? ") ;
			answer = keyboard.nextLine().charAt(0) ;
			
		
		} 
	}
		
		
	public static int doOneGuess(String secret, char[] userGuess, int numGuesses, Scanner keyboard) {
		System.out.print("Please enter your guess: ") ;
		char guess = keyboard.nextLine().toLowerCase().charAt(0) ;
		
		while (inAlpha(guess) == false) {
			System.out.print("Invalid input. Please try again: ") ;
			guess = keyboard.nextLine().toLowerCase().charAt(0) ;
		}
		
		if (hasLetter(secret, guess, userGuess ) == false) {
			System.out.println("There are no " + guess + "'s in the word.") ;

			System.out.println("You have " + numGuesses + " guesses left.") ;
			System.out.println() ;
			numGuesses-- ;
		}
		
		else{
			System.out.println("Your guess is correct!") ;
			if ( correctGuess(userGuess) == false) {
				System.out.print("The word now looks like:") ;
				displayArray(userGuess) ;
				System.out.println("You have " + numGuesses + " guesses left.") ;
				System.out.println() ;
				numGuesses-- ;
			}
		}
		
		return numGuesses ;
	}
	
	/**
	* @return  randomly selects a word, secret, from the array wordList
	*/
	public static String secretWord() {
		String[] wordList = { "homework", "Arizona", "watchtower", 
		"countrywide", "headstrong", "engineer", "endangerment", 
		"aibohphobia", "exploratory", "evangelian" } ;
		
		int rand = (int)(Math.random() * wordList.length) ;
		String secret = wordList[rand] ;
		return secret ;
		
		
	}
	
	/** Displays the contents of any array
	*
	*
	*/
	public static void displayArray( char[] test){
		for(int i=0; i<test.length; i++) 
			System.out.print( test[i]) ;
			
		System.out.println() ;
	}
	
	
	/** Creats an array of asterisks
	* @param  string word
	* @return asterisks
	*/
	
	public static char[] asterisk( String word ) {
		
		char[] asterisks = new char[word.length()];
		for(int i=0; i<word.length(); i++) 
			asterisks[i] = '*';
		return asterisks;
		
	}
	
	/**
	* @param  char guess
	* @return  true or false depending on whether their guess is in the alphabet
	*/
	public static boolean inAlpha( char guess ) {
		
		return ( guess >= 'a' && guess <= 'z' );
	
	}
	
	
	/**
	* @param  String word, char guess, char[] asteriskArray 
	* @return hasLetter, numletter
	*/
	
	public static boolean hasLetter( String word, char guess, char[] asteriskArray ) {
		boolean hasLetter = false ;
		for(int i=0; i<word.length() ;i++) {
			if(guess == word.charAt(i) && asteriskArray[i] != guess) {
				hasLetter = true;
				asteriskArray[i] = guess ;
			}
		}
		return hasLetter ;
	}

	
	/** Checks if the user guessed word correctly
	* @param  
	* @return  correct
	*/ 
	
	public static boolean correctGuess( char[] asteriskArray ) {
		boolean won = true ;
		for(int i=0; i<asteriskArray.length ;i++) {
			if(asteriskArray[i] == '*')
				won = false;
		}
		return won ;	
	}
}



