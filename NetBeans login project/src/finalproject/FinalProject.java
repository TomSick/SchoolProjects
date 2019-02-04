/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 *
 * @author Tom Sickenberger
 */

public class FinalProject {
     // main() method
     public static void main(String[] args) throws IOException{
          // Call loginScreen() method
          loginScreen();
     }
   
     // loginScreen() method for user login
     public static void loginScreen() throws IOException{
          // Declare variables
          String genPwd;              
          int userRole = 0;
          int attempts=3;
                  
          // Create an object 'br' for BufferedReader class to accept data from
          // user and read data from a file

          Scanner logIn = new Scanner(System.in);       
          System.out.println("Login");
          try {
              do{
                 attempts--; //Decrease the amount of remaing log in attempts 
                
                 //Take user's log in information
                 System.out.println("Enter Username");
                 String userName = logIn.nextLine();
                 System.out.println("Enter Password");
                 String userPass = logIn.nextLine();
       
                 // MD5 password encryption
                    String original = userPass;
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    md.update(original.getBytes());
                    byte[] digest = md.digest();
                 StringBuffer sb = new StringBuffer();
                     for (byte b : digest) {
                        sb.append(String.format("%02x", b & 0xff));
                     }
                   //Write the encrpyed password to a string
                genPwd = sb.toString();
                            
                   String currentLine;                  
                   // Open the credentials file
                   BufferedReader bin = new BufferedReader(new FileReader("credentials.txt"));
                                     
                   // Reads the each line from the file
                   while ((currentLine = bin.readLine()) != null)
                   {
                        // Check username
                        if (currentLine.contains(userName)) {     // Checks to first to see if the username is present in the current line
                             if (currentLine.contains(genPwd)){   // Next the password of the same line is checked to see if it matches the encrypted password entered
                                System.out.println("Log in successful");
                                System.out.println();
                                
                                //Depending on the job type on the line the username and password were present on a userRole value is assigned
                                if (currentLine.contains("admin")) {
                                    userRole = 1;
                                }
                                if (currentLine.contains("veterinarian")) {
                                    userRole = 2;
                                }
                                if (currentLine.contains("zookeeper")) {
                                    userRole = 3;
                                }                                    
                             }
                         }
                   }
         
                   // Checks to see if the user has failed login 3 times
                   // If they have the program will exit

                   if(attempts==-1){
                        System.out.println("You are attempted to login more then three times");
                        System.out.println("Exiting...");
                        System.exit(1);  
                   }
                   
                   // If username and password is true then this will call the userScreen() method with the users role
                   if (userRole > 0){  
                       UserRoles.userScreen(userRole); 
                   }                                              
                    
                   // Login will reset on failure if 3 failed attempets haven't been made
                   else{
                        System.out.println("Invalid Username or Password.");
                        System.out.println("Please try again.");                     
                        System.out.println(attempts+" more attemptes left.\n");   
                   }
              }while(attempts>0);
          }
          catch (NoSuchAlgorithmException | IOException e){
          }
     }        
}
