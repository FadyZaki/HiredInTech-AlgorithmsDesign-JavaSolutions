import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution_P1 {

	private static Solution_P1 s;

	public static void main(String[] args) {

		s = new Solution_P1();
		Scanner scanner = new Scanner(System.in);

		int mapRows = scanner.nextInt();
		int mapColumns = scanner.nextInt();
		int[][] map = new int[mapRows][mapColumns];

		int surroundingsRows = scanner.nextInt();
		int surroundingsColumns = scanner.nextInt();
		int[][] surroundings = new int[surroundingsRows][surroundingsColumns];

		int xDestination = scanner.nextInt();
		int yDestination = scanner.nextInt();
		Position robotDestination = s.new Position(xDestination - 1, yDestination - 1);
		scanner.nextLine();

		int xRobotPosition = -1;
		int yRobotPosition = -1;

		for (int i = 0; i < mapRows; i++) {
			String currentRow = scanner.nextLine();
			for (int j = 0; j < mapColumns; j++)
				map[i][j] = currentRow.charAt(j) == '2' || currentRow.charAt(j) == '3' ? 0
						: Character.getNumericValue(currentRow.charAt(j));
		}

		for (int i = 0; i < surroundingsRows; i++) {
			String currentRow = scanner.nextLine();
			for (int j = 0; j < surroundingsColumns; j++)
				if (currentRow.charAt(j) == '2') {
					surroundings[i][j] = 0;
					xRobotPosition = i;
					yRobotPosition = j;
				} else
					surroundings[i][j] = Character.getNumericValue(currentRow.charAt(j));
		}

		ArrayList<Position> robotSurroundingPositions = checkRobotSurroundingPosition(map, surroundings);
		HashSet<Position> robotPositions = new HashSet<Position>();
		for (Position p : robotSurroundingPositions) {
			robotPositions.add(s.new Position(p.getX() + xRobotPosition, p.getY() + yRobotPosition));
		}

		scanner.close();
		Node[][] bfsMap = prepareBfsGraph(map);
		boolean[][] robotPositionsMap = new boolean[map.length][map[0].length];
		Position position = null;
		try {
			for (Position p : robotPositions) {
				position = p;
				robotPositionsMap[p.getX()][p.getY()] = true;
			}
		} catch (Exception e) {
			System.out.println(position.getX() + " aho " + position.getY());
		}
		System.out.println(calculateShortestPath(bfsMap, robotPositions, robotPositionsMap, robotDestination));

	}

	private static Node[][] prepareBfsGraph(int[][] map) {
		Node[][] bfsMap = new Node[map.length][map[0].length];
		for (int i = 0; i < map.length; i++)
			for (int j = 0; j < map[0].length; j++)
				bfsMap[i][j] = s.new Node(map[i][j], s.new Position(i, j));

		return bfsMap;
	}

	private static int calculateShortestPath(Node[][] bfsMap, HashSet<Position> robotPositions,
			boolean[][] robotPositionsMap, Position robotDestination) {

		if (robotPositions.isEmpty())
			return -1;

		int shortestPath = -2;
		int currentDepth = 0;

		if (robotPositions.contains(robotDestination))
			shortestPath = 0;

		Queue<Node> queue = new LinkedList<Node>();
		Node destinationNode = bfsMap[robotDestination.getX()][robotDestination.getY()];
		destinationNode.setVisited();
		destinationNode.setDepth(currentDepth);
		queue.add(destinationNode);

		while (!queue.isEmpty()) {
			Node currentNode = queue.poll();

			if (currentDepth == currentNode.getDepth())
				currentDepth++;

			int currentX = currentNode.getP().getX();
			int currentY = currentNode.getP().getY();

			if (currentX + 1 < bfsMap.length && bfsMap[currentX + 1][currentY].getV() == 0) {
				Node n = bfsMap[currentX + 1][currentY];
				if (!n.isVisited()) {
					n.setDepth(currentDepth);
					n.setVisited();
					// Position p = s.new Position(currentX + 1, currentY);
					// if (robotPositions.contains(p)) {
					// shortestPath = currentDepth;
					// }
					if (robotPositionsMap[currentX + 1][currentY])
						shortestPath = currentDepth;
					queue.add(n);
				}
			}

			if (currentX - 1 >= 0 && bfsMap[currentX - 1][currentY].getV() == 0) {
				Node n = bfsMap[currentX - 1][currentY];
				if (!n.isVisited()) {
					n.setDepth(currentDepth);
					n.setVisited();
					// Position p = s.new Position(currentX - 1, currentY);
					// if (robotPositions.contains(p)) {
					// shortestPath = currentDepth;
					// }
					if (robotPositionsMap[currentX - 1][currentY])
						shortestPath = currentDepth;
					queue.add(n);
				}
			}

			if (currentY + 1 < bfsMap[0].length && bfsMap[currentX][currentY + 1].getV() == 0) {
				Node n = bfsMap[currentX][currentY + 1];
				if (!n.isVisited()) {
					n.setDepth(currentDepth);
					n.setVisited();
					// Position p = s.new Position(currentX, currentY + 1);
					// if (robotPositions.contains(p)) {
					// shortestPath = currentDepth;
					// }
					if (robotPositionsMap[currentX][currentY + 1])
						shortestPath = currentDepth;
					queue.add(n);
				}
			}

			if (currentY - 1 >= 0 && bfsMap[currentX][currentY - 1].getV() == 0) {
				Node n = bfsMap[currentX][currentY - 1];
				if (!n.isVisited()) {
					n.setDepth(currentDepth);
					n.setVisited();
					// Position p = s.new Position(currentX, currentY - 1);
					// if (robotPositions.contains(p)) {
					// shortestPath = currentDepth;
					// }
					if (robotPositionsMap[currentX][currentY - 1])
						shortestPath = currentDepth;
					queue.add(n);
				}
			}
		}

		return shortestPath;

	}

	private class Node {
		int v;
		Position p;
		int depth;
		boolean visited;

		Node(int v, Position p) {
			this.v = v;
			this.p = p;
			depth = 0;
			visited = false;
		}

		public int getV() {
			return v;
		}

		public Position getP() {
			return p;
		}

		public int getDepth() {
			return depth;
		}

		public void setDepth(int depth) {
			this.depth = depth;
		}

		public boolean isVisited() {
			return visited;
		}

		public void setVisited() {
			this.visited = true;
		}
	}

	private class Position {
		int x;
		int y;

		Position(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		@Override
		public boolean equals(Object o) {

			if (o == this)
				return true;
			if (!(o instanceof Position)) {
				return false;
			}

			Position position = (Position) o;

			return position.x == this.x && position.y == this.y;
		}

		@Override
		public int hashCode() {

			return ((x * 1001) + y) % 10737;
		}
	}

	private static ArrayList<Position> checkRobotSurroundingPosition(int[][] map, int[][] surroundings) {
		ArrayList<Position> robotSurroundingPositions = new ArrayList<Position>();
		for (int mRow = 0; mRow < map.length; mRow++) {
			for (int mColumn = 0; mColumn < map[0].length; mColumn++) {
				int sRow = 0;
				int sColumn = 0;
				surroundingsLoop: for (sRow = 0; sRow < surroundings.length && mRow + sRow < map.length; sRow++) {
					for (sColumn = 0; sColumn < surroundings[0].length
							&& mColumn + sColumn < map[0].length; sColumn++) {
						if (map[mRow + sRow][mColumn + sColumn] != surroundings[sRow][sColumn])
							break surroundingsLoop;
					}
				}
				if (sRow == surroundings.length && sColumn == surroundings[0].length) {
					robotSurroundingPositions.add(s.new Position(mRow, mColumn));
				}
			}
		}
		return robotSurroundingPositions;
	}
}
