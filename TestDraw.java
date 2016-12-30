import java.util.Arrays;

public class TestDraw {
	
	static int boardSize = 3;
	static char[][] gameBoard = {{'O','X','X'},
								 {'X','O','O'},
								 {'X','O',' '}};
	
	public static void main(String[] args){
		System.out.println(isDraw('X'));
	}
	

	public static boolean isDraw(char symbol){

		int spaceCount = 0;
		int sI = -1, sJ= -1; // sI and sJ are where the space is, i.e. at (i, j)
		int n = boardSize - 1;

		// CHECK IF THERE IS ONLY ONE EMPTY SPACE ON THE BOARD (ELSE RETURN FALSE)
		for (int i = 0; i < boardSize; i++){
			for (int j = 0; j < boardSize; j++){
				if (gameBoard[i][j] == ' ') {
					spaceCount++;
					sI = i;
					sJ = j;
				}
			}
		}
		if (spaceCount != 1) return false;

		// OTHERWISE, SPACECOUNT IS 1, SO CHECK ADJACENT TILES
		// if top
		if (sI == 0){
			// if left
			if (sJ == 0){
				if (gameBoard[sI][sJ+1] != symbol && 
						gameBoard[sI+1][sJ+1] != symbol &&
						gameBoard[sI+1][sJ] != symbol) return true;
			}
			// if middle
			else if (sJ < n){
				if (gameBoard[sI][sJ+1] != symbol && 
						gameBoard[sI+1][sJ+1] != symbol &&
						gameBoard[sI+1][sJ] != symbol &&
						gameBoard[sI+1][sJ-1] != symbol &&
						gameBoard[sI][sJ-1] != symbol) return true;
			}
			// if right
			else if (sJ == n){
				if (gameBoard[sI][sJ-1] != symbol && 
						gameBoard[sI+1][sJ-1] != symbol &&
						gameBoard[sI+1][sJ] != symbol) return true;
			}
		}
		// if middle
		else if (sI < n){
			// if left
			if (sJ == 0){
				if (gameBoard[sI-1][sJ] != symbol && 
						gameBoard[sI-1][sJ+1] != symbol &&
						gameBoard[sI][sJ+1] != symbol &&
						gameBoard[sI+1][sJ+1] != symbol &&
						gameBoard[sI+1][sJ] != symbol) return true;
			}
			// if middle
			else if (sJ < n){
				if (gameBoard[sI-1][sJ-1] != symbol && 
						gameBoard[sI-1][sJ] != symbol &&
						gameBoard[sI-1][sJ+1] != symbol &&
						gameBoard[sI][sJ-1] != symbol &&
						gameBoard[sI][sJ+1] != symbol &&
						gameBoard[sI+1][sJ-1] != symbol &&
						gameBoard[sI+1][sJ] != symbol &&
						gameBoard[sI+1][sJ+1] != symbol) return true;
			}
			// if right
			else if (sJ == n){
				if (gameBoard[sI-1][sJ-1] != symbol && 
						gameBoard[sI-1][sJ] != symbol &&
						gameBoard[sI][sJ-1] != symbol &&
						gameBoard[sI+1][sJ-1] != symbol &&
						gameBoard[sI+1][sJ] != symbol) return true;
			}
		}
		// if bottom
		else if (sI == n){
			// if left
			if (sJ == 0){
				if (gameBoard[sI-1][sJ] != symbol && 
						gameBoard[sI-1][sJ+1] != symbol &&
						gameBoard[sI][sJ+1] != symbol) return true;
			}
			// if middle
			else if (sJ < n){
				if (gameBoard[sI-1][sJ-1] != symbol && 
						gameBoard[sI-1][sJ] != symbol &&
						gameBoard[sI-1][sJ+1] != symbol &&
						gameBoard[sI][sJ-1] != symbol &&
						gameBoard[sI][sJ+1] != symbol) return true;
			}
			// if right
			else if (sJ == n){
				if (gameBoard[sI-1][sJ-1] != symbol && 
						gameBoard[sI-1][sJ] != symbol &&
						gameBoard[sI][sJ-1] != symbol) return true;
			}
		}

		// OTHERWISE
		return false;
	}
}
