import java.util.Arrays;

class Main
{
  public static void main(String[] args)
  {
    Board testBoard1 = new Board(9);
    Board testBoard2 = new Board(13);
    Board testBoard3 = new Board(19);
    
    int size1 = testBoard1.getSize();
    int size2 = testBoard2.getSize();
    int size3 = testBoard3.getSize();
    
    // place pieces in a large square around edges of board to test edge case
    for (int i = 1; i < size1 - 1; i++)
    {
      for (int j = 1; j < size1 - 1; j++)
      {
        assert testBoard1.placePiece(i, j, 1) : " cannot place";
      }
    }
    
    for (int i = 0; i < size1; i++)
    {
      assert testBoard1.placePiece(0, i, 2) : " cannot place";
    }
    
    for (int i = 0; i < size1; i++)
    {
      assert testBoard1.placePiece(size1 - 1, i, 2) : " cannot place";
    }
    
    for (int i = 1; i < size1 - 1; i++)
    {
      assert testBoard1.placePiece(i, 0, 2) : " cannot place";
      assert testBoard1.placePiece(i, size1 - 1, 2) : " cannot place";
    }
    
    // check that all pieces captured
    for (int i = 1; i < size1 - 1; i++)
    {
      for (int j = 1; j < size1 - 1; j++)
      {
        assert testBoard1.getBoard()[i][j] == 0 : " uncaptured piece";
      }
    }
  }
}
