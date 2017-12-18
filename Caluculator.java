import java.util.Scanner;

public class Main {

    public static int flag;

    public static void judge(int a, int b){
        int c = 0;

        if(a%2==0 && b%2==0){
            c = a+b;
            System.out.println(c);
        }else if(a%2!=0 && b%2!=0){
            c = a+b-1;
            System.out.println(c);
        }else{
            System.out.println("你简直是太酷了！");
        }

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String num = in.nextLine();
        char[] chars = num.toCharArray();
        int a = (int) chars[0];
        int b = (int) chars[2];
        judge(a,b);
    }
}
