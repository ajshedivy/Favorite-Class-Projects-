//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           P05 Memeage 5000
// Files:           ColorPlusChar, Memeage, MemeageTests, Color
// Course:          CS 300
//
// Author:          Adam Shedivy
// Email:           ajshedivy @ wisc
// Lecturer's Name: Dahl
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (name of your pair programming partner)
// Partner Email:   (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
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

public class ColorPlusChar extends Color {
  
  public ColorPlusChar(Color color, char character) {
    super(color);
    hideChar(character);
    
    
    
  }
  public ColorPlusChar(Color color) {
    super(color);
  } 
  
//stores 8-bit character within the least significant bits of color components
  public void hideChar(char character) {
    Color color = new Color(character);
    
    int one = color.getBits(2, 6);
    int two = color.getBits(2, 4);
    int three = color.getBits(2, 2);
    int four = color.getBits(2, 0);
       
    super.setBits(2, 24, one);
    super.setBits(2, 16, two);
    super.setBits(2, 8, three);
    super.setBits(2, 0, four);
    
  }
//retrieves 8-bit character from the least significant bits of color components
  public char revealChar() {
    Color color = new Color(0); //create new Color object to abstract char value
    
    /*get bits from 4 byte object */
    int one = super.getBits(2, 24);
    int two = super.getBits(2, 16);
    int three = super.getBits(2, 8);
    int four = super.getBits(2, 0);

    /*set bits in a color object that we will use to abstract value of char*/
    color.setBits(2, 6, one);
    color.setBits(2, 4, two);
    color.setBits(2, 2, three);
    color.setBits(2, 0, four);
    
    return color.getChar();
  }

}

