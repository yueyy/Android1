package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();
        int c = a;
        int d = b;
        while (b !=0)
        {
            int min = a%b;
            a = b;
            b = min;
        }
        int max = (c*d)/a;
        System.out.println(a);
        System.out.println(max);
    }
}
