/** 
 * @author Xiang Ao, Shijie Xu
 * @since Mar.24, 2019
 * 
 * This class have methods for Castling, En passant and Promotion.
 * 
 * CS213 Software Methodology Project 2: A playable chess.
 */
package chess;

import java.util.ArrayList;
//import java.util.Scanner;

public class SpecialMovement {
//	ArrayList<BlockNode> board;
	static SetPiece sp = new SetPiece();
	static PrintBoard pb = new PrintBoard();
	
	/**
	 * This method for promotion, current player could set pawn which reached enemy line to promotion
	 * pawn could promoted as Queen(Q), Bishop(B), Rook(R), Knight(N)
	 * 
	 * @param move		block which player wants to move to (integer location on board; range from 1-64)
	 * @param info		Role of piece player wants to promote as
	 * @param board		current board
	 */
	void promotion(int move, String info, ArrayList<BlockNode> board) {
		String promotionSign;
		switch (info) {
		case " Q":
			promotionSign = "Q";
			break;
		case " B":
			promotionSign = "B";
			break;
		case " R":
			promotionSign = "R";
			break;
		default:
			promotionSign = "N";
		}

		if (board.get(move).getPiece().getRole().equals("p")) {
			if (board.get(move).getPiece().getColor() == 1) {
				if (move >= 0 && move <= 7) {
					board.get(move).getPiece().setRole(promotionSign);
				}
			} else {
				if (move >= 56 && move <= 63) {
					board.get(move).getPiece().setRole(promotionSign);
				}
			}
		}
	}
	
	
	/**
	 * Castling method
	 * 
	 * @param select	player selected block (integer location on board; range from 1-64)
	 * @param move		block which player wants to move to (integer location on board; range from 1-64)
	 * @param board		current block
	 * @return return true selected two pieces could castling else return false
	 */
	boolean castling(int select, int move, ArrayList<BlockNode> board) {
		if (select == 60) {
			if (!board.get(60).getPiece().isCheck()) {
				if (!board.get(60).getPiece().isCastling()) {
					boolean ok;
					if (move == 62) {
						if (!board.get(63).getPiece().isCastling()) {
							if (board.get(61).getPiece() == null && board.get(62).getPiece() == null) {
								ok = true;
								for (int i = 0; i < 64; i++) {
									if (board.get(i).getPiece() == null) {
										continue;
									}
									if (board.get(i).getPiece().getColor() == 1) {
										continue;
									}
									if (sp.checkContain(board.get(61), board.get(i).getPiece().getNextPos())) {
										ok = false;
										break;
									}
									if (sp.checkContain(board.get(62), board.get(i).getPiece().getNextPos())) {
										ok = false;
										break;
									}
								}
								if (ok) {
									Pieces castling = board.get(60).getPiece();
									board.get(62).setPiece(castling);
									sp.clearBlock(board.get(60));
									castling = board.get(63).getPiece();
									board.get(61).setPiece(castling);
									sp.clearBlock(board.get(63));
									board.get(62).getPiece().setCastling(true);
									return true;
								}
							}
						}
					} else if (move == 58) {
						if (!board.get(56).getPiece().isCastling()) {
							if (board.get(57).getPiece() == null && board.get(58).getPiece() == null
									&& board.get(59).getPiece() == null) {
								ok = true;
								for (int i = 0; i < 64; i++) {
									if (board.get(i).getPiece() == null) {
										continue;
									}
									if (board.get(i).getPiece().getColor() == 1) {
										continue;
									}
									if (sp.checkContain(board.get(59), board.get(i).getPiece().getNextPos())) {
										ok = false;
										break;
									}
									if (sp.checkContain(board.get(58), board.get(i).getPiece().getNextPos())) {
										ok = false;
										break;
									}
								}
								if (ok) {
									Pieces castling = board.get(60).getPiece();
									board.get(58).setPiece(castling);
									sp.clearBlock(board.get(60));
									castling = board.get(56).getPiece();
									board.get(59).setPiece(castling);
									sp.clearBlock(board.get(56));
									board.get(58).getPiece().setCastling(true);
									return true;
								}
							}
						}
					}
				}
			}
		}
		if (select == 4) {
			if (!board.get(4).getPiece().isCheck()) {
				if (!board.get(4).getPiece().isCastling()) {
					boolean ok;
					if (move == 6) {
						if (!board.get(7).getPiece().isCastling()) {
							if (board.get(5).getPiece() == null && board.get(6).getPiece() == null) {
								ok = true;
								for (int i = 0; i < 64; i++) {
									if (board.get(i).getPiece() == null) {
										continue;
									}
									if (board.get(i).getPiece().getColor() == 2) {
										continue;
									}
									if (sp.checkContain(board.get(5), board.get(i).getPiece().getNextPos())) {
										ok = false;
										break;
									}
									if (sp.checkContain(board.get(6), board.get(i).getPiece().getNextPos())) {
										ok = false;
										break;
									}
								}
								if (ok) {
									Pieces castling = board.get(4).getPiece();
									board.get(6).setPiece(castling);
									sp.clearBlock(board.get(4));
									castling = board.get(7).getPiece();
									board.get(5).setPiece(castling);
									sp.clearBlock(board.get(7));
									board.get(6).getPiece().setCastling(true);
									return true;
								}
							}
						}
					} else if (move == 2) {
						if (!board.get(0).getPiece().isCastling()) {
							if (board.get(3).getPiece() == null && board.get(2).getPiece() == null
									&& board.get(1).getPiece() == null) {
								ok = true;
								for (int i = 0; i < 64; i++) {
									if (board.get(i).getPiece() == null) {
										continue;
									}
									if (board.get(i).getPiece().getColor() == 2) {
										continue;
									}
									if (sp.checkContain(board.get(3), board.get(i).getPiece().getNextPos())) {
										ok = false;
										break;
									}
									if (sp.checkContain(board.get(2), board.get(i).getPiece().getNextPos())) {
										ok = false;
										break;
									}
								}
								if (ok) {
									Pieces castling = board.get(4).getPiece();
									board.get(2).setPiece(castling);
									sp.clearBlock(board.get(4));
									castling = board.get(0).getPiece();
									board.get(3).setPiece(castling);
									sp.clearBlock(board.get(0));
									board.get(2).getPiece().setCastling(true);
									return true;
								}
							}
						}
					}
				}
			}
		}
		return false;
	}
	/**
	 * 
	 * @param s			player selected block (integer location on board; range from 1-64)
	 * @param m			block which player wants to move to (integer location on board; range from 1-64)
	 * @param enemy		location of enemy piece (integer location on board; range from 1-64)
	 * @param board		current board
	 * @return			return true if current piece could enPassant enemy piece
	 */
	boolean enPassant(int s, int m, int enemy, ArrayList<BlockNode> board) {
		boolean enpassant = false;
		if (board.get(s).getPiece().getRole().equals("p")) {
			if (s >= 48 && s <= 55) {
				if (m >= 32 && m <= 39) {
					board.get(s).getPiece().setEnpassant(true);
					//pl(board.get(s).getPiece().isEnpassant() + "");
				}
			}
			if (s >= 8 && s <= 15) {
				if (m >= 24 && m <= 31) {
					board.get(s).getPiece().setEnpassant(true);
					//pl(board.get(s).getPiece().isEnpassant() + "");
				}
			}
			if (board.get(s + 1).getPiece() != null) {
				if (board.get(s + 1).getPiece().getRole().equals("p")
						&& board.get(s + 1).getPiece().getColor() == enemy) {
					if (board.get(s + 1).getPiece().isEnpassant()) {
						if (enemy == 2) {
							if (m == s - 7) {
								Pieces p = board.get(s).getPiece();
								sp.clearBlock(board.get(s + 1));
								board.get(s - 7).setPiece(p);
								sp.clearBlock(board.get(s));
								enpassant = true;
							}
						} else {
							if (m == s + 9) {
								Pieces p = board.get(s).getPiece();
								sp.clearBlock(board.get(s + 1));
								board.get(s + 9).setPiece(p);
								sp.clearBlock(board.get(s));
								enpassant = true;
							}
						}
					}
				}
			}
			if (board.get(s - 1).getPiece() != null) {
				if (board.get(s - 1).getPiece().getRole().equals("p")
						&& board.get(s - 1).getPiece().getColor() == enemy) {
					if (board.get(s - 1).getPiece().isEnpassant()) {
						if (enemy == 2) {
							if (m == s - 9) {
								Pieces p = board.get(s).getPiece();
								sp.clearBlock(board.get(s - 1));
								board.get(s - 9).setPiece(p);
								sp.clearBlock(board.get(s));
								enpassant = true;
							}
						} else {
							if (m == s + 7) {
								Pieces p = board.get(s).getPiece();
								sp.clearBlock(board.get(s - 1));
								board.get(s + 7).setPiece(p);
								sp.clearBlock(board.get(s));
								enpassant = true;
							}
						}
					}
				}
			}
		}
		return enpassant;
	}
	/**
	 * This method is used to check if current piece located at specific line to enable or disable boolean enpassant
	 * 
	 * @param color 	current piece's color
	 * @param board		current board
	 */
	void checkEnpassant(int color, ArrayList<BlockNode> board) {
		if (color == 1) {
			for (int i = 32; i < 40; i++) {
				if (board.get(i).getPiece() != null) {
					if (board.get(i).getPiece().getRole().equals("p")) {
						board.get(i).getPiece().setEnpassant(false);
					}
				}
			}
		} else {
			for (int j = 24; j < 32; j++) {
				if (board.get(j).getPiece() != null) {
					if (board.get(j).getPiece().getRole().equals("p")) {
						board.get(j).getPiece().setEnpassant(false);

					}
				}
			}
		}
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
