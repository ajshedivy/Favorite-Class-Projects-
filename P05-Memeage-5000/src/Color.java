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


public class Color extends FourBytes {
  
  /**
   * call Fourbytes constructor to initialize value of a color 
   * as a 4 byte array
   * @param argb
   */
  public Color(int argb) {
    super(argb);
    
  }
  
  /**
   * create color object with specific values
   * use FourBytes.SetBits and GetBytes to initialize color
   *
   * @param alpha int range 0-255
   * @param red int range 0-255
   * @param green int range 0-255
   * @param blue int range 0-255
   */
  public Color(int alpha, int red, int green, int blue) {
    super(alpha + red + green + blue);
    setAlpha(alpha);
    setRed(red);
    setGreen(green);
    setBlue(blue);
  }
  
  public Color(Color other) {
    super(other.getInt());
  }
  
  public int getARGB() {
    return super.getInt();
    
  }
  
  public int getAlpha() {
    int alpha = super.getBits(8, 24);
    return alpha;
  }
  
  public int getRed() {
    int red = super.getBits(8, 16);
    return red;
  }
  
  public int getGreen() {
    int green = super.getBits(8, 8);
    return green;
  }
  
  public int getBlue() {
    int blue = super.getBits(8, 0);
    return blue;
  }
  
  public void setARGB(int argb) {
    super.setInt(argb);
    
  }
  
  public void setAlpha(int alpha) {
    super.setBits(8, 24, alpha);
  }
  
  public void setRed(int red) {
    super.setBits(8, 16, red);
  }
  
  public void setGreen(int green) {
    super.setBits(8, 8, green);
  }
  
  public void setBlue(int blue) {
    super.setBits(8, 0, blue);
  }
  
}
