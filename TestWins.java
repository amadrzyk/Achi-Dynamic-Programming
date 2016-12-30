import java.util.Arrays;

public class TestWins {
	
	static int boardSize = 3;
	static char[][] gameBoard = {{'X','O','O'},
								 {'O','X','O'},
								 {'O','X','O'}};
	
	public static void main(String[] args){
		System.out.println(wins('O'));
	}
	
	public static boolean wins (char symbol){
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
		
		// IF RETURN STATEMENT WAS NEVER REACHED
		return false;
	}

}
