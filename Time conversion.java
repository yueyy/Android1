import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
	int BJT = in.nextInt();
	int m = BJT/100;
	int n = BJT%100;
	while(m<0||m>23||n<0||n>59)
    {
        System.out.println("Please enter valid data!");
        BJT = in.nextInt();
        m = BJT/100;
        n = BJT%100;
    }
    if (m==0)
    {
        if (n<10)
        {
            System.out.println("160"+n);
        }else
        {
            System.out.println("16"+n);
        }
    }
    else
    {
        int M = m - 8;
        System.out.println(M * 100 + n);
    }
    }
}
