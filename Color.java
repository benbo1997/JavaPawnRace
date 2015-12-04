public enum Color{
  BLACK,
  WHITE,
  NONE;

  public String toString(){
    switch (this){
      case BLACK: return "BLACK";
      case WHITE: return "WHITE";
      case NONE: return "NONE";
      default: return "WTF";
    }
  }
}
