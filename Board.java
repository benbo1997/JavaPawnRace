public class Board{
  Square[][] board = new Square[8][8];

  public Board(char whiteGap, char blackGap){
    int whiteGapInt = (int) whiteGap - (int) 'A';
    int blackGapInt = (int) blackGap - (int) 'A';

    assert (whiteGapInt >= 0 && whiteGapInt < 8):
           "Invalid whiteGap in board constructor";
    assert (blackGapInt >= 0 && blackGapInt < 8):
           "Invalid blackGap in board constructor";

    for (int i = 0; i < 8; i ++){
      for (int j = 0; j < 8; j++){
        if (i == 1){
          if (j == whiteGapInt){
            board[i][j] = new Square(i , j, Color.NONE);
            continue;
          } else {
            board[i][j] = new Square(i, j, Color.WHITE);
            continue;
          }
        }
        if (i == 6){
          if (j == blackGapInt){
            board[i][j] = new Square(i , j, Color.NONE);
            continue;
          } else {
            board[i][j] = new Square(i, j, Color.BLACK);
            continue;
          }  
        }
        board[i][j] = new Square(i, j, Color.NONE);
      }
    }
  }

  public Square getSquare(int x, int y){
    return (new Square (0, 0, Color.NONE));
  }
  
  public void applyMove(Move move){

  }

  public void unapplyMove(Move move){

  }

  public void display(){
    System.out.println("   A B C D E F G H\n");

    for (int i = 7; i >= 0; i--){
      System.out.print((i + 1) + "  ");
      for (int j = 0; j < 8; j++){
        Color color = board[i][j].occupiedBy();
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
