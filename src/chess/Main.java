package chess;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Board board = new Board();

		board.set(Position.fromAlgebraic("a8"), new Rook(Color.BLACK));
		board.set(Position.fromAlgebraic("b8"), new Knight(Color.BLACK));
		board.set(Position.fromAlgebraic("c8"), new Bishop(Color.BLACK));
		board.set(Position.fromAlgebraic("d8"), new Queen(Color.BLACK));
		board.set(Position.fromAlgebraic("e8"), new King(Color.BLACK));
		board.set(Position.fromAlgebraic("f8"), new Bishop(Color.BLACK));
		board.set(Position.fromAlgebraic("g8"), new Knight(Color.BLACK));
		board.set(Position.fromAlgebraic("h8"), new Rook(Color.BLACK));
		board.set(Position.fromAlgebraic("a7"), new Pawn(Color.BLACK));
		board.set(Position.fromAlgebraic("b7"), new Pawn(Color.BLACK));
		board.set(Position.fromAlgebraic("c7"), new Pawn(Color.BLACK));
		board.set(Position.fromAlgebraic("d7"), new Pawn(Color.BLACK));
		board.set(Position.fromAlgebraic("e7"), new Pawn(Color.BLACK));
		board.set(Position.fromAlgebraic("f7"), new Pawn(Color.BLACK));
		board.set(Position.fromAlgebraic("g7"), new Pawn(Color.BLACK));
		board.set(Position.fromAlgebraic("h7"), new Pawn(Color.BLACK));
		
		board.set(Position.fromAlgebraic("a1"), new Rook(Color.WHITE));
		board.set(Position.fromAlgebraic("b1"), new Knight(Color.WHITE));
		board.set(Position.fromAlgebraic("c1"), new Bishop(Color.WHITE));
		board.set(Position.fromAlgebraic("d1"), new Queen(Color.WHITE));
		board.set(Position.fromAlgebraic("e1"), new King(Color.WHITE));
		board.set(Position.fromAlgebraic("f1"), new Bishop(Color.WHITE));
		board.set(Position.fromAlgebraic("g1"), new Knight(Color.WHITE));
		board.set(Position.fromAlgebraic("h1"), new Rook(Color.WHITE));
		board.set(Position.fromAlgebraic("a2"), new Pawn(Color.WHITE));
		board.set(Position.fromAlgebraic("b2"), new Pawn(Color.WHITE));
		board.set(Position.fromAlgebraic("c2"), new Pawn(Color.WHITE));
		board.set(Position.fromAlgebraic("d2"), new Pawn(Color.WHITE));
		board.set(Position.fromAlgebraic("e2"), new Pawn(Color.WHITE));
		board.set(Position.fromAlgebraic("f2"), new Pawn(Color.WHITE));
		board.set(Position.fromAlgebraic("g2"), new Pawn(Color.WHITE));
		board.set(Position.fromAlgebraic("h2"), new Pawn(Color.WHITE));
	
		startGame(board);
		
	}
	
	
	
	static void startGame(Board board) {
	    Scanner scanner = new Scanner(System.in);
	    Color currentPlayer = Color.WHITE;

	    while (true) {
	        board.print();
	        System.out.println();
	        if (board.isCheckmate(currentPlayer)) 
	        {System.out.println("MaTTTT");}
	        System.out.println(currentPlayer + " - Wähle deinen Zug:");

	        boolean moved = false;
	        while (!moved) {
	            try {
	                String fromStr = scanner.next();
	                String toStr = scanner.next();

	                Position from = Position.fromAlgebraic(fromStr);
	                Position to = Position.fromAlgebraic(toStr);

	                if (!board.isLegalMove(from, to)) {
	                    System.out.println("Illegaler Zug. Bitte erneut versuchen:");
	                    continue;
	                }

	                board.move(from, to);
	                moved = true;

	                Color opponent = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;

	                if (board.isInCheck(opponent)) {
	                    if (board.isCheckmate(opponent)) { 
	                        System.out.println("Schachmatt! " + opponent + " ist schachmatt. " + currentPlayer + " gewinnt.");
	                        board.print();
	                        return; 
	                    } else {
	                        System.out.println("Schach! " + opponent + " steht im Schach.");
	                    }
	                }

	                currentPlayer = opponent; 
	            } catch (IllegalArgumentException | IllegalStateException e) {
	                System.out.println(e.getMessage());
	                System.out.println("Bitte einen legalen Zug eingeben:");
	            } catch (Exception e) {
	                System.out.println("Ungültige Eingabe. Bitte erneut versuchen:");
	                scanner.nextLine();
	            }
	        }
	    }
	}

	

}
