public class MoveTests{
  public MoveTests(){
    getSANTests();
  }

  private static void getSANTests(){
    testGetSAN(new Move(new Square(1, 1, Color.WHITE),
                        new Square(1, 3, Color.NONE),
                        false,
                        false),
               "B4");
    testGetSAN(new Move(new Square(1, 1, Color.WHITE),
                        new Square(1, 3, Color.BLACK),
                        true,
                        false),
               "B2xB4");
    testGetSAN(new Move(new Square(1, 1, Color.WHITE),
                        new Square(1, 3, Color.BLACK),
                        false,
                        true),
               "B2xB4");
  }

  private static void testGetSAN(Move move, String expected){
    String actual = move.getSAN();
    if (!actual.equals(expected)){
      System.out.println("getSAN failed, expected: " + expected
                         + ", actual: " + actual + ".");
    }
  }
}
