public class Square{
  int xCoord, yCoord;
  Color color = Color.NONE;

  public Square(int xCoord, int yCoord, Color color) {
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
    return Color.NONE;
  }

  public void setOccupier(Color color){
    //implement
  }

  public String getCoord(){
    char c = (char) (xCoord + (int) 'A');
    String coord = "" + c + yCoord;
    return coord;
  }
}
