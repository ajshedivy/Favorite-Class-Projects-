import java.awt.DisplayMode;

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

/**
 * @author Samuel Bahr and Adam Shedivy
 * 
 *         The MegaBlockBuiderTester class contains 6 methods which perform
 *         multiple tests on the LinkedListMegaBlock and LinkedMegaBlock classes
 *
 */
public class MegaBlockBuilderTester {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	  LinkedListMegaBlock blocks = new LinkedListMegaBlock();
	  blocks.addBlue(new MegaBlock(Color.BLUE, '1'));
	  blocks.addRed(new MegaBlock(Color.RED, '2'));
	  blocks.addRed(new MegaBlock(Color.RED, '3'));
	  System.out.println(blocks.toString());
	  blocks.set(2, new MegaBlock(Color.RED, '4'));
	

	}
	public static void testDriver() {
	  System.out.println(testLinked());
	    
      System.out.println(testMegaBlockEquals());
      System.out.println(testMegaBlockToString());
      System.out.println(testLinkedMegaBlock());
      System.out.println(testLinkedMegaBlockListAddRed());
      System.out.println(testLinkedMegaBlockListAddRed());
      System.out.println(testLinkedListMegaBlock());
	}

	public static boolean testLinked() {
	  System.out.println("**TESTING testLinked()**");
		LinkedMegaBlock aBlock = new LinkedMegaBlock(new MegaBlock(Color.RED, 'a'));
		LinkedMegaBlock aBlock2 = new LinkedMegaBlock(new MegaBlock(Color.BLUE, 'b'));
		aBlock.setNext(aBlock2);
		//System.out.println(aBlock.toString());
		return true;
	}

	/**
	 * @return A boolean value which represents a Pass or Fail on the test method
	 */
	public static boolean testMegaBlockEquals() {
	  System.out.println("**TESTING MegaBlockEquals**");
		MegaBlock block = new MegaBlock(Color.RED, 'd'); // Creates a new MegaBlock Object
		MegaBlock block2 = new MegaBlock(Color.RED, 'e');// Creates the same MegaBlock Object
		if (block.equals(block2)) { // Tests whether the two objects equal each other.
			return true;
		}
		return false;
	}

	/**
	 * @return A boolean value which represents a Pass or Fail on the test method
	 */
	public static boolean testMegaBlockToString() {
	  System.out.println("**TESTING MegaBlockToString**");
		MegaBlock block = new MegaBlock(Color.BLUE, 'f'); // Creates new MegaBlock object
		char c1 = 'f';   //char label
		String string = block.toString(); // calls toString Method and assigns the output to a string
		String[] splitString = string.split(" "); // splits string into an array based upon a space
		String split = splitString[1];
		char[] charValue = split.toCharArray();  //array containing ['f']
		Character value = charValue[0];   //char label to be checked for accuracy 
		if (splitString[0].equals("BLUE") && value.equals(c1)) { // Checks for toString accuracy
			return true;
		}
		return false;
	}

	/**
	 * @return A boolean value which represents a Pass or Fail on the test method
	 *         Tests for Success of a Constructor, Accessor, and Mutator
	 */
	public static boolean testLinkedMegaBlock() {
	  System.out.println("**TESTING LinkedMegaBlock Class**");
		int count = 0; // Multiple Test
		LinkedMegaBlock block = new LinkedMegaBlock(new MegaBlock(Color.RED, 'd'));
		block.setBlock(new MegaBlock(Color.BLUE, 'e')); // Sets a Block
		if (block.getBlock().getColor() == Color.BLUE) { // Gets the same block
			count++; // Increments if the Statement is true
		}
		MegaBlock testBlock = block.getBlock(); // Tests Accessor Method
		if (testBlock.equals(block.getBlock())) {
			count++; // Increments if the statement is true
		}
		if (count == 2) {
			return true;
		}
		return false;

	}

	// checks for the correctness of the LinkedListMegaBlock.addRed() method
	/**
	 * @return A boolean value which represents a Pass or Fail on the test method
	 */
	public static boolean testLinkedMegaBlockListAddRed() {
	  System.out.println("**TESTING MegaBlockList addRed method**");
		int count = 0; // Multiple Tests
		LinkedListMegaBlock block = new LinkedListMegaBlock();
		block.addRed(new MegaBlock(Color.RED, 'd')); // Populating the LinkedListMegaBlock object
		if (block.get(0).getColor() == Color.RED) { // Tests whether the head contains the Color.Red object
			count++;
		}
		if (block.get(0) != null) { // Also checks if the head is null
			count++;
		}
		if (count == 2) { // Passes if both test pass
			return true;
		}
		return false;

	}

	// checks for the correctness of the LinkedListMegaBlock.removeBlue() method
	/**
	 * @return A boolean value which represents a Pass or Fail on the test method
	 */
	public static boolean testLinkedListMegaBlockRemoveBlue() {
	  System.out.println("**TESTING LinkedListMegaBlock removeBlue**");
		LinkedListMegaBlock block = new LinkedListMegaBlock();
		block.addRed(new MegaBlock(Color.RED, 'a')); // Populating the LinkedListMegaBlock
		block.addRed(new MegaBlock(Color.RED, 'b'));
		block.addRed(new MegaBlock(Color.RED, 'c'));
		block.addRed(new MegaBlock(Color.RED, 'd'));
		block.addYellow(4, new MegaBlock(Color.YELLOW, 'e'));
		block.addYellow(5, new MegaBlock(Color.YELLOW, 'f'));
		MegaBlock secondToLast = new MegaBlock(Color.BLUE, 'g'); // used within the test
		block.addBlue(secondToLast);
		block.addBlue(new MegaBlock(Color.BLUE, 'h'));
		block.removeBlue(); // removes the tail
		if (block.get(block.size() - 1).equals(secondToLast)) { // Tests if the tail was successfully removed, and a
																// shift within the LinkedListMegaBlock object
			return true;
		}
		return false;

	}
	public static boolean testLinkedListMegaBlock() {
	  System.out.println("**TESTING LinkedListMegaBlock Class");
	  int update = 0;
	  LinkedListMegaBlock list = new LinkedListMegaBlock();
      list.addYellow(0, new MegaBlock(Color.YELLOW, 'A'));
      list.addRed(new MegaBlock(Color.RED, 'B'));
      list.addYellow(list.size(), new MegaBlock(Color.YELLOW, 'C'));
      list.addYellow(2, new MegaBlock(Color.YELLOW, 'D'));
      list.addBlue(new MegaBlock(Color.BLUE, 'E'));
      System.out.println(list.toString());
      if (!list.get(0).equals(new MegaBlock(Color.RED, 'B'))) {
        System.out.println("inx 0");
        update = 0;
      }
      if (!list.get(1).equals(new MegaBlock(Color.YELLOW, 'A'))) {
        System.out.println("inx 1");
        update = 0;
      }
      else if (!list.get(2).equals(new MegaBlock(Color.YELLOW, 'D'))) {
        System.out.println("inx 2");
        update = 0;
      }
      else if (!list.get(3).equals(new MegaBlock(Color.YELLOW, 'C'))) {
        System.out.println("inx 3");
        update = 0;
      }
      else if (!list.get(4).equals(new MegaBlock(Color.BLUE, 'E'))) {
        System.out.println("inx 4");
        update = 0;
      }
      else {
        update = 1;
      }
      
      if (update == 1) {
        return true;
      }
      return false;
      
    
	}
	
	
} //end MegaBlockBuilderTester Class
