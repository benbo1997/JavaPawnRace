public class Game{
  Move[] history = new Move[120];
  int index = 0;
  Board board;
  Color currentPlayer = Color.WHITE;

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
    // all pawns gone
    // no valid moves left
    return false;
  }

  public Color getGameResult(){
    return Color.NONE;
  }

  // change switch statement to better method?
  public Move parseMove(String san){
    boolean ambiguous = false;
    boolean isAnyCapture = false;
    int len = san.length();
    String from, to;

    switch (len){
    case 2:
      to = san;
    case 5:
      ambiguous = true;
      from = "" + san.charAt(0) + san.charAt(1);
      to = "" + san.charAt(3) + san.charAt(4);
      if (san.charAt(3) == 'x'){
        isAnyCapture = true;
      }
    default: return  null;

    }
  } 

  private boolean isValidMove(String to){
    boolean valid = true;
    valid &= isValidCoord(to);

    return valid;
  }

  private boolean isValidMove(String from, String to, boolean isAnyCapture){
    boolean valid = true;
    valid &= isValidCoord(to);
    valid &= isValidCoord(from);

    return valid;
  }

  private boolean isValidCoord(String coord){
    char xLetter = coord.charAt(0);
    char yNumber = coord.charAt(1);

    int xInt = (int) xLetter - (int) 'A';
    int yInt = (int) yNumber - (int) '0';

    return (((xInt < 8) && (xInt >= 0)) || ((yInt < 8) && (yInt >= 0)));
    
  }

  private boolean isEnPassantCapture(){
    return false;
  }
}
