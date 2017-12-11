import java.util.*;
class Text15 {
    public static void main(String[] u) {
        Scanner scan = new Scanner(System.in);
        ChessBoard thegame = new ChessBoard();                 // Model object is created
        System.out.println("\nWelcome to the game\n");
            // Print the current board
        while (true) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++)
                    System.out.print("  " + thegame.getStatus(i, j).toString()); // getStatus
                System.out.println();
            }
            System.out.println();
            System.out.print("Your move: ");
            int x = scan.nextInt();
            int y = scan.nextInt();
            int i = scan.nextInt();  // get an int number from terminal window
            int j = scan.nextInt();
            thegame.move(x, y, i, j);                                 // move
            System.out.println(thegame.getMessage());         // getMessage*/
        }
    }
}