import java.util.*;
import static java.lang.Integer.parseInt;

public class Main {

    public static int parselnt(String s,int radix)
    {
        return parseInt(s,10);
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int number = in.nextInt();

        number = Math.abs(number);
        String num = String.valueOf(number);
        number = parselnt(num,10);

        String a = Integer.toString(Integer.valueOf(num),n);
        System.out.println(a);

    }
}

// 暂时只能计算整数 再继续改进！！！
