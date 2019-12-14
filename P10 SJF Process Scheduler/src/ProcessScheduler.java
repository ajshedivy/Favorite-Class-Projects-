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
import java.util.Scanner;

/**
 * Scheduler class that represents data type for the main scheduler for processes
 * 
 * @author ajshe
 *
 */


public class ProcessScheduler {

  private int currentTime; // stores the current time after the last run
  private int numProcessesRun; // stores the number of processes run so far
  private WaitingProcessQueue queue; // this processing unit's queue

  //// Strings used for driver app //////////////////////////////////////////////////////////
  private static String MENU = "" + "[schedule <burstTime>] or [s <burstTime>]\r\n"
      + "[run] or [r]\r\n" + "[quit] or [q]\r\n" + "Enter command:\r\n";
  private static String HEADER = "========== Welcome to the SJF Process Scheduler App ========";
  private static String sch = "schedule";
  private static String run = "run";
  private static String quit = "quit";
  //////////////////////////////////////////////////////////////////////////////////////////

  // constructor
  public ProcessScheduler() {
    this.queue = new WaitingProcessQueue();
    this.currentTime = 0;
    this.numProcessesRun = 0;

  }

  // This method inserts the given process in the WaitingProcessQueue queue.
  public void scheduleProcess(CustomProcess process) {
    queue.insert(process);
  }

  // runs the ready processes already scheduled in this processScheduler's queue
  public String run() {
    String log = ""; // string to store the log
    if (queue.size() == 1) {
      log += "Starting " + queue.size() + " process\n\n";
    } else {
      log += "Starting " + queue.size() + " processes\n\n";
    }

    while (!queue.isEmpty()) {

      // remove the best process, i.e. the top of the queue
      CustomProcess remove = queue.removeBest();
      log += "Time " + currentTime + " : Process ID " + remove.getProcessId() + " Starting.\n";
      this.currentTime += remove.getBurstTime();
      log += "Time " + currentTime + " : Process ID " + remove.getProcessId() + " Completed.\n";

      numProcessesRun++;
      if (queue.size() == 1) { // last process to be removed
        CustomProcess lastProcess = queue.removeBest();
        log +=
            "Time " + currentTime + " : Process ID " + lastProcess.getProcessId() + " Starting.\n";
        this.currentTime += lastProcess.getBurstTime();
        log +=
            "Time " + currentTime + " : Process ID " + lastProcess.getProcessId() + " Completed.\n";

        numProcessesRun++;
      } else {
        continue;
      }
    }
    log += "\nTime " + this.currentTime + ": All scheduled processes completed.\n";

    return log;
  }

  /**
   * helper method to run scheduler application
   * @param sc - scanner for user input
   * @param schedule - ProcessSchedule object 
   */
  private static void scheduleApp(Scanner sc, ProcessScheduler schedule) {
    System.out.println(MENU);

    String input = sc.nextLine();
    String[] cmd = input.split(" ");
    String first = cmd[0]; // first command
    int update = 0; // determines if application will run again or terminate

    // check for invalid commands
    if (!first.equals(sch) && !first.equals("s") && !first.equals(run) && !first.equals("r")
        && !first.equals(quit) && !first.equals("q")) {

      System.out.println("WARNING: Please enter a valid command!");
      update = 1; // update accordingly to start application
    }
    if (first.equals(sch) || first.equals("s")) { // case 1: user to schedule process

      // check to see if the second command valid
      try {
        int last = Integer.parseInt(cmd[1]);
        try { // schedule process if second command valid
          CustomProcess process = new CustomProcess(last);
          schedule.scheduleProcess(process);
          System.out.println("Process ID " + process.getProcessId() + " scheduled. Burst Time = "
              + process.getBurstTime());
          update = 1;
        } catch (IllegalArgumentException e) {
          System.out.println(e);
          update = 1;
        }
      } catch (Exception e) { // second command invalid exception caught
        System.out.println(e);
        System.out.println("WARNING: burst time MUST be an integer!");
        update = 1;
      }
    }
    if (first.equals(run) || first.equals("r")) { // case 2: user calls run() to run process(es)

      update = 1;
    }

    if (first.equals(quit) || first.equals("q")) { // case 3: user quits scheduler app
      System.out.println(schedule.numProcessesRun + " processes run in " + schedule.currentTime
          + " units of time!\n" + "Thank you for using our scheduler!\n" + "Goodbye!\n");
      update = 0;
    }
    if (update == 1) { // run application if update = 1
      scheduleApp(sc, schedule);
    } else {
      return;
    }

  }

  // helper for printing header to start application
  private static void printStuff() {
    System.out.println(HEADER);
  }

  // driver for the application, calls two helper methods
  private static void driver(Scanner sc, ProcessScheduler p) {
    printStuff();
    scheduleApp(sc, p);

  }


  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    ProcessScheduler s = new ProcessScheduler();
    driver(sc, s);

  }

} // end ProcessScheduler class


