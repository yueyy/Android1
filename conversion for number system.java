import java.util.*;

public class Main {

    public static int abs(int n)
    {
        if (n<0){
            return -n;
        }else {
            return n;
        }
    }

    public static double change(int in,int out,double num)
    {
        int integer = (int)num;
        double doubles = num - integer;
        if(in==out)
        {
            return num;
        }else
        {
            String strnum = String.valueOf(integer);
            int l = strnum.length();
            num = num - num;
            for(int i=0; i<l ; i++)
            {
                int a = integer%10;
                num = (int)Math.pow(in,i) + num;
                a = integer/10;
            }
            return num;
        }
    }

    public static void main(String[] args) {
        Scanner in =new Scanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();
        int number = in.nextInt();

        number = abs(number);
        System.out.println(change(a,b,number));
    }
}


// 暂时只能计算整数 晚上再改进！！！
// emmmmm有bug……零的问题 电脑没电了……晚上再改正！！！
