package resources;
import java.util.Scanner;

public class Player
{
  // private int piecesCaptured;
  private boolean passedLastTurn;
  private int color;
  private Board playingField;
  private Scanner scan;

  // constructs one player of a color
  public Player(int theColor, Board thePlayingField)
  {
    color = theColor;
    passedLastTurn = false;
    // piecesCaptured = 0;
    playingField = thePlayingField;
    scan = new Scanner(System.in);
  }

  // lets a player take a takeTurn
  // @param nothing
  // @return nothing
  // functionality:
  // - self updates passedLastTurn
  // - takes player input and performs actions accordingly
  // - is robust, and takes care of bad inputs

  // bread and butter method
  public void takeTurn()
  {
    // take input
    System.out.println("Enter P to pass, or a location to play a piece. (letter-number)");
    String playerResponse = scan.nextLine();

    // check for if playerResponse is a valid one
    while(!isValidResponse(playerResponse))
    {
      System.out.println("That is not a valid response!");
      playerResponse = scan.nextLine();
    }

    if(playerResponse.equals("P") || playerResponse.equals("p"))
    {
      passedLastTurn = true;
      return;
    }
    // code for actually playing the piece
    int y = responseToY(playerResponse);
    int x = responseToX(playerResponse);

    while(playingField.getBoard()[y][x] != 0)
    {
      System.out.println("You have to play on an empty spot.");
      playerResponse = scan.nextLine();
      // just in case player is ACTUALLY messing with me
      while(!isValidResponse(playerResponse))
      {
        System.out.println("That is not a valid response!");
        playerResponse = scan.nextLine();
      }
      y = responseToY(playerResponse);
      x = responseToX(playerResponse);
    }
    
    // placePiece actually returns a boolean so this works
    while(!playingField.placePiece(y, x, color))
    {
      System.out.println("You can't play there!");
      playerResponse = scan.nextLine();
      y = responseToY(playerResponse);
      x = responseToX(playerResponse);
    }

    // if they didn't pass, they didn't pass
    passedLastTurn = false;
  }

  // FOR BELOW TWO METHODS
  // turns a player response into board coordinates
  // @param response a VALID player response
  // @return the int y or x coordinate of the response

  // see notes for explanation of below methods
  public int responseToY(String response)
  {
    String toParse = response.substring(1);
    // - 1 because of 0 indexing
    return Integer.parseInt(toParse) - 1;
  }

  public int responseToX(String response)
  {
    char toConvert = response.charAt(0);
    // -65 instead of 64 because of 0 indexing
    return (int)toConvert - 65;
  }

  // checks if a player response is a valid one
  // @param theReponse ...
  // @return true if it is, false if it isn't
  // *a response is valid if it is a pass, or a valid
  // coordinate (is an actual location on the board)*

  // robustness checking method
  public boolean isValidResponse(String theResponse)
  {
    if(theResponse.equals("P") || theResponse.equals("p"))
    {
      return true;
    }
    
    if(theResponse.length() >= 2)
    {
      int y = 0;

      try
      {
        y = responseToY(theResponse);
      }
      catch(Exception NumberFormatException)
      {
        return false;
      }

      int x = responseToX(theResponse);

      if(y >= 0 && y < playingField.getSize() && x >= 0 && x < playingField.getSize())
      {
        return true;
      }
    }
    return false;
  }

  // getter method
  public boolean getPassedLastTurn()
  {
    return passedLastTurn;
  }
}
