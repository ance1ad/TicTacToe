// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
// цвета
    public static final String RESET_COLOR = "\u001B[0m";
    public static final String BLACK_COLOR = "\u001B[30m";
    public static final String RED_COLOR = "\u001B[31m";
    public static final String GREEN_COLOR = "\u001B[32m";
 // пример: System.out.println(RED_COLOR + "Ты ввел число, ебать красава !" + RESET_COLOR);


    public static void printTableGame(int[] moves)
    {
        System.out.println("_____________");
        for (int i = 0; i<9; i++) // вывод таблы
        {
            if(moves[i] == 1)
                System.out.print("|" + RED_COLOR + " x " + RESET_COLOR);
            else if(moves[i] == 2)
                System.out.print("|" + GREEN_COLOR + " o " + RESET_COLOR);
            else
                System.out.print("| "+ (i+1) + " ");

            if((i+1)%3 == 0)
                System.out.println("|\n|-----------|");
        }
    }


    public static boolean checkWin(int[] moves) // проверка проверка 8 комбинаций
    {
        // 1 горизонт
        if(moves[0] == moves[1] && moves[1] == moves[2]) {
            if(moves[0] == 1) {System.out.println(RED_COLOR + "Крестики победили !" + RESET_COLOR); return true;}
                if(moves[0] == 2) {System.out.println(GREEN_COLOR + "Нолики победили !" + RESET_COLOR); return true;}
        }
        // 2 горизонт
        if(moves[3] == moves[4] && moves[4] == moves[5]) {
            if(moves[3] == 1) {System.out.println(RED_COLOR + "Крестики победили !" + RESET_COLOR); return true;}
            if(moves[3] == 2) {System.out.println(GREEN_COLOR + "Нолики победили !" + RESET_COLOR); return true;}
        }
        // 3 горизонт
        if(moves[6] == moves[7] && moves[7] == moves[8]) {
            if(moves[6] == 1) {System.out.println(RED_COLOR + "Крестики победили !" + RESET_COLOR); return true;}
            if(moves[6] == 2) {System.out.println(GREEN_COLOR + "Нолики победили !" + RESET_COLOR); return true;}
        }
        // 1 вертикаль
        if(moves[0] == moves[3] && moves[3] == moves[6]) {
            if(moves[0] == 1) {System.out.println(RED_COLOR + "Крестики победили !" + RESET_COLOR); return true;}
            if(moves[0] == 2) {System.out.println(GREEN_COLOR + "Нолики победили !" + RESET_COLOR); return true;}
        }
        // 2 вертикаль
        if(moves[1] == moves[4] && moves[4] == moves[7]) {
            if(moves[1] == 1) {System.out.println(RED_COLOR + "Крестики победили !" + RESET_COLOR); return true;}
            if(moves[1] == 2) {System.out.println(GREEN_COLOR + "Нолики победили !" + RESET_COLOR); return true;}
        }
        // 3 вертикаль
        if(moves[2] == moves[5] && moves[5] == moves[8]) {
            if(moves[2] == 1) {System.out.println(RED_COLOR + "Крестики победили !" + RESET_COLOR); return true;}
            if(moves[2] == 2) {System.out.println(GREEN_COLOR + "Нолики победили !" + RESET_COLOR); return true;}
        }
        //  главная диагональ
        if(moves[0] == moves[4] && moves[4] == moves[8]) {
            if(moves[0] == 1) {System.out.println(RED_COLOR + "Крестики победили !" + RESET_COLOR); return true;}
            if(moves[0] == 2) {System.out.println(GREEN_COLOR + "Нолики победили !" + RESET_COLOR); return true;}
        }
        //  побочная диагональ
        if(moves[2] == moves[4] && moves[4] == moves[6]) {
            if(moves[2] == 1) {System.out.println(RED_COLOR + "Крестики победили !" + RESET_COLOR); return true;}
            if(moves[2] == 2) {System.out.println(GREEN_COLOR + "Нолики победили !" + RESET_COLOR); return true;}
        }
        return false;
    }

    public static boolean isNumeric(String str) {
        try
        {
            Integer.parseInt(str);
            return true;
        }
        catch(NumberFormatException e)
        {
            return false;
        }
    }


    public static int tryToMove(int[] moves)
    {
        boolean checkMove = false;
        Scanner scan; String input;
        int move = 0;
        scan = new Scanner(System.in);
        while(checkMove == false)
        {
            System.out.print("Ваш ход: ");
            input = scan.nextLine();
            checkMove = isNumeric(input);
            if(checkMove == true)
            {
                move = Integer.parseInt(input);
                if(move > 9 || move < 1)
                {
                    System.out.println(RED_COLOR + "Введите значение в диапазоне от 1 до 9 !" + RESET_COLOR);
                    checkMove = false;
                }
                else if(moves[move-1] != 0)
                {
                    System.out.println(RED_COLOR + "Выбранная ячейка занята, введите другую !" + RESET_COLOR);
                    checkMove = false;
                }
            }
            else
                System.out.println(RED_COLOR + "Введите корректное число " + RESET_COLOR);
        }
        return move;
    }

    public static int min(int a, int b)
    {
        if (a<=b)
            return a;
        return b;
    }

    public static void yourStep(int whoPlay, String namePlayer, int[] gameTable)
    {
        if(whoPlay == 1)
            System.out.println(RED_COLOR + "Ходит " + namePlayer  + RESET_COLOR);
        else
            System.out.println(GREEN_COLOR + "Ходит " + namePlayer  + RESET_COLOR);
        int move = tryToMove(gameTable); // проверка корректности ввода
        gameTable[move-1] = whoPlay;

        System.out.println(GREEN_COLOR + "Ход сделан, ваша таблица:" + RESET_COLOR);
        printTableGame(gameTable);
    }



    public static void startGame()
    {
        int[] moves = new int[9];
        printTableGame(moves);

        // есть ли победитель игры
        boolean win = false;

        // выбор игры
        System.out.print("Выберите тип игры: 1 - с другом, другой ввод - в одиночку: ");
        Scanner scan = new Scanner(System.in);
        String chooseMode = scan.nextLine();
        if ("1".equals(chooseMode)) System.out.println("Выбран режим игры с другом !");
        else System.out.println("Выбран одиночный режим с компьютером !");

        int countSteps = 0; // кол-во шагов
        while(!win && countSteps != 9)
        {
            // крестик
            yourStep(1, "крестик", moves);
            countSteps=countSteps+1;
            // проверка победы--------------------------------------
            win = checkWin(moves);

            // ход Нолика
            if(win == false && "1".equals(chooseMode) && countSteps != 9)
            {
                yourStep(2, "нолик", moves);
                countSteps=countSteps+1;

                // проверка победы--------------------------------------
                win = checkWin(moves);
            }
            if (win == false && "1".equals(chooseMode)==false && countSteps!= 9) // уже бот ходит
            {
                // отталкиваемся от хода крестика, бежим наверх

                boolean botIsChoose = false;
                int choose = 0; // выбор рандома
                while (botIsChoose == false)
                {
                    choose = ThreadLocalRandom.current().nextInt(0, 9);
                    if(moves[choose]==0) botIsChoose = true;
                }
                moves[choose]= 2;
                botIsChoose = false;
                countSteps=countSteps+1;
                System.out.println(GREEN_COLOR + "Противник походил в " + (choose+1) + ", ваша таблица:" + RESET_COLOR);
                printTableGame(moves);
            }
        }
        if(countSteps == 9 && win == false)
            System.out.println("Игра завершена ничьей !");

    }

    public static void main(String[] args)
    {


        Scanner start = new Scanner(System.in);
        String choose = "";

        while(!"3".equals(choose))
        {
            System.out.println(GREEN_COLOR + "Добро пожаловать в игру крестики нолики !" + RESET_COLOR);
            System.out.println("1 - начать играть");
            System.out.println("2 - правила игры");
            System.out.println("3 - выход из игры");
            choose = start.nextLine();

            if ("1".equals(choose)) {
                System.out.println("Игра началась ! ");
                startGame();}
            else if ("2".equals(choose))
                System.out.println("Это же крестики нолики, какие тут могут быть правила ");
            else if ("3".equals(choose))
                System.out.println("спасибо что зашли в нашу игру, ждем вас снова !");
            else
                System.out.println("Выберите, что хотите сделать: ");
        }

    }
}