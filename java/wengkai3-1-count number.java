

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        int odd = 0;
        int even = 0;
        do {
            if (num != -1) {
                if (num%2 != 0) {
                    odd += 1;
                } else{
                    even += 1;
                }
            }
            num = in.nextInt();
        }while (num != -1);
        System.out.println(odd + " " + even);
    }
}
