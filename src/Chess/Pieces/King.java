package Chess.Pieces;

import Chess.Game.Board;
import Chess.Game.Move;
import Chess.Game.Square;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class King extends Piece{

    public King(Board.Color color) {
        super(color);
        value = color == Board.Color.WHITE ? 3 : -3;
    }

    @Override
    public List<Move> generateMoves(Square pos, Board gameBoard) {
        Piece[][] board = gameBoard.board;
        int row = pos.row;
        int col = pos.col;
        //TODO ADD CAPTURING AND 4 OTHER SPOTS KING CAN MOVE TO
        List<Move> moves = new ArrayList<Move>(4);
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
    public Set<Square> generateThreatenedSquares(Square pos, Piece[][] board) {
        Set<Square> threats = new HashSet<>();
        //Just manually adding every square the king can attack
        threats.add(new Square(pos.row,pos.col + 1));
        threats.add(new Square(pos.row + 1, pos.col));
        threats.add(new Square(pos.row - 1, pos.col));
        threats.add(new Square(pos.row,pos.col - 1));
        threats.add(new Square(pos.row + 1, pos.col + 1));
        threats.add(new Square(pos.row - 1, pos.col -1));
        threats.add(new Square(pos.row + 1, pos.col -1));
        threats.add(new Square(pos.row - 1, pos.col + 1));
        return threats;
    }

    @Override
    public String toString(){
        return color == Board.Color.BLACK ? "k" : "K";
    }
}
