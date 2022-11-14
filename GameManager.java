
public class GameManager {
	private int selectedX;
	private int selectedY;
	private boolean isPieceSelected;
	private boolean isPlayer1;
	private int player1Pieces;
	private int player2Pieces;
	
	
	public GameManager() {
		this.setSelectedX(0);
		this.setSelectedX(0);
		this.setPieceSelected(false);
		this.setPlayer1(true);
	}


	public int getSelectedX() {
		return selectedX;
	}


	public void setSelectedX(int selectedX) {
		this.selectedX = selectedX;
	}


	public int getSelectedY() {
		return selectedY;
	}


	public void setSelectedY(int selectedY) {
		this.selectedY = selectedY;
	}


	public boolean isPieceSelected() {
		return isPieceSelected;
	}


	public void setPieceSelected(boolean isPieceSelected) {
		this.isPieceSelected = isPieceSelected;
	}


	public boolean isPlayer1() {
		return isPlayer1;
	}


	public void setPlayer1(boolean isPlayer1) {
		this.isPlayer1 = isPlayer1;
	}


	public int getPlayer1Pieces() {
		return player1Pieces;
	}


	public int getPlayer2Pieces() {
		return player2Pieces;
	}
	
	public void decrementPieces(boolean isPlayer1) {
		if (isPlayer1) {
			this.player1Pieces --;
		}else {
			this.player2Pieces --;
		}
	}
}
