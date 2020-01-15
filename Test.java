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
    
    for (int i = 1; i < size1 - 1; i++)
    {    
