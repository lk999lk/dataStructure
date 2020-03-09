package tree.huffmantree;

/**
 * 创建节点类
 * 为了让Node对象实现排序Collections集合排序，让Node实现Comparable接口
 */
public class Node implements Comparable<Node> {

	int value;//节点权值
	Node left;//指向左子节点
	Node right;//指向右子节点

	public Node(int value) {
		this.value = value;
	}

	/**
	 * 前序遍历
	 */
	public void preOrder(){
		System.out.println(this);
		if (this.left != null){
			this.left.preOrder();
		}
		if (this.right != null){
			this.right.preOrder();
		}
	}

	public String toString(){
		return "Node[value=" + value + "]";
	}

	@Override
	public int compareTo(Node o) {
		//表示从小到大排序
		return this.value-o.value;
	}
}
