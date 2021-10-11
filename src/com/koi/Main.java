package com.koi;

public class Main {

    private static final int GRID_SIZE = 9;

    public static void main(String[] args) {
        int[][] board = {
            {0, 1, 0, 2, 0, 0, 0, 0, 0},
            {0, 3, 0, 0, 4, 0, 0, 0, 5},
            {0, 0, 0, 0, 0, 6, 0, 7, 0},
            {1, 0, 2, 0, 0, 8, 0, 0, 0},
            {5, 0, 0, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 5, 0, 0, 9, 0, 4},
            {0, 7, 0, 3, 0, 0, 0, 0, 0},
            {8, 0, 0, 0, 1, 0, 0, 2, 0},
            {0, 0, 0, 0, 0, 9, 0, 6, 0},
        };

        if (solveBoard(board)) {
            printBoard(board);
            System.out.println("Sudoku have solved successfully !!!");
        } else {
            System.out.println("Sudoku can be solved");
        }
    }

    private static void printBoard(int[][] board) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("---------------------");
            }
            for (int j = 0; j < GRID_SIZE; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("| ");
                }
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean solveBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int column = 0; column < GRID_SIZE; column++) {
                if (board[row][column] == 0) {
                    for (int number = 1; number <= GRID_SIZE; number++) {
                        if (isValidPosition(board, number, row, column)) {
                            board[row][column] = number;

                            if (solveBoard(board)) {
                                return true;
                            } else {
                                board[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isInRow(int[][] board, int number, int row) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    public static boolean isInColumn(int[][] board, int number, int column) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[i][column] == number) {
                return true;
            }
        }
        return false;
    }

    public static boolean isInSquare(int[][] board, int number, int row, int column) {
        // Get top left position of square
        int currentSquareX = row - row % 3;
        int currentSquareY = column - column % 3;
        for (int i = currentSquareX; i < currentSquareX + 3; i++) {
            for (int j = currentSquareY; j < currentSquareY + 3; j++) {
                if (board[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isValidPosition(int[][] board, int number, int row, int column) {
        return !isInRow(board, number, row) &&
            !isInColumn(board, number, column) &&
            !isInSquare(board, number, row, column);
    }
}
