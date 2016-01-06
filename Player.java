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

  // checks if is a passed pawn before a move is made
  public boolean isPassedPawn(Square square){
    if (this.getColor() == Color.WHITE){
      int x = square.getX();
      int y = square.getY();


      if (y == 7){
        return true;
        //game should be won, code shouldn't run here
      }
      if (x == 0){
        for (int i = (y + 1); i < 8; i++){
          if (board.getSquare(0, i).occupiedBy() == Color.BLACK || board.getSquare(1, i).occupiedBy() == Color.BLACK){
            return false;
          }
        }
        return true;
      } else if (x == 7){
        for (int i = (y + 1); i < 8; i++){
          if (board.getSquare(7, i).occupiedBy() == Color.BLACK || board.getSquare(6, i).occupiedBy() == Color.BLACK){
            return false;
          }
        }
        return true;
      } else {
        for (int i = (y + 1); i < 8; i++){
          if (board.getSquare(x, i).occupiedBy() == Color.BLACK
                  || board.getSquare((x - 1), i).occupiedBy() == Color.BLACK
                  || board.getSquare((x + 1), i).occupiedBy() == Color.BLACK){
            return false;
          }
        }
        return true;
      }
    } else {
      int x = square.getX();
      int y = square.getY();


      if (y == 0) {
        return true;
        //game should be won, code shouldn't run here
      }
      if (x == 0) {
        for (int i = (y - 1); i >= 0; i--) {
          if (board.getSquare(0, i).occupiedBy() == Color.WHITE || board.getSquare(1, i).occupiedBy() == Color.WHITE) {
            return false;
          }
        }
        return true;
      } else if (x == 7) {
        for (int i = (y - 1); i >= 0; i--) {
          if (board.getSquare(7, i).occupiedBy() == Color.WHITE || board.getSquare(6, i).occupiedBy() == Color.WHITE) {
            return false;
          }
        }
        return true;
      } else {
        for (int i = (y - 1); i >= 0; i--) {
          if (board.getSquare(x, i).occupiedBy() == Color.WHITE
                  || board.getSquare((x - 1), i).occupiedBy() == Color.WHITE
                  || board.getSquare((x + 1), i).occupiedBy() == Color.WHITE) {
            return false;
          }
        }
        return true;
      }
    }
  }

  public void makeMove(){
    Move[] allValidMoves = getAllValidMoves();
    Move nextMove = null;

    //non-random method
    nextMove = decideMove(allValidMoves);

    // random method runs if null nextMove
    if (nextMove == null){
      Random random = new Random();
      do{
        nextMove = allValidMoves[random.nextInt(allValidMoves.length)];
      }while (nextMove == null);
    }

    game.applyMove(nextMove);

    //todo: implement makeMove
  }

  // weirdly refuses to win the game, problem with isPassedPawn on last column methink (just for black)
  private Move decideMove(Move[] allValidMoves){
    Move nextMove = null;
    Square passedPawn = null;
    for (Move mv: allValidMoves){
      if(mv == null){
        continue;
      }
      game.applyMove(mv);
      Square[] pawns = getAllPawns();
      for(Square pawn: pawns){
        if (pawn == null){
          continue;
        }
        if(isPassedPawn(pawn)){

          if (passedPawn == null){
            passedPawn = pawn;
            nextMove = mv;
          } else if (squaresRemaining(pawn) < squaresRemaining(passedPawn)){
            nextMove = mv;
            passedPawn = pawn;
          }
        }
      }

      game.unapplyMove(mv);
    }
    if (passedPawn != null){
      System.err.println(squaresRemaining(passedPawn));
    }

    return nextMove;
  }

  // returns 8 for checking a none square
  private int squaresRemaining(Square pawn){
    int currentY = pawn.getY();
    Color color = pawn.occupiedBy();
    if (color == color.NONE){
      return 8;
    }

    if (color == color.WHITE){
      return (7 - currentY);
    } else {
      return currentY;
    }
  }
}
