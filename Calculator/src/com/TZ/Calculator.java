package com.TZ;

public class Calculator {
    private String expression;
    private int firstArgument;
    private int secondArgument;
    private int operationn;
    private Analizator analyze;
    private boolean isInDec;
    private int result;

    public Calculator (Analizator analyze){
        this.analyze = analyze;
    }

    public String calculate(String expression){
        //Анализируем выражение
        analyze.setExpression(expression);
        this.analyze.analyze();

        //Переводим в int
        this.firstArgument = isInDec(analyze.getArguments(0));

        //Проверяем оба ли аргумента состоят из одних цифр
        if ((((int) analyze.getArguments(1).charAt(0)) < 60) == (isInDec)) {
            this.secondArgument = isInDec(analyze.getArguments(1));
        } else {
            throw new IllegalArgumentException("Ошибка!!! Используются одновременно разные системы счисления");
        }


        this.operationn = analyze.getOperation(0);

        if (operationn == 42){
            this.result= this.firstArgument * this.secondArgument;
        }
        if (operationn == 43){
            this.result= this.firstArgument + this.secondArgument;
        }
        if (operationn == 45){
            this.result= this.firstArgument - this.secondArgument;
        }
        if (operationn == 47){
            this.result= (int) (this.firstArgument / this.secondArgument);
        }

        //Проверяем на отрицательные числа в римских цифрах
        if ((this.result<1)&&(!isInDec)){
            throw new IllegalArgumentException("Ошибка!!! В римской системе нет отрицательных чисел");
        }

        //Вывод и преобразование
        if (this.isInDec) {
            return Integer.toString(this.result);
        } else {
            return decToRome(this.result);
        }
    }



    public String getExpression(){
        return this.expression;
    };
    public void setExpression(String exp){
        this.expression = expression;
    };


    //Метод для переводя из Римской в Арабскую
    public int romeToDec(String arg){
        int number = 0;
        String [] RomeNumbers = new String[] {"I","II","III","IV","V","VI","VII","VIII","IX","X"};
        for (int i =0; i<10; i++){
            if (RomeNumbers[i].equals(arg)){
                number = i+1;
            };
        }
        if (number == 0 ){throw new NumberFormatException("Programm exception");}
        return number;
    }

    //Метод для перевода из Арабских в Римские цифры
    public String decToRome(int arg){
        String [] RomeNumbers = new String[] {" ","I","II","III","IV","V","VI","VII","VIII","IX"};
        String [] RomeNumbers10 = new String[]{"X","XX","XXX","XL","L","LX","LXX","LXXX","XC","C"};
        if(arg < 10 ) {
            return RomeNumbers[arg];
        }else{
            return RomeNumbers10[(int)arg/10 -1]+ RomeNumbers[arg%10 ];
        }

    }



    //Метод для проверки, какие цифры ввели,  и перевод их к типу int
    public int isInDec(String exp){
        if (exp.charAt(0) < 60 ) {
            this.isInDec = true;
            return Integer.parseInt(exp);
        } else {
            this.isInDec = false;
            return romeToDec(exp);
        }
    }



}
