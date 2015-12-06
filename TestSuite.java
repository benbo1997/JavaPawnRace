public class TestSuite{
  public static void main(String[] args){
    System.out.println("Running tests...");

    System.out.println("\nSquareTests:");
    SquareTests squareTests = new SquareTests();

    System.out.println("\nMoveTests:");
    MoveTests moveTests = new MoveTests(); 

    System.out.println("\nBoardTests:");
    BoardTests boardTests = new BoardTests();

    System.out.println("\n\t     ...done");
  }
}
