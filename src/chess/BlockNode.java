/**
 * @author Xiang Ao, Shijie Xu
 * @since Mar.4, 2019
 * 
 * This class is the basic block in board.
 * .
 * CS213 Software Methodology Project 2: A playable chess.
 */
package chess;

public class BlockNode {
	private int letter;
	private int number;
	private String color;
	private Pieces piece;
	private boolean check;
	
	/**
	 * BlockNode constructor
	 * @param letter	The letter of block. Vertical order.
	 * @param number	The number of block. Horizontal order.
	 * @param color		The Color of block. White or black.
	 */
	public BlockNode(int letter, int number, String color) {
		this.letter = letter;
		this.number = number;
		this.color = color;
	}

	/**
	 * Get Letter
	 * @return letter
	 */
	public int getLetter() {
		return letter;
	}

	/**
	 * Set Letter
	 * @param letter set piece column location to letter
	 */
	public void setLetter(int letter) {
		this.letter = letter;
	}

	/**
	 * Get Number
	 * @return number get current piece's row location
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * Set Number
	 * @param number set piece row location to number
	 */
	public void setNumber(int number) {
		this.number = number;
	}
	
	/**
	 * Get Color
	 * @return color get piece's color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Set Color
	 * @param color	set piece's color to color
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * Get Piece
	 * @return piece get current piece
	 */
	public Pieces getPiece() {
		return piece;
	}

	/**
	 * Set Piece
	 * @param piece set piece
	 */
	public void setPiece(Pieces piece) {
		this.piece = piece;
	}
	/**
	 * check if current piece is check
	 * @return	true if piece is check
	 */
	public boolean isCheck() {
		return check;
	}
	/**
	 * set current piece's check status
	 * @param check	check status
	 */
	public void setCheck(boolean check) {
		this.check = check;
	}
	
	/**
	 * Print out piece's params.
	 */
	public String toString() {
		return piece.toString();
	}
}
