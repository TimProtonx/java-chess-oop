package chess;

public class Board {

	private final Piece[][] squares = new Piece[8][8];
	
	public Piece get(Position pos) {
		return squares[pos.row()][pos.col()];
	}
	
	public void set(Position pos, Piece piece) {
		squares[pos.row()][pos.col()] = piece;
	}
	
	public boolean isEmpty(Position pos) {
		return get(pos) == null;
	}
	
	public void print() {
		for (int row = 0; row < 8; row++) {
			System.out.print((8- row) + " ");
			for (int col = 0; col < 8; col++) {
				Piece p = squares[row][col];
				System.out.print((p == null ? ". " : p.symbol() + " "));
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	public void clear(Position pos) { 
		set(pos, null); 
		}

	
	public void move(Position from, Position to) {
		Piece moving = get(from);
		if (moving == null) {
			throw new IllegalStateException("No piece on " + from);
		}
		if(!moving.canMove(this,  from,  to)) {
			throw new IllegalArgumentException("Illegal move for " + moving.symbol() + ": " + from + " -> " + to);
		}
		Piece target = get(to);
		if (target != null && target.color() == moving.color()) {
			throw new IllegalArgumentException("Cannot move onto own piece: " + to);
		}
		 // --- En passant capture: Ziel ist leer, aber to == enPassantTarget und moving ist Pawn ---
	    if (moving instanceof Pawn && target == null && enPassantSquare != null && enPassantSquare.equals(to)) {
	        int dir = (moving.color() == Color.WHITE) ? -1 : 1;
	        Position capturedPawnPos = new Position(to.row() - dir, to.col()); // Feld "hinter" dem Ziel
	        clear(capturedPawnPos);
	    }
		
		// Zug ausführen
		set(to, moving);
		clear(from);
		
		 //  enPassantSquare aktualisieren
	    enPassantSquare = null;
	    
	    // Wenn ein Bauer 2 Felder vor ist, wird das übersprungene Feld en-passant-fähig
	    if (moving instanceof Pawn) {
	        int dr = to.row() - from.row();
	        if (Math.abs(dr) == 2) {
	            int midRow = (from.row() + to.row()) / 2;
	            enPassantSquare = new Position(midRow, from.col());
	        }
	    }
	}
	
	private Position enPassantSquare = null;
	
	public Position enPassantSquare() {
		return enPassantSquare;
	}
	
	public Position findKing(Color color) {
		for (int r  = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				Piece p = squares[r][c];
				if (p instanceof King && p.color() == color) {
					return new Position(r, c);
				}
			}
		}
		throw new IllegalStateException("King not found for " + color);
	}
	
	public boolean isSquareAttacked(Position target, Color byColor) {
		 for (int r = 0; r < 8; r++) {
		        for (int c = 0; c < 8; c++) {
		            Piece p = squares[r][c];
		            if (p == null || p.color() != byColor) continue;

		            Position from = new Position(r, c);
		            if (attacksSquare(p, from, target)) return true;
		        }
		    }
		    return false;
	}
	
	
	private boolean attacksSquare(Piece p, Position from, Position target) {
	    int dr = target.row() - from.row();
	    int dc = target.col() - from.col();

	    // Pawn: greift diagonal 1 Feld an (ohne zu prüfen, ob da eine Figur steht)
	    if (p instanceof Pawn) {
	        int dir = (p.color() == Color.WHITE) ? -1 : 1;
	        return dr == dir && Math.abs(dc) == 1;
	    }

	    // Knight: L-Sprung
	    if (p instanceof Knight) {
	        int ar = Math.abs(dr), ac = Math.abs(dc);
	        return (ar == 2 && ac == 1) || (ar == 1 && ac == 2);
	    }

	    // King: 1 Feld Umgebung
	    if (p instanceof King) {
	        int ar = Math.abs(dr), ac = Math.abs(dc);
	        return (ar <= 1 && ac <= 1) && !(ar == 0 && ac == 0);
	    }

	    // Rook/Bishop/Queen -> Strahlen prüfen
	    if (p instanceof Rook) {
	        return rayAttacks(from, target, Integer.compare(target.row(), from.row()),
	                                   Integer.compare(target.col(), from.col()),
	                                   true, false);
	    }
	    if (p instanceof Bishop) {
	        return rayAttacks(from, target, Integer.compare(target.row(), from.row()),
	                                   Integer.compare(target.col(), from.col()),
	                                   false, true);
	    }
	    if (p instanceof Queen) {
	        return rayAttacks(from, target, Integer.compare(target.row(), from.row()),
	                                   Integer.compare(target.col(), from.col()),
	                                   true, true);
	    }

	    return false;
	}

	private boolean rayAttacks(Position from, Position target, int drStep, int dcStep, boolean allowRookLike, boolean allowBishopLike) {
		int dr = target.row() - from.row();
		int dc = target.col() - from.col();
		
		boolean rookLike = (dr == 0 || dc == 0);
		boolean bishopLike = (Math.abs(dr) == Math.abs(dc));
		
		if (rookLike && !allowRookLike) return false;
		if (bishopLike && !allowBishopLike) return false;
		if (!rookLike && !bishopLike) return false;
		
		int r = from.row() + drStep;
		int c = from.col() + dcStep;
		
		while (r != target.row() || c != target.col()) {
			if (squares[r][c] != null) return false; // Blocker
				r += drStep;
				c += dcStep;
			}
			return true;
	}
	
	public boolean isInCheck(Color color) {
	    Position kingPos = findKing(color);
	    Color enemy = (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	    return isSquareAttacked(kingPos, enemy);
	}

	private Piece makeMoveRaw(Position from, Position to) {
	    Piece moving = get(from);
	    Piece captured = get(to);
	    squares[to.row()][to.col()] = moving;
	    squares[from.row()][from.col()] = null;
	    return captured;
	}

	private void undoMoveRaw(Position from, Position to, Piece captured) {
	    Piece moving = get(to);
	    squares[from.row()][from.col()] = moving;
	    squares[to.row()][to.col()] = captured;
	}


	public boolean isLegalMove(Position from, Position to) {
	    Piece moving = get(from);
	    if (moving == null) return false;

	    // Eigene Figur auf Ziel? verboten
	    Piece target = get(to);
	    if (target != null && target.color() == moving.color()) return false;

	    // Figurenregel (deine canMove-Regeln)
	    if (!moving.canMove(this, from, to)) return false;

	    // Probezug machen und prüfen, ob eigener König im Schach steht
	    Piece captured = makeMoveRaw(from, to);
	    boolean inCheck = isInCheck(moving.color());
	    undoMoveRaw(from, to, captured);

	    return !inCheck;
	}
	
	public boolean hasAnyLegalMove(Color color) {
	    for (int r1 = 0; r1 < 8; r1++) {
	        for (int c1 = 0; c1 < 8; c1++) {
	            Piece p = squares[r1][c1];
	            if (p == null || p.color() != color) continue;

	            Position from = new Position(r1, c1);

	            for (int r2 = 0; r2 < 8; r2++) {
	                for (int c2 = 0; c2 < 8; c2++) {
	                    Position to = new Position(r2, c2);
	                    
	                    if (isLegalMove(from, to) && !from.equals(to)) return true;
	                }
	            }
	        }
	    }
	    return false;
	}

	public boolean isCheckmate(Color color) {
	    return isInCheck(color) && !hasAnyLegalMove(color);
	}

	
}
