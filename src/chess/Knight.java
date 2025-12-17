package chess;

public class Knight extends Piece {

	public Knight (Color color) {super(color); }
	@Override public char symbol() {
		return color() == Color.WHITE ? 'N' : 'n';
	}
	
	@Override 
	public boolean canMove(Board board, Position from, Position to) {
		if (from.equals(to)) return false;
		int dr = Math.abs(to.row() - from.row());
		int dc = Math.abs(to.col() - from.col());
		
		// L-Form: (2,1) oder (1,2)
		return (dr == 2 && dc == 1) || (dr == 1 && dc == 2);
		
	}
}
