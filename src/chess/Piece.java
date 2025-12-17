package chess;

public abstract class Piece {
	private final Color color;
	
	protected Piece(Color color) {
		this.color = color;
	}
	
	public Color color() {
		return color;
	}
	
	public abstract char symbol();
	
	public abstract boolean canMove(Board board, Position from, Position to);
}
