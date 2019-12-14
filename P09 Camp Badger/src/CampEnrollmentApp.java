//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P09 Camp Badger
// Files: Camper, CamperBST, CampTreeNode, CampManager, CampEnrollmentApp
// Course: CS 300 fall 2019
//
// Author: Adam Shedivy
// Email: ajshedivy @ wisc
// Lecturer's Name: Gary Dahl
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Sam Bahr
// Partner Email: sdbahr @ wisc
// Partner Lecturer's Name: Gary Dahl
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: (identify each person and describe their help in detail)
// Online Sources: (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

/**
 * enrollment app
 * 
 * @author ajshe
 *
 */
public class CampEnrollmentApp {



  public static void main(String[] args) throws IOException {
    // list of valid commands
    Character stats = 'S';
    Character enroll = 'E';
    Character traverse = 'T';
    Character unEnroll = 'R';

    // list that holds the history of enrolled campers
    // NOTE: this is only a tool to see history and not used for parsing commands
    List<Camper> camperList = new ArrayList<Camper>();

    List<String> fileLines = Files.readAllLines(Paths.get("sim.txt"));
    CampManager manager = new CampManager();
    for (String line : fileLines) {
      if (line.toUpperCase().charAt(0) == stats) {
        manager.printStatistics();
      }
      if (line.toUpperCase().charAt(0) == enroll) { // enroll student
        String[] names = line.split(" ");
        String firstName = names[2];
        String lastName = names[1];
        int age = Integer.parseInt(names[3]);
        try {
          Camper addCamper = new Camper(firstName, lastName, age);
          manager.enrollCamper(addCamper);
          camperList.add(addCamper);
          System.out.print("--Enrollment of: ");
          System.out.println(firstName + ", " + lastName + " Successfull!!");
        } catch (IllegalArgumentException e) {
          int ageGap;

          // custom message if invalid age is entered
          if (age < 8) {
            ageGap = 8 - age;
            String msg = "Enroll in " + ageGap + " years";
            System.out.println(
                "**AGE INVALID**: the age = " + age + " is not in range [8, 14]. " + msg + " **");
          } else {
            ageGap = age;
            String msg2 = ageGap + " is too old :(";
            System.out.println(
                "**AGE INVALID**: the age = " + age + " is not in range [8, 14]. " + msg2 + " **");
          }
        }
      }
      if (line.toUpperCase().charAt(0) == traverse) { // traverse BST according to specified type 
        String[] names = line.split(" ");
        String travType = names[1]; // type of traversal 

        System.out.println("--- " + travType + " Traversal" + " ---");
        manager.traverse(travType);
      }
      if (line.toUpperCase().charAt(0) == unEnroll) { // un-enroll camper from Camp, ie remove camper from BST
        String[] names = line.split(" ");
        String firstName = names[2];
        String lastName = names[1];
        Camper delCamper = new Camper(firstName, lastName, 10); // camper to be removed 
        try {
          manager.unenrollCamper(delCamper);
          System.out.println("--Unenrollment of " + firstName + " " + lastName + " Successful");
        } catch (NoSuchElementException e) {
          System.out.println("CAUGHT: NoSuchElementException in enrollment app");
          System.out.println("**Unenrollment of " + firstName + " " + lastName + " UNSUCCESSFUL**");
        }
      }
    }

  }
} // end CampEnrollmentApp Class 


