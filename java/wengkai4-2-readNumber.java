import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static String[] pronunce = new String[]{"ling","yi","er","san","si","wu","liu","qi","ba","jiu"};

    public static String read(int num){
        int n;
        String string = ""+num;
        char[] chars = string.toCharArray();
        int length = chars.length;
        String single = new String();
        int[] number = new int[length];
        int i = length;
        do{
            number[i-1] = num % 10;
            num = num / 10;
            i = i - 1;
        }while(num>0 && i>0);

        for(int e : number){
            single = single +pronunce[e] + " ";
        }

        return single;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numb = in.nextInt();

        if(numb<0){
            numb = Math.abs(numb);
            System.out.println("fu "+read(numb).trim());
        }else {
            System.out.println(read(numb).trim());
        }
    }
}
