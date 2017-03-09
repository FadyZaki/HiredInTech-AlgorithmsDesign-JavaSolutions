import java.util.ArrayList;
import java.util.List;

class Solution {

	public static void main(String[] args) {
		// radars = [ [5, 10], [3, 25], [46, 99], [39, 40], [45, 50] ]
		List<Integer> range1 = new ArrayList<Integer>();
		range1.add(5);
		range1.add(10);

		List<Integer> range2 = new ArrayList<Integer>();
		range2.add(3);
		range2.add(25);

		List<Integer> range3 = new ArrayList<Integer>();
		range3.add(46);
		range3.add(99);

		List<Integer> range4 = new ArrayList<Integer>();
		range4.add(39);
		range4.add(40);

		List<Integer> range5 = new ArrayList<Integer>();
		range5.add(45);
		range5.add(50);

		List<List<Integer>> radars = new ArrayList<List<Integer>>();
		radars.add(range1);
		radars.add(range2);
		radars.add(range3);
		radars.add(range4);
		radars.add(range5);

		System.out.println(cover_the_border(100, radars));
	}

	public static int cover_the_border(int l, List< List<Integer> > radars) {
		
    	Solution s = new Solution();
    	PointType[] points = new PointType[radars.size()*2];
    	
    	int i=0;
    	for (List<Integer> range : radars){	
    		points[i++] = s.new PointType(range.get(0),true);
    		points[i++] = s.new PointType(range.get(1), false);
    	}
    	
    	mergeSort(0, points.length-1, points);
    	
    	int numberOfOpenRanges = 0;
    	int regionStart = 0;
    	int totalCoveredRange = 0;
    	for (int j=0; j<points.length; j++){
    		if(points[j].isStartpoint()) {
    			numberOfOpenRanges++;
    		
    			if(numberOfOpenRanges == 1)
    				regionStart = points[j].getPoint();
    		}
    		
    		else {
    			numberOfOpenRanges--;
    			
    			if (numberOfOpenRanges == 0)
    				totalCoveredRange += points[j].getPoint() - regionStart;
    		}
    			
    	}
    	
    	return totalCoveredRange;

    }

	private static void mergeSort(int lowIndex, int highIndex, PointType[] points) {

		if (highIndex == lowIndex)
			return;

		int middleIndex = (highIndex + lowIndex) / 2;
		mergeSort(lowIndex, middleIndex, points);
		mergeSort(middleIndex + 1, highIndex, points);

		PointType[] lowHalf = new PointType[middleIndex - lowIndex + 1];
		PointType[] highHalf = new PointType[highIndex - middleIndex];

		for (int i = 0; i < lowHalf.length; i++) {
			lowHalf[i] = points[lowIndex + i];
		}

		for (int i = 0; i < highHalf.length; i++) {
			highHalf[i] = points[middleIndex + 1 + i];
		}

		int i = 0;
		int j = 0;
		for (int k = lowIndex; k <= highIndex; k++) {

			if (i == lowHalf.length) {
				points[k] = highHalf[j++];
			}

			else if (j == highHalf.length) {
				points[k] = lowHalf[i++];
			}

			else if (lowHalf[i].getPoint() <= highHalf[j].getPoint()) {
				points[k] = lowHalf[i++];
			}

			else {
				points[k] = highHalf[j++];
			}
		}

	}

	private class PointType {

		private int point;
		private boolean type; // startpoint or endpoint

		public PointType(int point, boolean type) {
			super();
			this.point = point;
			this.type = type;
		}

		public int getPoint() {
			return point;
		}

		public void setPoint(int point) {
			this.point = point;
		}

		public boolean isStartpoint() {
			return type;
		}

		public void setType(boolean type) {
			this.type = type;
		}

	}
}