package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();
        while (b !=0)
        {
            int m = a%b;
            a = b;
            b = m;
        }
        System.out.println(a);
    }
}
