public class Board{
  private Square[][] board = new Square[8][8];

  public Board(char whiteGap, char blackGap){
    int whiteGapInt = (int) whiteGap - (int) 'A';
    int blackGapInt = (int) blackGap - (int) 'A';

    assert (whiteGapInt >= 0 && whiteGapInt < 8):
           "Invalid whiteGap in board constructor";
    assert (blackGapInt >= 0 && blackGapInt < 8):
           "Invalid blackGap in board constructor";

    for (int j = 0; j < 8; j ++){
      for (int i = 0; i < 8; i++){
        if (i == 1){
          if (j == whiteGapInt){
            board[j][i] = new Square(j , i, Color.NONE);
            continue;
          } else {
            board[j][i] = new Square(j, i, Color.WHITE);
            continue;
          }
        }
        if (i == 6){
          if (j == blackGapInt){
            board[j][i] = new Square(j , i, Color.NONE);
            continue;
          } else {
            board[j][i] = new Square(j, i, Color.BLACK);
            continue;
          }  
        }
        board[j][i] = new Square(j, i, Color.NONE);
      }
    }
  }

  public Square getSquare(int x, int y){
    return board[x][y];
  }
  // assumes a valid move
  public void applyMove(Move move){
    Square from = move.getFrom();
    Square to = move.getTo();
    board[to.getX()][to.getY()] = new Square(to.getX(), to.getY(),
                                             from.occupiedBy());
    board[from.getX()][from.getY()] = new Square(from.getX(), from.getY(),
                                                 Color.NONE);
  }
  // assumes this was the last move made (squares are reverted to how they
  // were addressed in move
  public void unapplyMove(Move move){
    Square from = move.getFrom();
    Square to = move.getTo();
    board[from.getX()][from.getY()] = from;
    board[to.getX()][to.getY()] = to;
  }

  public void display(){
    System.out.println("   A B C D E F G H\n");

    for (int i = 7; i >= 0; i--){
      System.out.print((i + 1) + "  ");
      for (int j = 0; j < 8; j++){
        Color color = board[j][i].occupiedBy();
        switch (color){
          case BLACK:
            System.out.print("B "); break;
          case WHITE:
            System.out.print("W "); break;
          case NONE:
            System.out.print(". "); break;
        }
      }
      System.out.println(" " + (i + 1));
    }

    System.out.println("\n   A B C D E F G H");
  }
}
