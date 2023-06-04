package patterns.backtracking;

public class SudokuSolver {
    // Helper function to check if a given number is valid in the current cell
    private static boolean isValid(char[][] board, int row, int col, char num) {
        // Check if we already have the same number in the same row, col or box
        for (int x = 0; x < 9; x++) {
            if (board[row][x] == num) {
                return false; // Check if the same number is in the same row
            }
            if (board[x][col] == num) {
                return false; // Check if the same number is in the same col
            }
            if (board[(row / 3) * 3 + x / 3][(col / 3) * 3 + x % 3] == num) {
                return false; // Check if the same number is in the same 3x3 box
            }
        }
        return true;
    }

    public static boolean solveSudoku(char[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == '.') { // If we find an empty cell
                    for (int num = 1; num <= 9; num++) { // Try every number from 1-9
                        if (isValid(board, row, col, (char) (num + '0'))) { // Check if the number is valid in the current cell
                            board[row][col] = (char) (num + '0'); // If it is valid, fill the cell with the number
                            if (solveSudoku(board)) { // Recursively call the function to solve the rest of the board
                                return true;
                            } else { // If the current number doesn't lead to a solution, backtrack by emptying the cell
                                board[row][col] = '.';
                            }
                        }
                    }
                    return false; // If we have tried every number and none of them lead to a solution, return false
                }
            }
        }
        return true; // If the board is completely filled, return true
    }
    public static void main(String[] args) {

        // Test case 1
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        System.out.println(SudokuSolver.solveSudoku(board));
        // expected output:  5 3 4 6 7 8 9 1 2
        //                   6 7 2 1 9 5 3 4 8
        //                   1 9 8 3 4 2 5 6 7
        //                   8 5 9 7 6 1 4 2 3
        //                   4 2 6 8 5 3 7 9 1
        //                   7 1 3 9 2 4 8 5 6
        //                   9 6 1 5 3 7 2 8 4
        //                   2 8 7 4 1 9 6 3 5
        //                   3 4 5 2 8 6 1 7 9
        for (int i = 0; i < 9; i++) {
            System.out.print("{");
            for (int j = 0; j < 9; j++) {
                System.out.print("'" + board[i][j] + "'', ");
            }
            System.out.print("},");
            System.out.println();
        }

        // Test case 2
        board = new char[][] {
                {'8', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '3', '6', '.', '.', '.', '.', '.'},
                {'.', '7', '.', '.', '9', '.', '2', '.', '.'},
                {'.', '5', '.', '.', '.', '7', '.', '.', '.'},
                {'.', '.', '.', '.', '4', '5', '7', '.', '.'},
                {'.', '.', '.', '1', '.', '.', '.', '3', '.'},
                {'.', '.', '1', '.', '.', '.', '.', '6', '8'},
                {'.', '.', '8', '5', '.', '.', '.', '1', '.'},
                {'.', '9', '.', '.', '.', '.', '4', '.', '.'}
        };

        System.out.println(solveSudoku(board));
        // expected output:  8 1 2 7 5 3 6 4 9
        //                   9 4 3 6 8 2 1 7 5
        //                   6 7 5 4 9 1 2 8 3
        //                   1 5 4 2 3 7 8 9 6
        //                   3 6 9 8 4 5 7 2 1
        //                   2 8 7 1 6 9 5 3 4
        //                   5 2 1 9 7 4 3 6 8
        //                   4 3 8 5 2 6 9 1 7
        //                   7 9 6 3 1 8 4 5 2
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
