import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        MineSweeper game = new MineSweeper();
        game.initializeGame();

        // Oyunun mevcut durumunu ekrana yazdırır
        System.out.println("Initial Game Board:");
        game.printBoard();

        // Örnek hamle yapma
        Scanner scanner = new Scanner(System.in);
        boolean gameOver = false;
        while (!gameOver) {
            System.out.print("Enter row (0-based index): pres q to exit ");
            int row = scanner.nextInt();
            if(row=='q')
                break;
            System.out.print("Enter column (0-based index): ");
            int col = scanner.nextInt();
            if(col=='q')
                break;

            gameOver = game.makeMove(row, col);


        }
    }
}