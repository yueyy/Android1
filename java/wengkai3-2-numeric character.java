import java.util.Scanner;

public class Main {

    public static double a;
    public static int[] aList = new int[7];
    public static boolean[] bList = new boolean[7];


    public static void list(int n){
        String string = ""+n;
        char[] cList = string.toCharArray();
        int i = 0;
        do{
            int m = n % 10;
            aList[i] = m;
            n = n / 10;
            if(i%2==m%2){
                bList[i] = true;
            }else{
                bList[i] = false;
            }
            i = i+1;
        }while(n>0 & i < cList.length);
    }

    public static double change(int number){
        String string = ""+number;
        char[] cList = string.toCharArray();
        for (int i = 0; i < cList.length; i++) {
            if(bList[i]==false){
                a = a + Math.pow(2,i);
            }
        }
        return a;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int number = in.nextInt();

        list(number);
        if(number==0){
            System.out.println(0);
        }else {
            number = (int) change(number);
            System.out.println(number);
        }
    }
}
