/**
 * Created by ben on 01/01/16.
 */
import java.util.Random;

public class Player {
  Game game;
  Board board;
  Color color;
  boolean isComputerPlayer;
  Player Opponent;

  public Player(Game game, Board board, Color color, boolean isComputerPlayer) {
    this.game = game;
    this.board = board;
    this.color = color;
    this.isComputerPlayer = isComputerPlayer;
  }

  public void setOpponent(Player opponent){
    Opponent = opponent;
  }

  public Player getOpponent() {
    return Opponent;
  }

  public Color getColor() {
    return color;
  }

  public boolean isComputerPlayer() {
    return isComputerPlayer;
  }

  public Square[] getAllPawns(){
    Square[] pawnSquares = new Square[8];

    int index = 0;
    for (int i = 0; i < 8; i++){
      for (int j = 0; j < 8; j++){
        if (board.getSquare(i , j).occupiedBy() == color){
          pawnSquares[index] = board.getSquare(i, j);
          index += 1;
        }
      }
    }

    for (int i = index; i < 8; i ++){
      pawnSquares[i] = null;
    }
    return pawnSquares;
  }

  //todo: add first move
  public Move[] getAllValidMoves(){
    Move[] allValidMoves = new Move[32];
    Square[] pawnSquares = getAllPawns();
    int index = 0;

    if (color == Color.WHITE) {
      for (Square squareFrom : pawnSquares) {
        if (squareFrom == null) {
          break;
        }

        int x = squareFrom.getX();
        int y = squareFrom.getY();

        //check for first move
        if ((y == 1) && board.getSquare(x, (y + 1)).occupiedBy() == Color.NONE
                && board.getSquare(x, (y + 2)).occupiedBy() == Color.NONE){
          Move move = new Move(squareFrom, board.getSquare(x, y + 2), false, false);
          allValidMoves[index] = move;
          index += 1;
        }

        //check for standard move forward
        if ((y + 1) < 8 && board.getSquare(x, (y + 1)).occupiedBy() == Color.NONE) {
          Move move = new Move(squareFrom, board.getSquare(x, (y + 1)), false, false);
          allValidMoves[index] = move;
          index += 1;
        }

        //check for taking
        if ((x + 1) < 8 && (y + 1) < 8 && board.getSquare(x + 1, y + 1).occupiedBy() == Color.BLACK){
          Move move = new Move(squareFrom, board.getSquare(x + 1, y + 1), true, false);
          allValidMoves[index] = move;
          index += 1;
        }
        if ((x - 1) >= 0 && (y + 1) < 8 && board.getSquare(x - 1, y + 1).occupiedBy() == Color.BLACK){
          Move move = new Move(squareFrom, board.getSquare(x - 1, y + 1), true, false);

          allValidMoves[index] = move;
          index += 1;
        }

        //check for enPassant
        Move lastMove = game.getLastMove();
        if (lastMove == null){
          continue;
        }
        if (lastMove.isFirstMove() && lastMove.getTo().getY() == y){
          if (lastMove.getTo().getX() == (x + 1)){
            Move move = new Move(squareFrom, board.getSquare(x + 1, y + 1), false, true);
            allValidMoves[index] = move;
            index += 1;
          } else if (lastMove.getTo().getX() == (x - 1)){
            Move move = new Move(squareFrom, board.getSquare(x - 1, y + 1), false, true);
            allValidMoves[index] = move;
            index += 1;
          }
        }

      }
    } else if (color == Color.BLACK){
      for (Square squareFrom : pawnSquares) {
        if (squareFrom == null) {
          break;
        }

        int x = squareFrom.getX();
        int y = squareFrom.getY();

        //check for first move
        if ((y == 6) && board.getSquare(x, (y - 1)).occupiedBy() == Color.NONE
                && board.getSquare(x, (y - 2)).occupiedBy() == Color.NONE){
          Move move = new Move(squareFrom, board.getSquare(x, y - 2), false, false);
          allValidMoves[index] = move;
          index += 1;
        }

        //check for move forward
        if ((y - 1) > 0 && board.getSquare(x, (y - 1)).occupiedBy() == Color.NONE) {
          Move move = new Move(squareFrom, board.getSquare(x, (y - 1)), false, false);
          allValidMoves[index] = move;
          index += 1;
        }

        //check for taking
        if ((x + 1) < 8 && (y - 1) > 0 && board.getSquare(x + 1, y - 1).occupiedBy() == Color.WHITE){
          Move move = new Move(squareFrom, board.getSquare(x + 1, y - 1), true, false);
          allValidMoves[index] = move;
          index += 1;
        }
        if ((x - 1) >= 0 && (y - 1) > 0 && board.getSquare(x - 1, y - 1).occupiedBy() == Color.WHITE){
          Move move = new Move(squareFrom, board.getSquare(x - 1, y - 1), true, false);
          allValidMoves[index] = move;
          index += 1;
        }

        //check for enPassant
        Move lastMove = game.getLastMove();
        if (lastMove == null){
          continue;
        }
        if (lastMove.isFirstMove() && lastMove.getTo().getY() == y){
          if (lastMove.getTo().getX() == (x + 1)){
            Move move = new Move(squareFrom, board.getSquare(x + 1, y - 1), true, true);
            allValidMoves[index] = move;
            index += 1;
          } else if (lastMove.getTo().getX() == (x - 1)){
            Move move = new Move(squareFrom, board.getSquare(x - 1, y - 1), true, true);
            allValidMoves[index] = move;
            index += 1;
          }
        }

      }
    }

    for (int i =  index; i < 32; i += 1){
      allValidMoves[i] = null;
    }

    return allValidMoves;
  }

  public boolean isPassedPawn(Square square){
    //todo: implement isPassedPawn
    return false;
  }

  public void makeMove(){
    if (isComputerPlayer){
      Move[] allValidMoves = getAllValidMoves();
      Move nextMove;
      Random random = new Random();
      do{
        nextMove = allValidMoves[random.nextInt(allValidMoves.length)];
      }while (nextMove == null);

      game.applyMove(nextMove);
    }
    //todo: implement makeMove
  }
}
