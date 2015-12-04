public class Move{
  Square from, to;
  boolean isCapture;

  public Move(Square from, Square to, boolean isCapture){
    this.from = from;
    this.to = to;
    this.isCapture = isCapture;
  }

  public Square getFrom(){
    return from;
  }

  public Square getTo(){
    return to;
  }

  public boolean isCapture(){
    return isCapture;
  }

  public String getSAN(){
    if (isCapture()){
      String SAN = from.getCoord() + "x" + to.getCoord();
      return SAN;
    } else if (isAmbiguous()){
      String SAN = from.getCoord() + "-" + to.getCoord();
      return SAN;
    } else {
      String SAN = to.getCoord();
      return SAN;
    }
  }

  private boolean isAmbiguous(){
    //implement
    return false;
  }
}
