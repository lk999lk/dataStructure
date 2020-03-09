package tree.avltree;

public class AVLTreeDemo {
	public static void main(String[] args) {
		int[] arr = {10,11,7,6,8,9};
		AVLTree avlTree = new AVLTree();

		for (int i = 0; i < arr.length; i++) {
			avlTree.add(new Node(arr[i]));
		}

		System.out.println("============== 中序遍历 =============");
		avlTree.infixOrder();

		System.out.println("树的高度="+avlTree.getRoot().height());
		System.out.println("树的左子树的高度="+avlTree.getRoot().rightHeight());
		System.out.println("当前的根节点="+avlTree.getRoot());

	}
}
