public class BoardTests{
  public BoardTests(){
//    constructorTests();
    getSquareTests();
    applyMoveTests();
    unapplyMoveTests();
  }

  private static void constructorTests(){
    testConstructor('F', 'A');
    testConstructor('B', 'H');
    testConstructor('C', 'C');
  }

  private static void getSquareTests(){
    testGetSquare(new Board('A', 'B'), 0, 0, new Square(0, 0, Color.NONE));
    testGetSquare(new Board('A', 'B'), 1, 0, new Square(1, 0, Color.NONE));
    testGetSquare(new Board('A', 'B'), 1, 1, new Square(1, 1, Color.WHITE));
  }

  private static void applyMoveTests(){
    Board board1 = new Board('A', 'A');
    testApplyMove(board1,
                  new Move(board1.getSquare(1, 1), board1.getSquare(1, 3),
                           false, false),
                  new Square(1, 1, Color.NONE),
                  new Square(1, 3, Color.WHITE));
    testApplyMove(board1,
                  new Move(board1.getSquare(6, 6), board1.getSquare(6, 4),
                           false, false),
                  new Square(6, 6, Color.NONE),
                  new Square(6, 4, Color.BLACK));
  }

  private static void unapplyMoveTests(){
    Board board1 = new Board('A', 'A');
    Move move = new Move(board1.getSquare(1, 1), board1.getSquare(1, 3),
                         false, false);
    board1.applyMove(move);
    testUnapplyMove(board1, move, new Square(1, 1, Color.WHITE),
                                  new Square(1, 3, Color.NONE));

    board1 = new Board('A', 'A');
    // note this isnt a valid move but normally move is only called on 
    // valid moves, just for testing purposes
    move = new Move(board1.getSquare(1,1), board1.getSquare(1,6),
                         true, false);
    board1.applyMove(move);
    testUnapplyMove(board1, move, new Square(1, 1, Color.WHITE),
                                  new Square(1, 6, Color.BLACK));
  }

  private static void testConstructor(char whiteGap, char blackGap){
    System.out.println("Does this look right for whiteGap: " + whiteGap +
                       " and blackGap: " + blackGap + "?");
    Board board = new Board(whiteGap, blackGap);
    board.display();
  }

  // assumes Constructor works
  private static void testGetSquare(Board board, int x, int y,
                                    Square expected){
    Square actual = board.getSquare(x, y);
    if (!actual.equals(expected)){
      System.out.println("getSquare failed, expected: " + expected +
                         ", actual: " + actual);
    }   
  }

  // assumes getSquare and constructor work
  private static void testApplyMove(Board board, Move move,
                                    Square expectedFrom, Square expectedTo){
    board.applyMove(move);
    Square actualFrom = board.getSquare(move.getFrom().getX(),
                                        move.getFrom().getY());
    Square actualTo = board.getSquare(move.getTo().getX(),
                                      move.getTo().getY());
    if (!actualFrom.equals(expectedFrom) || !actualTo.equals(expectedTo)){
      System.out.println("applyMove failed, expected: (to: " + expectedTo +
                         ", from: " + expectedFrom + "), actual: (to: " +
                         actualTo + ", from: " + actualFrom + ")");
    }
    board.display();
  }
  
  // relies on applyMove and constructor and getSquare
  private static void testUnapplyMove(Board board, Move move,
                                      Square expectedFrom, Square expectedTo){
    board.unapplyMove(move);
    Square actualFrom = board.getSquare(move.getFrom().getX(),
                                        move.getFrom().getY());
    Square actualTo = board.getSquare(move.getTo().getX(),
                                      move.getTo().getY());
    if (!actualFrom.equals(expectedFrom) || !actualTo.equals(expectedTo)){
      System.out.println("unapplyMove failed, expected: (to: " + expectedTo +
                         ", from: " + expectedFrom + "), actual: (to: " +
                         actualTo + ", from: " + actualFrom + ")");
    }
    board.display();
  }
}
