package com.company;

import java.util.Scanner;

public class Second {

    public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
	int BJT = in.nextInt();
	int m = BJT/100;
	int n = BJT%100;
	if (m == 0)
	{
		if (n<10)
        {
            System.out.println("160"+n);
        }
        if ( n <= 59)
        {
            System.out.println("16"+n);
        }
    }else if (m<=23)
    {
        int M =  m -8;
        System.out.println(M*100+n);
    }else{
        System.out.println("you are wrong!");
    }
    }
}
