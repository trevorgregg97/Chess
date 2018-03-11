package Chess.Game;
import Chess.Pieces.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Board {
	private Square delStart, delEnd;
	private Piece delPiece;
	public Piece[][] board;
	public Square whiteKing;
	public Square blackKing;
	public boolean isWhiteTurn;

	public enum Color {
		WHITE, BLACK;
	}
	
	public Board() {
		board = new Piece[8][8];
		whiteKing = new Square(0,4);
		blackKing = new Square(7,4);
        fillBoard(board);
        isWhiteTurn = true;
	}
	
	private Board(Piece[][] board, Piece delPiece, Square delStart, Square delEnd,boolean isWhiteTurn) {
		this.board = board;
		this.delPiece = delPiece;
		this.delEnd = delEnd;
		this.delStart = delStart;
		this.isWhiteTurn = isWhiteTurn;
	}
	
	private void fillBoard(Piece[][] board) {
		for(int i = 0; i < 8; i++) {
			board[1][i] = new Pawn(Color.WHITE);
			board[6][i] = new Pawn(Color.BLACK);
		}
		board[0][4] = new King(Color.WHITE);
		board[7][4] = new King(Color.BLACK);
	}
	
	public void undoMove() {
	    int delRowStart = delStart.row;
	    int delColStart = delStart.col;
	    int delRowEnd = delEnd.row;
	    int delColEnd = delEnd.col;
		Piece temp = board[delRowEnd][delColEnd];
		if(delPiece != null) {
			board[delRowEnd][delColEnd] = delPiece;
		}else{
		    board[delRowEnd][delColEnd] = null;
        }
		board[delRowStart][delColStart] = temp;
		isWhiteTurn = !isWhiteTurn;
		if(temp.toString().equals("k")){
		    blackKing = delStart;
        }else if(temp.toString().equals("K")){
		    whiteKing = delStart;
        }
	}
	
	public boolean isLegal(Move move, boolean isGeneratingThreat) {
		Piece pieceToMove = board[move.start.row][move.start.col];
		move.piece = pieceToMove;
		if(pieceToMove == null) {
			return false;
		}
		//Check if already in Check
        if(!isGeneratingThreat){
            if(inCheck(whiteKing) || inCheck(blackKing)){
                return false;
            }
            //Check if this move would put themselves into check
            applyMove(move,false);
            Square checkForCheckKing = isWhiteTurn ? whiteKing : blackKing;
            if(inCheck(checkForCheckKing)){
                undoMove();
                return false;
            }
            undoMove();
        }
		return true;
	}

	public boolean inCheck(Square kingPos){
	    if(generateThreats().contains(kingPos)){
	        System.out.println("In check!");
	        return true;
        }
        return false;
    }

	public boolean applyMove(Move move,boolean checkIfLegal) {
		if(checkIfLegal && !isLegal(move,false)) {
			return false;
		}
        int rowStart = move.start.row;
		int colStart = move.start.col;
		int rowEnd = move.end.row;
		int colEnd = move.end.col;

		delPiece = board[rowEnd][colEnd];
		delStart = new Square(rowStart,colStart);
		delEnd = new Square(rowEnd,colEnd);
		
		board[rowStart][colStart] = null;
		board[rowEnd][colEnd] = move.piece;
		move.piece.hasMoved = true;
		isWhiteTurn = !isWhiteTurn;
		if(move.start.equals(whiteKing)){
			whiteKing = move.end;
		}
		if(move.start.equals(blackKing)){
			blackKing = move.end;
		}
		return true;
	}

	public Set<Square> generateThreats(){
	    Set<Square> threats = new HashSet<>();
	    for(int i = 0; i < 8; i++){
	        for(int j = 0; j < 8; j++){
	            //TODO LOOK AT THIS MAYBE WRONG COLOR?
                
	            if(board[i][j] != null && board[i][j].color == (isWhiteTurn ? Color.BLACK : Color.WHITE)){
                    List<Move> pieceThreatsList =  board[i][j].generateThreatenedSquares(new Square(i,j), board);
                    for(int k = 0; k < pieceThreatsList.size(); k++){
                        if(isLegal(pieceThreatsList.get(k),true)){
                            threats.add(pieceThreatsList.get(k).end);
                        }
                    }
                }
            }
        }
        System.out.println(threats);
        return threats;
    }

	public Board copy() {
		Piece[][] copiedPieces = new Piece[8][8];
		for(int i = 0 ; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				copiedPieces[i][j] = board[i][j];
			}
		}
		Piece newDelPiece = null;
		if(delPiece != null) {
			newDelPiece = delPiece.copy();
		}
		return new Board(copiedPieces, newDelPiece,delStart, delEnd,isWhiteTurn);
	}
	
	@Override
	public String toString() {
		// Change to FULLY implement FEN
		String ret = "";
		for(int i = 7; i >= 0; i--) {
			int count = 0;
			for(int j = 0; j  < 8; j++) {
				if(board[i][j] == null) {
					count += 1;
				}else if(count > 0) {
					ret += count +"" + board[i][j];
					count = 0;
				}else {
					ret += board[i][j];
				}
			}
			if(count != 0) {
				ret += count;
			}
			ret += "/";
		}
		return ret;
	}
	
}
