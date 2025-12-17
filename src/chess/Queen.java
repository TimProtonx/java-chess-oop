package chess;

public class Queen extends Piece {

	public Queen (Color color) {super(color); }
	@Override public char symbol() {
		return color() == Color.WHITE ? 'Q' : 'q';
	}
	
	  @Override
	    public boolean canMove(Board board, Position from, Position to) {
	        if (from.equals(to)) return false;

	        int drAbs = Math.abs(to.row() - from.row());
	        int dcAbs = Math.abs(to.col() - from.col());

	        boolean rookLike = (drAbs == 0 || dcAbs == 0);	// gleiche Zeile oder gleiche Spalte
	        boolean bishopLike = (drAbs == dcAbs);			// 

	        if (!rookLike && !bishopLike) return false;

	        // Schritt-Richtung bestimmen
	        int dr = Integer.compare(to.row(), from.row());
	        int dc = Integer.compare(to.col(), from.col());

	        // Zwischenfelder prüfen (Zielfeld selbst nicht)
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
