/**
 * @author Xiang Ao, Shijie Xu
 * @since Mar.25, 2019
 * 
 * This class is created for checkmate check
 * 
 * CS213 Software Methodology Project 2: A playable chess.
 */
package chess;

import java.util.ArrayList;

public class Checkmate {
	static SetPiece sp = new SetPiece();
	/**
	 * help method: find king piece of either white or black
	 * 
	 * @param color		determine to find which color's king
	 * @param board		current board
	 * @return	return location of selected color King (integer location on board; range from 1-64). return -1 if couldn't find King
	 */
	int kingFinder(int color, ArrayList<BlockNode> board) {
		for (int i = 0; i < 64; i++) {
			if (board.get(i).getPiece() != null) {
				if (board.get(i).getPiece().getRole().equals("K")) {
					if (board.get(i).getPiece().getColor() == color) {
						return i;
					}
				}
			}
		}
		return -1;
	}
	/**
	 * Clear all pieces on board check status
	 * @param board current board
	 */
	void clearCheckStatus(ArrayList<BlockNode> board) {
		for(int i = 0; i<64; i++) {
			if(board.get(i).getPiece()!=null) {
				board.get(i).getPiece().setCheck(false);
			}
		}
	}
	/**
	 * Check if current King piece is safe or not
	 * @param kingIndex		integer location of King piece on board(range from: 1-64)
	 * @param board			current board
	 * @return	return true is current King is not be checked; else return false
	 */
	boolean protectKing(int kingIndex, ArrayList<BlockNode> board) {
		boolean safe = false;
		ArrayList<BlockNode> passWay = passingBy(kingIndex, board);
		int color = board.get(kingIndex).getPiece().getColor();
		for (int i = 0; i < 64; i++) {
			if (board.get(i).getPiece() != null) {
				if (board.get(i).getPiece().getColor() == color) {
					ArrayList<BlockNode> next = board.get(i).getPiece().getNextPos();
					for(int j = 0; j<next.size(); j++) {
						if(sp.checkContain(next.get(j),passWay)) {
							safe = true;
						}
					}
				}
			}
		}
		return safe;
	}
	/**
	 * Add all pieces which passed by King piece
	 * 
	 * @param kingIndex		integer location of King piece on board(range from: 1-64)
	 * @param board			current board
	 * @return	return an arraylist which include all pieces passed by King
	 */
	ArrayList<BlockNode> passingBy(int kingIndex, ArrayList<BlockNode> board) {
		ArrayList<BlockNode> key = new ArrayList<BlockNode>();
		ArrayList<Integer> checkPieces = new ArrayList<Integer>();
		int color = board.get(kingIndex).getPiece().getColor();

		for (int i = 0; i < 64; i++) {
			if (board.get(i).getPiece() != null) {
				if (board.get(i).getPiece().getColor() != color) {
					if (board.get(i).getPiece().isCheck()) {
						checkPieces.add(i);
					}
				}
			}
		}
		int attackerIndex, difference, diffAbs, attackerCol, attackerRow, kingCol, kingRow;
		int upperRight, upperLeft, lowerRight, lowerLeft;
		kingCol = kingIndex % 8;
		kingRow = 8 - kingIndex / 8;

		for (int j = 0; j < checkPieces.size(); j++) {
			attackerIndex = checkPieces.get(j);
			difference = attackerIndex - kingIndex;
			diffAbs = Math.abs(difference);
			attackerCol = attackerIndex % 8;
			attackerRow = 8 - attackerIndex / 8;
			upperRight = attackerCol - kingCol;
			upperLeft = kingCol - attackerCol;
			lowerRight = attackerCol - kingCol;
			lowerLeft = kingCol - attackerCol;

			switch (board.get(attackerIndex).getPiece().getRole()) {
			case "p":
				key.add(board.get(j));
				break;
			case "N":
				key.add(board.get(j));
				break;
			case "B":
				/**
				 * attacker piece locate at upper right
				 */
				if (attackerCol > kingCol && attackerRow > kingRow) {
					for (int ur = 0; ur < upperRight; ur++) {
						key.add(board.get(attackerIndex + 7 * ur));
					}
				}
				/**
				 * attacker piece locate at upper left
				 */
				if (attackerCol < kingCol && attackerRow > kingRow) {
					for (int ul = 0; ul < upperLeft; ul++) {
						key.add(board.get(attackerIndex + 9 * ul));
					}
				}
				/**
				 * attacker piece locate at lower right
				 */
				if (attackerCol > kingCol && attackerRow < kingRow) {
					for (int lr = 0; lr < lowerRight; lr++) {
						key.add(board.get(attackerIndex - 9 * lr));
					}
				}
				/**
				 * attacker piece locate at lower left
				 */
				if (attackerCol < kingCol && attackerRow < kingRow) {
					for (int ll = 0; ll < lowerLeft; ll++) {
						key.add(board.get(attackerIndex - 7 * ll));
					}
				}

				break;
			case "Q":
				/**
				 * at the same row and King is at right of attacker piece
				 */
				if (difference > 0 && difference < 8) {
					for (int r = 0; r < diffAbs; r++) {
						key.add(board.get(attackerIndex + r));
					}
				}
				/**
				 * at the same row and King is at left of attacker piece
				 */
				if (difference > -8 && difference < 0) {
					for (int l = 0; l < diffAbs; l++) {
						key.add(board.get(attackerIndex - l));
					}
				}
				/**
				 * at the same column and King is at below of attacker piece
				 */
				if (difference >= 8) {
					for (int b = 0; b < (diffAbs / 8); b++) {
						key.add(board.get(attackerIndex - b * 8));
					}
				} /**
					 * at the same column and King is at above of attacker piece
					 */
				if (difference <= -8) {
					for (int a = 0; a < (diffAbs / 8); a++) {
						key.add(board.get(attackerIndex + a * 8));
					}
				}
				/**
				 * attacker piece locate at upper right
				 */
				if (attackerCol > kingCol && attackerRow > kingRow) {
					for (int ur = 0; ur < upperRight; ur++) {
						key.add(board.get(attackerIndex + 7 * ur));
					}
				}
				/**
				 * attacker piece locate at upper left
				 */
				if (attackerCol < kingCol && attackerRow > kingRow) {
					for (int ul = 0; ul < upperLeft; ul++) {
						key.add(board.get(attackerIndex + 9 * ul));
					}
				}
				/**
				 * attacker piece locate at lower right
				 */
				if (attackerCol > kingCol && attackerRow < kingRow) {
					for (int lr = 0; lr < lowerRight; lr++) {
						key.add(board.get(attackerIndex - 9 * lr));
					}
				}
				/**
				 * attacker piece locate at lower left
				 */
				if (attackerCol < kingCol && attackerRow < kingRow) {
					for (int ll = 0; ll < lowerLeft; ll++) {
						key.add(board.get(attackerIndex - 7 * ll));
					}
				}
				break;
			case "R":
				/**
				 * at the same row and King is at right of attacker piece
				 */
				if (difference > 0 && difference < 8) {
					for (int r = 0; r < diffAbs; r++) {
						key.add(board.get(attackerIndex + r));
					}
				}
				/**
				 * at the same row and King is at left of attacker piece
				 */
				if (difference > -8 && difference < 0) {
					for (int l = 0; l < diffAbs; l++) {
						key.add(board.get(attackerIndex - l));
					}
				}
				/**
				 * at the same column and King is at below of attacker piece
				 */
				if (difference >= 8) {
					for (int b = 0; b < (diffAbs / 8); b++) {
						key.add(board.get(attackerIndex - b * 8));
					}
				} /**
					 * at the same column and King is at above of attacker piece
					 */
				if (difference <= -8) {
					for (int a = 0; a < (diffAbs / 8); a++) {
						key.add(board.get(attackerIndex + a * 8));
					}
				}
				break;
			}
		}

		return key;
	}
	/**
	 * Check if current piece could check enemy King
	 * 
	 * @param kingIndex		integer location of current piece(range from 1-64)
	 * @param display		if current piece could show up 
	 * @param board			current board
	 * @return	return true if current piece move couldn't check else print "Check" in a new line and return false
	 */
	boolean checkCheck(int kingIndex, boolean display, ArrayList<BlockNode> board) {
		int color = board.get(kingIndex).getPiece().getColor();
		boolean safe = true;
		board.get(kingIndex).getPiece().setCheck(false);
		for (int i = 0; i < 64; i++) {
			if (board.get(i).getPiece() != null) {
				if (board.get(i).getPiece().getColor() != color) {
					if(board.get(i).getPiece().getRole().equals("p")) {
						if(sp.checkContain(board.get(kingIndex), board.get(i).getPiece().getNextOcc())) {
							safe = false;
							board.get(i).getPiece().setCheck(true);
						}
					}else {
						if(sp.checkContain(board.get(kingIndex), board.get(i).getPiece().getNextPos())){
							safe = false;
							board.get(i).getPiece().setCheck(true);
						}
					}
				}
			}
		}
		if (!safe && display) {
			pl("");
			pl("Check");
		}
		return safe;
	}
	/**
	 * Check if pieces around King pieces could check the Kind
	 * 
	 * @param kingIndex		integer location of King piece on board(range from: 1-64)
	 * @param board			current board
	 * @return	return true if King couldn't be checked; else return false
	 */
	boolean checkAround(int kingIndex, ArrayList<BlockNode> board) {
		int color = board.get(kingIndex).getPiece().getColor();
		ArrayList<BlockNode> next = board.get(kingIndex).getPiece().getNextPos();
		boolean safe = false;
		if(next == null) {
			return safe;
		}
		for(int i = 0; i<next.size();i++) {
			board.get(i).setCheck(false);
			for(int j = 0; j<64; j++) {
				if(board.get(j).getPiece()!=null) {
					if(board.get(j).getPiece().getColor()!=color) {
						if(board.get(j).getPiece().getRole().equals("p")) {
							if(sp.checkContain(next.get(i), board.get(j).getPiece().getNextOcc())) {
								board.get(i).setCheck(true);
							}
						}else {
							if(sp.checkContain(next.get(i), board.get(j).getPiece().getNextPos())){
								board.get(i).setCheck(true);
							}
						}
					}
				}
			}
		}
		for(int n = 0; n<next.size();n++) {
			if(!next.get(n).isCheck()) {
				safe = true;
			}
		}
		return safe;
	}

	/**
	 * Print helper method
	 * 
	 * @param msg The message you want to print.
	 */
	public static void p(String msg) {
		System.out.print(msg);
	}

	/**
	 * Print helper method
	 * 
	 * @param msg The message you want to print in a line.
	 */
	public static void pl(String msg) {
		System.out.println(msg);
	}
}

