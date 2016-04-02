/**
 * This class uses Heuristic to solve N queen problem
 * Name: Abubakar Saad
 * Student id: 5489380
 */

package board;

import java.util.Random;

public class heuirsticClass{
	// Number of queen and size of the board (arrays)
	int N = 8;
	// int array
	int[][] board;
	// store_state is used for copying initial state of array
	int[][] store_state;
	// to keep track of configuration
	int count = 0;
	// collision - keeps track of collision with the same queen
	boolean collision = false;
	// board class variable to use board class methods
	Board test = new Board();
	// random generator
	Random rand = new Random();
	
	/**
	 * Initialize arrays and prints the solution to the console
	 */
	public heuirsticClass(){
		// controller the seed value
  		rand.setSeed(0);
		board = new int[N][N];
		// creates a board
		test.creatingBoard(board);
		// Place the queen on the board randomly 
		RandomlyPlaced(board);
		
		// Once solution is found, prints it out
		if(heuirtis(board) == false){
			System.out.println("No solution was found or somthing is wrong");
		}else{
			test.print(board);
		}
		System.out.println("Configuration count: " + count);
		
		
		
	}
	/**
	 * Generates a Board that place N queen randomly
	 * @param board - int array
	 */
	public void RandomlyPlaced(int[][] board){
		// random x coordinate
		int random_x;
		// random y coordinate
		int random_y;
		// keeps track of number of queens on the board
		int num_q = 0;

		while(num_q < N){
			random_x = rand.nextInt(N);
			random_y = rand.nextInt(N);
			if(board[random_x][random_y] != 1){
				board[random_x][random_y] = 1;
				num_q++;
			}
		}
	}
	
	/**
	 * This method takes a queen and place it in spot where there is only one collision or no collision
	 * @param board - int array
	 */
	public void next_state(int[][] board){
		// keeps track on when queen is placed
		boolean location = true;
		// random coordinates to select a queen from the board
		int q_x, q_y;
		while(location){
			q_x = rand.nextInt(N);
			q_y = rand.nextInt(N);
			// select a random queen from the board
			if(board[q_x][q_y] == 1){
				// random coordinates to place the queen
				int c_x, c_y;
				c_x = rand.nextInt(N);
				c_y = rand.nextInt(N);
				// checks if those random coordinates are placeable with 0 or 1 collision on the board
				// if they are places else find different coordinates
				if(isplaceable_2(board,c_x,c_y) == 0){
					if(board[c_x][c_y] != 1){
						board[c_x][c_y] = 1;
						board[q_x][q_y] = 0;
						location = false;
					}
				}else if(isplaceable_2(board,c_x,c_y) == 1){
					if(board[c_x][c_y] != 1){
						board[c_x][c_y] = 1;
						board[q_x][q_y] = 0;
						location = false;
					}
				}
			}else{
				location = true;
			}
		}
	}
	
	/**
	 * This method checks if all the directions for certain spot thats is not getting attack by other queen
	 * @param board - int array
	 * @param row - int row of the board
	 * @param col - int col of the board
	 * @return track_c - number of collision for that spot
	 */
	public int isplaceable_2(int[][] board, int row, int col){
		int track_c = 0;
		//check all the ith rows on the board
			for(int i=col+1; i<board.length; i++){
				if(board[row][i] == 1){
					if(collision == false){
						track_c++;
					}
					break;
				}
			}
		//check all the ith columns on the board
			for(int j=row+1; j<board.length; j++){
				if(board[j][col] == 1){
					if(collision == false){
						track_c++;
					}
					break;
				}
			}
		
			//check for diagonals

			for(int i=1;i<board.length; i++){
				// bottom right;
				if(row+i<board.length && col+i<board.length){
					if(board[row+i][col+i] == 1){
						if(collision == false){
							track_c++;
						}else{
							collision = true;
						}
						break;
					}
				}	
			}
			for(int i=1; i<board.length; i++){
				//bottom left
				if(row+i<board.length && col-i>=0){
					if(board[row+i][col-i] == 1){
						if(collision == false){
							track_c++;
						}else{
							collision = true;
						}
						break;
					}
				}
			}
			
			for(int i=1; i<board.length; i++){
				//top right
				if(row-i >=0 && col+i<board.length){
					if(board[row-i][col+i] == 1){
						if(collision == false){
							track_c++;
						}else{
							collision = true;
						}
						break;
					}
				}
				//top right
				if(row-i >=0 && col-i>=0){
					if(board[row-i][col-i] == 1){
						if(collision == false){
							track_c++;
						}else{
							collision = true;
						}
						break;
					}
				}
			}
		
		
		// return track_c
		return track_c;
	}

	
	/**
	 * Finds out the number of collision in a board and assign a board with a heuirtic score
	 * @param board - int array
	 * @return h_score - score of the board 
	 */
	public int heuirtis_score(int[][] board){
		int h_score=0;
		for(int i=0; i<board.length; i++){
			for(int j=0; j<board.length; j++){
				if(board[i][j] == 1){
					// gets the collision for all the queens on the board and sums it up in h_score
					h_score += isplaceable_2(board,i,j);
				}
			}
		}
		return h_score;
	}
	
	/**
	 * This method compare the heuirtis scores of the board and picks the better one to modify
	 * @param board - int array
	 */
	public boolean heuirtis(int[][] board){
		//bh_score store the score of initial board (boards that getting parsed)
		int bh_score = heuirtis_score(board);
		// sh_score store the score of next board generate by next_state method
		int sh_score;
		// checks if bh_score is 0 then return true. Found a solution
		if(bh_score == 0){
			return true;
		}else{
			// store that state of board into store_state
			store_state = board;
			// modify the board 
			next_state(board);
			// get the score of modify board
			sh_score = heuirtis_score(board);
			// compare the scores sends back "fitter" board
			if(sh_score < bh_score){
				if(heuirtis(store_state) == true){
					count++;
					return true;
				}
			}else if(bh_score < sh_score){
				if(heuirtis(board) == true){
					count++;
					return true;
				}
			}else{
				if(heuirtis(store_state) == true){
					count++;
					return true;
				}
			}
		}
		return false;
	}
	
	public static void main(String[] args){
		new heuirsticClass();
	}
	
	
}
