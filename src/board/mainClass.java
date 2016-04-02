/**
 * This class uses blind search to find the solution of N queen problem
 * Name: Abubakar Saad
 * Student id: 5489380
 */


package board;

public class mainClass {
	// board class variable to apply the methods
	Board test = new Board();
	// Number of queens or size of the board
	int N = 8;
	// board
	int[][] board;
	// count keeps track of how many configuration it takes to find a solution
	int count =0;
	
	/**
	 * Initailizes variables and prints a solution if exists.
	 */
	public mainClass(){
		board = new int[N][N];
		// creates a board
		test.creatingBoard(board);
		// finds a solution if exists prints it out 
		if(solution(board,0) == false){
			System.out.println("no solution or something is wrong");
		}else{
			test.print(board);
		}
		System.out.println("Configuration count: " + count);
	}
	
	/**
	 * This method checks the all the directions the queen is getting attack from
	 * @param board - int array
	 * @param row - ith-row in the board
	 * @param col - ith-col in the board
	 * @return - true/false if there are no collision then true else false
	 */
	public boolean isplaceable(int[][] board, int row, int col){
		
		// checks rows and columns
		for(int i=0; i<board.length; i++){
			// check the rows
			if(board[row][i] == 1){
				return false;
			}
			// checks the columns
			if(board[i][col] == 1){
				return false;
			}
		}
		
//		check for diagonals
		for(int i=0; i<board.length; i++){
			if(col-i >= 0 && row-i>=0){ 
				if(board[row-i][col-i] == 1){
					return false;
				}
			}
			if(col+i<board.length && row-i>=0){
				if(board[row-i][col+i] == 1){
					return false;
				}
			}
			if(row+i<board.length && col-i>=0){
				if(board[row+i][col-i] == 1){
					return false;
				}
			}
			
			if(row+i<board.length && col+i<board.length){
				if(board[row+i][col+i] == 1){
					return false;
				}
			}
		}
		
		// return true if queen is placeable
		return true;
	}
	
	/**
	 * This method runs till it places all the 
	 * @param board - int array
	 * @param col - col in the board
	 * @return - true and false. if number of columns is same as number of queens on the board it return true else false
	 */
	public boolean solution(int[][] board, int col){
		// base case - once number of columns equal to number of queens on the board return true
		if(col == N){
			return true;
		}else{
			// else keep placing queen and backtrack so they dont attack each other till number of queen equal to the columns
			for(int i=0; i<board.length; i++){
				if(isplaceable(board,i,col) == true){
					//places the queen
					board[i][col] = 1;
					// recursive call 
					if(solution(board,col+1) == true){
						return true;
					}else{
						count++;
						// backtrack if queen attack each other and place 0 otherwise
						board[i][col] = 0;
					}
					
				}
			}
		}
		
		return false;
	}
	
	public static void main(String[] args){
		new mainClass();
	}
}
