//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Matching Game
// Files: MatchingGame.java
// Course: CS300, Fall, 2019
//
// Author: Samuel Bahr
// Email: sdbahr@wisc.edu
// Lecturer's Name: Gary
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Adam Shedivy
// Partner Email: ajshedivy@wisc.edu
// Partner Lecturer's Name: Gary
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// X Write-up states that pair programming is allowed for this assignment.
// X We have both read and understand the course Pair Programming Policy.
// X We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: N/A
// Online Sources: Piazza
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.io.File;
import java.util.Random;
import processing.core.PApplet;
import processing.core.PImage;

public class MatchingGame {

  private final static String CONGRA_MSG = "CONGRATULATIONS! YOU WON!";
  private final static String NOT_MATCHED = "CARDS NOT MATCHED. Try again!";
  private final static String MATCHED = "CARDS MATCHED! Good Job!";
  private final static float[][] CARDS_COORDINATES =
      new float[][] {{170, 170}, {324, 170}, {478, 170}, {632, 170}, {170, 324}, {324, 324},
          {478, 324}, {632, 324}, {170, 478}, {324, 478}, {478, 478}, {632, 478}};
  private final static String[] CARD_IMAGES_NAMES = new String[] {"apple.png", "ball.png",
      "peach.png", "redFlower.png", "shark.png", "yellowFlower.png"};
  private static PApplet processing; // PApplet object that represents
  // the graphic display window
  private static Card[] cards; // one dimensional array of cards
  private static PImage[] images; // array of images of the different cards
  private static Random randGen; // generator of random numbers
  private static Card selectedCard1; // First selected card
  private static Card selectedCard2; // Second selected card
  private static boolean winner; // boolean evaluated true if the game is won,
  // and false otherwise
  private static int matchedCardsCount; // number of cards matched so far
  // in one session of the game
  private static String message; // Displayed message to the display window



  public static void main(String[] args) {
    Utility.runApplication(); // Runs the application

  }

  public static void setup(PApplet processing) {
    MatchingGame.processing = processing;


    images = new PImage[CARD_IMAGES_NAMES.length];
    for (int i = 0; i < images.length; i++) {

      images[i] = processing.loadImage("images" + File.separator + CARD_IMAGES_NAMES[i]);
    }
    // simple for statement that loads all of the pictures

    initGame();


  }

  public static void initGame() {

    randGen = new Random(Utility.getSeed());
    selectedCard1 = null;
    selectedCard2 = null;
    matchedCardsCount = 0;
    winner = false;
    message = "";
    // Initializes static fields
    int counter = 0; // used to count the iterations of the for loop
    int secondCounter = 0; // used to identify the index of the cards array
    int index = randGen.nextInt(12); // Generates a number that could potentially be used for the
                                     // index.
    int[] indexTracker = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11}; // Tracks the Index of
                                                                           // Cards

    cards = new Card[CARD_IMAGES_NAMES.length * 2];

    for (int i = 0; i < cards.length; i++) {
      if (counter == 6) {
        i = 0; // repeats the forloop twice to print out 12 cards instead of 6
      }
      if (counter == 12) { // when 12 cards have been initialize, breaks the forloop

        break;
      }
      while (indexTracker[index] == -1) { // checks to see if the index is unique / have been used
        index = randGen.nextInt(12); // Generates a unique index

      }
      cards[secondCounter] =
          new Card(images[i], CARDS_COORDINATES[index][0], CARDS_COORDINATES[index][1]);

      counter++;
      secondCounter++;
      indexTracker[index] = -1; // if an index has been used, the value at the index will equal -1.



    }


  }


  public static void draw() {
    processing.background(245, 255, 250);

    for (int i = 0; i < cards.length; i++) {
      cards[i].draw();
    }


    displayMessage(message);

  }

  public static void displayMessage(String message) {
    processing.fill(0);
    processing.textSize(20);
    processing.text(message, processing.width / 2, 50);
    processing.textSize(12);
  }

  public static void keyPressed() {

    if ((processing.key == 'n') || (processing.key == 'N')) {
      initGame(); // pressing n or N will restart the game
    }

  }

  /**
   * Checks whether the mouse is over a given Card
   * 
   * @return true if the mouse is over the storage list, false otherwise
   */
  public static boolean isMouseOver(Card card) {
    int x = (int) card.getX();
    int y = (int) card.getY();
    int xMax = x + 75; // 75 is half of the cards width
    int xMin = x - 75;
    int yMax = y + 75; // 75 is half of the cards height
    int yMin = y - 75;
    int x1 = processing.mouseX;
    int y1 = processing.mouseY;

    if ((x1 > xMin) && (x1 < xMax)) { // checks to see if the mouse is within the Card's boundaries.
      if ((y1 > yMin) && (y1 < yMax)) {
        return true;
      }
    }
    return false;
  }



  public static void mousePressed() {

    if ((selectedCard1 != null) && (selectedCard2 != null)) { // checks to see if the cards have
                                                              // been assigned
      if (message.equals(MATCHED)) {
        selectedCard1.deselect();
        selectedCard2.deselect();
        selectedCard1 = null; // Card is assigned a null value, for SelectedCard to be assigned
                              // again next round.
        selectedCard2 = null;
      } else {
        selectedCard1.deselect();
        selectedCard2.deselect();
        selectedCard1.setVisible(false);
        selectedCard2.setVisible(false);
        selectedCard1 = null;
        selectedCard2 = null;
      }
    }

    for (int i = 0; i < cards.length; i++) {

      if (isMouseOver(cards[i])) {

        if (selectedCard1 != null) { // checks to see if the SelectedCard1 is being used
          selectedCard2 = cards[i]; // Assigns SelectedCard2 with Card Value
          selectedCard2.select();
          selectedCard2.setVisible(true);

          if ((selectedCard1.getX() == selectedCard2.getX())
              && selectedCard1.getY() == selectedCard2.getY()) {
            // If user clicks on the same card twice, the round won't count.
            selectedCard1.deselect();
            selectedCard2.deselect();
            selectedCard1.setVisible(false);
            selectedCard2.setVisible(false);
            selectedCard1 = null;
            selectedCard2 = null;

          }


        } else {
          selectedCard1 = cards[i]; // Assigns SelectedCard1 with Card Value
          selectedCard1.select();
          selectedCard1.setVisible(true);

        }
      }
    }
    if ((selectedCard1 != null) && (selectedCard2 != null)) {

      if (matchingCards(selectedCard1, selectedCard2)) {
        matchedCardsCount++; // Increases if there's a match
        message = MATCHED;

      } else {
        message = NOT_MATCHED;


      }
      if (matchedCardsCount == 6) {
        message = CONGRA_MSG;
        winner = true;
      }


    }


  }


  /**
   * Checks whether two cards match or not
   * 
   * @param card1 reference to the first card
   * @param card2 reference to the second card
   * @return true if card1 and card2 image references are the same, false otherwise
   */
  public static boolean matchingCards(Card card1, Card card2) {
    PImage firstImage = card1.getImage();
    PImage secondImage = card2.getImage();

    if (firstImage.equals(secondImage)) { // compares the image rather than the card. More Precision
      return true;
    } else {
      return false;
    }
  }

}


