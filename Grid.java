import java.awt.Color;


public class Grid {
	private Square[][] grid;
	private Piece[][] pieces;
	
	public Grid() {
		this.grid = new Square[8][8];
		this.pieces = new Piece[8][8];
		this.initialiseGrid();
		this.initialisePieces();
	}
	
	private Color invertColor(Color color) {
		if (color == Color.red) {
			return Color.black;
		}else if (color == Color.black) {
			return Color.red;
		}else {
			return null;
		}
	}
	
	private void initialiseGrid() {
		Color squareColor = Color.red;
		
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				this.grid[y][x] = new Square(x, y, squareColor);
				squareColor = this.invertColor(squareColor);
			}
			
			squareColor = this.invertColor(squareColor);
		}
	}
	
	private void initialisePieces() {
		//add 12 black pieces
		int x = 0;
		
		for (int y = 0; y < 3; y++) {
			for (int z = 0; z < 4; z ++) {
				Piece newPiece = new Piece(x, y, Color.black, false);
				this.pieces[y][x] = newPiece;
				x += 2;
			}
			
			if (y == 0) {
				x = 1;
			}else if (y == 1) {
				x = 0;
			}
		}
		
        //add 12 white pieces
		x = 1;
		
		for (int y = 7; y > 4; y--) {
			for (int z = 0; z < 4; z ++) {
				Piece newPiece = new Piece(x, y, Color.white, false);
				this.pieces[y][x] = newPiece;
				x += 2;
			}
			
			if (y == 7) {
				x = 0;
			}else if (y == 6) {
				x = 1;
			}
		}
	}
	
	public Square getSquare(int y, int x) {
		return this.grid[y][x];
	}
	
	public Piece getPiece(int y, int x) {
		return this.pieces[y][x];
	}
	
	public void setSpace(int y, int x, Piece piece) {
		this.pieces[y][x] = piece;
	}
}
