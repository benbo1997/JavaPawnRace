public class Move{
  Square from, to;
  boolean isCapture;
  boolean isEnPassantCapture;

  public Move(Square from, Square to, boolean isCapture,
              boolean isEnPassantCapture){
    this.from = from;
    this.to = to;
    this.isCapture = isCapture;
    this.isEnPassantCapture = isEnPassantCapture;
  }

  public Square getFrom(){
    return from;
  }

  public Square getTo(){
    return to;
  }

  public boolean isAnyCapture(){
    return (isCapture || isEnPassantCapture);
  }

  public String getSAN(){
    if (isAnyCapture()){
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
