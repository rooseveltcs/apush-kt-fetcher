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
   public static int KTAnswer = 1;
   public static void main(String[] args)throws FileNotFoundException {
      File joking = new File("jokes.txt");
      // Program asks for chapter, saves name of file, creates File object, creates Scanner object of File
      System.out.println("What chapter do you want to access? \n **Write chapter and the chapter number and add .txt, NO SPACES. Ex: chapter1.txt");
      Scanner input = new Scanner(System.in); //Scanner that asks for the chapter that the user wants
      String chapterFileName = input.next(); //holds the chapter and becomes a txt file
      System.out.println();
      File chapterFile = new File (chapterFileName); //new
      Scanner keyTerms = new Scanner(chapterFile);      
    
      // Program asks for keyterm to search for, saves it
      System.out.print("What keyterm would you like? ");
      String kT = input.next();
      kT += input.nextLine();
      System.out.println();
       
      name(input, chapterFileName, chapterFile, keyTerms, kT, joking);
   
   }
   public static void askUser(Scanner input, String chapterFileName, File chapterFile, Scanner keyTerms, String kT, File joking)throws FileNotFoundException{
      System.out.println();
         // Program asks for chapter, saves name of file, creates File object, creates Scanner object of File
      System.out.println("What chapter do you want to access? \n **Write chapter and the chapter number and add .txt, NO SPACES. Ex: chapter1.txt");
      input = new Scanner(System.in); //Scanner that asks for the chapter that the user wants
      chapterFileName = input.next(); //holds the chapter and becomes a txt file
      System.out.println();
      chapterFile = new File (chapterFileName); //new
      keyTerms = new Scanner(chapterFile);      
      
      // Program asks for keyterm to search for, saves it
      System.out.print("What keyterm would you like? ");
      kT = input.next();
      kT += input.nextLine();
      System.out.println();
      
      name(input, chapterFileName, chapterFile, keyTerms, kT, joking);
   }
   //prints out the name
   //checks the name/keyterm - the first line of each set
   public static void name (Scanner input, String chapterFileName, File chapterFile, Scanner keyTerms, String kT, File joking) throws FileNotFoundException{
      boolean hasKT = false;
      String currentKT = "";
      String def = "";
      String sig = "";
      
      while(keyTerms.hasNextLine() && hasKT == false){
         currentKT = keyTerms.nextLine();
         def = keyTerms.nextLine();
         sig = keyTerms.nextLine();
         hasKT = kT.equalsIgnoreCase(currentKT); 
      
      }
      
      if(hasKT){ //checks if the KT is in the txt file
         System.out.println(currentKT + ": ");
         System.out.println();
         definitions(def);
         significance(sig);
         joke(input, chapterFileName, chapterFile, keyTerms, kT, joking);
      }
      else {
         System.out.println("Sorry. The keyterm you asked for is not in this chapter. Try a different chapter or check your spelling.");
         System.out.println();
         System.out.print("Would you like to try again? ");
         String again = input.next();
         System.out.println();
         if(again.startsWith("y")){
            joke(input, chapterFileName, chapterFile, keyTerms, kT, joking);
         }
         else {
            System.out.println("Thank you.");
            System.exit(0);
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
   //joke method
   public static void joke(Scanner input, String chapterFileName, File chapterFile, Scanner keyTerms, String kT, File joking) throws FileNotFoundException{
      Scanner jokes = new Scanner(joking);
      System.out.print("Do you have another keyterm? ");
      String answer = input.next();
      System.out.println();
      //int kTNumber = 1;
      String jk = ""; 
      String punchLine = "";
      while(answer.startsWith("y")){
         KTAnswer++;
         if(KTAnswer % 10 == 0){
            jk = jokes.nextLine();
            punchLine = jokes.nextLine();
            System.out.println(jk);
            String userAnswer = input.next();
            userAnswer += input.nextLine();
            if(userAnswer.equalsIgnoreCase(punchLine)){
               System.out.println("Congratulations! You guessed correctly!");
            }
            else {
               System.out.println("Too bad. The correct answer is: " + punchLine);
            }
         }
         askUser(input, chapterFileName, chapterFile, keyTerms, kT, joking);
      }
      //answer.substring(0, 1).equalsIgnoreCase("n") {
      if(answer.startsWith("n")){
         System.out.println("Thank you.");
         System.exit(0);
      }
   }
}