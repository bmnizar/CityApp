/**
 ** @BMN 2021
 **
 **/
public class d {
	public static void main(String[] args) {
	 
		Thread zzz =new Thread();zzz.start();
		System.out.println(zzz.getState());

	}

	private static void m1() {
		try {
			int a = 10;
			int b = 0;
			int c = a / b;
		} finally {
			System.out.println("finally");
		}

	}

	public static void main(char[] args) {

	}
}
