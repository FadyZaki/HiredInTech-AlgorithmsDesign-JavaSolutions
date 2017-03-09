import java.util.List;

class Solution_P2 {
   
	public static void main(String[] args) {
		System.out.println(count_numbers_factors(4l));
	}
	
	public static int count_numbers_factors(long number) {
		
    	if (number == 0 || number == 1) return 1;
		
		int numberOfFactors = 2; //1 and the number itself
    	long i = 0;
    	for (i=2; i*i<number; i++){
    		if(number%i==0) numberOfFactors +=2;
    	}
    	
    	if(i*i == number) numberOfFactors++;
    	
    	return numberOfFactors;
           
    }
}
