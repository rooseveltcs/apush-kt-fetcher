/*
*FetchKT.java
*Assingment: Final Project: FetchKT
*Purpose: Grab KT id and sig for the user
*@version 05/16/2014
*@author Simone Archer-Krauss  
*/

import java.util.*;
import java.io.*;
import java.awt.*;
 
public class FetchKT {
   public static int KTAnswer = 1; //class constant that keeps track of the number of keyterms that user has asked for
   public static void main(String[] args)throws FileNotFoundException {
      File joking = new File("jokes.txt"); //imports jokes file
      // Program asks for chapter, saves name of file, creates File object, creates Scanner object of File
      System.out.println("What chapter do you want to access? \n **Write chapter and the chapter number and add .txt, NO SPACES. Ex: chapter1.txt");
      Scanner input = new Scanner(System.in); //Scanner that asks for the chapter that the user wants
      String chapterFileName = input.next(); //holds the chapter and becomes a txt file
      System.out.println();
      File chapterFile = new File (chapterFileName); //imports the file that the user writes in (chapterFileName)
      Scanner keyTerms = new Scanner(chapterFile); //allows keyTerms to search through chapterFile      
    
      // Program asks for keyterm to search for, saves it
      System.out.print("What keyterm would you like? "); //asks the user for the keyterm that they want
      String kT = input.next(); //asks the user for the keyterm that they want
      kT += input.nextLine(); //looks at the first word in the user input line
      System.out.println(); //looks at the rest of the user input line
       
      name(input, chapterFileName, chapterFile, keyTerms, kT, joking); //calsl name method
   
   }
   //askUser method is called by name and joke methods if the user wants to continue looking for other keyterms
   public static void askUser(Scanner input, String chapterFileName, File chapterFile, Scanner keyTerms, String kT, File joking)throws FileNotFoundException{
      System.out.println();
      // Program asks for chapter, saves name of file, creates File object, creates Scanner object of File
      System.out.println("What chapter do you want to access? \n **Write chapter and the chapter number and add .txt, NO SPACES. Ex: chapter1.txt");
      input = new Scanner(System.in); //Scanner that asks for the chapter that the user wants
      chapterFileName = input.next(); //holds the chapter and becomes a txt file
      System.out.println();
      chapterFile = new File (chapterFileName); //imports the file that the user writes in (chapterFileName)
      keyTerms = new Scanner(chapterFile); //allows keyTerms to search through chapterFile
      
      // Program asks for keyterm to search for, saves it
      System.out.print("What keyterm would you like? "); //asks the user for the keyterm that they want
      kT = input.next(); //asks the user for the keyterm that they want
      kT += input.nextLine(); //looks at the first word in the user input line
      System.out.println(); //looks at the rest of the user input line
      
      name(input, chapterFileName, chapterFile, keyTerms, kT, joking); //calls name method
   }
   //prints out the name
   //checks the name/keyterm - the first line of each set
   public static void name (Scanner input, String chapterFileName, File chapterFile, Scanner keyTerms, String kT, File joking) throws FileNotFoundException{
      boolean hasKT = false; //boolean checking to see if the search has found the keyterm
      String currentKT = ""; //keeps track of the current keyterm
      String def = ""; //keeps track of the current definition
      String sig = ""; //keeps track of the current sigificance
      
      while(keyTerms.hasNextLine() && hasKT == false){ 
         currentKT = keyTerms.nextLine(); //runs through KT line
         def = keyTerms.nextLine(); //runs through def line
         sig = keyTerms.nextLine(); //runs through sig line
         hasKT = kT.equalsIgnoreCase(currentKT); //checks to see if the user input keyterm is equal to the current keyterm the program has
      
      }
      
      if(hasKT){ //checks if the KT is in the txt file
         System.out.println(currentKT + ": ");
         System.out.println();
         definitions(def); //calls definitions method
         significance(sig); //calls siginificance method
         joke(input, chapterFileName, chapterFile, keyTerms, kT, joking); //calls joke method
      }
      else {
         System.out.println("Sorry. The keyterm you asked for is not in this chapter. Try a different chapter or check your spelling.");
         System.out.println();
         System.out.print("Would you like to try again? ");
         String again = input.next(); //asks the user if they want to try again in the case of a wrong keyterm
         System.out.println();
         if(again.startsWith("y")){ //if the user says yes
            joke(input, chapterFileName, chapterFile, keyTerms, kT, joking); //calls joke method
         }
         else {
            System.out.println("Thank you.");
            System.exit(0); //exits the entire program
         }
      }
   }  
   
   //definitions method
   //prints out definition of the keyterm - id line 2 of each set
   public static void definitions(String def){
      System.out.println(def); 
      System.out.println();    
   }
   //significance method
   //prints out the significance of the keyterm which is line 3 of each set
   public static void significance(String sig){
      System.out.println(sig);
      System.out.println();
   }
   //joke method, asks if the user has another keyterm, adds to KTAnswer, checks how many keyterms have been asked for and if there are 10 then it prints out a joke, asks for the answer to the joke, calls askUser method
   public static void joke(Scanner input, String chapterFileName, File chapterFile, Scanner keyTerms, String kT, File joking) throws FileNotFoundException{
      Scanner jokes = new Scanner(joking);
      System.out.print("Do you have another keyterm? ");
      String answer = input.next(); //asks the user if they would like to search for another keyterm
      System.out.println();
      String jk = ""; 
      String punchLine = "";
      while(answer.startsWith("y")){
         KTAnswer++; //adds to KTAnswer
         if(KTAnswer % 10 == 0){
            jk = jokes.nextLine(); //gets joke
            punchLine = jokes.nextLine(); //gets punchLine
            System.out.println(jk); //prints out joke
            String userAnswer = input.next(); //asks for the user's answer
            userAnswer += input.nextLine(); //adds the rest of the user's answer line
            if(userAnswer.equalsIgnoreCase(punchLine)){ //if they type in the answer and it is correct
               System.out.println("Congratulations! You guessed correctly!");
            }
            else { //if the answer the user types in is incorrect
               System.out.println("Too bad. The correct answer is: " + punchLine); //prints out punchLine
            }
         }
         askUser(input, chapterFileName, chapterFile, keyTerms, kT, joking); //calls askUser method
      }
      if(answer.startsWith("n")){ //if the user does not have another keyterm
         System.out.println("Thank you.");
         System.exit(0); //exits entire program
      }
   }
}