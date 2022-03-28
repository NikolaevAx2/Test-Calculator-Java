package com.TZ;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Analizator analizator = new Analizator();
        Calculator calculator = new Calculator(analizator);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Для зарешения работы калькулятора введите: end");
        while (true) {
            System.out.println("Введите выражение:");

            String expression = scanner.nextLine();

            if (expression.equals("end")){
                System.out.println("Завершаю работу");
                break;
            }
            try {
                System.out.println(calculator.calculate(expression));
            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }



    }
}
