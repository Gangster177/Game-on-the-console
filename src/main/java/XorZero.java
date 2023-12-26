import java.util.Scanner;

class XorZero {
    public static final int SIZE = 3;
    public static final char EMPTY = '_';
    public static final char CROSS = 'X';
    public static final char ZERO = 'O';

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        char[][] field = new char[SIZE][SIZE];
        boolean isCrossTurn = true;

        // заполняем поле пробелами для визуализации
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                field[i][j] = EMPTY;
            }
        }
        while (true) {
            System.out.println("Ходят " + (isCrossTurn ? "крестики!" : "нолики!") + " Введи координаты:"); //1 2
            printField(field);
            String input = scan.nextLine();
            String[] parts = input.split(" "); // ["1", "2"]
            int x = Integer.parseInt(parts[0]) - 1;  // -1 для того чтобы введенные пользователем начало координат = 1 для программы было "0" ячейкой массива
            int y = Integer.parseInt(parts[1]) - 1;
            if (field[x][y] != EMPTY) {
                continue;
                // на тот случай если ячейка не свободна,т.е. не имеет символ EMPTY = "_"
            }
            field[x][y] = isCrossTurn ? CROSS : ZERO;
            if (isWin(field, isCrossTurn ? CROSS : ZERO)) {
                System.out.println("Выйграли " + (isCrossTurn ? "крестики!" : "нолики!"));
                // завершить игру
                break;
            } else {
                // TODO ходит другой игрок
                if (isCrossTurn) {
                    isCrossTurn = false;
                } else {
                    isCrossTurn = true;
                }
            }
        }
    }

    public static boolean isWin(char[][] field, char player) {
        if (checkHorisontal(field, player)
                || checkVertical(field, player)
                || checkMainDiagonal(field, player)
                || checkOptionalDiagonal(field, player)) {
            return true;
        }
        return false;
    }

    private static boolean checkOptionalDiagonal(char[][] field, char player) {
        for (int i = 0; i < field.length; i++) {
            if (field[i][field.length - 1 - i] != player) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkMainDiagonal(char[][] field, char player) {
        for (int i = 0; i < field.length; i++) {
            if (field[i][i] != player) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkVertical(char[][] field, char player) {
        for (int j = 0; j < field.length; j++) {
            int counter = 0;
            for (int i = 0; i < field[j].length; i++) {
                if (field[i][j] == player) {
                    counter++;
                }
            }
            if (counter == field.length) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkHorisontal(char[][] field, char player) {
        for (char[] row : field) {
            int counter = 0;
            for (char c : row) {
                if (c == player) {
                    counter++;
                }
            }
            if (counter == field.length) {
                return true;
            }
        }
        return false;
    }

    public static void printField(char[][] field) {
        for (char[] row : field) {
            for (char cell : row) {
                System.out.print(cell + " ");   // print__ потому что выводим в одну строку
            }
            System.out.println(" ");  // переход на новую строку
        }
    }
}