/**
 * This class generates boards and prints them out.
 * Name: Abubakar Saad
 * Student id: 5489380
 */

package board;

public class Board {

	
	public Board(){
		
	}
	/**
	 * This method creates a board and fill it with 0. "0" represents blank spaces on the board
	 * @param board - int array that given as a board
	 * @return int array which is board
	 */
	public int[][] creatingBoard(int[][] board){
		for(int i=0; i<board.length; i++){
			for(int j=0; j<board[i].length; j++){
				board[i][j] = 0;
			}
		}
		return board;
	}
	
	/**
	 * This method prints out the board
	 * @param board - int array
	 * @return int array
	 */
	public int[][] print(int[][] board){
		for(int i=0; i<board.length; i++){
			for(int j=0; j<board[i].length; j++){
				System.out.print(board[i][j]);
				
			}
			System.out.println("");
		}
		
		return board;
	}
	
	/**
	 * This method checks if there are collision with queen thats been placed on the board
	 * @param board
	 * @param row
	 * @param col
	 * @return
	 */
//	public int isplaceable(int[][] board, int row, int col){
//		int track_c = 0;
////		check all the columns in the ith row if they have a queen
//			for(int i=col+1; i<board.length; i++){
////				System.out.println("row: " + row + " col: " + col + " i: " + i);
//				if(board[row][i] == 1){
//					if(collision == false){
//						track_c++;
//					}
//					break;
//				}
//			}
//			
//			for(int j=row+1; j<board.length; j++){
//				if(board[j][col] == 1){
//					if(collision == false){
//						track_c++;
//					}
//					break;
//				}
//			}
//		
////			check for diagonals
//			for(int i=1;i<board.length; i++){
//				if(row+i<board.length && col+i<board.length){
//					if(board[row+i][col+i] == 1){
//						if(collision == false){
//							track_c++;
//						}else{
//							collision = true;
//						}
//						break;
//					}
//				}	
//			}
//			for(int i=1; i<board.length; i++){
//				//bottom left
//				if(row+i<board.length && col-i>=0){
//					if(board[row+i][col-i] == 1){
//						if(collision == false){
//							track_c++;
//						}else{
//							collision = true;
//						}
//						break;
//					}
//				}
//			}
//			// dont need top right and top left;
//		
//		
//		// return true if queen is placeable
//		return track_c;
//	}
}
