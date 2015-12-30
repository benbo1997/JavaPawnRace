public class Game{
  Move[] history = new Move[120];
  int index = 0;
  Board board;
  Color currentPlayer = Color.WHITE;
  Color winner = Color.NONE;

  public Game(Board board){
    this.board = board;
  }

  public Color getCurrentPlayer(){
    return currentPlayer;
  }

  public void changeColor(){
    if (currentPlayer == Color.WHITE){
      currentPlayer = Color.BLACK;
    } else {
      currentPlayer = Color.WHITE;
    }
  }

  public Move getLastMove(){
    if (index == 0){
      return null;
    }
    return history[index-1];
  }
  
  public void applyMove(Move move){
    history[index] = move;
    board.applyMove(move);
    changeColor();
    index += 1;
  }

  public void unapplyMove(Move move){
    if (index == 0){
      return;
    }
    board.unapplyMove(history[(index - 1)]);
    index -= 1;
    changeColor();
  }

  public boolean isFinished(){
    // reached end
    if (whiteWin() || blackWin()){
      return true;
    }

    // all pawns gone


    // no valid moves left
    return false;
  }

  private boolean whiteWin(){
    for (int i = 0; i < 8; i++) {
      if (board.getSquare(i, 7).occupiedBy() == Color.WHITE) {
        winner = Color.WHITE;
        return true;
      }
    }
    return false;
  }

  private boolean blackWin(){
    for (int i = 0; i < 8; i++){
      if (board.getSquare(i, 0).occupiedBy() == Color.BLACK) {
        winner = Color.BLACK;
        return true;
      }
    }
    return false;
  }

  public Color getGameResult(){
    return winner;
  }

  // change switch statement to better method?
  public Move parseMove(String san){
    boolean ambiguous = true;
    boolean isAnyCapture = false;
    boolean isEnPassantCapture = false;
    boolean isNormalCapture = false;
    int len = san.length();
    String to = "";
    String from = "";

    switch (len){
    case 2:
      to = san;
      isEnPassantCapture = false;
      isNormalCapture = false;
      break;
    case 5:
      ambiguous = false;
      from = "" + san.charAt(0) + san.charAt(1);
      to = "" + san.charAt(3) + san.charAt(4);
      if (san.charAt(3) == 'x'){
        isAnyCapture = true;
      }
      break;
    default: 
      return  null;
    }

    int toX = coordXInt(to);
    int toY = coordYInt(to);

    if (!isValidCoord(toX, toY)){
      return null;
    }
    if (ambiguous){
      from = calculateFrom(toX, toY);
      if (from == null){
        return null;
      }
    }

    int fromX = coordXInt(from);
    int fromY = coordYInt(from); 

    if (!isValidCoord(fromX, fromY)){
      return null;
    }

    Square toSq = board.getSquare(toX, toY);
    Square fromSq = board.getSquare(fromX, fromY);

    if (!isValidSquares(toSq, fromSq)){
      return null;
    }
    // returns null if a capture isnt explicitly stated, required?
    // should be after checking is a valid pawn selection

    
    if ((len == 2) && (isCapture(toSq))){
      return null;
    } else if ((len == 5) && (isCapture(toSq) || isEnPassantCapture(toSq))
               && !(isAnyCapture)){
      return null;
    }
    // this is not efficient (repeated calls to isEnpassantCapture and
    //   isCapture so can be tidied later
    if (len == 5){
      isNormalCapture = isCapture(toSq);
      isEnPassantCapture = isEnPassantCapture(toSq);
    }
    
    return new Move(toSq, fromSq, isNormalCapture, isEnPassantCapture);
  } 

  // assumes a valid 'to' move
  private String calculateFrom(int toX, int toY){
    String from;
    switch (currentPlayer){
    case WHITE:
      if (board.getSquare(toX, (toY - 1)).occupiedBy() == Color.WHITE &&
          board.getSquare(toX, (toY - 2)).occupiedBy() == Color.BLACK){
        from = "" + (char) ((toX) + (int) 'A')
                  + (char) ((toY - 1) + (int) '0');
      } else if(board.getSquare(toX, (toY - 1)).occupiedBy() == Color.BLACK &&
                 board.getSquare(toX, (toY - 2)).occupiedBy() == Color.WHITE){
        from = "" + (char) ((toX) + (int) 'A')
                  + (char) ((toY - 2) + (int) '0');
      } else {
        from = null;
      }
      break;
    case BLACK:
      if (board.getSquare(toX, (toY + 1)).occupiedBy() == Color.BLACK &&
          board.getSquare(toX, (toY + 2)).occupiedBy() == Color.WHITE){
        from = "" + (char) ((toX) + (int) 'A')
                  + (char) ((toY + 1) + (int) '0');
      } else if(board.getSquare(toX, (toY + 1)).occupiedBy() == Color.WHITE &&
                 board.getSquare(toX, (toY + 2)).occupiedBy() == Color.BLACK){
        from = "" + (char) ((toX) + (int) 'A')
                  + (char) ((toY + 2) + (int) '0');
      } else {
        from = null;
      }
      break;

    default:
      assert (true): "currentPlayer is NONE when calculating move";
      from = null;
    }
    
    return from;
  }

  private boolean isValidCoord(int x, int y){
    boolean valid = false;
    if ((x < 8) && (x >= 0) && (y < 8) && (y >= 0)){
      valid = true;
    }
    return valid;
  }

  private int coordXInt(String coord){
    assert (coord.length() == 2):
      "Coordinate cannot be converted to int if length != 2";

    char xLetter = coord.charAt(0);
    int xInt = (int) xLetter - (int) 'A';

    return xInt;
  }

  private int coordYInt(String coord){
    assert (coord.length() == 2):
      "Coordinate cannot be converted to int if length != 2";

    char yNumber = coord.charAt(1);
    int yInt = (int) yNumber - (int) '0';

    return yInt;
  }

  // assumes possibility of moving to same colour will be checked separately
  private boolean isCapture(Square to){
    if (to.occupiedBy() != Color.NONE){
      return true;
    }
    return false;
  }

  private boolean isEnPassantCapture(Square to){
    if (index == 0){
      return false;
    }
    Move prevMove = history[index - 1];
    if (prevMove.isFirstMove() && (to.getX() == prevMove.getTo().getX())){
      switch (currentPlayer){
      case WHITE:
        return ((to.getY() + 1) == prevMove.getTo().getY());
      case BLACK:
        return ((to.getY() - 1) == prevMove.getTo().getY());
      default:
        assert (true): "Can't check En Passant for current player NONE";
        return false;
      }
    }
    return false;
  }

  private boolean isValidSquares(Square to, Square from){
    if (from.occupiedBy() != currentPlayer){
      return false;
    }
    // jeeeeeeeeeeeeez this is so ugly
    switch (currentPlayer){
    case WHITE:
      if (((to.getY() == (from.getY() + 1)) 
           && (from.occupiedBy() == Color.NONE)
           && (to.getX() == from.getX()))
       || ((to.getY() == 1)
           && (from.getY() == 3)
           && (from.occupiedBy() == Color.NONE)
           && (to.getX() == from.getX())
           && (board.getSquare(to.getX(), 2).occupiedBy() == Color.NONE))
       || ((to.getY() == 5)
           && (from.getY() == 4)
           && (from.occupiedBy() == Color.NONE)
           && ((to.getX() == (from.getX() + 1))
               || (to.getX() == (from.getX() - 1)))
           && (board.getSquare(to.getX(), 5).occupiedBy() == Color.BLACK))
       || (to.getY() == (from.getY() + 1)
           && ((to.getX() == (from.getX() + 1))
               || (to.getX() == (from.getX() - 1)))
           && (to.occupiedBy() == Color.BLACK))){
        return true;
      } break;
    case BLACK:
      if (((to.getY() == (from.getY() - 1)) 
           && (from.occupiedBy() == Color.NONE)
           && (to.getX() == from.getX()))
       || ((to.getY() == 6)
           && (from.getY() == 4)
           && (from.occupiedBy() == Color.NONE)
           && (to.getX() == from.getX())
           && (board.getSquare(to.getX(), 5).occupiedBy() == Color.NONE))
       || ((to.getY() == 2)
           && (from.getY() == 3)
           && (from.occupiedBy() == Color.NONE)
           && ((to.getX() == (from.getX() + 1))
               || (to.getX() == (from.getX() - 1)))
           && (board.getSquare(to.getX(), 2).occupiedBy() == Color.WHITE))
       || (to.getY() == (from.getY() - 1)
           && ((to.getX() == (from.getX() + 1))
               || (to.getX() == (from.getX() - 1)))
           && (to.occupiedBy() == Color.WHITE))){
        return true;
      } break;
    default:
      assert (true): "Can't check a valid move for no player";
      return false;
    }
    return false;
  }
}
