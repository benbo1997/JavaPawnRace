public class BoardTests{
  public BoardTests(){
    constructorTests();
  }

  private static void constructorTests(){
    testConstructor('F', 'A');
    testConstructor('B', 'H');
    testConstructor('C', 'C');
  }

  private static void testConstructor(char whiteGap, char blackGap){
    System.out.println("Does this look right for whiteGap: " + whiteGap +
                       " and blackGap: " + blackGap + "?");
    Board board = new Board(whiteGap, blackGap);
    board.display();
  }
}
