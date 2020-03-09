package tree.arrbinarytree;

public class ArrBinaryTreeDemo {
	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5,6,7};

		//创建一个ArrBinaryTree
		ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
		System.out.println("========数组的前序遍历=======");
		arrayBinaryTree.preOrder();//1245367
		System.out.println("========数组的中序遍历=======");
		arrayBinaryTree.infixOrder();
		System.out.println("========数组的后序遍历=======");
		arrayBinaryTree.postOrder();
	}
}
