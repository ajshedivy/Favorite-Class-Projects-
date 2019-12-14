
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           BlockLinkedLists
// Files:           LinkedMegaBlock.java, LinkedListMegaBlock.java, MegaBlockBuilderTester.java
// Course:          CS 300, Fall, 2019
//
// Author:          Adam Shedivy
// Email:           ajshedivy@wisc.edu
// Lecturer's Name: Gary Dahl
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Samuel Bahr
// Partner Email:   adbahr@wisc.edu
// Partner Lecturer's Name: Gary Dahl
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   X   Write-up states that pair programming is allowed for this assignment.
//   X   We have both read and understand the course Pair Programming Policy.
//   X   We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates, 
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:         N/A
// Online Sources:  N/A
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////



import java.util.NoSuchElementException;


/**
 * @author Samuel Bahr` and Adam Shedivy
 * 
 *         The LinkedListMegaBlock class constructs a LinkedList consisting of
 *         LinkedMegaBlock objects. Within this class, one initializes a
 *         LInkedListMegaBlock and is able to add RED, BLUE, and YELLOW blocks
 *         to the list. In addition, one is able to set the blocks of the list,
 *         get blocks at a specific index, remove blocks, and clear the list.
 *
 */
public class LinkedListMegaBlock {
	private LinkedMegaBlock head; // head of this list
	private LinkedMegaBlock tail; // tail of this list
	private int size; // size of this list (total number of megablocks stored in this list)
	private int redCount; // number of RED megablocks stored in this list
	private int yellowCount; // number of YELLOW megablocks stored in this list
	private int blueCount; // number of BLUE megablocks stored in this list

	/**
	 * Constructor Initializes Linked List
	 */
	LinkedListMegaBlock() {
		this.head = null;
	}

	/**
	 * @return - Amount of Blue Blocks in the Linked List
	 */
	public int getBlueCount() {
		return blueCount;
	}

	/**
	 * @return - Amount of Red Blocks in the Linked List
	 */
	public int getRedCount() {
		return redCount;
	}

	/**
	 * @return - Amount of Yellow Blocks in the LinkedList
	 */
	public int getYellowCount() {
		return yellowCount;
	}

