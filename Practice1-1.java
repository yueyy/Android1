package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();
        int m = 1;
        for ( int i =2 ; i<=a&&i<=b; i++)
        {
            if( a%i == 0 && b%i == 0)
            {
                m = i;
            }
        }
        System.out.println(m);
    }
}
