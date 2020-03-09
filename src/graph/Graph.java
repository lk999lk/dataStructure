package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {
	private ArrayList<String> vertexList;//存储顶点集合
	private int[][] edges;//存储图对应的邻接矩阵
	private int numOfEdges;//表示边的数目
	private boolean[] isVisited;//记录某个节点是否被访问


	public Graph(int n) {
		//初始化矩阵和vertexList
		edges = new int[n][n];
		vertexList = new ArrayList<>(n);
		numOfEdges = 0;
	}

	/**
	 * 得到第一个邻接节点的下标w
	 * @param index 要查找的节点
	 * @return 如果存在就返回对应的下标，否则返回-1
	 */
	public int getFirstNeighbor(int index){
		for (int i = 0; i < vertexList.size(); i++) {
			if (edges[index][i]>0){
				return i;
			}
		}
		return -1;
	}

	/**
	 * 根据前一个邻接节点的下标来获取下一个邻接节点
	 * @param v1 要查找哪个节点的邻接节点
	 * @param v2 v1的前一个邻接节点
	 * @return
	 */
	public int getNextNeighbor(int v1, int v2){
		for (int i = v2+1; i < vertexList.size(); i++) {
			if (edges[v1][i]>0){
				return i;
			}
		}
		return -1;
	}

	/**
	 * 深度优先遍历
	 * i 第一次就是0
	 * @param isVisited
	 * @param i
	 */
	public void dfs(boolean[] isVisited, int i){
		//首先输出
		System.out.print(getValueByIndex(i) + "->");
		//将节点设置为已经访问过
		isVisited[i] = true;
		//查找i的第一个邻接节点w
		int w = getFirstNeighbor(i);
		while (w != -1){//说明有
			if (!isVisited[w]){
				dfs(isVisited,w);
			}
			//如果w已经被访问过
			w = getNextNeighbor(i,w);
		}
	}

	/**
	 * 重载dfs
	 */
	public void dfs(){
		isVisited = new boolean[vertexList.size()];
		for (int i = 0; i < getNumOfVertex(); i++) {
			if (!isVisited[i]){
				dfs(isVisited, i);
			}
		}
	}

	/**
	 * 对一个节点进行广度优先遍历的方法
	 * @param isVisited
	 * @param i
	 */
	public void bfs(boolean[] isVisited, int i){
		int u;//表示队列的头节点对应下标
		int w;//邻接节点w
		LinkedList<Object> queue = new LinkedList<>();//记录节点访问的顺序
		System.out.print(getValueByIndex(i) + "->");

		isVisited[i] = true;

		queue.addLast(i);

		while (!queue.isEmpty()){
			//取出队列的头节点下标
			u = (int) queue.removeFirst();
			//得到第一个邻接节点的下标w
			w = getFirstNeighbor(u);
			while (w!=-1){//找到
				//是否访问过
				if (!isVisited[w]){
					System.out.print(getValueByIndex(w)+"->");
					isVisited[w]  = true;
					queue.addLast(w);
				}
				//以u为前驱点，找w后面的下一个邻接点
				w = getNextNeighbor(u,w);//体现我们的广度优先
			}
		}
	}

	/**
	 * 遍历所有的节点，都进行广度优先搜索
	 */
	public void bfs(){
		isVisited = new boolean[vertexList.size()];
		for (int i = 0; i < getNumOfVertex(); i++) {
			if (!isVisited[i]){
				bfs(isVisited,i);
			}
		}
	}
	/**
	 * 返回节点的个数
	 * @return
	 */
	public int getNumOfVertex(){
		return vertexList.size();
	}

	/**
	 * 显示图对应的矩阵
	 */
	public void showGraph(){
		for (int[] link : edges){
			System.err.println(Arrays.toString(link));
		}
	}

	/**
	 * @return 返回边的数目
	 */
	public int getNumOfEdges(){
		return numOfEdges;
	}

	/**
	 * 返回节点i对应的数据
	 * @param i
	 * @return
	 */
	public String getValueByIndex(int i){
		return vertexList.get(i);
	}

	/**
	 * 返回v1和v2的权值
	 * @param v1
	 * @param v2
	 * @return
	 */
	public int getWeight(int v1, int v2){
		return edges[v1][v2];
	}

	/**
	 * 添加节点
	 * @param vertex
	 */
	public void insertVertex(String vertex){
		vertexList.add(vertex);
	}

	public void insertEdge(int v1,int v2, int weight){
		edges[v1][v2] = weight;
		edges[v2][v1] = weight;
		numOfEdges++;
	}

}
