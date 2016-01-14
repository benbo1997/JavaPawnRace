/**
 * Created by ben on 01/01/16.
 */
import java.util.Scanner;

public class PawnRace {

  public static void main(String[] args){

    String input;
    String player1Code = args[0];
    String player2Code = args[1];
    String whiteGap = args[2];
    String blackGap = args[3];

    char whiteGapChar = Character.toUpperCase(whiteGap.charAt(0));
    char blackGapChar = Character.toUpperCase(blackGap.charAt(0));
    Board board = new Board(whiteGapChar, blackGapChar);
    Game game = new Game(board);

    char player1Char = Character.toUpperCase(player1Code.charAt(0));
    char player2Char = Character.toUpperCase(player2Code.charAt(0));
    boolean player1PlayerBool;
    boolean player2PlayerBool;

    switch (player1Char){
      case 'P' : player1PlayerBool = true; break;
      case 'C' : player1PlayerBool = false; break;
      default:
        throw new AssertionError("Non Valid arguments given");
    }
    switch (player2Char){
      case 'P' : player2PlayerBool = true; break;
      case 'C' : player2PlayerBool = false; break;
      default:
        throw new AssertionError("Non Valid arguments given");
    }

    Player player1 = new Player(game, board, Color.WHITE, !player1PlayerBool);
    Player player2 = new Player(game, board, Color.BLACK, !player2PlayerBool);

    player1.setOpponent(player2);
    player2.setOpponent(player1);
    Player currentPlayer = player1;
    game.setPlayerTurn(player1);

    boolean finished = false;
    Scanner scanner = new Scanner(System.in);

    while (!finished){
      board.display();

      if (currentPlayer.isComputerPlayer()){
        currentPlayer.makeMove();
      } else {
        System.out.println("It is " + game.getCurrentColor() + "'s turn:");
        Move nextMove = null;

        while (nextMove == null){
          System.out.println("Enter a move:");
          /*
          Move[] valid = currentPlayer.getAllValidMoves();
          for (int i = 0; i < valid.length; i++){
            System.err.println(valid[i]);
          }
          Square[] pawns = currentPlayer.getAllPawns();
          for (int i = 0; i < pawns.length; i++){
            System.err.println(pawns[i]);
          }*/
          input = scanner.nextLine();
          nextMove = game.parseMove(input);

        }

        game.applyMove(nextMove);
      }

      finished = game.isFinished();

      if (currentPlayer == player1){
        currentPlayer = player2;
      } else {
        currentPlayer = player1;
      }
    }

    System.out.println("GAME OVER: " + game.getGameResult() + " WINS!");
    board.display();
  }
}
