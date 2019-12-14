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
import java.util.NoSuchElementException;

/**
 * class to manager the campers of Camp Badger
 * 
 * @author ajshe
 *
 */
public class CampManager extends java.lang.Object {

  private CamperBST campers;
  private final static String[] CABIN_NAMES =
      new String[] {"Otter Overpass", "Wolverine Woodland", "Badger Bunkhouse"};

  public CampManager() {
    this.campers = new CamperBST();
  }

  public void printStatistics() {
    System.out.println("---Printing Statisicts---");
    System.out.println("Number of Campers: " + campers.size());
    System.out.println("-------------------------");
  }

  /**
   * "Enrolls" a camper by determining their cabin and adding them to the tree.
   * 
   * Campers of the ages 8-9 should be in "Otter Overpass", ages 10-12 in "Wolverine Woodland", and
   * ages 13-14 in "Badger Bunkhouse"
   * 
   * @param newCamper
   */
  public void enrollCamper(Camper newCamper) {
    // enroll camper in cabin based on age
    if (newCamper.getAge() >= 8 && newCamper.getAge() <= 9) {
      newCamper.assignCabin(CABIN_NAMES[0]);
      campers.insert(newCamper);
    } else if (newCamper.getAge() >= 10 && newCamper.getAge() <= 12) {
      newCamper.assignCabin(CABIN_NAMES[1]);
      campers.insert(newCamper);
    } else if (newCamper.getAge() >= 13 && newCamper.getAge() <= 14) {
      newCamper.assignCabin(CABIN_NAMES[2]);
      campers.insert(newCamper);
    } else {
      throw new IllegalArgumentException();
    }
    // campers.insert(newCamper);
  }

  public java.util.Iterator<Camper> traverse(java.lang.String order) {

    return campers.traverse(order);
  }

  public void unenrollCamper(Camper delCamper) throws java.util.NoSuchElementException {
    try {
      campers.delete(delCamper);
    } catch (NoSuchElementException e) { // camper is not in the system
      throw e; // throw to CampEnrollment class to print specific message
    }
  }
} // end CampManager class
