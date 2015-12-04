public class SquareTests {
  //staticify everything!
 
  public SquareTests(){
    getXTests();
    getYTests();
    occupiedByTests();
    setOccupierTests();
    getCoordTests();
  }

  private static void getXTests(){
    testGetX(new Square(1, 7, Color.NONE), 1);
    testGetX(new Square(7, 7, Color.NONE), 7);
    testGetX(new Square(3, 1, Color.NONE), 3);
  }

  private static void getYTests(){
    testGetY(new Square(1, 7, Color.NONE), 7);
    testGetY(new Square(7, 7, Color.NONE), 7);
    testGetY(new Square(3, 1, Color.NONE), 1);
  }

  private static void occupiedByTests(){
    testOccupiedBy(new Square(1, 7, Color.NONE), Color.NONE);
    testOccupiedBy(new Square(1, 3, Color.WHITE), Color.WHITE);
    testOccupiedBy(new Square(2, 2, Color.BLACK), Color.BLACK);
    testOccupiedBy(new Square(1, 1, Color.BLACK), Color.BLACK);
  }

  private static void setOccupierTests(){
    testSetOccupier(new Square(1, 7, Color.NONE), Color.NONE);
    testSetOccupier(new Square(2, 2, Color.WHITE), Color.NONE);
  }

  private static void getCoordTests(){
    testGetCoord(new Square(1, 7, Color.WHITE), "B8");
    testGetCoord(new Square(3, 3, Color.NONE), "D4");
    testGetCoord(new Square(5, 1, Color.BLACK), "F2");
  }



  private static void testGetX(Square sq, int expected){
    if (sq.getX() != expected){
      System.out.println("testGetX failed, expected: " + expected
                         + ", actual: " + sq.getX());
    }
  }

  private static void testGetY(Square sq, int expected){
    if (sq.getY() != expected){
      System.out.println("testGetY failed, expected: " + expected
                         + ", actual: " + sq.getY());
    }
  }

  private static void testOccupiedBy(Square sq, Color expected){
    if (sq.occupiedBy() != expected){
      System.out.println("testOccupiedBy failed, expected: " + expected
                         + ", actual: " + sq.occupiedBy());
    }
  }
  
  private static void testSetOccupier(Square sq, Color expected){
    sq.setOccupier(expected);
    Color actual = sq.occupiedBy();
    if (actual != expected){
      System.out.println("setOccupier failed, expected: " + expected
                         + ", actual: " + actual);
    }
  }

  private static void testGetCoord(Square sq, String expected){
    if (!(sq.getCoord()).equals(expected)){
       System.out.println("getCoord failed, expected: " + expected
                         + ", actual: " + sq.getCoord());
    }
  }
}
