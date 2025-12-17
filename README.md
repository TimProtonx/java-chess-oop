# Chess – Object-Oriented Java Project

This project is an object-oriented chess game implemented in Java.
It was created as a learning project to practice Java basics and object-oriented programming (OOP).

---

## Project Goal

* Practice object-oriented programming in Java
* Use core OOP concepts like inheritance, encapsulation, and polymorphism
* Model game logic in a clean and structured way

This is a learning project, not a professional chess engine.

---

## Technologies

* Java
* Console-based application
* No external libraries

---

## Project Structure (Overview)

```
src/chess/
├── Main.java         // Game loop (console input)
├── Board.java        // Represents the chess board & logik
├── Piece.java        // Abstract base class for all pieces
├── Color.java         
├── Position.java
├── Pawn.java
├── Knight.java         
├── Bishop.java
├── Rook.java
├── Queen.java
└── King.java

```

### Object-Oriented Design

* `Piece` is an abstract base class
* Each chess piece has its own `canMove(...)` logic
* `Board` manages the game state
* `Main` controls the game flow

---

## Current Features

*  Basic movement rules for all pieces
*  Move validation per piece
*  Turn-based gameplay
*  Simple console interface

---

## Work in Progress

This project is still under development.
Planned improvements:


* Pawn promotion
* En passant
* Code refactoring and improvements
* Graphical user interface (GUI)

---

## Motivation

Chess is well suited for learning OOP because it has:

* Clear rules
* Different piece behaviors
* Complex logic that fits object-oriented design

The goal was to learn and practice, not to create perfect code.

---

## How to Run

1. Open the project in a Java IDE (e.g. IntelliJ IDEA or Eclipse)
2. Run `Main.java`
3. Enter moves via the console 
    - (e.g e2 e4) -> move Pawn from e2 to e4

---

