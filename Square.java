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
    return this.color;
  }

  public void setOccupier(Color color){
    this.color = color;
  }

  public String getCoord(){
    char c = (char) (xCoord + (int) 'A');
    String coord = "" + c + (yCoord + 1);
    return coord;
  }

  public boolean equals(Square square){
    if (this.getX() != square.getX()){
      return false;
    }
    if (this.getY() != square.getY()){
      return false;
    }
    if (this.occupiedBy() != square.occupiedBy()){
      return false;
    }
    return true; 
  }

  public String toString(){
    return "(Square at (" + this.getX() + ", " + this.getY() +
                 ") of colour " + this.occupiedBy() + ")";
  }
}
