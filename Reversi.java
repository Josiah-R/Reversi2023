/** 
  * @author	 	 Noah Jackson
  * @id          noah.jackson@betheluniversity.edu
  * @course      CSC 321: Programming 3
  * @assignment  Reversi Project
  * @related     Piece
  */
public class Reversi extends Board {
	//Fields
	private RevPiece[][] pieces;
	
	//Constructor
	public Reversi() {
		size = DEFAULTSIZE;
        pieces = new RevPiece[size][size];
		setGame();
	}
	
	//Accessors
	public RevPiece getPiece(int row, int column) {
        return pieces[row][column];
    }
	
	//Class methods
	private void setGame() {
        for (int row = 0; row < size; row++)
            for (int column = 0; column < size; column++)
                pieces[row][column] = new RevPiece(Piece.BLANK);
        getPiece(3,3).setType(2);
		getPiece(3,4).setType(1);
		getPiece(4,3).setType(1);
		getPiece(4,4).setType(2);
    }	
	
	public void makeMove(int row, int col, RevPiece move) { 
		if(isValid(move, row, col)) {
			pieces[row][col] = move;
			//flip over the effected pieces
		}
	}
	
	public boolean isValid(RevPiece piece, int row, int col) { 
		if (pieces[row][col].getType() == Piece.BLANK) { //check for blank type
			//determine which spots are playable for provided team
			// seq search thru the board
			//analyze each piece of the provided team
			//check all 8 directions for the nearest "same color" piece
			//keep tracks of opponets to be flipped
			 
		}
		return false;
		
	}
}