	/**
	 * @return - A Boolean which represents whether the linked list is empty or not.
	 */
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		return false;
	}

	/**
	 * @return size of Linked List
	 */
	public int size() {
		return size;
	}

	/**
	 * Clears Linked List
	 */
	public void clear() {
		this.head = null;
		this.tail = null;
		this.size = 0;
		this.redCount = 0;
		this.blueCount = 0;
		this.yellowCount = 0;
	}

	/**
	 * @param blueBlock - MegaBlock to be added to the linked list
	 */
	public void addBlue(MegaBlock blueBlock) {
		if(blueBlock == null) {
			throw new IllegalArgumentException();
		}
		if (!blueBlock.getColor().equals(Color.BLUE)) { // Checks to see if the MegaBlock is valid
			throw new IllegalArgumentException();
		}
		LinkedMegaBlock block = new LinkedMegaBlock(blueBlock);

		if (isEmpty()) { // Special Case
			head = block;
			tail = block;
			blueCount++;
			size++;
		} else {
			LinkedMegaBlock copy = head; // Makes a reference to the head
			for (int i = 1; i < size; i++) {
				copy = copy.getNext(); // Iterates until the second to last MegaBlock of the list
			}
			tail = block; // Assigns the tail to the newly created LinkedMegaBlock object
			copy.setNext(tail); // Sets the next reference as the tail.
			size++;
			blueCount++;

		}
	}

	/**
	 * @param redBlock - MegaBlock to be added to the linked list
	 */
	public void addRed(MegaBlock redBlock) {
		if (!redBlock.getColor().equals(Color.RED) || redBlock == null) {
			throw new IllegalArgumentException(); // Checks to see if the MegaBLock is valid
		}
		LinkedMegaBlock block = new LinkedMegaBlock(redBlock);

		if (isEmpty()) { // Special Case
			head = block;
			tail = block;
		} else {
			LinkedMegaBlock currBlock = head; // Makes a reference to the Head of the list
			head = block; // Assigns head to new LinkedMegaBlock object
			head.setNext(currBlock); // Sets the previous head as the next reference
		}
		size++;
		redCount++;

	}

	/**
	 * @param index       - Specific Index where a YellowBlock will be inserted
	 * @param yellowBlock - The MegaBlock which will be inserted into the linked
	 *                    list
	 */
	public void addYellow(int index, MegaBlock yellowBlock) {
		if(yellowBlock == null) {
			throw new IllegalArgumentException();
		}
		if (!yellowBlock.getColor().equals(Color.YELLOW)) { // Checks if the YellowBlock is valid
			throw new IllegalArgumentException();
		}
		if (index < redCount || index > size - blueCount) {
			throw new IndexOutOfBoundsException(); // Checks if the Index is valid
		}
		// Creates a new LinkedMegaBlock object with the yellowBlock embedded within 
		LinkedMegaBlock block = new LinkedMegaBlock(yellowBlock); 
		LinkedMegaBlock curBlock = head; // Creates a copy reference to the head
		int count = 0; // Used for iterating through the list
		if(size == 0) {
			//curBlock.setBlock(yellowBlock);
			head = block;
			tail = block;
			//tail = curBlock;
			yellowCount++;
			size++;
			
		}
		//single block list with one red block
		else if (size == 1 && head.getBlock().getColor().equals(Color.RED)) {
		  tail = block;
		  yellowCount++;
		  size++;
		}
		//single block list with one blue block
		else if (size == 1 && head.getBlock().getColor().equals(Color.BLUE)) {
		  head = block;
		  yellowCount++;
		  size++;
		}
		else {
		
			while (count < index - 1) {
				curBlock = curBlock.getNext();
				count++;
			}
			block.setNext(curBlock.getNext()); // Sets the next reference to the CurBlock's next reference
			curBlock.setNext(block); // Sets the next reference to CurBlock
			yellowCount++;
			size++;	
		
		}

	}

	/**
	 * @param index - Specific Index where a MegaBlock will be retrieved
	 * @return - Specific MegaBlock at the specified index
	 */
	public MegaBlock get(int index) {
		if (index < 0 || index >= size()) { // Checks for a valid index
			throw new IndexOutOfBoundsException();
		}
		int counter = 0; // used as an index tracker
		LinkedMegaBlock curBlock = head; // Copys reference of the head
		if (index == 0) { // Special Case
			return head.getBlock();
		}
		while (counter != index) { // Iterates through Linked List
			counter++;
			curBlock = curBlock.getNext();
		}
		return curBlock.getBlock(); // Returns the MegaBlock at specific Index
	}

	/**
	 * @param index - Specific Index where MegaBlock will be set
	 * @param block - Block that will be set within the Linked List
	 * @return - Returns the replaced Block
	 */
	public MegaBlock set(int index, MegaBlock block) {
		if (block == null) { // Checks if the Param Block is null
			throw new IllegalArgumentException();
		}
		if (index < 0 || index >= size()) { // Checks if the index is valid
			throw new IndexOutOfBoundsException();
		}
		LinkedMegaBlock curBlock = head; // Makes a copy of the head
		LinkedMegaBlock previousBlock;
		for (int i = 1; i < index - 1; i++) {
			curBlock = curBlock.getNext(); // Iterates through the list until the position before the index
		}
		previousBlock = curBlock; // Previous Block is assigned
		curBlock = curBlock.getNext(); // Iterates
		MegaBlock block2 = curBlock.getBlock(); // Grabs the replaced Block
		if (block2.getColor() != block.getColor()) {
			throw new IllegalArgumentException(); //Checks to see if the 
			                                      //Replaced Block and the 
			                                      //incoming Block are the
												  // same color
		}
		curBlock.setBlock(block); // Sets Block
		previousBlock.setNext(curBlock); // Sets the next reference
		return block2; // returns replaced
	}

	/**
	 * @return The removed Red MegaBlock(HEAD)
	 */
	public MegaBlock removeRed() {
		if (redCount == 0) {
			throw new NoSuchElementException(); //Checks to see if list contains any Red MegaBlocks
		}
		LinkedMegaBlock currBlock = head; // Makes a copy of the head.
		if (size == 1) { // Special Case
			MegaBlock block = head.getBlock();
			head = null;
			tail = null;
			size--;
			redCount--;
			return block;
		} else {
			MegaBlock block = head.getBlock(); // Grabs the Block at head
			currBlock = head.getNext(); // Iterates to the next LinkedMegaBlock object in list
			head = currBlock; // sets object as head
			size--;
			redCount--;
			return block;
		}
	}

	/**
	 * @return The removed Blue MegaBlock(TAIL)
	 */
	public MegaBlock removeBlue() {
		if (blueCount == 0) { // Checks if there are blue MegaBlocks within the LinkedList
			throw new NoSuchElementException();
		}
		MegaBlock block = tail.getBlock();
		   if (head == tail) {
		     MegaBlock specialBlock = tail.getBlock();
		     tail = null;
		     head = null;
		     size--;
		     blueCount--;
		     return specialBlock;
		   }
		   else {
		     LinkedMegaBlock runner = head;
		     while (runner.getNext() != tail) {
		       runner = runner.getNext();
		     }
		     tail = runner; 
		     size--;
		     blueCount--;
		     return block;
		   }

	}

	/**
	 * @param index - Specific index where the YellowBlock will be removed
	 * @return - The removed MegaBlock at the specific index.
	 */
	public MegaBlock removeYellow(int index) {
		if (index < redCount || index >= size - blueCount) {
			throw new IndexOutOfBoundsException(); // Checks if the Index is valid
		}
		if (yellowCount == 0) { // Checks if there are Yellow MegaBLocks within the LinkedList
			throw new NoSuchElementException();
		}
		LinkedMegaBlock currBlock = head; // Copy a reference of the head
		LinkedMegaBlock previousBlock;
		LinkedMegaBlock afterBlock;
		for (int i = 1; i < index - 1; i++) {
			currBlock = currBlock.getNext(); //Iterates through the list until block before the index
		}
		previousBlock = currBlock; // Previous block is then assigned
		currBlock = currBlock.getNext(); // Iterates
		afterBlock = currBlock.getNext(); // After block is then assigned
		previousBlock.setNext(afterBlock); // Previous block then sets next -> after block
		yellowCount--; // YellowCount reduces as well as the size
		size--;
		return currBlock.getBlock();

	}

	/**
	 * Overrides the Java.Object toString method. When called this method will
	 * output the LinkedMega Blocks that are associated with the LinkedListMegaBlock
	 * object.
	 */
	@Override
	public String toString() {

	  String link = "";
	    if (isEmpty()) {
	      return link;
	    }
	    else {
	      LinkedMegaBlock runner = head;
	      while (runner != null) {
	        link = link + runner.toString();
	        runner = runner.getNext();
	        }
	      }
	      return link;
	    }
	
	/**
	* Helper method to display the contents of a list of mega blocks
	* @param list a reference to a LinkedListMegaBlock object
	* @throws NullPointerException if list is null
	*/
	private static void display(LinkedListMegaBlock list) {
	// display list content
	System.out.println("list content: " + list);
	// display information about the size of this list and the its blocks' color counts
	System.out.println("Size: " + list.size() +
	", redCount: " + list.getRedCount() +
	", yellowCount: " + list.getYellowCount() +
	", blueCount: " + list.getBlueCount());
	System.out.println();
	}
	/**
	* Driver method to show how to use the implemented classes in P06 Mega Blocks Builder
	* @param args input arguments
	*/
	public static void main(String[] args) {
	// Create a new empty LinkedListMegaBlocks list
	LinkedListMegaBlock list = new LinkedListMegaBlock();
	// display list's content and size information
	display(list);
	// Add some blocks to list and display its contents and size information
	list.addBlue(new MegaBlock(Color.BLUE, 'C')); // add a blue mega block
	display(list);
	list.addBlue(new MegaBlock(Color.BLUE, 'S')); // add a blue mega block
	display(list);
	list.addYellow(0,new MegaBlock(Color.YELLOW, 'Y')); // add a yellow mega block
	// at index 0 of this list
	display(list);
	list.addRed(new MegaBlock(Color.RED, 'A')); // add a red mega block to this list
	list.addRed(new MegaBlock(Color.RED, 'B')); // add a red mega block to this list
	list.addYellow(3, new MegaBlock(Color.YELLOW, 'H')); // add a yellow mega block
	// at index 3 of this list
	display(list);
	// remove/add some blocks and display the list after each operation
	list.removeBlue(); // remove a blue block
	display(list);
	list.removeBlue(); // remove a blue block
	display(list);
	// add a yellow block at the end of list (the list contains zero blue blocks)
	list.addYellow(list.size(), new MegaBlock(Color.YELLOW, 'W'));
	display(list);
	list.removeRed(); // remove a red block
	display(list);
	list.removeYellow(list.size()-1); // remove the yellow block at the end of list
	display(list);
	}
	
	
} //end LinkedMegaBlock Class
