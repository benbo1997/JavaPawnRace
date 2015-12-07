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

  // kind of cheeky in how it asserts a legit move
  public Move parseMove(String san){
    boolean ambiguous = false;
    int len = san.length();

    String from, to;
    switch (len){
    case 2:
      to = 
    case 5:
    default: return  null;

    }
  }
}
