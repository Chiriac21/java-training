public class FooBarQix {

    public String compute(int number){
        String s = "";
            if(number % 3 == 0){
                s += "Foo";
            }
            if(number % 5 == 0){
                s += "Bar";
            }
            if(number % 7 == 0){
                s += "Qix";
            }

            char[] digits = String.valueOf(number).toCharArray();
            for(int digit : digits){
                if(digit == '3') {
                    s += "Foo";
                }
                if(digit == '5') {
                    s += "Bar";
                }
                if(digit == '7') {
                    s += "Qix";
                }
            }
            if(s.isEmpty()){
                s = "" + number;
            }
            return s;
    }

    public String compute2(int number){
        String s = "";
        boolean isDivisible = false;
        if(number % 3 == 0){
            s += "Foo";
            isDivisible = true;
        }
        if(number % 5 == 0){
            s += "Bar";
            isDivisible = true;
        }
        if(number % 7 == 0){
            s += "Qix";
            isDivisible = true;
        }

        char[] digits = String.valueOf(number).toCharArray();
        for(char digit : digits){
            if(digit == '3') {
                s += "Foo";
            }
            else if(digit == '5') {
                s += "Bar";
            }
            else if(digit == '7') {
                s += "Qix";
            }
            else if(digit == '0'){
                s += "*";
            }
            else if(!isDivisible){
                s += digit;
            }
        }
        return s;
    }

    public void printNumbers(){
        for(int i=1; i<=101; i++){
            //System.out.println(i + " => " + this.compute(i));
            System.out.println(i + " => " + this.compute2(i));
        }
    }
}
