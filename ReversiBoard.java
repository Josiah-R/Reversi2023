/********************************************************
    * Author: Josiah Railton
	* Course: CSC 321
	* Assignment: Reversi Game GUI Sample
    * Purpose: Board extension for a Reversi Board
        made for the Reversi Game.
********************************************************/

public class ReversiBoard extends Board {
    // A player class could probably simplifiy this in the future
    ReversiPiece player1, player2;
    // table of the pieces on the board
    protected ReversiPiece[][] pieces;

    // COnstructor that initializes the board with default size
    public ReversiBoard() {
        player1 = new ReversiPiece(ReversiPiece.WHITE);
        player2 = new ReversiPiece(ReversiPiece.BLACK);

        size = Board.DEFAULTSIZE;
        pieces = new ReversiPiece[size][size];
        blankBoard();
        addStart();
    }
    // Constructor that initializes the board with a given size
    public ReversiBoard(int inSize) {
        player1 = new ReversiPiece(ReversiPiece.WHITE);
        player2 = new ReversiPiece(ReversiPiece.BLACK);

        size = inSize;
        pieces = new ReversiPiece[size][size];
        blankBoard();
        addStart();
    }

    // Updated function adds ReversiPiece, not just piece. 
    protected void blankBoard() {
        int row, column;

        for (row = 0; row < size; row++)
            for (column = 0; column < size; column++)
                pieces[row][column] = new ReversiPiece(ReversiPiece.BLANK);

    }
    //Adds the starting four pieces
    //ight not be centered if an odd numbered square
    private void addStart() {
        pieces[Math.round(size / 2)][Math.round(size / 2)].setType(1);
        pieces[Math.round(size / 2) + 1][Math.round(size / 2) + 1].setType(1);
        pieces[Math.round(size / 2) + 1][Math.round(size / 2)].setType(2);
        pieces[Math.round(size / 2)][Math.round(size / 2) + 1].setType(2);
    }
    // Returns true if cell is empty, false if not
    public boolean cellIsEmpty(int row, int column) {
        if (pieces[row][column].getType() == ReversiPiece.BLANK)
            return true;
        else
            return false;
    }
    // Returns true if cell is on the board, false if not. 
    public boolean onTheBoard(int row, int column) {
        if (row > -1 && row < pieces.length)
            if (column > -1 && column < pieces[row].length)
                return true;
        return false;
    }

    // Direction map
    //   0   1   2
    //   7       3
    //   6   5   4
    // Returns an array of two integers to change the row and column values
    // Based on a given direction, mapped above in the comment. 
    private int[] getDirectionVariables(int direction) {
        if (direction == 0) return new int[] {-1, -1};
        if (direction == 1) return new int[] {-1, 0};
        if (direction == 2) return new int[] {-1, 1};
        if (direction == 3) return new int[] {0, 1};
        if (direction == 4) return new int[] {1, 1};
        if (direction == 5) return new int[] {1, 0};
        if (direction == 6) return new int[] {1, -1};
        if (direction == 7) return new int[] {0, -1};
        return new int[] {0,0};
    }
    // Checks if a move causes a flip in a given direction
    private boolean checkDirectionFlip(int row, int column, 
                                        ReversiPiece inPiece, int direction) {
        int[] cellChange;
        int currentRow, currentColumn;

        cellChange = getDirectionVariables(direction);

        //Looks in a direction till not the opposite piece
        currentRow = row + cellChange[0];
        currentColumn = column + cellChange[1];
        while (onTheBoard(currentRow, currentColumn) == true && 
                pieces[currentRow][currentColumn].getType() 
                == inPiece.getOpposite()) {
            currentRow = currentRow + cellChange[0];
            currentColumn = currentColumn + cellChange[1];
        }
        // Checks the next piece after all the opposite pieces in direction
        if (onTheBoard(currentRow, currentColumn) == true && 
            pieces[currentRow][currentColumn].getType() == inPiece.getType()) {
            return true;
        }
        return false;
    }
    // Returns if a given move is legal
    public boolean legalMove(int row, int column, ReversiPiece inPiece) {
        if (onTheBoard(row, column) == false) {
            return false;
        }
        if (cellIsEmpty(row, column) == false) {
            return false;
        }

        boolean legalMove;
        int direction;

        legalMove = false;
        direction = 0;
        // Checks each direction
        while (direction < 8 && legalMove == false) {
            legalMove = checkDirectionFlip(row, column, inPiece, direction);
            direction++;
        }
        return legalMove;
    }
    // Makes a move if it is legal
    public void makeMove(int row, int column, ReversiPiece inPiece) {
        if (legalMove(row, column, inPiece) == true) {
            int[] cellChange;
            int currentRow, currentColumn;

            pieces[row][column].setType(inPiece.getType());
            //Check all directions, flip any between type. 
            for (int direction = 0; direction < 8; direction++) {
                if (checkDirectionFlip(row, column, inPiece, direction) 
                        == true) {
                    // while opposite in direction, flip piece. 
                    cellChange = getDirectionVariables(direction);
                    currentRow = row + cellChange[0];
                    currentColumn = column + cellChange[1];
                    while (pieces[currentRow][currentColumn].getType() 
                            == inPiece.getOpposite()) {
                        pieces[currentRow][currentColumn].flipType();
                        currentRow = currentRow + cellChange[0];
                        currentColumn = currentColumn + cellChange[1];
                    }
                }
            }
        }
    }
    // Returns if a given piece type has a possible move
    // Could be changed with a player class
    // Could also use int inType for parameter instead of a piece
    // if changed in legalMove as well
    public boolean hasMove(ReversiPiece inPiece) {
        int row, column;
        for (row = 0; row < size; row++) {
            for (column = 0; column < size; column++) {
                if (legalMove(row, column, inPiece) == true) {
                    return true;
                }
            }
        }
        return false;
    }
    // Checks if either player has a possible move
    // Could be changed with a player class
    public boolean isDone() {
        if (hasMove(player1) == true || hasMove(player2) == true){
            return false;
        } else {
            return true;
        }
    }
    // Returns the piece type with the most pieces
    public int getWinner() {
        int row, column;
        int whiteCount, blackCount;
        whiteCount = 0;
        blackCount = 0;
        for (row = 0; row < size; row++) {
            for (column = 0; column < size; column++) {
                if (pieces[row][column].getType() == ReversiPiece.WHITE) {
                    whiteCount++;
                }
                if (pieces[row][column].getType() == ReversiPiece.BLACK) {
                    blackCount++;
                }
            }
        }
        if (whiteCount > blackCount) {
            return ReversiPiece.WHITE;
        } 
        if (blackCount > whiteCount) {
            return ReversiPiece.BLACK;
        }
        // Returns blank if they are tied. 
        return ReversiPiece.BLANK;
    }
}