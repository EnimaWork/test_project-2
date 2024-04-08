import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String input = scn.nextLine();
        String exception = "throws Exception";
        char operator = 0;
        char str = '"';
        String[] data = new String[0];

        if (input.contains(" + ")) {            // Проверяем, содержит ли строка input подстроку " + "
            data = input.split(" \\+ ");      // Если содержит, разбиваем строку input по " + " и помещаем подстроки в массив data
            operator = '+';       // Устанавливаем значение переменной operator равным символу сложения '+'
        } else if (input.contains(" - ")) {
            data = input.split(" - ");
            operator = '-';
        } else if (input.contains(" * ")) {
            data = input.split(" \\* ");
            operator = '*';
        } else if (input.contains(" / ")) {
            data = input.split(" / ");
            operator = '/';
        } else {
            System.out.println(exception); //Некорректный знак действия
            System.exit(0);
        }

        boolean containsChar = input.contains(String.valueOf("\"")); //метод проверки на наличие кавычек
        int count = input.length() - input.replace(String.valueOf(containsChar), "").length(); // метод считает количество повторений символа char

        boolean containsChar2 = data[0].charAt(0) == str && data[0].charAt(data[0].length() - 1) == str; //проверяет наличие символа в начале и конце первого элемента массива
        boolean containsChar3 = data[1].charAt(0) == str && data[1].charAt(data[1].length() - 1) == str; // тоже самое для второго элемента массива
        boolean containsChar4 = data[0].length() <= 12 && data[1].length() <= 12;

        if (containsChar2 == false || containsChar4 == false) {
            System.out.println(exception);
            System.exit(0);
        }

        if (operator == '+' || operator == '-') {
            if (containsChar == false || count >= 4 || containsChar3 == false) {
                System.out.println(exception);
                System.exit(0);
            }
            if (operator == '*' || operator == '/' || count >= 2) {   //Строчку можно делить или умножать только на число
                if (data[1].contains("\"")) {
                    System.out.println(exception);
                    System.exit(0);
                }
            }
        }

        for (int i = 0; i < data.length; i++) {
            data[i] = data[i].replace("\"", "");   //Убираем кавычки и заменяем их на пустоту перебирая все символы циклом for

        }

        if (operator == '+') {
            printIn(data[0] + data[1]);
        } else if (operator == '*') {
            int dataIn = Integer.parseInt(data[1]);
            if (isNumber(data[1]) == false) {
                System.out.println(exception);
                System.exit(0);
            }
            else {
                String result = "";
                for (int i = 0; i < dataIn; i++) {
                    result += data[0]; //+= для добавления содержимого первого элемента массива data к строке result в количестве = значению второго массива data
                }
                printIn(result);
            }
        } else if (operator == '-') {
            int index = data[0].indexOf(data[1]);  //ищем место у первого элемента массива от куда от совпадает со вторым
            if (index == -1) {
                printIn(data[0]);
            } else {
                String result = data[0].substring(0, index); //извлечение строчки от её начала до места из значения index
                result += data[0].substring(index + data[1].length()); //извлекаем место где заканчивается index и до конца строчки
                printIn(result);
            }
        } else {
            if (isNumber(data[1]) == false) {
                System.out.println(exception);
                System.exit(0);
            }
            int x = data[0].length() / Integer.parseInt(data[1]);
            String result = data[0].substring(0, x);
            printIn(result);
        }

    }

    public static boolean isNumber(String str) {   //проверка на число от 1 до 10
        try {
            int num = Integer.parseInt(str);
            if (num >= 1 && num <= 10); {
                return true;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }

    static void printIn(String text) {
        if (text.length() > 40) {
            text = text.substring(0, 40) + "...";
        }
        System.out.println("\""+text+"\"");
    }

}