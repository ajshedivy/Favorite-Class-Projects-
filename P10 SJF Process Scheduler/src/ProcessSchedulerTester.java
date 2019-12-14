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
import java.util.NoSuchElementException;

/**
 * tester class for P10
 * 
 * @author ajshe
 *
 */
public class ProcessSchedulerTester {

  // checks the correctness of the insert operation
  // implemented in the WaitingProcessQueue class
  public static boolean testInsertWaitingProcessQueue() {
    System.out.println("---------------TESING INSERT---------------");
    WaitingProcessQueue q = new WaitingProcessQueue();

    // processes to be added to queue

    CustomProcess process1 = new CustomProcess(10);
    CustomProcess process2 = new CustomProcess(9);
    CustomProcess process3 = new CustomProcess(8);
    CustomProcess process4 = new CustomProcess(7);
    CustomProcess process5 = new CustomProcess(6);
    CustomProcess bestProcess = new CustomProcess(2);
    CustomProcess[] processList = {process1, process2, process3, process4, process5};

    for (int i = 0; i < processList.length; i++) {
      q.insert(processList[i]);
    }

    if (q.size() != 5) {
      System.out.println("error is size, size= " + q.size());
      return false;
    }
    if (q.peekBest() != process5) {
      System.out.println("false best: " + q.peekBest());
      return false;
    }
    q.insert(bestProcess);
    if (q.peekBest() != bestProcess) {
      System.out.println("After inserting a process with burstTime = 2: " + q.peekBest());
      return false;
    }



    System.out.println(q.toString());
    return true;

  }


  // checks the correctness of the removeBest operation
  // implemented in the WaitingProcessQueue class
  public static boolean testRemoveBestWaitingProcessQueue() {
    System.out.println("---------------TESING REMOVE---------------");
    WaitingProcessQueue removeQ = new WaitingProcessQueue();

    // processes to be added to queue
    CustomProcess process7 = new CustomProcess(11);
    CustomProcess process8 = new CustomProcess(8);
    CustomProcess process9 = new CustomProcess(5);
    CustomProcess process10 = new CustomProcess(8);
    CustomProcess process11 = new CustomProcess(2);
    CustomProcess bestProcess1 = new CustomProcess(1);
    CustomProcess[] processList =
        {process7, process8, process9, process10, process11, bestProcess1};


    for (int i = 0; i < processList.length; i++) {
      removeQ.insert(processList[i]);
    }

    // remove first process
    System.out.println("**heap before remove:");
    removeQ.toString();
    CustomProcess remove = removeQ.removeBest();
    if (remove != bestProcess1) {
      System.out.println("process removed: " + remove);
      return false;
    }

    // remove second process
    System.out.println("heap after removing process: " + removeQ.toString());
    CustomProcess remove2 = removeQ.removeBest();
    if (remove2 != process11) {
      System.out.println("process removed: " + remove2);
      return false;
    }

    // check bad heap, should catch exception
    WaitingProcessQueue badQ = new WaitingProcessQueue(); // empty queue
    try {
      badQ.removeBest();
      System.out.println("exception not caught");
      return false;
    } catch (NoSuchElementException e) {
      System.out.println("exception caught in tester method");
    }

    System.out.println("final heap: " + removeQ.toString());
    return true;
  }

  // test WaitingProcessQueue peek method 
  public static boolean testPeekExeptions() {
    System.out.println("---------------TESING EXCEPTIONS---------------");
    WaitingProcessQueue queue = new WaitingProcessQueue();
    try { // peek empty heap
      queue.peekBest();
      return false;
    } catch (NoSuchElementException e) {
      System.out.println(e);
      System.out.println("caught in tester");
    }

    return true;
  }

  // test CustomProcess class 
  public static boolean testCustomProcess() {
    try { // illegal burst time, should catch e
      CustomProcess p = new CustomProcess(-3);
      return false;
    } catch (IllegalArgumentException e) {
      System.out.println(e);
      System.out.println("caught in tester");

    }

    // test compareTo and accessor methods for CustomProcess class
    CustomProcess p = new CustomProcess(10);
    CustomProcess p2 = new CustomProcess(9);
    if (p.compareTo(p2) < 0 || p2.compareTo(p) > 0 || p.compareTo(p2) == 0) {
      System.out.println("compare");
      return false;
    }
    if (p.getBurstTime() != 10 || p2.getBurstTime() != 9) {
      System.out.println("p burst: " + p.getBurstTime() + "p2: " + p2.getBurstTime());
      return false;
    }
    if (p.getProcessId() != 13 || p2.getProcessId() != 14) {
      System.out.println("p ID : " + p.getProcessId() + " " + "p2: " + p2.getProcessId());
      return false;
    }
    return true;
  }

  public static void testDriver() {
    System.out.println(testInsertWaitingProcessQueue());
    System.out.println(testRemoveBestWaitingProcessQueue());
    System.out.println(testPeekExeptions());
    System.out.println(testCustomProcess());
  }

  public static void main(String[] args) {
    testDriver();

  }

} // end ProcessSchedulerTester class
