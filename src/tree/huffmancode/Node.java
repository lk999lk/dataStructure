package tree.huffmancode;

/**
 * 创建节点类
 * 为了让Node对象实现排序Collections集合排序，让Node实现Comparable接口
 */
public class Node implements Comparable<Node> {

	Byte data;//存放数据(字符)本身，比如 'a'->97  ' '=32
	int weight;//节点权值
	Node left;//指向左子节点
	Node right;//指向右子节点

	public Node(Byte data, int weight) {
		this.data = data;
		this.weight = weight;
	}

	/**
	 * 前序遍历
	 */
	public void preOrder(){
		System.out.printf("%s  ",this);
		if (this.left != null){
			this.left.preOrder();
		}
		if (this.right != null){
			this.right.preOrder();
		}
	}

	@Override
	public String toString() {
		return "Node{"  + data +
				"--" + weight +
				'}';
	}

	@Override
	public int compareTo(Node o) {
		//表示从小到大排序
		return this.weight-o.weight;
	}
}
