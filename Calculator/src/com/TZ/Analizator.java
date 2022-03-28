package com.TZ;

public class Analizator {
    private String expression;
    public String [] arguments = new String[5];
    public int [] operation = new int[5];

    public Analizator (){
    }

    public String getExpression(){
        return this.expression;
    };
    public void setExpression(String expression){
        this.expression = expression;
    };

    public String getArguments(int argNumber){
        return this.arguments[argNumber];
    };
    public void setArguments(String arg, int argNumber){
        this.arguments[argNumber] = arg ;
    };

    public int getOperation(int oprNumber){
        return this.operation[oprNumber];
    };
    public void setOperation(int opr, int oprNumber){
        this.operation[oprNumber] = opr ;
    };

    public void analyze(){
        String number = null;
        boolean isNeedingSymbol;
        int argNumber = 0;
        int oprNumber = 0;

        //Проходим строчку посимвольно
        for (int i=0; i<=(this.expression.length()-1); i++){
            isNeedingSymbol = false;

                //Проверяем является ли символ частью числа
            if ((((int) this.expression.charAt(i) >= 48) && ((int) this.expression.charAt(i) <= 57)) || (((int) this.expression.charAt(i) >= 65) && ((int) this.expression.charAt(i) <= 90))) {
                if (number==null){
                    number = this.expression.charAt(i) + "";
                } else {
                    number = number + this.expression.charAt(i);
                }
                isNeedingSymbol=true;
            }

            //Проверяем является ли символ операцией
            if (((int) this.expression.charAt(i)==42) || ((int) this.expression.charAt(i)==43) ||((int) this.expression.charAt(i)==45) || ((int) this.expression.charAt(i)==47)) {
                this.operation[oprNumber] = (int) this.expression.charAt(i);
                isNeedingSymbol=true;
                oprNumber++;
                if (number != null) {
                    this.arguments[argNumber] = number;
                    argNumber++;
                    number= null;
                }
            }
            //Если встетился пробел, то сохраняем число в массив
            if ((int) this.expression.charAt(i) == 32) {
                isNeedingSymbol=true;
                if (number != null) {
                    arguments[argNumber] = number;
                    argNumber++;
                    number= null;
                }
            }

            //Исключение для неизвестного символа
            if (!isNeedingSymbol){
                throw new IllegalArgumentException("Ошибка!!! Символ " + this.expression.charAt(i) + " не может быть обработан программой" );
            };

        }
        if (number != null) {
            arguments[argNumber] = number;
            argNumber++;
        }

        if ((argNumber > 2)||(oprNumber > 1) ) {
            throw new IllegalArgumentException("Ошибка!!! Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        if ((argNumber < 1) || (oprNumber == 0)){
            throw new IllegalArgumentException("Ошибка!!! Строка не является математической операцией");
        }
    }


}
