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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Implementation of a BST consisting of campers for Camp Badger
 * 
 * @author ajshe
 *
 */
public class CamperBST {

  public CampTreeNode root;
  private int size;
  // LinkedList to maintain current traversal
  private LinkedList<Camper> traversedLList;

  public CamperBST() {
    this.size = 0;
    this.root = null;
  }


  public int size() {// returns the current size of the CamperBST
    return size;
  }

  public boolean isEmpty() { // returns true if the tree is empty, false otherwise
    return root == null;
  }

  /**
   * helper method for contains method. searches the BST recursively for specified camper
   * 
   * @param item - camper to search for
   * @param node - current "root" node of the subtree we are searching
   * @return - true if camper found, else false
   * @throws NoSuchElementException - if the camper we are searching for is null
   */
  private static boolean containsHelper(Camper item, CampTreeNode node)
      throws NoSuchElementException {
    if (item == null) {
      throw new NoSuchElementException();
    }
    // Recursive algorithm
    if (node == null) // reach a leaf or binary search tree empty
      return false; // unsuccessful search
    if (item.compareTo(node.getData()) == 0)
      return true; // successful search
    if (item.compareTo(node.getData()) < 0) {
      return containsHelper(item, node.getLeftNode()); // recur on the left child (left sub-tree)
    }
    return containsHelper(item, node.getRightNode()); // recur on the right child (right sub-tree)
  } // end helper method

  /**
   * Searches BST for camper
   * 
   * @param camper - camper to search for
   * @return - true if found, else false
   */
  private boolean contains(Camper camper) {
    try {
      if (containsHelper(camper, root) == false) {
        throw new NoSuchElementException();
      }
    } catch (NoSuchElementException e) {
      throw e;
    }
    return containsHelper(camper, root);
  } // end method

  // starts tree insertion by calling insertHelp() on the root and
  // assigning root to be the subtree returned by that method
  public void insert(Camper newCamper) {
    root = insertHelp(root, newCamper);
  }

  /**
   * Recursive helper method to insert.
   * 
   * @param current,   The "root" of the subtree we are inserting into, ie the node we are currently
   *                   at.
   * @param newCamper, the camper to be inserted into the tree
   * @return the root of the modified subtree we inserted into
   */
  private CampTreeNode insertHelp(CampTreeNode current, Camper newCamper) {
    if (current == null) { // base case
      current = new CampTreeNode();
      current.setData(newCamper);
      size++;
      return current;
    }
    if (newCamper.compareTo(current.getData()) < 0) {
      // add to left subtree
      if (current.getLeftNode() == null) {
        // add camper to the left
        CampTreeNode node = new CampTreeNode();
        node.setData(newCamper);
        current.setLeftNode(node);
        size++;
        return current;
      } else {
        // recur on existing left node
        insertHelp(current.getLeftNode(), newCamper);
      }
    } else { // (newCamper.compareTo(current.getData()) > 0)
      // add to right subtree
      if (current.getRightNode() == null) {
        // set new camper to right child
        CampTreeNode node = new CampTreeNode();
        node.setData(newCamper);
        current.setRightNode(node);
        size++;
        return current;
      } else {
        insertHelp(current.getRightNode(), newCamper);
      }
    }
    return current;

  } // end helper method

  /**
   * Deletes a Camper into the binary search tree if it exists.
   * 
   * @param key, the camper to be deleted from the tree
   * @throws NoSuchElementException if it is thrown by deleteHelp
   */
  public void delete(Camper key) throws NoSuchElementException {
    try {
      if (contains(key) == false) {
        throw new NoSuchElementException("camper does not exist in system");
      }
    } catch (NullPointerException e) {
      throw e;
    }
    root = deleteHelp(root, key);
  }

