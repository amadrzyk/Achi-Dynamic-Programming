import java.util.Arrays;

public class Achi {

	private char[][] gameBoard;
	private int boardSize;

	/*
	   The first parameter specifies the size of the game board, and the second one is the maximum level
	   of the game tree that will be explored by the program.
	 */
	public Achi (int board_size, int max_levels){
		gameBoard = new char[board_size][board_size];
		boardSize = board_size;

		// INITIALIZE EACH ROW'S CHAR TO A SPACE
		for (char[] row : gameBoard){
			Arrays.fill(row, ' ');
		}
	}

	// Sets size to 7919 (prime number between 5000 and 10000)
	public Dictionary createDictionary(){
		return new Dictionary(7919);
	}

	// IF FOUND, RETURN THAT PRE-COMPUTED SCORE, OR -1 IF NOT FOUND
	public int repeatedConfig(Dictionary configurations){
		return configurations.find(currentBoardToString());
	}

	public void insertConfig(Dictionary configurations, int score){
		ConfigData pair = new ConfigData(currentBoardToString(), score);

		try {
			configurations.insert(pair);
		} catch (DictionaryException e){
			e.getMessage();
		}
	}

	public void storePlay(int row, int col, char symbol){
		gameBoard[row][col] = symbol;
	}

	public boolean tileIsEmpty (int row, int col){
		return (gameBoard[row][col] == ' ') ? true : false;
	}

	public boolean tileIsComputer (int row, int col){
		return (gameBoard[row][col] == 'O') ? true : false;
	}

	public boolean tileIsHuman (int row, int col){
		return (gameBoard[row][col] == 'X') ? true : false;
	}

	public boolean wins (char symbol){
		// CREATE A WINNING COMBINATION OF SYMBOLS, OF GAMEBOARD'S LENGTH
		char[] array = new char[boardSize];
		Arrays.fill(array, symbol);
		String winningRow = String.valueOf(array);

		// CHECK IF ANY ROW IS A WINNING ROW
		for (char [] row : gameBoard){
			if (String.valueOf(row).equals(winningRow)) return true;
		}

		// ATTRIBUTES FOR THE FOLLOWING FOR LOOPS
		String currRow = "";

		// CHECK IF ANY COLUMN IS A WINNING ROW
		for (int j = 0; j < boardSize; j++){
			currRow = "";
			for (int i = 0; i < boardSize; i++){
				currRow += gameBoard[i][j];
			}
			if (currRow.equals(winningRow)) return true;
		}

		// CHECK IF FORWARD DIAGONAL IS A WINNING ROW
		currRow = "";
		int j = 0;
		for (int i = 0; i < boardSize; i++){
			currRow += gameBoard[i][j];
			j++;
		}
		if (currRow.equals(winningRow)) return true;

		// CHECK IF BACKWARD DIAGONAL IS A WINNING ROW
		currRow = "";
		j = boardSize - 1;
		for (int i = 0; i < boardSize; i++){
			currRow += gameBoard[i][j];
			j--;
		}
		if (currRow.equals(winningRow)) return true;

		// IF PREVIOUS RETURN STATEMENT WAS NEVER REACHED
		return false;
	}

	public boolean isDraw(char symbol){

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
			// if left (corner)
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
			// if right (corner)
			else if (sJ == n){
				if (gameBoard[sI][sJ-1] != symbol && 
						gameBoard[sI+1][sJ-1] != symbol &&
						gameBoard[sI+1][sJ] != symbol) return true;
			}
		}
		// if middle
		else if (sI < n){
			// if left (side)
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
			// if right (side)
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
			// if left (corner)
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
			// if right (corner)
			else if (sJ == n){
				if (gameBoard[sI-1][sJ-1] != symbol && 
						gameBoard[sI-1][sJ] != symbol &&
						gameBoard[sI][sJ-1] != symbol) return true;
			}
		}

		// OTHERWISE
		return false;
	}

	public int evalBoard(char symbol){
		if (wins('O')) return 3;
		else if (wins('X')) return 0;
		else if (isDraw(symbol)) return 2;
		else return 1;
	}

	private String currentBoardToString(){
		String boardString = "";
		for (char[] row : gameBoard){
			boardString += String.valueOf(row);
		}
		return boardString;
	}
}
