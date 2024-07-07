import java.util.Scanner;

public class Task4 {

    public static void main(String[] args) {
        int[][] board = new int[9][9];
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Conditons For Sudoku Puzzle:");
        System.out.println("1.Each of the digits 1-9 must occur exactly once in each ROW.");
        System.out.println("2.Each of the digits 1-9 must occur exactly once in each COLUMN.");
        System.out.println("3.Each of the digits 1-9 must occur exactly once in each of the 3x3 sub-boxes of the grid.\n");
        System.out.println("Board Size is 9X9.\n");
        System.out.println("Enter the Puzzle input as (1 0 3....)");
        System.out.println("Enter the Sudoku puzzle to solve (use 0 for empty cells):");
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = scanner.nextInt();
            }
        }

        scanner.close();

        if (solveSudoku(board)) {
            printBoard(board);
        } else {
            System.out.println("No solution exists");
        }
    }

    public static boolean solveSudoku(int[][] board) {
        int row = -1;
        int col = -1;
        boolean isEmpty = true;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    row = i;
                    col = j;
                    isEmpty = false;
                    break;
                }
            }
            if (!isEmpty) {
                break;
            }
        }

        if (isEmpty) {
            return true;
        }

        for (int num = 1; num <= 9; num++) {
            if (isSafe(board, row, col, num)) {
                board[row][col] = num;
                if (solveSudoku(board)) {
                    return true;
                }
                board[row][col] = 0;
            }
        }
        return false;
    }

    public static boolean isSafe(int[][] board, int row, int col, int num) {
        for (int d = 0; d < 9; d++) {
            if (board[row][d] == num) {
                return false;
            }
        }

        for (int r = 0; r < 9; r++) {
            if (board[r][col] == num) {
                return false;
            }
        }

        int sqrt = (int) Math.sqrt(9);
        int boxRowStart = row - row % sqrt;
        int boxColStart = col - col % sqrt;

        for (int r = boxRowStart; r < boxRowStart + sqrt; r++) {
            for (int d = boxColStart; d < boxColStart + sqrt; d++) {
                if (board[r][d] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void printBoard(int[][] board) {
    	
    	System.out.println("\nSolved Sudoku Puzzle is: ");
        for (int r = 0; r < 9; r++) {
            for (int d = 0; d < 9; d++) {
                System.out.print(board[r][d]);
                System.out.print(" ");
            }
            System.out.print("\n");

            if ((r + 1) % 3 == 0) {
                System.out.print("");
            }
        }
    }
}
