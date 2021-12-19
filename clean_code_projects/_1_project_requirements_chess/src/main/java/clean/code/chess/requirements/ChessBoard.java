package clean.code.chess.requirements;

public class ChessBoard {

    public static int MAX_BOARD_WIDTH = 7;
    public static int MAX_BOARD_HEIGHT = 7;

    public Pawn[][] pieces;

    public ChessBoard() {
        pieces = new Pawn[MAX_BOARD_WIDTH+1][MAX_BOARD_HEIGHT+1];

    }

    public void updatePiecePositionOnChessBoard(Pawn pawn, int xCoordinate, int yCoordinate) {
        pawn.setXCoordinate(xCoordinate);
        pawn.setYCoordinate(yCoordinate);
        pieces[xCoordinate][yCoordinate] = pawn;
    }

    public void addPawn(Pawn pawn, int xCoordinate, int yCoordinate, PieceColor pieceColor) {
        if (IsLegalBoardPosition(xCoordinate, yCoordinate) &&
                (isValidRow(yCoordinate,PieceColor.WHITE) || isValidRow(yCoordinate,PieceColor.BLACK)) &&
                isFreePosition(xCoordinate,yCoordinate)){
            pawn.setXCoordinate(xCoordinate);
            pawn.setYCoordinate(yCoordinate);
            pawn.setChessBoard(this);
            pawn.setPieceColor(pieceColor);
            pieces[xCoordinate][yCoordinate] = pawn;
        }
        else{
            throw new UnsupportedOperationException("Exception at: IsLegalBoardPosition()/isValidRow()/isFreePosition()");
        }
    }

    public boolean isFreePosition(int xCoordinate, int yCoordinate){
        return pieces[xCoordinate][yCoordinate] == null;
    }

    public boolean isValidRow(int yCoordinate, PieceColor color){
        if (color == color.WHITE) {
            return yCoordinate == 0 || yCoordinate == 1;
        }
        return yCoordinate == MAX_BOARD_HEIGHT - 1 || yCoordinate == MAX_BOARD_HEIGHT;
    }

    public boolean IsLegalBoardPosition(int xCoordinate, int yCoordinate) {
        return xCoordinate >= 0 && yCoordinate >= 0 && xCoordinate <= MAX_BOARD_WIDTH && yCoordinate <= MAX_BOARD_HEIGHT;
    }
}
