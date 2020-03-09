package graph;

public class GraphDemo {
	public static void main(String[] args) {
		int n = 8;
//		String Vertexts[] = {"A","B","C","D","E"};
		String Vertexts[] = {"1","2","3","4","5","6","7","8"};
		Graph graph = new Graph(n);
		for (String vertex : Vertexts){
			graph.insertVertex(vertex);
		}

		//添加边
//		A-B,A-C,B-C,B-D,B-E
//		graph.insertEdge(0,1,1);
//		graph.insertEdge(0,2,1);
//		graph.insertEdge(1,2,1);
//		graph.insertEdge(1,3,1);
//		graph.insertEdge(1,4,1);
		graph.insertEdge(0,1,1);
		graph.insertEdge(0,2,1);
		graph.insertEdge(1,3,1);
		graph.insertEdge(1,4,1);
		graph.insertEdge(3,7,1);
		graph.insertEdge(4,7,1);
		graph.insertEdge(2,5,1);
		graph.insertEdge(2,6,1);
		graph.insertEdge(5,6,1);

		graph.showGraph();

		System.out.println("==== 深度遍历 ====");
		graph.dfs();//1->2->4->8->5->3->6->7->


		System.out.println("\n==== 广度遍历 =====");
		graph.bfs();//1->2->3->4->5->6->7->8->

		System.out.println("\n===============");
		boolean[] isVisited = new boolean[8];
		graph.bfs(isVisited,0);
	}
}
