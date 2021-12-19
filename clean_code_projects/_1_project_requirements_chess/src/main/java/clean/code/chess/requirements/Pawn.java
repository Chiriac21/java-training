package clean.code.chess.requirements;

public class Pawn {

    private ChessBoard chessBoard;
    private int xCoordinate;
    private int yCoordinate;
    private PieceColor pieceColor;
    private boolean isFirstMove = true;

    public Pawn(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
    }

    public ChessBoard getChesssBoard() {
        return chessBoard;
    }

    public boolean isFirstMove() { return isFirstMove;}

    public void setChessBoard(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public void setXCoordinate(int value) {
        this.xCoordinate = value;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public void setYCoordinate(int value) {
        this.yCoordinate = value;
    }

    public PieceColor getPieceColor() {
        return this.pieceColor;
    }

    public void setPieceColor(PieceColor value) {
        pieceColor = value;
    }

    private int getMovementDirection(){
        if(this.getPieceColor() == PieceColor.WHITE) {
            return +1;
        }
        return -1;
    }

    public boolean isValidnewX(int newX){
        return newX == getXCoordinate();
    }

    public boolean isValidnewY(int newY){
        return newY == getYCoordinate() + getMovementDirection() ||
                newY == getYCoordinate() ||
                (isFirstMove && newY == getYCoordinate() + 2 * getMovementDirection());
    }

    public boolean isValidNewPosition(int newX, int newY) {
        return this.isValidnewX(newX) && this.isValidnewY(newY);
    }


    public void Move(MovementType movementType, int newX, int newY) {
        if(isValidNewPosition(newX,newY) && this.chessBoard.isFreePosition(newX,newY)){
                this.chessBoard.updatePiecePositionOnChessBoard(this, newX, newY);
            }
            isFirstMove = false;
    }

    @Override
    public String toString() {
        return CurrentPositionAsString();
    }

    protected String CurrentPositionAsString() {
        String eol = System.lineSeparator();
        return String.format("Current X: {" + xCoordinate + "} Current Y: {" + yCoordinate +"} Piece Color: {" +pieceColor+ "}");
    }
}
