iimport java.util.Arrays;
import java.util.Scanner;
// summary of code functions:
// - detects and removes any groups of captured pieces, regardless
// of group size or catalyst location
// - prevents players from making moves that can cause both players
// to enter an infinite loop fighting for the same location
// - prevents players from making moves that cause a group of their
// own pieces to be captured on the same turn (accounts for if a
// move will capture a group of enemy pieces before it suicides)
// - counter markers at edges of board adapt to various board sizes
// ft. extremely abusive text interactions
class Main
{
  public static void main(String[] args)
  {
    // scanner is useful
    Scanner usefulBoi = new Scanner(System.in);

    System.out.println("YOU WANNA PLAY GO? ENTER A BOARD SIZE! (9, 13, or 19)");
    String boardSize = usefulBoi.nextLine();

    // robustness check
    while(!(boardSize.equals("9") || boardSize.equals("13") || boardSize.equals("19")))
    {
      System.out.println("ENTER A VALID BOARD SIZE YOU IDIOT!");
      boardSize = usefulBoi.nextLine();
    }

    // parse the String to an int
    int intSize = Integer.parseInt(boardSize);
    char[] counter = new char[intSize];

    // create horizontal markers
    for(int i = 0; i < intSize; i++)
    {
      counter[i] = (char)(i + 65);
    }

    // initialize board and players
    Board theBoard = new Board(intSize);

    Player black = new Player(1, theBoard);
    Player white = new Player(2, theBoard);

    printTheBoard(counter, theBoard);
    
    // the loop for the game itself
    while(true)
    {
      System.out.println("BLACK TURN!!!");
      black.takeTurn();
      printTheBoard(counter, theBoard);

      if(isGameOver(black, white))
      {
        break;
      }
      
      System.out.println("WHITE TURN!!!");
      white.takeTurn();
      printTheBoard(counter, theBoard);

      if(isGameOver(black, white))
      {
        System.out.println();
        break;
      }
    }
    // tallies scores
    tallyScores();
  }

  // used to check if game is over
  // @param one the first player
  // @param two the second player
  // @return true if both players have passed their last turn,
  // and false otherwise

  // checks if both players passed last turn, needs to appear
  // twice in each iteration of  the playerOne-playerTwo loop
  public static boolean isGameOver(Player one, Player two)
  {
    if(one.getPassedLastTurn() && two.getPassedLastTurn())
    {
      System.out.println("WE DONE!!!");
      return true;
    }
    return false;
  }

  // prints the board with a counter
  // @param horizontalMarker the marker to print with the board
  // @param toPrint the board to print
  // @return nothing, just prints the board with a counter

  // print the board, with counters
  public static void printTheBoard(char[] horizontalMarker, Board toPrint)
  {
    // so board is not mismatched by size
    boolean isBigBoard = false;

    if(toPrint.getSize() > 9)
    {
      isBigBoard = true;
    }

    System.out.println();
    
    // extra space if board is big to prevent mismatch
    if(isBigBoard)
    {
      System.out.println("   " + Arrays.toString(horizontalMarker));
    }
    else
    {
      System.out.println("  " + Arrays.toString(horizontalMarker));
    }

    for(int i = 0; i < toPrint.getSize(); i++)
    {
      // sticks 0 in front of single digit to prevent mismatch
      if(i < 9 && isBigBoard)
      {
        System.out.print("0" + (i + 1) + " ");
      }
      else
      {
        System.out.print((i + 1) + " ");
      }
      System.out.println(Arrays.toString(toPrint.getBoard()[i]));
    }

    System.out.println();
  }

  // takes scores (player must count them) and prints winner
  // @param nothing, player input gives everything needed
  // @return nothing, only output is in the form of prints

  // tallies the scores (duh)
  // should be pretty self explanatory
  // includes system taking care of any bad inputs
  public static void tallyScores()
  {
    Scanner scan = new Scanner(System.in);
    
    System.out.println("ENTER BLACK SCORE!");
    String blackScore = scan.nextLine();
    while(!isValidScore(blackScore))
    {
      System.out.println("ENTER A VALID SCORE YOU IDIOT!!!");
      blackScore = scan.nextLine();
    }
    int intBlackScore = Integer.parseInt(blackScore);

    System.out.println("ENTER WHITE SCORE!");
    String whiteScore = scan.nextLine();
    while(!isValidScore(whiteScore))
    {
      System.out.println("ENTER A VALID SCORE YOU IDIOT!!!");
      whiteScore = scan.nextLine();
    }
    int intWhiteScore = Integer.parseInt(whiteScore);

    if(intWhiteScore + 5 > intBlackScore)
    {
      System.out.println("WHITE WINS!!!");
    }
    else if(intWhiteScore + 5 < intBlackScore)
    {
      System.out.println("BLACK WINS!!!");
    }
    else
    {
      System.out.println("TIE!!!");
    }
  }

  // really don't need JavaDoc here, do I?

  // checks if score entered is a valid number
  public static boolean isValidScore(String input)
  {
    try
    {
      Integer.parseInt(input);
    }
    catch(Exception numberFormatException)
    {
      return false;
    }
    return true;
  }
}
