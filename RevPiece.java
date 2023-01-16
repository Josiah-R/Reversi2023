/** 
  * @author	 	 Noah Jackson
  * @id          noah.jackson@betheluniversity.edu
  * @course      CSC 321: Programming 3
  * @assignment  Reversi Project
  * @related     Piece
  */
public class RevPiece extends Piece {
	public static final int WHITE = 1;
	public static final int BLACK = 2;
	
	public RevPiece(int inType) {
		super(inType);
	}
	
	public void setType(int newType) {
		type = newType;
	}
	
	public int getOpp(int inType) {
		if (inType < MAX-1 || inType > MAX)
			if (inType == 1) return 2;
			else return 1;
		return -1;
	}
	
	public char toChar() {
        if (type == WHITE) return 'W'; //White
        if (type == BLACK) return 'B'; //Black
        else return '-'; 		   //Blank
    }
}
