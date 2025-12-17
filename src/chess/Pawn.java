package chess;

public class Pawn extends Piece {

	public Pawn (Color color) {super(color); }
	@Override public char symbol() {
		return color() == Color.WHITE ? 'P' : 'p';
	}
	
	@Override 
	public boolean canMove(Board board, Position from, Position to) {
		if (from.equals(to)) return false;
		
		int direction = (color() == Color.WHITE) ? -1 : 1;
		int startRow = (color() == Color.WHITE) ? 6 : 1;
		
		int dr = to.row() - from.row();
		int dc = Math.abs(to.col() - from.col());
		
		// 1 Schritt worwärts 
		if (dc == 0 && dr == direction) {
			return board.isEmpty(to); 		// darf nur auf leeres Feld
		}
		
		// 2 Schritte vorwärts
		if (dc == 0 && dr == 2 * direction && from.row() == startRow) {
			Position between = new Position(from.row() + direction, from.col());
			return board.isEmpty(between) && board.isEmpty(to);
		}
		
		
		// Schlagen diagonal (1 Feld)
		if (dc == 1 && dr == direction) {
			Piece target = board.get(to);
			return target != null && target.color() != color();
		}
		
		// En passant: diagonal auf ein leeres Feld, wenn es das enPassantTarget ist
		if (dc == 1 && dr == direction) {
		    if (board.enPassantSquare() != null && board.enPassantSquare().equals(to) && board.isEmpty(to)) {
		        return true;  
		    }
		}
		   
		return false;	
	}
}
