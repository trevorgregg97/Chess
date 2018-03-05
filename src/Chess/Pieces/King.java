package Chess.Pieces;

import Chess.Game.Board;
import Chess.Game.Move;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece{

    public King(Board.Color color) {
        super(color);
        value = color == Board.Color.WHITE ? 3 : -3;
    }

    @Override
    public List<Move> generateMoves(int row, int col, Piece[][] board) {
        //TODO ADD CAPTURING
        List<Move> moves = new ArrayList<Move>(4);
        if(row + 1 < 8 && board[row+1][col] == null){
            Move oneForward = new Move(row,col,row+1,col,board);
            moves.add(oneForward);
        }
        if(col + 1 < 8 && board[row][col+1] == null){
            Move oneRight = new Move(row, col, row, col + 1, board);
            moves.add(oneRight);
        }
        if(row - 1 > -1 && board[row-1][col] == null){
            Move oneBack = new Move(row, col, row - 1, col, board);
            moves.add(oneBack);
        }
        if(col - 1 > -1 && board[row][col - 1] == null){
            Move oneLeft = new Move(row, col, row, col - 1, board);
            moves.add(oneLeft);
        }
        for(int i = 0; i < moves.size(); i++){
            moves.get(i).piece = this;
        }
        return moves;
    }

    @Override
    public Piece copy() {
        King king = new King(color);
        king.hasMoved = hasMoved;
        king.value = value;
        return king;
    }

    @Override
    public String toString(){
        return color == Board.Color.BLACK ? "k" : "K";
    }
}
