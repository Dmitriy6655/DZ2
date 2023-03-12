//К калькулятору из предыдущего дз добавить логирование.

import java.io.IOException;
import java.util.logging.*;

public class Task4 {

    

    public static void main(String[] args) throws IOException {

        Logger logger = Logger.getLogger(Task4.class.getName());
        logger.setLevel(Level.INFO);

        FileHandler ch = new FileHandler("log.txt");
        logger.addHandler(ch);

        SimpleFormatter sFormat = new SimpleFormatter();
        ch.setFormatter(sFormat);

        System.out.println("Математические операции калькулятора:");
        System.out.println("/ - деление; " + '\n' + "* - умножение;" + '\n' +
                "^ - возведение в целочисленную степень;"
                + '\n' + "+ сложение;" + '\n' + "- вычитание.");

        logger.log(Level.INFO, "Запрос ввода данных из консоли");
        System.out.println("Введите пример:");
        String str = System.console().readLine().replace(" ", "").toLowerCase();

        // calculation(str);
        if (calculation(str) == 55) {
            logger.log(Level.WARNING, "Ошибка деления!");
        } else {
            logger.log(Level.INFO, "Получение результата");
        }

    }

    public static double[] stringСonversion(String strInput, int d) {

        int end = d;
        int start = 0;

        char[] dst1 = new char[end - start];

        strInput.getChars(start, end, dst1, 0);// записываем в массив символов dst с 0 индекса(dstBegin)

        StringBuilder builder = new StringBuilder();// создаем пустую строку
        StringBuilder builder2 = new StringBuilder();// создаем 2 пустую строку

        for (int i = 0; i < dst1.length; i++) {
            builder.append(dst1[i]);
        }

        int start2 = end + 1;
        int end2 = strInput.length();
        char[] dst2 = new char[end2 - start2];

        strInput.getChars(start2, end2, dst2, 0);// записываем в массив символов dst с 0 индекса(dstBegin)

        for (int i = 0; i < dst2.length; i++) {
            builder2.append(dst2[i]);
        }

        int x = Integer.parseInt(builder.toString());// переводит строку в целое число
        double x2 = (double) x;// перевод типа int в double
        // System.out.println(x2);

        int y = Integer.parseInt(builder2.toString());
        double y2 = (double) y;// перевод типа int в double

        double array[] = new double[2];
        array[0] = x2;
        array[1] = y2;

        return array;
    }

    public static int calculation(String str) {

        // поиск индекса определенного знака
        int end = str.indexOf('/');

        int end1 = str.indexOf('*');
        int end2 = str.indexOf('^');
        int end3 = str.indexOf('+');
        int end4 = str.indexOf('-');

        double arr[] = new double[2];

        if (end >= 0) {

            if (arr[1] == 0) {
                // System.out.println("Ошибка деленения на 0!");
                return 55;

            } else {
                arr = stringСonversion(str, end);
                System.out.println(arr[0] / arr[1]);
            }

        } else if (end1 >= 0) {
            arr = stringСonversion(str, end1);
            System.out.println(arr[0] * arr[1]);

        } else if (end2 >= 0) {

            arr = stringСonversion(str, end2);
            System.out.println(Math.pow(arr[0], arr[1]));

        } else if (end3 >= 0) {

            arr = stringСonversion(str, end3);
            System.out.println(arr[0] + arr[1]);

        }

        else if (end4 >= 0) {

            arr = stringСonversion(str, end4);
            System.out.println(arr[0] - arr[1]);
        }
        return 1;

    }

}
