package resources;
import java.util.*;

public class Board
{
  private int[][] board;
  private String currentState;
  private String previousState;
  private String newState;
  private ArrayList<Integer> tempLocationStorage;
  private Converter convert;

  // creates a board of the specified size
  public Board(int size)
  {
    board = new int[size][size];
    previousState = "";
    currentState = "";
    newState = "";
    convert = new Converter();
    tempLocationStorage = new ArrayList<Integer>();
  }

  // places a piece and returns true if successful
  // @param y the intended y coordinate of the piece
  // @param x the intended x coordinate of the piece
  // @param color the color of the piece to place
  // @return true if piece is played, otherwise false
  // *this method un-does any invalid pieces placed*

  // places a piece (wow really) and checks if it is valid
  // returns true if successfully played and false otherwise
  // needs external check for whether spot is empty <- ***
  public boolean placePiece(int y, int x, int color)
  {
    int oppositeColor = 0;
    if(color == 1)
    {
      oppositeColor = 2;
    }
    if(color == 2)
    {
      oppositeColor = 1;
    }
    // board state before a move is made
    currentState = convert.boardToString(board);
    // placePiece() does checks for potential captures 
    board[y][x] = color;
    if(y + 1 < board.length && isCaptured(y + 1, x, oppositeColor))
    {
      removeCaptured();
    }
    if(y - 1 >= 0 && isCaptured(y - 1, x, oppositeColor))
    {
      removeCaptured();
    }
    if(x - 1 >= 0 && isCaptured(y, x - 1, oppositeColor))
    {
      removeCaptured();
    }
    if(x + 1 < board.length && isCaptured(y, x + 1, oppositeColor))
    {
      removeCaptured();
    }
    // updates current state
    newState = convert.boardToString(board);
    // restore board to previousState if move is not valid
    if(!isValidMove(y, x, color))
    {
      convert.stringToBoard(currentState, board);
      return false;
    }
    previousState = currentState;
    return true;
  }

  // checks if a move is valid
  // @param y duh
  // @param x duh
  // @param color duh
  // @return true if move is valid, and false if not
  // *prints out messages for rules violated*

  // checks if a move is valid
  public boolean isValidMove(int y, int x, int color)
  {
    if(newState.equals(previousState))
    {
      System.out.println("You have violated the KO rule. Wait until the next turn to play here.");
      return false;
    }
    // only needs to check if current location is captured
    // because playPiece would have already removed any
    // enemy piece this stone would capture
    if(isCaptured(y, x, color))
    {
      System.out.println("You cannot suicide!");
      return false;
    }
    return true;
  }

  // ... see below
  // @param I'm not doing this again
  // @return true if a group of pieces are captured, and
  // false otherwise (or if the color doesn't match up)
  // *updates the tempLocationStorage for removeCaptured*

  // checks if a location and group associated is captured
  // see notes for details
  public boolean isCaptured(int y, int x, int color)
  {
    // if the place isn't the right color then dont do it
    if(board[y][x] != color)
    {
      return false;
    }
    // create tracking ArrayLists
    ArrayList<Integer> scanned = new ArrayList<Integer>();
    ArrayList<Integer> toScan = new ArrayList<Integer>();
    // so loop has initial to work off of
    toScan.add(convert.convertToNumber(y, x, board.length));
    // if loops terminates and false has not been returned,
    // true should be returned
    while(toScan.size() > 0)
    {
      // only carry out if current location is unscanned
      if(scanned.indexOf(toScan.get(0)) == -1)
      {
        // get current location's coordinates
        int curLocation = toScan.get(0);
        int curY = convert.convertToY(curLocation, board.length);
        int curX = convert.convertToX(curLocation, board.length);
        // give surrounding area names for convenience
        int up = curY + 1;
        int down = curY - 1;
        int left = curX - 1;
        int right = curX + 1;

        // if any of surrounding is empty, return false
        // bunch of &&s is for cases of edge locations
        if(left >= 0 && board[curY][left] == 0 || right < board.length && board[curY][right] == 0 || up < board.length && board[up][curX] == 0 || down >= 0 && board[down][curX] == 0)
        {
          return false;
        }
        // the current cell does NOT have liberty
        // put any surrounding allied pieces into toScan
        if(left >= 0 && board[curY][left] == color)
        {
          toScan.add(convert.convertToNumber(curY, left, board.length));
        }
        if(right < board.length && board[curY][right] == color)
        {
          toScan.add(convert.convertToNumber(curY, right, board.length));
        }
        if(up < board.length && board[up][curX] == color)
        {
          toScan.add(convert.convertToNumber(up, curX, board.length));
        }
        if(down >= 0 && board[down][curX] == color)
        {
          toScan.add(convert.convertToNumber(down, curX, board.length));
        }
        scanned.add(convert.convertToNumber(curY, curX, board.length));
      }
      // if current location has been scanned, skip
      else
      {
        toScan.remove(0);
      }
    }
    // tempLocationStorage is for removeCaptured
    tempLocationStorage = scanned;
    // if no liberty, then captured
    return true;
  }

  // ...
  // @param none, tempLocationStorage is always there
  // @return none, only removes locations storage

  // takes all locations stored and empties it
  // only called when isCaptured completes its while loop,
  // meaning that a group of is captured
  public void removeCaptured()
  {
    for(int n: tempLocationStorage)
    {
      int y = convert.convertToY(n, board.length);
      int x = convert.convertToX(n, board.length);
      board[y][x] = 0;
    }
  }

  // NO JAVADOC FOR THE BELOW TWO

  // just a getter method
  public int[][] getBoard()
  {
    return board;
  }

  public int getSize()
  {
    return board.length;
  }
}
