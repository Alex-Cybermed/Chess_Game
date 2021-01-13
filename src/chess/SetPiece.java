/**
 * @author Xiang Ao, Shijie Xu
 * @since Mar.4, 2019
 * 
 * This class is setting and getting info. of each piece.
 * .
 * CS213 Software Methodology Project 2: A playable chess.
 */
package chess;

import java.util.ArrayList;
//import java.util.concurrent.CountDownLatch;

public class SetPiece {
	static SpecialMovement sm = new SpecialMovement();

	/**
	 * Find out next possible moves and collect them into an ArrayList Find out
	 * killing movement for pawns and collect them into occ list;
	 * 
	 * @param s     select The block with current piece you choose.
	 * @param board current board
	 */
	void nextList(int s, ArrayList<BlockNode> board) {
		ArrayList<BlockNode> next = new ArrayList<BlockNode>();
		ArrayList<BlockNode> nextKill = new ArrayList<BlockNode>();
		Pieces p = board.get(s).getPiece(); // current selected piece
		if (p == null) {
			return;
		}
		int sLetter = s % 8; // column location
		int sNumber = 8 - (s / 8); // row location
		int sUp = 8 - sNumber;
		int sDown = sNumber - 1;
		int sLeft = sLetter;
		int sRight = 7 - sLetter;
		int minUL = Math.min(sUp, sLeft);
		int minUR = Math.min(sUp, sRight);
		int minDR = Math.min(sDown, sRight);
		int minDL = Math.min(sDown, sLeft);
		if (p.getRole().equals("p")) {
			if (p.getColor() == 1) {
				if (s - 8 < 0) {

				} else if (board.get(s - 8).getPiece() == null) {
					next.add(board.get(s - 8));
					if (sNumber == 2) {
						if (board.get(s - 16).getPiece() == null) {
							next.add(board.get(s - 16));
						}
					}
				}
				if (s > 7) {
					if (s%8 == 0) {
						nextKill.add(board.get(s - 7));
					} else if (s%8 == 7) {
						nextKill.add(board.get(s - 9));
					}else {
						nextKill.add(board.get(s - 9));
						nextKill.add(board.get(s - 7));
					}
				} 
			} else if (p.getColor() == 2) {
				if (s + 8 > 63) {

				} else if (board.get(s + 8).getPiece() == null) {
					next.add(board.get(s + 8));
					if (sNumber == 7) {
						if (board.get(s + 16).getPiece() == null) {
							next.add(board.get(s + 16));
						}
					}
				}
				if (s < 56) {
					if (s%8 == 0) {
						nextKill.add(board.get(s + 9));
					} else if (s%8 == 7) {
						nextKill.add(board.get(s + 7));
					}else {
						nextKill.add(board.get(s + 9));
						nextKill.add(board.get(s + 7));
					}
				}
			}
			p.setNextPos(next);
			p.setNextOcc(nextKill);
		} else if (p.getRole().equals("R")) { // Rook
			/*
			 * Adding available block to list until it meet boarder or ally Block which
			 * occupied by enemy will also be appended into list. Below are four for-loops
			 * for four directions.
			 */
			for (int u = 1; u <= sUp; u++) {
				if (board.get(s - 8 * u) == null) {
					break;
				}
				if (board.get(s - 8 * u).getPiece() != null) {
					next.add(board.get(s - 8 * u));
					break;
				}
				next.add(board.get(s - 8 * u));
			}

			for (int d = 1; d <= sDown; d++) {
				if (board.get(s + 8 * d) == null) {
					break;
				}
				if (board.get(s + 8 * d).getPiece() != null) {
					next.add(board.get(s + 8 * d));
					break;
				}
				next.add(board.get(s + 8 * d));
			}
			for (int l = 1; l <= sLeft; l++) {
				if (board.get(s - l) == null) {
					break;
				}
				if (board.get(s - l).getPiece() != null) {
					next.add(board.get(s - l));
					break;
				}
				next.add(board.get(s - l));
			}
			for (int r = 1; r <= sRight; r++) {
				if (board.get(s + r) == null) {
					break;
				}
				if (board.get(s + r).getPiece() != null) {
					next.add(board.get(s + r));
					break;
				}
				next.add(board.get(s + r));
			}
			p.setNextPos(next);
		} else if (p.getRole().equals("N")) { // Knight
			if (sUp >= 2 && sRight >= 1) {
				next.add(board.get(s - 15));
			}
			if (sUp >= 1 && sRight >= 2) {
				next.add(board.get(s - 6));
			}
			if (sUp >= 2 && sLeft >= 1) {
				next.add(board.get(s - 17));
			}
			if (sUp >= 1 && sLeft >= 2) {
				next.add(board.get(s - 10));
			}
			if (sDown >= 1 && sLeft >= 2) {
				next.add(board.get(s + 6));
			}
			if (sDown >= 2 && sLeft >= 1) {
				next.add(board.get(s + 15));
			}
			if (sDown >= 2 && sRight >= 1) {
				next.add(board.get(s + 17));
			}
			if (sDown >= 1 && sRight >= 2) {
				next.add(board.get(s + 10));
			}
			p.setNextPos(next);
		} else if (p.getRole().equals("B")) { // Bishop
			for (int i = 1; i <= minUL; i++) {
				if (board.get(s - 9 * i).getPiece() != null) {
					next.add(board.get(s - 9 * i));
					break;
				} else {
					next.add(board.get(s - 9 * i));
				}
			}

			for (int j = 1; j <= minUR; j++) {
				if (board.get(s - 7 * j).getPiece() != null) {
					next.add(board.get(s - 7 * j));
					break;
				} else {
					next.add(board.get(s - 7 * j));
				}
			}

			for (int n = 1; n <= minDR; n++) {
				if (board.get(s + 9 * n).getPiece() != null) {
					next.add(board.get(s + 9 * n));
					break;
				} else {
					next.add(board.get(s + 9 * n));
				}
			}

			for (int m = 1; m <= minDL; m++) {
				if (board.get(s + 7 * m).getPiece() != null) {
					next.add(board.get(s + 7 * m));
					break;
				} else {
					next.add(board.get(s + 7 * m));
				}
			}

			p.setNextPos(next);
		} else if (p.getRole().equals("Q")) { // queen
			for (int u = 1; u <= sUp; u++) {
				if (board.get(s - 8 * u).getPiece() != null) {
					next.add(board.get(s - 8 * u));
					break;
				}
				next.add(board.get(s - 8 * u));
			}
			for (int d = 1; d <= sDown; d++) {
				if (board.get(s + 8 * d).getPiece() != null) {
					next.add(board.get(s + 8 * d));
					break;
				}
				next.add(board.get(s + 8 * d));
			}
			for (int l = 1; l <= sLeft; l++) {
				if (board.get(s - l).getPiece() != null) {
					next.add(board.get(s - l));
					break;
				}
				next.add(board.get(s - l));
			}
			for (int r = 1; r <= sRight; r++) {
				if (board.get(s + r).getPiece() != null) {
					next.add(board.get(s + r));
					break;
				}
				next.add(board.get(s + r));
			}
			for (int i = 1; i <= minUL; i++) {
				if (board.get(s - 9 * i).getPiece() != null) {
					next.add(board.get(s - 9 * i));
					break;
				} else {
					next.add(board.get(s - 9 * i));
				}
			}

			for (int j = 1; j <= minUR; j++) {
				if (board.get(s - 7 * j).getPiece() != null) {
					next.add(board.get(s - 7 * j));
					break;
				} else {
					next.add(board.get(s - 7 * j));
				}
			}

			for (int n = 1; n <= minDR; n++) {
				if (board.get(s + 9 * n).getPiece() != null) {
					next.add(board.get(s + 9 * n));
					break;
				} else {
					next.add(board.get(s + 9 * n));
				}
			}

			for (int m = 1; m <= minDL; m++) {
				if (board.get(s + 7 * m).getPiece() != null) {
					next.add(board.get(s + 7 * m));
					break;
				} else {
					next.add(board.get(s + 7 * m));
				}
			}
			p.setNextPos(next);
		} else if (p.getRole().equals("K")) { // King
			if (p.getColor() == 1) {
				if (sUp > 0) {
					if (board.get(s - 8).getPiece() != null) {
						if (board.get(s - 8).getPiece().getColor() == 2) {
							if (!checkBlock(s - 8, 2, board)) {
								next.add(board.get(s - 8));
							}
						}
					} else {
						if (!checkBlock(s - 8, 2, board)) {
							next.add(board.get(s - 8));
						}
					}
				}
				if (sDown > 0) {
					if (board.get(s + 8).getPiece() != null) {
						if (board.get(s + 8).getPiece().getColor() == 2) {
							if (!checkBlock(s + 8, 2, board)) {
								next.add(board.get(s + 8));
							}
						}
					} else {
						if (!checkBlock(s + 8, 2, board)) {
							next.add(board.get(s + 8));
						}
					}
				}
				if (sLeft > 0) {
					if (board.get(s - 1).getPiece() != null) {
						if (board.get(s - 1).getPiece().getColor() == 2) {
							if (!checkBlock(s - 1, 2, board)) {
								next.add(board.get(s - 1));
							}
						}
					} else {
						if (!checkBlock(s - 1, 2, board)) {
							next.add(board.get(s - 1));
						}
					}
				}
				if (sRight > 0) {
					if (board.get(s + 1).getPiece() != null) {
						if (board.get(s + 1).getPiece().getColor() == 2) {
							if (!checkBlock(s + 1, 2, board)) {
								next.add(board.get(s + 1));
							}
						}
					} else {
						if (!checkBlock(s + 1, 2, board)) {
							next.add(board.get(s + 1));
						}
					}
				}
				if (minUR > 0) {
					if (board.get(s - 7).getPiece() != null) {
						if (board.get(s - 7).getPiece().getColor() == 2) {
							if (!checkBlock(s - 7, 2, board)) {
								next.add(board.get(s - 7));
							}
						}
					} else {
						if (!checkBlock(s - 7, 2, board)) {
							next.add(board.get(s - 7));
						}
					}
				}
				if (minUL > 0) {
					if (board.get(s - 9).getPiece() != null) {
						if (board.get(s - 9).getPiece().getColor() == 2) {
							if (!checkBlock(s - 9, 2, board)) {
								next.add(board.get(s - 9));
							}
						}
					} else {
						if (!checkBlock(s - 9, 2, board)) {
							next.add(board.get(s - 9));
						}
					}
				}
				if (minDL > 0) {
					if (board.get(s + 7).getPiece() != null) {
						if (board.get(s + 7).getPiece().getColor() == 2) {
							if (!checkBlock(s + 7, 2, board)) {
								next.add(board.get(s + 7));
							}
						}
					} else {
						if (!checkBlock(s + 7, 2, board)) {
							next.add(board.get(s + 7));
						}
					}
				}
				if (minDR > 0) {
					if (board.get(s + 9).getPiece() != null) {
						if (board.get(s + 9).getPiece().getColor() == 2) {
							if (!checkBlock(s + 9, 2, board)) {
								next.add(board.get(s + 9));
							}
						}
					} else {
						if (!checkBlock(s + 9, 2, board)) {
							next.add(board.get(s + 9));
						}
					}
				}
			} else {
				if (sUp > 0) {
					if (board.get(s - 8).getPiece() != null) {
						if (board.get(s - 8).getPiece().getColor() == 1) {
							if (!checkBlock(s - 8, 1, board)) {
								next.add(board.get(s - 8));
							}
						}
					} else {
						if (!checkBlock(s - 8, 1, board)) {
							next.add(board.get(s - 8));
						}
					}
				}
				if (sDown > 0) {
					if (board.get(s + 8).getPiece() != null) {
						if (board.get(s + 8).getPiece().getColor() == 1) {
							if (!checkBlock(s + 8, 1, board)) {
								next.add(board.get(s + 8));
							}
						}
					} else {
						if (!checkBlock(s + 8, 1, board)) {
							next.add(board.get(s + 8));
						}
					}
				}
				if (sLeft > 0) {
					if (board.get(s - 1).getPiece() != null) {
						if (board.get(s - 1).getPiece().getColor() == 1) {
							if (!checkBlock(s - 1, 1, board)) {
								next.add(board.get(s - 1));
							}
						}
					} else {
						if (!checkBlock(s - 1, 1, board)) {
							next.add(board.get(s - 1));
						}
					}
				}
				if (sRight > 0) {
					if (board.get(s + 1).getPiece() != null) {
						if (board.get(s + 1).getPiece().getColor() == 1) {
							if (!checkBlock(s + 1, 1, board)) {
								next.add(board.get(s + 1));
							}
						}
					} else {
						if (!checkBlock(s + 1, 1, board)) {
							next.add(board.get(s + 1));
						}
					}
				}
				if (minUR > 0) {
					if (board.get(s - 7).getPiece() != null) {
						if (board.get(s - 7).getPiece().getColor() == 1) {
							if (!checkBlock(s - 7, 1, board)) {
								next.add(board.get(s - 7));
							}
						}
					} else {
						if (!checkBlock(s - 7, 1, board)) {
							next.add(board.get(s - 7));
						}
					}
				}
				if (minUL > 0) {
					if (board.get(s - 9).getPiece() != null) {
						if (board.get(s - 9).getPiece().getColor() == 1) {
							if (!checkBlock(s - 9, 1, board)) {
								next.add(board.get(s - 9));
							}
						}
					} else {
						if (!checkBlock(s - 9, 1, board)) {
							next.add(board.get(s - 9));
						}
					}
				}
				if (minDL > 0) {
					if (board.get(s + 7).getPiece() != null) {
						if (board.get(s + 7).getPiece().getColor() == 1) {
							if (!checkBlock(s + 7, 1, board)) {
								next.add(board.get(s + 7));
							}
						}
					} else {
						if (!checkBlock(s + 7, 1, board)) {
							next.add(board.get(s + 7));
						}
					}
				}
				if (minDR > 0) {
					if (board.get(s + 9).getPiece() != null) {
						if (board.get(s + 9).getPiece().getColor() == 1) {
							if (!checkBlock(s + 9, 1, board)) {
								next.add(board.get(s + 9));
							}
						}
					} else {
						if (!checkBlock(s + 9, 1, board)) {
							next.add(board.get(s + 9));
						}
					}
				}
			}

			p.setNextPos(next);
		}
	}

