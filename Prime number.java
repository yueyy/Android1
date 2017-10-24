package com.company;

public class Main {

    public static void main(String[] args) {
        for (int n =2; n<100; n++)
        {
            int isPrime = 1;
            for ( int i=2; i<n; i++)
            {
                if ( n%i == 0)
                {
                    isPrime = 0;
                    break;
                }
            }
            if (isPrime ==1)
            {
                System.out.println(n+" ");
            }
        }
    }
}
