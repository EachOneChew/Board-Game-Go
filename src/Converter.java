package src;

public class Converter
{
  // a converter takes coordinates and converts it to an int representation
  // it can also take an int representation and extract x or y coordinates
  // turns boards into strings & vice versa
  public Converter()
  {
    // doesn't need constructor, this is just methods
  }

  // converts a coordinate to a number
  // @param y ...
  // @param x ...
  // @param boardSize the size of the board (no really??)
  // @return the int that the coordinates have been converted to

  // see notes for method explanations
  public int convertToNumber(int y, int x, int boardSize)
  {
    return y * boardSize + x;
  }


  // FOR BELOW TWO METHODS
  // converts a number to its representative coordinate
  // @param number the number
  // @boardSize ...
  // @return the x or y coordinate represented by the number

  public int convertToX(int number, int boardSize)
  {
    return number % boardSize;
  }

  public int convertToY(int number, int boardSize)
  {
    return number / boardSize;
  }

  // updates a board according to a string board state
  // @param state the state of the board, as a string
  // @board the board to modify
  // @return nothing, but the board is changed according to state

  public void stringToBoard(String state, int[][] board)
  {
    int count = 0;
    for(int i = 0; i < board.length; i++)
    {
      for(int j = 0; j < board[i].length; j++)
      {
        // char ASCII conversion business going on here
        board[i][j] = state.charAt(count) - 48;
        count++;
      }
    }
  }

  // turns a board state into a string
  // @param board ...
  // @return the String that represents the board state

  public String boardToString(int[][] board)
  {
    String output = "";
    for(int[] arr: board)
    {
      for(int n: arr)
      {
        output += n;
      }
    }
    return output;
  }
}