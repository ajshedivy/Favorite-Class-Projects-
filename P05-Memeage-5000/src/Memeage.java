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


public class Memeage extends Image {
  
  public Memeage(File file) throws IOException {
    super(file);
  }
  public Memeage(File file, String meme) throws IOException,
  IllegalArgumentException {
    super(file);
    setMeme(meme);
  }
  /**
   * Hides message in given file
   * @param meme
   * @throws IllegalArgumentException
   */
  public void setMeme(String meme) throws IllegalArgumentException {
    int counter = 0;
    int height = super.getHeight();
    int width = super.getWidth();
    try {
      /* search for invalid input
       * if character value > 127, exception thrown
       */
      for (int i = 0; i < meme.length(); i++) { 
        if (meme.charAt(i) > 127 ) {
          throw new IllegalArgumentException("invalid character");
        }
      }
      if (meme.length() >= height * width) {
        throw new IllegalArgumentException("meme too long");
      }
      
      //main iteration, hides character within each color located in pixel
      mainIteration: for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          Color color = getColor(i, j);
          if (counter == meme.length()) {
            setColor(i, j, new ColorPlusChar(color, '\0'));  
            break mainIteration;
          }
          setColor(i, j, new ColorPlusChar(color, meme.charAt(counter)));
          
          counter++;
        }
      }
    }
    catch (IllegalArgumentException e) {
      System.out.println("invalid input: character has a bit representation "
          + "greater than 127 or the meme is too long");
      throw e;
    }
    }
  
  
  /**
   * extracts the hidden bits from individual pixel values
   * reconstructs meme as String
   * @return String meme
   * @throws IllegalStateException
   */
  public String getMeme() throws IllegalStateException {
    String meme = "";
    int height = this.getHeight();
    int width = this.getWidth();
    
    try { 
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
        ColorPlusChar charColor = new ColorPlusChar(getColor(i, j));
        
        char ch = charColor.revealChar();
        if (ch >127) {
          throw new IllegalStateException("char value > 127");
        }
        if (ch == '\0') {
          return meme;
        }
        
        meme += ch;
        }
    }
    throw new IllegalStateException("no null character");
  }
    catch (Exception e) {
      System.out.println("State invalid: no null character, or "
          + "char value is greater than 127 ");
      throw e;
    }
  }
}
