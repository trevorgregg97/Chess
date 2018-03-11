package Chess.Pieces;

import Chess.Game.Board;
import Chess.Game.Move;
import Chess.Game.Square;

import java.util.LinkedList;
import java.util.List;

public class King extends Piece{

    public King(Board.Color color) {
        super(color);
        value = color == Board.Color.WHITE ? 3 : -3;
    }

    @Override
    public List<Move> generateMoves(Square pos, Piece[][] board) {
        int row = pos.row;
        int col = pos.col;
        //TODO ADD CAPTURING AND 4 OTHER SPOTS KING CAN MOVE TO
        List<Move> moves = new LinkedList<>();
        if(row + 1 < 8 && board[row+1][col] == null){
            Move oneForward = new Move(pos,new Square(row+1,col),board);
            moves.add(oneForward);
        }
        if(col + 1 < 8 && board[row][col+1] == null){
            Move oneRight = new Move(pos, new Square(row,col + 1), board);
            moves.add(oneRight);
        }
        if(row - 1 > -1 && board[row-1][col] == null){
            Move oneBack = new Move(pos, new Square(row - 1, col), board);
            moves.add(oneBack);
        }
        if(col - 1 > -1 && board[row][col - 1] == null){
            Move oneLeft = new Move(pos, new Square(row, col - 1), board);
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
    public List<Move> generateThreatenedSquares(Square pos, Piece[][] board) {
        List<Move> threats = new LinkedList<>();
        //Just manually adding every square the king can attack
        threats.add(new Move(pos,new Square(pos.row,pos.col + 1),board,this));
        threats.add(new Move(pos,new Square(pos.row + 1, pos.col),board,this));
        threats.add(new Move(pos,new Square(pos.row - 1, pos.col),board,this));
        threats.add(new Move(pos,new Square(pos.row,pos.col - 1),board,this));
        threats.add(new Move(pos,new Square(pos.row + 1, pos.col + 1),board,this));
        threats.add(new Move(pos,new Square(pos.row - 1, pos.col -1),board,this));
        threats.add(new Move(pos,new Square(pos.row + 1, pos.col -1),board,this));
        threats.add(new Move(pos,new Square(pos.row - 1, pos.col + 1),board,this));
        return threats;
    }

    @Override
    public String toString(){
        return color == Board.Color.BLACK ? "k" : "K";
    }
}
