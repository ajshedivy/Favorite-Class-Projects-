//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P10 SJF Process Scheduler
// Files: CustomProcess, WaitingProcessQueue, ProcessScheduler, ProcessSchedulerTester
// Course: CS 300 fall 2019
//
// Author: Adam Shedivy
// Email: ajshedivy @ wisc
// Lecturer's Name: Gary Dahl
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: (name of your pair programming partner)
// Partner Email: (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
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
/**
 * class represents data type for the processes used in our application implements
 * java.lang.Comparable<CustomProcess>
 * 
 * @author ajshe
 *
 */

public class CustomProcess implements java.lang.Comparable<CustomProcess> {

  private static int nextProcessId = 1; // stores the id to be assigned to the next process
                                        // to be created
  private final int PROCESS_ID; // unique identifier for this process
  private int burstTime; // time required by this process for CPU execution



  // constructor
  public CustomProcess(int burstTime) {
    if (burstTime <= 0) {
      throw new IllegalArgumentException(
          "burstTime must be non zero. entered burstTime: " + burstTime);
    }
    this.burstTime = burstTime;
    this.PROCESS_ID = nextProcessId;
    nextProcessId++;
  }

  /**
   * p1.compareTo(p2) < 0 means that the p1 is smaller than p2. p1.compareTo(p2) == 0 means that p1
   * and p2 are equal with respect to the comparison criteria. p1.compareT o(p2) > 0 means that p1
   * is larger than p2.
   */
  @Override
  public int compareTo(CustomProcess other) {
    Integer compareBurst = this.burstTime;
    int answer = compareBurst.compareTo(other.burstTime); // int answer to be returned
    if (answer == 0) { // compare ID's of Processes 
      Integer process = this.getProcessId();
      return process.compareTo(other.getProcessId());
    }
    return answer;

  }

  public int getProcessId() {
    return this.PROCESS_ID;
  }

  public int getBurstTime() {
    return this.burstTime;
  }

  /**
   * Returns a String representation of this CustomProcess
   * 
   * @return a string representation of this CustomProcess
   */
  public String toString() {
    return "p" + this.PROCESS_ID + "(" + this.burstTime + ")";
  }



} // end CustomProcess class
