public class Square{
  final int xCoord, yCoord;
  Color color = Color.NONE;

  public Square(int xCoord, int yCoord, Color color) {
    assert (xCoord >= 0 && xCoord <= 7):
      "x coordinate should be between 1 and 7 inclusive";
    assert (yCoord >= 0 && yCoord <= 7):
      "y coordinate should be between 1 and 7 inclusive";

    this.xCoord = xCoord;
    this.yCoord = yCoord;
    this.color = color;
  }

  public int getX(){
    return xCoord;
  }

  public int getY(){
    return yCoord;
  }

  public Color occupiedBy(){
    //implement
    return this.color;
  }

  public void setOccupier(Color color){
    //implement
  }

  public String getCoord(){
    char c = (char) (xCoord + (int) 'A');
    String coord = "" + c + (yCoord + 1);
    return coord;
  }
}
