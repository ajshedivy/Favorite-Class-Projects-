//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           P09 Camp Badger
// Files:           Camper, CamperBST, CampTreeNode, CampManager, CampEnrollmentApp
// Course:          CS 300 fall 2019
//
// Author:          Adam Shedivy
// Email:           ajshedivy @ wisc
// Lecturer's Name: Gary Dahl 
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Sam Bahr
// Partner Email:   sdbahr @ wisc
// Partner Lecturer's Name: Gary Dahl
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates, 
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:         (identify each person and describe their help in detail)
// Online Sources:  (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
/**
 * Class to represent a camper as an object. Implements the comparable interface to allow us to
 * compare them to each other.
 * 
 * @author Michelle Jensen
 */

public class Camper implements Comparable<Camper> {

  private String firstName;
  private String lastName;
  private int age;
  private String cabin;

  /**
   * Constructor that sets the firstName, lastName, and age of an instance of the camper class.
   * 
   * @param firstName, the first name of the camper
   * @param lastName,  the last name of the camper
   * @param age,       the age of the camper
   * @throws IllegalArgumentException, if the age is outside of the range [8,14] (inclusive).
   */
  public Camper(String firstName, String lastName, int age) {
    if (age > 14 || age < 8)
      throw new IllegalArgumentException(
          "This person is either too old or too young to be in Camp Badger.");

    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
    this.cabin = "";
  }

  /**
   * Accessor that returns both the first and last name of a Camper in the correct format.
   * 
   * @return the string formated as "lastName, firstName". Ex. "Badger, Bucky"
   */
  public String getName() {
    return lastName + ", " + firstName;
  }

  /**
   * Getter for age field.
   * 
   * @return The age of this Camper.
   */
  public int getAge() {
    return age;
  };

  /**
   * Getter for firstName field. @ return The firstName of this Camper.
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Getter for lastName field.
   * 
   * @return The lastName of this Camper.
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Getter for cabin field.
   * 
   * @return The cabin of this Camper.
   */
  public String getCabin() {
    return cabin;
  }

  /**
   * Mutator for cabin field.
   * 
   * @param The name of the cabin to be assigned to this Camper.
   */
  public void assignCabin(String cabin) {
    this.cabin = cabin;
  }

  /**
   * Returns a string representation of this Camper.
   * 
   * @return This instance of camper formatted as "<lastName>, <firstName> Age: <age> Cabin:
   *         <cabin>". Ex. "Bucky, Badger Age: 10 Cabin: Badger Bunkhouse"
   */
  public String toString() {
    return lastName + ", " + firstName + " Age: " + age + " Cabin: " + cabin;
  }

  /**
   * Method required to use the Comparable interface. Compares based on "lastName, firstName" string
   * lexigraphically.
   * 
   * @return 0 if they are the same, a negative int (<0) if this Camper is less than the argument, a
   *         positive int (>0) if this Camper is greater than the argument.
   * @author Adam Shedivy <ajshedivy @ wisc>
   */
  @Override
  public int compareTo(Camper camper) {
    String firstName = this.lastName + ", " + this.firstName;
    String camperName = camper.lastName + ", " + camper.firstName;
    return firstName.compareTo(camperName);
    
    }

} // end Camper class