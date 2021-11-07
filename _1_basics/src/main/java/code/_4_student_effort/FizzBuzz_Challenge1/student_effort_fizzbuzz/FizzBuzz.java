public class FizzBuzz {

    public void printNumbers(){
            String s = "";
          for(Integer i=1; i<=100; i++){
                if(isDivisibleBy3(i)){
                    s += "Fizz ";
                }else if(isDivisibleBy5(i)){
                    s += "Buzz ";
                }else if(isDivisibleBy3And5(i)){
                    s += "FizzBuzz ";
                }else if(isDivisibleBy7(i)){
                    s += "Rizz ";
                }else if(isDivisibleBy11(i)){
                    s += "Jazz ";
                }else{
                    s += i.toString() + " ";
                }
          }
        System.out.println(s);
    }

    public boolean isDivisibleBy3(int number){
        return number % 3 == 0;
    }

    public boolean isDivisibleBy5(int number){
        return number % 5 == 0;
    }

    public boolean isDivisibleBy3And5(int number){
        return number % 3 == 0 && number % 5 == 0;
    }

    public boolean isDivisibleBy7(int number){
        return number % 7 == 0;
    }

    public boolean isDivisibleBy11(int number){
        return number % 11 == 0;
    }
}
