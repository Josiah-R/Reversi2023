/*  Josiah Railton
    Programming III, Spring 23
    Reversi*/

public class ReversiPiece extends Piece {
    // Sets up constant integers for the color types
    public static final int WHITE = 1;
    public static final int BLACK = 2;

    // Constructor to initialize ReversiPiece
    public ReversiPiece(int inType) {
        super(inType);
    }
    // Flips the type of piece from black and white
    public void flipType() {
        type = getOpposite();
    }
    // Sets a type.
    // Could be included in Piece, instead of ReversiPiece
    public void setType(int inType) {
        if (inType < MIN || inType > MAX) {
            System.err.println("invalid piece valid");
            type = -1;
        }
        // exception for invalid type needed
        type = inType;
    }
    // Returns the opposite color
    public int getOpposite() {
        if (type == WHITE) return BLACK;
        if (type == BLACK) return WHITE;
        return BLANK;
    }
}