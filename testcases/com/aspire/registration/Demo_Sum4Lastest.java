package com.aspire.registration;

import java.util.Arrays;

public class Demo_Sum4Lastest {

	public static void main(String[] args) {
		int a[] = { 1, 2, 5, 6, 3, 2 };
		int b[] = { 44, 66, 99, 77, 33, 22, 55 };
		int c[] = { -44, -5, -99, 3, 7, 0, 11 };
		System.out.println("Sum of 4 Lastest is: " + sumof4LastestNumber(a));
		System.out.println("Sum of 4 Lastest is CÁCH 2: " + sumOfLastestNumber_2(a));
		System.out.println("Sum of 4 Lastest is CÁCH 3: " + sumOfLastestNumber_3(a));
		System.out.println("Sum of 4 Lastest is: " + sumof4LastestNumber(b));
		System.out.println("Sum of 4 Lastest is CÁCH 2: " + sumOfLastestNumber_2(b));
		System.out.println("Sum of 4 Lastest is CÁCH 3: " + sumOfLastestNumber_3(b));
		System.out.println("Sum of 4 Lastest is: " + sumof4LastestNumber(c));

	}

	public static int sumof4LastestNumber(int[] a) {
		int total = a.length;

		Arrays.sort(a);
		for (int i : a) {
			System.out.println("Item of Array Asc is: " + i);
		}
		int sum = 0;
		for (int j = 0; j < 4; j++) {
			sum += a[total - 1];
			total--;
		}
		return sum;
	}

	public static int sumOfLastestNumber_2(int[] a) {
		int total = a.length;
		int temp;
		int sum =0;
		for (int i = 0; i < total; i++) {
			for (int j = i + 1; j < total; j++) {
				if (a[i] < a[j]) {
					temp = a[i];
					a[i] = a[j];
					a[j] = temp;
				}
			}
		}
		for(int i = 0; i <4; i++) {
			sum += a[i];
		}
		return sum;
	}

	public static int sumOfLastestNumber_3(int[] a){
		int i, j, temp, n = a.length;
		for (i = 0; i < n; i++)
		{
			for (j = i + 1; j < n; j++)
		{
				if (a[i] < a[j])
				{
					temp = a[i];

					a[i] = a[j];

					a[j] = temp;
				}
			}
			System.out.println("Item of Array Desc is: " + a[i]);
		}
		int sum = 0;
		for (i = 0; i < 4; i++)
		{
			sum += a[i];
		}
		return sum;
	}
}