	/**
	 * check if current piece could move from calculated next arraylist
	 * 
	 * @param curr current block
	 * @param next next arraylist which include all possible move for current piece
	 * @return return true if current piece is legal to move
	 */
	boolean checkContain(BlockNode curr, ArrayList<BlockNode> next) {
		if (next == null) {
			return false;
		}
		for (int i = 0; i < next.size(); i++) {
			if (next.get(i).getLetter() == curr.getLetter() && next.get(i).getNumber() == curr.getNumber()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Help method: set target block to null
	 * 
	 * @param target target blocknode
	 */
	void clearBlock(BlockNode target) {
		target.setPiece(null);
	}

	/**
	 * "FileRank FileRank" input command
	 * 
	 * Add beta En passant still got bugs: No.1 column a is not working No.2 do not
	 * add check En passant chance (every piece only have one time to en passant, if
	 * passed, chance gone)
	 * 
	 * @param white Identify if current player is white.
	 * @param s     The block with current piece you choose.
	 * @param m     The block you want to move to.
	 * @param board current board
	 * @return return true if legal else return false
	 */
	boolean fileRank(boolean white, int s, int m, ArrayList<BlockNode> board) {
		int enemy = 0;
		boolean enpassant = false;
		if (white) {
			enemy = 2;
			if (board.get(s).getPiece().getColor() == 2) {
				pl("Illegal move, try again");
				return false;
			}
			if (board.get(m).getPiece() != null) {
				if (board.get(m).getPiece().getColor() == 1) {
					pl("Illegal move, try again");
					return false;
				}
			}
		}
		if (!white) {
			enemy = 1;
			if (board.get(s).getPiece().getColor() == 1) {
				pl("Illegal move, try again");
				return false;
			}
			if (board.get(m).getPiece() != null) {
				if (board.get(m).getPiece().getColor() == 2) {
					pl("Illegal move, try again");
					return false;
				}
			}
		}
		setList(board);
		enpassant = sm.enPassant(s, m, enemy, board);
		if (!enpassant) {
			if (pawnKill(s, m, board)) {
				setList(board);
				return true;
			} else if (checkContain(board.get(m), board.get(s).getPiece().getNextPos())) {
				if (s == 0 || s == 4 || s == 7 || s == 56 || s == 60 || s == 63) {
					board.get(s).getPiece().setCastling(true);
				}
				Pieces p = board.get(s).getPiece();
				clearBlock(board.get(m));
				board.get(m).setPiece(p);
				clearBlock(board.get(s));
			} else {
				pl("Illegal move, try again");
				return false;
			}
		}
		setList(board);
		return true;
	}

	/**
	 * Check if move is kill if so check if this kill produced by pawn
	 * 
	 * @param s     select blocknode
	 * @param m     target blocknode to move
	 * @param board current board
	 * @return return true if kill from pawn
	 */
	boolean pawnKill(int s, int m, ArrayList<BlockNode> board) {
		if (board.get(s).getPiece() == null) {
			pl("Illegal move, try again");
			return false;
		}
		if (s % 8 == m % 8) {
			return false;
		}
		int color = board.get(s).getPiece().getColor();
		if (board.get(s).getPiece().getRole().equals("p")) {
			if (board.get(m).getPiece() != null) {
				if (board.get(m).getPiece().getColor() != color) {
					Pieces p = board.get(s).getPiece();
					clearBlock(board.get(s));
					board.get(m).setPiece(p);
					return true;
				} else {
					pl("Illegal move, try again");
					return false;
				}
			} else {
				pl("Illegal move, try again");
				return false;
			}
		}
		return false;
	}

	/**
	 * help method: set every piece on board to couldn't En Passant
	 * 
	 * @param board current board
	 */
	void setEnFalse(ArrayList<BlockNode> board) {
		for (int i = 0; i < 64; i++) {
			if (board.get(i).getPiece() != null) {
				board.get(i).getPiece().setEnpassant(false);
			}
		}
	}

	/**
	 * help method: set every piece's next move arraylist
	 * 
	 * @param board current board
	 */
	void setList(ArrayList<BlockNode> board) {
		for (int i = 0; i < 64; i++) {
			nextList(i, board);
		}
	}

	/**
	 * help method: used to check if move is legal
	 * 
	 * @param blockIndex Index of block player wants to move
	 * @param enemyColor Index of current piece's enemy's color 1:white 2:black
	 * @param board      current board
	 * @return if block player choosed is legal return true
	 */
	boolean checkBlock(int blockIndex, int enemyColor, ArrayList<BlockNode> board) {
		BlockNode b = board.get(blockIndex);
		for (int i = 0; i < 64; i++) {
			if (board.get(i).getPiece() != null) {
				if (board.get(i).getPiece().getColor() == enemyColor) {
					if (checkContain(b, board.get(i).getPiece().getNextPos())
							|| checkContain(b, board.get(i).getPiece().getNextOcc())) {
						return true;
					}
				}
			}
		}
		return false;
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
