import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Fraction a = new Fraction(in.nextInt(), in.nextInt());
        Fraction b = new Fraction(in.nextInt(),in.nextInt());
        a.print();
        b.print();
        a.plus(b).print();
        a.multiply(b).plus(new Fraction(5,6)).print();
        a.print();
        b.print();
        in.close();
    }

}

class Fraction {

    private int numerator;
    private int denominator;

    Fraction(int a, int b){
        numerator = a;
        denominator = b;
    }

    double toDouble(int a, int b){
        double d = (a+0.0) / b;
        return d;
    }

    Fraction plus(Fraction r){
        int num = 1;
        int den = 1;
        if(denominator != r.denominator){
            den = denominator * r.denominator;
            num = numerator * r.denominator + r.numerator * denominator;
        }else{
            num = numerator + r.numerator;
            den = denominator;
        }
        Fraction c = new Fraction(num,den);
        return c;
    }

    Fraction multiply(Fraction r){
        int num = 1;
        int den = 1;
        num = numerator * r.numerator;
        den = denominator * r.denominator;
        Fraction d = new Fraction(num,den);
        return d;
    }

    void print(){
        if (numerator >= denominator){
            System.out.println(numerator / denominator);
        }else {
            if(denominator % numerator == 0) {
                denominator = denominator / numerator;
                numerator = 1;
            }else{
                for (int i = 1; i < 10; i++) {
                    if(denominator % i == numerator % i){
                        denominator = denominator / i;
                        numerator = numerator /i;
                    }
                }
            }
            System.out.println(numerator + "/" + denominator);
        }
    }
}
