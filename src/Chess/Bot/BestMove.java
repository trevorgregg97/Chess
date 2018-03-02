package Chess.Bot;

public class BestMove<M> {
	public M move;
    public int value;
    
    public BestMove(int value) {
        this.value = value;
    }

    public BestMove(M move, int value) {
        this.move = move;
        this.value = value;
    }
    
    public BestMove<M> negate() {
        this.value = -this.value;
        return this;
    }

}
