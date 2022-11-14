import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

class ButtonActionListener implements ActionListener {
	private int x;
	private int y;
	private GameManager gameManager;
	private Grid gameGrid;
	private JLabel label;

	public ButtonActionListener(int x, int y, Grid gameGrid, GameManager gameManager, JLabel label) {
		this.x = x;
		this.y = y;
		this.gameGrid = gameGrid;
		this.gameManager = gameManager;
		this.label = label;
	}

	private void changeTurn(boolean isPlayer1) {
		// change turn to other player
		this.gameManager.setPieceSelected(false);
		this.gameManager.setPlayer1(!isPlayer1);

		if (this.gameManager.isPlayer1()) {
			this.label.setText("Turn: Player1");
		} else {
			this.label.setText("Turn: Player2");
		}
	}

	public void actionPerformed(ActionEvent e) {
		Piece selectedPiece = this.gameGrid.getPiece(this.y, this.x);
		Square selectedSquare = this.gameGrid.getSquare(this.y, this.x);

		int previousX = this.gameManager.getSelectedX();
		int previousY = this.gameManager.getSelectedY();
		Square previousSquare = this.gameGrid.getSquare(previousY, previousX);
		Piece previousPiece = this.gameGrid.getPiece(previousY, previousX);
		boolean isPlayer1 = this.gameManager.isPlayer1();

		if (this.gameManager.isPieceSelected()) {
			// if player clicks on selected piece again un-select it
			if (previousX == this.x && this.y == previousY) {
				this.gameManager.setPieceSelected(false);
				selectedSquare.getJButton().setBackground(selectedSquare.getColor());
			} else {
				// move piece to new location only if it is valid
				boolean isYValid = false;

				if (isPlayer1 && previousY - this.y == 1 || !isPlayer1 && this.y - previousY == 1) {
					isYValid = true;
				}

				if (Math.abs(this.x - previousX) == 1 && isYValid) {
					if (selectedPiece == null) {
//						//check if piece has reached the end
//						if (selectedPiece.getColor() == Color.white && this.y == 0) {
//							System.out.println("White reached end");
//							
//							//add queen piece to white 							
//						}
//						
//						if (selectedPiece.getColor() == Color.black && this.y == 7) {
//							System.out.println("Black reached end");
//							//add queen piece to black							
//						}

						// make changes to grid array
						this.gameGrid.setSpace(this.y, this.x, previousPiece);
						this.gameGrid.setSpace(previousY, previousX, null);

						// make changes to buttons
						previousSquare.getJButton().setIcon(null);
						selectedSquare.getJButton().setIcon(previousPiece);
						previousSquare.getJButton().setBackground(previousSquare.getColor());

						this.changeTurn(isPlayer1);
					} else {
						// handle piece take overs
						if (previousPiece.getColor() != selectedPiece.getColor()) {
							int dx = previousX - this.x;
							int dy = previousY - this.y;

							Piece neighbouringPiece = this.gameGrid.getPiece(previousY - (2 * dy),
									previousX - (2 * dx));
							Square neighbouringSquare = this.gameGrid.getSquare(previousY - (2 * dy),
									previousX - (2 * dx));

							if (neighbouringPiece == null) {
								// change grid
								this.gameGrid.setSpace(this.y, this.x, null);
								this.gameGrid.setSpace(previousY, previousX, null);
								this.gameGrid.setSpace(previousY - (2 * dy), previousX - (2 * dx), previousPiece);

								// change JButtons to display changes
								selectedSquare.getJButton().setIcon(null);
								previousSquare.getJButton().setBackground(selectedSquare.getColor());
								previousSquare.getJButton().setIcon(null);
								neighbouringSquare.getJButton().setIcon(previousPiece);

								this.gameManager.decrementPieces(!isPlayer1);
								this.gameManager.setPieceSelected(false); // turn does not change
							}
						}
					}
				} else {
					// handle take overs
					System.out.println("Invalid location");
				}
			}

		} else {
			if (selectedPiece != null) {
				Color selectedColor = selectedPiece.getColor();
				if ((selectedColor == Color.white && this.gameManager.isPlayer1())
						|| (selectedColor == Color.black && !this.gameManager.isPlayer1())) {
					this.gameManager.setSelectedX(this.x);
					this.gameManager.setSelectedY(this.y);
					this.gameManager.setPieceSelected(true);

					// change color of selected square to green
					selectedSquare.getJButton().setBackground(Color.green);
					previousSquare.getJButton().setBackground(previousSquare.getColor());
				}
			}
		}

		if (this.gameManager.getPlayer1Pieces() == 0 || this.gameManager.getPlayer2Pieces() == 0) {
			this.label.setText("Game Over");

			// TODO: Add better game over menu
		}
	}
}

public class GameBoard extends JFrame {
	private static final long serialVersionUID = 1L;

	public GameBoard(Grid gameGrid, GameManager gameManager) {
		// window configurations
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Java Checkers");
		setSize(600, 600);
		setResizable(false);

		// create main panel
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.black);
		mainPanel.setLayout(new BorderLayout());

		// Add label at the top of screen
		JLabel turn = new JLabel("Turn: Player1", SwingConstants.CENTER);
		turn.setForeground(Color.white);
		turn.setFont(new Font("Arial", 20, 20));

		mainPanel.add(turn, BorderLayout.NORTH);

		// add panel with the board buttons
		JPanel board = new JPanel();
		GridLayout gridLayout = new GridLayout(8, 8);
		board.setLayout(gridLayout);

		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				Square currentSquare = gameGrid.getSquare(y, x);
				Piece piece = gameGrid.getPiece(y, x);
				JButton squareButton = currentSquare.getJButton();
				squareButton.addActionListener(new ButtonActionListener(x, y, gameGrid, gameManager, turn));

				if (piece != null) {
					squareButton.setIcon(piece);
				}

				board.add(squareButton);
			}
		}

		mainPanel.add(board);
		add(mainPanel);
	}
}
