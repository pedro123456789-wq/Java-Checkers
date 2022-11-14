//@author: Pedro Lourenco
//TODO:
//Add queen piece when piece reaches end
//Add special movement for queen piece
//Add Game over screen


public class CheckersGame {
	public static void main(String[] args) {
		Grid gameGrid = new Grid();
		GameManager gameManager = new GameManager();
		GameBoard board = new GameBoard(gameGrid, gameManager);
		board.setVisible(true);
	}
}
