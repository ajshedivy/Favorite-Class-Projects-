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
 * class implements the WaitingQueueADT interface of CustomProcess houses min heap of processes
 * 
 * @author ajshe
 *
 */

public class WaitingProcessQueue implements WaitingQueueADT<CustomProcess> {

  private static final int INITIAL_CAPACITY = 3; // the initial capacity of this
                                                  // waiting process queue
  private CustomProcess[] data; // min heap-array storing the CustomProcesses
                                // inserted in this WaitingProcessQueue.
                                // data is an oversize array
  private int size; // number of CustomProcesses stored in this WaitingProcessQueue

  public WaitingProcessQueue() {
    this.data = new CustomProcess[INITIAL_CAPACITY];
  }

  private void swap(int i, int j) {

    CustomProcess temp = data[i];
    data[i] = data[j];
    data[j] = temp;

  }


  /**
   * implementation of Percolate Up algorithm of min heap
   * 
   * @param index - index to percolate up from
   */
  private void minHeapPercolateUp(int index) {
    while (index > 0) {
      int parent = (int) Math.floor((index - 1) / 2); // parent index
      int compareResult = data[index].compareTo(data[parent]); // integer value of the comparison of
                                                               // process at `index` to process at
                                                               // index `parent`
      if (compareResult >= 0) { // order of heap "heapified" for min heap
        break;
      } else {
        swap(index, parent);
        index = parent;
      }
    }
  } // end minHeapPercolateUp method

  /**
   * implementation of Percolate Down algorithm of min heap
   * 
   * @param index - index to percolate down from
   */
  private void minHeapPercolateDown(int index) {

    int smallest = index;
    int leftChild = (2 * index) + 1; // left child index
    int rightChild = (2 * index) + 2; // right child index

    // BASE CASE: if rightChild or leftChild are greater that the size, the end of the heap has been
    // reached
    if ((rightChild > size() - 1) || (leftChild > size() - 1)) {
      return;
    }

    // FOLLOWING USED FOR DEBUGGING:
    ///////////////////////////////////////////////////////////////////////
    // System.out.println("index to percolate down on: " + smallest);
    // System.out.println("left child: " + leftChild);
    // System.out.println("right child: " + rightChild);
    // System.out.println("data[smallest]: " + data[smallest]);
    // System.out.println("data[leftchild]: " + data[leftChild]);
    // System.out.println("data[rightchild]: " + data[rightChild]);
    // printdata();
    ///////////////////////////////////////////////////////////////////////

    // compare smallest to children
    if (leftChild < size() - 1 && data[smallest].compareTo(data[leftChild]) > 0) {
      smallest = leftChild;
    }
    if (rightChild < size() - 1 && data[smallest].compareTo(data[rightChild]) > 0) {
      smallest = rightChild;
    }
    if (smallest != index) { // swap processes
      swap(index, smallest);
      minHeapPercolateDown(smallest);
    }

  } // end minHeapPercolateDown method



  @Override
  /**
   * insert method for CustomProcess
   * 
   * @param newObject - new process to be inserted in min heap
   */
  public void insert(CustomProcess newObject) {
    try {
      data[size] = newObject;
      int index = size;
      minHeapPercolateUp(index);
      size++;
      
    }
    catch (ArrayIndexOutOfBoundsException e) {
      CustomProcess[] dataTemp = new CustomProcess[size() *2];
      for (int i = 0; i < size(); i++) {
        dataTemp[i] = data[i];
        
      }
      data = dataTemp;
      data[size] = newObject;
      int index = size;
      minHeapPercolateUp(index);
      size++;
      
    }
  }


  /**
   * remove best process from min heap
   */
  @Override
  public CustomProcess removeBest() {
    if (isEmpty()) {
      throw new NoSuchElementException("queue is empty, cannot remove from empty queue");
    }
    CustomProcess best = data[0]; // process to be removed
    data[0] = data[size - 1];
    data[size - 1] = null;
    minHeapPercolateDown(0); // Percolate down on first index
    size--;
    return best;
  }

  /**
   * peak the top of the min heap
   */
  @Override
  public CustomProcess peekBest() {
    if (data[0] == null || isEmpty()) {
      throw new NoSuchElementException("queue is empty");
    }
    return data[0];
  }

  @Override
  public int size() { // return size
    return size;
  }

  @Override
  public boolean isEmpty() { // check if heap is empty
    return size == 0;
  }

  @Override
  public String toString() { // print processes in the heap 
    String processString = "";
    if (isEmpty()) {
      return " ";
    }

    for (int i = 0; i < data.length; i++) {
      if (data[i] == null) {
        return processString;
      }
      processString += data[i].toString() + " ";

    }
    return processString;

  }
} // end WaitingProcessQueue class
