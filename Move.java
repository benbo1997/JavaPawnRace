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

  public boolean equals(Move move){
    if (this.from.equals(move.getFrom()) &&
        this.to.equals(move.getTo()) &&
        (this.isCapture == move.getIsCapture()) &&
        (this.isEnPassantCapture == move.getIsEnPassantCapture())){
      return true;
    }
    return false;
  }

  public Square getFrom(){
    return from;
  }

  public Square getTo(){
    return to;
  }

  public boolean getIsCapture(){
    return isCapture;
  }

  public boolean getIsEnPassantCapture(){
    return isEnPassantCapture;
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

  public boolean isFirstMove(){
    switch (from.occupiedBy()){
    case WHITE:
      if (from.getY() == 1 && to.getY() == 3){
        return true;
      }
      return false;
    case BLACK:
      if (from.getY() == 6 && to.getY() == 4){
        return true;
      }
      return false;
    default:
      assert (true): "Can't work out if an invalid move was the first move";
      return false;
    }
  }
}
