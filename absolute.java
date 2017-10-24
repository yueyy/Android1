import java.util.Scanner;

public class Main {

    public static int abs(int x)
    {
        if (x<0)
        {
            return -x;
        }else
        {
            return x;
        }
    }

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        System.out.println(abs(num));

    }
}
