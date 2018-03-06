import java.util.Scanner;

public class Main {

    private static int[] isPrime = new int[211];

    public static void prime(){
        isPrime[0] = 2;
        int count = 1;
        boolean b;
        for (int i = 3; i < 1224; i+=2) {
            b = true;
            for (int j = 3; j < Math.sqrt(i); j++) {
                if (i % j == 0) {
                    b = false;
                    break;
                }
            }
            if (b) {
                isPrime[count] = i;
                count += 1;
            }
        }
    }

    public static int sum(int n, int m){
        int sumup = 0;
        for (int i = n-1; i <m; i++) {
            sumup = sumup + isPrime[i];
        }
        return sumup;
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();

        prime();
        System.out.println(sum(n,m));
    }
}
