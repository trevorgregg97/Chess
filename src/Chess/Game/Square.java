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
    public String toString(){
        return "" + (char) (col + 97) + (row + 1);
    }
}
