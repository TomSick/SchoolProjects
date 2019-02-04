/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

import static finalproject.FinalProject.loginScreen;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tom Sickenberger
 */
public class UserRoles {
        
    public static void userScreen(int userRole) throws IOException {          
         Scanner userIn= new Scanner(System.in);  // Scanner for user input   
         String currentLine; 
         String logOut; 
         
         //If/else tree uses the value of userRole from logInScreen() to discern the type of user who has logged on
         
         if (userRole == 1) {
             try (BufferedReader br = new BufferedReader(new FileReader("admin.txt"))) { //Loads the admin text file
                 while ((currentLine = br.readLine()) != null) {                        //Loop runs through the file and prints contents until there is no content left
                    System.out.println(currentLine);
                 }
             }           catch (FileNotFoundException ex) {
                            Logger.getLogger(UserRoles.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
         
         else if (userRole == 2) {
             try (BufferedReader br = new BufferedReader(new FileReader("veterinarian.txt"))) { //Loads the vet text file
               while ((currentLine = br.readLine()) != null) {
                System.out.println(currentLine);
              }
             }           catch (FileNotFoundException ex) {
                Logger.getLogger(UserRoles.class.getName()).log(Level.SEVERE, null, ex);
            }           
         }
         
         else {
             try (BufferedReader br = new BufferedReader(new FileReader("zookeeper.txt"))) { //Loads the zookeeper text file
             while ((currentLine = br.readLine()) != null) {
              System.out.println(currentLine);
              }
                }           catch (FileNotFoundException ex) {
                Logger.getLogger(UserRoles.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
      
          System.out.println("Type exit to log out");
                    
            // Loop checks to see if the user enters exit to log out
          do{
              logOut = userIn.nextLine();
          }while(!logOut.equals("exit"));
          // If exit is entered the loop ends and the if statement runs
          if(logOut.equals("exit"))
          {// Log the user out by calling the login screen
              loginScreen();
          } 
     }
     
}
