/** 
 * @author Xiang Ao, Shijie Xu
 * @since Mar.4, 2019
 * 
 * Print methods in Board
 * 
 * CS213 Software Methodology Project 2: A playable chess.
 */
package chess;

import java.util.ArrayList;

public class PrintBoard {

	/**
	 * Print current board.
	 * @param board	The board you want to print.
	 */
	void printBoard(ArrayList<BlockNode> board) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if(board.get(i*8+j).getPiece()!=null) {
					if(board.get(i*8+j).getPiece().getColor()==1) {
						p("w"+board.get(i*8+j).getPiece().getRole()+" ");
					}else {
						p("b"+board.get(i*8+j).getPiece().getRole()+" ");
					}
				}else {
					p(board.get(i*8+j).getColor()+" ");
				}
			}
			pl(" " + (8 - i));
		}
		char codeLetter;
		for (int i = 0; i < 8; i++) {
			codeLetter = (char) ('a' + i);
			p(" " + codeLetter + " ");
		}
		pl("");
	}
	
	/**
	 * Print helper method
	 * @param msg	The message you want to print.
	 */
	public static void p(String msg) {
		System.out.print(msg);
	}

	/**
	 * Print helper method
	 * @param msg	The message you want to print in a line.
	 */
	public static void pl(String msg) {
		System.out.println(msg);
	}
}


