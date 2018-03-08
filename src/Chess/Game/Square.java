package Chess.Game;

public class Square {
    public int row;
    public int col;

    public Square(int row, int col){
        this.row = row;
        this.col = col;
    }

    @Override
    public int hashCode(){
        return row * 10 + col;
    }

    @Override
    public boolean equals(Object square){
        if (this == square)
            return true;
        if (square == null)
            return false;
        if (getClass() != square.getClass())
            return false;
        Square other = (Square) square;
        return other.col == col && other.row == row;
    }

    @Override
    public String toString(){
        return "" + (char) (col + 97) + (row + 1);
    }
}
