class Solution_P1 {
	
	public static void main(String[] args) {
		
		int result[] = new int[2];
		simplify_fraction(20, 100, result);
		
		System.out.println(result[0] + " " + result[1]);
		
	}
	
    public static void simplify_fraction(int numerator, int denominator, int[] result) {
        int gcd_nd = gcd(numerator, denominator);
        result[0] = numerator/gcd_nd;
        result[1] = denominator/gcd_nd;
    }
    
    private static int gcd (int n1, int n2) {
    	if(n2 == 0) return n1;
    	
    	else 
    		return gcd(n2, n1%n2);
    }
}