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
import java.io.File;
import java.io.IOException;

public class MemeageTests {

  public static void main(String[] args) throws IOException {
    
    testDriver();    
  }
  public static void testDriver() throws IOException {
    System.out.println("**TESTING P05**");
    System.out.println(testFourBytesSetBits());
    System.out.println(testFourBytesGetBits());
    System.out.println(testColor());
    System.out.println(testColorPlusChar());
    System.out.println(testImage());
    System.out.println(testMeamage());
  }
  
  public static boolean testFourBytesSetBits() {
    int output = 0;
    System.out.println("**testing SetBits**");
    FourBytes bitString = new FourBytes(0);
    bitString.setBits(3, 8, 13);
    
    if (bitString.getInt() == 1280) {
      System.out.println("value: " +bitString.getInt());
      output = 1;
    }
    if (bitString.getInt() == 1234) {
      output = 0;
    }
    if (output == 1) {
      return true;
    }
    else {
      return false;
    }
  }
  
  
  public static boolean testFourBytesGetBits() {
    int output = 0;
    System.out.println("**testing GetBits**");
    FourBytes bitString = new FourBytes(13312);
    //System.out.println(bitString.getInt());
    System.out.println(bitString.getBits(0, 10));
    
    if (bitString.getBits(4, 10) == 13) {
      System.out.println("return value: " + bitString.getBits(4, 10));
      output = 1;
    }
    if (bitString.getBits(32, 33) ==3) {
      output = 0;
    }
    if (output == 1) {
      return true;
    }
    else {
      return false;
    }
   
  }
  
  public static boolean testColor() {
    System.out.println("**testing Color Class**");
    Color myColor = new Color(123);
    if (myColor.getInt() != 123) {
      return false;
    }
    myColor.setBlue(174);
    myColor.setGreen(23);
    System.out.println("value: " + myColor.toString());
    if (myColor.getBlue() != 174) {
      return false;
    }
    if (myColor.getGreen() != 23) {
      System.out.println("return value of blue: " + myColor.getBlue());
      return false;
    }
    
    return true;
    
  }
  
  public static boolean testColorPlusChar() {
    System.out.println("**testing ColorPlusChar**");
    char ch = new Character('a');
    Color colorful = new Color(0);
    ColorPlusChar color = new ColorPlusChar(colorful);
    System.out.println("before: " + color.toString());  //should be 0
    color.hideChar(ch);
    if (!color.toString().equals("16908289 = 1000000100000000000000001")) {
      return false;
    }
    System.out.println("after: " + color.toString());
    if (color.revealChar() != 'a') {
      return false;
    }
    System.out.println("value of hidden character: " + color.revealChar());
    
    
    return true;
  }
  
  public static boolean testImage() throws IOException {
    System.out.println("**Testing Image Class**");
    File imageFile =  new File("testImage.png");
    
    Image image = new Image(imageFile);
    
    /*Test Height and Width
     *Width = 3
     *Height = 3
     **/
    if (image.getHeight() != 3) {
      System.out.println("height value: " + image.getHeight());
      return false;
    }
    if (image.getWidth() != 3) {
      System.out.println("Width value: " + image.getWidth());
      return false;
    }
    /*Test Color stored at x and y position of image
     * Blue = 255
     * Green = 255 
     * Red = 0
     */
    if (image.getColor(1, 1).getBlue() != 255) {
      System.out.println("value of Blue: " + image.getColor(1, 1).getBlue());
      return false;
    }
    if (image.getColor(1, 1).getGreen() != 255) {
      System.out.println("value of Green: " + image.getColor(1, 1).getGreen());
    }
    if (image.getColor(1, 1).getRed() != 0) {
      System.out.println("value of red: " + image.getColor(1, 1).getRed());
    }
    System.out.println(image.getColor(1, 1));
    
    return true;
    
  }
  
  public static boolean testMeamage() throws IOException {
    int output = 0;
    File imageFile =  new File("image02.png");
    File badImage =  new File("testImage.png");
    File getMemeFile = new File("image03.png");
    Memeage memeage = new Memeage(imageFile);   
    Memeage memeage2 = new Memeage(badImage);  
    Memeage memeage3 = new Memeage(imageFile); 
    Memeage memeage4 = new Memeage(getMemeFile);
    System.out.println("start");
    try {  //test setMeme
      memeage3.setMeme("ň");
      output = 0;
    }
    catch (IllegalArgumentException e) {
      System.out.println("caught in test, illegal character");
      output = 1;
    }

    try {  //test getMeme
      memeage4.getMeme(); 
      output = 0;
      }
    
    catch (IllegalStateException e) {
      System.out.println("caught in test2, no hidden message");
      output = 1;
    }
    try {
      memeage2.setMeme("asdfretss");
      output = 0;
    }
    catch (IllegalArgumentException e) {
      System.out.println("caught in test 3, meme too long");
      output = 1;
    }
    memeage.setMeme("adam");
    System.out.println(memeage.getMeme());
    if (memeage.getMeme().equals("adam")) {
      output = 1;
    }
    else {
      output = 0;
    }
    if (output == 1) {
      return true;
    }
    else {
      return false;
    }

  }
  
  
}