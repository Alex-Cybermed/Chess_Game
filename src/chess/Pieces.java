/**
 * @author Xiang Ao, Shijie Xu
 * @since Mar.4, 2019
 * 
 * This class is the basic node for each piece.
 * 
 * CS213 Software Methodology Project 2: A playable chess.
 */
package chess;

import java.util.ArrayList;

public class Pieces {

	private String role;
	private int color;//0-no color, 1-white, 2-black.
	private ArrayList<BlockNode> nextPos;
	private ArrayList<BlockNode> nextOcc;
	private boolean check;
	private boolean castling;
	private boolean enpassant;

	/**
	 * Pieces constructor.
	 * @param role	The role information of the piece
	 * @param color	The color of the piece. 0:no color. 1:white. 2:black.
	 */
	public Pieces(String role, int color) {
		this.role = role;
		this.color = color;
	}

	/**
	 *  get Role info
	 *  
	 * @return Role info.
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Set the role info.
	 * 
	 * @param role role info
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * Get current piece color
	 * 
	 * @return The Color/Player of current piece
	 */
	public int getColor() {
		return color;
	}

	/**
	 * Set color of current role
	 * 
	 * @param color color info
	 */
	public void setColor(int color) {
		this.color = color;
	}

	/**
	 * 
	 * @return Get the block list of next possible movement
	 */
	public ArrayList<BlockNode> getNextPos() {
		return nextPos;
	}

	/**
	 * Setting the next possible movement as a list.
	 * @param nextPos updated nextPos arraylist
	 */
	public void setNextPos(ArrayList<BlockNode> nextPos) {
		this.nextPos = nextPos;
	}

	/**
	 * Specially for pawns' killing movement.
	 * @return The next kill list
	 */
	public ArrayList<BlockNode> getNextOcc() {
		return nextOcc;
	}

	/**
	 * Setting the next possible killing movement.
	 * @param nextOcc updated nextOcc(kill list) arraylist
	 */
	public void setNextOcc(ArrayList<BlockNode> nextOcc) {
		this.nextOcc = nextOcc;
	}

	/**
	 * Getting check info. Especially for King.
	 * @return true or false
	 */
	public boolean isCheck() {
		return check;
	}

	/**
	 * Setting check info. Especially for King.
	 * @param check check status
	 */
	public void setCheck(boolean check) {
		this.check = check;
	}

	/**
	 * Getting castling info. Especially for King.
	 * @return true or false
	 */
	public boolean isCastling() {
		return castling;
	}
	
	/**
	 * Setting castling info. Especially for King.
	 * @param castling castling status
	 */
	public void setCastling(boolean castling) {
		this.castling = castling;
	}
	
	/**
	 * Getting enpassant info. Especially for pawn.
	 * @return if pawn could En passant
	 */
	public boolean isEnpassant() {
		return enpassant;
	}
	
	/**
	 * Setting enpassant info. Especially for pawn.
	 * @param enpassant enpassant info
	 */
	public void setEnpassant(boolean enpassant) {
		this.enpassant = enpassant;
	}

	/**
	 * To string function. Print role.
	 */
	public String toString() {
		return role;
	}
}
