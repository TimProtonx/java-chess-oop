package chess;

public class Bishop extends Piece {

	public Bishop (Color color) {super(color); }
	@Override public char symbol() {
		return color() == Color.WHITE ? 'B' : 'b';
	}
	
	@Override 
	public boolean canMove(Board board, Position from, Position to) {
		if (from.equals(to)) return false; 	// darf nicht stehen bleiben
		
		int dr = to.row() - from.row();
        int dc = to.col() - from.col();
		
		// diagonal bedeutet: |dr| == |dc|
        if (Math.abs(dr) != Math.abs(dc)) return false;
		
		// Richtung bestimmen
		int stepR = Integer.compare(to.row(), from.row());
		int stepC = Integer.compare(to.col(), from.col());
		
		// Alle Zwischenfelder prüfen
		int r = from.row() + stepR;
		int c = from.col() + stepC;
		while (r != to.row() || c != to.col()) {
			if (!board.isEmpty(new Position(r, c))) return false;
			r += stepR;
			c += stepC;
		}
		return true;
	}
}
