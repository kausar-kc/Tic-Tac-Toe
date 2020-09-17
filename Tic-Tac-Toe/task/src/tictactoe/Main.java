package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    static String GAME_NOT_FINISHED = "Game not finished";
    static String DRAW = "Draw";
    static String X_WINS = "X wins";
    static String O_WINS = "O wins";
    static String IMPOSSIBLE = "Impossible";

    static String values;

    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        values = "         ";

        printGame();

        String result = GAME_NOT_FINISHED;
        char player = 'X';

        while (result.equals(GAME_NOT_FINISHED)) {

            boolean noErrors = true;
            int firstCoordinate = 0;
            int secondCoordinate = 0;
            int index = 0;

            while (noErrors) {
                System.out.print("Enter the coordinates: ");
                try {
                    firstCoordinate = scanner.nextInt();
                    secondCoordinate = scanner.nextInt();

                    if (firstCoordinate < 1 || firstCoordinate > 3 ||
                            secondCoordinate < 1 || secondCoordinate > 3) {
                        System.out.println("Coordinates should be from 1 to 3!");
                    } else {
                        if (firstCoordinate == 1 && secondCoordinate == 1) {
                            index = 6;
                        } else if (firstCoordinate == 1 && secondCoordinate == 2) {
                            index = 3;
                        } else if (firstCoordinate == 1 && secondCoordinate == 3) {
                            index = 0;
                        } else if (firstCoordinate == 2 && secondCoordinate == 1) {
                            index = 7;
                        } else if (firstCoordinate == 2 && secondCoordinate == 2) {
                            index = 4;
                        } else if (firstCoordinate == 2 && secondCoordinate == 3) {
                            index = 1;
                        } else if (firstCoordinate == 3 && secondCoordinate == 1) {
                            index = 8;
                        } else if (firstCoordinate == 3 && secondCoordinate == 2) {
                            index = 5;
                        } else if (firstCoordinate == 3 && secondCoordinate == 3) {
                            index = 2;
                        }

                        if (values.charAt(index) != ' ') {
                            System.out.println("This cell is occupied! Choose another one!");
                        } else {
                            noErrors = false;
                        }
                    }
                } catch (InputMismatchException ex) {
                    System.out.println("You should enter numbers!");
                    scanner.nextLine();
                }
            }

            if (player == 'O') {

                values = values.substring(0, index) + player + values.substring(index + 1);
                player = 'X';
            } else {
                values = values.substring(0, index) + player + values.substring(index + 1);
                player = 'O';
            }

            printGame();

            result = checkResult();

        }

        System.out.println(result);

    }

    static String checkResult() {
        boolean three_x = false;
        boolean three_o = false;

        if ((count(values, 'X') - count(values, 'O') > 1)
                || (count(values, 'O') - count(values, 'X') > 1)) {
            System.out.println(IMPOSSIBLE);
            return "";
        }

        if (rowCheck(0)) {
            if (values.charAt(0) == 'X') {
                three_x = true;
            } else if (values.charAt(0) == 'O') {
                three_o = true;
            }

        }
        if (rowCheck(3)) {
            if (values.charAt(3) == 'X') {
                three_x = true;
            } else if (values.charAt(3) == 'O') {
                three_o = true;
            }
        }
        if (rowCheck(6)) {
            if (values.charAt(6) == 'X') {
                three_x = true;
            } else if (values.charAt(6) == 'O') {
                three_o = true;
            }
        }

        if (colCheck(0)) {
            if (values.charAt(0) == 'X') {
                three_x = true;
            } else if (values.charAt(0) == 'O') {
                three_o = true;
            }
        }

        if (colCheck(1)) {
            if (values.charAt(1) == 'X') {
                three_x = true;
            } else if (values.charAt(1) == 'O') {
                three_o = true;
            }
        }

        if (colCheck(2)) {
            if (values.charAt(2) == 'X') {
                three_x = true;
            } else if (values.charAt(2) == 'O') {
                three_o = true;
            }
        }

        if (diaCheck(0, 8)) {
            if (values.charAt(0) == 'X') {
                three_x = true;
            } else if (values.charAt(0) == 'O') {
                three_o = true;
            }
        }

        if (diaCheck(2, 6)) {
            if (values.charAt(2) == 'X') {
                three_x = true;
            } else if (values.charAt(2) == 'O') {
                three_o = true;
            }
        }

        if (three_x && three_o) {
            return IMPOSSIBLE;
        } else if (three_x) {
            return X_WINS;
        } else if (three_o) {
            return O_WINS;
        } else if (values.contains("_") || values.contains(" ")) {
            return GAME_NOT_FINISHED;
        } else {
            return DRAW;
        }
    }

    static void printGame() {
        System.out.println("---------");
        for (int i = 0; i < 9; i += 3) {
            System.out.print("| ");
            System.out.print(values.charAt(i) + " " + values.charAt(i + 1)
                    + " " + values.charAt(i + 2));
            System.out.println(" |");
        }
        System.out.println("---------");
    }

    static boolean rowCheck(int index) {
        return values.charAt(index) == values.charAt(index + 1) &&
                values.charAt(index) == values.charAt(index + 2);
    }

    static boolean colCheck(int index) {
        return values.charAt(index) == values.charAt(index + 3) &&
                values.charAt(index) == values.charAt(index + 6);
    }

    static boolean diaCheck(int index1, int index3) {
        return values.charAt(index1) == values.charAt(4)
                && values.charAt(index1) == values.charAt(index3);
    }

    static int count(String data, char letter) {
        int count = 0;

        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) == letter) {
                count++;
            }
        }

        return count;
    }
}
