/**
 * Created by: Kevin Ha
 * I pledge my honor that I have abided by the Stevens Honor System.
 * Section: CS 284 A
 */


package timeComplex;

public class Complexity {
	
	/**
	 * Time Complexity of n
	 * @param n : Runs O(n) times
	 */
	public static void method0(int n) {
		int counter = 0;
		
		for(int i = 0; i < n; i++) {
			System.out.println("Operation " + counter);
			counter++;
		}
	}
	
	/**
	 * Time Complexity of n^2
	 * @param n : Runs O(n^2) times
	 */
	
	public static void method1(int n) {
		int counter = 0;
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				System.out.println("Operation " + counter);
				counter++;
			}
			
		}
	}
	
	/**
	 * Time Complexity of n^3
	 * @param n : Runs O(n^3) times
	 */
	
	public static void method2(int n) {
		int counter = 0;
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				for(int z = 0; z < n; z++) {
					System.out.println("Operation " + counter);
					counter++;
				}
			}
			
		}
	}
	
	/**
	 * Time Complexity of log(n)
	 * @param n : Runs log(n) times
	 */
	
	public static void method3(int n) {
		int counter = 0;
		
		for(int i = 1; i < n; i*=2) {
			System.out.println("Operation " + counter);
			counter++;
		}
	}
	
	/**
	 * Time Complexity of n log(n)
	 * @param n : Runs n log(n) times
	 */
	
	public static void method4(int n) {
		int counter = 0;
		
		for(int i = 0; i < n; i++) {
			for(int j = 1; j < n; j*=2) {
				System.out.println("Operation " + counter);
				counter++;
			}
		}
	}
	
	/**
	 * Time Complexity of loglog(n)
	 * @param n : Runs loglog(n) times
	 */
	
	public static void method5(int n) {
		int counter = 0;
		
		for(int i = 2; i < n; i*=i) {
			System.out.println("Operation " + counter);
			counter++;
		}
	}
	
	
	/**
	 * Time Complexity of 2^n
	 * @param n Runs O(2^n) times
	 * @param static int counter set outside in order to prevent reset to 0
	 */
	
	private static int counter = 0;
	
	public static int method6(int n) {
	
		if (n <= 0) {
			counter++;
			System.out.println("Operation " + counter);
			return n;
		}
		
		else {
			counter++;
			return method6(n - 1) + method6(n - 2);
		}
	}
	
	//log log n
    public void method7(int n){
        int counter = 0;
        for(int k = 1; k < n; k*=2){
            for(int i = 1; i < n; i*=2){
                System.out.println("Operation " + counter);
                counter++;
            }
        }
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Complexity n = new Complexity();
		n.method3(32);
		//n.method6(3);
	}

}
