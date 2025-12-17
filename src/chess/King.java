package chess;

public class King extends Piece {
	public King (Color color) {super(color); }
	@Override public char symbol() {
		return color() == Color.WHITE ? 'K' : 'k';
	}
	
	@Override 
	public boolean canMove(Board board, Position from, Position to) {
		int dr = Math.abs(to.row() - from.row());
		int dc = Math.abs(to.col() - from.col());
		
		return (dr <= 1) && (dc <= 1) && !(dr == 0 && dc == 0);
			// König darf genau 1 Feld horizontal, vertikal oder diagonal
			// darf nicht stehen bleiben 0 ; 0
	}
}
