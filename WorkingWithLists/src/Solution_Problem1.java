import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution_Problem1 {
    public static int jump_over_numbers(List<Integer> list) {
    	int index = 0;
    	int numberOfJumps = 0;
    	while (index < list.size()){
    		int newIndex = list.get(index);
    		if (newIndex <= 0) {
    			numberOfJumps = -1;
    			break;
    		}
    		index += newIndex;
    		
    		numberOfJumps ++;
    	}
    	
    	
		return numberOfJumps;
    }
    
    public static void main(String[] args) {
		System.out.println(jump_over_numbers(new ArrayList<Integer>(Arrays.asList(3,4,1,2,5,6,9,0,1,2,3,1))));
	}
}
