/** 
 * @author Xiang Ao, Shijie Xu
 * @since Mar.4, 2019
 * 
 * This class is main class
 * 
 * CS213 Software Methodology Project 2: A playable chess.
 */
package chess;

import java.util.ArrayList;
import java.util.Scanner;

public class Chess {
	static ArrayList<BlockNode> board;
	static InitialBoard ib = new InitialBoard();
	static PrintBoard pb = new PrintBoard();
	static SetPiece sp = new SetPiece();
	static SpecialMovement sm = new SpecialMovement();
	static Checkmate cm = new Checkmate();

	static int stringToIndex(String input) {
		int selectLetter = (int) input.charAt(0) - 97;
		int selectNumber = 7 - ((int) input.charAt(1) - 49);
		int out = selectNumber * 8 + selectLetter;
		return out;
	}

	/**
	 * Main method
	 * 
	 *@param args unused
	 */
	public static void main(String[] args) {
		board = ib.initial();
		pb.printBoard(board);
		Scanner sc = new Scanner(System.in);
		boolean end = false;
		boolean white, black;
		String input, s, m, info, opinion;
		int select, move;
		while (!end) {
			white = true;
			black = true;
			while (white) {
				cm.clearCheckStatus(board);
				if(!cm.checkCheck(cm.kingFinder(1, board), false, board)) {
					if(!cm.checkAround(cm.kingFinder(1, board), board) 
							&& !cm.protectKing(cm.kingFinder(1, board), board)) {
						end = true;
						black = false;
						pl("Black wins");
						break;
					}
				}
				pl("");
				p("White's move: ");
				input = sc.nextLine();
				s = input.substring(0, 2);
				m = input.substring(3, 5);
				info = input.substring(5);

				if (input.equals("resign")) {
					pl("Black wins");
					end = true;
					black = false;
					break;
				} else {
					select = stringToIndex(s);
					move = stringToIndex(m);
					pl(s+" "+m);
					pl("");
					sm.checkEnpassant(1, board);
					if (sm.castling(select, move, board)) {
						white = false;
						pb.printBoard(board);
						continue;
					} else if (sp.fileRank(true, select, move, board)) {
						sm.promotion(move, info, board);
						white = false;
						pb.printBoard(board);
					}
					if (info.equals(" draw?")) {
						pl("Agree?(y/n)");
						opinion = sc.nextLine();
						if (opinion.equals("y")) {
							pl("draw");
							end = true;
							black = false;
							pb.printBoard(board);
							break;
						} else {
							pl("Opponent rejected");
						}
					}
					if(cm.kingFinder(2, board)==-1) {
						end = true;
						black = false;
						pl("White wins");
						break;
					}
					cm.checkCheck(cm.kingFinder(1, board), true, board);
					cm.checkCheck(cm.kingFinder(2, board), true, board);
				}
			}
			while (black) {
				cm.clearCheckStatus(board);
				if(!cm.checkCheck(cm.kingFinder(2, board), false, board)) {
					if(!cm.checkAround(cm.kingFinder(2, board), board) 
							&& !cm.protectKing(cm.kingFinder(2, board), board)) {
						end = true;
						white = false;
						pl("White wins");
						break;
					}
				}
				pl("");
				p("Black's move: ");
				input = sc.nextLine();
				s = input.substring(0, 2);
				m = input.substring(3, 5);
				info = input.substring(5);
				if (input.equals("resign")) {
					pl("White wins");
					end = true;
					white = false;
					break;
				} else {
					select = stringToIndex(s);
					move = stringToIndex(m);
					pl(s+" "+m);
					pl("");
					sm.checkEnpassant(2, board);
					if (sm.castling(select, move, board)) {
						black = false;
						pb.printBoard(board);
						continue;
					} else if (sp.fileRank(false, select, move, board)) {
						sm.promotion(move, info, board);
						black = false;
						pb.printBoard(board);
					}
					if (info.equals(" draw?")) {
						pl("Agree?(y/n)");
						opinion = sc.nextLine();
						if (opinion.equals("y")) {
							pl("draw");
							end = true;
							white = false;
							pb.printBoard(board);
							break;
						} else {
							pl("Opponent rejected");
						}
					}
					if(cm.kingFinder(1, board)==-1) {
						end = true;
						black = false;
						pl("Black wins");
						break;
					}
					cm.checkCheck(cm.kingFinder(1, board), true, board);
					cm.checkCheck(cm.kingFinder(2, board), true, board);
				}
			}
		}
		sc.close();
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
