public class GameTests{
  public GameTests(){
    parseMoveTests();
  }

  private static void parseMoveTests(){
    Game game1 = new Game(new Board('A', 'A'));
    testParseMove(game1, "B3",
                  new Move(new Square(1, 1, Color.WHITE),
                           new Square(1, 2, Color.NONE),
                           false, false));
  }

  private static void testParseMove(Game game, String san, Move expected){
    Move actual = game.parseMove(san);
    if (!actual.equals(expected)){
      System.out.println("testParseMove failed for " + san);
    }
  }
}
