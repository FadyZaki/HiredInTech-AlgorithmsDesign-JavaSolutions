import java.util.ArrayList;
import java.util.List;

class Solution_P2 {
	
	public static void main(String[] args) {
		
		ArrayList<Integer> task_difficulties = new ArrayList<Integer>();
		//24 23 22 10 20
		task_difficulties.add(24);
		task_difficulties.add(23);
		task_difficulties.add(22);
		task_difficulties.add(10);
		task_difficulties.add(20);
		maximum_completed_tasks(5, 88, task_difficulties);
	}
	
	public static int maximum_completed_tasks(int n, int t, List<Integer> task_difficulties) {

		int maximum_completed_tasks = 0;
		int current_difficulty = 0;
		int[] task_difficulties_array = new int[n];
		int i = 0;
		for (int td : task_difficulties) {
			task_difficulties_array[i] = td;
			i++;
		}

		quickSort(0, task_difficulties_array.length - 1, task_difficulties_array);
		
		for (int j=0; j<task_difficulties_array.length; j++){
			current_difficulty += task_difficulties_array[j];
			if(j != 0){
				current_difficulty += task_difficulties_array[j] - task_difficulties_array[j-1];
			}
			
			if(current_difficulty <= t)
				maximum_completed_tasks++;
			else
				break;
		}

		
		return maximum_completed_tasks;

	}

	public static void quickSort(int low, int high, int[] unsortedArray) {
    	
    	int pivot = unsortedArray[(low + high)/2];
    	swap((low + high)/2,high,unsortedArray);
    	
    	int i=low;
    	for (int j=low; j<high; j++){
    		if (unsortedArray[j] <= pivot){
    			swap(i,j,unsortedArray);
    			i++;		
    		}
    	}
    	swap(i,high,unsortedArray);
    	
    	if(i>low)
    		quickSort(low, i-1, unsortedArray);
    	
    	if(i<high)
    		quickSort(i+1, high, unsortedArray);
    	
    	
    }

	public static void swap(int i1, int i2, int[] array) {
		int temp = array[i1];
		array[i1] = array[i2];
		array[i2] = temp;

	}
}