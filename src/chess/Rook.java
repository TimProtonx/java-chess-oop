package chess;

public class Rook extends Piece {
	public Rook (Color color) {super(color); }
	@Override public char symbol() {
		return color() == Color.WHITE ? 'R' : 'r';
	}
	
	@Override 
	public boolean canMove(Board board, Position from, Position to) {
		if (from.equals(to)) return false; 	// darf nicht stehen bleiben
		
		boolean sameRow = from.row() == to.row();
		boolean sameCol = from.col() == to.col();
		if (!sameRow && !sameCol) return false;
		
		// Richtung bestimmen
		int dr = Integer.compare(to.row(), from.row());
		int dc = Integer.compare(to.col(), from.col());
	
		// Alle Zwischenfelder prüfen
		int r = from.row() + dr;
		int c = from.col() + dc;
		while (r != to.row() || c != to.col()) {
			if (!board.isEmpty(new Position(r, c))) return false;
			r += dr;
			c += dc;
		}
		return true;
	}
}
