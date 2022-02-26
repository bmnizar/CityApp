package com.citylist.backend;

import java.io.IOException;

public class TestClass {
	public static void main(String[] args) throws IOException {
		TestClass.SpecialNumbers(4, 10, 8);
	}

	static long SpecialNumbers(int Number, int DecreasingCost, int IncreasingCost) {
		 
		int totalCost = 0;
		Integer clonedNumber = Number;
		while ((checkIsPrime(clonedNumber) == false))

		{
			totalCost = totalCost + IncreasingCost;
			clonedNumber = clonedNumber + 1;
			if (totalCost > Math.pow(10, 9)) {
//we check the decreasing
				clonedNumber = Number;
				while ((checkIsPrime(clonedNumber) == false))

				{
					totalCost = totalCost + DecreasingCost;
					clonedNumber = clonedNumber - 1;
				}
			}
		}

		return totalCost;

	}

	private static boolean checkIsPrime(int Number) {
		boolean isPrime = false;
		for (int i = 2; i <= Number / 2; ++i) {

			if (checkPrime0(i)) {
				if (checkPrime0(Number - i)) {

					isPrime = true;
				}

			}
		}
		return isPrime;
	}

	public static boolean checkPrime0(int num) {
		boolean isPrime = true;

		for (int i = 2; i <= num / 2; ++i) {
			if (num % i == 0) {
				isPrime = false;
				break;
			}
		}

		return isPrime;
	}
}