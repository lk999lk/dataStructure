package tree.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HuffmanTree {
	public static void main(String[] args) {
		int[] arr = {13,7,8,3,29,6,1,};
		Node root = createHuffmanTree(arr);

		preOrder(root);

	}

	public static void preOrder(Node root){
		if (root != null){
			System.out.println("========= 前序遍历 =========");
			root.preOrder();
		} else {
			System.out.println("是空树，不能遍历--");
		}
	}

	/**
	 * 创建赫夫曼树的方法
	 * @param arr 需要创建成哈夫曼树的数组
	 * @return 创建好后的赫夫曼树的root节点
	 */
	public static Node createHuffmanTree(int[] arr){
		/*
		遍历arr数组
		将arr数组的每一个元素构成一个Node
		将Node加入ArrayList中
		 */
		ArrayList<Node> nodes = new ArrayList<>();
		for (int value : arr){
			nodes.add(new Node(value));
		}

		while (nodes.size()>1){
			//排序，从小到大
//			Collections.sort(nodes, new Comparator<Node>() {
//				@Override
//				public int compare(Node o1, Node o2) {
//					return o1.value-o2.value;
//				}
//			});

			Collections.sort(nodes);
			System.out.println("nodes=" + nodes);

			//取出根节点权值最小的两颗二叉树
			//（1）取出权值最小的节点（二叉树）
			Node leftNode = nodes.get(0);
			//（2）取出权值第二小的节点（二叉树）
			Node rightNode = nodes.get(1);
			//（3）构建一颗新的二叉树
			Node parent = new Node(leftNode.value+rightNode.value);
			parent.left = leftNode;
			parent.right = rightNode;
			//（4）从ArrayList删除处理过的二叉树
			nodes.remove(leftNode);
			nodes.remove(rightNode);
			//（5）将parent加入到nodes
			nodes.add(parent);
		}
		//返回哈夫曼树的root节点
		return nodes.get(0);
	}
}
