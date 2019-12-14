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
 * 
 *         The Linked MegaBlock class creates a LinkedMegaBlock object. Within
 *         the constructor, it passes through a MegaBlock and an additional
 *         LinkedMegaBlock next which points to the next MegaBlock in the
 *         LinkedMegaBlock object. This class contains 5 methods, 2 accessor
 *         (getBlock(), getNext()) and 2 mutators (setBlock(), setNext()) and a
 *         Override toString method.
 */
public class LinkedMegaBlock {

	private MegaBlock block; // data field of this linkedMegaBlock
	private LinkedMegaBlock next; // link to the next linkedMegaBlock

	/**
	 * @param block - The block the LinkedMegaBlock will initialize to.
	 */
	LinkedMegaBlock(MegaBlock block) {
		this.block = block; // sets the parameter as the instance field
		this.next = null; // initializes next as null
	}

	/**
	 * @param block - The block the LinkedMegaBlock will initialize to.
	 * @param next  - Points to the next MegaBlock within the LinkedMegaBlock
	 *              object.
	 */
	LinkedMegaBlock(MegaBlock block, LinkedMegaBlock next) {
		this.block = block;
		this.next = next;
	}

	/**
	 * @return - The Block within the LinkedMegaBlock object.
	 */
	public MegaBlock getBlock() {
		return this.block;
	}

	/**
	 * @param block - A Block which will be set as the instance field MegaBlock
	 *              variable
	 */
	public void setBlock(MegaBlock block) {
		this.block = block;
	}

	/**
	 * @return - The reference to the next LinkedMegaBlock.
	 */
	public LinkedMegaBlock getNext() {
		return this.next;
	}

	/**
	 * @param next - Sets the next reference to the Next LinkedMegaBlock.
	 */
	public void setNext(LinkedMegaBlock next) {
		this.next = next;
	}

	/**
	 * Overrides the Java.Object toString method, when called this method will output the
	 * characteristics about the block and a chain to the next block. 
	 */
	@Override
	public String toString() {
		if(block == null) {
			return "";
		}
	    if(next == null) {
	    	return block.toString() + " -> END";
	    }
	    else {
	    	return block.toString() + " -> ";
	    }
	}

} //end LinkedMegaBlock Class