  /**
   * Recursive helper method to delete.
   * 
   * @param current, The "root" of the subtree we are deleting from, ie the node we are currently
   *                 at.
   * @param key,     the camper to be deleted from the tree
   * @return the root of the modified subtree we deleted from
   * @throws NoSuchElementException if the camper is not in the tree
   */
  private CampTreeNode deleteHelp(CampTreeNode current, Camper key) throws NoSuchElementException {
    if (contains(key) == false) { // check if the camper to be removed exists
      throw new NoSuchElementException();
    }
    if (current == null) { // base case
      return current;
    }

    // if the key to be removed is smaller than current camper,
    // then the key is housed in left subtree
    if (key.compareTo(current.getData()) < 0) {
      current.setLeftNode(deleteHelp(current.getLeftNode(), key));

      // If the key to be removed is greater than the current camper,
      // then the key is housed in right subtree
    } else if (key.compareTo(current.getData()) > 0) {
      current.setRightNode(deleteHelp(current.getRightNode(), key));

      // if the key is he same as the current camper, then we need to delete the current node
    } else {

      // current has only one child or no child
      if (current.getLeftNode() == null) {
        CampTreeNode temp = current.getRightNode();
        size--;
        return temp;
      } else if (current.getRightNode() == null) {
        CampTreeNode temp = current.getLeftNode();
        size--;
        return temp;
      }

      // if current camper has two children, we need to find inorder successor, or
      // smallest camper in right subtree
      CampTreeNode min = minValue(current.getRightNode()); // call helper method

      // set the data of current to the inorder successors data
      current.setData(min.getData());

      // delete the inorder successor from tree
      current.setRightNode(deleteHelp(current.getRightNode(), min.getData()));
    }
    return current;
  } // end method

  /**
   * given camper in the BST, find the smallest (minimum value) camper of that subtree
   * 
   * @param current - non-empty CampTreeNode is BST
   * @return - smallest CampTreeNode in tree
   */
  private CampTreeNode minValue(CampTreeNode current) {
    CampTreeNode camper = current;
    while (current.getLeftNode() != null) { // search tree
      camper = current.getLeftNode(); // left node will have the smallest value
      current = current.getRightNode();
    }
    return camper;

  } // end method


  // returns an iterator of camper in the correct order as designated
  public Iterator<Camper> traverse(String order) {
    // first time traversing need to initialize LinkedList
    if (traversedLList == null) {
      traversedLList = new LinkedList<Camper>();
    } else {
      // clear the list to start over for a new traversal
      traversedLList.clear();
    }
    traverseHelp(root, order);
    return traversedLList.listIterator();
  }

  /**
   * Recursive helper method to traverse. Will take the current CampTreeNodes data and add it to
   * traversedLList based on the given order. Then continue to recurse on the correct subtree.
   * 
   * @param current, the root of the current subtree we are traversing
   * @param order,   the type of traversal to perform
   */
  private void traverseHelp(CampTreeNode current, String order) {
    if (order.equals("PREORDER")) {
      if (current == null)
        return;

      /* first print data of node */
      traversedLList.add(current.getData());

      /* then recur on left sutree */
      traverseHelp(current.getLeftNode(), order);

      /* now recur on right subtree */
      traverseHelp(current.getRightNode(), order);
    }

    if (order.equals("POSTORDER")) {
      if (current == null)
        return;

      // first recur on left subtree
      traverseHelp(current.getLeftNode(), order);

      // then recur on right subtree
      traverseHelp(current.getRightNode(), order);

      // now deal with the node
      traversedLList.add(current.getData());
    }
    if (order.equals("INORDER")) {
      if (current == null)
        return;

      /* first recur on left child */
      traverseHelp(current.getLeftNode(), order);

      /* then print the data of node */
      traversedLList.add(current.getData());

      /* now recur on right child */
      traverseHelp(current.getRightNode(), order);
    }

  } // end helper method



  // Prints the contents of this tree in alphabetical order
  // based on the string "lastName, firstName"
  public void print() {
    printHelp(root);
  }

  private void printHelp(CampTreeNode current) {
    if (current == null) {
      return;
    }
    printHelp(current.getLeftNode());
    System.out.println(current.getData());
    printHelp(current.getRightNode());
  }



} // end CamperBST class
