package chess;

public final class Position {
	private final int row;
	private final int col;
	
	public Position(int row, int col) {
		if (row < 0 || row > 7 || col < 0 || col > 7) {
			throw new IllegalArgumentException("Out of bounds: row=" + row + ", col=" + col);
		}
		this.row = row;
		this.col = col;
	}
	
	public int row() {
		return row;
	}
	
	public int col() {
		return col;
	}
	
	
	public static Position fromAlgebraic (String s) {
		if (s== null || s.length() != 2) {
			throw new IllegalArgumentException("Bad square:" + s);
		}
		char file = Character.toLowerCase(s.charAt(0)); 	// a..h
		char rank = s.charAt(1);							// 1..8
		
		int col = file - 'a';
		int rankNumber = rank - '0'; 						// '2' -> 2
		int row = 8 - rankNumber;							// rank 8 -> row 0, rank 1 -> row 7
		
		
		return new Position(row,col);					// nutzt Bounds-Prüfung im Konstruktor
	}
	
	public String toAlgebraic() {
		char file = (char) ('a' + col);
		char rank = (char) ('0' + (8 - row));
		return "" + file + rank;
	}
	
	@Override
	public String toString() {
		return toAlgebraic();
	}
	
	// bitte nochmal tiefer gehen
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (!(o instanceof Position)) return false;
	    Position other = (Position) o;
	    return row == other.row && col == other.col;
	}

	// bitte nochmal tiefer gehen
	@Override
	public int hashCode() {
	    return 31 * row + col;
	}
	

}
