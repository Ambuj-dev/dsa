package patterns.backtracking;

public class WordSearch {
    public static boolean dfs(char[][] board, String word, int i, int j, int k) {
        // check if current coordinates are out of grid or the current cell doesn't
        // match the current character of the word
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(k)) {
            return false;
        }
        // check if we have reached the end of the word
        if (k == word.length() - 1) {
            return true;
        }
        // mark the current cell as visited by replacing it with '/'
        char tmp = board[i][j];
        board[i][j] = '/';
        // check all 4 adjacent cells recursively
        boolean res = dfs(board, word, i + 1, j, k + 1) ||
                dfs(board, word, i - 1, j, k + 1) ||
                dfs(board, word, i, j + 1, k + 1) ||
                dfs(board, word, i, j - 1, k + 1);
        // backtrack by replacing the current cell with its original value
        board[i][j] = tmp;
        return res;
    }

    public static boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // start the search from every cell
                if (dfs(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // Test Case 1
        char[][] board1 = {
                { 'A', 'B', 'C', 'E' },
                { 'S', 'F', 'C', 'S' },
                { 'A', 'D', 'E', 'E' }
        };
        String word1 = "ABCCED";
        System.out.println(exist(board1, word1)); // expected output: true

        // Test Case 2
        char[][] board2 = {
                { 'A', 'B', 'C', 'E' },
                { 'S', 'F', 'C', 'S' },
                { 'A', 'D', 'E', 'E' }
        };
        String word2 = "SEE";
        System.out.println(exist(board2, word2)); // expected output: true

        // Test Case 3
        char[][] board3 = {
                { 'A', 'B', 'C', 'E' },
                { 'S', 'F', 'C', 'S' },
                { 'A', 'D', 'E', 'E' }
        };
        String word3 = "ABCB";
        System.out.println(exist(board3, word3)); // expected output: false

        char[][] board4 = { { 'a', 'a' } };
        String word4 = "aaa";
        System.out.println(exist(board4, word4)); // expected output: false

        char[][] board5 = { { 'a' } };
        String word5 = "a";
        System.out.println(exist(board5, word5)); // expected output: true

        char[][] board6 = {
                { 'a', 'b', 'c', 'd', 'e' },
                { 'f', 'g', 'h', 'i', 'j' },
                { 'k', 'l', 'm', 'n', 'o' },
                { 'p', 'q', 'r', 's', 't' },
                { 'u', 'v', 'w', 'x', 'y' },
                { 'z', 'a', 'b', 'c', 'd' }
        };
        String word6 = "abcde";
        System.out.println(exist(board6, word6)); // expected output: true

        char[][] board7 = {
                { 'a', 'b', 'c', 'd', 'e' },
                { 'f', 'g', 'h', 'i', 'j' },
                { 'k', 'l', 'm', 'n', 'o' },
                { 'p', 'q', 'r', 's', 't' },
                { 'u', 'v', 'w', 'x', 'y' },
                { 'z', 'a', 'b', 'c', 'd' }
        };
        String word7 = "zabcd";
        System.out.println(exist(board7, word7)); // expected output: true
    }
}
