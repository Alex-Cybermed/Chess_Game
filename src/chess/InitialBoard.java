/**
 * @author Xiang Ao, Shijie Xu
 * @since Mar.7, 2019
 * 
 * This class initials the board and pieces' position on board.
 * 
 * CS213 Software Methodology Project 2: A playable chess.
 */

package chess;

import java.util.ArrayList;

public class InitialBoard {
	ArrayList<BlockNode> board;

	/**
	 * Initial a new board.
	 * @return A fix size arrayList which contains a board information.
	 */
	ArrayList<BlockNode> initial() {
		board = new ArrayList<BlockNode>();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (i % 2 == 0) {
					if (j % 2 == 0) {
						BlockNode block = new BlockNode(j, (8 - i), "  ");
						initialPiece(block);
						board.add(block);
					} else {
						BlockNode block = new BlockNode(j, (8 - i), "##");
						initialPiece(block);
						board.add(block);
					}
				} else {
					if (j % 2 == 0) {
						BlockNode block = new BlockNode(j, (8 - i), "##");
						initialPiece(block);
						board.add(block);
					} else {
						BlockNode block = new BlockNode(j, (8 - i), "  ");
						initialPiece(block);
						board.add(block);
					}
				}
			}
		}
		return board;
	}

	/**
	 * Set initial board with beginning pieces' places and implement their params.
	 * @param block The block you want to initialize.
	 */
	void initialPiece(BlockNode block) {
		if (block.getLetter() == 0 || block.getLetter() == 7) {
			if (block.getNumber() == 8) {
				Pieces piece = new Pieces("R", 2);
				block.setPiece(piece);
			} else if (block.getNumber() == 1) {
				Pieces piece = new Pieces("R", 1);
				block.setPiece(piece);
			}
		} else if (block.getLetter() == 1 || block.getLetter() == 6) {
			if (block.getNumber() == 8) {
				Pieces piece = new Pieces("N", 2);
				block.setPiece(piece);
			} else if (block.getNumber() == 1) {
				Pieces piece = new Pieces("N", 1);
				block.setPiece(piece);
			}
		} else if (block.getLetter() == 2 || block.getLetter() == 5) {
			if (block.getNumber() == 8) {
				Pieces piece = new Pieces("B", 2);
				block.setPiece(piece);
			} else if (block.getNumber() == 1) {
				Pieces piece = new Pieces("B", 1);
				block.setPiece(piece);
			}
		} else if (block.getLetter() == 3) {
			if (block.getNumber() == 8) {
				Pieces piece = new Pieces("Q", 2);
				block.setPiece(piece);
			} else if (block.getNumber() == 1) {
				Pieces piece = new Pieces("Q", 1);
				block.setPiece(piece);
			}
		} else if (block.getLetter() == 4) {
			if (block.getNumber() == 8) {
				Pieces piece = new Pieces("K", 2);
				block.setPiece(piece);
			} else if (block.getNumber() == 1) {
				Pieces piece = new Pieces("K", 1);
				block.setPiece(piece);
			}
		}
		if (block.getNumber() == 7) {
			Pieces piece = new Pieces("p", 2);
			piece.setNextPos(initialNextList(block));
			block.setPiece(piece);
		} else if (block.getNumber() == 2) {
			Pieces piece = new Pieces("p", 1);
			piece.setNextPos(initialNextList(block));
			block.setPiece(piece);
		}
	}

	/**
	 * Initial the next possible move of pawns into their arrayList.
	 * @param block Block with current piece.
	 * @return An arrayList with initialized "next move list"
	 */
	ArrayList<BlockNode> initialNextList(BlockNode block) {
		if (block.getPiece() == null) {
			return null;
		}
		Pieces p = block.getPiece();
		ArrayList<BlockNode> next = new ArrayList<BlockNode>();
		if (p.getRole().equals("p")) {
			if (block.getPiece().getColor() == 1) {
				BlockNode neighbor = board.get(8-(block.getNumber()+1)*8+block.getLetter());
				next.add(neighbor);
				neighbor = board.get(8-(block.getNumber()+2)*8+block.getLetter());
				next.add(neighbor);
			} else if (block.getPiece().getColor() == 2) {
				BlockNode neighbor = board.get(8-(block.getNumber()-1)*8+block.getLetter());
				next.add(neighbor);
				neighbor = board.get(8-(block.getNumber()-2)*8+block.getLetter());
				next.add(neighbor);
			} else {

			}
		}
		return next;
	}
}
