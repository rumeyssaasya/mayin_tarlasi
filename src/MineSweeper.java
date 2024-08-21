import java.util.Random;
import java.util.Scanner;

public class MineSweeper {
    private int rows;
    private int cols;
    private String[][] gameBoard;
    private String[][] mineBoard;

    // Oyun başlangıç metodunu tanımlar
    public void initializeGame() {
        Scanner scanner = new Scanner(System.in);

        // Kullanıcıdan geçerli satır ve sütun sayıları alınır
        do {
            System.out.print("Enter the number of rows (minimum 2): ");
            rows = scanner.nextInt();
            System.out.print("Enter the number of columns (minimum 2): ");
            cols = scanner.nextInt();
        } while (!isValidSize(rows, cols));

        // Matrisleri oluşturur
        gameBoard = new String[rows][cols];
        mineBoard = new String[rows][cols];

        // Matrisleri başlangıç değerleri ile doldurur
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                gameBoard[i][j] = "-";
                mineBoard[i][j] = "-";
            }
        }

        // Mayınları yerleştirir
        placeMines();
    }

    // Boyutların geçerli olup olmadığını kontrol eder
    private boolean isValidSize(int rows, int cols) {
        return rows >= 2 && cols >= 2;
    }

    // Mayınları rastgele yerleştirir
    private void placeMines() {
        Random random = new Random();
        int totalMines = (rows * cols) / 4;
        int placedMines = 0;

        while (placedMines < totalMines) {
            int randomRow = random.nextInt(rows);
            int randomCol = random.nextInt(cols);

            // Eğer bu hücrede zaten mayın yoksa, yerleştirir
            if (!mineBoard[randomRow][randomCol].equals("*")) {
                mineBoard[randomRow][randomCol] = "*";
                placedMines++;
            }
        }
    }

    // Kullanıcının seçtiği koordinatın geçerli olup olmadığını kontrol eder
    private boolean isValidCoordinate(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    // Kullanıcının seçtiği koordinatın mayın olup olmadığını kontrol eder
    public boolean makeMove(int row, int col) {
        if (!isValidCoordinate(row, col)) {
            System.out.println("Invalid coordinates. Try again.");
            return false;
        }

        if (gameBoard[row][col].equals("-")) {
            if (mineBoard[row][col].equals("*")) {
                System.out.println("Game Over! You hit a mine.");
                revealBoard(); // Mayınları ve açılan hücreleri gösterir
                return true; // Oyunun bittiğini belirtir
            } else {
                int mineCount = countMinesAround(row, col);
                gameBoard[row][col] = Integer.toString(mineCount);
                // Daha fazla hücre açma işlemleri eklenebilir
            }
        } else {
            System.out.println("This cell has already been opened. Try another cell.");
        }

        return false; // Oyun bitmemişse false döner
    }


    // Bir hücrenin çevresindeki mayın sayısını hesaplar
    private int countMinesAround(int row, int col) {
        int count = 0;
        for (int r = row - 1; r <= row + 1; r++) {
            for (int c = col - 1; c <= col + 1; c++) {
                if (r >= 0 && r < rows && c >= 0 && c < cols && mineBoard[r][c].equals("*")) {
                    count++;
                }
            }
        }
        return count;
    }

    // Oyun tahtasını ekrana yazdırır
    public void printBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(gameBoard[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void revealBoard() {
        System.out.println("Game Over! Here's the final board:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mineBoard[i][j].equals("*")) {
                    System.out.print("* ");
                } else if (gameBoard[i][j].equals("-")) {
                    System.out.print("- ");
                } else {
                    System.out.print(gameBoard[i][j] + " ");
                }
            }
            System.out.println();
        }
    }


}
