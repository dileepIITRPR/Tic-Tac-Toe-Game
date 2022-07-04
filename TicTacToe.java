import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();
    static ArrayList<Integer> player2Positions = new ArrayList<Integer>();

    public static void main(String[] args) {
        char[][] gameBoard = { { ' ', '|', ' ', '|', ' ' },
                { '-', '+', '-', '+', '-' },
                { ' ', '|', ' ', '|', ' ' },
                { '-', '+', '-', '+', '-' },
                { ' ', '|', ' ', '|', ' ' } };

        char[][] demoGameBoard = { { '1', '|', '2', '|', '3' },
                { '-', '+', '-', '+', '-' },
                { '4', '|', '5', '|', '6' },
                { '-', '+', '-', '+', '-' },
                { '7', '|', '8', '|', '9' } };

        System.out.println(
                "__Following is the TicTacToe game board with place value numbers kindly enter the respective place value numbers when asked__");
        printGameBoard(demoGameBoard);
        System.out.println("This is the blank gameboard for playing ---->");
        printGameBoard(gameBoard);
        System.out.println("__Type 1 if you want to play with CPU or type 0 if you want to play with Your Friend__");
        Scanner sc = new Scanner(System.in);
        int opt = sc.nextInt();

        //if statement for player-vs-cpu game
        if (opt == 1) {
            while (true) {
                Scanner scan = new Scanner(System.in);
                System.out.print("__Enter your place value(1-9): ");
                int playerPos = scan.nextInt();
                while (playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {
                    System.out.println("__Entered Position is already taken! Enter a correct position__");
                    playerPos = scan.nextInt();
                }
                placePiece(gameBoard, playerPos, "player");
                String result = checkWineer();
                if (result.length() > 0) {
                    System.out.println(result);
                    break;
                }

                Random rand = new Random();
                int cpuPos = rand.nextInt(9) + 1;
                while (playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
                    cpuPos = rand.nextInt(9) + 1;
                }
                placePiece(gameBoard, cpuPos, "cpu");

                printGameBoard(gameBoard);
                result = checkWineer();
                if (result.length() > 0) {
                    System.out.println(result);
                    break;
                }
            }
            //if statement for player-vs-player game
        } else if (opt == 0) {
            while (true) {
                Scanner scan = new Scanner(System.in);
                System.out.print("__Player 1, Enter your place value(1-9): ");
                int playerPos = scan.nextInt();
                while (playerPositions.contains(playerPos) || player2Positions.contains(playerPos)) {
                    System.out.println("__Entered Position is already taken! Enter a correct position__");
                    playerPos = scan.nextInt();
                }
                placePiece(gameBoard, playerPos, "player");
                printGameBoard(gameBoard);
                String result = checkWineer();
                if (result.length() > 0) {
                    System.out.println(result);
                    break;
                }

                Scanner scan2 = new Scanner(System.in);
                System.out.print("__Player 2, Enter your place value(1-9): ");
                int player2Pos = scan2.nextInt();
                while (playerPositions.contains(player2Pos) || player2Positions.contains(player2Pos)) {
                    System.out.println("__Entered Position is already taken! Enter a correct position__");
                    player2Pos = scan2.nextInt();
                }

                placePiece(gameBoard, player2Pos, "player2");

                printGameBoard(gameBoard);
                result = checkWineer();
                if (result.length() > 0) {
                    System.out.println(result);
                    break;
                }
            }
        } else {
            System.out.println("__Entered Value is Invalid (kindly enter either 1 or 0)__");
        }

    }
    //to print the gameboard
    public static void printGameBoard(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
    //to place the player's symbol in the gameboard
    public static void placePiece(char[][] gameBoard, int pos, String user) {

        char symbol = ' ';
        if (user.equals("player")) {
            symbol = 'X';
            playerPositions.add(pos);
        } else if (user.equals("cpu")) {
            symbol = 'C';
            cpuPositions.add(pos);
        } else if (user.equals("player2")) {
            symbol = 'O';
            player2Positions.add(pos);
        }

        switch (pos) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }
    //to check the winner of the game 
    public static String checkWineer() {

        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(3, 5, 7);

        List<List> Winning = new ArrayList<List>();
        Winning.add(topRow);
        Winning.add(midRow);
        Winning.add(botRow);
        Winning.add(leftCol);
        Winning.add(midCol);
        Winning.add(rightCol);
        Winning.add(cross1);
        Winning.add(cross2);

        for (List l : Winning) {
            if (playerPositions.containsAll(l)) {
                return "___Congratulations Player1 You Won!___";
            } else if (cpuPositions.containsAll(l)) {
                return "___CPU Won!___";
            } else if (player2Positions.containsAll(l)) {
                return "___Congratulations Player2 You won!___";
            } else if (playerPositions.size() + cpuPositions.size() == 9) {
                return "___Game Draw!___";
            }
        }

        return "";
    }
}