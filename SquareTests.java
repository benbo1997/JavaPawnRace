public class SquareTests {
  //staticify everything!
 
  public static void main(String[] args){
    System.out.println("Running tests...");

    getXTests();
    getYTests();
//    occupiedByTests();
//    setOccupierTests();
//    getCoordTests();
//
    System.out.println("\t...done");
  }

  private static void getXTests(){
    testGetX(new Square(1, 7, Color.NONE), 1);
    testGetX(new Square(7, 7, Color.NONE), 7);
    testGetX(new Square(3, 1, Color.NONE), 3);
  }

  private static void getYTests(){
    testGetX(new Square(1, 7, Color.NONE), 7);
    testGetX(new Square(7, 7, Color.NONE), 7);
    testGetX(new Square(3, 1, Color.NONE), 1);
  }

  private void occupiedByTests(){
    // fell asleep here
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

  private void testOccupiedBy(Square sq, Color expected){
    if (sq.occupiedBy() != expected){
      System.out.println("testOccupiedBy failed, expected: " + expected
                         + ", actual: " + sq.occupiedBy());
    }
  }
  
  private void testSetOccupier(Square sq, Color expected){
    sq.setOccupier(expected);
    Color actual = sq.occupiedBy();
    if (actual != expected){
      System.out.println("setOccupier failed, expected: " + expected
                         + ", actual: " + actual);
    }
  }

  private void testGetCoord(Square sq, String expected){
    if (!(sq.getCoord()).equals(expected)){
       System.out.println("getCoord failed, expected: " + expected
                         + ", actual: " + sq.getCoord());
    }
  }
}
