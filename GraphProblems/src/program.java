import java.util.ArrayList;
import java.util.Scanner;

public class program {

	private static program s;
	
	public static void main(String[] args) {

		s = new program();
		Scanner scanner = new Scanner(System.in);

		int v = scanner.nextInt();
		int e = scanner.nextInt();
		int source = scanner.nextInt();
		scanner.nextLine();

		int[][] edges = new int[e][2];
		for (int i=0; i<e; i++){
			edges[i][0] = scanner.nextInt();
			edges[i][1] = scanner.nextInt();
		}
		scanner.close();
		
		Graph g = s.new Graph(v, edges);
		
		System.out.println(s.new DFS(g).getNumberOfConnectedComponents(source) -1);

	}
	
	private class DFS {
		Graph g;
		boolean[] visited;
		
		public DFS(Graph g) {
			this.g = g;
		}
		
		public int getNumberOfConnectedComponents(int source){
			this.visited = new boolean[g.getV()+1];
			return dfs(source);
		}
		
		public int dfs(int source) {
			ArrayList<Integer> vertices = g.getAdjVertices(source);
			visited[source] = true;
			int subtreeSize = 0;
			for (Integer v : vertices){
				if(!visited[v]){
					subtreeSize += dfs(v);
				}
			}
			
			return subtreeSize + 1;
		}
	}


	private class Graph {
		
		private ArrayList<Integer>[] adjList; 
		
		@SuppressWarnings("unchecked")
		public Graph(int v, int edges[][]) {
			adjList = new ArrayList[v+1];
			for( int i = 0; i <= v; i++) {
			    adjList[i] = new ArrayList<Integer>();
			}
			for (int i=0; i<edges.length; i++){
				adjList[edges[i][0]].add(edges[i][1]);
				adjList[edges[i][1]].add(edges[i][0]);
			}
		}
		
		public ArrayList<Integer> getAdjVertices(int vertex){
			return adjList[vertex];
		}
		
		public int getV(){
			return adjList.length - 1;
		}
		
		
	}
}
